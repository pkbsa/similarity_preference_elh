package io.github.xlives.batch.owl.dynamicprogramming.sim;

import org.apache.commons.io.FileUtils;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;

import java.io.File;
import java.io.IOException;

public class OWLClassExtractor {
    public static void main(String[] args) throws IOException {
        String owlFilePath = "/Users/siranutakarawuthi/Code/Ontop/sim-preference-elh/batch-owl-dynamicprogramming-sim/output/output.owl";
        File OutputFile = new File("/Users/siranutakarawuthi/Code/Ontop/sim-preference-elh/batch-owl-dynamicprogramming-sim/output/outputpair");

        StringBuilder ResultOutput;
        ResultOutput = new StringBuilder();


        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology;
        try {
            ontology = manager.loadOntologyFromOntologyDocument(IRI.create("file:" + owlFilePath));
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
            return;
        }

        ShortFormProvider shortFormProvider = new SimpleShortFormProvider();

        for (OWLClass owlClass1 : ontology.getClassesInSignature()) {
            String className1 = shortFormProvider.getShortForm(owlClass1);

            for (OWLClass owlClass2 : ontology.getClassesInSignature()) {
                String className2 = shortFormProvider.getShortForm(owlClass2);

                if (className1.equals(className2) || className1.compareTo(className2) >= 0) {
                    continue; // Skip pairing if className1 is equal to or comes after className2 lexicographically
                }
                System.out.println(className1 + " " + className2);
                ResultOutput.append(className1 + " " + className2);
                ResultOutput.append("\n");
            }
        }


        FileUtils.writeStringToFile(OutputFile, ResultOutput.toString(), false);
    }
}