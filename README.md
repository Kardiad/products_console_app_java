# About the app
This app is a personal proyect where I'm training in Java languaje and desing patterns. The app is built with the next desing patterns:

- Strategy: To make menus and submenus they are in:
    - core/MainMenu.java: This is the runner file which have every controllers in run method.
    - controllers/*.java: This is the specific controller, or submenu, using master repository to do everything.
    - business/*.java: This is the bussiness logic, now it is empty because i'm thinking how I have to do this. An idea that i'm thinking is make methods to every actions, for example: if you update a item, make a:
        `(new BussinessProduct).updateBussinessLogic(scannMiddleware, masterRepository);`
    
- Repository: To make an uniform way to update, list, insert and delete objects
    - repository/MasterRepository.java: this repository have the manager of every entities in the program, abstracted to every manager.
    - managers/*.java: this folder will have every manager of the app, in this moment we are saving data runtime to make test, finaly my idea is save it in a database using JDBC. Managers have the next function, manage the data to do this we need a core class ScanMiddleware, this class make a adapted Scanner to input data in the application, for this reason is in core package.