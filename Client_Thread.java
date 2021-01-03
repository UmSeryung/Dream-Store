package _20210102_Biorhythm_TCP;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Client_Thread extends Thread {
	String ip;
	int port;

	Socket socket;
	BufferedReader br;
	BufferedWriter bw;

	JProgressBar[] jpb;
	JLabel[] jl;

	public Client_Thread(String ip, int port) throws UnknownHostException, IOException {
		this.ip = ip;
		this.port = port;
		socket = new Socket(ip, port);
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	public void set(JProgressBar[] jpb, JLabel[] jl) {
		this.jpb = jpb;
		this.jl = jl;
	}

	public void sendMSg(String msg) {
		try {
			bw.write(msg);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() { // ¹ÞÀº°Å
		while (true) {
			try {
				String str = br.readLine();
				str = str.trim();
				File_IO fio = new File_IO();
				fio.write_file(str);
				print(fio.read_file());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void print(String str) {
		String[] temp = str.split(",");
		double bios1 = Double.parseDouble(temp[0]);
		double bios2 = Double.parseDouble(temp[1]);
		double bios3 = Double.parseDouble(temp[2]);
		double bios4 = Double.parseDouble(temp[3]);

		jpb[0].setValue((int) bios1);
		jpb[1].setValue((int) bios2);
		jpb[2].setValue((int) bios3);
		jpb[3].setValue((int) bios4);

		for (int i = 0; i < 4; i++) {
			if (jpb[i].getValue() < 0) {
				jpb[i].setForeground(Color.RED);
			} else {
				jpb[i].setForeground(Color.GREEN);
			}
		}

		jl[0].setText(jl[0].getText() + " : " + bios1);
		jl[1].setText(jl[1].getText() + " : " + bios2);
		jl[2].setText(jl[2].getText() + " : " + bios3);
		jl[3].setText(jl[3].getText() + " : " + bios4);
	}
}
