import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Tag("FileFunctionalityFinal")
public class FileFunctionalityFinal {

    @Test
    public void cleanAllTestFiles() throws IOException {
        System.out.println("Entering FileFunctionalityFinal.cleanAllTestFiles");
        TestUtils.cleanAllTestFiles();
        System.out.println("Exiting FileFunctionalityFinal.cleanAllTestFiles");
    }

}