package Fonts;

import java.awt.Font;


// Интерфейс съдържащ всички стилове шрифт на основния прозорец
public interface TitleFonts {
    String fTitle = "Cambria";
    String fButtons = "Arial";

    Integer titleSize = 26;
    Integer buttonsSize = 14;

    Font title = new Font(fTitle, Font.PLAIN, titleSize);
    Font buttons = new Font(fButtons, Font.BOLD, buttonsSize);
}
