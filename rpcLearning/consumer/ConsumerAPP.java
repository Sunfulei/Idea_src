package rpcLearning.consumer;

import rpcLearning.provider.Calculator;

public class ConsumerAPP {
    public static void main(String[] args){
        Calculator calculator = new CalculatorRemoteImpl();
        int result = calculator.add(3, 3);
        System.out.println("result "+result);
    }
}
