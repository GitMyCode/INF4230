package connect5.ia;

import connect5.Grille;
import connect5.Joueur;
import connect5.Position;
import connect5.ia.JoueurArtificiel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MB on 10/6/2014.
 */
public class MyTest {

    static int nbcol = 12;
    static int nbligne = 12;

    public static int[] testByte(){

        String to_convert =
        "000000000000" +
        "000000000000" +
        "0000N0000000" +
        "000000000000" +
        "000000B00000" +
        "00000BNB0000" +
        "0000NBN0B000" +
        "0000NN000B00" +
        "0000N0B00000" +
        "000000000000" +
        "000000000000" +
        "000000000000";

        String test2 =
              "" +
                      "-------B----" +
                      "--B----N_N--" +
                      "---N-N-BBBN-" +
                      "----N-NBB---" +
                      "---NBNBBBNB-" +
                      "----NBNN----" +
                      "---BNBNB----" +
                      "--N---------" +
                      "------------" +
                      "------------" +
                      "------------" +
                      "------------";

        //"132.208.137.66";
      String f=
              "-----------" +
                      "-----------" +
                      "--------B--" +
                      "-------N---" +
                      "---BNNN----" +
                      "----BNBN---" +
                      "----NNBN---" +
                      "---BNBBB---" +
                      "----NB-----" +
                      "----B-N----" +
                      "---B-------" +
                      "--N--------" +
                      "-----------" +
                      "-----------";

      String f2= "-----------" +
              "-----------" +
              "----N-N----" +
              "--NNBBB-BB-" +
              "---B-NNBN--" +
              "----BNNNNB-" +
              "-----BNNN--" +
              "-----NBNNB-" +
              "----BNBNBB-" +
              "----N-BB---" +
              "----BB-----" +
              "------N----" +
              "-----------" +
              "-----------";

      String testMoreThan5=
                "-------------\n" +
                "-------------\n" +
                "-------------\n" +
                "-----N-B-----\n" +
                "-----N-B-----\n" +
                "-----N-B-----\n" +
                "-------------\n" +
                "-----N-B-----\n" +
                "-----N-B-----\n" +
                "-----N-------\n" +
                "-------------";
        Map<Character,Byte> convert = new HashMap<Character,Byte>();
        convert.put('0',(byte)0);
        convert.put('_',(byte)0);
        convert.put('-',(byte)0);
        convert.put('N',(byte)1);
        convert.put('B',(byte)2);


        char[] table = {'_', 'N', 'B' };

        int[] to_test = new int[nbcol * nbligne];

        for(int i=0; i< to_test.length; i++){
            to_test[i] = convert.get(f2.charAt(i)) -1;
        }
        return to_test;
    }


    public static void main(String args[]){

        Joueur joueur = new JoueurArtificiel();

        // Test B1
        System.out.println("Test #1");
        nbligne = 14; nbcol = 11;
        Grille g = new Grille(nbligne,nbcol,testByte());
        Position coup = joueur.getProchainCoup(g, 2000);
        System.out.println((coup.ligne==4 && coup.colonne==0) ? "Réussi": "Échoué");


        // Test B2
        /*g = new Grille(5, 7);
        for(int i=0;i<4;i++){
            g.set(0, 2+i, 1);
            g.set(4, 2+i, 2);
        }*/
        coup = joueur.getProchainCoup(g, 20000);
        System.out.println("Test #1");
        System.out.println((coup.ligne==0 && (coup.colonne==1||coup.colonne==6)) ? "Réussi": "Échoué");

    }
}
