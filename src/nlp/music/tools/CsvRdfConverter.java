package nlp.music.tools;

import java.io.File;
import java.lang.Math;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvRdfConverter {

    /**
     *  HIGH Priority
     *
     *  todo: Read csv files
     *  todo: Read note values from lines with the notes_on_c value (3rd column)
     *  todo: Create rdf triple with {note 1} {inChordWith} {note 2} schema for notes that appear together
     *  todo: Create rdf triple with {note 1} {followedBy} {note 2} schema for a sequence of two notes
     *  todo: Create rdf triple with {note 2} {precededBy} {note 1} schema for a sequence of two notes
     *  done: Convert note numbers into alphanumeric notation
     *
     */


    public static String getNote(int num){
        // Given a number 0 - 127 read from a csv file in the midi specification, returns the unique note and the octave
        String[] notesInAnOctave = {"C", "C#", "D", "D#", "E","F", "F#","G", "G#", "A", "A#", "B"};

        // Find the note based on number (they are mapped from C - B, and cycle every 12 numbers)
        if ( 0 <= num && num < 128) {
            return notesInAnOctave[num % 12] + ((int) Math.floor(num / 12));
        } else {
            /*Throw an error, message "Note number from midi file is out of bounds. Must be between 0 - 127.*/
            return "Error: not a note";
        }
    }

    public void convertSongs(File f) throws IOException, NullPointerException {

        Pattern notePattern = Pattern.compile("note_on_c", Pattern.CASE_INSENSITIVE);

        if (f.isDirectory()) {
            for (File ff : f.listFiles()) {
                convertSongs(ff);
            }
        }
        else {
            // Reads input from a csv song file, and converts the song into rdf objects
            String thisLine;
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(f.getAbsolutePath()));
                while ((thisLine = csvReader.readLine()) != null) {
                    Matcher m = notePattern.matcher(thisLine);
                    if (m.find()){
                        System.out.print(thisLine.split("\\s*,\\s*")[4] + ", ");
                        System.out.println(getNote(Integer.valueOf(thisLine.split("\\s*,\\s*")[4])));
                        //System.out.print(thisLine + ", ");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
