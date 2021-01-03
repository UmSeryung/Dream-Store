package _20210102_Biorhythm_TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Server_Thread extends Thread {
	int port;
	ServerSocket ss;

	BufferedReader br;
	BufferedWriter bw;

	public Server_Thread(int port) {
		this.port = port;
		try {
			ss = new ServerSocket(port);
			Socket socket = ss.accept();
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendMsg(String msg) {
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
				String result = calculation(str);
				sendMsg(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String calculation(String str) {
		String[] temp = str.split("-");
		int year = Integer.parseInt(temp[0]);
		int month = Integer.parseInt(temp[1]);
		int day = Integer.parseInt(temp[2]);

		LocalDate birthday = LocalDate.of(year, month, day);
		LocalDate now = LocalDate.now();

		long time = ChronoUnit.DAYS.between(birthday, now);

		float[] bios = new float[4];
		bios[0] = (float) (Math.sin(2 * Math.PI * time / 23) * 100);
		bios[1] = (float) (Math.sin(2 * Math.PI * time / 28) * 100);
		bios[2] = (float) (Math.sin(2 * Math.PI * time / 33) * 100);
		bios[3] = (float) (Math.sin(2 * Math.PI * time / 38) * 100);

		String s = "";
		for (int i = 0; i < 4; i++) {
			s += String.format("%,.2f", bios[i]) + ",";
		}
		return s;
	}
}
