package rpcLearning.provider;

import rpcLearning.provider.Calculator;

public class CalculatorImpl implements Calculator {

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
