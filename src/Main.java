import GUI.Frame;
import GUI.Content;
import GUI.TitleBar;


public class Main {
    public static void main (String[] args) {
        // Заглавието на името на програмата и нейните размери
        String title = "[ youtube-dl ]";
        Integer windowWidth = 820;
        Integer windowHeight = 408;

        // Височината на заглавната лента и основния прозорец
        Integer barHeight = 38;
        Integer contentHeight = windowHeight - barHeight;

        // Създаване на тялото, заглавната лента и полето със съдържание
        Frame window = new Frame(title, windowWidth, windowHeight);
        TitleBar titleBar = new TitleBar(window, title, windowWidth, barHeight);
        Content content = new Content(windowWidth, contentHeight);

        // Добаване към тялото заглавната леста и полето със съдържание
        window.Add(titleBar, 0);
        window.Add(content, 1);

        // Визоализиране на тялото (прогламата)
        window.setVisible(true);
    }
}
