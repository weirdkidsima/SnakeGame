import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class GameLogic extends JPanel implements ActionListener{
	public static int score;
    private final int size = 320;
    private final int dot_size = 16;
    private final int all_dots = 400;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[all_dots];
    private int[] y = new int[all_dots];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;


    public GameLogic(){
    	Color mint = new Color(0, 255, 127);
        setBackground(mint);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    public void initGame(){
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i*dot_size;
            y[i] = 48;
        }
        timer = new Timer(250,this);
        timer.start();
        createApple();
    }

    public void createApple(){
        appleX = new Random().nextInt(20)*dot_size;
        appleY = new Random().nextInt(20)*dot_size;
    }

    public void loadImages(){
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot,x[i],y[i],this);
            }
        } else{
            String gameover = "Game Over. Your Score: ";
            Color midnightBlue = new Color(25,25,112);
            g.setColor(midnightBlue);
            g.drawString(gameover+score,130,size/2);
        }
    }

    public void move(){ 
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left){
            x[0] -= dot_size;
        }
        if(right){
            x[0] += dot_size;
        } if(up){
            y[0] -= dot_size;
        } if(down){
            y[0] += dot_size;
        }
    }

    public void checkApple(){
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            score++;
            createApple();
        }
    }

    public void checkCollisions(){
        for (int i = dots; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }

        if(x[0]>size){
            inGame = false;
        }
        if(x[0]<0){
            inGame = false;
        }
        if(y[0]>size){
            inGame = false;
        }
        if(y[0]<0){
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollisions();
            move();

        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }

            if(key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                right = false;
                down = true;
                left = false;
            }
        }
    }


}
