import java.awt.MouseInfo;
import java.awt.Point;

public class TikTokDetect {
    private static int previousY = -1; // last Y
    private static int swipeCount = 0; // count of swipes

    // Check mouse by 90 pixels
    public static boolean hasSwipedUp(int previousY, int currentY) {
        return (previousY - currentY) >= 90;
    }

    public static void main(String[] args) {
        boolean wasInArea = false;

        while (true) {
            Point location = MouseInfo.getPointerInfo().getLocation();
            int x = location.x;
            int y = location.y;

            boolean isInArea = (x >= 10 && x <= 382) && (y >= 77 && y <= 844);

            if (isInArea) {
                if (!wasInArea) {
                    // initialize previousY
                    previousY = y;
                } else {
                    // Mouse in area = check swipe
                    if (hasSwipedUp(previousY, y)) {
                        swipeCount++;
                        System.out.println("Swipe up detected! Total swipes: " + swipeCount);
                        // Update previousY to prevent counting multiple times for same swipe(doesnt
                        // even really work i used the timer at the end to filter missinputs)
                        previousY = y;
                    } else {
                        previousY = y;
                    }
                }
            } else {
                // Outside area resets previousY
                previousY = -1;
            }

            wasInArea = isInArea;

            try {
                Thread.sleep(500); // adjust frequency as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
