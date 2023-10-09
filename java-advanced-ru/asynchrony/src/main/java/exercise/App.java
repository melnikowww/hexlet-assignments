package exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(
        String file1Path,
        String file2Path,
        String resultFilePath) {

        CompletableFuture<String> futureFile1 = CompletableFuture.supplyAsync(() -> {
            String file1;
            Path path1 = Paths.get(file1Path);
            try {
                if (Files.notExists(path1)) {
                    throw new NoSuchFileException("File 1 don`t exist");
                }
                file1 = Files.readString(path1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return file1;
        });

        CompletableFuture<String> futureFile2 = CompletableFuture.supplyAsync(() -> {
            String file2;
            Path path2 = Path.of(file2Path);
            if (Files.notExists(path2)) {
                throw new RuntimeException("File 2 not exists!");
            }
            try {
                file2 = Files.readString(path2);
            } catch (Exception e) {
                throw new RuntimeException("File 2 trouble!");
            }
            return file2;
        });

        return futureFile1.thenCombine(futureFile2, (f1, f2) -> {
            String resultFile = f1 + f2;
            Path resultPath = Path.of(resultFilePath);
            if (Files.notExists(resultPath)) {
                try {
                    Files.createFile(resultPath);
                } catch (IOException e) {
                    throw new RuntimeException("Creating of result file is not succeeded");
                }
            }
            try {
                Files.writeString(resultPath, resultFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return resultFile;
        }).exceptionally(ex -> {
            System.out.println(ex);
            return null;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        App.unionFiles(
            "src/resources/file1.txt",
            "src/resources/file2.txt",
            "src/resources/res.txt");
        // END
    }
}

