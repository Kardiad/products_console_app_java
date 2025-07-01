package interfaces;

import core.ScanMiddleware;
import repository.MasterRepository;

public interface BussinessInterface {
    public Object finder(ScanMiddleware scan, MasterRepository repository);
    public Object finderById(ScanMiddleware scan, MasterRepository repository);
    public Object createItem(ScanMiddleware scan, MasterRepository repository);
}
