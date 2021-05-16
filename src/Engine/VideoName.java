package Engine;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class VideoName {
    // Създаване на променлива за линка
    public String link;

    // Създаване на настойките на API-то
    private final static String api = "api/youtube-vn.exe ";
    private final static String[] removeNullCharacters = { "\0", "" };
    private final static Character[] invalidCharacters = { '"', '*', '<', '>', '?', '/', '\\', '|' };

    public VideoName(String link) {
        this.link = link; // Присвояване на лика
    }

    // Функция, която стартира подпроцес и връща името на видеоклипа
    public String getName() throws IOException, InterruptedException {
        String name;
        String path = api + link;
        Process process = Runtime.getRuntime().exec(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder str = new StringBuilder();

        while ((name = reader.readLine()) != null) str.append(name);
        name = str.toString();
        process.waitFor();

        for (Character c : invalidCharacters) name = name.replace(c, Character.MIN_VALUE);
        return name.replace(removeNullCharacters[0], removeNullCharacters[1]);
    }
}
