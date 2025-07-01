package business;

import consts.LinesStatus;
import consts.ManagersNames;
import core.ScanMiddleware;
import entities.Lines;
import entities.Products;
import interfaces.BussinessInterface;
import managers.LinesManager;
import managers.ProductsManager;
import repository.MasterRepository;

public class BussinessLines implements BussinessInterface {
    @Override
    public Object finder(ScanMiddleware scan, MasterRepository repository) {
        System.out.println("Insert the next data to find line [id,quantity,status]");
        int id = scan.writePositiveInt();
        int quantity = scan.writePositiveInt();
        LinesStatus status = scan.writeLineStatus();
        System.out.println("If you want to search by product, you can write the data");
        Products filter = (Products) repository
                .getManager(ManagersNames.PRODUCTS.toString())
                .dataAccessObject(scan, repository);
        Products products = (Products) repository
                .getManager(ManagersNames.PRODUCTS.toString())
                .findOneBy(filter, repository);
        return new Lines(id, products, quantity, products.getPricePerUnit() * quantity, status);
    }

    @Override
    public Object finderById(ScanMiddleware scan, MasterRepository repository) {
        System.out.println("Insert the id of line");
        int id = scan.writePositiveInt();
        return new Lines(id, new Products(), 0,  0.0, null);
    }

    @Override
    public Object createItem(ScanMiddleware scan, MasterRepository repository) {
        System.out.println("To create a line you need to insert ['quantity', 'Product']");
        int quantity = scan.writePositiveInt();
        Products filter = (Products) repository
                .getManager(ManagersNames.PRODUCTS.toString())
                .dataAccessObject(scan, repository);
        Products products = (Products) repository
                .getManager(ManagersNames.PRODUCTS.toString())
                .findOneBy(filter, repository);
        int lastId = ((LinesManager)repository.getManager(ManagersNames.LINES.toString())).getLastId();
        return new Lines(lastId, products, quantity, products.getPricePerUnit() * quantity, LinesStatus.ACTIVE);
    }
}
