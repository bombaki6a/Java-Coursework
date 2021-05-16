package Actions;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public record Info(String url) implements Action, ActionListener {
    // Създаване на клас, който достъпва приложениея от машината
    private final static Desktop desktop = Desktop.getDesktop();

    // Функция за задаване на действие към бутон
    @Override
    public void Listening(JButton parent) {
        if (parent != null) parent.addActionListener(this);
    }

    @Override
    public void Listening(JLabel parent) {

    }

    // Функция позволяваща при натискате на бутона да се отвори зададения линк
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            desktop.browse(new URI(url));
        } catch (URISyntaxException | IOException error) {
            error.printStackTrace();
        }
    }
}
