import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class MovieCollection {
    public void imports(){
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        ArrayList<Integer> ratings = new ArrayList<Integer>();
        ArrayList<String> cast = new ArrayList<String>();
        try {
            int add = 0;

            File myFile = new File("src//movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                int i = 0;
                int next = 0;
                String sub = "";
                while(i >= 0){
                    i = data.indexOf(",");
                    if(i > 0){
                        String s = data.substring(0, i);
                        add += Integer.parseInt(s);
                        data = data.substring(i + 1);
                    }
                    else {

                    }
                }
                ratings.add(add);
                add = 0;
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
