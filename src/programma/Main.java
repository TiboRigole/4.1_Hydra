package programma;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main (String[] args){
    	Scanner sc = new Scanner(System.in);

		//

		//// testing van dingen, moet weg in de main


		//

		int aantalHydraBomen = Integer.parseInt(sc.nextLine());
    	
    	int aantalTakken;

    	HydraBoom hydraBoom = new HydraBoom();

    	//voor elke hydraboom
    	for(int hydraId = 0; hydraId<aantalHydraBomen ; hydraId++){
    		hydraBoom.reset();

    		//lezen van de input
    		aantalTakken = Integer.parseInt(sc.nextLine());

    		//lezen van de stam
    		hydraBoom.leesStam(sc.nextLine());


    		for(int i=1; i<aantalTakken ; i++){

    			String tak = sc.nextLine();
				tak = tak.replaceAll("\\s","");

    			char takId = tak.charAt(0);

				Tak takAanTePassen = hydraBoom.getTak(takId);


    			//zolang we een getal inlezen
    			StringBuilder sb = new StringBuilder();
				int plek = 1;

				while(Character.isDigit(tak.charAt(plek))) {
					sb.append(tak.charAt(plek));
					plek++;
				}

				//in sb.toString zit het aantalTakjes

				//inlezen van de takjes
				for(int x=plek ; x<tak.length(); x++){
					char waarde = tak.charAt(x);

					takAanTePassen.addTak(waarde);
				}

			}//einde van de input



    		//en nu éh
			//proberen de letters niet meer mee te laten tellen
			//gewoon tot het einde gaan
			//per verbinding aan de stam werken?
			//misschien wel een goed idee
			int aantalKnips = 0;

			boolean klaar = false;

			while(!klaar){
			    hydraBoom.knip();
			    aantalKnips++;


			    klaar = hydraBoom.checkIfKlaar();
            }


            System.out.println(aantalKnips);

    	}//einde verwerking van 1 hydraboom






    }



}






class HydraBoom {
	private Tak wortel;

	static Tak returnTak;


	static Tak vader;



	public void leesStam(String stam) {
		this.wortel = new Tak('Z');
		String takken = this.getTakkenVanWortel(stam);

		for(int i = 0; i < takken.length(); ++i) {
			this.wortel.addTak(takken.charAt(i));
		}

	}

	public void reset() {
		this.wortel = new Tak('Z');
	}


	//input converteer methode
	private String getTakkenVanWortel(String tak) {
		StringBuilder sb = new StringBuilder();
		boolean weZittenAanGetal = false;
		boolean weZittenOverGetal = false;

		for(int i = 0; i < tak.length(); ++i) {
			char karakter = tak.charAt(i);
			if (!weZittenAanGetal && Character.isDigit(karakter)) {
				weZittenAanGetal = true;
			}

			if (weZittenAanGetal && !weZittenOverGetal && !Character.isDigit(karakter)) {
				weZittenOverGetal = true;
			}

			if (weZittenOverGetal && Character.isLetter(karakter)) {
				sb.append(karakter);
			}

			if (weZittenOverGetal && karakter == '*'){
				sb.append('*');
			}
		}


		return sb.toString();
	}

	public Tak getTak(char takId){



		for(int i=0; i<wortel.getAantalTakken(); i++){

			visitTak(wortel.getTakOpPlek(i), takId);
		}


		return returnTak;

	}

	public void visitTak(Tak tak, char takId){

		if(tak.getTakId() == takId){
			//deze moeten we hebben

			returnTak = tak;

		}

		for(int i=0; i<tak.getAantalTakken(); i++){
			visitTak(tak.getTakOpPlek(i), takId);
		}



	}

	public int getAantalHoofdTakken(){
		return wortel.getAantalTakken();
	}

	public boolean checkIfKlaar() {
		return wortel.getAantalTakken()==0;
	}

	public void knip() {

		Tak oudeTak = wortel.getTakOpPlek(0);

		//als de tak enkel een sterretje bevat, en geen subtakken
		if(oudeTak.getAantalTakken()==0){
			wortel.removeTakOpPlek(0);
		}
		else{
			vader = oudeTak;
			knipRecursief(false, oudeTak.getTakOpPlek(0));
			//graak naar de tak met lege ArrayList
			//ga terug naar zijn vader
			//remove0 van zijn vader
			Tak geknipteTak = new Tak(wortel.getTakOpPlek(0));
			wortel.addTak(new Tak(geknipteTak));

		}
	}

	private void knipRecursief(boolean alGeknipt, Tak oudeTak) {
		//boolean is als er al geknipt is, begint op false

		if(!alGeknipt && oudeTak.getAantalTakken()==0){
			vader.removeTakOpPlek(0);

			alGeknipt = true;

		}
		if(!alGeknipt && oudeTak.getAantalTakken()!=0){
			vader = oudeTak;
			knipRecursief(false, oudeTak.getTakOpPlek(0));
		}
		if(alGeknipt){
			//doe niets meer
		}
	}

}


class Tak {
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



}

