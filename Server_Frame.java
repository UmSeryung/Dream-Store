package _20200102_Biorhythm_TCP;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Server_Frame extends JFrame implements ActionListener {
	Server_Thread st;
	Container ct;
	JLabel jl_port = new JLabel("Server Port", JLabel.CENTER);
	JLabel jl_none = new JLabel();
	JTextField jtf = new JTextField();
	JButton bt = new JButton("set");

	public Server_Frame() {
		ct = getContentPane();
		bt.addActionListener(this);

		ct.setLayout(new GridLayout(2, 2));
		ct.add(jl_port);
		ct.add(jl_none);
		ct.add(jtf);
		ct.add(bt);

		setTitle("TCP Biothythm Server");
		setSize(350, 100);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int port = Integer.parseInt(jtf.getText());
		st = new Server_Thread(port);
		st.start();
	}
}
