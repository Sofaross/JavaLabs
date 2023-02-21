package Calcul.ComandCalc;

import Calcul.Calc;
import Calcul.Comand;

public class PUSH extends Comand {
    @Override
    public void perform(String[] str, Calc stack){
        int i=0;
            if (number(str[i+1]))
            {
                stack.getStack().push(Double.valueOf(str[i+1]));
            }
            else stack.getStack().push(stack.getMap().get(str[i+1]));
    }
public  boolean number(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
