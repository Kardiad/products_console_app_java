package repository;

import core.ScanMiddleware;
import consts.ManagersNames;
import interfaces.CRUDInterface;
import managers.InvoiceManager;
import managers.LinesManager;
import managers.ProductsManager;

public class MasterRepository {

    private LinesManager linesManager;
    private ProductsManager productsManager;
    private InvoiceManager invoiceManager;

    public MasterRepository(){
        this.linesManager = new LinesManager();
        this.productsManager = new ProductsManager();
        this.invoiceManager = new InvoiceManager();
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
        if(manager.equalsIgnoreCase(ManagersNames.PRODUCTS.toString())){
            return this.productsManager;
        }
        if(manager.equalsIgnoreCase(ManagersNames.LINES.toString())){
            return this.linesManager;
        }
        if(manager.equalsIgnoreCase(ManagersNames.INVOICES.toString())){
            return this.invoiceManager;
        }
        return null;
    }
    public void setManager(String managerName, CRUDInterface manager){
        if(managerName.equalsIgnoreCase(ManagersNames.PRODUCTS.toString())){
            this.productsManager = (ProductsManager) manager;
        }
        if(managerName.equalsIgnoreCase(ManagersNames.LINES.toString())){
            this.linesManager = (LinesManager) manager;
        }
        if(managerName.equalsIgnoreCase(ManagersNames.INVOICES.toString())){
            this.invoiceManager = (InvoiceManager) manager;
        }
    }
}
