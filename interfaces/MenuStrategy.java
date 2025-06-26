package interfaces;

import repository.MasterRepository;
import core.ScanMiddleware;

public interface MenuStrategy {
    public void run(ScanMiddleware scan, MasterRepository repository);
}
