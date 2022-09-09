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
    File userFile;
    File booksFile;
    
    public void createFileHangedRecords(String fileString) throws IOException {
        File nameFile = booksFile;      //The automatic file creaded is the booksFile
        if ("users".equals(fileString)) {    //Unless it is specified the contrary
            nameFile = userFile;
        }
        
        nameFile = new File(nameFile + ".txt");
    }
    
    
    public void writeInFile(String fileString, String textToWrite) {
        File nameFile = booksFile;      //The automatic file creaded is the booksFile
        if ("users".equals(fileString)) {    //Unless it is specified the contrary
            nameFile = userFile;
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
    
    public ArrayList readInFile(String fileString){
        File nameFile = booksFile;      //The automatic file creaded is the booksFile
        if ("users".equals(fileString)) {    //Unless it is specified the contrary
            nameFile = userFile;
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
