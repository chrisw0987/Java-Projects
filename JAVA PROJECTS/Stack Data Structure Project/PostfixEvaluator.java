package cse214hw2;

import cse214hw2.Evaluator;

import java.sql.SQLOutput;
import java.util.Stack;

import static cse214hw2.Operator.isOperator;

public class PostfixEvaluator implements Evaluator {

    @Override
    public double evaluate(String expressionString) {
        Stack<Double> stack = new Stack<Double>();
        String[] splitExpression = expressionString.split(" ");
        double a;
        double b;
        double math = 0;
        for (int i = 0; i < splitExpression.length; i++) {
            if ((!splitExpression[i].isEmpty()) && !(Operator.isOperator(splitExpression[i]))) {//IF NOT OPERATOR
                stack.push(Double.parseDouble(splitExpression[i]));
            }
            else {
                if (!stack.isEmpty()) {
                b = stack.pop();
                a = stack.pop();
                switch (splitExpression[i]) {
                    case "+":
                        math = a + b;
                        break;
                    case "-":
                        math = a - b;
                        break;
                    case "*":
                        math = a * b;
                        break;
                    case "/":
                        math = Math.round((a / b)*100)/100.0;
                        break;
                }
                stack.push(math);
            }
            }
        }
        //return Math.round(stack.pop());
        if(!stack.isEmpty()) {
            return stack.pop();
        }
        else {
            return -1.0;
        }
        }
    }
