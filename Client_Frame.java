package _20210102_Biorhythm_TCP;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class Client_Frame extends JFrame implements ActionListener {
	Client_Thread ct;
	Container cct;
	JPanel jp1 = new JPanel();
	JLabel jl_ip = new JLabel("Server IP", JLabel.CENTER);
	JLabel jl_port = new JLabel("Server Port", JLabel.CENTER);
	JLabel jl_none = new JLabel();
	JTextField jtf_ip = new JTextField();
	JTextField jtf_port = new JTextField();
	JButton bt_set = new JButton("connect"); //set

	JProgressBar[] jpb = new JProgressBar[4];
	JLabel jl[] = new JLabel[4];
	String[] jl_text = new String[] { "Body", "Emotional", "Intellectual", "Perception" };
	JLabel jl_input = new JLabel("Enter date of birth", JLabel.CENTER);
	JLabel[] jl_date = new JLabel[3];
	String[] jl_date_text = new String[] { "Year", "Month", "Day" };
	JTextField[] jtf_date = new JTextField[3];
	JButton bt = new JButton("Calculation");

	public Client_Frame() {
		set_component();
		set_layout();

		setTitle("TCP Biothythm Client");
		setSize(500, 400);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void set_component() {
		cct = getContentPane();
		for (int i = 0; i < 4; i++) {
			jpb[i] = new JProgressBar(JProgressBar.VERTICAL);
			jpb[i].setMaximum(100);
			jpb[i].setMinimum(-100);
			jl[i] = new JLabel(jl_text[i], JLabel.CENTER);
		}

		for (int i = 0; i < 3; i++) {
			jl_date[i] = new JLabel(jl_date_text[i], JLabel.CENTER);
			jtf_date[i] = new JTextField();
		}
		bt_set.addActionListener(this);
		bt.setEnabled(false);
		bt.addActionListener(this);
	}

	public void set_layout() {
		jp1.setLayout(new GridLayout(2, 3));
		jp1.add(jl_ip);
		jp1.add(jl_port);
		jp1.add(jl_none);
		jp1.add(jtf_ip);
		jp1.add(jtf_port);
		jp1.add(bt_set);

		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(1, 4, 5, 5));
		for (int i = 0; i < 4; i++) {
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			temp.add(jpb[i], BorderLayout.CENTER);
			temp.add(jl[i], BorderLayout.SOUTH);
			jp2.add(temp);
		}

		JPanel jp3 = new JPanel();
		jp3.setLayout(new GridLayout(2, 3));
		for (int i = 0; i < 3; i++) {
			jp3.add(jl_date[i]);
		}
		for (int i = 0; i < 3; i++) {
			jp3.add(jtf_date[i]);
		}

		JPanel jp4 = new JPanel();
		jp4.setLayout(new BorderLayout());
		jp4.add(jl_input, BorderLayout.NORTH);
		jp4.add(jp3, BorderLayout.CENTER);

		JPanel jp5 = new JPanel();
		jp5.setLayout(new BorderLayout());
		jp5.add(jp2, BorderLayout.CENTER);
		jp5.add(jp4, BorderLayout.SOUTH);

		cct.setLayout(new BorderLayout());
		cct.add(jp1, BorderLayout.NORTH);
		cct.add(jp5, BorderLayout.CENTER);
		cct.add(bt, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		
		if (s.equals("connect")) {
			try {
				ct = new Client_Thread(jtf_ip.getText(), Integer.parseInt(jtf_port.getText()));
				ct.start();
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			bt_set.setEnabled(false);
			bt.setEnabled(true);
		} else if (s.equals("Calculation")) {
			ct.set(jpb, jl);
			ct.sendMSg(jtf_date[0].getText()+"-"+jtf_date[1].getText()+"-"+jtf_date[2].getText());
		}

	}

}
