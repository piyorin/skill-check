package q006.value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class DivisorValue implements IValue {
    //切り上げ桁数
    private int digits;
    //切り上げ方法
    private RoundingMode mode;

    /**
\     * @param digits 切り上げ桁数
      * @param mode 切り上げ方法
      */
    public DivisorValue(int digits, RoundingMode mode){
        this.digits = digits;
        this.mode   = mode;
    }

    @Override
    public void execute(Stack<BigDecimal> values) {
        // スタックから2つの値を抜き出し、割り算した結果をスタックに積む
        BigDecimal right = values.pop();
        BigDecimal left = values.pop();
        values.push(left.divide(right, digits, mode));
    }
}
