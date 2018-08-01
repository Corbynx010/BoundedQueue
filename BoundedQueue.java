/**
 * Fixed-capacity collection that supports adding to one end and
 * removing from the other.
 */
public class BoundedQueue<T> {
    int capacity;
    boolean dropOldest;
    Item head;
    static int boundedQueues;
    static int droppedItems;
    /**
     * Constructs an empty BoundedQueue with the given capacity.
     *
     * The parameter dropOldest specifies what will happen when put()
     * is called and the queue is full. If dropOldest is true, the
     * oldest item (which would be returned by get()) is dropped. If
     * dropOldest is false, the new item is dropped.
     */
    public BoundedQueue(int capacity, boolean dropOldest) {
        this.capacity = capacity;
        this.dropOldest = dropOldest;
        boundedQueues++;
    }

    /**
     * Returns the number of items in the queue.
     */
    public int count() {
        return head.count();
    }

    /**
     * Returns true if the queue does not contain any items.
     */
    public boolean empty() {
        if(head == null){ return true; }
        else { return false; }
    }

    /**
     * Returns true if the queue has reached its capacity.
     */
    public boolean full() {
        if(head == null){
            if(capacity == 0){ return true;}
            else if(capacity != 0){ return false; }
        }
        if(head.count() >= capacity){
            return true;
        }
        else{ return false; }
    }

    /**
     * Adds the item to the queue.
     *
     * If the queue is full, an item is dropped as described in the
     * constructor.
     */
    public void put(T item) {
        if(full() && dropOldest){
            add(item);
            get();
            return;
        }
        if(full() && !dropOldest){
            //system prntln ddnt add coz full
            return;
        }
        if(!full()){
            add(item);
            return;
        }
    }

    /**
     * Adds an item to the linked list
     */
    public void add(T item){
        Item temp = new Item(item);
        temp.setNext(head);
        head = temp;
    }

    /**
     * Retrieves and removes the oldest item in the queue.
     *
     * If the queue is empty, returns null.
     */
    public T get() {
        if(head == null) { return null; }
        if(head.getNext() == null) {
            Item temp = head;
            head = null;
            droppedItems++;
            return temp.getItem();
        }
        Item temp;
        temp = head;
        while(temp.getNext().getNext() != null){
            temp = temp.getNext();
        }
        Item temp2 = temp.getNext();
        temp.setNext(null);
        droppedItems++;
        return temp2.getItem();
    }

    public static void reportStats(){
        System.out.println("Total Bound Queues created: " + boundedQueues);
        System.out.println("Total Items dropped: " + droppedItems);
    }

    /**
     * Helper for creating an array with T as the element type. The
     * elements are initialized to null.
    private T[] makeArray(int size) {
        // We would normally use "new T[size]", but we can't because T
        // is a type parameter and Java does not support generic array
        // creation. The following is an imperfect workaround.
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[size];
        return array;
    }
        */
    /**
     * Implements a item in a linked list.
     * Contains the item T and the next item in the list (or null if it is the tail)
     */
    public class Item{
        T item;
        Item next = null;
        public Item(T o)
        {
            item = o;
        }
        public Item getNext(){ return next; }
        public void setNext(Item x){ next = x; }
        public int count() {
            if (getNext() != null) {
                return (1 + getNext().count());
            } else {
                return 1;
            }
        }
        public T getItem() {
            return item;
        }
    }
}
