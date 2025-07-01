package controllers;

import business.BussinessProduct;
import consts.ManagersNames;
import managers.ProductsManager;
import repository.MasterRepository;
import core.ScanMiddleware;
import consts.AppStatus;
import interfaces.MenuStrategy;

public class ProductMenuStrategy implements MenuStrategy {

    private boolean runnable;
    private final static String VALIDATE = ManagersNames.PRODUCTS.toString();
    private final BussinessProduct bussinessProduct;

    public ProductMenuStrategy(String strategy){

        this.runnable = strategy.equalsIgnoreCase(VALIDATE);
        this.bussinessProduct = new BussinessProduct();
    }

    @Override
    public void run(ScanMiddleware scanMiddleware, MasterRepository masterRepository) {
        if(this.runnable){
            int status;
            do{
                this.welcome();
                status = scanMiddleware.writeStatus();
                this.actionProducts(status, scanMiddleware, masterRepository);
            }while (status != AppStatus.EXIT.getStatus());
        }
    }

    //now we put contents of menu class here.
    private void welcome(){
        System.out.println("Welcome our database of products, please select option");
        System.out.println("[2: To create item]");
        System.out.println("[3: to delete item]");
        System.out.println("[4: to update item]");
        System.out.println("[5: to list items]");
        System.out.println("[6: to find items]");
        System.out.println("[999: to find exit]");
    }

    private void actionProducts(int status, ScanMiddleware scanMiddleware, MasterRepository masterRepository){
        scanMiddleware.writeString(); // this one is for clean the buffer
        if(status == AppStatus.INSERT.getStatus()){
            this.bussinessProduct.create(scanMiddleware, masterRepository);
            System.out.println(VALIDATE+" created!");
        }
        if(status == AppStatus.LIST.getStatus()){
            System.out.println("This is your "+VALIDATE+" list");
            this.bussinessProduct.list(scanMiddleware, masterRepository);
        }
        if(status == AppStatus.FIND.getStatus()){
            System.out.println("Find your "+VALIDATE);
            this.bussinessProduct.findOne(scanMiddleware, masterRepository);
        }
        if(status == AppStatus.DELETE.getStatus()){
            System.out.println("Delete your "+VALIDATE);
            this.bussinessProduct.delete(scanMiddleware, masterRepository);
        }
        if(status == AppStatus.UPDATE.getStatus()){
            System.out.println("Update your "+VALIDATE);
            this.bussinessProduct.update(scanMiddleware, masterRepository);
        }
    }
}
