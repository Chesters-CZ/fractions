public class Fraction implements IFraction {

    private final Integer numerator;
    private final Integer denominator;

    public Fraction(Integer numerator, Integer denominator) {
        this.numerator = numerator;
        this.denominator = denominator;

        if (denominator == 0) throw new ArithmeticException("Cannot Divide by 0");
    }

    public static Fraction createNormalised(Integer numerator, Integer denominator) {
        try {
            for (int i = denominator / 2; i > 0; i--) {
                if (denominator % i == 0 && numerator % i == 0) {
                    return new Fraction(numerator / i, denominator / i);
                }
            }
            throw new Exception();
        } catch (Exception e) {
            return new Fraction(numerator, denominator);
        }

    }

    /**
     * @link https://www.baeldung.com/java-greatest-common-divisor
     */
    private static Integer findGreatestCommonDenominator(Integer i1, Integer i2) {
        if (i1 < i2) return findGreatestCommonDenominator(i2, i1);
        if (i2 == 0) return i1;
        return findGreatestCommonDenominator(i2, i1 % i2);
    }

    private static Integer findLowestCommonMultiple(Integer i1, Integer i2) {
        if (i1 == 0 || i2 == 0) return 0;
        else {
            int gcd = findGreatestCommonDenominator(i1, i2);
            return Math.abs(i1 * i2) / gcd;
        }
    }

    @Override
    public Integer getNumerator() {
        return numerator;
    }

    @Override
    public Integer getDenominator() {
        return denominator;
    }

    @Override
    public IFraction plus(IFraction other) {
        return new Fraction((getNumerator() * other.getDenominator()) + (getDenominator() * other.getNumerator()), getDenominator() * other.getDenominator());
    }

    @Override
    public IFraction minus(IFraction other) {
        return new Fraction((getNumerator() * other.getDenominator()) - (getDenominator() * other.getNumerator()), getDenominator() * other.getDenominator());
    }

    @Override
    public IFraction times(IFraction other) {
        return new Fraction(getNumerator() * other.getNumerator(), getDenominator() * other.getDenominator());
    }

    @Override
    public IFraction dividedBy(IFraction other) {
        return new Fraction(getNumerator() * other.getDenominator(), getDenominator() * other.getNumerator());
    }

    @Override
    public String toString() {
        return "Fraction " + numerator + "|" + denominator;
    }
}
