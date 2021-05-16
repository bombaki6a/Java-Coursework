package Engine;

import java.io.File;


public class Cleaner {
    // Създаване на раширенията които търсим и пътя на директорията, в която ги търсим
    private final static String webm = ".webm";
    private final static String part = ".part";
    private final static String path = System.getProperty("user.dir");

    // Вземане на списък на файлове в посочената директория
    private final static File[] fList = new File(path).listFiles();

    // Функция за изчестване на файловете с дадените разширения
    public static void Cleaning() {
        assert fList != null;
        for (File f : fList) {
            if ((f.getName().endsWith(webm)) || (f.getName().endsWith(part))) {
                boolean delete = f.delete();
            }
        }
    }
}
