package ia.missionary;

public class Side {
	
	private int missionaries;
	private int cannibals;
	private boolean valid;
	
	Side() {
		missionaries = 0;
		cannibals = 0;
		checkValid();
	}
	
	Side (int x) {
		missionaries = x;
		cannibals = x;
		checkValid();
	}
	
	Side (int x, int y) {
		missionaries = x;
		cannibals = y;
		checkValid();
	}

	public int getMissionaries() {
		return missionaries;
	}

	public int getCannibals() {
		return cannibals;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	private void checkValid() {
		if(cannibals > missionaries && missionaries > 0) valid = false;
		else valid = true;
	}
	
}