package ArithmeticExpression;


// implementation of composite design pattern.
interface ArithmeticExpression {
    int solve();
}
enum Operator {
    ADD,
    SUBTRACT
}
class Expression implements ArithmeticExpression {
    ArithmeticExpression leftPart;
    ArithmeticExpression rightPart;
    Operator operator;
    Expression(ArithmeticExpression leftPart, ArithmeticExpression rightPart, Operator operator) {
        this.leftPart = leftPart;
        this.rightPart = rightPart;
        this.operator = operator;
    }
    @Override
    public int solve() {
        int left = leftPart.solve();
        int right = rightPart.solve();
        int result = 0;
        switch (operator) {
            case ADD : result = left + right;
                        break;
            case SUBTRACT: result = left - right;
                    break;
        }
        return result;
    }
}

class Operand implements ArithmeticExpression{
    int value;
    Operand(int value) {
        this.value = value;
    }
    @Override
    public int solve() {
        return value;
    }
}

public class Main {
    public static void main(String[] args) {
        Operand op1 = new Operand(2);
        Operand op2 = new Operand(3);
        Operand op3 = new Operand(5);
        Expression e2 = new Expression(op2, op3, Operator.ADD);
        Expression e1 = new Expression(op1, e2, Operator.ADD);
        System.out.println(e1.solve());
    }
}
