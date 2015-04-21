package ia.missionary;

import java.util.ArrayList;

public class State {

	private Side start;
	private Side end;
	private boolean gameOv;
	private boolean valid;
	private boolean going;
	
	State() {
		start = new Side(3);
		end = new Side();
		going = true;
		checkValid();
		checkGameOver();
		printState();
	}
	
	State(State s) {
		start = s.start;
		end = s.end;
		going = !s.going;
		checkValid();
		checkGameOver();
		printState();
	}
	
	State(State s, Comp c, Pos p) {
		int sM = 0;
		int eM = 0;
		int sC = 0;
		int eC = 0;
		int inv;
		
		if (p == Pos.START) inv = 1;
		else inv = -1;
		
		switch (c) {
			case M1:
				eM = 1;
				sM = -1;
				break;
			case C1:
				eC = 1;
				sC = -1;
				break;
			case M1C1:
				eM = 1;
				sM = -1;
				eC = 1;
				sC = -1;
				break;
			case M2:
				eM = 2;
				sM = -2;
				break;
			case C2:
				eC = 2;
				sC = -2;
				break;
			default:
				System.out.println("Error");
				break;
		}
		
		sM *= inv;
		eM *= inv;
		sC *= inv;
		eC *= inv;
		
		start = new Side(s.start.getMissionaries() + sM, s.start.getCannibals() + sC);
		end = new Side(s.end.getMissionaries() + eM, s.end.getCannibals() + eC);;
		going = !s.going;
		checkValid();
		checkGameOver();
		printState();
	}
	
	public boolean gameOver() {
		return gameOv;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	private void checkValid() {
		if (!start.isValid()
				|| !end.isValid() ) valid = false;
		else valid = true;
	}
	
	public void printState() {
		System.out.println("-State-");
		System.out.println("Valid: " + isValid());
		System.out.println("GameOver: " + gameOver());
		System.out.println("===Start===");
		System.out.println("Missionaries: " + start.getMissionaries());
		System.out.println("Cannibals: " + start.getCannibals());
		System.out.println("-----------------------------");
		System.out.println("===End===");
		System.out.println("Missionaries: " + end.getMissionaries());
		System.out.println("Cannibals: " + end.getCannibals());
		System.out.println("-----------------------------");
	}
	
	public ArrayList<State> genStates() {
		ArrayList<State> nStates = new ArrayList<State>();
		Side active;
		if (going) {
			active = start;
			if(active.getMissionaries() > 0) {
				nStates.add(new State(this, Comp.M1, Pos.START));
				if(active.getMissionaries() > 1) nStates.add(new State(this, Comp.M2, Pos.START));
			}
			if(active.getCannibals() > 0) {
				nStates.add(new State(this, Comp.C1, Pos.START));
				if(active.getCannibals() > 1) nStates.add(new State(this, Comp.C2, Pos.START));
			}
			if(active.getCannibals() > 0 && active.getMissionaries() > 0) 
				nStates.add(new State(this, Comp.M1C1, Pos.START));
		}
		else {
			active = end;
			if(active.getMissionaries() > 0) {
				nStates.add(new State(this, Comp.M1, Pos.END));
				if(active.getMissionaries() > 1) nStates.add(new State(this, Comp.M2, Pos.END));
			}
			if(active.getCannibals() > 0) {
				nStates.add(new State(this, Comp.C1, Pos.END));
				if(active.getCannibals() > 1) nStates.add(new State(this, Comp.C2, Pos.END));
			}
			if(active.getCannibals() > 0 && active.getMissionaries() > 0) 
				nStates.add(new State(this, Comp.M1C1, Pos.END));
		}
		return nStates;
	}
	
	private void checkGameOver() {
		if (end.getMissionaries() == 3 && end.getCannibals() == 3) gameOv = true;
		else gameOv = false;
	}
	
}
