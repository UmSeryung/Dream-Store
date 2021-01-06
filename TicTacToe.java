package _20210106_TicTacToe;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TicTacToe extends JFrame implements ActionListener {
	Container ct;
	JButton bt_start = new JButton("Start");
	JPanel jp1 = new JPanel();
	JButton[][] bt = new JButton[3][3];
	JTextArea jta = new JTextArea();

	ImageIcon ii_blank = new ImageIcon("tictactoe_blank.png");
	ImageIcon ii_user = new ImageIcon("tictactoe_user.png");
	ImageIcon ii_com = new ImageIcon("tictactoe_computer.png");

	boolean[][] total = new boolean[3][3];
	boolean[][] user = new boolean[3][3];
	boolean[][] computer = new boolean[3][3];

	int count = 1;

	public TicTacToe() {
		set_component();
		set_layout();

		setTitle("Tic Tac Toe");
		setSize(300,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public void set_component() {
		ct = getContentPane();
		bt_start.addActionListener(this);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addActionListener(this);
			}
		}
	}

	public void set_layout() {
		jp1.setLayout(new GridLayout(3, 3));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				jp1.add(bt[i][j]);
			}
		}
		ct.setLayout(new BorderLayout());
		ct.add(bt_start, new BorderLayout().NORTH);
		ct.add(jp1, new BorderLayout().CENTER);
		ct.add(jta, new BorderLayout().SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton temp = (JButton) e.getSource();

		if (temp == bt_start) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					bt[i][j].setIcon(ii_blank);
				}
			}
		} else {
			if (count == 9) {
				jta.setText("Draw");
			}
			if (count % 2 == 1) { // user
				if (temp.getText().equals("")) {
					temp.setIcon(ii_user);
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if (temp == bt[i][j]) {
								user[i][j] = true;
								total[i][j] = true;
								judge(user, 1);
							}
						}
					}
				}
			}
			count++;
			if (count % 2 == 0) { // computer
				Random rd = new Random();
				int x = 0;
				int y = 0;
				while (true) {
					x = rd.nextInt(3);
					y = rd.nextInt(3);
					if (total[x][y] == false) {
						total[x][y] = true;
						computer[x][y] = true;
						bt[x][y].setIcon(ii_com);
						break;
					}
				}
				judge(computer, 2);
			}
			count++;
		}
	}

	public void judge(boolean[][] arr, int who) {
		boolean clue1 = false;
		boolean clue2 = false;
		boolean clue3 = false;
		boolean clue4 = false;

		for (int i = 0; i < 3; i++) {
			clue1 = (arr[i][0] == true && arr[i][1] == true && arr[i][2] == true);
			clue2 = (arr[0][i] == true && arr[1][i] == true && arr[2][i] == true);
			clue3 = (arr[0][0] == true && arr[1][1] == true && arr[2][2] == true);
			clue4 = (arr[0][2] == true && arr[1][1] == true && arr[2][0] == true);
		}

		if (clue1 || clue2 || clue3 || clue4) {
			if (who == 1) {
				jta.setText("My victory!!");
			} else {
				jta.setText("Computer Victory!");
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (total[i][j] == false) {
						bt[i][j].setEnabled(false);
						bt[i][j].setIcon(ii_blank);
					}
				}
			}
		}
	}
}
