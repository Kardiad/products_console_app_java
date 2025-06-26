package consts;

public enum AppStatus {
    START(1),
    INSERT(2),
    DELETE(3),
    UPDATE(4),
    LIST(5),
    FIND(6),
    EXIT(999),
    RETRY(777);

    private final int status;

    AppStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return this.status;
    }

    @Override
    public String toString() {
        return Integer.toString(this.status);
    }
}
