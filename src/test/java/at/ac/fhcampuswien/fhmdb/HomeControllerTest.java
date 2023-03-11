package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {



    List<Movie> movieList;
    ObservableList<Movie> actualmovieObservableList;

    List<Movie> dummyList;

    private static HomeController homeController;
    @BeforeAll
    static void init(){
        homeController = new HomeController();
    }
    @Test
    public void test1(){
        homeController.initState();
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }


    @Test
    public void using_sort_Asc_on_un_filtered_list_gets_sorted_ascending_observable_list(){
        //given
            Movie movie = new Movie("CREED III", "Adonis Creed tries very hard to be like Rocky", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.SPORT));
            Movie movie2 = new Movie("Inception", "Man goes to sleep, goes to sleep again and again", Arrays.asList(Genre.ACTION, Genre.DRAMA,Genre.SCIENCE_FICTION,Genre.MYSTERY));
            Movie movie3 = new Movie("ONE PIECE RED", "Ruffy and his CREW have a good sleep, while Shanks and his CREW solos", Arrays.asList(Genre.ACTION, Genre.MUSICAL,Genre.ANIMATION,Genre.ADVENTURE));

            ObservableList<Movie> actualObservableList = FXCollections.observableArrayList();
            List<Movie> actualList = new ArrayList<>();
            actualList.add(movie2);
            actualList.add(movie3);
            actualList.add(movie);

            ObservableList<Movie> expectedObservableList = FXCollections.observableArrayList();
            List<Movie> expectedList = new ArrayList<>();
            expectedList.add(movie);
            expectedList.add(movie2);
            expectedList.add(movie3);

        //when
            actualObservableList.setAll(actualList);
            expectedObservableList.setAll(expectedList);
            actualObservableList = homeController.sortAsc(actualObservableList);

        //then

            assertEquals(expectedObservableList,actualObservableList);
    }


    @Test
    public void using_sort_Desc_on_un_filtered_list_gets_sorted_descending_observable_list(){
        //given
        Movie movie = new Movie("CREED III", "Adonis Creed tries very hard to be like Rocky", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.SPORT));
        Movie movie2 = new Movie("Inception", "Man goes to sleep, goes to sleep again and again", Arrays.asList(Genre.ACTION, Genre.DRAMA,Genre.SCIENCE_FICTION,Genre.MYSTERY));
        Movie movie3 = new Movie("ONE PIECE RED", "Ruffy and his CREW have a good sleep, while Shanks and his CREW solos", Arrays.asList(Genre.ACTION, Genre.MUSICAL,Genre.ANIMATION,Genre.ADVENTURE));

        ObservableList<Movie> actualObservableList = FXCollections.observableArrayList();
        List<Movie> actualList = new ArrayList<>();
        actualList.add(movie2);
        actualList.add(movie);
        actualList.add(movie3);

        ObservableList<Movie> expectedObservableList = FXCollections.observableArrayList();
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(movie3);
        expectedList.add(movie2);
        expectedList.add(movie);

        //when
        actualObservableList.setAll(actualList);
        expectedObservableList.setAll(expectedList);
        actualObservableList = homeController.sortDesc(actualObservableList);

        //then

        assertEquals(expectedObservableList,actualObservableList);
    }

    @Test
    public void using_sort_Asc_on_un_filtered_list_gets_sorted_ascending_list(){
        //given
        Movie movie = new Movie("CREED III", "Adonis Creed tries very hard to be like Rocky", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.SPORT));
        Movie movie2 = new Movie("Inception", "Man goes to sleep, goes to sleep again and again", Arrays.asList(Genre.ACTION, Genre.DRAMA,Genre.SCIENCE_FICTION,Genre.MYSTERY));
        Movie movie3 = new Movie("ONE PIECE RED", "Ruffy and his CREW have a good sleep, while Shanks and his CREW solos", Arrays.asList(Genre.ACTION, Genre.MUSICAL,Genre.ANIMATION,Genre.ADVENTURE));

        List<Movie> actualList = new ArrayList<>();
        actualList.add(movie2);
        actualList.add(movie3);
        actualList.add(movie);

        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(movie);
        expectedList.add(movie2);
        expectedList.add(movie3);

        //when
        actualList = homeController.sortAsc(actualList);

        //then

        assertEquals(expectedList,actualList);
    }


    @Test
    public void using_sort_Desc_on_un_filtered_list_gets_sorted_descending_list(){
        //given
        Movie movie = new Movie("CREED III", "Adonis Creed tries very hard to be like Rocky", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.SPORT));
        Movie movie2 = new Movie("Inception", "Man goes to sleep, goes to sleep again and again", Arrays.asList(Genre.ACTION, Genre.DRAMA,Genre.SCIENCE_FICTION,Genre.MYSTERY));
        Movie movie3 = new Movie("ONE PIECE RED", "Ruffy and his CREW have a good sleep, while Shanks and his CREW solos", Arrays.asList(Genre.ACTION, Genre.MUSICAL,Genre.ANIMATION,Genre.ADVENTURE));

        List<Movie> actualList = new ArrayList<>();
        actualList.add(movie2);
        actualList.add(movie);
        actualList.add(movie3);

        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(movie3);
        expectedList.add(movie2);
        expectedList.add(movie);

        //when
        actualList = homeController.sortDesc(actualList);

        //then

        assertEquals(expectedList,actualList);
    }

    @Test
    public void using_filter_with_no_genre_and_no_query_show_all_movies(){
        //given
        Movie movie = new Movie("CREED III", "Adonis Creed tries very hard to be like Rocky", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.SPORT));
        Movie movie2 = new Movie("ONE PIECE RED", "Ruffy and his CREW have a good sleep, while Shanks and his CREW solos", Arrays.asList(Genre.ACTION, Genre.MUSICAL,Genre.ANIMATION,Genre.ADVENTURE));
        Movie movie3 = new Movie("Inception", "Man goes to sleep, goes to sleep again and again", Arrays.asList(Genre.ACTION, Genre.DRAMA,Genre.SCIENCE_FICTION,Genre.MYSTERY));

        List<Movie> inputList = new ArrayList<>();
        inputList.add(movie);
        inputList.add(movie2);
        inputList.add(movie3);

        List<Movie> actual = new ArrayList<>();

        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(movie);
        expectedList.add(movie2);
        expectedList.add(movie3);


        //when
        actual = homeController.filterOptions(inputList,"","NO_FILTER",inputList);

        //then
        assertEquals(expectedList,actual);
    }

    @Test
    public void using_filter_with_no_genre_and_movie_query_show_one_movie(){
        //given
        Movie movie = new Movie("CREED III", "Adonis Creed tries very hard to be like Rocky", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.SPORT));
        Movie movie2 = new Movie("ONE PIECE RED", "Ruffy and his CREW have a good sleep, while Shanks and his CREW solos", Arrays.asList(Genre.ACTION, Genre.MUSICAL,Genre.ANIMATION,Genre.ADVENTURE));
        Movie movie3 = new Movie("Inception", "Man goes to sleep, goes to sleep again and again", Arrays.asList(Genre.ACTION, Genre.DRAMA,Genre.SCIENCE_FICTION,Genre.MYSTERY));

        List<Movie> inputList = new ArrayList<>();
        inputList.add(movie);
        inputList.add(movie2);
        inputList.add(movie3);

        List<Movie> actual = new ArrayList<>();

        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(movie);


        //when
        actual = homeController.filterOptions(inputList,"crEEd","NO_FILTER",inputList);

        //then
        assertEquals(expectedList,actual);
    }

    @Test
    public void using_filter_with_no_genre_and_movie_query_show_one_movie_when_expected_is_two(){
        //given
        Movie movie = new Movie("CREED III", "Adonis Creed tries very hard to be like Rocky", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.SPORT));
        Movie movie2 = new Movie("ONE PIECE RED", "Ruffy and his CREW have a good sleep, while Shanks and his CREW solos", Arrays.asList(Genre.ACTION, Genre.MUSICAL,Genre.ANIMATION,Genre.ADVENTURE));
        Movie movie3 = new Movie("Inception", "Man goes to sleep, goes to sleep again and again", Arrays.asList(Genre.ACTION, Genre.DRAMA,Genre.SCIENCE_FICTION,Genre.MYSTERY));

        List<Movie> inputList = new ArrayList<>();
        inputList.add(movie);
        inputList.add(movie2);
        inputList.add(movie3);

        List<Movie> actual = new ArrayList<>();

        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(movie);
        expectedList.add(movie2);


        //when
        actual = homeController.filterOptions(inputList,"crEEd","NO_FILTER",inputList);

        //then
        assertNotEquals(expectedList,actual);
    }

    @Test
    public void using_filter_with_genre_and_movie_query_show_one_movie(){
        //given
        Movie movie = new Movie("CREED III", "Adonis Creed tries very hard to be like Rocky", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.SPORT));
        Movie movie2 = new Movie("ONE PIECE RED", "Ruffy and his CREW have a good sleep, while Shanks and his CREW solos", Arrays.asList(Genre.ACTION, Genre.MUSICAL,Genre.ANIMATION,Genre.ADVENTURE));
        Movie movie3 = new Movie("Inception", "Man goes to sleep, goes to sleep again and again", Arrays.asList(Genre.ACTION, Genre.DRAMA,Genre.SCIENCE_FICTION,Genre.MYSTERY));

        List<Movie> inputList = new ArrayList<>();
        inputList.add(movie);
        inputList.add(movie2);
        inputList.add(movie3);

        List<Movie> actual = new ArrayList<>();

        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(movie);


        //when
        actual = homeController.filterOptions(inputList,"crEEd","ACTION",inputList);

        //then
        assertEquals(expectedList,actual);
    }

    @Test
    public void using_filter_with_genre_and_movie_query_show_one_movie_when_expected_is_two(){
        //given
        Movie movie = new Movie("CREED III", "Adonis Creed tries very hard to be like Rocky", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.SPORT));
        Movie movie2 = new Movie("ONE PIECE RED", "Ruffy and his CREW have a good sleep, while Shanks and his CREW solos", Arrays.asList(Genre.ACTION, Genre.MUSICAL,Genre.ANIMATION,Genre.ADVENTURE));
        Movie movie3 = new Movie("Inception", "Man goes to sleep, goes to sleep again and again", Arrays.asList(Genre.ACTION, Genre.DRAMA,Genre.SCIENCE_FICTION,Genre.MYSTERY));

        List<Movie> inputList = new ArrayList<>();
        inputList.add(movie);
        inputList.add(movie2);
        inputList.add(movie3);

        List<Movie> actual = new ArrayList<>();

        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(movie);
        expectedList.add(movie2);


        //when
        actual = homeController.filterOptions(inputList,"crEEd","ACTION",inputList);

        //then
        assertNotEquals(expectedList,actual);
    }

    @Test
    public void using_filter_with_genre_and_movie_query_show_two_movies(){
        //given
        Movie movie = new Movie("CREED III", "Adonis Creed tries very hard to be like Rocky", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.SPORT));
        Movie movie2 = new Movie("ONE PIECE RED", "Ruffy and his CREW have a good sleep, while Shanks and his CREW solos", Arrays.asList(Genre.ACTION, Genre.MUSICAL,Genre.ANIMATION,Genre.ADVENTURE));
        Movie movie3 = new Movie("Inception", "Man goes to sleep, goes to sleep again and again", Arrays.asList(Genre.ACTION, Genre.DRAMA,Genre.SCIENCE_FICTION,Genre.MYSTERY));

        List<Movie> inputList = new ArrayList<>();
        inputList.add(movie);
        inputList.add(movie2);
        inputList.add(movie3);

        List<Movie> actual = new ArrayList<>();

        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(movie);
        expectedList.add(movie2);


        //when
        actual = homeController.filterOptions(inputList,"crE","ACTION",inputList);

        //then
        assertEquals(expectedList,actual);
    }
}