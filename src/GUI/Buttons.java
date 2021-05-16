package GUI;

import Actions.Action;

import Fonts.TitleFonts;
import Colors.TitleColor;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;


class Buttons extends JButton {
    private Color hover; // Цявят при посочване на бутона
    private Color pressed; // Цвят при натискате на бутона
    private Color background; // Фоновия цвят на бутона

    public Buttons(String title) {
        super(title); // Текста в бутона
        super.setContentAreaFilled(false); // Премахване на елементите на бутона

        setFocusable(false); // Премахване на фокуса от бутона
        setBorderPainted(false); // Премахване на рамките на бутона
        SetFont(TitleFonts.buttons, TitleColor.FONT); // Задаване на шрифт на бутона
        setHorizontalAlignment(SwingConstants.CENTER); // Подравняване в центъра
    }

    // Функция за задаване на действие
    public void SetListening(Action action) {
        if (action != null) action.Listening(this);
    }

    // Функция за задавне на размерите на бутона
    public void SetSize(Integer width, Integer height) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
    }

    // Функция за задаване на стила, размер и цвят на шрифта
    public void SetFont(Font font, Color color) {
        setFont(font);
        setForeground(color);
    }

    // Задава на цветове на бутона
    public void SetColors(Color hover, Color pressed, Color background) {
        this.hover = hover;
        this.pressed = pressed;
        this.background = background;
    }

    // Функция за задаване на рамка на бутона
    public void SetBorder(Color color, Integer thickness) {
        setBorderPainted(true);
        setBorder(BorderFactory.createLineBorder(color, thickness));
    }

    // Функция за изчертаване на бутона
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) g.setColor(pressed);
        else if (getModel().isRollover()) g.setColor(hover);
        else g.setColor(background);

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
