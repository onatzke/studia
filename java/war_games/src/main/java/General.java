import java.util.*;

public class General {
    private String name;
    private Army army;
    private int gold;
    private State state;
    private List<Observer> observers;

    public General(String name, int initialGold) {
        this.name = name;
        this.army = new Army();
        this.gold = initialGold;
        this.observers = new ArrayList<>();
        this.state = new ActiveState();
    }

    public String getName() {
        return name;
    }

    public Army getArmy() {
        return army;
    }

    public int getGold() {
        return gold;
    }

    public State getState() {
        return state;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void informObserver(String message) {
        for (Observer observer : observers) {
            observer.update(getName() + ": " + message);
        }
    }

    public void makeActions(List<Soldier> participants) {
        if (state != null) {
            state.Actions(this, participants);
        }
    }

    public void attack(General defender) {
        if (state != null) {
            state.Attack(this, defender);
        }
    }

    public void buySoldiers(Rank rank, int count) {
        if (state != null) {
            state.BuySoldiers(this, rank, count);
        }
    }
}
