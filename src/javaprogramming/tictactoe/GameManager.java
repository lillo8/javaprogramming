package javaprogramming.tictactoe;

import java.util.Hashtable;

/**
 * Holds all data and game logics
 * @author Dario
 *
 */
public class GameManager {

	private int whoIsPlaying, clickCounter;
	private Hashtable<BoardPoint, Integer> board;
	private int playerOnePoints, playerTwoPoints;

	public int getWhoIsPlaying() {
		return whoIsPlaying;
	}

	public void setWhoIsPlaying(int whoIsPlaying) {
		this.whoIsPlaying = whoIsPlaying;
	}

	public int getPlayerOnePoints() {
		return playerOnePoints;
	}

	public int getPlayerTwoPoints() {
		return playerTwoPoints;
	}

	public void addPoints(int player) {
		if (player == 1) this.playerOnePoints++;
		if (player == 2) this.playerTwoPoints++;
	}
	public boolean addSign(BoardPoint point, int player) {
		if (board.containsKey(point)) {
			return false;
		} else {
			board.put(point, player);
			clickCounter++;
			return true;
		}
	}
	public void addClickCounter() {
		this.clickCounter++;
	}

	public int getSign(BoardPoint point) {
		if (board.containsKey(point)) {
			return board.get(point);
		} else {
			return -1;
		}
	}

	public GameManager() {
		whoIsPlaying = 1;
		board = new Hashtable<BoardPoint, Integer>();
		playerOnePoints = 0;
		playerTwoPoints = 0;
	}

	public void resetBoard() {
		board.clear();
		this.clickCounter = 0;
	}
	
	public void resetScore() {
		this.clickCounter = 0;
		this.playerOnePoints = 0;
		this.playerTwoPoints = 0;
	}

	public int findWinner() {
		int[][][] wins = { 
				{ { 0, 0 }, { 1, 0 }, { 2, 0 } }, 
				{ { 0, 1 }, { 1, 1 }, { 2, 1 } },
				{ { 0, 2 }, { 1, 2 }, { 2, 2 } }, 
				{ { 0, 0 }, { 0, 1 }, { 0, 2 } }, 
				{ { 1, 0 }, { 1, 1 }, { 1, 2 } },
				{ { 2, 0 }, { 2, 1 }, { 2, 2 } }, 
				{ { 0, 0 }, { 1, 1 }, { 2, 2 } }, 
				{ { 2, 0 }, { 1, 1 }, { 0, 2 } } 
				};		
		for (int i = 0; i < wins.length; i++) {
			BoardPoint p1 = new BoardPoint(wins[i][0][0], wins[i][0][1]);
			BoardPoint p2 = new BoardPoint(wins[i][1][0], wins[i][1][1]);
			BoardPoint p3 = new BoardPoint(wins[i][2][0], wins[i][2][1]);

			if (getSign(p1) > 0 && getSign(p2) > 0 && getSign(p3) > 0 && getSign(p1) == getSign(p2)
					&& getSign(p2) == getSign(p3)) {
				return getSign(p1);
			}
		}
		return -1;//No winners
	}

	public int getClickCounter() {
		return clickCounter;
	}
}
