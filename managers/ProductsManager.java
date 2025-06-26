package managers;

import core.ScanMiddleware;
import entities.Products;
import consts.ProductStatus;
import interfaces.CRUDInterface;
import repository.MasterRepository;

import java.util.ArrayList;

public class ProductsManager implements CRUDInterface {

    private static ProductsManager manager;
    private ArrayList<Products> products;

    public ProductsManager(){
        if(this.manager == null){
            this.products = new ArrayList<Products>();
        }
    }

    @Override
    public Object dataAccessById(ScanMiddleware scanMiddleware, MasterRepository repository){
        System.out.println("Insert the id of product");
        int id = scanMiddleware.writePositiveInt();
        return new Products(id, "", "", null, 0.0);
    }

    @Override
    public Object dataAccessObject(ScanMiddleware scanMiddleware, MasterRepository repository){
        System.out.println("Insert the next data to find product [id,name,brand,status,price]");
        int id = scanMiddleware.writePositiveInt();
        String name = scanMiddleware.writeString();
        String brand = scanMiddleware.writeString();
        scanMiddleware.writeString();
        ProductStatus status = scanMiddleware.writeProductStatus();
        double pricePerUnit = scanMiddleware.writeDouble();
        return new Products(id, name, brand, status, pricePerUnit);
    }

    @Override
    public Object dataAccessObjectAutoIncrement(ScanMiddleware scanMiddleware, MasterRepository repository){
        System.out.println("To create a product you need to insert ['name', 'brand', 'price']");
        String name = scanMiddleware.writeString();
        String brand = scanMiddleware.writeString();
        double pricePerUnit = scanMiddleware.writeDouble();
        return new Products(this.products.size()+1, name, brand, ProductStatus.ACTIVE, pricePerUnit);
    }

    @Override
    public ArrayList<Products> findAll() {
        return (ArrayList<Products>) this.products;
    }

    @Override
    public Object findOneBy(Object filter, MasterRepository repository) {
        Products p = (Products) filter;
        for(Products product : this.products){
            if(product.equals(p)){
                return product;
            }
        }
        return new Products();
    }

    @Override
    public void create(Object product, MasterRepository repository) {
        this.products.add((Products) product);
    }

    @Override
    public void delete(Object item, MasterRepository repository) {
        Products castProduct = (Products) item;
        this.products.removeIf(p -> p.equals(castProduct));
    }

    @Override
    public void patch(Object item, Object itemId, MasterRepository repository) {
        Products castedObject = (Products) item;
        for(Products p : this.products){
            if(p.equals(itemId)){
                p.setBrand(castedObject.getBrand());
                p.setName(castedObject.getName());
                p.setStatus(castedObject.getStatus());
                p.setPricePerUnit(castedObject.getPricePerUnit());
                break;
            }
        }
    }

    @Override
    public void put(Object item, Object itemId, MasterRepository repository) {
        Products castedObject = (Products) item;
        Products itemCasted = (Products) itemId;
        for (Products p : this.products){
            if(p.equals(itemId)){
                this.delete(p, repository);
                this.create(
                        new Products(
                                itemCasted.getId(),
                                castedObject.getName(),
                                castedObject.getBrand(),
                                castedObject.getStatus(),
                                castedObject.getPricePerUnit()
                        )
                        , repository
                );
                break;
            }
        }
    }
}
