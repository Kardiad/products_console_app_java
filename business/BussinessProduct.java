package business;

import consts.ManagersNames;
import consts.ProductStatus;
import core.ScanMiddleware;
import entities.Products;
import interfaces.BussinessInterface;
import managers.ProductsManager;
import repository.MasterRepository;

import java.util.ArrayList;

public class BussinessProduct implements BussinessInterface {

    //@TODO implements valueobjects in every params, to make easer everything

    @Override
    public Object finder(ScanMiddleware scan, MasterRepository repository) {
        System.out.println("Create your finder object, you need to write [id, name, brand, status, pricePerUnit]");
        int id = scan.writePositiveInt();
        String name = scan.writeString();
        String brand = scan.writeString();
        scan.writeString();
        ProductStatus status = scan.writeProductStatus();
        double pricePerUnit = scan.writeDouble();
        return new Products(id, name, brand, status, pricePerUnit);
    }

    @Override
    public Object finderById(ScanMiddleware scan, MasterRepository repository) {
        System.out.println("Insert the [id] of product");
        int id = scan.writePositiveInt();
        return new Products(id, "", "", ProductStatus.HIDDEN, 0);
    }

    @Override
    public Object createItem(ScanMiddleware scan, MasterRepository repository) {
        System.out.println("To create a product you need to insert ['name', 'brand', 'price']");
        String name = scan.writeString();
        String brand = scan.writeString();
        double pricePerUnit = scan.writeDouble();
        int lastId = ((ProductsManager)repository.getManager(ManagersNames.PRODUCTS.toString())).getLastId();
        return new Products(lastId, name, brand, ProductStatus.ACTIVE, pricePerUnit);
    }

    @Override
    public void update(ScanMiddleware scan, MasterRepository repository) {
        repository.setManager(ManagersNames.PRODUCTS.toString(), repository
                .getManager(ManagersNames.PRODUCTS.toString())
                .patch(
                        this.finder(scan, repository),
                        this.finderById(scan, repository),
                        repository
                ));
    }

    @Override
    public void create(ScanMiddleware scan, MasterRepository repository) {
        repository.setManager(ManagersNames.PRODUCTS.toString(), repository
                .getManager(ManagersNames.PRODUCTS.toString())
                .create(
                        this.createItem(scan, repository),
                        repository
                ));
    }

    @Override
    public void delete(ScanMiddleware scan, MasterRepository repository) {
        repository.setManager(ManagersNames.PRODUCTS.toString(), repository
                .getManager(ManagersNames.PRODUCTS.toString())
                .delete(
                        this.finderById(scan, repository),
                        repository
                ));
    }

    @Override
    public void list(ScanMiddleware scan, MasterRepository repository) {
        ArrayList<?> products = repository.getManager(ManagersNames.PRODUCTS.toString()).findAll();
        for(Object p : products){
            System.out.println((Products) p);
        }

    }

    @Override
    public void findOne(ScanMiddleware scan, MasterRepository repository) {
        Products p = (Products) repository
                .getManager(
                        ManagersNames.PRODUCTS.toString()
                )
                .findOneBy(
                        this.finder(scan,repository),
                        repository
                );
        System.out.println(p);
    }

    @Override
    public void findOneById(ScanMiddleware scan, MasterRepository repository) {
        Products p = (Products) repository
                .getManager(
                        ManagersNames.PRODUCTS.toString()
                )
                .findOneBy(
                        this.finderById(scan,repository),
                        repository
                );
        System.out.println(p);
    }
}
