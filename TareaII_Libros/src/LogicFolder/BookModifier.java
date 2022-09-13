package LogicFolder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author pamelamurillo
 */
public class BookModifier {
    
    
    public void addBook(String[] bookData) { //bookData[0] = bookName, bookData[1] = timesBought, bookData[2] = timesRented
        String newLine = bookData[0] + ", " + bookData[1] + ", " + bookData[2];
        
        ArchiveManager archiveManager = new ArchiveManager();
        archiveManager.createFileBooks();
        archiveManager.writeInFile("books", newLine);
        
    }
    
    public HashMap<String, HashMap> getBookList() {
        HashMap<String, HashMap> bookHashMap = new HashMap<>();
        
        ArchiveManager archiveManager = new ArchiveManager();
        archiveManager.createFileBooks();
        ArrayList bookList = archiveManager.readInFile("books");
        
        for (int line = 0; bookList.size() > line; line++) {
            String[] bookData = bookList.get(line).toString().split(", ");
            HashMap<String, String> subBook = new HashMap<>();
            
            subBook.put( "timesBought", bookData[1] );
            subBook.put( "timesRented", bookData[2] );
            
            bookHashMap.put(bookData[0], subBook);
        }
        
        return bookHashMap;
        
    }
    
    
    public void deleteBook(String bookName){
        
    }
    
    
}
