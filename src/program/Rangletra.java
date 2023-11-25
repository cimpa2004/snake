package program;

public class Rangletra{
    public Rangletra(String n, Integer p){
        nev = n;
        pontszam = p;
    }
    public String nev;
    public Integer pontszam;

    @Override
    public boolean equals(Object o){
        Rangletra temp = (Rangletra) o;
        if (temp.nev.equals(this.nev) && temp.pontszam.equals(this.pontszam))
            return true;
        return false;
    }
}
