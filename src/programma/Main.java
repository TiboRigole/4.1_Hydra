package programma;

import programma.Tak;

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
