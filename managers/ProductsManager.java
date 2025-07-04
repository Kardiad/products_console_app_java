package managers;

import business.BussinessProduct;
import core.ScanMiddleware;
import entities.Products;
import interfaces.CRUDInterface;
import repository.MasterRepository;

import java.util.ArrayList;

public class ProductsManager implements CRUDInterface {

    private final ArrayList<Products>  products;
    private final BussinessProduct bussinessProduct;

    public ProductsManager(){
        this.products = new ArrayList<>();
        this.bussinessProduct = new BussinessProduct();
    }

    @Override
    public Object dataAccessById(ScanMiddleware scanMiddleware, MasterRepository repository){
        return bussinessProduct.finderById(scanMiddleware, repository);
    }

    @Override
    public Object dataAccessObject(ScanMiddleware scanMiddleware, MasterRepository repository){
        return bussinessProduct.finder(scanMiddleware, repository);
    }

    @Override
    public Object dataAccessObjectAutoIncrement(ScanMiddleware scanMiddleware, MasterRepository repository){
        return this.bussinessProduct.createItem(scanMiddleware, repository);
    }

    @Override
    public ArrayList<Products> findAll() {
        return this.products;
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
    public CRUDInterface create(Object product, MasterRepository repository) {
        this.products.add((Products) product);
        return this;
    }

    @Override
    public CRUDInterface delete(Object item, MasterRepository repository) {
        Products castProduct = (Products) item;
        this.products.removeIf(p -> p.equals(castProduct));
        return this;
    }

    @Override
    public CRUDInterface patch(Object item, Object itemId, MasterRepository repository) {
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
        return this;
    }

    @Override
    public CRUDInterface put(Object item, Object itemId, MasterRepository repository) {
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
        return this;
    }

    public int getLastId(){
        return this.products.size()+1;
    }
}
