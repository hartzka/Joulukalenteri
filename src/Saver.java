
import java.io.RandomAccessFile;
import java.util.List;


public class Saver {
    public void tallennaData(List<Data> data, String tiedosto) throws Throwable {

        RandomAccessFile raf = new RandomAccessFile(tiedosto, "rwd");
        raf.seek(0);

        for (Data d : data) {
            raf.writeBytes(varmistaKoko(d.city, 30));
            raf.writeBytes(varmistaKoko(d.kaupunki, 30));
            raf.writeBytes(varmistaKoko(d.kuva, 20));
            raf.writeBytes(varmistaKoko(d.pvm, 2));
            raf.writeBytes(varmistaKoko(d.kk, 2));
            raf.writeBytes(varmistaKoko(d.vuosi, 4));
            raf.writeBytes(varmistaKoko(d.num, 3));
            raf.writeBytes(varmistaKoko(d.luukku, 2));
            raf.writeBytes(varmistaKoko(d.avattu, 1));
        };

        raf.close();
    }
    
    public void updateData(String city, Data d, String tiedosto) throws Throwable {
 

        RandomAccessFile raf = new RandomAccessFile(tiedosto, "r");
        int index = 0;

        for (int i = 0; i < raf.length(); i+=94) {
            raf.seek(i);
            byte[] city2 = new byte[30];

            raf.read(city2);
            if (!varmistaKoko(city, 30).equals(new String(city2))) {
                continue;
            }
            index = i;
            break;
        }

        raf = new RandomAccessFile(tiedosto, "rwd");
        raf.seek(index);

        raf.writeBytes(varmistaKoko(d.city, 30));
            raf.writeBytes(varmistaKoko(d.kaupunki, 30));
            raf.writeBytes(varmistaKoko(d.kuva, 20));
            raf.writeBytes(varmistaKoko(d.pvm, 2));
            raf.writeBytes(varmistaKoko(d.kk, 2));
            raf.writeBytes(varmistaKoko(d.vuosi, 4));
            raf.writeBytes(varmistaKoko(d.num, 3));
            raf.writeBytes(varmistaKoko(d.luukku, 2));
            raf.writeBytes(varmistaKoko(d.avattu, 1));

        raf.close();

    }

    private String varmistaKoko(String merkkijono, int pituus) {
        if (merkkijono.length() > pituus) {
            return merkkijono.substring(0, pituus);
        }

        while (merkkijono.length() < pituus) {
            merkkijono = merkkijono + " ";
        }

        return merkkijono;
    }
}
