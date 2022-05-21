public class CalculationConstant {
    public void Calculate_Constant_Log(long time, int number){
        double c;
        c = (time/Math.log(number));
        System.out.println("the value of the constant is: " + c);

    }

    public void Calculate_Constant_Square(long time, int number){
        double c;
        int square = 2;
        c = (time/Math.pow(number, square));
        System.out.println("the value of the constant is: " + c);

    }
}