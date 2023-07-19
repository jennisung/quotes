package quotes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

public class Quotes {
    private List<Quote> listOfQuotes;

//    public Quotes(String filePath) throws IOException {
//        Gson gsonParser = new Gson();
//        Type listTypeForQuotes = new TypeToken<List<Quote>>(){}.getType();
//        FileReader fileReaderForQuotes = new FileReader(filePath);
//        this.listOfQuotes = gsonParser.fromJson(fileReaderForQuotes, listTypeForQuotes);
//    }

    public Quotes(String filePath) throws IOException {
    Gson gsonParser = new Gson();
    Type listTypeForQuotes = new TypeToken<List<Quote>>(){}.getType();
    FileReader fileReaderForQuotes = new FileReader(filePath);
    try {
        this.listOfQuotes = gsonParser.fromJson(fileReaderForQuotes, listTypeForQuotes);
    } finally {
        fileReaderForQuotes.close();
    }
}

    public Quote getRandomQuote() {
        Random randomNumberGenerator = new Random();
        return listOfQuotes.get(randomNumberGenerator.nextInt(listOfQuotes.size()));
    }

    public List<Quote> getListOfQuotes() {
        return listOfQuotes;
    }

    public static class Quote {
        private String author;
        private String text;

        public Quote(String author, String text) {
            this.author = author;
            this.text = text;
        }

        public String getAuthor() {
            return author;
        }

        public String getText() {
            return text;
        }
    }
}

// package quotes;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.util.List;
//import java.util.Random;
//
//public class Quotes {
//    private List<Quote> listOfQuotes;
//
//    public Quotes(String filePath) throws IOException {
//        Gson gsonParser = new Gson();
//        Type listTypeForQuotes = new TypeToken<List<Quote>>(){}.getType();
//        FileReader fileReaderForQuotes = new FileReader(filePath);
//        this.listOfQuotes = gsonParser.fromJson(fileReaderForQuotes, listTypeForQuotes);
//    }
//
//    public Quote getRandomQuote() {
//        Random randomNumberGenerator = new Random();
//        return listOfQuotes.get(randomNumberGenerator.nextInt(listOfQuotes.size()));
//    }
//
//    public class Quote {
//        private String author;
//        private String text;
//
//        public Quote(String author, String text) {
//            this.author = author;
//            this.text = text;
//        }
//
//        public String getAuthor() {
//            return author;
//        }
//
//        public String getText() {
//            return text;
//        }
//    }
//}

