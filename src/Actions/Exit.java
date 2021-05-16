package Actions;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;


public class Exit implements Action, ActionListener {
    // Създаване на достъп до главния прозорец и статуса на изход
    public Frame parent;
    private final static Integer status = 0;

    public Exit(Frame parent) {
        this.parent = parent; // Присвояване на главния прозорец
    }

    // Функция за задаване на действие към бутона за изход от програмата
    @Override
    public void Listening(JButton parent) {
        if (parent != null) parent.addActionListener(this);
    }

    @Override
    public void Listening(JLabel parent) {

    }

    // Функция за затваряне на прогламата при натискането на бутона за изход
    @Override
    public void actionPerformed(ActionEvent e) {
        parent.dispose();
        System.exit(status);
    }
}
