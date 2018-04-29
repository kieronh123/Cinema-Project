import JSON.*;
import Tills.*;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class JSONTest {
    @Test
    public void moviesJsonTest(){
        String moviesString = "[{\"Movie_Runtime\": 7200, \"Movie_Name\": \"Batman Begins\", \"Movie_Image\": \"image_url1\", \"Movie_Rating\": \"12A\", \"Movie_Info\": \"Man scared of bats dresses up to beat up people\", \"Movie_ID\": 1}, {\"Movie_Runtime\": 8400, \"Movie_Name\": \"Superman: Man of Steel\", \"Movie_Image\": \"image_url2\", \"Movie_Rating\": \"15\", \"Movie_Info\": \"Is it a bird, is it a plane? No its Superman!\", \"Movie_ID\": 2}]";
        Movie[] movies = JSON.moviesFromJson(moviesString);
        String[] stringArray = {movies[0].toString(), movies[1].toString()};

        assertThat(stringArray[0], is((new Movie(1, "Batman Begins", "12A", "7200", "Man scared of bats dresses up to beat up people", "image_url1")).toString()));
        assertThat(stringArray[1], is((new Movie(2, "Superman: Man of Steel", "15", "8400", "Is it a bird, is it a plane? No its Superman!", "image_url2")).toString()));
    }

    @Test
    public void movieJsonTest(){
        String movieString = "[{\"Movie_Runtime\": 7200, \"Movie_Name\": \"Batman Begins\", \"Movie_Image\": \"image_url1\", \"Movie_Rating\": \"12A\", \"Movie_Info\": \"Man scared of bats dresses up to beat up people\", \"Movie_ID\": 1}]";
        Movie movie = JSON.movieFromJson(movieString);
        String string = movie.toString();

        assertThat(string, is((new Movie(1, "Batman Begins", "12A", "7200", "Man scared of bats dresses up to beat up people", "image_url1")).toString()));
    }
    
}
