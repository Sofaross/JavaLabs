package Calcul.ComandCalc;

import Calcul.Calc;
import Calcul.Comand;

public class POP extends Comand {
    @Override
    public void perform(String[] str, Calc stack){
        stack.getStack().pop();
    }
}