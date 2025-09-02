import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VotingMachineSimulator extends JFrame {
    private int votesA = 0, votesB = 0, votesC = 0;

    private JLabel lblVotesA, lblVotesB, lblVotesC;

    public VotingMachineSimulator() {
        setTitle("🗳️ Voting Machine Simulator");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("🗳️ Electronic Voting Machine", JLabel.CENTER);
        heading.setFont(new Font("Verdana", Font.BOLD, 26));
        heading.setOpaque(true);
        heading.setBackground(new Color(52, 73, 94));
        heading.setForeground(Color.white);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(heading, BorderLayout.NORTH);

        JPanel votePanel = new JPanel(new GridLayout(1, 3, 20, 20));
        votePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        votePanel.setBackground(new Color(236, 240, 241));

        votePanel.add(createVoteButton("Candidate A", new Color(41, 128, 185)));
        votePanel.add(createVoteButton("Candidate B", new Color(39, 174, 96)));
        votePanel.add(createVoteButton("Candidate C", new Color(192, 57, 43)));

        add(votePanel, BorderLayout.CENTER);

        JPanel resultPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));
        resultPanel.setBackground(new Color(236, 240, 241));

        lblVotesA = createResultLabel("Votes A: 0");
        lblVotesB = createResultLabel("Votes B: 0");
        lblVotesC = createResultLabel("Votes C: 0");

        resultPanel.add(lblVotesA);
        resultPanel.add(lblVotesB);
        resultPanel.add(lblVotesC);

        add(resultPanel, BorderLayout.SOUTH);
    }

    private JPanel createVoteButton(String name, Color color) {
        JButton btn = new JButton("Vote " + name);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(color);
        btn.setForeground(Color.white);
        btn.setFocusPainted(false);

        btn.addActionListener(e -> {
            if (name.contains("A")) votesA++;
            else if (name.contains("B")) votesB++;
            else if (name.contains("C")) votesC++;
            updateResults();
        });

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createLineBorder(color, 3));
        panel.add(label, BorderLayout.NORTH);
        panel.add(btn, BorderLayout.CENTER);

        return panel;
    }

    private JLabel createResultLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setOpaque(true);
        label.setBackground(Color.white);
        label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        return label;
    }

    private void updateResults() {
        lblVotesA.setText("Votes A: " + votesA);
        lblVotesB.setText("Votes B: " + votesB);
        lblVotesC.setText("Votes C: " + votesC);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VotingMachineSimulator().setVisible(true));
    }
}
