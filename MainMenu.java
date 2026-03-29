import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main Menu to show the parameter options for the simulation.
 * 
 * @author Ahyaan Malik
 * @version 3/29/2026
 */
public class MainMenu implements ActionListener, Runnable {

    private static final String MENU_CARD = "Menu";
    private static final String SIMULATION_CARD = "Normal Mode";

    private CardLayout cardLayout;
    private JPanel cards;
    private JFrame frame;

    private JPanel panel;

    private JPanel buttons;

    private JLabel mainText;

    private JButton startSimulation;

    private final Color BACKGROUND_COLOR = SimulationConstants.BACKGROUND_COLOR;

    private final Color SECONDARY_COLOR = SimulationConstants.SECONDARY_COLOR;

    private static final int BOARD_DIMENSIONS = SimulationConstants.BOARD_DIMENSIONS;

    public MainMenu() {
    }

    /**
     * The run method to set up the GUI.
     */
    @Override
    public void run() {
        // Our basic GUI setup, a JFrame with a JPanel inside it.
        frame = new JFrame("Territory Control");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(BOARD_DIMENSIONS, BOARD_DIMENSIONS);
            }
        };

        panel = new JPanel(new BorderLayout());

        mainText = new JLabel("Territory Control", SwingConstants.CENTER);
        mainText.setFont(mainText.getFont().deriveFont(48.0f));
        panel.add(mainText, BorderLayout.NORTH);

        buttons = new JPanel(new FlowLayout());
        buttons.setBackground(BACKGROUND_COLOR);
        startSimulation = new JButton("Start Simulation");
        startSimulation.setBackground(SECONDARY_COLOR);
        startSimulation.addActionListener(this);
        buttons.add(startSimulation);
        panel.add(buttons, BorderLayout.CENTER);
        panel.setBackground(SECONDARY_COLOR);

        // CardLayout
        cards.add(panel, MENU_CARD);
        cards.add(new Simulator(cardLayout, cards), SIMULATION_CARD);

        cardLayout.show(cards, MENU_CARD);

        frame.add(cards);
        // Display the window we've created.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();

        if (buttonText.equals("Start Simulation")) {
            cardLayout.show(cards, SIMULATION_CARD);
        }
    }

    /**
     * The main method is responsible for creating a thread
     * that will construct and show the GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MainMenu());
    }
}
