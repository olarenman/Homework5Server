import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * args[0] sets port for server.
 */
public class Server {

    private static final int DEFAULT_PORT = 12000;
    private static final String PATH_FOR_TEXT_FILE = "words.txt";

    public static void main(String[] args) {
        Words words = new Words(PATH_FOR_TEXT_FILE);
        int port = (args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]));

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Waiting for connection on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connected to" + socket.getInetAddress());
                Thread handler = new Thread(new GameHandler(socket, words));
                handler.setPriority(handler.getPriority() + 1);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}