package quotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuotesAPI {
    public static <Quote> void main(String[] args) throws IOException {
        String apiUrl = "https://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";



        String quoteData = fetchRandomQuote(apiUrl);


        if (quoteData != null) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Quote quote = gson.fromJson(quoteData, Quote.class);



            System.out.println("Quote: " + quote.getQuoteText() + "\nAuthor: " + quote.getQuoteAuthor() + "\nQuote Link: " + quote.getQuoteLink());



            cacheQuote(quote);
        } else {

            displayRandomQuoteFromFile();
        }
    }

    private static String fetchRandomQuote(String apiUrl) {
        try {
            URL quoteUrl = new URL(apiUrl);
            HttpURLConnection quoteUrlConnection = (HttpURLConnection) quoteUrl.openConnection();
            quoteUrlConnection.setRequestMethod("GET");
            quoteUrlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");


            int statusCode = quoteUrlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                System.err.println("Request failed with status code: " + statusCode);
                return null;
            }


            InputStreamReader quoteInputStreamReader = new InputStreamReader(quoteUrlConnection.getInputStream());
            BufferedReader quoteBufferedReader = new BufferedReader(quoteInputStreamReader);
            return quoteBufferedReader.readLine();
        } catch (IOException e) {
            System.err.println("Error fetching the quote: " + e.getMessage());
            return null;
        }
    }

    private static void cacheQuote(Quote quote) {
        File quotesFile = new File("./quotes.json");
        try (FileWriter fileWriter = new FileWriter(quotesFile, true)) {
            Gson gson = new Gson();
            gson.toJson(Quote, fileWriter);
            fileWriter.append(System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error writing the quote to the file: " + e.getMessage());
        }
    }

    private static void displayRandomQuoteFromFile() {
        File quotesFile = new File("./quotes.json");
        try (BufferedReader reader = new BufferedReader(new FileReader(quotesFile))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
            }
            if (count == 0) {
                System.err.println("No quotes available in the file.");
                return;
            }



            int randomIndex = (int) (Math.random() * count);
            count = 0;
            try (BufferedReader randomQuoteReader = new BufferedReader(new FileReader(quotesFile))) {
                while ((line = randomQuoteReader.readLine()) != null) {
                    if (count == randomIndex) {
                        Gson gson = new Gson();
                        Quote randomQuote = gson.fromJson(line, Quote.class);


                        System.out.println("Random Quote (from file): " + randomQuote.getQuoteText() + "\nAuthor: " + randomQuote.getQuoteAuthor() + "\nQuote Link: " + randomQuote.getQuoteLink());
                        return;
                    }
                    count++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the quotes file: " + e.getMessage());
        }
    }
}

