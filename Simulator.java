import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.FlowLayout;
import java.util.ArrayList;

/**
 * Main Simulation Panel, also serves as the territory grid
 *
 * @author Ahyaan Malik
 * @version 3/29/2026
 */
public class Simulator extends JPanel implements ActionListener {

    private final Color BACKGROUND_COLOR = SimulationConstants.BACKGROUND_COLOR;
    private final Color SECONDARY_COLOR = SimulationConstants.SECONDARY_COLOR;

    private static final int CELL_SIZE = SimulationConstants.CELL_SIZE;
    private static final int COLS = SimulationConstants.COLS;
    private static final int ROWS = SimulationConstants.ROWS;

    public int[][] cells;

    private ArrayList<ColorPlayer> players;
    private Timer timer;

    private CardLayout cardLayout;
    private JPanel cards;

    private JLabel statusLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton backButton;

    public Simulator(CardLayout cardLayout, JPanel cards) {
        this.cardLayout = cardLayout;
        this.cards = cards;

        setLayout(new BorderLayout());

        // Initialize grid
        cells = new int[COLS][ROWS];
        for (int x = 0; x < COLS; x++)
            for (int y = 0; y < ROWS; y++)
                cells[x][y] = -1;

        players = new ArrayList<>();

        // top
        statusLabel = new JLabel("Simulation", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        // bottom
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        backButton = new JButton("Back to Menu");

        startButton.setBackground(BACKGROUND_COLOR);
        stopButton.setBackground(BACKGROUND_COLOR);
        backButton.setBackground(BACKGROUND_COLOR);

        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        backButton.addActionListener(this);

        JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftButtons.setBackground(SECONDARY_COLOR);
        leftButtons.add(backButton);

        JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtons.setBackground(SECONDARY_COLOR);
        rightButtons.add(startButton);
        rightButtons.add(stopButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(SECONDARY_COLOR);
        bottomPanel.add(leftButtons, BorderLayout.WEST);
        bottomPanel.add(rightButtons, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // timer for simulation steps
        timer = new Timer(100, this);
    }

    /**
     * Checks if the cell at (x, y) is claimed.
     * 
     * @param x x coordinate of cell
     * @param y y coordinate of cell
     * @return true if the cell is claimed by a player, false if it is unclaimed
      *
     */
    public boolean isClaimed(int x, int y) {
        return cells[x][y] != -1;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COLS * CELL_SIZE, ROWS * CELL_SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw cells
        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                int id = cells[x][y];
                if (id == -1) {
                    g.setColor(BACKGROUND_COLOR);
                } else {
                    g.setColor(players.get(id).getColor());
                }
                g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

        // Draw grid lines
        g.setColor(new Color(0, 0, 0, 40));
        for (int x = 0; x <= COLS; x++)
            g.drawLine(x * CELL_SIZE, 0, x * CELL_SIZE, ROWS * CELL_SIZE);
        for (int y = 0; y <= ROWS; y++)
            g.drawLine(0, y * CELL_SIZE, COLS * CELL_SIZE, y * CELL_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

    }
}