import java.util.Scanner;

public class Movie {
    private int id;
    private String title;
    private String director;
    private int year;

    public Movie() {
    }

    public Movie(int id, String title, String director, int year) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void display() {
        System.out.printf("ID: %d | Title: %s | Director: %s | Year: %d\n", this.id, this.title, this.director, this.year);
    }

}

