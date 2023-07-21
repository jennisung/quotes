package quotes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class QuotesAPITest {
    private URI apiUri;
    private Quotes quotes;

    @BeforeEach
    void setUp() throws URISyntaxException, IOException {
        apiUri = new URI("https://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en");
        quotes = new Quotes("./quotes.json");
    }

    @Test
    void getRandomQuoteFromAPI() {
        Quotes.Quote apiQuote = QuotesAPI.getRandomQuoteAPI(apiUri, quotes);
        assertNotNull(apiQuote);
        assertNotNull(apiQuote.getText());
        assertNotNull(apiQuote.getAuthor());

        System.out.println("API Quote: " + apiQuote.getText() + "\nAuthor: " + apiQuote.getAuthor());
    }

//    @Test
//    void cacheQuotes() {
//        int initialSize = quotes.getListOfQuotes().size();
//        Quotes.Quote apiQuote = QuotesAPI.getRandomQuoteAPI(apiUri, quotes);
//        if (apiQuote != null) {
//            QuotesAPI.cacheQuotes(apiQuote, quotes);
//            int newSize = quotes.getListOfQuotes().size();
//            assertEquals(initialSize + 1, newSize);
//
//            System.out.println("Cached API Quote: " + apiQuote.getText() + "\nAuthor: " + apiQuote.getAuthor());
//            System.out.println("Updated List Size: " + newSize);
//        }
//    }
}


