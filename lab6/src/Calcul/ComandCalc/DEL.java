package Calcul.ComandCalc;

import Calcul.Calc;
import Calcul.Comand;

public class DEL extends Comand {
    @Override
    public void perform(String[] str, Calc stack){
        try{
            double del = stack.getStack().pop() / stack.getStack().pop();
            stack.getStack().push(del);
        }
        catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}