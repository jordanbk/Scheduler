package C195.Utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * This class handles logging user login activity
 * @author Jordan Burke
 */
public class Logger {
    //file for login activity
    private static final String FILENAME = "login_activity.txt";

    public Logger() {}

    /**
     * This method writes to the login_activity.txt file with login activity
     * @param username user's username
     * @param success success of login
     */
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
