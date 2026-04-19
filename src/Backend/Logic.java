package Backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Logic {

    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    private ObservableList<Genre> genres = FXCollections.observableArrayList();
    private ObservableList<Rental> rentals = FXCollections.observableArrayList();

    public void addGenre(String name) {
        genres.add(new Genre(name));
    }
    public void removeGenre(String name) {
        for (int i = 0; i < genres.size(); i++) {
            Genre g = genres.get(i);
            if (g.getName().equals(name)) {
                genres.remove(i);
                break;
            }
        }
    }
    public ObservableList<Genre> getGenres() {
        return genres;
    }
    public void addMovie(String name, Genre genre) {
        movies.add(new Movie(name, genre));
    }
    public void removeMovie(String name, Genre genre) {
        for (int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if (m.getName().equals(name) && m.getGenre() == genre) {
                movies.remove(i);
                break;
            }
        }
    }
    public ObservableList<Movie> getAllMovies() {
        return movies;
    }
    public List<Movie> getMovieForGenre(Genre genre) {
        List<Movie> result = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if (m.getGenre() == genre) {
                result.add(m);
            }
        }
        return result;
    }
    public void addCustomer(String name, int phone, String email) {
        customers.add(new Customer(name, phone, email));
    }
    public void removeCustomer(String name) {
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            if (c.getName().equals(name)) {
                customers.remove(i);
                break;
            }
        }
    }
    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    public void addRental(Customer customer, Movie movie) {
        rentals.add(new Rental(customer, movie));
    }
    public ObservableList<Rental> getRentals() {
        return rentals;
    }
    public List<Rental> getReturnedItems(Customer customer) {
        List<Rental> result = new ArrayList<>();

        for (int i = 0; i < rentals.size(); i++) {
            Rental r = rentals.get(i);
            if (r.getIsReturned() && r.getCustomer() == customer) {
                result.add(r);
            }
        }
        return result;
    }
    public List<Rental> getBorrowedItems(Customer customer) {
        List<Rental> result = new ArrayList<>();
        for (int i = 0; i < rentals.size(); i++) {
            Rental r = rentals.get(i);
            if (!r.getIsReturned() && r.getCustomer() == customer) {
                result.add(r);
            }
        }
        return result;
    }
    public void removeRental(Customer customer, Movie movie) {
    for (int i = 0; i < rentals.size(); i++) {
        Rental r = rentals.get(i);

        if (r.getCustomer() == customer &&
            r.getMovie() == movie &&
            !r.getIsReturned()) {

            rentals.remove(i);
            break;
        }
    }
}
}