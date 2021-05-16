package Actions;

import Engine.InputLink;

import Colors.ContentColor;

import java.io.IOException;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.JLabel;
import javax.swing.JButton;


public class Input implements Action, MouseListener {
    // Създаване на достъп но до полето за линкове
    private JLabel parent;

    @Override
    public void Listening(JButton parent) {

    }

    // Функция за задаване на дейстиве към полето за линкове
    @Override
    public void Listening(JLabel parent) {
        this.parent = parent;
        this.parent.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    // Функция, която поставя линк в полето за линкове, когато натиснем върху него
    @Override
    public void mousePressed(MouseEvent e) {
        try {
            InputLink input = new InputLink();
            if (input.isLinkCorrect()) parent.setText(input.link);
        } catch (IOException | UnsupportedFlavorException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    // Функция, която променя цвета на текста в полето за линкове, когато мишката е върху него
    @Override
    public void mouseEntered(MouseEvent e) {
        parent.setForeground(ContentColor.H_FONT);
    }

    // Функция, която променя цвета на текста в полето за линкове, когато дръпнем мишката от него
    @Override
    public void mouseExited(MouseEvent e) {
        parent.setForeground(ContentColor.FONT);
    }
}
