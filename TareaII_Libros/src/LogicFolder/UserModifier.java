package LogicFolder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author pamelamurillo
 */
public class UserModifier {
        
    
    ArchiveManager archiveManager = new ArchiveManager();

    
    public String[] getUserInfo(String userName) {
        String[] userData = new String[5];
        HashMap<String, HashMap> userHashMap = getUserFile();
        HashMap<String, String> subUser = userHashMap.get(userName);
        
        userData[0] = userName;
        userData[1] = subUser.get("type");
        userData[2] = subUser.get("password");
        userData[1] = subUser.get("booksBought");
        userData[2] = subUser.get("booksRented");
        
        
        return userData;
    }
    
    
    public HashMap<String, HashMap> getUserFile() {
        archiveManager.createFileUsers();
        ArrayList userList = archiveManager.readInFile("users");
        HashMap<String, HashMap> temporalHashMap = new HashMap<String, HashMap>();

        for (int line = 0; userList.size() > line; line++) {
            String newLine = userList.get(line).toString();
            String[] newLineArray = newLine.split(", ");
            
            HashMap<String, String> subHashMap = new HashMap<String, String>();
            subHashMap.put("type", newLineArray[1]);
            subHashMap.put("password", newLineArray[2]);
            subHashMap.put("booksBought", newLineArray[3]);
            subHashMap.put("booksRented", newLineArray[4]);
            
            temporalHashMap.put(newLineArray[0], subHashMap);
        }
        return temporalHashMap;
    }
    
    public boolean confirmIfUserExist(String userName) {
        boolean doesItExist = getUserFile().containsKey(userName);
        return doesItExist;
    }
    
    
    public boolean addUser(String userName, String password) {
        boolean userAdded = false;
        
        if (confirmIfUserExist(userName) == false) {
            archiveManager.createFileUsers();
            archiveManager.writeInFile("users", userName + ", user, " + password + "0, 0");
            userAdded = true;
        }
        
        return userAdded;
    }
    
    
    public void deleteBook(String userName){
        archiveManager.createFileUsers();
        ArrayList<String> userList = archiveManager.readInFile("users");

        String newUserList = "";
        
        for (int line = 0; userList.size() > line; line++) {
            
            String[] currentLine = userList.get(line).toString().split(", ");
            
            if ( !currentLine[0].equals(userName) ) {
                
                if (line == 0 && !userList.get(line).equals("")) {
                    newUserList = userList.get(line);
                } else {
                    newUserList = newUserList + "\n" + userList.get(line);
                }
                
            }
        }
        
        archiveManager.deleteAFile("users");
        archiveManager.createFileBooks();
        archiveManager.writeInFile("users", newUserList);

    }
    
    public void changeUser(String userName, String boughtOrRented) {
        
        String[] userInfo = getUserInfo(userName);

        if ( boughtOrRented.equals("bought") ) {
            userInfo[1] = userInfo[1] + 1;
        } else {
            userInfo[2] = userInfo[2] + 1;
        }

        deleteBook(userName);       // This two lines will
        addUser(userName, userInfo[2]);          // modify the file of users and update the new value
   

    }
    
}
