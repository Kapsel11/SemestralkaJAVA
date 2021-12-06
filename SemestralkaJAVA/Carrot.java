import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

/**
 * Trieda Carrot reprezentuje objekt mrkvu v tejto hre, uchovava jej suradnice, velkost, obrazok, posun.
 * 
 * @version 2019.12.14
 * @author Dávid Hrebičík
 */

public class Carrot {
    
    private static final int RES_X = 800;
    
    private int dx;
    private int x;
    private int y;
    private int w;
    private int h;
    private Image image;
    
    /**
     * Konstruktor posle spravu privatnej metode loadImage() ktora inicializuje atributy
     */
    
    public Carrot() {
        
        this.loadImage();
    }
    
    /**
     * Privátna metóda ktorá inicializuje atribúty 
     */
    
    private void loadImage() {
        
        this.image = (new ImageIcon("resources/carrot.png")).getImage();
        
        
        this.y = 0;
        this.w = image.getWidth(null);
        this.h = image.getHeight(null);
        this.x = (RES_X / 2)  - this.w;
    }
    
    /**
     * Vráti súradnicu x
     * @return hodnota súradnice x
     */
    
    public int getX() {
        
        return this.x;
    }
    
    /**
     * Vráti súradnicu y
     * @return hodnota súradnice y
     */
    
    public int getY() {
        
        return this.y;
    }
    
    /**
     * Vráti šírku obrázka 
     * @return hodnota šírky obrazu
     */
    
    public int getWidth() {
        
        return this.w;
    }
    
    /**
     * Vráti výšku obrázka 
     * @return hodnota výšky obrazu
     */
    
    public int getHeight() {
    
        return this.h;
    }
    
    /**
     * Vracia obrázok
     * @return obrázok oblaku typu Image
     */
    
    public Image getImage() {
    
        return this.image;
    }
    
    /**
     * Zmení atribút posunu
     */
    
    public void move() {
        
        x += dx;
    }
    
    /**
     * Metóda ktorá sa volá z triedy Game keď uzivatel pustil stlaceny klaves
     * Ak pustil sipku dolava alebo doprava tak sa atribut posunu nastaví na 0
     * Kontroluje ci nie je atribut mimo okna - ak ano tak zmeni atribut x a nepusti obrazok mimo okna
     * @param e typu KeyEvent ktory uchovava informacie o klavesach
     */
    
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            if (x <= 0 ) {
                x = 0;
            }
            this.dx = 0;
        }
        
        if (key == KeyEvent.VK_RIGHT) {
            if (x >=  (RES_X - 10  - this.w)) {
                x = RES_X - 10  - this.w;
            }
            this.dx = 0;
        }
    }
    
    /**
     * Metóda ktorá sa volá z triedy Game keď uzivatel stlacil nejaky klaves
     * Ak stlacil sipku dolava alebo doprava tak sa atribut posunu zmeni
     * Kontroluje ci nie je atribut mimo okna - ak ano tak zmeni atribut x a nepusti obrazok mimo okna
     * @param e typu KeyEvent ktory uchovava informacie o klavesach
     */
    
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            if (x <= 0 ) {
                x = 0;
            }
            dx = -3;
        }

        if (key == KeyEvent.VK_RIGHT) {
            if (x >=  (RES_X - 10 - this.w)) {
                x = RES_X - 10 - this.w;
            }
                
            dx = 3;
        }
    }
    
    /**
     * Vráti obdlznik ktory reprezentuje obrazok mrkvy
     * @return objekt typu Rectangle 
     */
    
    public Rectangle getRectangle() {
        return new Rectangle(this.x, this.y, this.w - 5, this.h - 5);
    }
}
