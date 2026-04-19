import Backend.Logic;
import Views.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;


public class App extends Application {
    @Override
    public void start(Stage stage){
        Logic backend = new Logic();
        GenreView genreView = new GenreView(backend);
        MovieView movieView = new MovieView(backend);
        CustomerView customerView = new CustomerView(backend);
        RentalView rentalView = new RentalView(backend);

        Tab genreTab = new Tab("Genres", genreView.getView());
        Tab movieTab = new Tab("Movies", movieView.getView());
        Tab customerTab = new Tab("Customers", customerView.getView());
        Tab rentalTab = new Tab("Rentals", rentalView.getView());

        genreTab.setClosable(false);
        movieTab.setClosable(false);
        customerTab.setClosable(false);
        rentalTab.setClosable(false);
 
        TabPane tabPane = new TabPane(genreTab, movieTab, customerTab, rentalTab);
 
        Scene scene = new Scene(tabPane, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Video System");
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
