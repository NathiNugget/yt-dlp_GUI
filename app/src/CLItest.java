import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class CLItest {

    public static void downloadElement(String[] args){
        String[] stringArr = args; 
        ProcessBuilder pb;
        try {
            Runtime runTime = Runtime.getRuntime();
            if (stringArr[1].equals("MP4")) {
            pb = new ProcessBuilder("cmd.exe", "/c", "yt-dlp.exe --remux-video mp4 -o \"%(title)s.mp3\" " , stringArr[0]);
        } else {
            pb = new ProcessBuilder("cmd.exe", "/c", "yt-dlp.exe -f \"ba\" -o \"%(title)s.mp3\" ", stringArr[0]);
        }
            
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line; 
            String previous = ""; 
            
            Long i = 0L; 
            while (true) {
                line = r.readLine(); 
                if (line == null) {
                    if (previous != null){
                        previous = null; 
                    }
                    else break;

                } 
                
                // System.out.println(line);
                GUI.setResult(previous);
                if (line != null && line != previous){
                    GUI.setResult(line); 
                }
                if (!(line == null)) GUI.setResult(line);
                previous = line; 
                i++; 
                
            }
            GUI.showPath(); 
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateYT() throws updateException {
        Runtime runTime = Runtime.getRuntime(); 
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "yt-dlp.exe -U"); 
        pb.redirectErrorStream(true);
        Process p;
        try {
            p = pb.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = ""; 
            while (true){
                try {
                    line = r.readLine();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
                if (line == null){
                    break; 
                } else if (line.contains("yt-dlp is up to date")){
                    throw new updateException("yt-dlp is already up to date");
                }
                
        }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

}