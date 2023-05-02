import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileRenamer {
    public static void main(String[] args) {
        // Get the directory path from user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter directory path:");
        String directoryPath = scanner.nextLine();

        // Get the prefix from user input
        System.out.print("Enter prefix for new file names (press Enter for no prefix):");
        String prefix = scanner.nextLine();

        // Get all files in the directory and its subdirectories
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        renameFiles(files, prefix);
        
        // Display a message indicating the operation is complete
        System.out.println("All files in '" + directoryPath + "' have been renamed.");
    }

    private static void renameFiles(File[] files, String prefix) {
        // Loop through each file and rename it
        for (File file : files) {
            if (file.isDirectory()) {
                // Recursively process subdirectories
                renameFiles(file.listFiles(), prefix);
            } else {
                // Get the file extension
                String extension = "";
                int extensionIndex = file.getName().lastIndexOf(".");
                if (extensionIndex > 0) {
                    extension = file.getName().substring(extensionIndex);
                }
                
                // Generate a new file name using the current date and time
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String newName = dateFormat.format(new Date());

                // Add the prefix to the new file name, if one was specified
                if (prefix != null && !prefix.isEmpty()) {
                    newName = prefix + "_" + newName;
                }

                // Add the file extension to the new file name
                newName += extension;

                // Rename the file
                File newFile = new File(file.getParentFile(), newName);
                file.renameTo(newFile);
            }
        }
    }
}
