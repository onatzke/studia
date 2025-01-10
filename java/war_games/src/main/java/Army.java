import java.util.*;

public class Army {
    private List<Soldier> soldiers;

    public Army() {
        this.soldiers = new ArrayList<>();
    }

    public void addSoldier(Soldier soldier) {
        soldiers.add(soldier);
    }

    public void removeSoldier(Soldier soldier) {
        soldiers.remove(soldier);
    }

    public int getFullPower() {
        return soldiers.stream()
                .mapToInt(Soldier::getPower)
                .sum();
    }

    public List<Soldier> getSoldiers() {
        return new ArrayList<>(soldiers);
    }

    public void setSoldiers(List<Soldier> soldiers) {
        this.soldiers = new ArrayList<>(soldiers);
    }

    public void removeDeadSoldiers() {
        soldiers.removeIf(Soldier::isDead);
    }

    public Soldier killOneSoldier() {
        if (soldiers.isEmpty()) return null;
        Random random = new Random();
        return soldiers.get(random.nextInt(soldiers.size()));
    }
}