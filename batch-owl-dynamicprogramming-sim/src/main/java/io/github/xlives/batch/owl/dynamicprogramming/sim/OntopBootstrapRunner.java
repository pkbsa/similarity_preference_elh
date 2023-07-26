package io.github.xlives.batch.owl.dynamicprogramming.sim;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class OntopBootstrapRunner {
    private static String ontologyFilePath = "/Users/siranutakarawuthi/Code/Ontop/sim-preference-elh/batch-owl-dynamicprogramming-sim/output/output.owl";;

    public void useBootstrap() {
        String ontologBaseURL = "http://example.org/voc";
        String propertiesFile = "/Users/siranutakarawuthi/Code/Ontop/sim-preference-elh/batch-owl-dynamicprogramming-sim/input/university-complete.properties";
        String owlFile = ontologyFilePath;
        String obdaFile = "/Users/siranutakarawuthi/Code/Ontop/sim-preference-elh/batch-owl-dynamicprogramming-sim/output/university-complete.obda";

        String ontopCommand = "ontop";

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
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;

            System.out.println("Output:");
            while ((line = stdoutReader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("\n=====================");

            System.out.println("Error messages:");
            while ((line = stderrReader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("\n=====================");

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

    public void setPathAndUseBatchConfiguration(String ontologyFilePath) throws Exception {
        this.ontologyFilePath = ontologyFilePath;

        System.out.println("\nRunning BatchConfiguration...");
        BatchConfiguration batchConfig = new BatchConfiguration();
        batchConfig.setOntologyFilePath(ontologyFilePath);
        batchConfig.main(new String[]{});
    }

    public void toBeImplementedMethod() {
        // to be implemented
    }

    public static void main(String[] args) throws Exception {
        OntopBootstrapRunner runner = new OntopBootstrapRunner();
//        runner.useBootstrap();
        runner.setPathAndUseBatchConfiguration(ontologyFilePath);
//        runner.toBeImplementedMethod();
    }
}
