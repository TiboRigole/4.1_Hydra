package programma;

public class HydraBoom {
    private Tak wortel;

    static Tak returnTak;

    public HydraBoom() {}


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

        System.out.println(sb.toString());
        return sb.toString();
    }

    public Tak getTak(char takId){



        for(int i=0; i<wortel.getAantalTakken(); i++){
            System.out.println("nieuwe tak vanuit wortel gevisit");
            visitTak(wortel.getTakOpPlek(i), takId);
        }

        System.out.println("is dit nu de tak die we wouden?");
        System.out.println(returnTak.getTakId());
        return returnTak;

    }

    public void visitTak(Tak tak, char takId){

        if(tak.getTakId() == takId){
            //deze moeten we hebben
            System.out.println("tak met "+takId + " gevonden!!");
            returnTak = tak;

        }

        for(int i=0; i<tak.getAantalTakken(); i++){
            visitTak(tak.getTakOpPlek(i), takId);
        }



    }

}