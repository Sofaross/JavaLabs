package Calcul.ComandCalc;

import Calcul.Calc;
import Calcul.Comand;

public class DEFINE extends Comand {
    @Override
    public void perform(String[] str, Calc stack){
        int i=0;
        stack.getMap().put(str[i+1], Double.valueOf(str[i+2]));
    }
}