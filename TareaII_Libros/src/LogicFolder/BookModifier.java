package LogicFolder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author pamelamurillo
 */
public class BookModifier {
    
    
    public boolean doesItExist(String bookName) {
        return getBookList().containsKey(bookName);
    }
    
    
    public boolean addBook(String[] bookData) { //bookData[0] = bookName, bookData[1] = timesBought, bookData[2] = timesRented
        boolean bookAdded = false;
        if ( doesItExist(bookData[0]) == false ) {
            String newLine = bookData[0] + ", " + bookData[1] + ", " + bookData[2];
        
            ArchiveManager archiveManager = new ArchiveManager();
            archiveManager.createFileBooks();
            archiveManager.writeInFile("books", newLine);
            
            bookAdded = true;
        }

        return bookAdded;
    }
    
    
    public boolean deleteBook(String bookName){
        HashMap<String, HashMap> bookHashMap = getBookList();
        boolean bookDeleted = false;
        
        if ( doesItExist(bookName) == true ) {
            bookHashMap.remove(bookName);
            bookDeleted = true;
        }
        
        return bookDeleted;
    }

    
    public String[] getBookInfo(String bookName) {
        String[] bookData = new String[3];
        HashMap<String, HashMap> bookHashMap = getBookList();
        HashMap<String, String> subBook = bookHashMap.get(bookName);
        
        bookData[0] = bookName;
        bookData[1] = subBook.get("timesBought");
        bookData[2] = subBook.get("timesRented");
        
        return bookData;
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
    
}
