

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Methods {
    public static final int port = 6666;
    public static final String ipAddress = "192.168.64.16";
    private ServerSocket serverSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    //Constructr
    public Methods() throws IOException {

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
        System.out.println("Mensaje recibido de: "+ partes[0]  +" contenido:" + partes[1]);

        enviarDatos(partes[1]);
        if (partes[1].equals(" /bye")) {
            inputStream.close();
            outputStream.close();
            serverSocket.close();
            System.out.println("CONEXIÓN CON SERVIDOR CERRADA");
        }

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

    private void  enviarDatos(String r) throws IOException {
        String result = String.valueOf(r);
        byte [] longitud = result.getBytes();
        outputStream.writeInt(longitud.length);
        outputStream.write(longitud);

    }


}
