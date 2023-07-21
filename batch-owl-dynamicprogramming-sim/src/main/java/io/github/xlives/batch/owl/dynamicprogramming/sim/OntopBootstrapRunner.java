import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OntopBootstrapRunner {
    public static void main(String[] args) {
        String ontologBaseURL = "http://example.org/voc";
        String propertiesFile = "input/university-complete.properties";
        String owlFile = "input/bootstrap.owl";
        String obdaFile = "input/bootstrap.obda";

        // Replace this with the actual path to the ontop executable on your system
        String ontopCommand = "ontop";

        // Command to execute the ontop bootstrap
        String[] command = {
                ontopCommand,
                "bootstrap",
                "-b",
                ontologBaseURL,
                "-p",
                propertiesFile,
                "-t",
                owlFile,
                "-m",
                obdaFile
        };

        try {
            // Create ProcessBuilder and start the process
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // Read the output of the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Ontop bootstrap command executed successfully!");
            } else {
                System.out.println("Error occurred during Ontop bootstrap command execution.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
