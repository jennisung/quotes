package quotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class QuotesAPI {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String apiUrl = "https://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";

        Quotes quotes = new Quotes("./quotes.json");
        Quotes.Quote quote = getRandomQuoteAPI(new URI(apiUrl), quotes);

        if (quote != null) {
            System.out.println("Quote: " + quote.getText() + "\nAuthor: " + quote.getAuthor());
        } else {
            System.out.println("No quotes from the API.");
            randomQuote(quotes);
        }
    }

    public static Quotes.Quote getRandomQuoteAPI(URI apiUri, Quotes quotes) {
        Quotes.Quote apiQuote = quoteFromAPI(apiUri);
        if (apiQuote != null) {
            cacheQuotes(apiQuote, quotes);
        }
        return apiQuote;
    }

    private static Quotes.Quote quoteFromAPI(URI apiUri) {
        try {
            HttpURLConnection quoteUrlConnection = (HttpURLConnection) apiUri.toURL().openConnection();
            quoteUrlConnection.setRequestMethod("GET");

            if (quoteUrlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.err.println("Error: " + quoteUrlConnection.getResponseCode());
                return null;
            }

            try (BufferedReader quoteBufferedReader = new BufferedReader(new InputStreamReader(quoteUrlConnection.getInputStream()))) {
                String quoteData = quoteBufferedReader.readLine();
                Gson gson = new Gson();
                return gson.fromJson(quoteData, Quotes.Quote.class);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    private static void cacheQuotes(Quotes.Quote quote, Quotes quotes) {
        quotes.getListOfQuotes().add(quote);
        File quotesFile = new File("./quotes.json");
        try (FileWriter fileWriter = new FileWriter(quotesFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(quotes.getListOfQuotes(), fileWriter);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void randomQuote(Quotes quotes) {
        Quotes.Quote randomQuote = quotes.getRandomQuote();
        if (randomQuote == null) {
            System.err.println("No quotes in the file.");
        } else {
            System.out.println("Random Quote from File: " + randomQuote.getText() + "\nAuthor: " + randomQuote.getAuthor());
        }
    }



}
