package core;

import consts.ManagersNames;
import controllers.LineMenuStrategy;
import controllers.ProductMenuStrategy;
import repository.MasterRepository;

public class StrategyMenuResolver {
    private final String strategy;
    
    public StrategyMenuResolver(String strategy){
        this.strategy = strategy;
    }
    public void run(ScanMiddleware scan , MasterRepository action){
        if(this.strategy.equalsIgnoreCase(ManagersNames.PRODUCTS.toString())){
            (new ProductMenuStrategy(this.strategy)).run(scan, action);
        }
        if(this.strategy.equalsIgnoreCase(ManagersNames.LINES.toString())){
            (new LineMenuStrategy(this.strategy)).run(scan, action);
        }
        if(this.strategy.equalsIgnoreCase(ManagersNames.INVOICES.toString())){
            System.out.println("Option not implemented! coming soon");
            //(new LineMenuStrategy(this.strategy)).run(scan, action);
        }
    }
}
