import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Chooser {


    //пользователь выбирает тип калькулятора: простой или польский
    private int simpleOrPolish() {
        int simpleOrPolish = 0;
        String choos;
        Scanner in = new Scanner(System.in);
        System.out.println("Выберите калькулятор, который Вы хотите использовать");
        System.out.println("ПРОСТОЙ - введите 1");
        System.out.println("ПОЛЬСКИЙ - введите 2");
        System.out.println("ВЫХОД - введите q");
        for (; ; ) {
            choos = in.next();
            switch (choos) {
                case "1":
                    simpleOrPolish = 1;
                    break;

                case "2":
                    simpleOrPolish = 2;
                    break;
                case "q":
                    System.exit(0);
                    break;
                default:
                    continue;
            }
            return simpleOrPolish;
        }

    }

    //пользователь выбирает римские или арабские числа
    private int romanOrArabic() {

        int romanOrArabic = 0;
        String choos;
        Scanner in = new Scanner(System.in);
        System.out.println("Выберите цыфры, которые Вы хотите использовать");
        System.out.println("Арабские - введите 1");
        System.out.println("Римские - введите 2");
        for (; ; ) {
            choos = in.next();
            switch (choos) {
                case "1":
                    romanOrArabic = 1;
                    break;

                case "2":
                    romanOrArabic = 2;
                    break;
                default:
                    continue;
            }
            return romanOrArabic;
        }

    }

    //определяем конечный тип калькулятора
    public CalculatorType resultType() {
        CalculatorType calculatorType = null;
        int simpleOrPolish = simpleOrPolish();
        int romanOrArabic = romanOrArabic();

        if ((romanOrArabic == 1) && (simpleOrPolish == 1)) {
            calculatorType = CalculatorType.SIMPLE_ARABIC;
        }
        if ((romanOrArabic == 1) && (simpleOrPolish == 2)) {
            calculatorType = CalculatorType.POLISH_ARABIC;
        }

        if ((romanOrArabic == 2) && (simpleOrPolish == 1)) {
            calculatorType = CalculatorType.SIMPLE_ROMAN;
        }
        if ((romanOrArabic == 2) && (simpleOrPolish == 2)) {
            calculatorType = CalculatorType.POLISH_ROMAN;
        }
        return calculatorType;
    }

}
