import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class GeneralTest {
    private General general1;
    private General general2;
    private Secretary sekretarz;

    @Before
    public void setup() {
        general1 = new General("General 1", 1000);
        general2 = new General("General 2", 1000);
        sekretarz = new Secretary();
        general1.addObserver(sekretarz);
        general2.addObserver(sekretarz);
    }

    @Test
    public void testBattleOutcome() {
        general1.buySoldiers(Rank.KAPITAN, 2);
        general2.buySoldiers(Rank.SZEREGOWY, 3);

        general1.attack(general2);

        assertTrue(general1.getGold() > 1000);
        assertTrue(general2.getGold() < 1000);
    }

    @Test
    public void testManeuvers() {
        general1.buySoldiers(Rank.SZEREGOWY, 2);
        int initialGold = general1.getGold();
        general1.makeActions(general1.getArmy().getSoldiers());

        assertEquals(2, general1.getArmy().getSoldiers().get(0).getExp());
        assertTrue(general1.getGold() < initialGold);
    }

    @Test
    public void testBuySoldiers() {
        int initialGold = general1.getGold();
        general1.buySoldiers(Rank.SZEREGOWY, 2);

        assertEquals(2, general1.getArmy().getSoldiers().size());
        assertEquals(initialGold - 20, general1.getGold());
    }

    @Test
    public void testSetState() {
        State newState = new ActiveState();
        general2.setState(newState);
        assertEquals(newState, general2.getState());
    }

    @Test
    public void testAddObserver() {
        general1.addObserver(sekretarz);
        general1.buySoldiers(Rank.SZEREGOWY, 1);
        assertFalse(sekretarz.getArmyReports().isEmpty());
    }

    @Test
    public void testBankruptState() {
        General general = new General("Bankrupt", 50);
        general.setState(new BankruptState());

        General enemy = new General("Wróg", 1000);
        enemy.buySoldiers(Rank.SZEREGOWY, 1);

        general.buySoldiers(Rank.SZEREGOWY, 1);
        assertEquals(50, general.getGold());

        general.attack(enemy);
    }

    @Test
    public void testBankruptToActiveTransition() {
        General general = new General("General", 1000);
        general.buySoldiers(Rank.KAPITAN, 2);
        general.setGold(50);
        general.setState(new BankruptState());

        General enemy = new General("Bakrupt", 1000);
        enemy.buySoldiers(Rank.SZEREGOWY, 1);

        general.attack(enemy);

        assertTrue("Generał powinien posiadać ponad 100 zlota.", general.getGold() > 100);
        assertTrue("Stan powinien być ActiveState", general.getState() instanceof ActiveState);
    }

    @Test
    public void testManeuverSystem() {
        general1.buySoldiers(Rank.SZEREGOWY, 2);
        List<Soldier> participants = general1.getArmy().getSoldiers();
        int initialGold = general1.getGold();

        general1.makeActions(participants);

        assertEquals(initialGold - 2, general1.getGold());
        assertEquals(2, participants.get(0).getExp());
    }
}
