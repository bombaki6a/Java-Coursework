package GUI;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;


public class Frame extends JFrame {
    private static final Float opacity = 0.95f; // стойността на прозрачно на програмагам
    private final GridBagConstraints constraints = new GridBagConstraints(); // Ограничения

    public Frame(String title, Integer width, Integer height) {
        super(title); // Задаване на име на програмата
        setSize(width, height); // Задаване на размерите на прогрмата
        setLayout(new GridBagLayout()); // Задаване на мрежовидно оформление

        setResizable(false); // Премахване на оразмеряването
        setUndecorated(true); // Премахване на стандартната рамка на програмата (премахване на заглавната лента)

        setOpacity(opacity); // Задаване на определената прозрачност
        setLocationRelativeTo(null); // Центриране на прозореца в средата на екрана
        setDefaultCloseOperation(EXIT_ON_CLOSE); // При затваряне на прозореца да се изключи и програмата
    }

    // Функкция за добавяне на елемевти към тялото на програмата
    public void Add(Component component, Integer gridY) {
        constraints.gridx = 0;
        constraints.gridy = gridY;

        if (component != null) add(component, constraints);
    }
}
