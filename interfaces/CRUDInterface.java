package interfaces;

import core.ScanMiddleware;
import repository.MasterRepository;

import java.util.ArrayList;

public interface CRUDInterface {
    public ArrayList<?> findAll();
    public Object findOneBy(Object filter, MasterRepository repository);
    public void create(Object item, MasterRepository repository);
    public void delete(Object item, MasterRepository repository);
    public void patch(Object item, Object itemId, MasterRepository repository);
    public void put(Object item, Object itemId, MasterRepository repository);
    public Object dataAccessObject(ScanMiddleware scanMiddleware, MasterRepository repository);
    public Object dataAccessObjectAutoIncrement(ScanMiddleware scanMiddleware, MasterRepository repository);
    public Object dataAccessById(ScanMiddleware scanMiddleware, MasterRepository repository);
    //TODO we need to create: an assignate method, delete asignation, update asignation and find asignation
}
