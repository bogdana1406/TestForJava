import java.util.Arrays;

public class CalculatorTwoMembers {

    private String rightStr(String s) {
        //удаляем пробелы в строке
        String str = s.replaceAll("\\s", "").toUpperCase();
        return str;
    }

    //проверяем, что в строке только один арифметический знак и цыфры
    private boolean isValidArabicEx(String s) throws Exception {
        String str = rightStr(s);
        if (!str.matches("\\d+(-|\\+|\\*|\\/)\\d+")) {
            throw new Exception("строка не валидна");
        }
        return true;
    }

    //     проверяем, что в строке только один арифметический знак и буквы(I,V,X,L,C,D,M)
    private boolean isValidRomanEx(String s) throws Exception {
        String str = rightStr(s);
        System.out.println("правильная строка " + str);
        if (!str.matches("[IVXLCDM]+(-|\\+|\\*|\\/)[IVXLCDM]+")) {
            throw new Exception("строка не валидна");
        }
        return true;
    }

    // определяем знак операции
    private int operationType(String s) {
        String str = rightStr(s);
        int operationType = -1;
        if (str.contains("+")) {
            operationType = 1;
        }
        if (str.contains("-")) {
            operationType = 2;
        }
        if (str.contains("*")) {
            operationType = 3;
        }
        if (str.contains("/")) {
            operationType = 4;
        }
        return operationType;
    }

    //
    //возвращаем массив арабских цифер
    private int[] arabicArray(String s) throws Exception {
        String[] arabicArray;
        int[] arabicNum = new int[2];
        String str = rightStr(s);
        boolean valid = isValidArabicEx(str);
        if (valid) {
            arabicArray = str.split("-|\\+|\\*|\\/");
            for (int i = 0; i < arabicArray.length; i++ ) {
                arabicNum[i] = Integer.parseInt(arabicArray[i]);
            }
        }
        return arabicNum;
    }
//
    //возвращаем массив римских цифер (в строковых значениях)
    private String[] romanArray(String s) throws Exception {

        String[] romanArray = new String[2];
        String str = rightStr(s);
        boolean valid = isValidRomanEx(str);
        if (valid) {
            romanArray = str.split("-|\\+|\\*|\\/");
        }
        return romanArray;
    }


    public int calculateArabic(String s) throws Exception {

        int[] arabicArray = arabicArray(s);
        int operationType = operationType(s);
        int result = 0;
        switch (operationType) {
            case 1:
                result = arabicArray[0] + arabicArray[1];
                break;
            case 2:
                result = arabicArray[0] - arabicArray[1];
                break;
            case 3:
                result = arabicArray[0] * arabicArray[1];
                break;
            case 4:

                try {
                    result = arabicArray[0] / arabicArray[1];
                } catch (ArithmeticException e) {
                    throw new Exception("делить на 0 нельзя ");
                }
                break;
        }
        System.out.println("результат " + result);
        return result;
    }

    public String  calculateRoman(String s) throws Exception {
        String[] romanArray = romanArray(s);
        int operationType = operationType(s);

        if (romanArray.length != 2) {
            throw new Exception("некорректный ввод ");
        }

        int[] convertArr = Convertor.arrayConvertor(romanArray);
        int result = 0;
        String convertResult;

        switch (operationType) {
            case 1:
                result = convertArr[0] + convertArr[1];
                break;
            case 2:
                result = convertArr[0] - convertArr[1];
                break;
            case 3:
                result = convertArr[0] * convertArr[1];
                break;
            case 4:
                try {
                    result = convertArr[0] / convertArr[1];
                } catch (ArithmeticException e) {
                    throw new Exception("делить на 0 нельзя ");
                }
                break;
        }
        convertResult = Convertor.intToRoman(result);
        System.out.println("результат " + convertResult);
        return convertResult;
    }
}

