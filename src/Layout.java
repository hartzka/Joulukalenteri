


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JPanel;


public class Layout extends JPanel {
    private int wi;
    private int he;
    private Joulukalenteri jk;
    private Field p1;
    private Field p2;
    private Field p3;
    private Field p4;
    private Field p5;
    private Field p6;
    private Field p7;
    private Field p8;
    private Field p9;
    private Field p10;
    private Field p11;
    private Field p12;
    private Field p13;
    private Field p14;
    private Field p15;
    private Field p16;
    private Field p17;
    private Field p18;
    private Field p19;
    private Field p20;
    private Field p21;
    private Field p22;
    private Field p23;
    private Field p24;
    private HashMap<Integer, Data> luukut;
    
    public Layout(Joulukalenteri jk, HashMap<Integer, Data> luukut) throws Throwable {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        he = (int) (3*screenSize.getHeight()/4);
        wi = (int) (3*screenSize.getWidth()/4);
        this.luukut = luukut;
        this.jk = jk;
        updat();
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(218,41,28));
        g.fill3DRect(0, 0, wi, he, true);
    }
    
    
    public void updat() throws Throwable {
        removeAll();
        p1 = new Field(5, 5, 0, 0, 150, 150, luukut.get(1), jk,1);
        p2 = new Field(5, 5, 0, 0, 150, 150, luukut.get(2), jk,2);
        p3 = new Field(5, 5, 0, 0, 150, 150, luukut.get(3), jk,3);
        p4 = new Field(5, 5, 0, 0, 150, 150, luukut.get(4), jk,4);
        p5 = new Field(5, 5, 0, 0, 150, 150, luukut.get(5), jk,5);
        p6 = new Field(5, 5, 0, 0, 150, 150, luukut.get(6), jk,6);
        p7 = new Field(5, 5, 0, 0, 150, 150, luukut.get(7), jk,7);
        p8 = new Field(5, 5, 0, 0, 150, 150, luukut.get(8), jk,8);
        p9 = new Field(5, 5, 0, 0, 150, 150, luukut.get(9), jk,9);
        p10 = new Field(5, 5, 0, 0, 150, 150, luukut.get(10), jk,10);
        p11 = new Field(5, 5, 0, 0, 150, 150, luukut.get(11), jk,11);
        p12 = new Field(5, 5, 0, 0, 150, 150, luukut.get(12), jk,12);
        p13 = new Field(5, 5, 0, 0, 150, 150, luukut.get(13), jk,13);
        p14 = new Field(5, 5, 0, 0, 150, 150, luukut.get(14), jk,14);
        p15 = new Field(5, 5, 0, 0, 150, 150, luukut.get(15), jk,15);
        p16 = new Field(5, 5, 0, 0, 150, 150, luukut.get(16), jk,16);
        p17 = new Field(5, 5, 0, 0, 150, 150, luukut.get(17), jk,17);
        p18 = new Field(5, 5, 0, 0, 150, 150, luukut.get(18), jk,18);
        p19 = new Field(5, 5, 0, 0, 150, 150, luukut.get(19), jk,19);
        p20 = new Field(5, 5, 0, 0, 150, 150, luukut.get(20), jk,20);
        p21 = new Field(5, 5, 0, 0, 150, 150, luukut.get(21), jk,21);
        p22 = new Field(5, 5, 0, 0, 150, 150, luukut.get(22), jk,22);
        p23 = new Field(5, 5, 0, 0, 150, 150, luukut.get(23), jk,23);
        p24 = new Field(5, 5, 0, 0, 150, 150, luukut.get(24), jk,24);
     
        setLayout(null);
        setBounds(0,0,1500,1000);
        p1.setLayout(null);
        p1.setBounds(70,10,160,160);
        add(p1);
        p2.setLayout(null);
        p2.setBounds(280,30,160,160);
        add(p2);
        p3.setLayout(null);
        p3.setBounds(490, 10,160,160);
        add(p3);
        p4.setLayout(null);
        p4.setBounds(700,30,160,160);
        add(p4);
        p5.setLayout(null);
        p5.setBounds(910, 10,160,160);
        add(p5);
        p6.setLayout(null);
        p6.setBounds(1120,30,160,160);
        add(p6);
        
        p7.setLayout(null);
        p7.setBounds(90,190,160,160);
        add(p7);
        p8.setLayout(null);
        p8.setBounds(300,210,160,160);
        add(p8);
        p9.setLayout(null);
        p9.setBounds(510,190,160,160);
        add(p9);
        p10.setLayout(null);
        p10.setBounds(720,210,160,160);
        add(p10);
        p11.setLayout(null);
        p11.setBounds(930,190,160,160);
        add(p11);
        p12.setLayout(null);
        p12.setBounds(1140,210,160,160);
        add(p12);
        
        p13.setLayout(null);
        p13.setBounds(70,370,160,160);
        add(p13);
        p14.setLayout(null);
        p14.setBounds(280,390,160,160);
        add(p14);
        p15.setLayout(null);
        p15.setBounds(490,370,160,160);
        add(p15);
        p16.setLayout(null);
        p16.setBounds(700,390,160,160);
        add(p16);
        p17.setLayout(null);
        p17.setBounds(910,370,160,160);
        add(p17);
        p18.setLayout(null);
        p18.setBounds(1120,390,160,160);
        add(p18);
        
        p19.setLayout(null);
        p19.setBounds(90,550,160,160);
        add(p19);
        p20.setLayout(null);
        p20.setBounds(300,570,160,160);
        add(p20);
        p21.setLayout(null);
        p21.setBounds(510,550,160,160);
        add(p21);
        p22.setLayout(null);
        p22.setBounds(720,570,160,160);
        add(p22);
        p23.setLayout(null);
        p23.setBounds(930,550,160,160);
        add(p23);
        p24.setLayout(null);
        p24.setBounds(1140,570,160,160);
        add(p24);
    }
}
