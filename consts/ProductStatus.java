package consts;

public enum ProductStatus  {
    IN_PROGRESS("in progress"),
    ACTIVE("active"),
    HIDDEN("hidden"),
    UPDATED("updated");

    private String status;

    ProductStatus(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
