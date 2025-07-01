package managers;

import business.BussinessLines;
import core.ScanMiddleware;
import consts.LinesStatus;
import consts.ManagersNames;
import entities.Lines;
import entities.Products;
import interfaces.CRUDInterface;
import repository.MasterRepository;

import java.util.ArrayList;

public class LinesManager implements CRUDInterface {

    private final ArrayList<Lines> lines;
    private final BussinessLines bussinessLines;

    public LinesManager(){
        this.lines = new ArrayList<>();
        this.bussinessLines = new BussinessLines();
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
        return this.bussinessLines.finderById(scanMiddleware, repository);
    }

    @Override
    public Object dataAccessObject(ScanMiddleware scanMiddleware, MasterRepository repository){
       return this.bussinessLines.finder(scanMiddleware, repository);
    }

    @Override
    public Object dataAccessObjectAutoIncrement(ScanMiddleware scanMiddleware, MasterRepository repository){
       return this.bussinessLines.createItem(scanMiddleware, repository);
    }

    public int getLastId(){
        return this.lines.size()+1;
    }
}
