package _4��_���̿�������_��¥���;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File_IO {
	BufferedReader br;
	BufferedWriter bw;

	String input_path = "4��_rhythm.txt"; // �о���� ��
	String output_path = "4��_rhythm.txt"; // ��� ��� �� ����

	public File_IO() {
		try {
			bw = new BufferedWriter(new FileWriter(output_path));
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String read_file() {
		String temp = "";
		String str = "";
		try {
			br = new BufferedReader(new FileReader(input_path));
			for (int i = 1; (temp = br.readLine()) != null; i++) {
				str += temp + "\r\n";
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return str;
	}

	public void write_file(String str) {
		try {
			bw = new BufferedWriter(new FileWriter(output_path, true));
			bw.write(str);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
