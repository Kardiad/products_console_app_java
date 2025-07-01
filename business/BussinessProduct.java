package business;

import consts.ManagersNames;
import consts.ProductStatus;
import core.ScanMiddleware;
import entities.Products;
import interfaces.BussinessInterface;
import managers.ProductsManager;
import repository.MasterRepository;

public class BussinessProduct implements BussinessInterface {


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
        System.out.println("Insert id to find product");
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
}
