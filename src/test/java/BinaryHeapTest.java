import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.highloadjava.profilerlab.BinaryHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryHeapTest {
    BinaryHeap binaryHeap = new BinaryHeap();
    @Test
    void swap() {
        List<Integer> list = new ArrayList<>(Arrays.asList(25, 5, 11));
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(5, 25, 11));

        binaryHeap.swap(list, 0, 1);

        assertEquals(expectedList, list);

    }

    @Test
    void sortOurHeap() {
        binaryHeap.clearHeap();
        List<Integer> list = new ArrayList<>(Arrays.asList(25, 5, 11));
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(5, 25, 11));

        for (int i = list.size() / 2; i >= 0; i--) {
            binaryHeap.swap(list, 0, i);//!!!
            binaryHeap.sortOurHeap(list, i, 0);
        }

        assertEquals(list, expectedList);
    }

    @Test
    void getHeap() {
        binaryHeap.clearHeap();
        binaryHeap.adder(20);
        binaryHeap.adder(8);
        binaryHeap.adder(11);
        assertEquals(3, binaryHeap.getHeap().size());
    }

    @Test
    void adder() {
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(1, 25, 27));
        binaryHeap.adder(1);
        binaryHeap.adder(25);
        binaryHeap.adder(27);
        assertEquals(expectedList, binaryHeap.getHeap());
        binaryHeap.clearHeap();
        binaryHeap.adder(25);
        binaryHeap.adder(1);
        binaryHeap.adder(27);
        assertEquals(expectedList, binaryHeap.getHeap());
    }

    @Test
    void deleteMin() {
        binaryHeap.clearHeap();
        binaryHeap.adder(25);
        binaryHeap.adder(1);
        binaryHeap.adder(27);
        binaryHeap.adder(2);
        assertEquals(1, binaryHeap.deleteMin());
    }

    @Test
    void clearHeap() {
        binaryHeap.clearHeap();
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(2, 28));
        binaryHeap.adder(28);
        binaryHeap.adder(2);
        assertEquals(expectedList, binaryHeap.getHeap());
        binaryHeap.clearHeap();
        assertTrue(binaryHeap.getHeap().isEmpty());
    }

    @Test
    void search() {
        binaryHeap.clearHeap();
        binaryHeap.adder(25);
        binaryHeap.adder(2);
        binaryHeap.adder(3);
        assertEquals(25, binaryHeap.search(1));
    }

    @Test
    void heapSort() {
        binaryHeap.clearHeap();
        List<Integer> sortList = new ArrayList<>(Arrays.asList(25, 2, 3, 45, 20));

        binaryHeap.heapSort(sortList, sortList.size());
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(45, 25, 20, 3, 2));

        assertEquals(expectedList, sortList);
    }

    @Test
    void contains() {
        binaryHeap.clearHeap();
        binaryHeap.adder(25);
        binaryHeap.adder(2);
        binaryHeap.adder(3);
        binaryHeap.adder(45);
        binaryHeap.adder(20);
        assertTrue(binaryHeap.contains(45));
        assertFalse(binaryHeap.contains(1));
        binaryHeap.clearHeap();
        assertThrows(IllegalStateException.class, () -> {
            binaryHeap.contains(25);
        });
    }

    @Test
    void isEmpty() {
        binaryHeap.clearHeap();
        assertTrue(binaryHeap.isEmpty());
    }

    @Test
    void outInArray() {
        binaryHeap.clearHeap();
        binaryHeap.adder(25);
        binaryHeap.adder(3);
        binaryHeap.adder(27);
        String expectedStr = "3 25 27";
        assertEquals(expectedStr, binaryHeap.outInArray());
    }

    @Test
    void outInHeap() {
        binaryHeap.clearHeap();
        binaryHeap.adder(25);
        binaryHeap.adder(3);
        binaryHeap.adder(27);
        String expectedStr = "3 \n" +
                "25 27";
        assertEquals(expectedStr.replaceAll("\n", " ").trim(), binaryHeap.outInHeap().replaceAll("\n", " ").trim());
    }

    @Test
    void testConstructor() {
        BinaryHeap binaryHeap = new BinaryHeap(Arrays.asList(25, 3, 27));
        assertEquals(Arrays.asList(3, 25, 27), binaryHeap.getHeap());
    }
}
