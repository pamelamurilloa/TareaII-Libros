package MainStartFolder;

import InterfacesFolder.LoginFrame;
import LogicFolder.ArchiveManager;
import LogicFolder.UserModifier;

public class MainStart {

    private static UserModifier userModif = new UserModifier();
            
    public static void main(String[] args) {
        addAdministrator();
        
        LoginFrame mainFrame = new LoginFrame();
        mainFrame.setVisible(true);
    }
    
    public static void addAdministrator() {
        ArchiveManager archiveManager = new ArchiveManager();
        archiveManager.createFileUsers();
        userModif.addUser("01Admin", "password", "administrator", "0", "0");
        
    }    
    
}
