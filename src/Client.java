/*import java.io.*;
import java.net.Socket;

public class Client {

    private BufferedReader input;
    private PrintWriter output;
    private BufferedReader keyboardReader;
    private Socket socket;

    public Client() throws IOException {
        this.socket = new Socket("localhost", 8080);

    }

    public static void main(String args[]){
        setUpSockedStreams();

    }

    public void turnOn() throws IOException {
        output.println(keyboardReader.readLine());
    }

    public static void setUpSockedStreams() throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        output = new PrintWriter(socket.getOutputStream(), true);
    }
}
*/
// tested with net cat: terminal > nc 127.0.01 8080