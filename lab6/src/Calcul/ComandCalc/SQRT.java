package Calcul.ComandCalc;

import Calcul.Calc;
import Calcul.Comand;

public class SQRT extends Comand {
    @Override
    public void perform(String[] str, Calc stack){
        stack.getStack().push(Math.sqrt(stack.getStack().pop()));
    }
}