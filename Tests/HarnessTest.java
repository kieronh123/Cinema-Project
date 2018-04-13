import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class HarnessTest {

    @Test
    public void successfulGet(){
        boolean bool = true;
        assertThat(bool, is(true));
    }

}