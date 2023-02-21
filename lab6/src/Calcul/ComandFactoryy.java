package Calcul;

import Calcul.ComandCalc.DEFINE;
import Calcul.ComandCalc.PRINT;
import Calcul.ComandCalc.SQRT;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import static Calcul.readStrFromFile.splitStr;

public class ComandFactoryy {
    public static void gg(String line,Calc stack) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        FileInputStream fileInputStream = new FileInputStream("D:\\javaaa\\lab6\\Comand.properties.txt");
        Properties properties = new Properties();
        properties.load(fileInputStream);

        var str= splitStr(line);
        Class<?> SomeClass = Class.forName(properties.getProperty(str[0]));
        Constructor<Comand> cns = (Constructor<Comand>) SomeClass.getDeclaredConstructor();
        Comand comand = cns.newInstance();
        comand.perform(str, stack);

    }
}