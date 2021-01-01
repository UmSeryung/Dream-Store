package _4번_바이오리듬계산_날짜계산;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File_IO {
	BufferedReader br;
	BufferedWriter bw;

	String input_path = "4번_rhythm.txt"; // 읽어오는 거
	String output_path = "4번_rhythm.txt"; // 결과 출력 및 저장

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
