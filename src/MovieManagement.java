import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieManagement {
    public static List<Movie> movieList = new ArrayList<>();

    public static void addMovie(Scanner scanner) {
        Movie movie = new Movie();
        movie.setTitle(InputDataHelper.inputTitle(scanner));
        movie.setDirector(InputDataHelper.inputDescription(scanner));
        movie.setYear(InputDataHelper.inputYear(scanner));

        //Gọi procedure add_movie
        try (Connection conn = Database.getConnection()) {
            CallableStatement cstmt = conn.prepareCall("{CALL add_movie(?,?,?)}");
            cstmt.setString(1, movie.getTitle());
            cstmt.setString(2, movie.getDirector());
            cstmt.setInt(3, movie.getYear());

            boolean result = cstmt.executeUpdate() > 0;
            if (result) {
                System.out.println("Movie added successfully");
            } else {
                System.err.println("Movie not added successfully");
            }
        } catch (SQLException e) {
            System.err.println("Error while adding movie");
        }
    }

    public static void listMovies() {
        movieList.clear();//xóa danh sách cũ
        try (Connection conn = Database.getConnection()) {
            CallableStatement callStmt = conn.prepareCall("{call list_movie()}");
            ResultSet rs = callStmt.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setYear(rs.getInt("year"));
                movieList.add(movie);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        if (movieList.isEmpty()) {
            System.err.println("No movies added");
        } else {
            System.out.println("List of movies added");
            for (Movie movie : movieList) {
                movie.display();
            }
        }
    }

    public static Movie findMovieById(int id) {
        Movie movie = null;
        try (Connection conn = Database.getConnection()) {
            CallableStatement cstmt = conn.prepareCall("{CALL find_id(?)}");
            cstmt.setInt(1, id);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setYear(rs.getInt("year"));
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return movie;
    }

    public static void updateMovie(Scanner scanner) {
        System.out.println("Enter movie id to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Movie movie = findMovieById(id);
        if (movie == null) {
            System.err.println("Movie not found");
        } else {
            Movie updateMovie = new Movie();
            updateMovie.setId(movie.getId());
            updateMovie.setTitle(movie.getTitle());
            updateMovie.setDirector(movie.getDirector());
            updateMovie.setYear(movie.getYear());

            boolean isExist = true;
            do {
                System.out.println("""
                        ==============UPDATE MOVIE============
                        1. Update title
                        2. Update director
                        3. Update year
                        4. Exit
                        Your choice:
                        """);
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Enter new title: ");
                        updateMovie.setTitle(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Enter new director: ");
                        updateMovie.setDirector(scanner.nextLine());
                        break;
                    case 3:
                        System.out.println("Enter new year: ");
                        updateMovie.setYear(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 4:
                        isExist = false;
                        break;
                    default:
                        System.err.println("Invalid choice");
                }
            } while (isExist);
            //Gọi procedure update_movie
            try (Connection conn = Database.getConnection()) {
                CallableStatement cstmt = conn.prepareCall("{CALL update_movie(?,?,?,?)}");
                cstmt.setInt(1, id);
                cstmt.setString(2, updateMovie.getTitle());
                cstmt.setString(3, updateMovie.getDirector());
                cstmt.setInt(4, updateMovie.getYear());

                boolean result = cstmt.executeUpdate() > 0;
                if (result) {
                    System.out.println("Movie updated successfully");
                } else {
                    System.err.println("Movie not updated successfully");
                }
            } catch (Exception e) {
                System.err.println("Error while updating movie");
                e.printStackTrace();
            }
        }
    }

    public static void deleteMovie(Scanner scanner) {
        System.out.println("Enter movie id to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Movie movie = findMovieById(id);
        if (movie == null) {
            System.err.println("Movie not found");
        }else {
            try(Connection conn=Database.getConnection()){
                CallableStatement cstmt = conn.prepareCall("{CALL delete_movie(?)}");
                cstmt.setInt(1, id);
                boolean result = cstmt.executeUpdate() > 0;
                if (result) {
                    System.out.println("Movie deleted successfully");
                }else {
                    System.err.println("Movie not deleted successfully");
                }
            } catch (Exception e) {
                System.err.println("Error while deleting movie");
            }
        }
    }
}
