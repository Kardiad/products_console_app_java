package managers;

import core.ScanMiddleware;
import interfaces.CRUDInterface;
import repository.MasterRepository;

import java.util.ArrayList;

public class InvoiceManager implements CRUDInterface {
    @Override
    public ArrayList<?> findAll() {
        return null;
    }

    @Override
    public Object findOneBy(Object filter, MasterRepository repository) {
        return null;
    }

    @Override
    public void create(Object item, MasterRepository repository) {

    }

    @Override
    public void delete(Object item, MasterRepository repository) {

    }

    @Override
    public void patch(Object item, Object itemId, MasterRepository repository) {

    }

    @Override
    public void put(Object item, Object itemId, MasterRepository repository) {

    }

    @Override
    public Object dataAccessObject(ScanMiddleware scanMiddleware, MasterRepository repository) {
        return null;
    }

    @Override
    public Object dataAccessObjectAutoIncrement(ScanMiddleware scanMiddleware, MasterRepository repository) {
        return null;
    }

    @Override
    public Object dataAccessById(ScanMiddleware scanMiddleware, MasterRepository repository) {
        return null;
    }
}
