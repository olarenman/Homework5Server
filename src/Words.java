import java.io.*;
import java.util.ArrayList;

/**
 * Object for reading words from text file and return a random word.
 */
public class Words {

    private ArrayList<String> words;

    public Words(String path) {
        String strline;
        words = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            while ((strline = br.readLine()) != null)
                words.add(strline);
            br.close();
            fis.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRandomWord() {
        if (words.isEmpty())
            return null;
        else {
            return words.get((int) (Math.random() * words.size())).toLowerCase();
        }
    }
}