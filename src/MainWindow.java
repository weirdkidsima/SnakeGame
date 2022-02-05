import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainWindow extends JFrame {
	
	public  MainWindow() {
		setTitle("Snake Game");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocation(400,400);
		setSize(400,400);
		add(new GameLogic());
		setVisible(true);
		ImageIcon ic = new ImageIcon("icon.png"); // icon created by Dylan Hernandez
		this.setIconImage(ic.getImage());
		this.setResizable(false);
		
	}
	public static  void main(String[] args) {
		MainWindow mw = new MainWindow();
	}

}
