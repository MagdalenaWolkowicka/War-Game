package card.model;

public enum Rank {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(15),
    QUEEN(20),
    KING(25),
    ACE(30);

    private int number;

    Rank(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}
