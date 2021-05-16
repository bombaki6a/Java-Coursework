package Engine;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.awt.Component;

import javax.swing.JLabel;


public class DownloadMusic extends Thread {
    // Създаване на връзки към нужните обекти от главния прозорец
    private final JLabel input;
    private final JLabel percentages;
    private final Component parent;
    private final Component button;

    // Създаване на вързка за пътя и помощна променлива за процентите
    private String path;
    private Integer percentage = 0;

    // Създаване на настройките на API-то
    private final static String extension = ".mp3";
    private final static String api = "api/youtube-dl.exe ";
    private final static String settings = " -f bestaudio[ext=webm] -o ";

    // Създаване на помощни променливи за обработка на текста, който хвърля API-то
    private final static String[] replace = { " ", "" };
    private final static String[] split = { "]", "%of" };

    public DownloadMusic(Component parent, JLabel input, Component button, JLabel percentages, String path) {
        this.path = path; // Присвояване на пътя на файла
        this.input = input; // Присвояване на полето за линкове
        this.parent = parent; // Присвояване на бутона за музика
        this.button = button; // Присвояване на бутона за видео
        this.percentages = percentages; // Присвояване на процентите
    }

    // Функция, която при стартиране на нишката, ще се изпълни
    @Override
    public void run() {
        path = String.format("\"%s\"", path) + extension;
        String music = api + input.getText() + settings + path;

        try {
            Process process = Runtime.getRuntime().exec(music);
            Stream(new BufferedReader(new InputStreamReader(process.getInputStream())));

            input.setEnabled(true);
            button.setEnabled(true);
            parent.setEnabled(true);

            percentages.setText("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Функция, която чете хвърления текст от API-то и го обработва до проценти
    private void Stream(BufferedReader reader) throws IOException {
        String name;
        String getP = null;

        if (reader != null) {
            while ((name = reader.readLine()) != null) {
                String repData = name.replace(replace[0], replace[1]);
                if (!repData.isEmpty()) getP = repData.split(split[0])[1].split(split[1])[0];

                assert getP != null;
                if (!getP.contains(":")) {
                    int cleanP = (int) Float.parseFloat(getP);
                    if (cleanP > percentage) {
                        percentage = (int)(((float) cleanP / 100) * 100);
                        percentages.setText(percentage + "%");
                    }
                }
            }
        }
    }
}
