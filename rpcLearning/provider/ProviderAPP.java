package rpcLearning.provider;

import rpcLearning.request.CalculateRpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ProviderAPP {
    private Calculator calculator = new CalculatorImpl();

    public static void main(String[] args) throws IOException {
        new ProviderAPP().run();
    }

    private void run() throws IOException{
        ServerSocket listener = new ServerSocket(9090);
        try{
            while(true){
                Socket socket = listener.accept();
                try{
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Object object = objectInputStream.readObject();

                    int result = 0;
                    if(object instanceof CalculateRpcRequest){
                        CalculateRpcRequest calculateRpcRequest = (CalculateRpcRequest)object;
                        if("add".equals(calculateRpcRequest.getMethod())){
                            result = calculator.add(calculateRpcRequest.getA(), calculateRpcRequest.getB());
                        }else{
                            throw new UnsupportedOperationException();
                        }
                    }

                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(new Integer(result));

                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    socket.close();
                }
            }
        }finally{
            listener.close();
        }
    }
}
