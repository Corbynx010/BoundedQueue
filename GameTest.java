import org.junit.*;

import static org.junit.Assert.*;

public class GameTest {
    /**
     * This is an example test. You can delete it.
     */
    @Test
    public void exampleTest() {
        assertEquals("1 + 1 should equal 2", 2, 1 + 1);
        assertTrue("3 should be less than 4", 3 < 4);
    }

    @Test
    public void generateAndProcess(){
        BoundedQueue<Action> queue = new BoundedQueue<Action>(10, true);
        Action ma = new MoveAction(1, 10);
        queue.put(ma);
        ma = new MoveAction(6, -2);
        queue.put(ma);
        ma = new MoveAction(-3, 12);
        queue.put(ma);
        Game game = new Game();
        game.generateMovements(queue);
        game.process(queue);
        assertTrue("x should be 6, and y should be 17", (game.getX() == 6 && game.getY() == 17));
    }
}
