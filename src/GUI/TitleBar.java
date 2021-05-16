package GUI;

import Actions.Exit;
import Actions.Info;
import Actions.Dragged;
import Actions.Minimize;

import Fonts.TitleFonts;
import Colors.TitleColor;

import java.awt.Frame;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;


public class TitleBar extends JPanel {
    // Ширина на заглавието и бутоните
    private final static Integer titleWidth = 152;
    private final static Integer buttonsWidth = 60;

    // Визуалните символи на бутоните
    private final static String exitSymbol = "X";
    private final static String infoSymbol = "?";
    private final static String minimizeSymbol = "-";

    // Създаване на елемените на заглавната лента
    private final static Buttons exit = new Buttons(exitSymbol);
    private final static Buttons info = new Buttons(infoSymbol);
    private final static Buttons minimize = new Buttons(minimizeSymbol);
    private final static Labels titleName = new Labels(TitleColor.BACKGROUND);

    // Ограниченията в мрежата на заглавната лента и линк към информация за програмага
    private final static GridBagConstraints constraints = new GridBagConstraints();
    private final static String infoURL = "https://github.com/bombaki6a/Java-Coursework";

    public TitleBar(Frame parent, String title, Integer width, Integer height) {
        setLayout(new GridBagLayout()); // Задаване на мрежовидно оформление
        setBackground(TitleColor.BACKGROUND); // Задаване на фоновият цвят
        setPreferredSize(new Dimension(width, height)); // Задаване на размерите на заглавната лента

        // Остояния в мрежата
        int top = 0, bottom = 0, right = 0;
        int left = (width / 2) - (titleWidth / 2);

        // Избиране на поле в межата и задавен на остояниета в него
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(top, left, bottom, right);

        // Добавяне на заглавие към заглавната лента
        titleName.setText(title);
        titleName.SetSize(titleWidth, height);
        titleName.SetFont(TitleFonts.title, TitleColor.FONT);
        add(titleName, constraints);

        // Промяна на остоянието от ляво
        left -= (buttonsWidth * 3);

        // Избиране на поле в межата и задавен на остояниета в него
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(top, left, bottom, right);

        // Добавяне на бутона за минимизация в заглавната лента
        minimize.SetSize(buttonsWidth, height);
        minimize.SetListening(new Minimize(parent));
        minimize.SetColors(TitleColor.M_HOVER, TitleColor.M_PRESSED, TitleColor.BACKGROUND);
        add(minimize, constraints);

        // Промяна на остоянието от ляво
        left = 0;

        // Избиране на поле в межата и задавен на остояниета в него
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.insets = new Insets(top, left, bottom, right);

        // Добавяне на бутона за информация в заглавната лента
        info.SetSize(buttonsWidth, height);
        info.SetListening(new Info(infoURL));
        info.SetColors(TitleColor.M_HOVER, TitleColor.M_PRESSED, TitleColor.BACKGROUND);
        add(info, constraints);

        // Промяна на остоянието от ляво
        left = 0;

        // Избиране на поле в межата и задавен на остояниета в него
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.insets = new Insets(top, left, bottom, right);

        // Добавяне на бутона за затваряне на програмата в заглавната лента
        exit.SetSize(buttonsWidth, height);
        exit.addActionListener(new Exit(parent));
        exit.SetColors(TitleColor.E_HOVER, TitleColor.E_PRESSED, TitleColor.BACKGROUND);
        add(exit, constraints);

        // Добавяне на действие за местене на прогрмата към заглавната лента
        new Dragged(parent).Listening(this);
    }
}
