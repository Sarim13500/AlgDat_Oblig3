package no.oslomet.cs.algdat.Oblig3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstrebarn, høyrebarn;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstrebarn = v;
            høyrebarn = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstrebarn;
            else if (cmp > 0) p = p.høyrebarn;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi, "ikke lov med null verdi");

        Node<T> p = rot;                          // p starter i roten
        Node<T> q = null;
        int variabel = 0;                             // introduserer en hjelpevariabel

        while (p != null)
        {
            q = p;                                      //q er p sin forrige
            variabel = comp.compare(verdi,p.verdi);     // sammenligner verdi med p sin verdi

            if (variabel<0){                            //hvis verdi er mindre enn p sin verdi
                p=p.venstrebarn;
            }
            else {                                      //hvis verdi er større eller lik p sin verdi
                p=p.høyrebarn;
            }
        }

        p = new Node<>(verdi, q); // oppretter en ny node med verdi=verdi og forelder=q

        if (q == null) {
            rot = p;                  // rotnoden
        }
        else if (variabel < 0) {
            q.venstrebarn = p;         // setter venstrebarn
        }
        else {
            q.høyrebarn = p;                  // setter høyrebarn
        }

        antall++;
        return true;
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {

        int teller = 0;

        if (!inneholder(verdi)){
            return teller;
        }

        Node<T> p = rot;
        while (p != null){

            int cmp = comp.compare(verdi,p.verdi);
            if (p.verdi.equals(verdi)){
                teller++;
            }
            if (cmp<0){
                p=p.venstrebarn;
            }
            else {
                p=p.høyrebarn;
            }

        }


return teller;
        //throw new UnsupportedOperationException("Ikke kodet ennå!");


    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {


        Objects.requireNonNull(p); //Sjekker om p er Null

        while(true){
            if(p.venstrebarn != null){
                p=p.venstrebarn;        //Venstrebarn til p
            }else if(p.høyrebarn != null){
                p=p.høyrebarn;          //Høyrebarn til p
            }
            else{
                return p;
            }
        }
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {


        Node<T> parent=p.forelder;          //oppretter p sin foreldereNode

        if(parent == null){
            return null;                    //Hvis parent er null så returneres null
        }

        if(parent.høyrebarn==p || parent.høyrebarn==null){
            return parent; //returnerer parent hvis parent sin høyre er p eller parent sin høyre er null
        }
        else{
            return førstePostorden(parent.høyrebarn); // kaller på førstepostorden
        }

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postorden(Oppgave<? super T> oppgave) {


        Node<T> p= rot;                                 //setter p til å være roten

        Node<T> forst = førstePostorden(p);             // kaller på førstePostorden for p

        oppgave.utførOppgave(forst.verdi);
        while (forst.forelder != null){
            forst= nestePostorden(forst);
            oppgave.utførOppgave(Objects.requireNonNull(forst.verdi));
        }




        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {

        postordenRecursive(rot, oppgave);


    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {



        if (p==null){
            return;                              //returnerer hvis p er null
        }

        postordenRecursive(p.venstrebarn, oppgave);                 //rekursivt kall for p sitt venstrebarn

        postordenRecursive(p.høyrebarn,oppgave);                    //rekursivt kall for p sitt høyrebarn

        oppgave.utførOppgave(p.verdi);


        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
