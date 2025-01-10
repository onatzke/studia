import java.util.List;

public class BankruptState implements State {
    @Override
    public void Actions(General general, List<Soldier> participants) {
        general.informObserver("Zbankrutowany generał nie może podejmować akcji");
    }

    @Override
    public void Attack(General attacker, General defender) {
        if (attacker.getArmy().getFullPower() > 0) {
            new ActiveState().Attack(attacker, defender);
            if (attacker.getGold() > 100) {
                attacker.setState(new ActiveState());
                attacker.informObserver("Generał wrócił do aktywnego stanu");
            }
        }
    }

    @Override
    public void BuySoldiers(General general, Rank rank, int count) {
        general.informObserver("Zbankrutowany generał nie może kupować żołnierzy");
    }
}
