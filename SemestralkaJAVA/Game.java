import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.EventQueue;
import java.awt.RenderingHints;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Trieda Game reprezentuje panel v ktorom sa bude odohravat tato hra
 * Rozsiruje triedu JPanel ktora moze reprezentovat Panel
 * Implementuje rozhranie triedy ActionListener vdaka tomu mozeme reagovat na vstupy od uzivatela
 * Vykresluje objekty typu Hp, Cloud, Carrot a posiela im spravy a na zaklade navratovych hodnot a vstupu od uzivatela meni stav panelu a objektov
 * 
 * @version 2019.12.14
 * @author Dávid Hrebičík
 */

public class Game extends JPanel implements ActionListener {
    
    private static final int DELAY = 10;
    private static final int SIZE_CLOUDS = 10;
    private static final int HP = 3;
    
    private Timer timer;
    private Cloud[] clouds;
    private int currSize;
    private Carrot carrot;
    private boolean koniec;
    private Hp hp;
    private JLabel scoreLabel;
    private int score;
    
    /**
     * Konstruktor posle spravu privatnej metode initGame() ktora dalej inicializuje atributy 
     */
    
    public Game() {
        
        this.initGame();
    }
    
    /**
     * Privátna metóda ktorá inicializuje atribúty, nastavuje vlastnosti panelu, pridava KeyListener, Timer, JLabel
     * 
     */
    
    private void initGame() {
        
        this.addKeyListener(new Adapter());
        this.setBackground(new Color(201, 255, 255));
        this.setFocusable(true);
        this.carrot = new Carrot();
        this.clouds = new Cloud[SIZE_CLOUDS];
        for (int i = 0 ; i < SIZE_CLOUDS; i++) {
            clouds[i] = new Cloud();
        }
        this.currSize = 1;
        this.scoreLabel = new JLabel("Score: 0");
        scoreLabel.setPreferredSize(new Dimension(770, 15));
        scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
        scoreLabel.setVerticalAlignment(JLabel.TOP);
        add(scoreLabel);
        this.timer = new Timer(DELAY, this);
        this.timer.start();
        this.clouds[0].changeDifference();
        this.hp = new Hp(HP);
        this.score = 0;
        this.koniec = false;
    }
    
    /**
     * Táto metóda je spustená zakaždým, keď operačný systém povie AWT enginu, že je potrebné maľovať plátno.
     * Volá privátnu metódu draw ktora urobi vykreslovanie objektov
     * @Override 
     */
    
    @Override
    public void paint(Graphics g) {
        
        super.paint(g);     
        this.draw(g);
    }
    
    /**
     * Ak mame viac zivotov ako 0 tak vykresli objekty Carrot, Cloud, Hp
     */
    
    private void draw(Graphics g) {
        
        if (this.hp.getCurrSize() > 0) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(this.carrot.getImage(), this.carrot.getX(),
                            carrot.getY(), this);
            for (int i = 0; i < currSize; i++) {                
                g2d.drawImage(this.clouds[i].getImage(), this.clouds[i].getX(),
                            this.clouds[i].getY(), this); 
            }
            
            for (int i = 0; i < hp.getCurrSize(); i++) {                
                g2d.drawImage(hp.getImage(), this.hp.getX() + (i *  this.hp.getSizeBetweenHps()),
                            this.hp.getY(), this); 
            }
        }
    }
    
    /**
     * JVM zavola tuto metodu ked sa udeje nejaka akcia 
     *@Override
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.play();
    }
    
    /**
     * Posunie suradnice mrkvy a vykresli platno
     */
    
    private void paintCarrot() {
    
        this.carrot.move();
        this.repaint();
        
    }
    
    /**
     * Vnutorna privatna trieda ktora rozsiruje abstraktnu triedu KeyAdapter ktora je urcena na prijimanie vstupov z klavesnice
     * Ked sa stlaci alebo pusti stlaceny klaves posiela spravu triede Carrot
     */
    
    private class Adapter extends KeyAdapter {
    
        @Override
        public void keyReleased(KeyEvent e) {
            carrot.keyReleased(e);
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            carrot.keyPressed(e);
        }
    }
    
    /**
     * Posunie suradnice oblakov a vykresli platno
     */
    
    private void paintClouds() {
            
        for (int i = 0; i < currSize; i++) {
        
            this.clouds[i].move();
            this.repaint();    
        }     
    
    }
    
    /**
     * Kontroluje ci sa mrkva nedotyka oblakov ak ano tak vrati true inak false
     * @return ak sa dotyka mrkva oblakov true inak false
     */
    
    public boolean carrotInters() {
        
        for (int i = 0; i < currSize; i++) {
            if (this.carrot.getRectangle().intersects(this.clouds[i].getRectangle())) {
                this.clouds[i].setUp();
                return true;
            }
        }
        return false;
       
        
    }
    
    /**
     * Kontroluje ci sa nejake oblaky nedotykaju ak ano tak jeden z nich nastavi na zaciatok
     */
    
    public void cloudsInters() {
         
        for (int j = 0; j < currSize; j++) {
            for (int i = 0; i < currSize; i++) {
                if (i != j && this.clouds[j].getRectangle().intersects(this.clouds[i].getRectangle())) {
                    this.clouds[j].setUp();
                }
            }
            
        }
        
    }
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            Frejm frame = new Frejm();
            
        });
    }
    
    /**
     * Jadro celej hry
     */
    
    private void play() {
    
        if (this.hp.getCurrSize() > 0) {    // Ak je pocet zivotov vecsi ako 0
            this.addCloud();
            this.paintClouds();
            this.paintCarrot();
            this.cloudsInters();
            for (int i = 0; i < currSize; i++) {    // Pre pocet oblakov kontroluje ci nie su mimo okna ak ano tak zvysi skore a nastavi ich na zaciatok
                if (this.clouds[i].getY() <= 0 - this.clouds[i].getHeight()) {
                    this.incrementScore();
                    this.clouds[i].setUp();
                }
            }
            if (this.carrotInters()) {  // Ak sa mrkva dotyka oblakov tak odobere zivot a ak je pocet zivotov 0 zavola end() ktora ukonci hru
                this.hp.removeHp();
                if (this.hp.getCurrSize() == 0) {
                    
                    end();
                } 
            }
        }
    }
    
    /**
     * Prida dalsi oblak ak ich nie je max pocet
     */
    
    private void addCloud() {
        if (currSize < SIZE_CLOUDS) {
            if (this.clouds[currSize - 1].getY() < 50 ) {
                this.clouds[currSize].changeDifference();
                this.currSize++;
            }
        }
    }
    
    /**
     * Zvysi skore a vypise ho
     */
    
    public void incrementScore() {
        score++;
        scoreLabel.setText("Score: " + score);
        repaint();
    }
    
    /**
     * Ukonci hru
     */
    
    private void end() {
        
        this.setBackground(new Color(201, 255, 255));
        scoreLabel.setPreferredSize(new Dimension(300, 300));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setVerticalAlignment(JLabel.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.HANGING_BASELINE, 30));
        scoreLabel.setText("YOUR SCORE IS: " + score);
        koniec = true;
    }
}
    
    
    

