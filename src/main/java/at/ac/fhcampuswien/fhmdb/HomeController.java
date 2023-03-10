package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    @FXML
    public JFXComboBox<Genre> genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();


    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        searchBtn.setOnAction(actionEvent -> {
            List<Movie> movies = new ArrayList<>();
            if(genreComboBox.getPromptText().equals("Filter by Genre"))
                genreComboBox.getSelectionModel().selectFirst();
            if (searchField.getText().isEmpty()
                    && genreComboBox.getSelectionModel().getSelectedItem().toString().equals("NO_GENRE")){
                movies.addAll(allMovies);
            }
            for (Movie movie : allMovies) {
                if ((!searchField.getText().isEmpty())
                        && ((movie.getTitle().toLowerCase().contains(searchField.getText().toLowerCase())
                        || movie.getDescription().toLowerCase().contains(searchField.getText().toLowerCase()))
                        && genreComboBox.getSelectionModel().getSelectedItem().toString().equals("NO_GENRE"))){
                    movies.add(movie);
                }
            }
            observableMovies.setAll(movies);
        });

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            Comparator<Movie> comparator = (movie1, movie2) -> movie1.getTitle().compareTo(movie2.getTitle());

            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                comparator = Comparator.comparing(Movie::getTitle);
                FXCollections.sort(observableMovies, comparator);
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                comparator = comparator.reversed();
                FXCollections.sort(observableMovies, comparator);
                sortBtn.setText("Sort (asc)");
            }
        });
    }
}