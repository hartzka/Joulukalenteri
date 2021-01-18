
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.swing.SwingUtilities;

public class Joulukalenteri {

    private UI ui;
    private List<Data> datat;
    private List<Data> inUse;
    private Reader reader;
    private Saver saver;
    private HashMap<Integer, Data> luukut;

    public Joulukalenteri() throws Throwable {
        this.reader = new Reader();
        this.saver = new Saver();

        //addData();
        this.inUse = reader.lueData("vanhat.data");
        this.datat = reader.lueData("vapaat.data");
        luukut = new HashMap<>();
        updateLuukut();
        this.ui = new UI(this, luukut);
        SwingUtilities.invokeLater(ui);

    }

    private void updateLuukut() throws Throwable {
        LocalDateTime lo = LocalDateTime.now();
        int kk = lo.getMonthValue();
        int y = lo.getYear();
        int pv = lo.getDayOfMonth();
        System.out.println(lo);

        if (kk == 12 && y == 2020 && pv <= 24) {
            for (Data d : inUse) {
                int luukku = Integer.parseInt(d.luukku);
                luukut.put(luukku, d);
            }
            for (int i = 1; i <= pv; i++) {
                if (!luukut.containsKey(i)) {
                    //arvo uusi data
                    Random r = new Random();
                    String city, kaupunki, kuva;
                    Data d = new Data("", "", "", "", "", "", "", "", "");
                    Data ud;
                    while (true) {
                        ud = datat.get(r.nextInt(datat.size()));
                        city = ud.city;
                        if (notInUse(city)) {
                            break;
                        }
                    }
                    d.city = city;
                    while (true) {
                        ud = datat.get(r.nextInt(datat.size()));
                        kaupunki = ud.kaupunki;
                        if (notInUse(kaupunki)) {
                            break;
                        }
                    }
                    d.kaupunki = kaupunki;
                    while (true) {
                        ud = datat.get(r.nextInt(datat.size()));
                        kuva = ud.kuva;
                        if (notInUse(kuva)) {
                            break;
                        }
                    }
                    d.kuva = kuva;
                    int p, k, v, l;
                    while (true) {
                        p = r.nextInt(31) + 1;
                        k = r.nextInt(12) + 1;
                        v = r.nextInt(21) + 1998;
                        l = r.nextInt(101);
                        if ((p > 28 && k == 2) || (p > 30 && (k == 4 || k == 6 || k == 9 || k == 11))) {
                            break;
                        }
                        boolean found = false;
                        for (Data va : inUse) {
                            if ((Integer.parseInt(va.num) == l) || (Integer.parseInt(va.vuosi) == v && Integer.parseInt(va.pvm) == p && Integer.parseInt(va.kk) == k)) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            break;
                        }
                    }
                    d.kk = Integer.toString(k);
                    d.pvm = Integer.toString(p);
                    d.luukku = Integer.toString(i);
                    d.num = Integer.toString(l);
                    d.vuosi = Integer.toString(v);
                    inUse.add(d);
                    luukut.put(i, d);
                }
            }
            saver.tallennaData(inUse, "vanhat.data");
        }
        for (int i = 1; i <= 24; i++) {
            if (!luukut.containsKey(i)) {
                Data d = new Data("", "", "", "", "", "", "-1", "", "");
                luukut.put(i, d);
            }
        }
    }

    public void setAvattu(int num) throws Throwable {
        for (Data d : inUse) {
            if (Integer.parseInt(d.luukku) == num) {
                d.avattu = "1";
                saver.updateData(d.city, d, "vanhat.data");
                break;
            }
        }
    }

    private boolean notInUse(String s) {
        for (Data a : inUse) {
            if (a.city.equals(s) || a.kaupunki.equals(s) || a.kuva.equals(s)) {
                return false;
            }
        }
        return true;
    }

