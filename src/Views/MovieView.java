package Views;

import Backend.*;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MovieView {
    private Logic logic;
    private VBox root;

    public MovieView(Logic logic) {
        this.logic = logic;
        buildUI();
    }

    public void buildUI() {
        Label genreLabel = new Label("Genre:");
        genreLabel.setPrefWidth(100);

        // Bound to live genre list — genres added in GenreView appear here automatically
        ComboBox<Genre> genreCombo = new ComboBox<>(logic.getGenres());
        genreCombo.setPrefWidth(250);

        HBox genreRow = new HBox(10, genreLabel, genreCombo);
        genreRow.setAlignment(Pos.CENTER_LEFT);

        Label nameLabel = new Label("Name:");
        nameLabel.setPrefWidth(100);

        TextField nameField = new TextField();
        nameField.setPrefWidth(250);

        HBox nameRow = new HBox(10, nameLabel, nameField);
        nameRow.setAlignment(Pos.CENTER_LEFT);

        Button saveButton = new Button("Save Movie");
        saveButton.setPrefWidth(250);

        HBox saveRow = new HBox(saveButton);
        saveRow.setAlignment(Pos.CENTER_RIGHT);

        Label registeredLabel = new Label("Registered:");
        registeredLabel.setPrefWidth(100);

        ComboBox<String> registeredCombo = new ComboBox<>();
        registeredCombo.setPrefWidth(250);

        HBox registeredRow = new HBox(10, registeredLabel, registeredCombo);
        registeredRow.setAlignment(Pos.CENTER_LEFT);

        Button removeButton = new Button("Remove Movie");
        removeButton.setPrefWidth(250);

        HBox removeRow = new HBox(removeButton);
        removeRow.setAlignment(Pos.CENTER_RIGHT);

        // When genre changes, refresh the registered movies list for that genre
        // Also attach a listener to the global movie list so saves in this view reflect immediately
        genreCombo.setOnAction(e -> {
            Genre selected = genreCombo.getValue();
            refreshRegistered(registeredCombo, selected);

            logic.getAllMovies().addListener((ListChangeListener<Movie>) change ->
                refreshRegistered(registeredCombo, genreCombo.getValue())
            );
        });

        saveButton.setOnAction(e -> {
            String movieName = nameField.getText();
            Genre selectedGenre = genreCombo.getValue();

            if (movieName.isEmpty() || selectedGenre == null) return;

            logic.addMovie(movieName, selectedGenre);
            // listener on getAllMovies() fires and refreshes registeredCombo
            nameField.clear();
        });

        removeButton.setOnAction(e -> {
            String selectedMovie = registeredCombo.getValue();
            Genre selectedGenre = genreCombo.getValue();

            if (selectedMovie == null || selectedGenre == null) return;

            logic.removeMovie(selectedMovie, selectedGenre);
            registeredCombo.getItems().remove(selectedMovie);
            registeredCombo.setValue(null);
        });

        VBox form = new VBox(12, genreRow, nameRow, saveRow, registeredRow, removeRow);
        form.setPadding(new Insets(20));

        root = new VBox(form);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
    }

    private void refreshRegistered(ComboBox<String> registeredCombo, Genre genre) {
        registeredCombo.getItems().clear();
        registeredCombo.setValue(null);
        if (genre == null) return;
        for (int i = 0; i < logic.getMovieForGenre(genre).size(); i++) {
            registeredCombo.getItems().add(logic.getMovieForGenre(genre).get(i).getName());
        }
    }

    public VBox getView() {
        return root;
    }
}