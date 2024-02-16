import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class MovieCollection {

    private Scanner scanner;
    ArrayList<Movie> movieList = new ArrayList<Movie>();
    public void imports(){
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        String hi = "";
        try {
            int add = 0;

            File myFile = new File("src//movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                ArrayList<String> list = new ArrayList<String>();
                String data = fileScanner.nextLine();
                int i = 0;
                String title = "";
                String cast = "";
                String director = "";
                String overview = "";
                int runtime = 0;
                double rating = 0;
                while(i >= 0) {
                    i = data.indexOf(",");
                    if(i >= 0) {
                        hi = data.substring(0, i);
                        list.add(hi);
                        data = data.substring(i + 1);
                    }
                    else {
                        list.add(data);
                    }
                }
                title = list.get(0);
                cast = list.get(1);
                director = list.get(2);
                overview = list.get(3);
                runtime = Integer.parseInt(list.get(4));
                rating = Double.parseDouble(list.get(5));
                Movie movie = new Movie(title, cast, director, overview, runtime, rating);
                movieList.add(movie);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void searchTitles(){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        String search = "";
        System.out.println("Enter a title search term");
        search = scanner.nextLine();
        search = search.toLowerCase();
        for(int i = 0; i < movieList.size(); i ++){
            if(movieList.get(i).getTitle().toLowerCase().contains(search)){
                movies.add(movies.get(i));
            }
        }
    }

    public void searchCast(){

    }

    public void menu(){
        imports();
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";
        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }
}