    private void addData() throws Throwable {
        List<Data> lista = new ArrayList<>();
        lista.add(new Data("Chongqing", "Helsinki", "images/1.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Shanghai", "Espoo", "images/2.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Delhi", "Tampere", "images/3.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Beijing", "Vantaa", "images/4.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Dhaka", "Oulu", "images/5.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Mumbai", "Turku", "images/6.png", "0", "", "0", "0", "0", "0"));
        lista.add(new Data("Lagos", "Jyvaskyla", "images/7.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Chengdu", "Lahti", "images/8.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Karachi", "Kuopio", "images/9.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Guangzhou", "Pori", "images/10.png", "0", "0", "0", "0", "0", "0"));

        lista.add(new Data("Istanbul", "Kouvola", "images/11.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Tokyo", "Joensuu", "images/12.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Tianjin", "L.ranta", "images/13.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Moskova", "H.linna", "images/14.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Sao Paulo", "Vaasa", "images/15.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Kinshasa", "Seinajoki", "images/16.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Baoding", "Rovaniemi", "images/17.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Lahore", "Mikkeli", "images/18.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Kairo", "Kotka", "images/19.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Soul", "Salo", "images/20.png", "0", "0", "0", "0", "0", "0"));

        lista.add(new Data("Jakarta", "Porvoo", "images/21.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Wenzhou", "Kokkola", "images/22.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("MexicoC", "Hyvinkaa", "images/23.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Lima", "Lohja", "images/24.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Lontoo", "Jarvenpaa", "images/25.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Bangkok", "Nurmijarvi", "images/26.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Xian", "Rauma", "images/27.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Chennai", "K.nummi", "images/28.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Bangalore", "Tuusula", "images/29.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("New York", "Kajaani", "images/30.png", "0", "0", "0", "0", "0", "0"));

        lista.add(new Data("HoChiMinh", "Kerava", "images/31.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Hyderabad", "Savonlinna", "images/32.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Shenzhen", "Nokia", "images/33.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Suzhou", "Kaarina", "images/34.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Nanjing", "Ylojarvi", "images/35.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Dongguan", "Kangasala", "images/36.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Teheran", "Vihti", "images/37.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Quanzhou", "Riihimaki", "images/38.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Shenyang", "Raasepori", "images/39.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Bogota", "Imatra", "images/40.png", "0", "0", "0", "0", "0", "0"));

        lista.add(new Data("Hong Kong", "Raahe", "images/41.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Fuzhou", "Sastamala", "images/42.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Bagdad", "Raisio", "images/43.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Changsha", "Hollola", "images/44.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Wuhan", "Lempaala", "images/45.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Hanoi", "Tornio", "images/46.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Rio de J", "Siil.jarvi", "images/47.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Qingdao", "Iisalmi", "images/48.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Foshan", "Kemi", "images/49.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Zunyi", "V.koski", "images/50.png", "0", "0", "0", "0", "0", "0"));

        lista.add(new Data("Santiago", "Kurikka", "images/51.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Riyadh", "Varkaus", "images/52.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Ahmedabad", "Jamsa", "images/53.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Singapore", "Mantsala", "images/54.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Shantou", "Hamina", "images/55.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Ankara", "Sipoo", "images/56.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Yangon", "Lieto", "images/57.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Pietari", "Mustasaari", "images/58.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Sydney", "Piet.saari", "images/59.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Casablanca", "Pirkkala", "images/60.png", "0", "0", "0", "0", "0", "0"));

        lista.add(new Data("Melbourne", "Naantali", "images/61.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Abidjan", "Aanekoski", "images/62.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Alexandria", "Heinola", "images/63.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Kolkata", "Laukaa", "images/64.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Surat", "Pieksamaki", "images/65.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Johannesb.", "Kempele", "images/66.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Dar es S.", "Forssa", "images/67.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Mogadishu", "Akaa", "images/68.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Harbin", "Janakkala", "images/69.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Giza", "Kauhava", "images/70.png", "0", "0", "0", "0", "0", "0"));

        lista.add(new Data("Izmir", "Orimattila", "images/71.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Zhengzhou", "Loimaa", "images/72.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Taipei", "Uusikaup.", "images/73.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Los Angeles", "Kuusamo", "images/74.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Changchun", "Ylivieska", "images/75.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Cape Town", "Parainen", "images/76.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Yokohama", "Loviisa", "images/77.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Khartum", "Kontiolaht", "images/78.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Guayaquil", "Lapua", "images/79.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Hangzhou", "Kauhajoki", "images/80.png", "0", "0", "0", "0", "0", "0"));

        lista.add(new Data("Xiamen", "Ulvila", "images/81.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Berliini", "Kalajoki", "images/82.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Busan", "Ilmajoki", "images/83.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Ningbo", "Liperi", "images/84.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Jeddah", "Eura", "images/85.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Durban", "M.hamina", "images/86.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Alger", "Nuorgam", "images/87.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Kabul", "Kankaanpaa", "images/88.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Hefei", "Lieksa", "images/89.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Mashhad", "Pedersore", "images/90.png", "0", "0", "0", "0", "0", "0"));

        lista.add(new Data("Pyongyang", "Nivala", "images/91.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Madrid", "Paimio", "images/92.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Faisalabad", "Hameenkyro", "images/93.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Baku", "Kitee", "images/94.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Tangshan", "Sotkamo", "images/95.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Ekurhuleni", "Huittinen", "images/96.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Nairobi", "Liminka", "images/97.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Pune", "Inari", "images/98.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Jaipur", "Muurame", "images/99.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("B. Aires", "Ii", "images/100.png", "0", "0", "0", "0", "0", "0"));

        lista.add(new Data("Montreal", "Keuruu", "images/101.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Kiova", "Joutsa", "images/102.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Rooma", "Leppavirta", "images/103.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Tukholma", "Lapinlahti", "images/104.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Oslo", "Masku", "images/105.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Valletta", "Kauniainen", "images/106.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Barcelona", "Eckero", "images/107.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Tallinna", "Hattula", "images/108.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Lissabon", "Utsjoki", "images/109.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Praha", "Narpio", "images/110.png", "0", "0", "0", "0", "0", "0"));

        lista.add(new Data("Toronto", "Orivesi", "images/111.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Budapest", "Muhos", "images/112.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("K.hamina", "Somero", "images/113.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Wien", "Karkkila", "images/114.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Las Vegas", "Laitila", "images/115.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Auckland", "Sodankyla", "images/116.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Varsova", "Hanko", "images/117.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Riika", "Loppi", "images/118.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Pariisi", "Asikkala", "images/119.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Reykjavik", "Poytya", "images/120.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Miami", "Humppila", "images/121.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Amsterdam", "Karijoki", "images/122.png", "0", "0", "0", "0", "0", "0"));
        lista.add(new Data("Glasgow", "Kolari", "images/123.png", "0", "0", "0", "0", "0", "0"));

        saver.tallennaData(lista, "vapaat.data");
    }
}
