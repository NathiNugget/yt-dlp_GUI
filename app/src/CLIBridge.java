import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLIBridge {

    public static void downloadElement(String[] args) {

        String[] stringArr = args;
        ProcessBuilder pb;
        try {
            if (stringArr[1].equals("MP4")) {
                pb = new ProcessBuilder("cmd.exe", "/c",
                        "yt-dlp.exe -f ba+bv -t mp4 --embed-subs --sub-lang \"en.*, da.*\" -o \"%(title)s\"",
                        stringArr[0]);

            } else { // The added .mp3 file extension is on purpose
                pb = new ProcessBuilder("cmd.exe", "/c", "yt-dlp.exe -f \"ba\" -o \"%(title)s.mp3\"",
                        stringArr[0]);
            }

            pb.directory(new File(System.getProperty("user.dir")));
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            String previous = "";
            int idxOfPercentage = 0;
            while (true) {
                line = r.readLine();

                if (line == null) {
                    if (previous != null) {
                        previous = null;
                    } else
                        break;

                }
                
                System.out.println(line);
                GUI.setResult(previous);

                if ((line != null))
                    GUI.setResult(line);
                previous = line;

            }
            GUI.showPath();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}