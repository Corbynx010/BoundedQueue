import org.junit.*;

import static org.junit.Assert.*;

public class BoundedQueueTest {
    @Test
    public void newQueueIsEmpty() {
        BoundedQueue<String> queue = new BoundedQueue<String>(/*size:*/ 3, /*dropOldest:*/ false);
        assertTrue("new BoundedQueue should be empty", queue.empty());
    }

    @Test
    public void queueWithItemIsNotEmpty() {
        BoundedQueue<String> queue = new BoundedQueue<String>(3, false);
        queue.put("hello");
        assertTrue("BoundedQueue should not be empty after an item is added", !queue.empty());
    }

    @Test
    public void queueCountsItems() {
        BoundedQueue<String> queue = new BoundedQueue<String>(3, false);
        queue.put("hello");
        queue.put("hi");
        queue.put("hey");
        assertTrue("BoundedQueue.count() should return 3", (queue.count()==3));
    }

    @Test
    public void queueWithMaxItemsIsFull() {
        BoundedQueue<String> queue = new BoundedQueue<String>(3, false);
        queue.put("hello");
        queue.put("hi");
        queue.put("hey");
        assertTrue("BoundedQueue should be full after items are added", queue.full());
    }

    @Test
    public void queueWithNotMaxItemIsNotFull() {
        BoundedQueue<String> queue = new BoundedQueue<String>(3, false);
        queue.put("hello");
        assertTrue("BoundedQueue should not be empty after an item is added", !queue.full());
    }

    @Test
    public void queueDropsOldest() {
        BoundedQueue<String> queue = new BoundedQueue<String>(1, true);
        queue.put("hello");
        queue.put("hi");
        queue.put("hey");
        queue.put("greetings");
        assertTrue("BoundedQueue.head should be greetings, and count should be 3 after 4 items are added", (queue.count()==1 && queue.head.getItem().equals("greetings")));
    }

    @Test
    public void queueDoesNotDropsOldest() {
        BoundedQueue<String> queue = new BoundedQueue<String>(3, false);
        queue.put("hello");
        queue.put("hi");
        queue.put("hey");
        queue.put("greetings");
        assertTrue("BoundedQueue.count() should be 3 after 4 items are added", (queue.head.getItem().equals("hey")));
    }
    @Test
    public void queueGetReturnsTail() {
        BoundedQueue<String> queue = new BoundedQueue<String>(3, false);
        queue.put("hello");
        queue.put("hi");
        queue.put("hey");
        assertTrue("BoundedQueue.get() should return hello after tail is got, and count should be 2", (queue.get().equals("hello") && queue.count() == 2));
    }

    @AfterClass
    public static void reportStats() {
        BoundedQueue.reportStats();
    }
}
