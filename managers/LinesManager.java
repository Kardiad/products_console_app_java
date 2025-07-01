package managers;

import business.BussinessLines;
import core.ScanMiddleware;
import entities.Lines;
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
    public CRUDInterface create(Object product, MasterRepository repository) {
        this.lines.add((Lines) product);
        return this;
    }

    @Override
    public CRUDInterface delete(Object item, MasterRepository repository) {
        Lines castProduct = (Lines) item;
        this.lines.removeIf(p -> p.equals(castProduct));
        return this;
    }

    @Override
    public CRUDInterface patch(Object item, Object itemId, MasterRepository repository) {
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
        return this;
    }

    @Override
    public CRUDInterface put(Object item, Object itemId, MasterRepository repository) {
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
        return this;
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
