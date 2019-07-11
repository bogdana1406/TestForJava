public enum CalculatorType {

    SIMPLE_ROMAN(1), POLISH_ROMAN(2), SIMPLE_ARABIC(3), POLISH_ARABIC(4);
    private int value;

    CalculatorType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
