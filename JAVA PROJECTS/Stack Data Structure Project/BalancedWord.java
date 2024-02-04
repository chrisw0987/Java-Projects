package cse214hw2;

import java.util.Stack;

public class BalancedWord {
    private final String word;
    public BalancedWord(String word) {
        if (isBalanced(word))
            this.word = word;
        else
            throw new IllegalArgumentException(String.format("%s is not a balanced word.", word));
    }
    private static boolean isBalanced(String word) {
        Stack<Character> stack = new Stack<>();
        for (char ch = 0 ; ch <= word.toCharArray().length; ch++) {
            if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    } // TODO
    public String getWord() {
        return word;
    }


}

