
public class Data {
    
    String city; //ulkomainen kaupunki
    String kaupunki; //kotimainen
    String kuva; //random kuva
    String pvm; //päivämäärä
    String kk; //kuukausi
    String vuosi;
    String num; //luku 1-100
    String luukku; //kalenteriluukun numero
    String avattu;
    
    public Data(String c, String k, String i, String p, String ku, String v, String n, String l, String a) {
        city = c;
        kaupunki = k;
        kuva = i;
        pvm = p;
        kk = ku;
        vuosi = v;
        num = n;
        luukku = l;
        avattu = a;
    }
}
