package Spring.MultiThreading.model;

public enum AccountType {
    SAVINGS(1),
    RECURRING(2),
    FIXED(3);

    private final int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


