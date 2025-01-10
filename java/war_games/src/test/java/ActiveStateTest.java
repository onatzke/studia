import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.List;

public class ActiveStateTest {
    private General general1;
    private General general2;
    private Secretary sekretarz;

    @Before
    public void setup() {
        general1 = new General("General1", 1000);
        general2 = new General("General2", 1000);
        sekretarz = new Secretary();
        general1.addObserver(sekretarz);
        general2.addObserver(sekretarz);
    }

    @Test
    public void testSoldierPromotion() {
        Soldier soldier = new Soldier(Rank.SZEREGOWY);
        for (int i = 0; i < 5; i++) {
            soldier.gainExp();
        }
        assertEquals(Rank.KAPRAL, soldier.getRank());
    }

    @Test
    public void testAction() {
        general1.buySoldiers(Rank.SZEREGOWY, 2);
        List<Soldier> participants = general1.getArmy().getSoldiers();
        int initialGold = general1.getGold();

        general1.makeActions(participants);

        assertEquals(initialGold - 2, general1.getGold());
        assertEquals(2, participants.get(0).getExp());
    }

    @Test
    public void testBattleAndDeath() {
        general1.buySoldiers(Rank.MAJOR, 1);
        general2.buySoldiers(Rank.SZEREGOWY, 1);

        Soldier soldier2 = general2.getArmy().getSoldiers().get(0);
        soldier2.setExp(1);

        general1.attack(general2);
        general1.attack(general2);

        assertTrue(soldier2.isDead());
        assertTrue(general2.getArmy().getSoldiers().isEmpty());
        assertFalse(general1.getArmy().getSoldiers().isEmpty());
    }
}