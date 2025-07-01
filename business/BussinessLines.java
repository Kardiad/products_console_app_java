package business;

import consts.LinesStatus;
import consts.ManagersNames;
import core.ScanMiddleware;
import entities.Lines;
import entities.Products;
import interfaces.BussinessInterface;
import managers.LinesManager;
import repository.MasterRepository;

import java.util.ArrayList;

public class BussinessLines implements BussinessInterface {

    private BussinessProduct bussinessProduct;

    public BussinessLines(){
        this.bussinessProduct = new BussinessProduct();
    }
    @Override
    public Object finder(ScanMiddleware scan, MasterRepository repository) {
        System.out.println("Insert the next data to find line [id,quantity,status]");
        int id = scan.writePositiveInt();
        int quantity = scan.writePositiveInt();
        LinesStatus status = scan.writeLineStatus();
        System.out.println("If you want to search by product, you can write the data");
        Products products = (Products) repository
                .getManager(
                        ManagersNames.PRODUCTS.toString()
                )
                .findOneBy(
                        (Products) this.bussinessProduct.finder(scan, repository),
                        repository
                );
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
        Products products = (Products) repository
                .getManager(ManagersNames.PRODUCTS.toString())
                .findOneBy(
                        (Products) this.bussinessProduct.finder(scan, repository),
                        repository
                );
        int lastId = ((LinesManager)repository.getManager(ManagersNames.LINES.toString())).getLastId();
        return new Lines(lastId, products, quantity, products.getPricePerUnit() * quantity, LinesStatus.ACTIVE);
    }

    @Override
    public void update(ScanMiddleware scan, MasterRepository repository) {
        repository.setManager(ManagersNames.LINES.toString(), repository
                .getManager(ManagersNames.LINES.toString())
                .patch(
                        this.finder(scan, repository),
                        this.finderById(scan, repository),
                        repository
                ));
    }

    @Override
    public void create(ScanMiddleware scan, MasterRepository repository) {
        repository.setManager(ManagersNames.LINES.toString(), repository
                .getManager(ManagersNames.LINES.toString())
                .create(
                        this.createItem(scan, repository),
                        repository
                ));
    }

    @Override
    public void delete(ScanMiddleware scan, MasterRepository repository) {
        repository.setManager(ManagersNames.LINES.toString(), repository
                .getManager(ManagersNames.LINES.toString())
                .delete(
                        this.finderById(scan, repository),
                        repository
                ));
    }

    @Override
    public void list(ScanMiddleware scan, MasterRepository repository) {
        ArrayList<?> products = repository.getManager(ManagersNames.LINES.toString()).findAll();
        for(Object p : products){
            System.out.println((Lines) p);
        }
    }

    @Override
    public void findOne(ScanMiddleware scan, MasterRepository repository) {
        Lines l = (Lines) repository
                .getManager(
                        ManagersNames.LINES.toString()
                )
                .findOneBy(
                        this.finder(scan,repository),
                        repository
                );
        System.out.println(l);
    }

    @Override
    public void findOneById(ScanMiddleware scan, MasterRepository repository) {
        Lines l = (Lines) repository
                .getManager(
                        ManagersNames.LINES.toString()
                )
                .findOneBy(
                        this.finderById(scan,repository),
                        repository
                );
        System.out.println(l);
    }
}
