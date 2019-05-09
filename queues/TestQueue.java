/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */


public class TestQueue {
    public void testAddFirst() {
        Deque<Integer> testDeque = new Deque<Integer>();
        testDeque.addFirst(1);
        assert (testDeque.size() == 1);
    }

    public void testRemoveFirst() {
        Deque<Integer> testDeque = new Deque<Integer>();
        testDeque.addFirst(1);
        testDeque.removeFirst();
    }

    public void testIterator() {
        Deque<Integer> testDeque = new Deque<Integer>();
        for (int i = 1; i < 10; i++) {
            testDeque.addFirst(i);
        }
        for (int i : testDeque) {
            System.out.println(i);
        }
    }


    public static void main(String[] args) {
        TestQueue tQ = new TestQueue();
        tQ.testAddFirst();
        tQ.testRemoveFirst();
        tQ.testIterator();
    }
}
