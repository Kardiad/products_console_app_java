package managers;

import core.ScanMiddleware;
import consts.LinesStatus;
import consts.ManagersNames;
import entities.Lines;
import entities.Products;
import interfaces.CRUDInterface;
import repository.MasterRepository;

import java.util.ArrayList;

public class LinesManager implements CRUDInterface {

    private static LinesManager manager;
    private ArrayList<Lines> lines;

    public LinesManager(){
        if(manager == null){
            manager = new LinesManager();
            this.lines = new ArrayList<>();
        }
    }

    @Override
    public ArrayList<Lines> findAll() {
        return this.lines;
    }

    @Override
    public Object findOneBy(Object filter, MasterRepository repository) {
        Lines p = (Lines) filter;
        for(Lines lines : this.lines){
            if(lines.equals(p)){
                return lines;
            }
        }
        return null;
    }

    @Override
    public void create(Object product, MasterRepository repository) {
        this.lines.add((Lines) product);
    }

    @Override
    public void delete(Object item, MasterRepository repository) {
        Lines castProduct = (Lines) item;
        this.lines.removeIf(p -> p.equals(castProduct));
    }

    @Override
    public void patch(Object item, Object itemId, MasterRepository repository) {
        Lines castedObject = (Lines) item;
        for(Lines l : this.lines){
            if(l.equals(itemId)){
                l.setProducts(castedObject.getProducts());
                l.setStatus(castedObject.getStatus());
                l.setQuantity(castedObject.getQuantity());
                l.setTotalLinePrice(castedObject.getTotalLinePrice());
                break;
            }
        }
    }

    @Override
    public void put(Object item, Object itemId, MasterRepository repository) {
        Lines castedObject = (Lines) item;
        Lines itemCasted = (Lines) itemId;
        for (Lines l : this.lines){
            if(l.equals(itemId)){
                this.delete(l, repository);
                this.create(
                        new Lines(
                                itemCasted.getId(),
                                castedObject.getProducts(),
                                castedObject.getQuantity(),
                                castedObject.getTotalLinePrice(),
                                castedObject.getStatus()
                        )
                        , repository
                );
                break;
            }
        }
    }

    @Override
    public Object dataAccessById(ScanMiddleware scanMiddleware, MasterRepository repository){
        System.out.println("Insert the id of line");
        int id = scanMiddleware.writePositiveInt();
        return new Lines(id, new Products(), 0,  0.0, null);
    }

    @Override
    public Object dataAccessObject(ScanMiddleware scanMiddleware, MasterRepository repository){
        System.out.println("Insert the next data to find line [id,quantity,status]");
        int id = scanMiddleware.writePositiveInt();
        int quantity = scanMiddleware.writePositiveInt();
        LinesStatus status = scanMiddleware.writeLineStatus();
        System.out.println("If you want to search by product, you can write the data");
        Products filter = (Products) repository
                .getManager(ManagersNames.PRODUCTS.toString())
                .dataAccessObject(scanMiddleware, repository);
        Products products = (Products) repository
                .getManager(ManagersNames.PRODUCTS.toString())
                .findOneBy(filter, repository);
        return new Lines(id, products, quantity, products.getPricePerUnit() * quantity, status);
    }

    @Override
    public Object dataAccessObjectAutoIncrement(ScanMiddleware scanMiddleware, MasterRepository repository){
        System.out.println("To create a line you need to insert ['quantity', 'Product']");
        int quantity = scanMiddleware.writePositiveInt();
        Products filter = (Products) repository
                .getManager(ManagersNames.PRODUCTS.toString())
                .dataAccessObject(scanMiddleware, repository);
        Products products = (Products) repository
                .getManager(ManagersNames.PRODUCTS.toString())
                .findOneBy(filter, repository);
        return new Lines(this.lines.size()+1, products, quantity, products.getPricePerUnit() * quantity, LinesStatus.ACTIVE);
    }
}
