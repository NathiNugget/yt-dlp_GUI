import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

public class GUI {

    private static JTextArea result;
    private static int count;
    private JScrollPane scrollPn;

    public GUI() {
        JFrame frame = new JFrame("yt-dlp with GUI by Nathaniel Finn Michel Risum");

        count = 0;

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

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(new Rectangle(940, 240, 800, 720));
        // frame.setSize(600, 600);
        JTextArea plug = new JTextArea(
                " This GUI is developed, published and updated by Nathaniel Finn Michel Risum.\n Find me at GitHub: NathiNugget or by mail: nathaniel.riusm2@gmail.com\n Link to GitHub repository to download the newest release: https://github.com/NathiNugget/yt-dlp_GUI.git");
        plug.setEditable(false);
        plug.setFont(new Font("Comic Sans MS", 0, 8));

        JTextArea announcement = new JTextArea(
                "Copy the link to a YouTube video, then click either button below in order to download an MP3 or MP4.\nThe file will be saved in the folder/place the program is run from");
        announcement.setEditable(false);

        result = new JTextArea("");
        result.setEditable(false);
        scrollPn = new JScrollPane(result);
        scrollPn.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPn.setAutoscrolls(true);
        scrollPn.setPreferredSize(new Dimension(400, 400));

        announcement.setBackground(new Color(240, 240, 240));
        announcement.setWrapStyleWord(true);
        announcement.setFont(new Font("Verdana", 0, 12));

        JButton mp3 = new JButton("CLICK FOR AUDIO DOWNLOAD");

        JButton mp4 = new JButton("CLICK FOR VIDEO DOWNLOAD");
        mp4.setAlignmentX(Component.LEFT_ALIGNMENT);

        mp3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                try { // This gets a string from clipboard
                    final String txt = (String) Toolkit.getDefaultToolkit()
                            .getSystemClipboard().getData(DataFlavor.stringFlavor);
                    if (txt.equals(""))
                        return;
                    setResult("Download is starting shortly, please wait...");
                    new SwingWorker<Void, Void>() {

                        @Override
                        protected Void doInBackground() throws Exception {
                            String text = validateText(txt);
                            CLIBridge.downloadElement(new String[] { text, "MP3" });
                            return null;
                        }

                    }.execute();
                } catch (Exception e) {
                    System.out.println(e.getStackTrace());
                }

            }
        });
        mp4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                try { // This gets a string from clipboard
                    final String txt = (String) Toolkit.getDefaultToolkit()
                            .getSystemClipboard().getData(DataFlavor.stringFlavor);
                    if (txt.equals(""))
                        return;
                    setResult("Download is starting shortly, please wait...");
                    new SwingWorker<Void, Void>() {

                        @Override
                        protected Void doInBackground() throws Exception {
                            String text = validateText(txt);
                            CLIBridge.downloadElement(new String[] { text, "MP4" });
                            return null;
                        }

                    }.execute();
                } catch (Exception e) {
                    System.out.println(e.getStackTrace());
                }

            }
        });

        plug.setMaximumSize(new Dimension(10000, 50));
        announcement.setMaximumSize(new Dimension(700, 200));

        announcement.setBorder(new EmptyBorder(50, 0, 0, 0));
        buttonPanel.setBorder(new EmptyBorder(40, 0, 0, 0));

        panel.add(plug);
        panel.add(announcement); // Adds Button to content pane of frame
        buttonPanel.add(mp3);
        buttonPanel.add(mp4);
        panel.add(buttonPanel);
        panel.add(scrollPn);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private String validateText(String text) throws Exception {
        if (!text.contains("watch"))
            throw new Exception("This was not a YouTube-link");

        int idx = text.indexOf('&');
        if (-1 != idx)
            return text.substring(0, idx).replaceAll(" ", "");

        return text.toString().replaceAll(" ", "");

    }

    public static void setResult(String s) {
        {
            if (count % 3 == 0) {
                String previous = result.getText();

                if ((previous.length() <= 0)) {
                    for (char c : s.toCharArray()) {
                        System.out.println("'" + c + "'");
                    }
                    System.out.println(previous.length());
                    System.out.println("Initial value: " + s);
                    result.setText(s + "\n");
                } else {
                    if (!(s == null) && !s.equals(previous))
                        result.setText(result.getText() + "\n" + s);
                }

            }
            count++;

        }
    }

    public static void showPath() {
        result.setText(result.getText() + "\nFile saved to: " + Paths.get(".").toAbsolutePath().normalize().toString());
    }

}
