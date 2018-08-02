package programma;

public class HydraBoom {
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
