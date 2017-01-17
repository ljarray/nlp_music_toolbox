package nlp.music;

import nlp.music.tools.CsvRdfConverter;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here

        CsvRdfConverter classics = new CsvRdfConverter();
        classics.convertSongs(new File("data/csv/bach"));
    }
}


