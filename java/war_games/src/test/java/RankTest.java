import org.junit.Test;
import static org.junit.Assert.*;

public class RankTest {

    @Test
    public void testRankValues() {
        assertEquals(1, Rank.SZEREGOWY.getValue());
        assertEquals(2, Rank.KAPRAL.getValue());
        assertEquals(3, Rank.KAPITAN.getValue());
        assertEquals(4, Rank.MAJOR.getValue());
    }

    @Test
    public void testRankOrder() {
        Rank[] expectedOrder = {Rank.SZEREGOWY, Rank.KAPRAL, Rank.KAPITAN, Rank.MAJOR};
        assertArrayEquals(expectedOrder, Rank.values());
    }

    @Test
    public void testValueComparisons() {
        assertTrue(Rank.MAJOR.getValue() > Rank.KAPITAN.getValue());
        assertTrue(Rank.KAPITAN.getValue() > Rank.KAPRAL.getValue());
        assertTrue(Rank.KAPRAL.getValue() > Rank.SZEREGOWY.getValue());
    }

}
