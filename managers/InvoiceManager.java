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
    public CRUDInterface create(Object item, MasterRepository repository) {
        return null;
    }

    @Override
    public CRUDInterface delete(Object item, MasterRepository repository) {
        return null;
    }

    @Override
    public CRUDInterface patch(Object item, Object itemId, MasterRepository repository) {
        return null;
    }

    @Override
    public CRUDInterface put(Object item, Object itemId, MasterRepository repository) {
        return null;
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
