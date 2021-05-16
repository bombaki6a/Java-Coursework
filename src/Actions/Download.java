package Actions;

import Engine.InputLink;
import Engine.VideoName;
import Engine.DownloadVideo;
import Engine.DownloadMusic;

import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Download implements Action, ActionListener {
    // Създаване на достъп до определени класове и създаване на прозорец за записване файл
    private final JLabel input;
    private final JButton button;
    private final JLabel percentages;
    private final JFileChooser fileChooser = new JFileChooser();

    // Създаване на достъп до родителският клас и типа на файловете, които ще запазваме
    private Component parent;
    private String extension;
    private String description;

    public Download(JLabel input, JButton button, JLabel percentages) {
        this.input = input; // Присвояване на полето за линкове
        this.button = button; // Присвояване на един от бутоните
        this.percentages = percentages; // Присвояване на процентите

        fileChooser.setAcceptAllFileFilterUsed(false); // Премахваме опцията да показва всички файлове
    }

    // Функция за поемане на зададеното дейстие от бутон
    @Override
    public void Listening(JButton parent) {
        this.parent = parent; // Присвояваме бутова използащ този клас, за да може да го коригираме

        // Определяне на файловете, които да се виждат в изкачащия прозорец
        if (parent != null) {
            switch (parent.getName()) {
                case "Video" -> {
                    description = "Video";
                    extension = "mp4";
                }
                case "Music" -> {
                    description = "Music";
                    extension = "mp3";
                }
            }

            parent.addActionListener(this); // Слагане на бутона в изчакване за определено действие
        }

        // Добавяме към изкачащия прозорец филтър за файловете, които да се показват
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(description, extension));
    }

    @Override
    public void Listening(JLabel parent) {

    }

    // Фукция, която се изпълня при натискането на бутона
    @Override
    public void actionPerformed(ActionEvent e) {
        // Вземане на името на клипа
        VideoName videoName = new VideoName(input.getText());

        // Поставяне на името на клипа като припоръчително в изкачащия прозорец
        try {
            fileChooser.setSelectedFile(new File(videoName.getName()));
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }

        // Проверка за коректността на линкът
        if (InputLink.isLinkCorrect(input.getText())) {
            // Показване на изкачащия прозорец
            if (fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
                // Забраняве на порзването на бутоните и полетоза линкове
                parent.setEnabled(false);
                input.setEnabled(false);
                button.setEnabled(false);

                // Добавяне на пътя за записване на файла
                String path = fileChooser.getSelectedFile().getAbsolutePath();

                // Определяне на дали да се свали клип или музика
                switch (parent.getName()) {
                    case "Video" -> {
                        DownloadVideo video = new DownloadVideo(parent, input, button, percentages, path);
                        video.start();
                    }
                    case "Music" -> {
                        DownloadMusic music = new DownloadMusic(parent, input, button, percentages, path);
                        music.start();
                    }
                }
            }
        }
    }
}
