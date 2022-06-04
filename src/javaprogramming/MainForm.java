package javaprogramming;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Form of the game, it creates the user interface and instantiate the game
 * manager
 *
 */
public class MainForm extends JFrame {

	private static final long serialVersionUID = -7948179213219311352L;
	private JLabel labelPoints;
	private GameCanvas gameCanvas;
	private GameManager gameManager;
	public final static int WIDTH = 800;
	public final static int HEIGHT = 600;

	public MainForm() {
		super("Tic Tac Toe");
		setLayout(new BorderLayout());
		gameManager = new GameManager();

		// User interface
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		JButton buttonNewGame = new JButton("Reset Game");
		this.labelPoints = new JLabel(
				"X: " + gameManager.getPlayerOnePoints() + " - O: " + gameManager.getPlayerTwoPoints());
		northPanel.add(buttonNewGame);
		northPanel.add(this.labelPoints);
		buttonNewGame.addActionListener(e -> {
			this.gameManager.resetBoard();
			this.gameManager.resetScore();
			labelPoints.setText("X: " + gameManager.getPlayerOnePoints() + " - O: " + gameManager.getPlayerTwoPoints());
			this.gameCanvas.repaint();
		});

		// Game
		this.gameCanvas = new GameCanvas(gameManager, labelPoints);

		// Form
		add(northPanel, BorderLayout.NORTH);
		add(this.gameCanvas, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		setPreferredSize(new Dimension(800, 600));
		setResizable(false);
		pack();
		setVisible(true);
	}

}
