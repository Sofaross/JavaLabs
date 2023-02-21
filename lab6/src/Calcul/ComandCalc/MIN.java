package Calcul.ComandCalc;
import Calcul.Calc;
import Calcul.Comand;


public class MIN extends Comand {
    @Override
    public void perform(String[] str, Calc stack){
        stack.getStack().push(stack.getStack().pop() - stack.getStack().pop());
    }
}