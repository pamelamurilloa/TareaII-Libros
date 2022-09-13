package LogicFolder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author pamelamurillo
 */
public class BookModifier {
    
    ArchiveManager archiveManager = new ArchiveManager();
    
    
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
        
        archiveManager.createFileBooks();
        ArrayList bookList = archiveManager.readInFile("books");
        
        for (int line = 0; bookList.size() > line; line++) {
            if ( !bookList.get(line).toString().equals("") ) {
                String[] bookData = bookList.get(line).toString().split(", ");
                HashMap<String, String> subBook = new HashMap<>();

                subBook.put( "timesBought", bookData[1] );
                subBook.put( "timesRented", bookData[2] );

                bookHashMap.put(bookData[0], subBook);
            }
        }
        
        return bookHashMap;
        
    }
    
    public boolean doesItExist(String bookName) {
        return getBookList().containsKey(bookName);
    }
    
    
    public boolean addBook(String[] bookData) { //bookData[0] = bookName, bookData[1] = timesBought, bookData[2] = timesRented
        boolean bookAdded = false;
        if ( doesItExist(bookData[0]) == false ) {
            String newLine = bookData[0] + ", " + bookData[1] + ", " + bookData[2];
        
            archiveManager.createFileBooks();
            archiveManager.writeInFile("books", newLine);
            
            bookAdded = true;
        }

        return bookAdded;
    }
    
    
    public void deleteBook(String bookName){
        archiveManager.createFileBooks();
        ArrayList<String> bookList = archiveManager.readInFile("books");

        String newBookList = "";
        
        for (int line = 0; bookList.size() > line; line++) {
            String[] currentLine = bookList.get(line).toString().split(", ");
            if ( !currentLine[0].equals(bookName) ) {
                if (line == 0 && !bookList.get(line).equals("")) {
                    newBookList = bookList.get(line);
                } else {
                    newBookList = newBookList + "\n" + bookList.get(line);
                }
            }
        }
        
        archiveManager.deleteAFile("books");
        archiveManager.createFileBooks();
        archiveManager.writeInFile("books", newBookList);

    }
    
    public boolean changeBook(String bookName, String boughtOrRented) {
        boolean bookChanged = false;
        
        if ( doesItExist(bookName) ) {
            String[] bookInfo = getBookInfo(bookName);

            if ( boughtOrRented.equals("bought") ) {
                bookInfo[1] = "" + ( Integer.parseInt(bookInfo[1] ) + 1);
            } else {
                bookInfo[2] = "" + ( Integer.parseInt(bookInfo[2] ) + 1);
            }
            
            deleteBook(bookName);       // This two lines will
            addBook(bookInfo);          // modify the file of books and update the new value
            
            bookChanged = true;    
            
        }
        
        return bookChanged;
    }
    
}
