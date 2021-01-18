
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


public class Reader {
    public List<Data> lueData(String tiedosto) throws Throwable {
        RandomAccessFile raf = new RandomAccessFile(tiedosto, "r");

        List<Data> data = new ArrayList<>();

        raf.seek(0);
       
        byte[] city = new byte[30];
        byte[] kaupunki = new byte[30];
        byte[] kuva = new byte[20];
        byte[] pvm = new byte[2];
        byte[] kk = new byte[2];
        byte[] vuosi = new byte[4];
        byte[] num = new byte[3];
        byte[] luukku = new byte[2];
        byte[] avattu = new byte[1];

        while (raf.getFilePointer() < raf.length()) {
            Data d = new Data("","","","","","","","","");
  
            raf.read(city);
            raf.read(kaupunki);
            raf.read(kuva);
            raf.read(pvm);
            raf.read(kk);
            raf.read(vuosi);
            raf.read(num);
            raf.read(luukku);
            raf.read(avattu);
            
            d.city = new String(city).trim();
            if (d.city.charAt(0) == '0') d.city = d.city.substring(1,d.city.length());
            d.kaupunki = new String(kaupunki).trim();
            d.kuva = new String(kuva).trim();
            d.pvm = new String(pvm).trim();
            d.kk = new String(kk).trim();
            d.vuosi = new String(vuosi).trim();
            d.num = new String(num).trim();
            d.luukku = new String(luukku).trim();
            d.avattu = new String(avattu).trim();
            
            System.out.println("************************");
            System.out.println(d.city);
            System.out.println(d.kaupunki);
            System.out.println(d.kuva);
            System.out.println(d.pvm);
            System.out.println(d.kk);
            System.out.println(d.vuosi);
            System.out.println(d.num);
            System.out.println(d.luukku);
            System.out.println(d.avattu);
            
            data.add(d);
        }

        return data;
    }
}
