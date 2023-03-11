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
    @FXML
    public JFXButton resetBtn;
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
    public final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes


    Comparator<Movie> comparatorForObserve = (movie1, movie2) -> movie1.getTitle().compareTo(movie2.getTitle());
    Comparator<Movie> comparatorForList = (movie1, movie2) -> movie1.getTitle().compareTo(movie2.getTitle());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //on Load filling Lists
        observableMovies.addAll(allMovies);
        allSortedMovies.addAll(allMovies);

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        //ComboBox on Load settings
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());
        genreComboBox.getSelectionModel().selectFirst();

        // Sort button example
        sortBtn.setOnAction(actionEvent -> {
            //
            if(sortBtn.getText().equals("Sort (asc)")) {
                sortDesc(observableMovies);
                sortDesc(allSortedMovies);
                sortBtn.setText("Sort (desc)");
            } else {
                sortAsc(observableMovies);
                sortAsc(allSortedMovies);
                sortBtn.setText("Sort (asc)");
            }
        });
    }

    //sorting for ascending when a observableList is given
    public ObservableList<Movie> sortAsc(ObservableList<Movie> list){
        comparatorForObserve = Comparator.comparing(Movie::getTitle);
        FXCollections.sort(list, comparatorForObserve);
        return list;
    }
    //sorting for descending when a observableList is given
    public ObservableList<Movie> sortDesc(ObservableList<Movie> list){
        comparatorForObserve = Comparator.comparing(Movie::getTitle).reversed();
        FXCollections.sort(list, comparatorForObserve);
        return list;
    }

    //sort for descending when array list is given
    public List<Movie> sortDesc(List<Movie> list){
        list.sort(Collections.reverseOrder(comparatorForList));
        return list;
    }
    //sort for ascending when array list is given

    public List<Movie> sortAsc(List<Movie> list){
        list.sort(comparatorForList);
        return list;
    }

    public List<Movie> filterOptions(List<Movie> listOfMovies,String searchString, String searchGenre,List<Movie> sortedList){
        //temporary moviesList
        List<Movie> movies = new ArrayList<>();

        //fill the empty list with a sortedMovieList
        List<Movie> allSortedMoviesForObserve = new ArrayList<>(sortedList);

        //shows movies filtered by title OR description
        for (Movie movie : listOfMovies) {
            if (searchString.isEmpty()
                    && searchGenre.equals("NO_FILTER")){
                movies.add(movie);
            }
            if ((!searchString.isEmpty())
                    && ((movie.getTitle().toLowerCase().contains(searchString.toLowerCase())
                    || movie.getDescription().toLowerCase().contains(searchString.toLowerCase()))
                    && searchGenre.equals("NO_FILTER"))){
                movies.add(movie);
            }
            //shows movies filtered by title OR description AND genres
            if ((!searchString.isEmpty())
                    && ((movie.getTitle().toLowerCase().contains(searchString.toLowerCase())
                    || movie.getDescription().toLowerCase().contains(searchString.toLowerCase()))
                    && movie.getGenres().toString().contains(searchGenre))){
                movies.add(movie);
            }
            //shows movies only by selected genre
            if ((searchString.isEmpty())
                    && movie.getGenres().toString().contains(searchGenre)){
                movies.add(movie);
            }
        }
        //only elements which are the same in movies and allSortedMoviesForObserve get added to allSortedMoviesforObserve
        allSortedMoviesForObserve.retainAll(movies);

        List<Movie> returnedMovies = new ArrayList<>();
        returnedMovies.addAll(allSortedMoviesForObserve);
        return returnedMovies;
    }

    public ObservableList<Movie> filterMovies(ActionEvent actionEvent) {
        observableMovies.setAll(filterOptions(allMovies,searchField.getText(),genreComboBox.getSelectionModel().getSelectedItem().toString(),allSortedMovies));
        return observableMovies;
    }



    //reset to the original view
    public void resetObserveMovies(ActionEvent actionEvent) {
        observableMovies.setAll(allMovies);
        searchField.setText("");
        genreComboBox.getSelectionModel().selectFirst();

    }

    public void initState() {
        observableMovies.clear();
        observableMovies.setAll(allMovies);
    }


}