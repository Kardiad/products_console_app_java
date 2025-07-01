package repository;

import core.ScanMiddleware;
import consts.ManagersNames;
import interfaces.CRUDInterface;
import managers.InvoiceManager;
import managers.LinesManager;
import managers.ProductsManager;
import java.util.HashMap;

public class MasterRepository {

    private final HashMap<String, CRUDInterface> managers;
    private final String[] repositories = {
            ManagersNames.PRODUCTS.toString(),
            ManagersNames.LINES.toString(),
            ManagersNames.INVOICES.toString()
    };

    public MasterRepository(){
        managers = new HashMap<>();
        this.managers.put(this.repositories[0], new ProductsManager());
        this.managers.put(this.repositories[1], new LinesManager());
        this.managers.put(this.repositories[2], new InvoiceManager());
    }

    private Object createIdProduct(CRUDInterface manager, ScanMiddleware scanMiddleware){
        return manager.dataAccessById(scanMiddleware, this);
    }

    private Object createProduct(CRUDInterface manager, ScanMiddleware scanMiddleware){
        return manager.dataAccessObject(scanMiddleware, this);
    }

    public CRUDInterface create(CRUDInterface manager, ScanMiddleware scanMiddleware){
        manager.create(manager.dataAccessObjectAutoIncrement(scanMiddleware, this), this);
        return manager;
    }

    public void list(CRUDInterface manager){
        for(Object item : manager.findAll()){
            System.out.println(item);
        }
    }

    public void showOne(CRUDInterface manager, ScanMiddleware scanMiddleware){
        System.out.println(manager.findOneBy(this.createProduct(manager, scanMiddleware), this));
    }

    public CRUDInterface deleteById(CRUDInterface manager, ScanMiddleware scanMiddleware){
        manager.delete(this.createIdProduct(manager, scanMiddleware), this);
        return manager;
    }

    public CRUDInterface update(CRUDInterface manager, ScanMiddleware scanMiddleware){
        Object product = manager.dataAccessObject(scanMiddleware, this);
        Object productId = manager.dataAccessObject(scanMiddleware, this);
        manager.patch(product, productId,this);
        return manager;
    }

    public CRUDInterface getManager(String manager){
        for(String singleManager: this.repositories){
            if(singleManager.equalsIgnoreCase(manager)){
                return this.managers.get(manager);
            }
        }
        return null;
    }
    public void setManager(String managerName, CRUDInterface manager){
        for(String singleManager: this.repositories){
            if(singleManager.equalsIgnoreCase(managerName)){
                this.managers.put(managerName, manager);
            }
        }
    }
}
