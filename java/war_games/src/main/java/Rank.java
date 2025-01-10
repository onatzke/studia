public enum Rank {
    SZEREGOWY(1),
    KAPRAL(2),
    KAPITAN(3),
    MAJOR(4);

    private int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
