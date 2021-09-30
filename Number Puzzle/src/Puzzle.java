import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Puzzle extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JLabel[] box = new JLabel[8];
	private JLabel lblTotalMoves, lblLevel;
	private int[] locX = { 0, 100, 200 }, locY = { 0, 100, 200 };

	private Action action = new Action();

	private JPanel container;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Puzzle frame = new Puzzle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Puzzle() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);

//		Drag
		new OnDrag(contentPane, this);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 500, 600);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel exit_btn = new JLabel();
		exit_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Puzzle.this.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				exit_btn.setIcon(new ImageIcon(Puzzle.class.getResource("/images/close_btn_red.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exit_btn.setIcon(new ImageIcon(Puzzle.class.getResource("/images/close_btn_white.png")));
			}
		});
		exit_btn.setIcon(new ImageIcon(Puzzle.class.getResource("/images/close_btn_white.png")));
		exit_btn.setBounds(465, 11, 25, 19);
		panel.add(exit_btn);

		JLabel min_btn = new JLabel("");
		min_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		min_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Puzzle.this.setState(JFrame.ICONIFIED);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				min_btn.setIcon(new ImageIcon(Puzzle.class.getResource("/images/min_btn_red.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				min_btn.setIcon(new ImageIcon(Puzzle.class.getResource("/images/min_btn_white.png")));
			}
		});
		min_btn.setIcon(new ImageIcon(Puzzle.class.getResource("/images/min_btn_white.png")));
		min_btn.setBounds(431, 11, 25, 19);
		panel.add(min_btn);

		container = new JPanel();
		container.setBorder(new LineBorder(new Color(0, 0, 0)));
		container.setBackground(new Color(175, 238, 238));
		container.setBounds(101, 186, 300, 300);
		panel.add(container);
		container.setLayout(null);

//		JLabel lblNewLabel = new JLabel("1");
//		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
//		lblNewLabel.setOpaque(true);
//		lblNewLabel.setForeground(new Color(0, 0, 255));
//		lblNewLabel.setBackground(new Color(255, 255, 255));
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 30));
//		lblNewLabel.setBounds(0, 0, 100, 100);
//		container.add(lblNewLabel);

		JButton btnNext = new JButton("NEXT");
		btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (action.isWin) {
					lblLevel.setText("Level : " + (Integer.parseInt(lblLevel.getText().substring(8)) + 1));
					action.nextLevel();
				}
			}
		});
		btnNext.setBackground(Color.WHITE);
		btnNext.setBounds(132, 530, 230, 33);
		panel.add(btnNext);

		lblTotalMoves = new JLabel("Total Moves : 0");
		lblTotalMoves.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalMoves.setBounds(269, 155, 132, 26);
		panel.add(lblTotalMoves);

		lblLevel = new JLabel("Level : 1");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLevel.setBounds(101, 155, 83, 26);
		panel.add(lblLevel);

		JLabel lblNumberPuzzleGame = new JLabel("Number Puzzle Game");
		lblNumberPuzzleGame.setForeground(Color.BLUE);
		lblNumberPuzzleGame.setBackground(Color.BLUE);
		lblNumberPuzzleGame.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNumberPuzzleGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberPuzzleGame.setFont(new Font("Consolas", Font.BOLD, 28));
		lblNumberPuzzleGame.setBounds(72, 61, 350, 45);
		panel.add(lblNumberPuzzleGame);

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				action.refresh();
			}
		});
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Puzzle.class.getResource("/images/reset.png")));
		label.setBounds(215, 493, 49, 26);
		panel.add(label);

		JLabel bg_image = new JLabel("");
		bg_image.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		bg_image.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		bg_image.setBackground(Color.YELLOW);
		bg_image.setBounds(0, 0, 500, 600);
		panel.add(bg_image);
		bg_image.setIcon(new ImageIcon(Puzzle.class.getResource("/images/bg_img1.png")));

//		init method call
		init();

	}

	private void init() {
		System.out.println("Init method ");
		for (int i = 0; i < box.length; i++) {
			box[i] = new JLabel("" + (i + 1));
			box[i].setSize(100, 100);
			box[i].setBackground(Color.red);
			box[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			box[i].setHorizontalAlignment(SwingConstants.CENTER);
			box[i].setFont(new Font("Consolas", Font.BOLD, 24));

			box[i].setOpaque(true);
			box[i].setForeground(new Color(0, 0, 255));
			box[i].setBackground(new Color(255, 155, 25));
			box[i].setBorder(new LineBorder(new Color(0, 0, 0)));

			// Add action on box
			action.addMoveLabel(lblTotalMoves);
			box[i].addMouseListener(action);
			action.addButton(box);

			// add box on container
			 container.add(box[i]);
		}

		int k = 0;
		for (int i = 0; i < locX.length; i++) {
			for (int j = 0; j < locY.length; j++) {
			if(k < 8) {
				box[k].setLocation(locX[j], locY[i]);
			}
				k++;
			}
		}
	}
}
