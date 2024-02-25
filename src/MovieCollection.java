import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class MovieCollection {

    private Scanner scanner = new Scanner(System.in);
    ArrayList<Movie> movieList = new ArrayList<Movie>();
    public void imports(){
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
                movies.add(movieList.get(i));
            }
        }
        int count = 1;
        if(movies.isEmpty()){
            System.out.println("No movies titles match that search term");
        }
        else{
            for(Movie movie: movies){
                System.out.println(count + ". " + movie.getTitle());
                count ++;
            }
            System.out.println("Which movie would you like to learn more about?");
            System.out.println("Enter number: ");
            int idx = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Title: " + movies.get(idx - 1).getTitle());
            System.out.println("Runtime: " + movies.get(idx - 1).getRuntime() + " minutes");
            System.out.println("Directed by: " + movies.get(idx - 1).getDirector());
            System.out.println("Cast: " + movies.get(idx - 1).getCast());
            System.out.print("Overview: " + movies.get(idx - 1).getOverview());
            System.out.println("UserRating: " + movies.get(idx - 1).getUserRating());
        }
    }

    public static void selectionSortWordList(ArrayList<String> words) {
        for(int i = 0; i < words.size(); i ++){
            for(int l = i + 1; l < words.size(); l ++){
                if(words.get(l).compareTo(words.get(i)) < 0){
                    String temp = words.get(l);
                    String set = words.get(i);
                    words.set(l, set);
                    words.set(i, temp);
                }
            }
        }
    }

    public void searchCast(){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        ArrayList<String> casts = new ArrayList<String>();
        String search = "";
        System.out.println("Enter a person to search for (first name or last name):");
        search = scanner.nextLine();
        search = search.toLowerCase();
        for(int i = 0; i < movieList.size(); i ++){
            if(movieList.get(i).getCast().toLowerCase().contains(search)){
                String cast = movieList.get(i).getCast();
                String[] list = cast.split("\\|");
                for(String hi: list){
                    if(!casts.contains(hi) && hi.toLowerCase().contains(search)){
                        casts.add(hi);
                    }
                }
            }
        }
        selectionSortWordList(casts);
        int count = 1;
        if(casts.isEmpty()){
            System.out.println("No results match your search");
        }
        else{
            for(String cast: casts){
                System.out.println(count + ". " + cast);
                count ++;
            }
            count --;
            System.out.println("You would like to see the movies for which cast");
            int idx = scanner.nextInt();
            String cast = casts.get(idx - 1);
            if(idx <= count){
                for(int i = 0; i < movieList.size(); i ++){
                    if(movieList.get(i).getCast().contains(cast)){
                        movies.add(movieList.get(i));
                    }
                }
                int count2 = 1;
                for(Movie movie: movies){
                    System.out.println(count2 + ". " + movie.getTitle());
                    count2 ++;
                }

                System.out.println("Which movie would you like to learn more about?");
                System.out.println("Enter number: ");
                int idx2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Title: " + movies.get(idx2 - 1).getTitle());
                System.out.println("Runtime: " + movies.get(idx2 - 1).getRuntime() + " minutes");
                System.out.println("Directed by: " + movies.get(idx2 - 1).getDirector());
                System.out.println("Cast: " + movies.get(idx2 - 1).getCast());
                System.out.print("Overview: " + movies.get(idx2 - 1).getOverview());
                System.out.println("UserRating: " + movies.get(idx2 - 1).getUserRating());
            }
        }
    }


    public void menu() {
        imports();
        String menuOption = "";
        boolean returnToMainMenu = true;

        while (!menuOption.equals("q")) {
            if (returnToMainMenu) {
                System.out.println("Welcome to the movie collection!");
                System.out.println("------------ Main Menu ----------");
                System.out.println("- search (t)itles");
                System.out.println("- search (c)ast");
                System.out.println("- (q)uit");
                System.out.print("Enter choice: ");
                menuOption = scanner.nextLine();
            }

            if (menuOption.equals("t")) {
                searchTitles();
                returnToMainMenu = false;
            } else if (menuOption.equals("c")) {
                searchCast();
                returnToMainMenu = false;
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }

            if (!returnToMainMenu) {
                System.out.println("** Press Enter to Return to Main Menu **");
                scanner.nextLine(); // Wait for the user to press Enter
                returnToMainMenu = true;
            }
        }
    }
}
