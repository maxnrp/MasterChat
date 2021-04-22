import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ClientHandler implements Runnable{

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private LinkedList<ClientHandler> sockets;

    public ClientHandler(Socket socket, LinkedList<ClientHandler> sockets) throws IOException {
        this.socket = socket;
        this.sockets = sockets;
        setUpSockedStreams();
        output.println("CONNECTION ESTABLISHED");
        output.println("COMMANDS: NAME | USERS | BROADCAST | EXIT \n");

    }

    @Override
    public void run() {
        while (true){
            try {
                String message = input.readLine();
                if (message.equals("exit")){
                    output.println("You are being disconnected\nGood-bye!");
                    Server.sockets.remove(this);
                    Thread.interrupted();
                    socket.close();
                } else if (message.equals("name")){
                    output.println("Please insert name: ");
                    Thread.currentThread().setName(input.readLine());
                    output.println("Your name was updated: " + Thread.currentThread().getName().toUpperCase());
                } else if (message.equals("users")){
                    output.println("Users in chat at the moment: " + Server.sockets.size());
                    System.out.println(Thread.getAllStackTraces().get(Thread.currentThread().getName()));
                } else if (message.startsWith("broadcast")){
                    int firstSpace = message.indexOf(" ");
                    if (firstSpace != -1){
                        broadcast(message.substring(firstSpace+1));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void broadcast(String msg){
        for (ClientHandler socket: sockets){
            socket.output.println(msg);
        }
    }


    public void setUpSockedStreams() throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }
}