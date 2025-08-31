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
    
    private static final String RED_COLOR = "#8B0000";
    private static final String GRAY_COLOR = "#808080";

    private static final DialogueLine[] DIALOGUE = {
            new DialogueLine("Esperava muito de você", 2000, RED_COLOR),
            new DialogueLine("De nós", 1500, RED_COLOR),
            new DialogueLine("Mas eu tenho que aceitar", 1000, RED_COLOR),
            new DialogueLine("Que sempre serei", 1000, RED_COLOR),
            new DialogueLine("Sozinho", 1000, RED_COLOR),
            new DialogueLine("...", 1000, RED_COLOR),
            new DialogueLine("Sinto como se...", 2000, RED_COLOR),
            new DialogueLine("Tivesse roubado uma vida", 2000, RED_COLOR),
            new DialogueLine("Não sou a pessoa que deveria ser", 2000, RED_COLOR),
            new DialogueLine("Eu sou vazio", 1500, RED_COLOR),
            new DialogueLine("Mas laços humanos sempre levam", 1500, RED_COLOR),
            new DialogueLine("A grandes complicações", 1000, RED_COLOR),
            new DialogueLine("Compomissos, cumplicidade", 800, RED_COLOR),
            new DialogueLine("Levar passoas ao aeroporto", 2000, RED_COLOR),
            new DialogueLine("Eu acho que as pessoa ao meu redor", 1500, RED_COLOR),
            new DialogueLine("Estão sempre fazendo algum tipo de conexão", 2000, RED_COLOR),
            new DialogueLine("Como amizade ou romance", 3000, RED_COLOR),
            new DialogueLine("Além disso,", 1000, RED_COLOR),
            new DialogueLine("Se eu deixar alguém se aproximar assim", 1500, RED_COLOR),
            new DialogueLine("Eles veriam quem eu realmente sou", 1500, RED_COLOR),
            new DialogueLine("E eu não posso deixar isso acontecer", 1500, RED_COLOR),
            new DialogueLine("Não estou nada infeliz", 1000, RED_COLOR),
            new DialogueLine("Estou bem satisfeito em seguir minha vida", 1500, RED_COLOR),
            new DialogueLine("Acreditando em NADA", 1500, RED_COLOR),
            new DialogueLine("Sem medo de que possa ver algo maior por ai", 1500, RED_COLOR),
            new DialogueLine("Não sei o que me sez ser como eu sou", 1500, RED_COLOR),
            new DialogueLine("Mas", 500, RED_COLOR),
            new DialogueLine("O que que que tenha sido", 1000, RED_COLOR),
            new DialogueLine("Deixou um vazio aqui dentro", 8000, RED_COLOR),
    };

    private static int currentLineIndex = -1;
    private static JLabel lyricsLabel;
    private static Timer timer;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Dexter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(Color.BLACK);
            frame.add(mainPanel);

            JLabel musicInfoLabel = new JLabel("Dexter", SwingConstants.CENTER);
            musicInfoLabel.setForeground(Color.WHITE);
            musicInfoLabel.setFont(new Font("Garamond", Font.BOLD, 24));
            mainPanel.add(musicInfoLabel, BorderLayout.NORTH);

            JPanel lyricsContainer = new JPanel();
            lyricsContainer.setBackground(Color.BLACK);

            lyricsLabel = new JLabel(getFormattedLyrics());
            lyricsLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            lyricsLabel.setForeground(Color.WHITE);

            JScrollPane scrollPane = new JScrollPane(lyricsLabel);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setPreferredSize(new Dimension(600, 400));


            scrollPane.getViewport().setBackground(Color.BLACK);
            scrollPane.setBackground(Color.BLACK);

            lyricsContainer.add(scrollPane);
            mainPanel.add(lyricsContainer, BorderLayout.CENTER);

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