import org.junit.Test;
import Tills.*;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;
import Tills.Controllers.*;

/**
 * Test class used for JUnit testing of the Tills System.
 * @param time          time is used from PaymentPageController for Testing
 * @param name          name is used from PaymentPageController for Testing
 * @param seat          seat is used from PaymentPageController for Testing
 * @param total         total is used from PaymentPageController for Testing
 * @param row           row is used from PaymentPageController for Testing
 * @param column        column is used from PaymentPageController for Testing
 * @param screeningID   screeningID is used from PaymentPageController for Testing
 */
public class testFields {
    //
    //Fields used for testing
    public PaymentPageController name;
    public PaymentPageController time;
    public PaymentPageController seat;
    public PaymentPageController total;
    public PaymentPageController row;
    public PaymentPageController column;
    public PaymentPageController screeningID;

//    public TicketPageController name;
//    public TicketPageController time;
//    public TicketPageController screeningID;
//    public TicketPageController age;


    private ArrayList testList;

    @BeforeClass
    public static void onceExecutedBeforeAll() {
        System.out.println("@BeforeClass: Execute One at a time");
    }


    /**
     * TestsBelow Will test that the infomration entered into fields are of correct
     * Type
     */
    @Test
    public void testFields() {
        PaymentPageController fields = new PaymentPageController("13:00", "Batman", "25", 10.00, "2", "2", 15);
        assertThat(fields.getTime(), is("13:00"));
        assertThat(fields.getName(), is("Batman"));
        assertThat(fields.getSeat(), is("25"));
        assertThat(fields.getTotal(), is(10.00));
        assertThat(fields.getRow(), is("2"));
        assertThat(fields.getColumn(), is("2"));
        assertThat(fields.getScreeningID(), is(15));

    }

    @Test
    public void testMoreFields() {
        TicketPageController fields = new TicketPageController("13:00", "Batman", 25, "10");
        assertThat(fields.getTime(), is("13:00"));
        assertThat(fields.getName(), is("Batman"));
        assertThat(fields.getAge(), is("10"));
        assertThat(fields.getScreeningID(), is(25));
    }


}
