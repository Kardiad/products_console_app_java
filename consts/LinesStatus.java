package consts;

public enum LinesStatus {
    ACTIVE(1),
    INACTIVE(2),
    UPDATED(3),
    HIDDEN(4);

    private int status;

    LinesStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return this.status;
    }

    @Override
    public String toString(){
        return Integer.toString(this.status);
    }
}
