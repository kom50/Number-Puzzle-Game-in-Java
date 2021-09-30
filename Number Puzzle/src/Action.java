import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Action extends MouseAdapter {

	private int blankX = 200, blankY = 200; // default
	private int[] locX = { 0, 100, 200, 0, 100, 200, 0, 100, 200 }, 
			locY = { 0, 0, 0, 100, 100, 100, 200, 200, 200 };

	private int moves;
	private JLabel moveLabel;

	private List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);

	private JLabel[] box;
	public boolean isWin = false;

	// Methods
	private void win() {
		int full = 0;
		for (int i = 0; i < locX.length - 1; i++) {
			if (box[i].getX() == locX[i] && box[i].getY() == locY[i]) {
				full += 1;
			}
		}
		if (full == 8) {
			JOptionPane.showMessageDialog(null, "Congratulations! You won.", null, JOptionPane.INFORMATION_MESSAGE);
			isWin = true;
		}
	}

	private void init() {
		moves = 0;
		moveLabel.setText("Total Move : " + moves);

		for (int i = 0; i < box.length; i++) {
			box[i].setLocation(locX[list.get(i)], locY[list.get(i)]);
		}
		// BLank Index		
		blankX = locX[list.get(list.size() - 1)];
		blankY = locY[list.get(list.size() - 1)];
	}

	// Infinite level
	public void nextLevel() {
		//
		isWin = false;
		// Generate Random
		Collections.shuffle(list);

		init();
	}

	public void addButton(JLabel[] box) {
		this.box = new JLabel[box.length];
		for (int i = 0; i < this.box.length; i++) {
			this.box[i] = box[i];
		}
	}

	public void addMoveLabel(JLabel moveLabel) {
		this.moveLabel = moveLabel;
	}

	public void refresh() {
		init();
	}

	private boolean once = true;

	@Override
	public void mousePressed(MouseEvent event) {
		// Run only first time when application is start and we mouse pressed on box
		if (once) {
			nextLevel();
			once = false;
		}
		// target button (JLabel)
		JLabel target = (JLabel) event.getSource();

		if (!isWin) { // is not win then run this code
			int x, y, tempX = 0, tempY = 0;

			x = (int) target.getX(); // get x coordinate of target button
			y = (int) target.getY(); // get y coordinate of target button

			tempX = blankX - x;
			tempY = blankY - y;
			if (((tempX == -100 || tempX == 100) && tempY == 0) || ((tempY == -100 || tempY == 100) && tempX == 0)) {
				target.setLocation(blankX, blankY);
				blankX = x;
				blankY = y;

				moves++;
				moveLabel.setText("Total Moves : " + moves);
			}
			win(); // Checking for win or not
		}
	}
}
