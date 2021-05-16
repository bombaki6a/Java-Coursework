package Engine;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.awt.Component;
import javax.swing.JLabel;


public class DownloadVideo extends Thread {
    // Създаване на връзки към нужните ни обекти от главния прозорец
    private final JLabel input;
    private final JLabel percentages;
    private final Component parent;
    private final Component button;

    // Създаване на връзка за пътя на файла
    private final String path;

    // Създаване на помощни променливи за проценитите
    private Integer bonus = 0;
    private Integer percentage = 0;

    // Създаване на настройките на API-то
    private final static String api = "api/youtube-dl.exe ";
    private final static String videoSettings = " -f bestvideo[ext=webm] -o video.webm";
    private final static String audioSettings = " -f bestaudio[ext=webm] -o audio.webm";

    // Създаване на помощни променливи за обработка на текста, който хвърля API-то
    private final static String[] replace = { " ", "" };
    private final static String[] split = { "]", "%of" };

    public DownloadVideo(Component parent, JLabel input, Component button, JLabel percentages, String path) {
        this.path = path; // Присвояване на пътя на файла
        this.input = input; // Присвояване на полето за линкове
        this.parent = parent; // Присвояване на бутона за видео
        this.button = button; // Присвояване на бутона за музика
        this.percentages = percentages; // Присвояване на процентите
    }

    // Функция, която при стартиране на нишката ще се изпълни
    @Override
    public void run() {
        String video = api + input.getText() + videoSettings;
        String audio = api + input.getText() + audioSettings;

        try {
            Process videoProcess = Runtime.getRuntime().exec(video);
            Stream(new BufferedReader(new InputStreamReader(videoProcess.getInputStream())));

            Process audioPrecess = Runtime.getRuntime().exec(audio);
            Stream(new BufferedReader(new InputStreamReader(audioPrecess.getInputStream())));

            Merge merging = new Merge(String.format("\"%s\"", path), percentages, percentage);
            merging.start();
            merging.join();

            input.setEnabled(true);
            button.setEnabled(true);
            parent.setEnabled(true);
        } catch (IOException | InterruptedException e) { e.printStackTrace(); }
    }

    // Функция, която чете хвърления текст от API-то и го обработва в проценти
    private void Stream(BufferedReader reader) throws IOException {
        String line;
        String getP = null;

        if (reader != null) {
            while ((line = reader.readLine()) != null) {
                String repData = line.replace(replace[0], replace[1]);
                if (!repData.isEmpty()) getP = repData.split(split[0])[1].split(split[1])[0];

                assert getP != null;
                if (!getP.contains(":")) {
                    int cleanP = (int)((((int)Float.parseFloat(getP)) * 0.35) + bonus);
                    if (cleanP > percentage) {
                        percentage = cleanP;
                        percentages.setText(percentage + "%");
                    }
                }
            }
        }

        bonus = percentage;
    }
}
