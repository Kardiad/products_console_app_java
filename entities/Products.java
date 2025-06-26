package entities;

import consts.ProductStatus;

public class Products {
    private int id;
    private String name;
    private String brand;
    private ProductStatus status;
    private double pricePerUnit;

    public Products(){
        this.id = 0;
        this.name = "?";
        this.brand = "?";
        this.status = ProductStatus.HIDDEN;
        this.pricePerUnit = 0;
    }

    public Products(int id, String name, String brand, ProductStatus status, double pricePerUnit){
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.status = status;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        Products castedProduct = (Products) obj;
        if(castedProduct.getId() == this.id){
            return true;
        }
        if(!castedProduct.getName().isEmpty() && this.name.startsWith(castedProduct.getName())){
            return true;
        }
        if(!castedProduct.getBrand().isEmpty() && this.brand.startsWith(castedProduct.getBrand())){
            return true;
        }
        if(castedProduct.getPricePerUnit() == this.pricePerUnit){
            return true;
        }
        return castedProduct.getStatus()!=null && this.status.equals(castedProduct.getStatus());
    }

    @Override
    public String toString() {
        return "{ id : "+this.id+", name : \""+this.name+"\", brand: \""+this.brand+"\", status: \""+this.status+"\", pricePerUnit:\""+this.pricePerUnit+"\"}";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Products setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Products setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public Products setStatus(ProductStatus status) {
        this.status = status;
        return this;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
