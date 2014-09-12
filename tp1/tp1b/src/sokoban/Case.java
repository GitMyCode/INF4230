package sokoban;

import java.util.Comparator;

/**
 * Created by MB on 9/10/2014.
 */
public class Case implements Comparable<Case>,Cloneable{


    protected int x;
    protected int y;
    protected char symbole;


    public Case( int x, int y, char symbole){
        this.x = x;
        this.y = y;
        this.symbole = symbole;
    }

    @Override
    public boolean equals(Object o) {

        Case c = (Case) o;
        if(this.x != c.x)
            return false;

        if( this.y != c.y)
            return false;

        return true;
    }

    @Override
    public int compareTo(Case aCase) {

        if(x < aCase.x ) return -1;
        if(x > aCase.x ) return +1;

        if(y < aCase.y) return -1;
        if(y > aCase.y) return +1;


        return 0;


   }

    public void applyDeplacement(String action){
        if(action=="E")
            this.y -= 1;
        if(action=="W")
            this.y += 1;
        if(action=="N")
            this.x -= 1;
        if(action=="S")
            this.x += 1;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Case result = (Case) super.clone();
        result.x = x;
        result.y = y;
        result.symbole = symbole;

        return result;
    }
}
