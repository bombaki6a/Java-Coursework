package Actions;

import java.awt.Frame;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Dragged implements MouseListener, MouseMotionListener {
    // Създаване на x и y кординатите на мишката
    private Integer x;
    private Integer y;

    // Създаване на достъп до главия прозорец
    private final Frame parent;

    public Dragged(Frame parent) {
        this.parent = parent; // Присвояване на главия прозорец
    }

    // Функция за задаване на действиета към заглавното поле
    public void Listening(Component parent) {
        if (parent != null) {
            parent.addMouseListener(this);
            parent.addMouseMotionListener(this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    // Футкция за вземане на кординатите на мишката
    @Override
    public void mousePressed(MouseEvent e) {
        if (e != null) {
            x = e.getX();
            y = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Функция за вземане на кординатите от влаченото на мишката и прилагането им
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = 0;
        int y = 0;

        if (e != null) {
            x = e.getXOnScreen() - this.x;
            y = e.getYOnScreen() - this.y;
        }

        parent.setLocation(x, y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
