import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
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
            System.out.println("Overview: " + movies.get(idx - 1).getOverview());
            System.out.println("UserRating: " + movies.get(idx - 1).getUserRating());
        }
    }

    public void searchCast() {
        ArrayList<Movie> movies = new ArrayList<>();
        ArrayList<String> casts = new ArrayList<>();
        String search = "";

        System.out.println("Enter a person to search for (first name or last name):");
        search = scanner.nextLine().toLowerCase();

        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getCast().toLowerCase().contains(search)) {
                String cast = movieList.get(i).getCast();
                String[] list = cast.split("\\|");
                for (String actor : list) {
                    if (!casts.contains(actor) && actor.toLowerCase().contains(search)) {
                        casts.add(actor);
                    }
                }
            }
        }

        if (casts.isEmpty()) {
            System.out.println("No results match your search");
        } else {
            int count = 1;
            for (String cast : casts) {
                System.out.println(count + ". " + cast);
                count++;
            }

            System.out.println("Select a cast member to see movies:");
            int selectedCastIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (selectedCastIndex > 0 && selectedCastIndex <= casts.size()) {
                String selectedCast = casts.get(selectedCastIndex - 1);
                for (Movie movie : movieList) {
                    if (movie.getCast().contains(selectedCast)) {
                        movies.add(movie);
                    }
                }

                if (!movies.isEmpty()) {
                    int count2 = 1;
                    for (Movie movie : movies) {
                        System.out.println(count2 + ". " + movie.getTitle());
                        count2++;
                    }

                    System.out.println("Select a movie to see details:");
                    int selectedMovieIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    if (selectedMovieIndex > 0 && selectedMovieIndex <= movies.size()) {
                        Movie selectedMovie = movies.get(selectedMovieIndex - 1);
                        System.out.println("Title: " + selectedMovie.getTitle());
                        System.out.println("Runtime: " + selectedMovie.getRuntime() + " minutes");
                        System.out.println("Directed by: " + selectedMovie.getDirector());
                        System.out.println("Cast: " + selectedMovie.getCast());
                        System.out.println("Overview: " + selectedMovie.getOverview());
                        System.out.println("UserRating: " + selectedMovie.getUserRating());
                    } else {
                        System.out.println("Invalid movie selection");
                    }
                } else {
                    System.out.println("No movies found for selected cast");
                }
            } else {
                System.out.println("Invalid cast selection");
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
