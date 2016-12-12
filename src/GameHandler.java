
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * Handler for connected clients.
 */
public class GameHandler implements Runnable {

    private static final String WIN_MESSAGE = "You win!";
    private static final String LOOSE_MESSAGE = "You loose!";
    private Socket socket;
    private Words words;
    private InetAddress clientIP;
    private String currentWord;
    private String guess;
    private String currentHiddenWord;

    public GameHandler(Socket socket, Words words) {
        this.words = words;
        this.socket = socket;
        clientIP = socket.getInetAddress();
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());

            while(true){
                String msg = (String) fromClient.readObject();
                System.out.println(msg);

            }

            /*
            while (true) {
                msgObj = (HangmanMessageObj) fromClient.readObject();

                if (msgObj.isCloseConnection()) {
                    fromClient.close();
                    toClient.close();
                    socket.close();
                    System.out.println("Closing connection to: " + clientIP);
                    break;
                } else if (msgObj.isNewGame()) {
                    msgObj.setNotNewGame();
                    setNewWord();
                } else if (msgObj.isNewRound()) {
                    msgObj.setNewRound(false);
                    msgObj.resetGuesses();
                    msgObj.setMessageToPlayer(null);
                    setNewWord();
                } else {
                    guess = msgObj.getGuess().toLowerCase();
                    currentHiddenWord = msgObj.getHiddenWord();
                    if (guess.length() == 1) {
                        String newHiddenWord = "";
                        for (int i = 0; i < currentWord.length(); i++) {
                            if (currentWord.charAt(i) == guess.charAt(0)) {
                                newHiddenWord = newHiddenWord + currentWord.charAt(i);
                            } else {
                                newHiddenWord = newHiddenWord + currentHiddenWord.charAt(i);
                            }
                        }
                        msgObj.setHiddenWord(newHiddenWord);
                    } else if (guess.equals(currentWord)) {
                        msgObj.setHiddenWord(currentWord);
                    }
                    if (currentWord.equals(msgObj.getHiddenWord())) {
                        msgObj.setMessageToPlayer(WIN_MESSAGE);
                        msgObj.incrementClientScore();
                    } else if (msgObj.getGuessesLeft() == 1) {
                        msgObj.setMessageToPlayer(LOOSE_MESSAGE);
                        msgObj.incrementServerScore();
                    }
                    msgObj.decrementGuesses();
                }
                toClient.writeObject(msgObj);
                toClient.flush();
            }*/
        } catch (IOException e) {
            System.out.println(e.getMessage() + " " + clientIP);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
/*
    private void setNewWord() {
        currentWord = words.getRandomWord();
        msgObj.setHiddenWord(currentWord.replaceAll(".", "-"));
        System.out.println(currentWord);
    }
    */
