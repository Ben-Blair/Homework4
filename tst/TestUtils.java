import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestUtils {
    public static String NameFileContext;
    public static String NameFile;

    public static void cleanAllTestFiles() throws IOException {
        System.out.println("Entering TestUtils.cleanAllTestFiles");

        Files.deleteIfExists((new File(TestPersonality.ACTUAL_OUTPUT_BATCH_FILE_NAME)).toPath());
        Files.deleteIfExists((new File(TestPersonality.ACTUAL_OUTPUT_SINGLE_FILE_NAME)).toPath());

        System.out.println("Exiting TestUtils.cleanAllTestFiles");
    }
}