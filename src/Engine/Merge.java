package Engine;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JLabel;


public class Merge extends Thread {
    // Създаване на вързка с прозените и пътя на файла
    private final String path;
    private final JLabel label;

    // Създаване на помощна променлива за прозентите
    private Integer percentage;

    // Създаване на настройките на API-то
    private final static String extension = ".mp4";
    private final static String api = "api/ffmpeg.exe ";
    private final static String settings = "-i audio.webm -i video.webm -acodec aac -ac 2 -vcodec copy ";

    // Създаване на помощни променливи за обработката на текста, който API-то хвърля
    private final static String[] replace = { ":", "" };
    private final static String[] split = { "Duration: ", ",", "time=", " ", ":" };

    public Merge(String path, JLabel label, Integer percentage) {
        this.path = path; // Присвояване на пътя
        this.label = label; // Присвояване на процентите
        this.percentage = percentage; // Присвояване на процените до къдете е стигнала програмата
    }

    // Функция, която при стартиране на нишката ще се изпълни
    @Override
    public void run() {
        String fullPath = api + settings + path + extension;

        try {
            Process merge = Runtime.getRuntime().exec(fullPath);
            Stream(new BufferedReader(new InputStreamReader(merge.getErrorStream())));
            merge.waitFor();
            Cleaner.Cleaning();
            label.setText("Done!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Функция, която чете хвърления тект от API-то и го обработва в проценти
    private void Stream(BufferedReader reader) throws IOException {
        String line;
        int currentTime;
        int percentageOfTime;
        Integer baseTime = null;
        Integer bonus = percentage;

        if (reader != null) {
            while((line = reader.readLine()) != null) {
                if ((line.contains(split[0])) && (baseTime == null)) {
                    line = line.split(split[0])[1].split(split[1])[0].replace(replace[0], replace[1]);
                    baseTime = Math.round(Float.parseFloat(line));
                }

                if (line.contains(split[2])) {
                    assert baseTime != null;
                    line = line.split(split[2])[1].split(split[3])[0].replace(replace[0], replace[1]);
                    currentTime = Math.round(Float.parseFloat(line));
                    percentageOfTime = (int)(((float) currentTime / baseTime) * 100);
                    percentageOfTime = (int)(((float)percentageOfTime * 0.30) + bonus);

                    if (percentageOfTime > percentage) {
                        percentage = percentageOfTime;
                        label.setText(percentage + "%");
                    }
                }
            }
        }
    }
}
