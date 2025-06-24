import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieManagement movieManagement = new MovieManagement();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("""
                    ===============MOVIE MANAGEMENT================
                    1. Add movie
                    2. Display movie details
                    3. Update movie details
                    4. Delete movie details
                    5. Exit program
                    Your choice: 
                    """);
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    movieManagement.addMovie(sc);
                    break;
                case 2:
                    movieManagement.listMovies();
                    break;
                case 3:
                    movieManagement.updateMovie(sc);
                    break;
                case 4:
                    movieManagement.deleteMovie(sc);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.err.println("Invalid choice. Please enter a valid choice (1-5).");
            }
        } while (true);
    }
}