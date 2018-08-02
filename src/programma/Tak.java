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

	//getters
	public char getTakId(){return takId;}


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