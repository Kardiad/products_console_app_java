package interfaces;

import core.ScanMiddleware;
import repository.MasterRepository;

import java.util.ArrayList;

public interface CRUDInterface {
    public ArrayList<?> findAll();
    public Object findOneBy(Object filter, MasterRepository repository);
    public CRUDInterface create(Object item, MasterRepository repository);
    public CRUDInterface delete(Object item, MasterRepository repository);
    public CRUDInterface patch(Object item, Object itemId, MasterRepository repository);
    public CRUDInterface put(Object item, Object itemId, MasterRepository repository);
    public Object dataAccessObject(ScanMiddleware scanMiddleware, MasterRepository repository);
    public Object dataAccessObjectAutoIncrement(ScanMiddleware scanMiddleware, MasterRepository repository);
    public Object dataAccessById(ScanMiddleware scanMiddleware, MasterRepository repository);
}
