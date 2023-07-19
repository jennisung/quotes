package quotes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuotesTest {

    @Test
    void testGetText() {
        String expectedText = "sample quote text";
        Quotes.Quote quote = new Quotes.Quote("Author", expectedText);

        String actualText = quote.getText();
        assertEquals(expectedText, actualText);
    }

    @Test
    void testGetAuthor() {
        String expectedAuthor = "Author Name";
        Quotes.Quote quote = new Quotes.Quote(expectedAuthor, "This is a sample quote text");
        String actualAuthor = quote.getAuthor();
        assertEquals(expectedAuthor, actualAuthor);
    }
}








