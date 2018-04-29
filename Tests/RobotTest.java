import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Dimension;

public class RobotTest {
    Robot robot = new Robot();

    public static void main(String[] args) throws AWTException {
        new RobotTest();
    }

    public RobotTest() throws AWTException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() / 2;
        double height = screenSize.getHeight();
        float xpos = (float) width;
        float ypos = (float) height;
        robot.setAutoDelay(40);
        robot.setAutoWaitForIdle(true);
        //140 first X
        //+120 per button X

        //230 first Y
        //+340 per button Y
        //robot.delay(4000);


        //PURCHASING VIP SENIOR TICKET WITH REGULAR INPUT - batman begins first viewing
        //Clicking movie
        robot.mouseMove((int) (xpos * 0.072), (int) (ypos * 0.249));
        leftClick();
        //TICKET SELECTION PAGE
        ticketSelect(xpos, ypos, 1, 1);
        //Select seat 3C
        robot.mouseMove((int) (xpos * 0.5), (int) (ypos * 0.559));
        leftClick();
        //Selecting confirm
        robot.mouseMove((int) (xpos * 0.125), (int) (ypos * 0.915));
        leftClick();
        //RECEIPTS PAGE
        receiptPageTest(xpos, ypos, "55");

        //PURCHASING REGULAR CHILD TICKET
        robot.mouseMove((int) (xpos * 0.59), (int) (ypos * 0.54));
        leftClick();
        ticketSelect(xpos, ypos, 2, 0);
        robot.mouseMove((int) (xpos * 0.104), (int) (ypos * 0.324));
        leftClick();
        robot.mouseMove((int) (xpos * 0.125), (int) (ypos * 0.915));
        leftClick();
        receiptPageTest(xpos, ypos, "20");

        //PURCHASING PURCHASING NON VIP ADULT
        robot.mouseMove((int) (xpos * 0.59), (int) (ypos * 0.249));
        leftClick();
        ticketSelect(xpos, ypos, 0, 0);
        robot.mouseMove((int) (xpos * 0.5), (int) (ypos * 0.324));
        leftClick();
        robot.mouseMove((int) (xpos * 0.125), (int) (ypos * 0.915));
        leftClick();
        receiptPageTest(xpos, ypos, "15.50");
        //650+ 39 Y
        //840 X

        //TESTING Amount Given Inputs
        //PURCHASING PURCHASING NON VIP ADULT
        robot.mouseMove((int) (xpos * 0.59), (int) (ypos * 0.249));
        leftClick();
        ticketSelect(xpos, ypos, 0, 0);
        robot.mouseMove((int) (xpos * 0.104), (int) (ypos * 0.324));
        leftClick();
        robot.mouseMove((int) (xpos * 0.125), (int) (ypos * 0.915));
        leftClick();
        //after confirm
        //Typing a negative
        type("-50");
        robot.mouseMove((int) (xpos * 0.411), (int) (ypos * 0.749));
        leftClick();
        robot.mouseMove((int) (xpos * 0.614), (int) (ypos * 0.25833));
        leftClick();
        for (int i = 0; i < 6; i++) {
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        }
        //Typing a String
        type("abc");
        robot.mouseMove((int) (xpos * 0.411), (int) (ypos * 0.749));
        leftClick();
        robot.mouseMove((int) (xpos * 0.614), (int) (ypos * 0.25833));
        leftClick();
        for (int i = 0; i < 6; i++) {
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        }
        //Typing a value that is lower than the amount due
        type("1");
        robot.mouseMove((int) (xpos * 0.411), (int) (ypos * 0.749));
        leftClick();
        robot.mouseMove((int) (xpos * 0.614), (int) (ypos * 0.25833));
        leftClick();

        //Returning to home
        robot.mouseMove((int) (xpos * 0.411), (int) (ypos * 0.619));
        leftClick();

        // robot.mouseMove(40, 160);
        // robot.delay(500);
        //
        // leftClick();
        // robot.delay(500);
        // leftClick();
        //
        // //robot.delay(500);
        // //type("This is a test of the Java Robot class");

        System.exit(0);
    }

    private void leftClick() {
        robot.delay(100);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(200);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(200);
    }

    private void type(int i) {
        robot.delay(40);
        robot.keyPress(i);
        robot.keyRelease(i);
    }

    private void type(String s) {
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            int code = b;
            // keycode only handles [A-Z] (which is ASCII decimal [65-90])
            if (code > 96 && code < 123) code = code - 32;
            robot.delay(40);
            robot.keyPress(code);
            robot.keyRelease(code);
        }
    }

    //Selects ticket type and if VIP -- NOT ticket itself
    private void ticketSelect(float xpos, float ypos, int ticket, int vip) {
        robot.delay(500);
        robot.mouseMove((int) (xpos * 0.385), (int) (ypos * 0.221));
        leftClick();
        robot.delay(500);
        //Select adult
        float ticket_percentage;
        if (ticket == 0) {
            //Adult
            robot.mouseMove((int) (xpos * 0.385), (int) (ypos * 0.2675));
        } else if (ticket == 1) {
            //Senior
            robot.mouseMove((int) (xpos * 0.385), (int) (ypos * 0.29));
        } else if (ticket == 2) {
            //Child
            robot.mouseMove((int) (xpos * 0.385), (int) (ypos * 0.3));
        }
        robot.delay(100);
        leftClick();
        robot.mouseMove((int) (xpos * 0.885), (int) (ypos * 0.221));
        leftClick();
        if (vip == 0) {
            //NOT VIP
            robot.mouseMove((int) (xpos * 0.885), (int) (ypos * 0.28));
        } else if (vip == 1) {
            //VIP
            robot.mouseMove((int) (xpos * 0.885), (int) (ypos * 0.270));
        }
        leftClick();
    }

    private void receiptPageTest(float xpos, float ypos, String input) {
        robot.delay(500);
        type(input);
        robot.mouseMove((int) (xpos * 0.411), (int) (ypos * 0.749));
        leftClick();
        //Creating the PDF
        robot.mouseMove((int) (xpos * 0.411), (int) (ypos * 0.688));
        leftClick();
        //Returning to home
        robot.mouseMove((int) (xpos * 0.411), (int) (ypos * 0.619));
        leftClick();
    }
}
