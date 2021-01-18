

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Field extends JPanel {

    BufferedImage image;
    BufferedImage image2;
    BufferedImage card_surface;

    Graphics2D graphics2D;
    int currentX, currentY, oldX, oldY;
    private int marginx, marginy, wid, hed, alkux, alkuy;
    private String ima;
    private boolean[][] opened;
    private int openedCount;
    private boolean enabled;
    private Joulukalenteri jk;
    private boolean chosen;
    private int luukku;
    private Data data;
    

    public Field(int alkux, int alkuy, int mx, int my, int wi, int he, Data d, Joulukalenteri jk, int luukku) throws Throwable {
        this.marginx = mx;
        this.marginy = my;
        this.wid = wi;
        this.hed = he;
        this.alkux = alkux;
        this.alkuy = alkuy;
        this.data = d;
        chosen = false;
        this.opened = new boolean[212][212];
        openedCount = 0;
        this.jk = jk;
        this.luukku = luukku;
        final Stroke stroke = new BasicStroke(20.0F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        enabled = !d.num.equals("-1");
        if (d.avattu.equals("1")) {
            repaint();
            enabled = false;
        }

        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (enabled) {
                    currentX = e.getX();
                    currentY = e.getY();
                    if (graphics2D != null) {
                        graphics2D.setStroke(stroke);
                        graphics2D.setPaint(Color.GRAY);
                        graphics2D.fillOval(currentX - 10, currentY - 10, 20, 20);
                        repaint();
                        for (int i = currentY - 9; i <= currentY + 9; i++) {
                            for (int j = currentX - 9; j <= currentX + 9; j++) {
                                if (i >= 0 && j >= 0 && i <= he && j <= wi) {
                                    if (!opened[i][j]) {
                                        opened[i][j] = true;
                                        openedCount++;
                                        if (openedCount > 17000) {
                                            try {
                                                reveal();
                                            } catch (Throwable ex) {
                                                Logger.getLogger(Field.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        //System.out.println("x " + currentX + " y " + currentY);
                        oldX = currentX;
                        oldY = currentY;
                    }
                }
            }

        });
    }

    private Image TransformColorToTransparency(BufferedImage image, Color c1, Color c2) {
        // Primitive test, just an example
        final int r1 = c1.getRed();
        final int g1 = c1.getGreen();
        final int b1 = c1.getBlue();
        final int r2 = c2.getRed();
        final int g2 = c2.getGreen();
        final int b2 = c2.getBlue();
        ImageFilter filter = new RGBImageFilter() {
            public final int filterRGB(int x, int y, int rgb) {
                int r = (rgb & 0xFF0000) >> 16;
                int g = (rgb & 0xFF00) >> 8;
                int b = rgb & 0xFF;
                if (r >= r1 && r <= r2
                        && g >= g1 && g <= g2
                        && b >= b1 && b <= b2) {
                    // Set fully transparent but keep color
                    return rgb & 0xFFFFFF;
                }
                return rgb;
            }
        };

        ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }

    private BufferedImage ImageToBufferedImage(Image image2, int width, int height) {
        BufferedImage dest = new BufferedImage(
                width + marginx, height + marginy, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(image2, -marginx - alkux, -marginy - alkuy, null);
        
        return dest;
    }

    
    public void clear() {
        image = null;
        image2 = null;
        card_surface = null;
        repaint();
    }

    public void paintComponent(Graphics g) {

        if (image == null) {

            image = new BufferedImage(wid, hed, BufferedImage.TYPE_INT_ARGB);
            image2 = new BufferedImage(wid, hed, BufferedImage.TYPE_INT_ARGB);
            String imagePath = data.kuva;
            
            File inFile = new File(imagePath);
            try {
                image = ImageIO.read(inFile);
            } catch (java.io.IOException e) {
            }
            
            graphics2D = image.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
                  
            repaint();
        }

        if (card_surface == null) {
            card_surface = new BufferedImage(wid +  2*marginx + alkux, hed + 2 * marginy + alkuy, BufferedImage.TYPE_INT_ARGB);
            graphics2D = (Graphics2D) card_surface.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            graphics2D.setPaint(new Color(13,239,66));
            graphics2D.fillRect(0, 0, getSize().width, getSize().height);
            
            graphics2D.setPaint(Color.DARK_GRAY);
            graphics2D.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));
            graphics2D.drawString(Integer.toString(luukku), 55, 97);
            
            repaint();
        }
            g.setColor(Color.DARK_GRAY);
        
        g.fillRect(0, 0, 2 * alkux + 2*marginx + wid, 2 * alkuy + 2 * marginy + hed);
        
        g.drawImage(card_surface, 0, 0, null);
        
        g.setColor(new Color(13,239,66));
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        g.drawString(data.city, 93, 20);
        g.drawString(data.kaupunki, 93, 55);
        g.drawString(data.pvm + "." + data.kk + "." + data.vuosi, 93, 90);
        g.drawString("      " + data.num, 93, 125);
        
        Image transpImg2 = TransformColorToTransparency(card_surface, new Color(0, 50, 77), new Color(200, 200, 255));
        BufferedImage resultImage2 = ImageToBufferedImage(transpImg2, image.getWidth(), image.getHeight());
        
        g.drawImage(image, marginx + alkux, marginy + alkuy, null);
        
        g.drawImage(resultImage2, marginx + alkux, marginy + alkuy, null);
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, alkux, alkuy + 2 * marginy + hed);
        g.fillRect(0, 0, alkux + 2*marginx + wid, alkuy);
        
        if (data.avattu.equals("1")) {
            try {
                reveal();
            } catch (Throwable ex) {
                Logger.getLogger(Field.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void reveal() throws Throwable {
        enabled = false;
        openedCount = 0;
        data.avattu = "1";
        jk.setAvattu(luukku);
        opened = new boolean[211][211];
        graphics2D.setPaint(Color.GRAY);
        graphics2D.fill3DRect(0, 0, 160, 160, true);   
    }
}
