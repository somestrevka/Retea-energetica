import com.fasterxml.jackson.databind.ObjectMapper;
import data.InputData;
import data.OutputData;
import utils.Procesare;

import java.io.File;

/**
 * Entry point to the simulation
 */
public final class Main {

    private Main() { }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {
        InputData inputData;
        OutputData outputData = new OutputData();
        Procesare procesare = new Procesare();

        ObjectMapper objectMapper = new ObjectMapper(); // realizam citirea datelor din fisier
        inputData = objectMapper.readValue(new File(args[0]), InputData.class);
        procesare.allTurns(inputData, outputData);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(args[1]), outputData);
    }
}
