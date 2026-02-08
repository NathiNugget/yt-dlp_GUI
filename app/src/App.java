import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            GUI gui = new GUI();
            System.out.println("Application started successfully.");
        } catch (Exception e) {
            try (PrintWriter out = new PrintWriter(new FileWriter("error_log.txt", true))) {
                out.println("Error: " + e.getMessage());
                e.printStackTrace(out);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
