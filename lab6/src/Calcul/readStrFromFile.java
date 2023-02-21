package Calcul;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

public class readStrFromFile {
    public static void readFromFile(String filename) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Calc stack = new Calc();
        try {
            File file = new File(filename);
            FileReader fileInput = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileInput);
            String buff="";
            while ((buff = reader.readLine()) != null) {
                if (!buff.matches("#.+")) {
                    var line = buff.toUpperCase();
                    ComandFactoryy.gg(line,stack);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String readFromBuffer() throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        System.out.println("Вводите команды");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line="";
        String buff;
        Calc stack = new Calc();
        while (null != (buff = bufferedReader.readLine()) && !buff.isEmpty()) {
            if (buff.startsWith("#")) continue;
            line = buff.toUpperCase()+" ";

            ComandFactoryy.gg(line,stack);
        }
        return line;
    }

    public static String[] splitStr(String line){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\+\\*\\/\\-]");
        var word = pattern.split(line);
        return word;
    }
}
