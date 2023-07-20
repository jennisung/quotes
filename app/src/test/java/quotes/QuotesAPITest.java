package quotes;

import org.junit.Test;

// import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class QuotesAPITest {
    private Quotes quotes;

@Test void randomQuote() {
    Quotes.Quote quote = QuotesAPI.fetchQuoteFromAPI("https://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en", quotes);
    assertNotNull("The fetched quote should not be null", quote);
    assertTrue("The quote should have a non-empty text", !quote.getText().isEmpty());
    assertTrue("The quote should have a non-empty author", !quote.getAuthor().isEmpty());

    // Print out the quote for testing purposes
    System.out.println("Random Quote:");
    System.out.println("Text: " + quote.getText());
    System.out.println("Author: " + quote.getAuthor());
}

}

