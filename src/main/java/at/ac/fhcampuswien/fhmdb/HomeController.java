package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;


public class HomeController implements Initializable {

    boolean isSorted = false;

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

    public List<Movie> allSortedMovies = new ArrayList<>();
    public List<Movie> allSortedMovies2 = new ArrayList<>();
    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes


    Comparator<Movie> comparator = (movie1, movie2) -> movie1.getTitle().compareTo(movie2.getTitle());
    Comparator<Movie> comparator2 = (movie1, movie2) -> movie1.getTitle().compareTo(movie2.getTitle());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list
        allSortedMovies.addAll(allMovies);
        allSortedMovies2.addAll(allMovies);
        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());
        genreComboBox.getSelectionModel().selectFirst();

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here


        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            //
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortDesc(observableMovies);
                sortDesc(allSortedMovies);
                sortDesc(allSortedMovies2);
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortAsc(observableMovies);
                sortAsc(allSortedMovies);
                sortAsc(allSortedMovies2);
                sortBtn.setText("Sort (asc)");
            }
        });
    }

    public ObservableList<Movie> sortAsc(ObservableList<Movie> list){
        comparator = Comparator.comparing(Movie::getTitle);
        FXCollections.sort(list, comparator);
        return list;
    }

    public ObservableList<Movie> sortDesc(ObservableList<Movie> list){
        comparator = Comparator.comparing(Movie::getTitle).reversed();
        FXCollections.sort(list, comparator);
        return list;
    }

    public List<Movie> sortDesc(List<Movie> list){
        list.sort(Collections.reverseOrder(comparator2));
        return list;
    }

    public List<Movie> sortAsc(List<Movie> list){
        list.sort(comparator2);
        return list;
    }


    public ObservableList<Movie> filterMovies(ActionEvent actionEvent) {
        List<Movie> movies = new ArrayList<>();

        List<Movie> sortedMovies = new ArrayList<>();
       sortedMovies.addAll(observableMovies.stream().toList());

       allSortedMovies.clear();
       allSortedMovies.addAll(allSortedMovies2);

        //show all movies, if nothing is being filtered for

        //shows movies filtered by title OR description
        for (Movie movie : allMovies) {
            if (searchField.getText().isEmpty()
                    && genreComboBox.getSelectionModel().getSelectedItem().toString().equals("NO_FILTER")){
                movies.add(movie);
            }
            if ((!searchField.getText().isEmpty())
                    && ((movie.getTitle().toLowerCase().contains(searchField.getText().toLowerCase())
                    || movie.getDescription().toLowerCase().contains(searchField.getText().toLowerCase()))
                    && genreComboBox.getSelectionModel().getSelectedItem().toString().equals("NO_FILTER"))){
                movies.add(movie);
            }
            //shows movies filtered by title OR description AND genres
            if ((!searchField.getText().isEmpty())
                    && ((movie.getTitle().toLowerCase().contains(searchField.getText().toLowerCase())
                    || movie.getDescription().toLowerCase().contains(searchField.getText().toLowerCase()))
                    && movie.getGenres().toString().contains(genreComboBox.getSelectionModel().getSelectedItem().toString()))){
                movies.add(movie);
            }
            //shows movies only by selected genre
            if ((searchField.getText().isEmpty())
                    && movie.getGenres().toString().contains(genreComboBox.getSelectionModel().getSelectedItem().toString())){
                movies.add(movie);
            }
        }
        allSortedMovies.retainAll(movies);


        observableMovies.setAll(allSortedMovies);

        return observableMovies;
    }
}