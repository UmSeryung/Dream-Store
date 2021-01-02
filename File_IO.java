package _20200102_Biorhythm_TCP;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File_IO {
	String input_path = "bio.txt";
	String output_path = "bio.txt";
	FileWriter fw;
	FileReader fr;

	public File_IO() {
		try {
			fw = new FileWriter(output_path);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String read_file() {
		int i;
		String str = "";
		try {
			fr = new FileReader(input_path);
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
			fw = new FileWriter(output_path, true);
			char input[] = new char[str.length()];
			str.getChars(0, str.length(), input, 0);
			fw.write(input);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
