package Calcul;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Calc {
    private Stack<Double> stack= new Stack<>();
    private Map<String,Double> map= new HashMap<>();

    public Stack<Double> getStack() {return stack;}
    public  Map<String,Double> getMap() {
        return map;
    }
}

