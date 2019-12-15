package rpcLearning.consumer;

import rpcLearning.provider.Calculator;
import rpcLearning.request.CalculateRpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class CalculatorRemoteImpl implements Calculator {

    public static final int PORT = 9090;
    //private static Logger log = LoggerFactory.getLogger(CalculatorRemoteImpl.class);

    @Override
    public int add(int a, int b) {
        List<String> addressList = lookupProviders("Calculator.add");
        String address = chooseTarget(addressList);
        try{
            Socket socket = new Socket(address, PORT);
            CalculateRpcRequest request = generateRequest(a, b);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object response = objectInputStream.readObject();

            if(response instanceof Integer){
                return (Integer)response;
            }else{
                throw new InternalError();
            }


        }catch(Exception e){
            e.printStackTrace();
            throw new InternalError();
        }

    }

    private CalculateRpcRequest generateRequest(int a, int b){
        CalculateRpcRequest request = new CalculateRpcRequest();
        request.setA(a);
        request.setB(b);
        request.setMethod("add");
        return request;
    }

    private String chooseTarget(List<String> providers){
        if(providers == null || providers.size() == 0){
            throw new IllegalArgumentException();
        }
        return providers.get(0);
    }

    public static List<String> lookupProviders(String name){
        List<String> strings = new ArrayList<>();
        strings.add("127.0.0.1");
        return strings;
    }
}
