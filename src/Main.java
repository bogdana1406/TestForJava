import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Chooser ch = new Chooser();
        CalculatorType type;

        presentation();
        in.nextLine();

        do {
            type = ch.resultType();
            inputInvitation(in, type);
        } while (true);

    }

    //является ли принимаемая лексем оператором (+-*/)
    public static void presentation() {
        System.out.println("\nОПИСАНИЕ.\n\n Программа включает в себя 2 калькулятора:");
        System.out.println("ПРОСТОЙ - работает с 2-мя числами. Выполняет операции типов: a + b, a - b, a * b, a / b ");
        System.out.println("ПОЛЬСКИЙ - обрабатывает выражения с несколькими операциями. Допустимы операции: сложения, вычитания, умножения, целочисленного деления, а так же скобки");
        System.out.println("Оба калькулятора могут работать с арабскими и римскими числами");
        System.out.println("Римские числа обрабатываются, только если они находятся в диапазоне (0; 4000]");
        System.out.println("При работе с арабскими числами предполагается, что они целые, положительные - операция унарного минуса НЕ реализованна");
        System.out.println("Результат вычисления может быть отрицательным целым числом");
        System.out.println("Деление возвращает только целую часть частного");
        System.out.println("Вычисления производятся согласно алгоритму \"ОБРАТНАЯ ПОЛЬСКАЯ НОТАЦИЯ\"");
        System.out.println("Для продолжения нажмите Enter");
    }

    public static void inputInvitation(Scanner in, CalculatorType type) {

        String param = "";

        while (!"q".equals(param)) {

            try {
                proc(type, in);
                /*System.out.println("Для продолжения введите Enter");
                in.nextLine();*/
            } catch (Exception e) {
//                e.printStackTrace();
                System.out.println(e.getMessage());
            }

            System.out.println("Чтобы выбрать другой тип калькулятора нажмите q. Для продолжения введите любой другой символ");
            param = in.next();
            in.nextLine();

        }
    }

    private static void proc(CalculatorType type, Scanner in) throws Exception {

        switch (type) {
            case SIMPLE_ARABIC: {
                System.out.println("Ввдите строку типа: а ~ b , где a, b - положительные целые арабские числа, ~ - математический оператор (+, -, *, /)");
                String input = in.nextLine();
                CalculatorTwoMembers calc = new CalculatorTwoMembers();
                calc.calculateArabic(input);
            }
            break;

            case POLISH_ARABIC: {
                System.out.println("Ввдите строку типа: а ~ b ~(c ~ d) , где a, b - положительные целые арабские числа, ~ - математический оператор (+, -, *, /)");
                String input = in.nextLine();
                CalculatorMultyMembers calc = new CalculatorMultyMembers();
                calc.calculateArabic(input, CalculatorType.POLISH_ARABIC);
            }
            break;

            case SIMPLE_ROMAN: {

                System.out.println("Ввдите строку типа: а ~ b , где a, b - римские числа, ~ - математический оператор (+, -, *, /)");
                String input = in.nextLine();
                CalculatorTwoMembers calc = new CalculatorTwoMembers();
                calc.calculateRoman(input);
            }
            break;

            case POLISH_ROMAN: {
                System.out.println("Ввдите строку типа: а ~ b ~(c ~ d) , где a, b - римские числа, ~ - математический оператор (+, -, *, /)");
                String input = in.nextLine();
                CalculatorMultyMembers calc = new CalculatorMultyMembers();
                calc.calculateArabic(input, CalculatorType.POLISH_ROMAN);
            }
            break;
        }


    }


}

