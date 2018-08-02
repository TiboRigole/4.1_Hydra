package programma;

import java.util.ArrayList;

public class Tak {
	private char takId;

	private ArrayList<Tak> takkenLijst;


	public Tak(){
	    takId = '0';
	    takkenLijst = new ArrayList<Tak>();
    }

	//constructor
	public Tak(char takId){
		this.takId = takId;
		takkenLijst = new ArrayList<Tak>();
	}

	//copyconstructor
	public Tak(Tak tak){
		this.takId = tak.getTakId();
		this.takkenLijst = new ArrayList<Tak>();
		for(Tak t : tak.takkenLijst){
			this.takkenLijst.add(new Tak(t));
		}
	}

	//getters
	public char getTakId(){return takId;}

	public ArrayList<Tak> getTakkenLijst(){
		return takkenLijst;
	}

	public void addTak(char c) {
		takkenLijst.add(new Tak(c));
	}

	public void addTak(Tak t){
		takkenLijst.add(t);
	}

	public int getAantalTakken(){
		return takkenLijst.size();
	}

	public Tak getTakOpPlek(int i){
		return takkenLijst.get(i);
	}


	public void removeTakOpPlek(int i) {
		takkenLijst.remove(i);
	}

	public void knip() {

	}


}