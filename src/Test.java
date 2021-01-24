import checker.Checkstyle;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * The type Config.
 */
class Config {
    private String homework;
    private String description;
    private Integer checkstyleScore;
    private Integer homeworkDesignScore;
    private Integer readmeScore;

    private Integer gitScore;
    private List<TestType> testTypes;

    /**
     * Gets homework.
     *
     * @return the homework
     */
    public String getHomework() {
        return homework;
    }

    /**
     * Sets homework.
     *
     * @param homework the homework
     */
    public void setHomework(final String homework) {
        this.homework = homework;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets checkstyle score.
     *
     * @return the checkstyle score
     */
    public Integer getCheckstyleScore() {
        return checkstyleScore;
    }

    /**
     * Sets checkstyle score.
     *
     * @param checkstyleScore the checkstyle score
     */
    public void setCheckstyleScore(final Integer checkstyleScore) {
        this.checkstyleScore = checkstyleScore;
    }

    /**
     * Gets homework design score.
     *
     * @return the homework design score
     */
    public Integer getHomeworkDesignScore() {
        return homeworkDesignScore;
    }

    /**
     * Sets homework design score.
     *
     * @param homeworkDesignScore the homework design score
     */
    public void setHomeworkDesignScore(final Integer homeworkDesignScore) {
        this.homeworkDesignScore = homeworkDesignScore;
    }

    /**
     * Gets readme score.
     *
     * @return the readme score
     */
    public Integer getReadmeScore() {
        return readmeScore;
    }

    /**
     * Sets readme score.
     *
     * @param readmeScore the readme score
     */
    public void setReadmeScore(final Integer readmeScore) {
        this.readmeScore = readmeScore;
    }

    /**
     * Gets test types.
     *
     * @return the test types
     */
    public List<TestType> getTestTypes() {
        return testTypes;
    }

    /**
     * Sets test types.
     *
     * @param testTypes the test types
     */
    public void setTestTypes(final List<TestType> testTypes) {
        this.testTypes = testTypes;
    }

    /**
     * Gets git score.
     *
     * @return the git score
     */
    public Integer getGitScore() {
        return gitScore;
    }

    /**
     * Sets git score.
     *
     * @param gitScore the git score
     */
    public void setGitScore(Integer gitScore) {
        this.gitScore = gitScore;
    }
}

/**
 * The type Test type.
 */
class TestType {

    private Integer score;
    private String type;

    /**
     * Gets score.
     *
     * @return the score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(final Integer score) {
        this.score = score;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(final String type) {
        this.type = type;
    }
}

/**
 * The type Test.
 */
public final class Test {
    private static final String IN_FOLDER = "in/";
    private static final String REF_FOLDER = "ref/";
    private static final String CHECKER_RESOURCES_FOLDER = "checker/resources/";
    private static final File TEST_INPUTS_FILE = new File(CHECKER_RESOURCES_FOLDER + IN_FOLDER);

    private static final String OUT_FILE = "results.out";
    private static final File TEST_OUT_FILE = new File(OUT_FILE);

    private static final File CONFIG_FILE = new File(CHECKER_RESOURCES_FOLDER + "config.json");

    private static final int MAX_MILLISECONDS_PER_TEST = 1000;

    private static int score = 0;
    private static int totalScore = 0;

    private Test() { }

    /**
     * Method to be called for testing the homework
     *
     * @param argv String[]
     */
    public static void main(final String[] argv) {
        runTests();
        //preTestCleanUp();
        System.exit(0);
    }

    private static Config loadConfig() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(CONFIG_FILE, Config.class);
        } catch (IOException e) {
            System.out.println("Could not find config file.");
            System.exit(-1);
        }

        return null;
    }

    private static void runTests() {
        Config config = loadConfig();
        totalScore = config.getCheckstyleScore();
        int manualScore = config.getReadmeScore()
                + config.getHomeworkDesignScore()
                + config.getGitScore();

        for (final File testFile: Objects.requireNonNull(TEST_INPUTS_FILE.listFiles())) {
            String testFileName = testFile.getName();

            preTestCleanUp();

            final String[] testArgv = createTestArgv(testFile);
            final Future<Object> future = createTimerTask(testArgv);

            runTest(testFileName, config, future);
        }

        boolean checkstylePassed = Checkstyle.testCheckstyle();
        if (checkstylePassed) {
            score += config.getCheckstyleScore();
        }

        System.out.println("Total score: .......................... " + score
                + "/" + totalScore);
        System.out.println("Up to "
                + manualScore
                + " points will be awarded manually by the teaching assistants."
                + " (README & GIT)");
        System.out.println("The final value can be exceeded for great implementations.");
    }

    private static void runTest(
            final String testFileName,
            final Config config,
            final Future<Object> task
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        File refFile = new File(CHECKER_RESOURCES_FOLDER + REF_FOLDER + testFileName);

        try {
            task.get(MAX_MILLISECONDS_PER_TEST, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            printMessage(testFileName, "Timeout");
            return;
        } catch (Exception e) {
            printMessage(testFileName, "Program ended with exception: " + e.getMessage());
            return;
        } finally {
            task.cancel(true);
        }

        if (!TEST_OUT_FILE.exists()) {
            printMessage(testFileName, "Output file not found. Skipping test...");
        } else {
            try {
                var actual = objectMapper.readTree(TEST_OUT_FILE);
                var expected = objectMapper.readTree(refFile);

                final int testScore = testMaxScore(config, testFileName);
                totalScore += testScore;

                if (expected.equals(actual)) {
                    score += testScore;
                    printMessage(testFileName, testScore + "/" + testScore, true);
                } else {
                    printMessage(testFileName, "0/" + testScore, true);
                }
            } catch (IOException e) {
                printMessage(testFileName,
                        "Output file badly formatted. Skipping test... + " + e.getMessage());
            }
        }
    }

    private static Future<Object> createTimerTask(final String[] argv) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Callable<Object> task = () -> {
            Main.main(argv);
            return null;
        };

        return executor.submit(task);
    }

    private static String[] createTestArgv(final File testFile) {
        List<String> listArgv = new ArrayList<>();
        listArgv.add(testFile.getAbsolutePath());
        listArgv.add(OUT_FILE);
        String[] argv = new String[0];
        return listArgv.toArray(argv);
    }

    private static void preTestCleanUp() {
        TEST_OUT_FILE.delete();
    }

    private static void printMessage(
            final String testFileName,
            final String message
    ) {
        printMessage(testFileName, message, false);
    }

    private static void printMessage(
            final String testFileName,
            final String message,
            final boolean trail
    ) {
        String fileName = testFileName.split("\\.")[0];
        if (trail) {
            System.out.println("[" + fileName + "]: ..................... " + message);
        } else {
            System.out.println("[" + fileName + "]: " + message);
        }
    }

    private static int testMaxScore(
            final Config config,
            final String testFileName
    ) {
        for (TestType testType: config.getTestTypes()) {
            if (testFileName.contains(testType.getType())) {
                return testType.getScore();
            }
        }

        printMessage(testFileName, "Test score not found. Skipping test...");
        return 0;
    }
}
