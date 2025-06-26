package core;

import controllers.LineMenuStrategy;
import controllers.ProductMenuStrategy;
import repository.MasterRepository;
import consts.AppStatus;

public class MenuClass {

    private ScanMiddleware scanMiddleware;
    private MasterRepository actions;
    private static final String YES = "y";

    public MenuClass() {
        this.scanMiddleware = new ScanMiddleware();
        this.actions = new MasterRepository();
    }

    public void run(){

        int selectedStatus = AppStatus.START.getStatus();
        String strategy, yesOrNot;
        do{
            this.welcome();
            strategy = this.scanMiddleware.writeString();
            //this one calls to one controller waiting a AppStatus command strategy is like /strategy/2 -> create product
            new ProductMenuStrategy(strategy).run(this.scanMiddleware, this.actions);
            new LineMenuStrategy(strategy).run(this.scanMiddleware, this.actions);
            System.out.println("Do you want to do something?(y/n)");
            yesOrNot = this.scanMiddleware.writeString();
            selectedStatus = (yesOrNot.equalsIgnoreCase(YES))?AppStatus.RETRY.getStatus():AppStatus.EXIT.getStatus();
        }while (selectedStatus != AppStatus.EXIT.getStatus());
        this.exit();
    }

    private void exit(){
        System.out.println("Thank you, to use our database!");
    }

    private void welcome(){
        System.out.println("Hello! we are making invoices please select your option");
        System.out.println("Write: [product] to make some products");
        System.out.println("Write: [lines] to make a product line of your invoice, remember is required to have some products");
        System.out.println("Write: [invoices] to make a invoice, remember is required to have some lines to make some invoices");
    }
}
