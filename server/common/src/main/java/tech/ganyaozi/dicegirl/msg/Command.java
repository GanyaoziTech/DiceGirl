package tech.ganyaozi.dicegirl.msg;


public enum Command {
    Ping(1),
    Pong(2),
    Client_Init(3);

    private int value;

    Command(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
