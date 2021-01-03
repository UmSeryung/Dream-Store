package _20210103_ExchageRateCalc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ExchangeRateCalc extends JFrame implements ActionListener {
	Container ct;
	JPanel jp1 = new JPanel();
	JCheckBox[] jcb = new JCheckBox[4];
	String[] jcb_text = new String[] { "USD", "KRW", "CNY", "EUR" };
	JTextField jtf = new JTextField();

	JPanel jp2 = new JPanel();
	String[] list_text = new String[] { "USD", "KRW", "CNY", "EUR" };
	JList list = new JList(list_text);

	JTextArea jta = new JTextArea();
	JButton bt = new JButton("Exchange Rate");

	boolean[] list_check = new boolean[4];

	double usd;

	public ExchangeRateCalc() {
		set_component();
		set_layout();

		setTitle("Exchange Rate Calculation");
		setSize(500, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public void set_component() {
		ct = getContentPane();
		for (int i = 0; i < 4; i++) {
			jcb[i] = new JCheckBox();
			jcb[i].setText(jcb_text[i]);
			jcb[i].addActionListener(this);
		}
		bt.addActionListener(this);
	}

	public void set_layout() {
		jp1.setLayout(new GridLayout(1, 2));
		JPanel jp1_temp = new JPanel();
		jp1_temp.setLayout(new GridLayout(1, 4));
		for (int i = 0; i < 4; i++) {
			jp1_temp.add(jcb[i]);
		}
		jp1.add(jp1_temp);
		jp1.add(jtf);

		jp2.setLayout(new BorderLayout());
		JPanel jp2_temp = new JPanel();
		jp2_temp.setLayout(new GridLayout(1, 2));
		jp2_temp.add(list);
		jp2_temp.add(jta);
		jp2.add(jp2_temp, new BorderLayout().CENTER);
		jp2.add(bt, new BorderLayout().EAST);
		ct.setLayout(new BorderLayout());
		ct.add(jp1, new BorderLayout().NORTH);
		ct.add(jp2, new BorderLayout().CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt) {
			for (int i = 0; i < list.getSelectedIndices().length; i++) {
				list_check[list.getSelectedIndices()[i]] = true;
			}
			print();
		} else if (e.getSource() == jcb[0]) { // usd
			usd = Double.parseDouble(jtf.getText());
		} else if (e.getSource() == jcb[1]) { // krw
			usd = Double.parseDouble(jtf.getText()) / 1130;
			System.out.println(usd);
		} else if (e.getSource() == jcb[2]) { // cny
			usd = Double.parseDouble(jtf.getText()) / 6.64;
		} else if (e.getSource() == jcb[3]) { // eur
			usd = Double.parseDouble(jtf.getText()) / 0.85;
		}
	}

	public void print() {
		for (int i = 0; i < 4; i++) {
			if (list_check[i] == true) {
				if (i == 0) {
					jta.append("USD : " + Math.round(usd * 100) / 100.0 + "\n");
				} else if (i == 1) {
					jta.append("KRW : " + Math.round(usd * 1130 * 100) / 100.0 + "\n");
				} else if (i == 2) {
					jta.append("CNY : " + Math.round(usd * 6.64 * 100) / 100.0 + "\n");
				} else if (i == 3) {
					jta.append("EUR : " + Math.round(usd * 0.85 * 100) / 100.0 + "\n");
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExchangeRateCalc erc = new ExchangeRateCalc();
	}

}
