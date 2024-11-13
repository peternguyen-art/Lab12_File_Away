import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.*;
    public class FileInspector {
        public static void main(String [] args){
            JFileChooser chooser=new JFileChooser();
            File selectedFile;
            File currentDirectory= new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(currentDirectory);
            if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                selectedFile=chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                try{
                    InputStream in =
                            new BufferedInputStream(Files.newInputStream(file,CREATE));
                    BufferedReader reader=
                            new BufferedReader(new InputStreamReader(in));
                    while (reader.ready()){
                        System.out.println(reader.readLine());
                    }
                    reader.close();
                }
                catch(IOException e){
                    System.out.println("error occurred");
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("You must select a file");
            }

            if ((chooser.showOpenDialog(null)) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;

                try (BufferedReader reader = Files.newBufferedReader(file)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lineCount++;
                        String[] words = line.split("\\s+");
                        wordCount += words.length;
                        charCount += line.length();
                    }
                    System.out.println("Number of lines: " + lineCount);
                    System.out.println("Number of words: " + wordCount);
                    System.out.println("Number of character: " + charCount);
                } catch (IOException e) {
                    System.out.println("An error occurred");
                    e.printStackTrace();
                }
            } else{
                System.out.println("You must select a file");
            }
        }
    }

