package LogicFolder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pamelamurillo
 */

public class ArchiveManager {
    File usersFile;
    File booksFile;
    
    public void createFileBooks(){
        booksFile = new File("books.txt");
    }
    
    public void createFileUsers(){
        usersFile = new File("users.txt");
    }
    
    
    public void writeInFile(String fileString, String textToWrite) { //This can get "users" or "books", and the text you wanna write in it
        File nameFile = booksFile;      //The automatic file creaded is the booksFile
        if ("users".equals(fileString)) {    //Unless it is specified the contrary
            nameFile = usersFile;
        }
        
        try {
            
            FileWriter writer;
            writer = new FileWriter(nameFile, true);

            BufferedWriter bufferWriter = new BufferedWriter(writer);

            bufferWriter.write(textToWrite);        //writes the text
            bufferWriter.newLine();                 //Goes to the next line
            bufferWriter.close();                   //closes the file

        } catch (IOException ex) {
            Logger.getLogger(ArchiveManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public ArrayList readInFile(String fileString){ //This can get "users" or "books"
        File nameFile = booksFile;      //The automatic file creaded is the booksFile
        if ("users".equals(fileString)) {    //Unless it is specified the contrary
            nameFile = usersFile;
        }
        
        ArrayList infoFile = new ArrayList();
        String line;
        
        try {
            FileReader reader = new FileReader(nameFile);
            BufferedReader bufferReader = new BufferedReader(reader);

            while ( (line = bufferReader.readLine()) != null ) {
                infoFile.add(line);
            }
            
            bufferReader.close();
        
        } catch (IOException ex) {
            Logger.getLogger(ArchiveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return infoFile;
    }
    
}
