import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ColorGame -extends JPanel
 * 
 */
public class ColorGame extends JPanel implements MouseListener, ActionListener, Runnable {
	public static final int GOOD_SHAPE_SIZE = 50;

	private int numOfGoodShapes; // Number of good shapes
	private int numOfGoodShapesToDisplay; // Number of times good shapes are presented.
	private JFrame frame;
	private GoodShapesPanel goodShapesPanel;
	private JButton button;
	private ShapesDisplayPanel shapesDisplayPanel;
	private int numberOfGoodShapesShown;
	private JLabel score;
	private JPanel bottomPanel;

	public ColorGame(int numOfGoodShapes, int numOfGoodShapesToDisplay) {
		this.numOfGoodShapes = numOfGoodShapes;
		this.numOfGoodShapesToDisplay = numOfGoodShapesToDisplay;
		numberOfGoodShapesShown=0;
		score = new JLabel("0");

		frame = new JFrame("color game");
		frame.setSize(500,400);
		frame.setLayout(new BorderLayout());

		initializeGoodShapesPanel();

		button = new JButton("start");

		button.addActionListener(this);


		bottomPanel = new JPanel();
		bottomPanel.add(button);
		frame.add(bottomPanel,BorderLayout.SOUTH);
		frame.add(goodShapesPanel,BorderLayout.WEST);

		shapesDisplayPanel = new ShapesDisplayPanel();



		frame.add(shapesDisplayPanel,BorderLayout.CENTER);

		frame.setVisible(true);

	}

	private void initializeGoodShapesPanel() {
		Set<Shape> set = new HashSet<>();

		for (int i=1; i<=numOfGoodShapes; i++){
			set.add(new Shape(new Point(20,i*(GOOD_SHAPE_SIZE+20)),new Dimension(GOOD_SHAPE_SIZE,GOOD_SHAPE_SIZE)));
		}
		goodShapesPanel = new GoodShapesPanel(set);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		System.out.println(me.getPoint());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		button.setVisible(false);
		bottomPanel.add(score);

		Thread t = new Thread(this);
		shapesDisplayPanel.addMouseListener(this);
		t.start();
	}

	private void incrementScore() {
		int currentScore = Integer.parseInt(score.getText());
		score.setText("" + (currentScore+1));
	}
	private void decrementScore() {
		int currentScore = Integer.parseInt(score.getText());
		score.setText("" + (currentScore-1));
	}

	public static void main(String[] args) {
		ColorGame cg = new ColorGame(2,
				10
		);

	}

	@Override
	public void run() {

		while(numberOfGoodShapesShown < numOfGoodShapesToDisplay){

			shapesDisplayPanel.setDisplayShape(true);
			shapesDisplayPanel.repaint();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			shapesDisplayPanel.setDisplayShape(false);
			shapesDisplayPanel.repaint();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
