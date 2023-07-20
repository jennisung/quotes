package quotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
// import java.util.List;

public class QuotesAPI {
    public static void main(String[] args) throws IOException {
        String apiUrl = "https://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";

        Quotes quotes = new Quotes("./quotes.json");
        Quotes.Quote quote = fetchRandomQuoteFromAPI(apiUrl, quotes);

        if (quote != null) {
            System.out.println("Quote: " + quote.getText() + "\nAuthor: " + quote.getAuthor());
        } else {
            displayRandomQuoteFromFile(quotes);
        }
    }

    private static Quotes.Quote fetchRandomQuoteFromAPI(String apiUrl, Quotes quotes) {
        Quotes.Quote apiQuote = fetchQuoteFromAPI(apiUrl);
        if (apiQuote != null) {
            quotes.getListOfQuotes().add(apiQuote);
            cacheQuotes(quotes);
        }
        return apiQuote;
    }

    private static Quotes.Quote fetchQuoteFromAPI(String apiUrl) {
        try {
            URL quoteUrl = new URL(apiUrl);
            HttpURLConnection quoteUrlConnection = (HttpURLConnection) quoteUrl.openConnection();
            quoteUrlConnection.setRequestMethod("GET");
            quoteUrlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

            int statusCode = quoteUrlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                System.err.println("Error: " + statusCode);
                return null;
            }

            InputStreamReader quoteInputStreamReader = new InputStreamReader(quoteUrlConnection.getInputStream());
            BufferedReader quoteBufferedReader = new BufferedReader(quoteInputStreamReader);
            String quoteData = quoteBufferedReader.readLine();
            quoteBufferedReader.close();

            Gson gson = new Gson();
            return gson.fromJson(quoteData, Quotes.Quote.class);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    private static void cacheQuotes(Quotes quotes) {
        File quotesFile = new File("./quotes.json");
        try (FileWriter fileWriter = new FileWriter(quotesFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(quotes.getListOfQuotes(), fileWriter);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void displayRandomQuoteFromFile(Quotes quotes) {
        File quotesFile = new File("./quotes.json");
        try (BufferedReader reader = new BufferedReader(new FileReader(quotesFile))) {
            Gson gson = new Gson();
            Quotes.Quote[] quoteArray = gson.fromJson(reader, Quotes.Quote[].class);

            if (quoteArray == null || quoteArray.length == 0) {
                System.err.println("No quotes in the file.");
                return;
            }

            int randomIndex = (int) (Math.random() * quoteArray.length);
            Quotes.Quote randomQuote = quoteArray[randomIndex];

            System.out.println("Random Quote : " + randomQuote.getText() + "\nAuthor: " + randomQuote.getAuthor());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}





// import com.google.gson.Gson;
// import com.google.gson.GsonBuilder;
// import org.checkerframework.framework.qual.EnsuresQualifierIf;

// import java.io.*;
// import java.net.HttpURLConnection;
// import java.net.URL;

// public class QuotesAPI {
//     public static void main(String[] args) throws IOException {
//         String apiUrl = "https://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";

//         QuotesGS.Quote quote = fetchRandomQuote(apiUrl);

//         if (quote != null) {
//             Gson gson = new GsonBuilder().setPrettyPrinting().create();
//             System.out.println("Quote: " + quote.getQuoteText() + "\nAuthor: " + quote.getQuoteAuthor() + "\nQuote Link: " + quote.getQuoteLink());

//             cacheQuote(quote);
//         } else {
//             displayRandomQuoteFromFile();
//         }
//     }

//     private static QuotesGS.Quote fetchRandomQuote(String apiUrl) {
//         try {
//             URL quoteUrl = new URL(apiUrl);
//             HttpURLConnection quoteUrlConnection = (HttpURLConnection) quoteUrl.openConnection();
//             quoteUrlConnection.setRequestMethod("GET");
//             quoteUrlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

//             int statusCode = quoteUrlConnection.getResponseCode();
//             if (statusCode != HttpURLConnection.HTTP_OK) {
//                 System.err.println("Request failed with status code: " + statusCode);
//                 return null;
//             }

//             InputStreamReader quoteInputStreamReader = new InputStreamReader(quoteUrlConnection.getInputStream());
//             BufferedReader quoteBufferedReader = new BufferedReader(quoteInputStreamReader);
//             String quoteData = quoteBufferedReader.readLine();
//             quoteBufferedReader.close();

//             Gson gson = new Gson();
//             return gson.fromJson(quoteData, QuotesGS.Quote.class);
//         } catch (IOException e) {
//             System.err.println("Error fetching the quote: " + e.getMessage());
//             return null;
//         }
//     }

//     private static void cacheQuote(QuotesGS.Quote quote) {
//         File quotesFile = new File("./quotes.json");
//         try (FileWriter fileWriter = new FileWriter(quotesFile, true)) {
//             Gson gson = new Gson();
//             gson.toJson(quote, fileWriter);
//             fileWriter.append(System.lineSeparator());
//         } catch (IOException e) {
//             System.err.println("Error writing the quote to the file: " + e.getMessage());
//         }
//     }


//         private static void displayRandomQuoteFromFile() {
//         File quotesFile = new File("./quotes.json");
//         try (BufferedReader reader = new BufferedReader(new FileReader(quotesFile))) {
//             String line;
//             EnsuresQualifierIf.List<QuotesGS.Quote> quotesList = new ArrayList<>();
//             while ((line = reader.readLine()) != null) {
//                 Gson gson = new Gson();
//                 QuotesGS.Quote quote = gson.fromJson(line, QuotesGS.Quote.class);
//                 quotesList.add(quote);
//             }

//             if (quotesList.isEmpty()) {
//                 System.err.println("No quotes available in the file.");
//                 return;
//             }

//             int randomIndex = (int) (Math.random() * quotesList.size());
//             QuotesGS.Quote randomQuote = quotesList.get(randomIndex);

//             System.out.println("Random Quote (from file): " + randomQuote.getQuoteText() + "\nAuthor: " + randomQuote.getQuoteAuthor() + "\nQuote Link: " + randomQuote.getQuoteLink());
//         } catch (IOException e) {
//             System.err.println("Error reading the quotes file: " + e.getMessage());
//         }
//     }
// }

