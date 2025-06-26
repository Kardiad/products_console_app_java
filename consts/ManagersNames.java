package consts;

public enum ManagersNames {
    PRODUCTS("products"),
    LINES("lines"),
    INVOICES("invoices");

    private String manager;

    ManagersNames(String manager){
        this.manager = manager;
    }

    @Override
    public String toString(){
        return this.manager;
    }
}
