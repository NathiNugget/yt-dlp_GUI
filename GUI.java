import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage; 


public class GUI {

    public GUI(){
        JFrame frame = new JFrame("yt-dlp with GUI by Nathaniel Finn Michel Risum");
        frame.setIconImage(new ImageIcon(".\\yt.png").getImage());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(new Rectangle(940, 240, 800, 600));
        // frame.setSize(600, 600);
        JTextArea plug = new JTextArea(" This GUI is developed, published and updated by Nathaniel Finn Michel Risum.\n Find me at GitHub: NathiNugget or by mail: nathaniel.riusm2@gmail.com\n Link to GitHub repository to download the newest release: https://github.com/NathiNugget/yt-dlp_GUI.git");
        JTextField announcement = new JTextField("Insert URL in the text-field below, then click on either button at the page end in order to download an MP3 or MP4");
        JTextField text = new JTextField("");
        announcement.setBackground(new Color(180, 180, 180));
        announcement.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT); 
        announcement.setFont(new Font("Comic Sans MS", 0, 14));
        text.setBackground(new Color(180, 180, 180)); 
       
       
       
        JButton mp3 = new JButton("CLICK FOR AUDIO DOWNLOAD"); 
        JButton mp4 = new JButton("CLICK FOR VIDEO DOWNLOAD"); 
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
        panel.add(mp3);
        panel.add(mp4);
        panel.add(update);
        
        
        frame.getContentPane().add(panel);
        frame.setVisible(true);


    }
        public static void main(String[] args) {
            GUI gui = new GUI(); 
        }
    
}
