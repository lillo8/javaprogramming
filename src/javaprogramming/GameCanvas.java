package javaprogramming;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * In this canvas everything is painted!
 *
 */
public class GameCanvas extends JPanel {

	private static final long serialVersionUID = 4037578664698380201L;
	private final GameManager gameManager;
	int w1, w2, h1, h2;

	public GameCanvas(GameManager gameManager, JLabel labelPoints) {
		setBackground(Color.WHITE);
		this.gameManager = gameManager;

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				BoardPoint clickedBP = findBoardPoint(me.getX(), me.getY());
				if (clickedBP != null && gameManager.getSign(clickedBP) == -1) {
					gameManager.addSign(clickedBP, gameManager.getWhoIsPlaying());

					if (gameManager.getWhoIsPlaying() == 1)
						gameManager.setWhoIsPlaying(2);
					else
						gameManager.setWhoIsPlaying(1);

					repaint();
					int winner = gameManager.findWinner();
					if (winner > 0) {
						JOptionPane.showMessageDialog(null, "And the winner is... " + ((winner == 1) ? "X" : "O"));
						gameManager.resetBoard();
						gameManager.addPoints(winner);
						labelPoints.setText(
								"X: " + gameManager.getPlayerOnePoints() + " - O: " + gameManager.getPlayerTwoPoints());
						repaint();
					}
					if (gameManager.getClickCounter()==9) {
						//Nobody won!
						JOptionPane.showMessageDialog(null, "It's tied!");
						gameManager.resetBoard();
						repaint();
		
					}

				} else {
					JOptionPane.showMessageDialog(null, "Wrong move!");
				}

			}

			private BoardPoint findBoardPoint(int x, int y) {
				int resultX, resultY;
				if (x < w1)
					resultX = 0;
				else if (x > w2)
					resultX = 2;
				else if (x > w1 && x < w2)
					resultX = 1;
				else
					resultX = -1;

				if (y < h1)
					resultY = 0;
				else if (y > h2)
					resultY = 2;
				else if (y > h1 && y < h2)
					resultY = 1;
				else
					resultY = -1;
				if (resultX >= 0 && resultY >= 0)
					return new BoardPoint(resultX, resultY);
				else
					return null;
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int width = this.getWidth();
		int height = this.getHeight();

		// Draw lines
		g2.drawRect(20, 10, width - 40, height - 20);
		g2.setStroke(new BasicStroke(3));
		w1 = (width) / 3 - 20;
		w2 = (width) * 2 / 3 - 20;
		h1 = height / 3;
		h2 = height * 2 / 3;

		g2.drawLine(w1, 30, w1, height - 40);
		g2.drawLine(w2, 30, w2, height - 40);
		g2.drawLine(50, h1, width - 100, h1);
		g2.drawLine(50, h2, width - 100, h2);

		// Draw signs
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int playerSign = this.gameManager.getSign(new BoardPoint(i, j));
				if (playerSign > 0) {
					int xCoordinate = w1 / 2 + i * w1;
					int yCoordinate = h1 / 2 + h1 * j;
					if (playerSign == 1) {
						g2.setColor(Color.BLUE);
						g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 80));
						g2.drawString("X", xCoordinate - 25, yCoordinate + 25);
					} else {
						g2.setColor(Color.RED);
						g2.drawOval(xCoordinate - 25, yCoordinate - 25, 50, 50);
					}

				}
			}
		}
	}

}
