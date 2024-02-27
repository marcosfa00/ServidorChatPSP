import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Methods {
    public static final int port = 6666;
    public static final String ipAddress = "192.168.64.16";
    private ServerSocket serverSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    //Constructr
    public Methods() throws IOException {
        this.serverSocket = new ServerSocket(port,0, InetAddress.getByName(ipAddress));
    }
    public void initStreams(Socket socket) throws IOException {
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void getMessage() throws IOException {
        int longitudMensaje = inputStream.readInt();
        byte[] mensajeBytes = new byte[longitudMensaje];
        inputStream.readFully(mensajeBytes);
        String msg = new String(mensajeBytes);
        String [] partes = getUserMSG(msg);
        System.out.println("Mensaje enviado por el usuario:"+ partes[0]  +"contenido:" + partes[1]);
    }

    /**
     * Recieve the complete message
     * @param msg
     * @return the Split of the username and the message
     */
    private String[] getUserMSG(String msg){
        String[] usernameMSG = msg.split("\\:");
        return usernameMSG;
    }


}
