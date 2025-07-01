package interfaces;

import core.ScanMiddleware;
import repository.MasterRepository;

public interface BussinessInterface {
    public Object finder(ScanMiddleware scan, MasterRepository repository);
    public Object finderById(ScanMiddleware scan, MasterRepository repository);
    public Object createItem(ScanMiddleware scan, MasterRepository repository);
    public void update(ScanMiddleware scan, MasterRepository repository);
    public void create(ScanMiddleware scan, MasterRepository repository);
    public void delete(ScanMiddleware scan, MasterRepository repository);
    public void list(ScanMiddleware scan, MasterRepository repository);
    public void findOne(ScanMiddleware scan, MasterRepository repository);
    public void findOneById(ScanMiddleware scan, MasterRepository repository);
}
