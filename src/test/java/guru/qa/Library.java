package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960);
        Book[] books = {book1, book2};

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonBooks = objectMapper.writeValueAsString(books);
            File file = new File("src/test/resources/books.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonBooks);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJsonContainsObjects() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("src/test/resources/books.json");
            Book[] books = objectMapper.readValue(file, Book[].class);
            assertEquals(2, books.length); // Проверяем, что есть две книги
            assertEquals("The Great Gatsby", books[0].getTitle());
            assertEquals("To Kill a Mockingbird", books[1].getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}