
# Ict_202_Assignment_

REPORT: Conversion of Infix Expression to Postfix and Prefix
INTRODUCTION
In expression evaluation, different notations are used to represent arithmetic expressions. The most common is infix notation, where operators are written between operands (e.g., A + B). However, computers find it easier to evaluate expressions written in postfix (Reverse Polish Notation) and prefix (Polish Notation) forms.
This report presents the design and implementation of a Java program to convert an infix expression into postfix and prefix forms using a stack data structure.

2. Objective
•	To design an algorithm for converting infix expressions to postfix and prefix
•	To demonstrate the workflow of the solution
•	To develop pseudocode for the algorithms
•	To implement the solution using Java

3. Workflow of the Solution
3.1 Infix to Postfix Conversion Workflow
1.	Start
2.	Initialize an empty stack and an empty output string
3.	Scan the infix expression from left to right
4.	If the character is:
o	Operand → Add to output
o	Opening bracket '(' → Push onto stack
o	Closing bracket ')' → Pop from stack until '(' is found
o	Operator →
	Pop operators from stack with higher or equal precedence
	Push current operator onto stack
5.	After scanning, pop all remaining operators from the stack
6.	Output the postfix expression
7.	End

3.2 Infix to Prefix Conversion Workflow
1.	Start
2.	Reverse the infix expression
3.	Replace:
o	'(' with ')'
o	')' with '('
4.	Convert the modified expression to postfix
5.	Reverse the postfix result to obtain prefix
6.	Output the prefix expression
7.	End

4. Pseudocode
4.1 Infix to Postfix
function infixToPostfix(expression):
    create empty stack
    create empty result

    for each character ch in expression:
        if ch is operand:
            append ch to result

        else if ch is '(':
            push ch onto stack

        else if ch is ')':
            while top of stack is not '(':
                append pop(stack) to result
            pop '(' from stack

        else if ch is operator:
            while stack not empty AND precedence(top) >= precedence(ch):
                append pop(stack) to result
            push ch onto stack

    while stack not empty:
        append pop(stack) to result

    return result

4.2 Infix to Prefix
function infixToPrefix(expression):
    reverse expression

    replace '(' with ')'
    replace ')' with '('

    postfix = infixToPostfix(expression)

    prefix = reverse(postfix)

    return prefix

5. Java Program
import java.util.Stack;

public class InfixConversion {

    // Function to define operator precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Function to check operand
    static boolean isOperand(char ch) {
        return Character.isLetterOrDigit(ch);
    }

    // Convert Infix to Postfix
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (isOperand(ch)) {
                result += ch;
            }
            else if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            }
            else {
                while (!stack.isEmpty() &&
                       precedence(stack.peek()) >= precedence(ch)) {
                    result += stack.pop();
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Reverse string
    static String reverse(String exp) {
        return new StringBuilder(exp).reverse().toString();
    }

    // Convert Infix to Prefix
    static String infixToPrefix(String exp) {
        String reversed = reverse(exp);

        String modified = "";
        for (int i = 0; i < reversed.length(); i++) {
            char ch = reversed.charAt(i);

            if (ch == '(')
                modified += ')';
            else if (ch == ')')
                modified += '(';
            else
                modified += ch;
        }

        String postfix = infixToPostfix(modified);

        return reverse(postfix);
    }

    public static void main(String[] args) {
        String infix = "(A+B)*C-D";

        System.out.println("Infix Expression: " + infix);
        System.out.println("Postfix Expression: " + infixToPostfix(infix));
        System.out.println("Prefix Expression: " + infixToPrefix(infix));
    }
}

6. Sample Output
Infix Expression: (A+B)*C-D
Postfix Expression: AB+C*D-
Prefix Expression: -*+ABCD

7. Conclusion
The conversion of infix expressions into postfix and prefix forms is efficiently achieved using a stack. This approach ensures correct handling of operator precedence and parentheses. The implementation in Java demonstrates how these algorithms can be applied in real-world programming for expression evaluation.


