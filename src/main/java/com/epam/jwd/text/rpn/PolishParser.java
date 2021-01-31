package com.epam.jwd.text.rpn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolishParser{

    public static List<String> RPN(List<String> input){
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        int priority;
        for (String s : input){
            priority = precedence(s);
            if (priority == 0){
                output.add(s);
            }
            if (priority == 1){
                stack.push(s);
            }
            if (priority > 1){
                while (!stack.isEmpty()){
                    if (precedence(stack.peek()) >= priority){
                        output.add(stack.pop());
                    } else
                        break;
                }
                stack.push(s);
            }
            if (priority == -1){
                while (precedence(stack.peek()) != 1){
                    output.add(stack.pop());
                }
                stack.pop();
            }
        }
        while (!stack.isEmpty()){
            output.add(stack.pop());
        }
        return output;
    }

    private static int precedence(String operator){
        switch (operator){
            case "~":
                return 5;
            case ">>":
            case "<<":
                return 4;
            case "&":
                return 3;
            case "|":
                return 2;
            case "(":
                return 1;
            case ")":
                return -1;
            default:
                return 0;
        }
    }

}
