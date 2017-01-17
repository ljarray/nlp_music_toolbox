package nlp.music.tools;

//import org.apache.commons.io.FilenameUtils;
//import java.io.File;

public class MidiCsvConverter {

    public static void main(String[] args) throws Exception {

        /**
         * LOW Priority
         *
         * todo create batch process to convert files in the midi folder into csv files and save them in the csv folder.
         * todo Add code to convert files on Windows
         *
         * Note: Previous Code Below
         */

//        // Create constant for target directory
////        File MIDI_DIRECTORY = new File("/Volumes/Vaults/Coding/Data/Classical-Midis");
////        runMidicsv(MIDI_DIRECTORY);
//
//        File DATA_DIR = new File("/Volumes/Vaults/Coding/Java/nlp_music_toolbox/src/main/resources/data");
//    }
//
//    public static void runMidicsv(File f) throws Exception {
//        if (f.isDirectory())
//            for (File ff : f.listFiles()) runMidicsv(ff);
//        else {
//            // Run the midicsv [input file] [output file] command in Terminal for files in the directory
//            // WARNING: it does not check for .mid extensions. So it will crash for other file types in the directory
//            String[] terminalCommand = {"/usr/local/Cellar/midicsv/1.1/bin/midicsv", f.getAbsolutePath(), FilenameUtils.removeExtension(f.getAbsolutePath()) + ".csv"};
//            Runtime.getRuntime().exec(terminalCommand);
//        }
    }

}