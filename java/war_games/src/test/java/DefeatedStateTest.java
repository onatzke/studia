import org.junit.Test;
import static org.junit.Assert.*;

public class DefeatedStateTest {
    @Test
    public void testDefeatedState() {
        General general = new General("Defeated", 1000);
        general.setState(new BankruptState());
        general.buySoldiers(Rank.SZEREGOWY, 1);

        assertEquals(1000, general.getGold());
        assertTrue(general.getArmy().getSoldiers().isEmpty());
    }
}