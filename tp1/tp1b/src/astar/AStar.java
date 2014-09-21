/* INF4230 - Intelligence artificielle
 * UQAM / Département d'informatique
 * Automne 2014 / TP1 - Algorithme A*
 * http://ericbeaudry.ca/INF4230/tp1/
 */
package astar;

import java.text.NumberFormat;
import java.util.*;

public class AStar {

    public static TreeSet<Etat> open, close;
    public static TreeSet<Etat> open2;


    public static Map<Etat,Etat> open_map;
    public static Map<Etat,Etat> open_map2;

    public static Etat last_visited;

    public static List<Action> genererPlan(Monde monde, Etat etatInitial, But but, Heuristique heuristique){
        long starttime = System.currentTimeMillis();

        // À Compléter.
        // Implémentez l'algorithme A* ici.


        LinkedHashMap<Etat,Etat> test2 = new LinkedHashMap<Etat, Etat>();

        open_map = new TreeMap<Etat, Etat>();
        open_map2 = new TreeMap<Etat, Etat>();
        open = new TreeSet<Etat>(new Comparator<Etat>(){
            public int compare(Etat a, Etat b){

                if(a.f < b.f){
                    return 1;
                }
                if( a.f > b.f){
                    return -1;
                }

                if(a.equals(b)){
                    return 0;
                }
                return a.compareTo(b);


            }
        });
        open2 = new TreeSet<Etat>(new Comparator<Etat>(){
            public int compare(Etat a, Etat b){

                if(a.f < b.f){
                    return 1;
                }
                if( a.f > b.f){
                    return -1;
                }

                if(a.equals(b)){
                    return 0;
                }
                return a.compareTo(b);


            }
        });


  //      open = new TreeSet<Etat>();
/*        close = new TreeSet<Etat>(new Comparator<Etat>(){
            public int compare(Etat a, Etat b){
                int cmp = a.compareTo(b);
                if(cmp != 0){
                    if(a.f < b.f){
                        return -1;
                    }
                    if( a.f > b.f){
                        return 1;
                    }

                }
                return cmp;

            }
        });*/


//        open = new TreeSet<Etat>();
        close = new TreeSet<Etat>();



        List<Action> plan = new LinkedList<Action>();

        //int key = etatInitial.hashCode();


        int etat_generer=0;
        int nb_visite = 0;

        Etat arrive = etatInitial;
        last_visited = etatInitial;
        open2.add(etatInitial);
        open_map2.put(etatInitial,etatInitial);

        int deep_limit = 400;
        boolean but_statisfait = false;
        while (  !(but_statisfait || open2.isEmpty()) ){
            deep_limit = (int) Math.pow((deep_limit ),1.25);

            System.out.println("deep: "+deep_limit +" nb visite: "+nb_visite);

            open.addAll(open2);
            open_map.putAll(open_map2);

            close.removeAll(open);
           // close.clear();
            open_map2.clear();
            open2.clear();


            while(open.size() > 0){
                nb_visite++;
                Etat etat_init = open.last();


                if(but.butSatisfait(etat_init)){
                    arrive = etat_init;
                    but_statisfait = true;
                    break;
                }





                int si_open = open.size();
                if(!open.remove(etat_init)){
                    System.out.println("falsde");
                }
                if(si_open != open.size()+1){
                    System.out.println("problem");
                }

                open_map.remove(etat_init);


                if(open.size() != open_map.size()){
                    System.out.println("ope");
                }
                close.add(etat_init);

                if(deep_limit < etat_init.g){
                    open2.add(etat_init);
                    open_map2.put(etat_init,etat_init);
                    continue;
                }

                voisins(etat_init,monde,etatInitial,but,heuristique,deep_limit);




            }
        }




        // Étapes suggérées :
        //  - Restez simple.
        //  - Ajoutez : TreeSet<Etat> open, close;.
        //  - Ajoutez etatInitial dans open.
        //  - Numérotez les itérations.
        //  - Pour chaque itération :
        //  --  Affichez le numéro d'itération.
        //  --  Faites une boucles qui itère tous les états e dans open pour trouver celui avec e.f minimal.
        //  --  Affichez l'état e sélectionné (les e.f affichés devraient croître);
        //  --  Vérifiez si l'état e satisfait le but. 
        //  ---   Si oui, sortez du while.
        //  ---   Une autre boucle remonte les pointeurs parents.
        //  --  Générez les successeurs de e.
        //  --  Pour chaque état successeur s de e:
        //  ---   Vérifiez si s.etat est dans closed.
        //  ---   Calculez s.etat.g = e.g + s.cout.
        //  ---   Vérifiez si s.etat existe dans open.
        //  ----    Si s.etat est déjà dans open, vérifiez son .f.
        //  ---   Ajoutez s.etat dans open si nécessaire.
        //  - Exécutez le programme sur un problème très simple.
        //  --  Vérifiez le bon fonctionnement de la génération des états.
        //  --  Vérifiez que e.f soit croissant (>=).
        //  - Une fois que l'algorithme :
        //  -- Ajoutez un TreeSet<Etat> open2 avec un comparateur basé sur f.
        //  -- Évaluez la pertinence d'un PriorityQueue.
        //  - Commentez les lignes propres au déboggage.

        // Un plan est une séquence (liste) d'actions.
        Etat pas = arrive;
        while(pas.actionDepuisParent != null){
            plan.add(pas.actionDepuisParent);
            pas= pas.parent;
        }

        Collections.reverse(plan);

        etat_generer = open.size()+ close.size();

        long lastDuration = System.currentTimeMillis() - starttime;
        // Les lignes écrites débutant par un dièse '#' seront ignorées par le valideur de solution.
        System.out.println("# Nombre d'états générés : " + etat_generer);
        System.out.println("# Nombre d'états visités : " + nb_visite);
        System.out.println("# Durée : " + lastDuration + " ms");
        System.out.println("# Coût : " + nf.format(arrive.g));
        return plan;
    }




    private static void voisins(Etat current, Monde monde, Etat etatInitial, But but, Heuristique heuristique,int deep_limit){

        List<Action> action_voisin = monde.getActions(current);


        for( Action a : action_voisin ){
            Etat voisin = monde.executer(current,a);
            if( !close.contains(voisin)){

                double newG = a.cout + current.g;


                Etat open_voisin = null;

                open_voisin = open_map.get(voisin);


                if( open_voisin==null || newG < open_voisin.g ){


                    if(open_voisin !=null){
                        open.remove(open_voisin);
                    }
                    voisin = (open_voisin == null) ? voisin : open_voisin;

                    voisin.parent = current;
                    voisin.actionDepuisParent = a;
                    voisin.h = heuristique.estimerCoutRestant(voisin,but);
                    voisin.g = newG;
                    voisin.f = voisin.g + voisin.h;


                    if(open_voisin == null){
                        open_map.put(voisin,voisin);
                    }
                    open.add(voisin);


                }
            }
        }



    }

    static Etat getEtat(TreeSet<Etat> treeSet, Etat equivalent){

        for(Etat e: treeSet){
            if( e.equals(equivalent)){
                return  e;
            }
        }
        return null;
    }

    static final NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
    static {
        nf.setMaximumFractionDigits(1);
    }
}
