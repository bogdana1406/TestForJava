import java.util.*;

public class Convertor {

    private static final Map<String, Integer> map = new LinkedHashMap<>(7);

    static {
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);
    }

    //проверяем является ли строка, которая представляет римское число - валидной.
    // (действительно ли это римское число)

    public static boolean isRomanNumberValid(String s) {
        if (s.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) {
            return true;
        } else {
            return false;
        }
    }

    //переводим римское число в арабское
    public static int romanToInt(String s) throws Exception {
        String takeRoman = s.toUpperCase();
        int arabic = 0;

        boolean validator = isRomanNumberValid(s);
        //создаем итератор для map
        if (!validator) {
            throw new Exception("Выражение невалидно");
        }
        Iterator it = map.entrySet().iterator();
        //пока в map есть элементы
        while (it.hasNext()) {
            //инициализируем счетчик для позиции
            int i = 0;
            //
            Map.Entry<String, Integer> pair = (Map.Entry) it.next();
            //пока входящая строка содержит символы и i меньще ее длины
            while ((takeRoman.length() > 0) && (i < takeRoman.length())) {
                //если входящая строка начинается со значения, которое совпадает с ключом map
                // добавляем данный ключ в итоговый список.
                // из входящей строки удаляем соответствующие символы
                if (takeRoman.startsWith(pair.getKey())) {
                    arabic += pair.getValue();
//                    System.out.println(romanToArray);
                    takeRoman = takeRoman.substring(pair.getKey().length());
//                    System.out.println(arabic);
                    //иначе увеличиваем счетчик позиции
                } else {
                    i++;
                }
            }
        }
        return arabic;
    }

    private static final TreeMap<Integer, String> map2 = new TreeMap<>(Collections.reverseOrder());

    static {
        map2.put(1000, "M");
        map2.put(900, "CM");
        map2.put(500, "D");
        map2.put(400, "CD");
        map2.put(100, "C");
        map2.put(90, "XC");
        map2.put(50, "L");
        map2.put(40, "XL");
        map2.put(10, "X");
        map2.put(9, "IX");
        map2.put(5, "V");
        map2.put(4, "IV");
        map2.put(1, "I");
    }

    public static String intToRoman(int num) {
        if ((num <= 0) || (num >= 4000)) {
            System.out.println(num + " за пределами диапазона (0; 4000)");
        }
        StringBuilder roman = new StringBuilder();
        for (Integer i : map2.keySet()) {
            for (int j = 1; j <= num / i; j++) {
                roman.append(map2.get(i));
            }
            num %= i;
        }
        return roman.toString();
    }

    public static int[] arrayConvertor(String[] arrayRoman) throws Exception {
        int len = arrayRoman.length;
        int[] convertArr = new int[len];
        for (int i = 0; i < convertArr.length; i++) {
            convertArr[i] = romanToInt(arrayRoman[i]);
        }
        return convertArr;
    }

    public static List<String> arrayConvertor(List<String> romanPostfix) throws Exception {
        List<String> convertArr = new ArrayList<>();
        for (String i : romanPostfix) {
            if ("+".equals(i) || "-".equals(i) || "*".equals(i) || "/".equals(i)) {
                convertArr.add(i);
            } else {
                convertArr.add(String.valueOf(romanToInt(i)));
            }
        }
        return convertArr;
    }

}
