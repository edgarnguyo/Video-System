package Views;

import Backend.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CustomerView {
    private Logic logic;
    private VBox root;

    public CustomerView(Logic logic) {
        this.logic = logic;
        buildUI();
    }

    public void buildUI() {
        Label nameLabel = new Label("Customer Name:");
        nameLabel.setPrefWidth(100);
        TextField nameField = new TextField();
        nameField.setPrefWidth(250);
        Label phoneLabel = new Label("Phone:");
        phoneLabel.setPrefWidth(100);
        TextField phoneField = new TextField();
        phoneField.setPrefWidth(250);
        Label emailLabel = new Label("Email:");
        emailLabel.setPrefWidth(100);
        TextField emailField = new TextField();
        emailField.setPrefWidth(250);

        HBox nameRow = new HBox(10, nameLabel, nameField);
        nameRow.setAlignment(Pos.CENTER_LEFT);

        HBox phoneRow = new HBox(10, phoneLabel, phoneField);
        phoneRow.setAlignment(Pos.CENTER_LEFT);

        HBox emailRow = new HBox(10, emailLabel, emailField);
        emailRow.setAlignment(Pos.CENTER_LEFT);

        Button addButton = new Button("Add Customer");
        addButton.setPrefWidth(150);

        HBox addRow = new HBox(addButton);
        addRow.setAlignment(Pos.CENTER);

        Label registeredLabel = new Label("Registered:");
        registeredLabel.setPrefWidth(100);

        ComboBox<Customer> customerCombo = new ComboBox<>(logic.getCustomers());
        customerCombo.setPrefWidth(250);

        HBox registeredRow = new HBox(10, registeredLabel, customerCombo);
        registeredRow.setAlignment(Pos.CENTER_LEFT);

        Button removeButton = new Button("Remove Customer");
        removeButton.setPrefWidth(150);

        HBox removeRow = new HBox(removeButton);
        removeRow.setAlignment(Pos.CENTER);

        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String phoneText = phoneField.getText();
            String email = emailField.getText();

            if (name.isEmpty() || phoneText.isEmpty() || email.isEmpty()) return;

            int phone = Integer.parseInt(phoneText);

            logic.addCustomer(name, phone, email);
            nameField.clear();
            phoneField.clear();
            emailField.clear();
        });

        removeButton.setOnAction(e -> {
            Customer selected = customerCombo.getValue();
            if (selected == null) return;
            logic.removeCustomer(selected.getName()); 
            customerCombo.setValue(null);
        });

        VBox form = new VBox(12, nameRow, phoneRow, emailRow, addRow, registeredRow, removeRow);
        form.setPadding(new Insets(20));

        root = new VBox(form);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
    }

    public VBox getView() {
        return root;
    }
}