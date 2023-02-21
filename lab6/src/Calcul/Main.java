package Calcul;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static Calcul.readStrFromFile.*;

public class Main {
    public static void main(String args[]) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (args.length !=0) {
            String filename = args[0];
            readFromFile(filename);
        }
        else readFromBuffer();
    }
}
