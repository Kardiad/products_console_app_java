package entities;

import consts.LinesStatus;

import java.util.Objects;

public class Lines{

    private int id;
    private Products products;
    private int quantity;
    private double totalLinePrice;
    private LinesStatus status;

    public Lines(){
        this.id = 0;
        this.products = new Products();
        this.quantity = 0;
        this.totalLinePrice =0;
        this.status = LinesStatus.HIDDEN;
    }

    public Lines(int id, Products products, int quantity, double totalLinePrice, LinesStatus status){
        this.id = id;
        this.products = products;
        this.quantity = quantity;
        this.totalLinePrice = totalLinePrice;
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Lines lines = (Lines) object;
        if(this.id == lines.id) return true;
        if(this.quantity == lines.quantity) return true;
        if(Double.compare(totalLinePrice, lines.totalLinePrice) == 0) return true;
        if(Objects.equals(products, lines.products)) return true;
        return lines.getStatus().equals(this.getStatus());
    }

    @Override
    public String toString() {
        return "{"
                +"  id:\"" + id + "\""
                +", products: \"" + products + "\""
                +", quantity:\"" + quantity + "\""
                +", totalLinePrice:\"" + totalLinePrice + "\""
                +", status:\""+ status + "\""
                +"}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products, quantity, totalLinePrice);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalLinePrice() {
        return totalLinePrice;
    }

    public void setTotalLinePrice(double totalLinePrice) {
        this.totalLinePrice = totalLinePrice;
    }

    public LinesStatus getStatus() {
        return status;
    }

    public void setStatus(LinesStatus status) {
        this.status = status;
    }
}
