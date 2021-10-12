package datastructure.stack;

/**
 * @author mjh
 * @date 2021-10-12 14:36
 * (中缀表达式)栈实现计算器功能,括号除外
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "10+900/10/10*2-9";
        char[] expreChar = expression.toCharArray();//将字符串转换成字符数组
        ArrayStack2 numStack = new ArrayStack2(10);//保存数字的栈
        ArrayStack2 operStack = new ArrayStack2(10);//保存运算符的栈
        //字符串索引
        int index = 0;
        //暂存处理结果

        //将原始的字符串根据数据,运算符分别入栈
        while (index < expreChar.length) {
            //判断该字符是否为运算符
            //若是
            if (operStack.isOperator(expreChar[index])) {
                //判断运算符栈是否为空,若为空,直接入栈
                if (operStack.isEmpty()) {
                    operStack.push(expreChar[index++]);
                } else { //若不为空,则判断当前运算符与栈顶运算符的优先级
                    //若栈顶优先级大于当前运算符,则数字栈出栈2个数,运算符栈出栈一个运算符,并以该运算符进行运算
                    if (operStack.priority(expreChar[index]) <= operStack.priority((char) operStack.peek())) {
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        int operator = operStack.pop();
                        int temp = numStack.calculate(num1, num2, operator);
                        numStack.push(temp);
                        operStack.push(expreChar[index]);
                        index++;
                    } else {//若栈顶优先级小于当前运算符,则直接入栈
                        operStack.push(expreChar[index++]);
                    }
                }
            } else { //若不是运算符,直接入栈数据栈
                String keepNum = "";
                keepNum += expreChar[index];

                //多位数据的处理
                while (index + 1 < expreChar.length && '0' <= expreChar[index + 1] && expreChar[index + 1] <= '9') {
                    keepNum += expreChar[++index];
                }
                numStack.push(Integer.parseInt(keepNum));
                index++;
            }
        }

        //将栈中的数据取出进行运算
        while (!operStack.isEmpty()) {
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int operator = operStack.pop();
            int temp = numStack.calculate(num1, num2, operator);
            numStack.push(temp);
        }

        System.out.printf("表达式 %s=%d", expression, numStack.peek());
    }
}

class ArrayStack2 {
    private int maxSize;
    private int top = -1;
    private int[] stack;

    //构造器,创建指定大小的栈
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    boolean isFull() {
        return top == maxSize - 1;
    }

    boolean isEmpty() {
        return top == -1;
    }

    public void push(int n) {
        if (isFull()) {
            System.out.println("栈以满~~");
            return;
        }
        stack[++top] = n;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空~~");
        }
        int value = stack[top--];
        return value;
    }

    //显示所有有效数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    public int peek() {
        return stack[top];
    }

    //判断是否为运算符
    public boolean isOperator(char oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    //返回运算符的优先级,值越大,优先级越高
    //暂时假定只有+-*/
    public int priority(char oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public int calculate(int num1, int num2, int oper) {
        int rs;
        switch (oper) {
            case '+':
                rs = num2 + num1;
                break;
            case '-':
                rs = num2 - num1;
                break;
            case '*':
                rs = num1 * num2;
                break;
            case '/':
                rs = num2 / num1;
                break;
            default:
                throw new RuntimeException("运算符不正确!");
        }
        return rs;
    }
}
