import java.util.*;

public class CalculatorMultyMembers {

    private static boolean validator = true;
    private static String operators = "+-*/";
    private static String delimiters = "()" + operators;

    //удаляем пробелы, приводим к верхнему регистру
    private String rightStr(String s) {
        //удаляем пробелы в строке
        String str = s.replaceAll("\\s", "").toUpperCase();
        return str;
    }

    //является ли принимаемая лексема оператором (+-*/)
    private boolean isOperator(String lexem) {

        boolean isOperator = false;
        for (int i = 0; i < operators.length(); i++) {
            if (lexem.charAt(0) == operators.charAt(i)) {
                isOperator = true;
            }
        }
        return isOperator;
    }

    //является ли принимаемая лексема делиметром (+-*/())
    private boolean isDelimitr(String lexem) {
        boolean isDelimetr = false;
        for (int i = 0; i < delimiters.length(); i++) {
            if (lexem.charAt(0) == delimiters.charAt(i)) {
                isDelimetr = true;
            }
        }
        return isDelimetr;

    }

    //
    //определяем приоритет операции
    private int priority(String delimiter) {
        int priority = 0;
        if (delimiter.equals("(")) {
            priority = 1;
        }
        if (delimiter.equals("+") || delimiter.equals("-")) {
            priority = 2;
        }
        if (delimiter.equals("*") || delimiter.equals("/")) {
            priority = 3;
        }
        if (delimiter.equals(")")) {
            priority = 4;
        }
        return priority;
    }


    //выполняет действие со стеком и списком, если лексема - является оператором
    private void switchOperator(Deque<String> stack, List<String> postfix, String lexem, String operator) {
        if (stack.isEmpty() || (priority(stack.peek()) < priority(operator))) {
            stack.push(lexem);
        } else {
            while (!stack.isEmpty() && (priority(stack.peek()) >= priority(operator))) {
                postfix.add(stack.pop());
            }
            stack.push(lexem);
        }
    }

    private List<String> resultArr(String s) {
        String input = rightStr(s);
        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        //разбиваем строку на лексемы
        StringTokenizer tokenizer = new StringTokenizer(input, delimiters, true);
        //текущая лексема
        String lexem;
        //пока в токенайзере есть лексемы
        while (tokenizer.hasMoreTokens()) {
            lexem = tokenizer.nextToken();
            //если последняя лексема является знаком (заменить при проверке валидности введеной строки)
            if (!tokenizer.hasMoreTokens() && isOperator(lexem)) {
                System.out.println("выражение заканчивается на арифметический знак - некорректно");
                validator = false;
                return postfix;
            }
            //если лексема является разделителем

            //в зависимости от приоритета
            switch (lexem) {
                //если лексема является открывающейся скобкой - добавляем ее в стек
                case "(": {
                    stack.push(lexem);
                    break;
                }
                //если лексема является закрывающейся скобкой
                case "+": {
                    switchOperator(stack, postfix, lexem, "+");
                    break;
                }
                case "-": {
                    switchOperator(stack, postfix, lexem, "-");
                    break;

                }
                case "*": {
                    switchOperator(stack, postfix, lexem, "*");
                    break;
                }
                case "/": {
                    switchOperator(stack, postfix, lexem, "/");
                    break;

                }
                case ")": {
                    //все лексемы, которые до открывающейся скобки добавляем в список postfix, удаляем из стека.
                    // в стеке должны остаться только элементы, которые были помещены перед открывающейся скобкой и сама открывающаяся скобка
                    while (!"(".equals(stack.peek())) {
                        postfix.add(stack.pop());
                    }
                    //если в стеке не осталось открывающейся скобки - скобки не были согласованны
                    if (stack.isEmpty()) {
                        System.out.println("скобки не согласованны");
                        validator = false;
                    }
                    //удаляем открывающуюся скобку
                    stack.pop();

                    break;
                }
                default: {
                    postfix.add(lexem);
                }
            }

        }


        //если в стеке остались символы (только знаки операции) - выталкиваем их в итоговый массив
        while (!stack.isEmpty() && isOperator(stack.peek())) {
            postfix.add(stack.pop());
        }

        //если в стеке оставались символы кроме знаков и они не были вытолкнуты в список
        if (!stack.isEmpty()) {
            System.out.println("скобки не согласованны");
            validator = false;
            System.out.println(stack);
        }
        //если валидатор имеет значение false, очищаем итоговый список
        if (!validator) {
            postfix.clear();
            System.out.println("некорректное выражние");
        }
//        System.out.println(postfix);
        return postfix;
    }

    //считаем результат. если операция вічитания или деления необходимо обратить внимание, что порядок елементо стека - обратній
    public int calculateArabic(String s, CalculatorType type) throws Exception {

        List<String> postfix;
        if (type == CalculatorType.POLISH_ARABIC) {
            postfix = resultArr(s);
        } else {
            postfix = Convertor.arrayConvertor(resultArr(s));
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (String el : postfix) {
            switch (el) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-": {

                    Integer b = stack.pop(), a = stack.pop();
                    stack.push(a - b);
                }
                break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/": {

                    Integer b = stack.pop(), a = stack.pop();

                    try {
                        stack.push(a / b);
                    } catch (ArithmeticException e) {
                        throw new Exception("Нельзя делить на 0 ");
                    }
                }
                break;

                default:
                    try {
                        stack.push(Integer.valueOf(el));
                    } catch (NumberFormatException e) {
                        throw new Exception("некорректный ввод ");
                    }
                    break;
            }
        }

        int result = stack.pop();
        if (type == CalculatorType.POLISH_ARABIC) {
            System.out.println("Результат " + result);
        } else {
            System.out.println("Результат " + Convertor.intToRoman(result));
        }

        return result;
    }

}
