package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Book;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;

public class LibraryTest {
    @Test
    public void createJsonTest() throws Exception {
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960);
        Book[] books = {book1, book2};
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBooks = objectMapper.writeValueAsString(books);
        File file = new File("src/test/resources/books.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(jsonBooks);
        fileWriter.close();
    }
}