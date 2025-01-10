import org.junit.Test;
import static org.junit.Assert.*;

public class SoldierTest {
    @Test
    public void testSoldierPromotion() {
        Soldier soldier = new Soldier(Rank.SZEREGOWY);
        for (int i = 0; i < 5; i++) {
            soldier.gainExp();
        }
        assertEquals(Rank.KAPRAL, soldier.getRank());
        assertEquals(2, soldier.getExp());
    }

    @Test
    public void testSoldierDeath() {
        Soldier soldier = new Soldier(Rank.SZEREGOWY);
        soldier.setExp(1);
        soldier.loseExp();
        assertTrue(soldier.isDead());
    }

    @Test
    public void testGetPower() {
        Soldier soldier = new Soldier(Rank.KAPITAN);
        soldier.setExp(2);
        assertEquals(6, soldier.getPower());
    }

}