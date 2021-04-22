import java.io.*;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 8080;
    public static LinkedList<ClientHandler> sockets = new LinkedList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(3);


    public static void main(String[] args) throws IOException {
        System.out.println("[SERVER] Initializing Server");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            serverSocket.close();
            e.printStackTrace();
        }
        System.out.println("[SERVER] Waiting for connection");

        while (true) {
                sockets.add(new ClientHandler(serverSocket.accept(), sockets));
                pool.execute(sockets.getLast());
        }
    }
}