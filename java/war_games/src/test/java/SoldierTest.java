import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class ArmyTest {
    private Army army;

    @Before
    public void setup() {
        army = new Army();
    }

    @Test
    public void testAddSoldier() {
        Soldier soldier = new Soldier(Rank.SZEREGOWY);
        army.addSoldier(soldier);
        assertEquals(1, army.getSoldiers().size());
    }


    @Test
    public void testGetTotalPower() {
        Soldier soldier1 = new Soldier(Rank.SZEREGOWY);
        Soldier soldier2 = new Soldier(Rank.KAPRAL);
        army.addSoldier(soldier1);
        army.addSoldier(soldier2);
        assertEquals(3, army.getFullPower());
    }

    @Test
    public void testRemoveDeadSoldiers() {
        Soldier aliveSoldier = new Soldier(Rank.SZEREGOWY);
        Soldier deadSoldier = new Soldier(Rank.SZEREGOWY);
        deadSoldier.setExp(0);

        army.addSoldier(aliveSoldier);
        army.addSoldier(deadSoldier);
        army.removeDeadSoldiers();

        assertEquals(1, army.getSoldiers().size());
    }

    @Test
    public void testSetSoldiers() {
        Soldier soldier1 = new Soldier(Rank.SZEREGOWY);
        Soldier soldier2 = new Soldier(Rank.KAPRAL);
        List<Soldier> soldiers = Arrays.asList(soldier1, soldier2);

        army.setSoldiers(soldiers);

        assertEquals(2, army.getSoldiers().size());
        assertEquals(3, army.getFullPower());
    }
}
