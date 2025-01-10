import java.util.List;


public interface State {
    void Actions(General general, List<Soldier> participants);
    void Attack(General attacker, General defender);
    void BuySoldiers(General general, Rank rank, int count);
}