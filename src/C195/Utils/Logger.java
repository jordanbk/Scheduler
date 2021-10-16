package C195.Utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Logger {
    private static final String FILENAME = "login_activity.txt";

    public Logger() {}

    public static void AuditLogger (String username, boolean success) {
        try (FileWriter filewriter = new FileWriter(FILENAME, true);
             BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
             PrintWriter printwriter = new PrintWriter(bufferedwriter)) {
             bufferedwriter.append(LocalDateTime.now() + "," + " Username: " + username + "," +
                    " Login Success: " + (success ? " Success" : " Failure") + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
