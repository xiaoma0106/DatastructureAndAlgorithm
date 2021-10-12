package datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author mjh
 * @date 2021-10-12 16:45
 * 1. 将字符串转换到数组中
 * 2.中缀表达式转化成后缀表达式(逆波兰表达式)
 * 1)需要一个栈辅助,存放运算符
 * 3.根据逆波兰表达式求值
 */
public class PolandNotation {
    public static void main(String[] args) {
        String expression = "9999+((5+5)*2-6/3)";
        List<String> infixList = toInfixExpressionList(expression);
        System.out.println("中缀表达式:" + infixList);
        List<String> suffixList = toSuffixExpressionList(infixList);
        System.out.println("后缀表达式:" + suffixList);
        int result=calculate(suffixList);
        System.out.println(expression+"="+result);

    }

    //1将一个字符串转换成字符串数组
    public static List<String> toInfixExpressionList(String str) {
        int index = 0;
        List<String> list = new ArrayList<>();
        String keepNum = "";
        while (index < str.length()) {
            if (str.charAt(index) < '0' || str.charAt(index) > '9') {
                list.add("" + str.charAt(index));
                index++;
            } else {
                //难点:多位数的处理
                while (index < str.length() && str.charAt(index) >= '0' && str.charAt(index) <= '9') {
                    keepNum += str.charAt(index);
                    index++;
                }
                list.add(keepNum);
                keepNum = "";
            }
        }
        return list;
    }

    //

    /**
     * 2中缀表达式转换成后缀表达式(逆波兰表达式)
     *
     * @param infixStr
     * @return 1, 存放运算符的辅助数组stack, 存放后缀表达式的ArrayList(suffixStr)
     * 2,从头到尾遍历中缀表达式 infixStr
     * 3,若是数字,则直接加到suffixStr
     * 4,若是 ( 则直接入栈stack
     * 5,若是 ) 则判断stack栈顶是否为 (,
     * 若不是,则出栈并将运算符添加到suffixStr,
     * 若是,则出栈(
     * 6,判断当前运算符优先级是否比栈顶运算符高,
     * 若不是则stack出栈,添加到suffixStr,直到stack为空或栈顶优先级小于当前运算符
     * 若是则直接入栈
     * 其他情况:栈为空时,或,栈顶元素为(时,同样直接入栈
     */
    public static List<String> toSuffixExpressionList(List<String> infixStr) {
        Stack<String> stack = new Stack<>();//存放运算符的辅助栈
        List<String> suffixStr = new ArrayList<>();//存放后缀表达式
        int index = 0;
        int len = infixStr.size();

        while (index < len) {
            String temp = infixStr.get(index);
            if (temp.matches("\\d+")) {
                suffixStr.add(temp);
                index++;
            } else if (temp.equals("(")) {
                stack.push(temp);
                index++;
            } else if (temp.equals(")")) {
                while (!stack.peek().equals("(")) {
                    suffixStr.add(stack.pop());
                }
                stack.pop();
                index++;
            } else {
                while (stack.size() > 0 && Operation.priority(temp) <= Operation.priority(stack.peek())) {
                    suffixStr.add(stack.pop());
                }
                stack.push(temp);
                index++;
            }
        }

        while (stack.size() > 0) {
            suffixStr.add(stack.pop());
        }
        return suffixStr;
    }

    /**
     * 3.根据后缀表达式求值
     * 从头到尾遍历后缀表达式
     * 若是数值则直接入栈,
     * 若为运算符,则出栈2个数值,以该运算符进行运算,并将结果入栈
     * 直到遍历完整个后缀表达式
     */

    public static int calculate(List<String> list) {
        int index = 0;
        Stack<Integer> stack = new Stack<>();
        while (index < list.size()) {
            String temp = list.get(index);
            if (temp.matches("\\d+")) {
                stack.push(Integer.parseInt(temp));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (temp) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                        System.out.println("运算符不正确!");
                }
            }
            index++;
        }
        return stack.peek();
    }
}

class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    public static int priority(String str) {
        int result;
        switch (str) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不是运算符!");
                result = 0;
        }
        return result;
    }
}
