package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Book;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BooksTest {
    @Test
    public void JsonContainsObjectsTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/test/resources/books.json");
        Book[] books = objectMapper.readValue(file, Book[].class);
        assertNotNull(file);
        assertEquals(2, books.length);
        assertEquals("The Great Gatsby", books[0].getTitle());
        assertEquals("To Kill a Mockingbird", books[1].getTitle());

    }
}

