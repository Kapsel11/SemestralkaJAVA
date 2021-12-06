import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Trieda Cloud reprezentuje objekt oblak v tejto hre, uchovava jeho suradnice, velkost, obrazok, posun.
 * 
 * @version 2019.12.14
 * @author Dávid Hrebičík
 */

public class Cloud {
    
    private int dy;
    private int x;
    private int y;
    private int w;
    private int h;
    private Image image;
    private static final int RES_X = 800;
    private static final int RES_Y = 600;
    
    /**
     * Konstruktor posle spravu privatnej metode loadImage() ktora inicializuje atributy
     */
    
    public Cloud() {
        
        this.loadImage();
    }
    
    /**
     * Privátna metóda ktorá inicializuje atribúty 
     */
    
    private void loadImage() {
        this.image = (new ImageIcon("resources/Oblak.png")).getImage();
        
        this.dy = 0;
        this.w = this.image.getWidth(null);
        this.h = this.image.getHeight(null);
        this.y = RES_Y + this.h;
        Random rand = new Random(); 
        this.x = rand.nextInt(RES_X - this.w);
    }
    
    /**
     * Zmení atribút posunu
     */
    
    public void move() {
    
        this.y += this.dy ;
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
     * Vracia posun
     * @return hodnota posunu
     */
    
    public int getDifference() {
    
        return this.dy;
    }
    
    /**
     * Nastaví atribúty oblaku na začiatok 
     */
    
    public void setUp() {
        Random rand = new Random(); 
        this.x = rand.nextInt(RES_X - this.w);
        this.y = RES_Y + this.h;
        this.dy = -2;
    }
    
    /**
     * Zmení posun
     */
    
    public void changeDifference() {
        
        this.dy = -2;
    }
    
    /**
     * Vráti obdlznik ktory reprezentuje obrazok oblaku
     * @return objekt typu Rectangle 
     */
    
    public Rectangle getRectangle() {
        return new Rectangle(this.x , this.y, this.w - 5, this.h - 5);
    }
    
}
