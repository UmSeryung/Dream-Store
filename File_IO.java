package _20210105_WordCounter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File_IO {
	FileReader fr;
	FileWriter fw;

	String input_filename;// 읽어오는 거
	String output_filename = "wordcount.txt"; // 결과 출력 및 저장

	public File_IO() {
		try {
			fw = new FileWriter(output_filename);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String read_file(String input_filename) {
		this.input_filename = input_filename;
		int i;
		String str = "";
		try {
			fr = new FileReader(input_filename);
			while ((i = fr.read()) != -1) {
				str = str + (char) i;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public void write_file(String str) {
		try {
			fw = new FileWriter(output_filename, true);
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
