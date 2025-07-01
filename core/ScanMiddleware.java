package core;

import consts.AppStatus;
import consts.LinesStatus;
import consts.ProductStatus;

import java.util.Scanner;

public class ScanMiddleware {

    private final Scanner scanner;

    public ScanMiddleware(){
        this.scanner = new Scanner(System.in);
    }

    public int writePositiveInt(){
        try{
            int number = this.scanner.nextInt();
            return Math.max(number, 0);
        }catch (Exception e){
            return 0;
        }
    }

    public String writeString(){
        return this.scanner.nextLine();
    }

    public double writeDouble(){
        try{
            return this.scanner.nextDouble();
        } catch (Exception e) {
            return 0.0;
        }
    }

    public ProductStatus writeProductStatus(){
        String status = this.scanner.nextLine();
        if(status.equals(ProductStatus.HIDDEN.toString())){
            return ProductStatus.HIDDEN;
        }
        if(status.equals(ProductStatus.ACTIVE.toString())){
            return ProductStatus.ACTIVE;
        }
        if(status.equals(ProductStatus.UPDATED.toString())){
            return ProductStatus.UPDATED;
        }
        if(status.equals(ProductStatus.IN_PROGRESS.toString())){
            return ProductStatus.IN_PROGRESS;
        }
        return ProductStatus.ACTIVE;
    }

    public int writeStatus(){
        int status = this.scanner.nextInt();
        if(AppStatus.EXIT.getStatus() == status){
            return AppStatus.EXIT.getStatus();
        }
        if(AppStatus.DELETE.getStatus() == status){
            return AppStatus.DELETE.getStatus();
        }
        if(AppStatus.INSERT.getStatus() == status){
            return AppStatus.INSERT.getStatus();
        }
        if(AppStatus.LIST.getStatus() == status){
            return AppStatus.LIST.getStatus();
        }
        if(AppStatus.UPDATE.getStatus() == status){
            return AppStatus.UPDATE.getStatus();
        }
        if(AppStatus.START.getStatus() == status){
            return AppStatus.START.getStatus();
        }
        if(AppStatus.FIND.getStatus() == status){
            return AppStatus.FIND.getStatus();
        }
        return AppStatus.RETRY.getStatus();
    }

    public LinesStatus writeLineStatus(){
        int status = this.scanner.nextInt();
        if(LinesStatus.ACTIVE.getStatus() == status){
            return LinesStatus.ACTIVE;
        }
        if(LinesStatus.UPDATED.getStatus() == status){
            return LinesStatus.UPDATED;
        }
        if(LinesStatus.HIDDEN.getStatus() == status){
            return LinesStatus.HIDDEN;
        }
        return LinesStatus.INACTIVE;
    }
}
