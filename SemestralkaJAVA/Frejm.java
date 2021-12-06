import javax.swing.JFrame;

/**
 * Trieda Frejm reprezentuje okno v ktorom hra prebieha
 * Rozsiruje triedu JFrame ktora moze reprezentovat okno a podporuje Swing komponenty ktore dalej vyuzivame na vykreslovanie, vstupy, atd..
 * 
 * @version 2019.12.14
 * @author Dávid Hrebičík
 */

public class Frejm extends JFrame {
    
    private static final int RES_X = 800;
    private static final int RES_Y = 600;
    
    /**
     * Konstruktor posle spravu privatnej metode init() ktora inicializuje okno 
     */
    
    public Frejm() {
        
        init();
    }
    
    /**
     * Vytvori novy objekt typu Game a prida ho do noveho Frejmu
     * Privátna metóda ktorá inicializuje Frejm a nastavi mu vlastnosti
     */
    
    private void init() {
        
        this.add(new Game());
        this.setTitle("Kerrot");
        this.setSize(RES_X, RES_Y);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
