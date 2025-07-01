package controllers;

import business.BussinessLines;
import consts.ManagersNames;
import repository.MasterRepository;
import core.ScanMiddleware;
import consts.AppStatus;
import interfaces.MenuStrategy;

public class LineMenuStrategy implements MenuStrategy {
    private boolean runnable;
    private final static String VALIDATE = ManagersNames.LINES.toString();
    private final BussinessLines bussinessLines;

    public LineMenuStrategy(String strategy){

        this.runnable =  strategy.equalsIgnoreCase(VALIDATE);
        this.bussinessLines = new BussinessLines();
    }

    @Override
    public void run(ScanMiddleware scan, MasterRepository repository) {
        if(this.runnable){
            int status;
            do{
                this.welcome();
                status = scan.writeStatus();
                this.actionProducts(status, scan, repository);
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
            this.bussinessLines.create(scanMiddleware, masterRepository);
            System.out.println(VALIDATE+" created!");
        }
        if(status == AppStatus.LIST.getStatus()){
            System.out.println("This is your "+VALIDATE+" list");
            this.bussinessLines.list(scanMiddleware, masterRepository);
        }
        if(status == AppStatus.FIND.getStatus()){
            System.out.println("Find your "+VALIDATE);
            this.bussinessLines.findOne(scanMiddleware, masterRepository);
        }
        if(status == AppStatus.DELETE.getStatus()){
            System.out.println("Delete your "+VALIDATE);
            this.bussinessLines.delete(scanMiddleware, masterRepository);
        }
        if(status == AppStatus.UPDATE.getStatus()){
            System.out.println("Update your "+VALIDATE);
            this.bussinessLines.update(scanMiddleware, masterRepository);
        }

    }
}
