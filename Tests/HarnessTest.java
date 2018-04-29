
import Tills.Harness;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HarnessTest {
    @Test
    public void testGetRequest() {
        try {
            String string = Harness.sendGet("movies").toString();
            assertThat(string, is("[{\"Movie_ID\": 1, \"Movie_Name\": \"Batman Begins\", \"Movie_Rating\": \"12A\", \"Movie_Runtime\": 7200, \"Movie_Info\": \"Man scared of bats dresses up to beat up people\", \"Movie_Director\": \"Christopher Nolan\", \"Movie_Actors\": \"Christian Bale, Danny Nay, Natalie Portman\", \"Movie_Image\": \"../static/img/batman_begins.jpg\"}, {\"Movie_ID\": 2, \"Movie_Name\": \"Superman: Man of Steel\", \"Movie_Rating\": \"15\", \"Movie_Runtime\": 8400, \"Movie_Info\": \"Is it a bird, is it a plane? No its Superman!\", \"Movie_Director\": \"Michael Bay\", \"Movie_Actors\": \"Gary Oldman, Gary Coleman, Gary Newman\", \"Movie_Image\": \"../static/img/superman.jpg\"}, {\"Movie_ID\": 3, \"Movie_Name\": \"Frozen\", \"Movie_Rating\": \"U\", \"Movie_Runtime\": 7200, \"Movie_Info\": \"Princess with hypothermia estranges her family\", \"Movie_Director\": \"M. Night Shamalamalan\", \"Movie_Actors\": \"Beneictus Chrimblesnatch, Sting, Dimitri Popov\", \"Movie_Image\": \"../static/img/frozen.jpg\"}, {\"Movie_ID\": 4, \"Movie_Name\": \"The Great Escape\", \"Movie_Rating\": \"12A\", \"Movie_Runtime\": 5400, \"Movie_Info\": \"Man who loves tunnels digs out of camp\", \"Movie_Director\": \"Alfred Hitchcock\", \"Movie_Actors\": \"Steve McQueen, Killian Reestein\", \"Movie_Image\": \"../static/img/great_escape.jpg\"}, {\"Movie_ID\": 5, \"Movie_Name\": \"Pirates of the Carribean: The Black Pearl\", \"Movie_Rating\": \"12A\", \"Movie_Runtime\": 9000, \"Movie_Info\": \"Man with alcohol problem steals ships and pretends to be a captain\", \"Movie_Director\": \"Stephen Spielberg\", \"Movie_Actors\": \"Johnny Depp, Kiera Knightly, Legolas\", \"Movie_Image\": \"../static/img/pirates_of_the_caribbean_one.jpg\"}, {\"Movie_ID\": 6, \"Movie_Name\": \"Scary Movie III\", \"Movie_Rating\": \"18+\", \"Movie_Runtime\": 7800, \"Movie_Info\": \"Spooky stuff occurs in this spooky movie\", \"Movie_Director\": \"Quentin Tarentino\", \"Movie_Actors\": \"Danny Dyer, Ray Winston\", \"Movie_Image\": \"../static/img/scary_movie_3.jpg\"}]"));
        } catch (Exception e) {
        }
    }


}
