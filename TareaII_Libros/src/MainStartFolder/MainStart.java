package MainStartFolder;

import InterfacesFolder.LoginFrame;
import LogicFolder.ArchiveManager;

public class MainStart {

    public static void main(String[] args) {
        addAdministrator();
        
        LoginFrame mainFrame = new LoginFrame();
        mainFrame.setVisible(true);
    }
    
    public static void addAdministrator() {
        ArchiveManager archiveManager = new ArchiveManager();
        archiveManager.createFileUsers();
        archiveManager.writeInFile("users", "01Admin, administrator, password"); //The 1st is the file (users/books), the 2nd is the information that will be written
        
    }    
    
}
