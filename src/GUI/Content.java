package GUI;

import Actions.Input;
import Actions.Download;

import Fonts.ContentFonts;
import Colors.ContentColor;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.BorderFactory;


public class Content extends JPanel {
    // Размери на елементите на основия прозорец
    private final static Integer inputWidth = 560;
    private final static Integer inputHeight = 36;
    private final static Integer buttonsWidth = 260;
    private final static Integer buttonsHeight = 80;
    private final static Integer percentagesWidth = 550;
    private final static Integer percentagesHeight = 100;

    // Текстовете на елементите на основия прозорец
    private final static String percentagesText = "0%";
    private final static String inputText = "[ Link ]";
    private final static String videoText = "Download Video";
    private final static String musicText = "Download Music";

    // Създване на елементите на основия прозорец
    private final static Buttons video = new Buttons(videoText);
    private final static Buttons music = new Buttons(musicText);
    private final static Labels input = new Labels(inputText, ContentColor.BACKGROUND);
    private final static Labels percentages = new Labels(percentagesText, ContentColor.BACKGROUND);

    // Дебелината на рамките и ограниченията на елементите
    private final static Integer borderThickness = 1;
    private final static Integer panelBorderThickness = 2;
    private final static GridBagConstraints constraints = new GridBagConstraints();

    public Content(Integer width, Integer height) {
        setLayout(new GridBagLayout()); // Задаване на мрежовидно оформление
        setBackground(ContentColor.BACKGROUND); // Задавен на фоновия цвят
        setPreferredSize(new Dimension(width, height)); // Задаване на размерите на основия прозорец
        setBorder(BorderFactory.createLineBorder(ContentColor.W_BORDER, panelBorderThickness)); // Съзване на рамка

        // Остояние в мрежата
        int top = 20;
        int left = 0, bottom = 0, right = 0;

        // Избиране на поле в межата и задавен на остояниета в него
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(top, left, bottom, right);

        // Добавяне на полето за линкове към основния прозорец
        input.SetSize(inputWidth, inputHeight);
        input.SetBorder(ContentColor.BORDER, borderThickness);
        input.SetFont(ContentFonts.input, ContentColor.FONT);
        input.SetListening(new Input());
        add(input, constraints);

        // Промяна на остоянието в дясно
        right = 300;

        // Избиране на поле в межата и задавен на остояниета в него
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(top, left, bottom, right);

        // Добаване на бутона за сваляне на клип към основния прозорец
        video.setName("Video");
        video.SetSize(buttonsWidth, buttonsHeight);
        video.SetBorder(ContentColor.BORDER, borderThickness);
        video.SetFont(ContentFonts.buttons, ContentColor.FONT);
        video.SetListening(new Download(input, music, percentages));
        video.SetColors(ContentColor.HOVER, ContentColor.PRESSED, ContentColor.BACKGROUND);
        add(video, constraints);

        // Промяна на остояниеята в дяско и ляво
        right = 0;
        left = 300;

        // Избиране на поле в межата и задавен на остояниета в него
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(top, left, bottom, right);

        // Добавяне на бутона за сваляне на музика към основния прозорец
        music.setName("Music");
        music.SetSize(buttonsWidth, buttonsHeight);
        music.SetBorder(ContentColor.BORDER, borderThickness);
        music.SetFont(ContentFonts.buttons, ContentColor.FONT);
        music.SetListening(new Download(input, video, percentages));
        music.SetColors(ContentColor.HOVER, ContentColor.PRESSED, ContentColor.BACKGROUND);
        add(music, constraints);

        // Промяна на остоянията в ляво и отгоре
        left = 0;
        top = 20;

        // Избиране на поле в межата и задавен на остояниета в него
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(top, left, bottom, right);

        // Добавяне на процентите към основния прозорец
        percentages.SetSize(percentagesWidth, percentagesHeight);
        percentages.SetFont(ContentFonts.percentages, ContentColor.FONT);
        add(percentages, constraints);
    }
}
