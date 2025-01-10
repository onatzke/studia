import java.util.List;

public class DefeatedState implements State {
    @Override
    public void Actions(General general, List<Soldier> participants) {
        general.informObserver("Pokonany generał nie może podejmować akcji");
    }

    @Override
    public void Attack(General attacker, General defender) {
        attacker.informObserver("Pokonany generał nie może atakować");
    }

    @Override
    public void BuySoldiers(General general, Rank rank, int count) {
        general.informObserver("Pokonany generał nie może kupować żołnierzy");
    }
}
