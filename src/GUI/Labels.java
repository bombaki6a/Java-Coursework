package GUI;

import Actions.Action;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;


public class Labels extends JLabel {
    public Labels(String title, Color color) {
        super(title); // Заване на текст
        setOpaque(true); // Премахване на прозрачността на фона
        setBackground(color); // Задаване на цят на фона
        setHorizontalAlignment(SwingConstants.CENTER); // Подраняване в центъра
    }

    public Labels(Color color) {
        setOpaque(true); // Премахване на прозрачността на фона
        setBackground(color); // Задаване на цвят на фона
        setHorizontalAlignment(SwingConstants.CENTER); // Подравянавне в центъра
    }

    // Функция за задаване на действие
    public void SetListening(Action e) {
        if (e != null) e.Listening(this);
    }

    // Функция за задаване на размера на етикета
    public void SetSize(Integer width, Integer height) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
    }

    // Функция за задаване на стила, размера и цевена на шрифта
    public void SetFont(Font font, Color color) {
        setForeground(color);
        setFont(font);
    }

    // Функция за добвяне на рамка с определена дебилина
    public void SetBorder(Color color, Integer thickness) {
        setBorder(BorderFactory.createLineBorder(color, thickness));
    }
}
