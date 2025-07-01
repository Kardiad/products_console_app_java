package controllers;

import consts.ManagersNames;
import managers.LinesManager;
import repository.MasterRepository;
import core.ScanMiddleware;
import consts.AppStatus;
import interfaces.MenuStrategy;

public class LineMenuStrategy implements MenuStrategy {
    private boolean runnable;
    private final static String VALIDATE = ManagersNames.LINES.toString();

    public LineMenuStrategy(String strategy){
        this.runnable =  strategy.equalsIgnoreCase(VALIDATE);
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
        LinesManager linesManager = (LinesManager) masterRepository.getManager(VALIDATE);
        if(status == AppStatus.INSERT.getStatus()){
            masterRepository.setManager(VALIDATE, (LinesManager) masterRepository.create(linesManager, scanMiddleware));
            System.out.println(VALIDATE+" created!");
        }
        if(status == AppStatus.LIST.getStatus()){
            System.out.println("This is your "+VALIDATE+" list");
            masterRepository.list(linesManager);
        }
        if(status == AppStatus.FIND.getStatus()){
            System.out.println("Find your "+VALIDATE);
            masterRepository.showOne(linesManager, scanMiddleware);
        }
        if(status == AppStatus.DELETE.getStatus()){
            System.out.println("Delete your "+VALIDATE);
            masterRepository.setManager(VALIDATE, (LinesManager) masterRepository.deleteById(linesManager, scanMiddleware));
        }
        if(status == AppStatus.UPDATE.getStatus()){
            System.out.println("Update your "+VALIDATE);
            masterRepository.setManager(VALIDATE, (LinesManager) masterRepository.update(linesManager, scanMiddleware));
        }

    }
}
