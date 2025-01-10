public class Soldier {
    private Rank rank;
    private int exp;
    private final int ID;
    private static int nextID = 1;

    public Soldier(Rank rank) {
        this.rank = rank;
        this.exp = 1;
        this.ID = nextID++;
    }

    public int getPower() {
        return rank.getValue() * exp;
    }

    public void gainExp() {
        exp++;
        checkForPromotion();
    }

    private void checkForPromotion() {
        if (exp >= 5 * rank.getValue()) {
            switch (rank) {
                case SZEREGOWY:
                    rank = Rank.KAPRAL;
                    break;
                case KAPRAL:
                    rank = Rank.KAPITAN;
                    break;
                case KAPITAN:
                    rank = Rank.MAJOR;
                    break;
                default:
                    break;
            }
            exp = 1;
        }
    }

    public boolean isDead() {
        return exp <= 0;
    }

    public void loseExp() {
        exp--;
        if (isDead()) {
            exp = 0;
        }
    }

    public Rank getRank() {
        return rank;
    }

    public int getId() {
        return ID;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int experience) {
        this.exp = experience;
    }
}