import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Main {
    static class DialogueLine {
        String text;
        int durationMillis;
        String color;

        public DialogueLine(String text, int durationMillis, String color) {
            this.text = text;
            this.durationMillis = durationMillis;
            this.color = color;
        }
    }

    private static final String PINK_COLOR = "#FF1493";
    private static final String CYAN_COLOR = "#00FFFF";
    private static final String GRAY_COLOR = "#808080";

    private static final DialogueLine[] DIALOGUE = {
            new DialogueLine("I love you!", 1000, PINK_COLOR),
            new DialogueLine("You might as well take my heart", 3000, PINK_COLOR),
            new DialogueLine("It's already full of you!", 1500, PINK_COLOR),
            new DialogueLine("Please go.", 1000, CYAN_COLOR),
            new DialogueLine("What is it?", 1000, PINK_COLOR),
            new DialogueLine("What's wrong, my dear?", 1500, PINK_COLOR),
            new DialogueLine("You don't know anything about me anymore", 1500, CYAN_COLOR),
            new DialogueLine("We only spoke again four months ago!", 1500, CYAN_COLOR),
            new DialogueLine("Four months?", 1000, PINK_COLOR),
            new DialogueLine("I've known you my whole life", 1500, PINK_COLOR),
            new DialogueLine("All your life?", 1000, CYAN_COLOR),
            new DialogueLine("It's true", 1000, PINK_COLOR),
            new DialogueLine("When I hear a beautiful song", 1500, PINK_COLOR),
            new DialogueLine("I think \"this song reminds me of him\"", 1500, PINK_COLOR),
            new DialogueLine("I looked at the codes knowing...", 2000, PINK_COLOR),
            new DialogueLine("\"One day I would use them for you.\"", 2000, PINK_COLOR),
            new DialogueLine("...", 2000, PINK_COLOR)
    };

    private static int currentLineIndex = -1;
    private static JLabel lyricsLabel;
    private static Timer timer;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Dangerously Yours");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(Color.BLACK);
            frame.add(mainPanel);

            JLabel musicInfoLabel = new JLabel("Dangerously Yours", SwingConstants.CENTER);
            musicInfoLabel.setForeground(Color.WHITE);
            musicInfoLabel.setFont(new Font("Garamond", Font.BOLD, 24));
            mainPanel.add(musicInfoLabel, BorderLayout.NORTH);

            lyricsLabel = new JLabel(getFormattedLyrics(), SwingConstants.CENTER);
            lyricsLabel.setFont(new Font("Arial", Font.PLAIN, 22));
            mainPanel.add(lyricsLabel, BorderLayout.CENTER);

            JPanel controlPanel = new JPanel();
            controlPanel.setBackground(Color.DARK_GRAY);

            JButton playButton = new JButton("Play");
            playButton.setFont(new Font("Garamond", Font.BOLD, 16));

            playButton.addActionListener(e -> {
                startNextLineTimer();
                playButton.setEnabled(false);
            });

            controlPanel.add(playButton);
            mainPanel.add(controlPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }

    private static void startNextLineTimer() {
        if (timer != null) {
            timer.stop();
        }

        currentLineIndex++;
        if (currentLineIndex >= DIALOGUE.length) {
            currentLineIndex = 0;
        }

        int duration = DIALOGUE[currentLineIndex].durationMillis;

        lyricsLabel.setText(getFormattedLyrics());

        timer = new Timer(duration, e -> {
            startNextLineTimer();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private static String getFormattedLyrics() {

        StringBuilder sb = new StringBuilder("<html><body>");

        for (int i = 0; i < DIALOGUE.length; i++) {
            DialogueLine line = DIALOGUE[i];

            if (i == currentLineIndex) {
                sb.append("<p style='color:").append(line.color).append("; font-weight:bold;'>").append(line.text).append("</p>");
            } else {
                sb.append("<p style='color:").append(GRAY_COLOR).append(";'>").append(line.text).append("</p>");
            }
        }
        sb.append("</body></html>");
        return sb.toString();
    }
}
