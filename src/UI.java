
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class UI implements Runnable {

    private Joulukalenteri jk;
    private JFrame frame;
    private Layout la;
    private HashMap<Integer, Data> luukut;

    public UI(Joulukalenteri jk, HashMap<Integer, Data> luukut) throws Throwable {
        this.frame = new JFrame();
        frame.addKeyListener(new ShowRulesListener());
        this.luukut = luukut;
        this.jk = jk;
        this.la = new Layout(jk, luukut);
    }

    @Override
    public void run() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setTitle("Joulukalenteri");
        int wi = (int) (3*screenSize.getWidth()/4);
        int he = (int) (3*screenSize.getHeight()/4);
        frame.setPreferredSize(new Dimension(wi, he));
       
        frame.setResizable(false);
        luoKomponentit(frame.getContentPane());
        frame.pack();

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void luoKomponentit(Container cont) {
        cont.setLayout(new GridLayout(1, 1));
        cont.add(la);
        la.repaint();
    }

    public void updateLayout() throws Throwable {
        la.updat();
        la.repaint();
    }

    public void repaint() {
        la.repaint();
    }

    public Layout getLa() {
        return la;
    }

    private class ShowRulesListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {

        }

        @Override
        public void keyPressed(KeyEvent ke) {

        }

        @Override
        public void keyReleased(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_I) {
                JDialog ruleFrame = new JDialog(frame, true);
                ruleFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                ruleFrame.setSize(600, 800);
                JScrollPane scroll;
                JEditorPane rulesTextPane = new JEditorPane("text/html", "");
                rulesTextPane.setEditable(false);
                String rulesText = "<b>Joulukalenteri</b>"
                        + "<br><br> <b>Pelin ohje:</b> <br>Raaputa luukkuja auki silloin kun haluat.\n"
                        + "Luukut avautuvat raaputettavaksi oikeana päivänä. Kun luukku on raaputettu auki, se myös jää avatuksi.\n"
                        + "Luukuissa on erilaista tietoa:<ul><li>Päivän kuva</li><li>Maailman kaupunki</li><li>Suomen kaupunki</li><li>Päivämäärä</li><li>Numero</li></ul>"
                        + "<br>Hauskoja raaputushetkiä!"
                        + "<br>Copyright KH 11/2018, paranneltu 12/2019";
                rulesTextPane.setText(rulesText);
                ruleFrame.add(scroll = new JScrollPane(rulesTextPane));

                ruleFrame.setVisible(true);
            }
        }
    }

}
