package Engine;

import java.io.IOException;

import java.util.Arrays;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;


public class InputLink {
    // Създаване на променлива за линка
    public String link;

    // Помощни функции за обратката и проверкана на линка
    private final static String[] split = { "&t=", "&" };
    private final static String[] contain = { "www.youtube.com/watch?v=", "https://youtu.be/" };

    public InputLink() throws IOException, UnsupportedFlavorException {
        // Вземане на линка от клипборда
        link = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        // Обработка на линка до приемлив вид
        for (String s : split) if (link.contains(s)) link = link.split(s)[0];
    }

    // Променлива, която връща true или false след проверка на линка
    public boolean isLinkCorrect() {
        return isLinkCorrect(link);
    }

    // Променлива, която връща true или false след проверка на линка, който му се подаде
    public static boolean isLinkCorrect(String link) {
        if (link != null) return Arrays.stream(contain).anyMatch(link::contains);
        else return false;
    }
}
