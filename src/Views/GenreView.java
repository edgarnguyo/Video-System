package Views;

import Backend.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GenreView {
    private Logic logic;
    private VBox root;

    public GenreView(Logic logic) {
        this.logic = logic;
        buildUI();
    }

    public void buildUI() {
        Label nameLabel = new Label("Name:");
        nameLabel.setPrefWidth(100);
        TextField nameField = new TextField();
        nameField.setPrefWidth(250);
        HBox nameRow = new HBox(10, nameLabel, nameField);
        nameRow.setAlignment(Pos.CENTER_LEFT);

        Button saveButton = new Button("Save");
        saveButton.setPrefWidth(150);
        HBox saveRow = new HBox(saveButton);
        saveRow.setAlignment(Pos.CENTER);

        Label registeredLabel = new Label("Registered:");
        registeredLabel.setPrefWidth(100);

        // Bound to the live ObservableList<Genre> — updates automatically when genres are added/removed
        // Genre.toString() returns its name so the combo displays it correctly
        ComboBox<Genre> registeredCombo = new ComboBox<>(logic.getGenres());
        registeredCombo.setPrefWidth(250);
        HBox registeredRow = new HBox(10, registeredLabel, registeredCombo);
        registeredRow.setAlignment(Pos.CENTER_LEFT);

        Button removeButton = new Button("Remove");
        removeButton.setPrefWidth(150);
        HBox removeRow = new HBox(removeButton);
        removeRow.setAlignment(Pos.CENTER);

        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            if (name.isEmpty()) return;
            logic.addGenre(name); 
            nameField.clear();
        });

        removeButton.setOnAction(e -> {
            Genre selected = registeredCombo.getValue();
            if (selected == null) return;
            logic.removeGenre(selected.getName()); 
            registeredCombo.setValue(null);
        });

        VBox form = new VBox(12, nameRow, saveRow, registeredRow, removeRow);
        form.setPadding(new Insets(20));

        root = new VBox(form);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
    }

    public VBox getView() {
        return root;
    }
}