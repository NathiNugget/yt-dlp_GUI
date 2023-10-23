import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
 


public class GUI {
    
    private static JTextArea result; 
    private static int count; 
    
    
    public GUI(){
        count = 0; 
        JFrame frame = new JFrame("yt-dlp with GUI by Nathaniel Finn Michel Risum");
        Image icon;
        try {
            
            icon = ImageIO.read(getClass().getResource("rsc/yt.png"));
            frame.setIconImage(icon);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
        
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel(new FlowLayout()); 
        JPanel updatePanel = new JPanel(new FlowLayout()); 
        updatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 200));


        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(new Rectangle(940, 240, 800, 720));
        // frame.setSize(600, 600);
        JTextArea plug = new JTextArea(" This GUI is developed, published and updated by Nathaniel Finn Michel Risum.\n Find me at GitHub: NathiNugget or by mail: nathaniel.riusm2@gmail.com\n Link to GitHub repository to download the newest release: https://github.com/NathiNugget/yt-dlp_GUI.git");
        plug.setEditable(false);
        plug.setFont(new Font("Comic Sans MS", 0, 14));
        
        JTextField announcement = new JTextField("Insert URL in the text-field below, then click either button below in order to download an MP3 or MP4");
        announcement.setEditable(false);
        JTextField text = new JTextField("");

        result = new JTextArea(""); 
        result.setEditable(false);
        
        announcement.setBackground(new Color(180, 180, 180));
        announcement.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT); 
        announcement.setFont(new Font("Verdana", 0, 12));
        text.setBackground(new Color(180, 180, 180)); 
        
       
       
        JButton mp3 = new JButton("CLICK FOR AUDIO DOWNLOAD"); 
        
        JButton mp4 = new JButton("CLICK FOR VIDEO DOWNLOAD"); 
        mp4.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JButton update = new JButton("UPDATE YT-DLP"); 

        mp3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                CLItest.downloadElement(new String[]{text.getText().replaceAll(" ", ""), "MP3"});
                
            }
        });
        mp4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CLItest.downloadElement(new String[]{text.getText().replaceAll(" ", ""), "MP4"});
            }
        });
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt){
                try {
                    CLItest.updateYT();
                } catch (updateException e) {
                    // TODO Auto-generated catch block
                    frame.setTitle("yt-dlp with GUI by Nathaniel Finn Michel Risum: ERROR 1 --> yt-dlp is already updated on your system");
                }
            }
        });
        plug.setMaximumSize(new Dimension(10000, 50));
        announcement.setMaximumSize(new Dimension(10000, 40));
        text.setMaximumSize(new Dimension(600, 30));
        panel.add(plug);
        panel.add(announcement); // Adds Button to content pane of frame
        panel.add(text);
        buttonPanel.add(mp3); 
        buttonPanel.add(mp4);
        updatePanel.add(update);
        panel.add(buttonPanel); 
        panel.add(update);
        panel.add(result); 
        
        
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        String fonts[] = 
        GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (int i = 0; i < fonts.length; i++) {
        System.out.println(fonts[i]);
        }
    }

    public static void setResult(String s){ 
        {
            if (count % 3 == 0){
                String previous = result.getText(); 

                if ((previous.length() <= 0)) {
                    for (char c : s.toCharArray()) {
                        System.out.println("'"+c+"'");
                    }
                    System.out.println(previous.length());
                    System.out.println("Initial value: " + s);
                    result.setText(s+"\n");
                } else {
                    if (!(s == null) && !s.equals(previous)) result.setText(result.getText() + "\n" + s);
                }
                
            }
             count++;
            
        }
    }

    public static void showPath(){
        result.setText(result.getText() + "\nFile saved to: " + Paths.get(".").toAbsolutePath().normalize().toString());
    }
        
    
}
