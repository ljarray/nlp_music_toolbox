package nlp.music.tools;

import java.io.File;
import java.lang.Math;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Note {
    String note;
    Integer time;
    Integer channel;

    public Note (String line){
        String[] cols = line.split("\\s*,\\s*");

        channel = Integer.valueOf(cols[0]);
        time = Integer.valueOf(cols[1]);
        note = setNote(Integer.valueOf(cols[4]));
    }

    public static String setNote(int num){
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

    public Integer getChannel(){ return channel; }

    public Integer getTime(){ return time; }

    public String getNote(){ return note; }
}

class Song {

    ArrayList<Integer> timeIndices = new ArrayList<>();
    HashMap<Integer, ArrayList<Note>> notesByTime = new HashMap<>();

    public Song (){}

    public void addNote(String line){
        Note n = new Note(line);
        if ( !timeIndices.contains(n.getTime()) )
           timeIndices.add(n.getTime());

        if ( !notesByTime.containsKey(n.getTime()) )
            notesByTime.put(n.getTime(), new ArrayList());

        if ( !notesByTime.get(n.getTime()).contains(n))
            notesByTime.get(n.getTime()).add(n);
    }

}

public class CsvRdfConverter {

    public static void convertSongs(File f) throws IOException, NullPointerException {

        Pattern notePattern = Pattern.compile("note_on_c", Pattern.CASE_INSENSITIVE); // matches lines with Note_on_c
        Song currentSong = new Song();

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
                    if (m.find() && !thisLine.split("\\s*,\\s*")[5].equals("0")){ // lines with the Note_on_c attribute && notes with nonzero volume
                        currentSong.addNote(thisLine);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            /**
             *  High Priority
             *
             *  todo: take the notes in currentSong and add them into the RDF framework
             *  todo: Create rdf triple with {note 1} {inChordWith} {note 2} schema for notes that appear together
             *  todo: Create rdf triple with {note 1} {followedBy} {note 2} schema for a sequence of two notes
             *  todo: Create rdf triple with {note 2} {precededBy} {note 1} schema for a sequence of two notes
             *
             */

        }
    }
}