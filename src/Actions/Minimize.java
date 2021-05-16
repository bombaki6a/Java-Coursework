package Actions;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;


public class Minimize implements Action, ActionListener {
    // Създаване на достъп до главният прозорец
    public Frame parent;

    public Minimize(Frame parent) {
        this.parent = parent; // Присвояване на главния прозорец
    }

    // Функция за задаване на действие към бутона за минимизация
    @Override
    public void Listening(JButton parent) {
        if (parent != null) parent.addActionListener(this);
    }

    @Override
    public void Listening(JLabel parent) {

    }

    // Функция, която се изпълнява при натискате на зададения бутон и минимизира програмата
    @Override
    public void actionPerformed(ActionEvent e) {
        parent.setExtendedState(Frame.ICONIFIED);
    }
}
