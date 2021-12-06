import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Trieda Hp reprezentuje zivoty v tejto hre
 * 
 * @version 2019.12.14
 * @author Dávid Hrebičík
 */

public class Hp {
    
    private Image image;
    private int size;
    private int currSize;
    private int sizeBetweenHps;
    private int x;
    private int y;
    
    /**
     * Konstruktor inicializuje premenne
     * @param size pocet zivotov
     */
    
    public Hp(int size) {
        // initialise instance variables

        this.image = (new ImageIcon("resources/Heart.png")).getImage();
        this.sizeBetweenHps = 50;
        this.size = size;
        this.x = 0;
        this.y = 0;
        this.currSize = this.size;
    }
    
    /**
     * Konstruktor inicializuje premenne
     * @param size pocet zivotov
     * @param sizeBetweenHps pocet pixelov medzi obrazmi zivotov ktore sa budu vykreslovat
     */
    
    public Hp(int size, int sizeBetweenHps) {
        // initialise instance variables
        
        this.image = (new ImageIcon("resources/Heart.png")).getImage();
        this.sizeBetweenHps = sizeBetweenHps;
        this.size = size;
        this.x = 0;
        this.y = 0;
        this.currSize = this.size;
    }
    
    /**
     * Konstruktor inicializuje premenne
     * @param size pocet zivotov
     * @param sizeBetweenHps pocet pixelov medzi obrazmi zivotov ktore sa budu vykreslovat
     * @param x suradnica x kde sa maju vykreslit zivoty
     * @param y suradnica y kde sa maju vykreslit zivoty
     */
    
    public Hp(int size, int sizeBetweenHps, int x, int y) {
        // initialise instance variables
        this.image = (new ImageIcon("resources/Heart.png")).getImage();
        this.sizeBetweenHps = sizeBetweenHps;
        this.size = size;
        this.x = x;
        this.y = y;
        this.currSize = this.size;
    }
    
    /**
     * Vracia maximalny pocet zivotov aky mozeme dosiahnut - inicializovane v konstruktore
     * @return maximalny pocet zivotov
     */
    
    public int getSize() {
        
        return this.size;
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
     * Vráti aktuálny počet zivotov
     * @return aktualny pocet zivotov
     */
    
    public int getCurrSize() {
        
        return this.currSize;
    }
    
    /**
     * Vráti velkost medzi jednotlivymi obrazmi zivotov 
     * @return velkost medzi jednotlivymi obrazmi zivotov 
     */
    
    public int getSizeBetweenHps() {
        
        return this.sizeBetweenHps;
    }
    
    /**
     * Vracia obrázok
     * @return obrázok zivota typu Image
     */
    
    public Image getImage() {
    
        return image;
    }
    
    /**
     * Prida zivot ak je aktualny pocet zivotov mensi ako maximalny
     * @return true ak sa pridal zivot inak false 
     */
    
    public boolean addHp() {
        
        if (this.currSize < this.size) {
            this.currSize++;
            return true;
        }
        return false;
    }
    
    /**
     * Odobere zivot ak je aktualny pocet zivotov väčší ako 0
     * @return true ak sa odobral zivot inak false 
     */
    
    public boolean removeHp() {
        
        if (this.currSize > 0) {
            this.currSize--;
            return true;
        }
        return false;
    }
}
