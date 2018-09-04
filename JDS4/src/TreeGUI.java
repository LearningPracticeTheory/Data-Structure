/* add Level to solve node cover problem */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

public class TreeGUI<AnyType extends Comparable<AnyType>> extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int DIAMETER = 30;
	public static final int X_DISTANCE = 40;
	public static final int Y_DISTANCE = 68;
	public static final int PAUSE_TIME = 200; //ms
	private Color nodeColor = Color.DARK_GRAY;
	
	private BinarySearchTree<AnyType>.TreeNode root = null;
	
	TreeGUI(BinarySearchTree<AnyType>.TreeNode root) {
		this.root = root;
		initializedFrame();
	}
	
	private void initializedFrame() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("TreeGUI");
		this.setResizable(false);
		this.getContentPane().setBackground(Color.BLACK);
		this.setBackground(Color.BLACK);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawTree(g);
	}
	
	private void drawTree(Graphics g) {
		this.drawTree(WIDTH/2-DIAMETER/2, 70, WIDTH/2-DIAMETER/2, 70, g, root);
	}
/*preorderTraversl*/	
	private void drawTree(int px, int py, int x, int y, Graphics g, BinarySearchTree<AnyType>.TreeNode t) {
		drawLine(px, py, x, y, g);
		pause();
		drawCircle(x, y, g);
		pause();
		drawString(x, y, t.data, g);
		pause();
		if(t.left != null) drawTree(x, y, leftSideX(x), downSideY(y), g, t.left);
		if(t.right != null) drawTree(x, y, rightSideX(x), downSideY(y), g, t.right);
	}
	
	private void drawString(int x, int y, AnyType data, Graphics g) {
		x -= 5;
		y += 6;
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.setFont(new Font("Cambria", Font.PLAIN, 20));
		g.drawString(data.toString(), x, y);
		g.setColor(c);
	}
	
	private void drawLine(int px, int py, int x, int y, Graphics g) {
		Color c = g.getColor();
		g.setColor(nodeColor);
		g.drawLine(px, py, x, y);
		g.setColor(c);
	}	
		
	private void drawCircle(int x, int y, Graphics g) {
		x = x-DIAMETER/2;
		y = y-DIAMETER/2;
		Color c = g.getColor();
		g.setColor(nodeColor);
		g.fillOval(x, y, DIAMETER, DIAMETER);
		g.setColor(c);
	}
	
	private int leftSideX(int px) {
		return px - X_DISTANCE;
	}
	
	private int rightSideX(int px) {
		return px + X_DISTANCE;
	}
	
	private int downSideY(int py) {
		return py + Y_DISTANCE;
	}
	
	private void pause() {
		try {
			Thread.sleep(PAUSE_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
}
