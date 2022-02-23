import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

class LinkedListTest {

    @Test
    void testGetIteratorIndex() {
        LinkedList<String> sList = new LinkedList<>(new String[] { "!", "?", ".", "," });
        assertThrows(NullPointerException.class, () -> {
            sList.getIteratorIndex();
        });
        sList.positionIterator();
        assertEquals(0, sList.getIteratorIndex());
        sList.advanceIterator();
        sList.advanceIterator();
        assertEquals(2, sList.getIteratorIndex());
        sList.advanceIterator();
        assertEquals(3, sList.getIteratorIndex());
        sList.advanceIterator();
        assertThrows(NullPointerException.class, () -> {
            sList.getIteratorIndex();
        });

    }

    @Test
    void testFindIndex() {
        LinkedList<Integer> iList = new LinkedList<>();
        assertEquals(-1, iList.findIndex(4));
        LinkedList<String> sList = new LinkedList<>(new String[] { "!", "?", ".", "," });
        assertEquals(0, sList.findIndex("!"));
        assertEquals(1, sList.findIndex("?"));
        assertThrows(NullPointerException.class, () -> {
            sList.getIterator();
        });
        assertEquals(2, sList.findIndex("."));
        assertEquals(3, sList.findIndex(","));

    }

    @Test
    void testAdvanceIteratorToIndex() {
        LinkedList<Integer> iList = new LinkedList<>();
        assertThrows(NullPointerException.class, () -> {
            iList.advanceIteratorToIndex(3);
        });
        LinkedList<String> sList = new LinkedList<>(new String[] { "!", "?", ".", "," });
        assertThrows(NullPointerException.class, () -> {
            sList.advanceIteratorToIndex(2);
        });
        sList.positionIterator();
        sList.advanceIteratorToIndex(2);
        assertEquals(".", sList.getIterator());
        assertThrows(NullPointerException.class, () -> {
            sList.advanceIteratorToIndex(4);
        });
        sList.positionIterator();
        sList.advanceIteratorToIndex(3);
        assertEquals(",", sList.getIterator());
        sList.positionIterator();
        sList.advanceIteratorToIndex(0);
        assertEquals("!", sList.getIterator());

    }
}