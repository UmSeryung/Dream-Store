package _20210101_Bioryhthm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;

public class Biorhythm extends JFrame implements ActionListener {

	Container ct;
	JPanel jp1 = new JPanel();
	JPanel jp1_1 = new JPanel();
	JProgressBar[] jpb = new JProgressBar[4];
	JLabel[] jl = new JLabel[4];
	JPanel jp1_2 = new JPanel();
	String[] jl_text = new String[] { "Body", "Emotional", "Intellectual", "Perception" };
	String[] jl_date_text = new String[] { "Year", "Month", "Day" };

	JPanel jp2 = new JPanel();
	JLabel jl_input = new JLabel("Enter date of birth", JLabel.CENTER);
	JLabel[] jl_date = new JLabel[3];
	JPanel jp2_1 = new JPanel();
	JTextField[] jtf = new JTextField[3];

	JButton bt = new JButton("Calculation");

	public Biorhythm() {
		set_component();
		set_layout();

		setTitle("Today_Biorhythm");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public void set_component() {
		ct = getContentPane();
		for (int i = 0; i < 4; i++) {
			jpb[i] = new JProgressBar(JProgressBar.VERTICAL);
			jpb[i].setMaximum(100);
			jpb[i].setMinimum(-100);
			jl[i] = new JLabel(jl_text[i], JLabel.CENTER);
		}
		jp2.setBackground(Color.GRAY);
		for (int i = 0; i < 3; i++) {
			jl_date[i] = new JLabel(jl_date_text[i], JLabel.CENTER);
			jtf[i] = new JTextField();
		}
		bt.addActionListener(this);
	}

	public void set_layout() {
		jp1.setLayout(new BorderLayout());
		jp1_1.setLayout(new GridLayout(1, 4, 5, 5));
		jp1_2.setLayout(new GridLayout(1, 4, 5, 5));
		for (int i = 0; i < 4; i++) {
			jp1_1.add(jpb[i]);
			jp1_2.add(jl[i]);
		}
		jp1.add(jp1_1, new BorderLayout().CENTER);
		jp1.add(jp1_2, new BorderLayout().SOUTH);

		jp2.setLayout(new BorderLayout());
		jp2_1.setLayout(new GridLayout(2, 3));
		for (int i = 0; i < 3; i++) {
			jp2_1.add(jl_date[i]);
		}
		for (int i = 0; i < 3; i++) {
			jp2_1.add(jtf[i]);
		}
		jp2.add(jl_input, new BorderLayout().NORTH);
		jp2.add(jp2_1, new BorderLayout().CENTER);

		JPanel temp = new JPanel();
		temp.setLayout(new BorderLayout());
		temp.add(jp1, new BorderLayout().CENTER);
		temp.add(jp2, new BorderLayout().SOUTH);

		ct.setLayout(new BorderLayout());
		ct.add(temp, new BorderLayout().CENTER);
		ct.add(bt, new BorderLayout().SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GregorianCalendar today = new GregorianCalendar();
		GregorianCalendar birthday = new GregorianCalendar();

		int year = Integer.parseInt(jtf[0].getText());
		int month = Integer.parseInt(jtf[1].getText());
		int day = Integer.parseInt(jtf[2].getText());

		birthday.set(year, month - 1, day);
		// 24hours 60minutes 60seconds 1000milliseconds
		long b_temp = birthday.getTimeInMillis() / (24 * 60 * 60 * 1000);
		long t_temp = today.getTimeInMillis() / (24 * 60 * 60 * 1000);
		long temp = t_temp - b_temp;
		int days = (int) temp;

		float[] bios = new float[4];
		bios[0] = (float) (Math.sin(2 * Math.PI * days / 23) * 100);
		bios[1] = (float) (Math.sin(2 * Math.PI * days / 28) * 100);
		bios[2] = (float) (Math.sin(2 * Math.PI * days / 33) * 100);
		bios[3] = (float) (Math.sin(2 * Math.PI * days / 38) * 100);

		for (int i = 0; i < 4; i++) {
			jpb[i].setValue((int) bios[i]);
			jl[i].setText(jl_text[i] + ":" + String.format("%,.2f", bios[i]));
		}

		for (int i = 0; i < 4; i++) {
			if (jpb[i].getValue() >= 0) {
				jpb[i].setForeground(Color.GREEN);
			} else {
				jpb[i].setForeground(Color.RED);
			}
		}
	}
}
