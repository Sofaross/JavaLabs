package Calcul.ComandCalc;
import Calcul.Calc;
import Calcul.Comand;

public class PRINT extends Comand {

    @Override
    public void perform(String[] str, Calc stack){
        System.out.println(stack.getStack());
    }
}