package cse214hw2;

import cse214hw2.Converter;
import java.util.Stack;

import static cse214hw2.Operator.isOperator;

public class ToPostfixConverter implements Converter {


    @Override
    public String convert(ArithmeticExpression expression) {
        Stack<Operator> stack = new Stack<Operator>();
        String infix = expression.getExpression().replaceAll(" ", "");
        TokenBuilder postfix = new TokenBuilder();
        for (int i = 0; i < infix.length(); ) {
            if (isOperand(Character.toString(infix.charAt(i)))) {//CHECK IF OPERAND, IF SO, DUMP INTO POSTFIX
                String multidigit = nextToken(infix,i);
                int x ;
                for (x = 0; x < multidigit.length(); x++) {
                    postfix.append(multidigit.charAt(x));
                }
                postfix.append(' ');
                i = i + x;
            }
            else if (infix.charAt(i) == Operator.LEFT_PARENTHESIS.getSymbol()) {  //5A CONDITION
                stack.push(Operator.LEFT_PARENTHESIS);
                i++;
            }
            else if (infix.charAt(i)== Operator.RIGHT_PARENTHESIS.getSymbol()) {
                while (!stack.isEmpty() && stack.peek() != Operator.LEFT_PARENTHESIS) {
                    postfix.append(stack.pop().getSymbol());
                    postfix.append(' ');
                }
                if(!stack.isEmpty() && stack.peek() == Operator.LEFT_PARENTHESIS)
                {
                    stack.pop();
                }
                i++;
            }
            else if(isOperator(infix.charAt(i))) {
                Operator op = Operator.of(infix.charAt(i));
                int rank = op.getRank();

                if(stack.isEmpty() || stack.peek() == Operator.LEFT_PARENTHESIS) {
                    stack.push(op);
                }
                else if (rank < stack.peek().getRank()) {
                    stack.push(op);
                }
                else if(rank >= stack.peek().getRank()) {
                    while(!stack.isEmpty() && rank >= stack.peek().getRank() && stack.peek() != Operator.LEFT_PARENTHESIS) {
                        postfix.append(stack.pop().getSymbol());
                        postfix.append(' ');
                    }
                    stack.push(op);
                }
                i++;
            }

        }
            while(!stack.isEmpty()) {
                postfix.append(stack.pop().getSymbol());
                postfix.append(' ');
            }
        return postfix.build();
    }

    @Override
    public String nextToken(String s, int start) {
        TokenBuilder newString = new TokenBuilder();
        int i = start;
        while ((s.charAt(i) >= 48 && s.charAt(i) <= 57) || s.charAt(i) == '.') { //48 & 57 ASCII TABLE
                newString.append(s.charAt(i));
            if (i + 1 < s.length()) {
                i++;
            }
            else {
                break;
            }
        }
        return newString.build();
    }

    @Override
    public boolean isOperand(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isOperator(s.charAt(i)) || s.charAt(i) == Operator.LEFT_PARENTHESIS.getSymbol() || s.charAt(i) == Operator.RIGHT_PARENTHESIS.getSymbol()) {
                return false;
            }
        }
        return true;
    }
}