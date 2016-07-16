package nlp.music.tools;

import java.lang.Math;

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

    String note = "";

        String[] notesInAnOctave = {"C", "C#", "D", "D#", "E","F", "F#","G", "G#", "A", "A#", "B"};
        // Find the note based on number (they are mapped from C - B, and cycle every 12 numbers)
        if ( 0 >= num && num < 128) {
            note = notesInAnOctave[num % 12] + ((int) Math.floor(num / 12));
        } else {/*Throw an error, message "Note number from midi file is out of bounds. Must be between 0 - 127.*/}

        return note;
    }
}
