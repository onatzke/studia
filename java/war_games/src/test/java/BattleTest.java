import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class BattleTest {
    private General general1;
    private General general2;
    private ActiveState activeState;
    private Secretary sekretarz;

    @Before
    public void setup() {
        general1 = new General("General 1", 1000);
        general2 = new General("General 2", 1000);
        activeState = new ActiveState();
        sekretarz = new Secretary();
        general1.addObserver(sekretarz);
        general2.addObserver(sekretarz);
    }

    @Test
    public void testBattleOutcomeWinnerGainsGold() {
        general1.buySoldiers(Rank.KAPITAN, 2);
        general2.buySoldiers(Rank.SZEREGOWY, 1);

        int initialWinnerGold = general1.getGold();
        int initialLoserGold = general2.getGold();

        activeState.BattleOutcome(general1, general2, true);

        assertTrue(general1.getGold() > initialWinnerGold);
        assertTrue(general2.getGold() < initialLoserGold);
        assertEquals(initialLoserGold - (initialLoserGold / 10), general2.getGold());
    }

    @Test
    public void testBattleOutcomeWinnerSoldiersGainExp() {
        general1.buySoldiers(Rank.SZEREGOWY, 2);
        general2.buySoldiers(Rank.SZEREGOWY, 1);

        List<Soldier> winnerSoldiers = general1.getArmy().getSoldiers();
        int initialExperience = winnerSoldiers.get(0).getExp();

        activeState.BattleOutcome(general1, general2, true);

        for (Soldier soldier : winnerSoldiers) {
            assertTrue(soldier.getExp() > initialExperience);
        }
    }

    @Test
    public void testBattleOutcomeLoserSoldiersLoseExp() {
        general1.buySoldiers(Rank.KAPITAN, 1);
        general2.buySoldiers(Rank.SZEREGOWY, 1);

        Soldier loserSoldier = general2.getArmy().getSoldiers().get(0);
        int initialExperience = loserSoldier.getExp();

        activeState.BattleOutcome(general1, general2, true);

        assertTrue(loserSoldier.getExp() < initialExperience);
    }


    @Test
    public void testBattleDraw() {
        general1.buySoldiers(Rank.SZEREGOWY, 1);
        general2.buySoldiers(Rank.SZEREGOWY, 1);

        int initial1Size = general1.getArmy().getSoldiers().size();
        int initial2Size = general2.getArmy().getSoldiers().size();

        activeState.Attack(general1, general2);

        assertEquals(initial1Size - 1, general1.getArmy().getSoldiers().size());
        assertEquals(initial2Size - 1, general2.getArmy().getSoldiers().size());
    }


    @Test
    public void testBattleEqualPowerArmies() {
        general1.buySoldiers(Rank.KAPRAL, 1);
        general2.buySoldiers(Rank.SZEREGOWY, 2);

        int initial1Size = general1.getArmy().getSoldiers().size();
        int initial2Size = general2.getArmy().getSoldiers().size();

        activeState.Attack(general1, general2);

        assertEquals(initial1Size - 1, general1.getArmy().getSoldiers().size());
        assertEquals(initial2Size - 1, general2.getArmy().getSoldiers().size());
    }
    @Test
    public void testBuySoldiers() {
        int initialGold = general1.getGold();
        int soldierCount = 2;
        Rank rank = Rank.SZEREGOWY;

        activeState.BuySoldiers(general1, rank, soldierCount);

        assertEquals(soldierCount, general1.getArmy().getSoldiers().size());
        assertEquals(initialGold - (10 * rank.getValue() * soldierCount), general1.getGold());
    }

    @Test
    public void testInsufficientGold() {
        general1.setGold(5);
        int initialGold = general1.getGold();
        int initialArmySize = general1.getArmy().getSoldiers().size();

        activeState.BuySoldiers(general1, Rank.SZEREGOWY, 1);

        assertEquals(initialArmySize, general1.getArmy().getSoldiers().size());
        assertEquals(initialGold, general1.getGold());
    }

}