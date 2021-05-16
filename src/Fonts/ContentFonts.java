package Fonts;

import java.awt.Font;


// Интерфейс съдържащ стиловете шрифт на основния прозорец
public interface ContentFonts {
    String fInput = "Arial";
    String fButtons = "Cambria";
    String fPercentages = "Comic Sans MS";

    Integer inputSize = 24;
    Integer buttonsSize = 28;
    Integer percentagesSize = 70;

    Font input = new Font(fInput, Font.PLAIN, inputSize);
    Font buttons = new Font(fButtons, Font.BOLD, buttonsSize);
    Font percentages = new Font(fPercentages, Font.PLAIN, percentagesSize);
}
