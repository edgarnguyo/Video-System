package Backend;

public class Movie {
    private String name;
    private Genre genre;

    public Movie(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }
    @Override
    public String toString() {
        return name;
    }
}
