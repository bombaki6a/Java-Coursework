package Actions;

import javax.swing.JLabel;
import javax.swing.JButton;

// Този интерфейс е създаден за улеснява на задаването на действията към обектите
public interface Action {
    void Listening(JButton parent);
    void Listening(JLabel parent);
}
