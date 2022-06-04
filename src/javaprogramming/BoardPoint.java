package javaprogramming;

/**
 * Utility class to identify the position
 * of a sign and the sign itself
 *
 */
public class BoardPoint {
	
	private int x,y;

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		return (String.valueOf(x) + String.valueOf(y)).hashCode();  
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) 
			return false;
		BoardPoint other = (BoardPoint) obj;
		return (this.x == other.x) && (this.y == other.y);  
	}
	
	public BoardPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
