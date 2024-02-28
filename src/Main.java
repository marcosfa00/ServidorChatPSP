import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Methods m = new Methods();
        ServerSocket serverSocket = new ServerSocket();
        InetSocketAddress addr=new InetSocketAddress(Methods.ipAddress,Methods.port);
        System.out.println("Esperando conexion...");
        serverSocket.bind(addr);
        Socket newSocket = serverSocket.accept();
        System.out.println("Conexion realizada");
        m.initStreams(newSocket);
        while(true) {
            m.getMessage();
        }

    }
}