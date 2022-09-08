package it.polito.tdp.yelp.model;

public class Adiacenza {
	
	User u1;
	User u2; 
	int sim;
	
	public Adiacenza(User u1, User u2, int sim) {
		super();
		this.u1 = u1;
		this.u2 = u2;
		this.sim = sim;
	}

	public User getU1() {
		return u1;
	}

	public void setU1(User u1) {
		this.u1 = u1;
	}

	public User getU2() {
		return u2;
	}

	public void setU2(User u2) {
		this.u2 = u2;
	}

	public int getSim() {
		return sim;
	}

	public void setSim(int sim) {
		this.sim = sim;
	}

	@Override
	public String toString() {
		return u1 + ", " + u2 + ",   GRADO=" + sim;
	}
	
	
	

}
