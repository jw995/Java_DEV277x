

public class Fraction {

    private int den, num;

    public Fraction(int num, int den){// two parameters constructor
        if (den==0){
            throw new IllegalArgumentException("Denominator cannot be zero!");
        }else if (den<0){
            this.den=-den; this.num=-num;
        }else {
            this.den=den; this.num=num;
        }
    }

    public Fraction(int num){// one parameter constructor
        this(num, 1);
    }

    public Fraction(){//default constructor
        this(0,1);
    }

    public int getNum(){
        return num;
    }

    public int getDen(){
        return den;
    }

    public String toString(){
        if (den == 1){
            return Integer.toString(num);
        }else{
            return (num + "/" + den);
        }
    }

    public double toDouble(){
        return (double)(num/den);
    }

    public Fraction add(Fraction other){
        Fraction sum=new Fraction((num*other.den)+(den*other.num), (other.den)*den);
        sum.toLowestTerms();
        return sum;
    }

    public Fraction sub(Fraction other){
        Fraction sum = new Fraction((num*other.den)-(den*other.num), other.den*den);
        sum.toLowestTerms();
        return sum;
    }

    public Fraction mul(Fraction other){
        Fraction sum = new Fraction(num*other.num, den*other.den);
        sum.toLowestTerms();
        return sum;
    }

    public Fraction div(Fraction other){
        if (other.num == 0){
            throw new IllegalArgumentException("Cannot divide by zero!");
        }
        Fraction sum = new Fraction(num*other.den, den*other.num);
        sum.toLowestTerms();
        return sum;
    }

    public boolean equals(Object other){
        Fraction otherObj = (Fraction) other;
        otherObj.toLowestTerms();
        this.toLowestTerms();

        if (this.num == otherObj.num && this.den == otherObj.den) {
            return true;
        }else return false;
    }

    public void toLowestTerms(){
        num=num/gcd(num,den);
        den=den/gcd(num,den);
    }

    public static int gcd(int num, int den){
        //  greatest common denominator
        int rem = num % den;
        if (rem == 0){
            return den;
        }else {
            return gcd(den, rem);
        }
    }
}
