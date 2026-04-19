package Views;

import Backend.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RentalView {
    private Logic logic;
    private VBox root;

    public RentalView(Logic logic) {
        this.logic = logic;
        buildUI();
    }

    public void buildUI() {
        Label customerLabel = new Label("Customer:");
        customerLabel.setPrefWidth(100);

        // Bound to live customer list
        ComboBox<Customer> customerCombo = new ComboBox<>(logic.getCustomers());
        customerCombo.setPrefWidth(250);

        HBox customerRow = new HBox(10, customerLabel, customerCombo);
        customerRow.setAlignment(Pos.CENTER_LEFT);

        Label movieLabel = new Label("Movie:");
        movieLabel.setPrefWidth(100);

        // Bound to live movie list
        ComboBox<Movie> movieCombo = new ComboBox<>(logic.getAllMovies());
        movieCombo.setPrefWidth(250);

        HBox movieRow = new HBox(10, movieLabel, movieCombo);
        movieRow.setAlignment(Pos.CENTER_LEFT);

        Button rentButton = new Button("Rent Movie");
        rentButton.setPrefWidth(250);

        HBox rentRow = new HBox(rentButton);
        rentRow.setAlignment(Pos.CENTER_RIGHT);

        Label registeredLabel = new Label("Rentals:");
        registeredLabel.setPrefWidth(100);

        ComboBox<String> rentalCombo = new ComboBox<>();
        rentalCombo.setPrefWidth(250);

        HBox registeredRow = new HBox(10, registeredLabel, rentalCombo);
        registeredRow.setAlignment(Pos.CENTER_LEFT);

        Button removeButton = new Button("Remove Rental");
        removeButton.setPrefWidth(250);

        HBox removeRow = new HBox(removeButton);
        removeRow.setAlignment(Pos.CENTER_RIGHT);

        rentButton.setOnAction(e -> {
            Customer customer = customerCombo.getValue();
            Movie movie = movieCombo.getValue();

            if (customer == null || movie == null) return;

            logic.addRental(customer, movie);
            rentalCombo.getItems().add(customer.getName() + " - " + movie.getName());
        });

        removeButton.setOnAction(e -> {
            String selected = rentalCombo.getValue();
            if (selected == null) return;

            String[] parts = selected.split(" - ");
            if (parts.length != 2) return;

            Customer customer = findCustomer(parts[0]);
            Movie movie = findMovie(parts[1]);

            if (customer == null || movie == null) return;

            logic.removeRental(customer, movie);
            rentalCombo.getItems().remove(selected);
            rentalCombo.setValue(null);
        });

        VBox form = new VBox(12, customerRow, movieRow, rentRow, registeredRow, removeRow);
        form.setPadding(new Insets(20));

        root = new VBox(form);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
    }

    private Customer findCustomer(String name) {
        for (int i = 0; i < logic.getCustomers().size(); i++) {
            Customer c = logic.getCustomers().get(i);
            if (c.getName().equals(name)) return c;
        }
        return null;
    }

    private Movie findMovie(String name) {
        for (int i = 0; i < logic.getAllMovies().size(); i++) {
            Movie m = logic.getAllMovies().get(i);
            if (m.getName().equals(name)) return m;
        }
        return null;
    }

    public VBox getView() {
        return root;
    }
}