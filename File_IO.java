package _20210104_ScoreManagement;

import java.io.FileWriter;
import java.io.IOException;

public class File_IO {
	FileWriter fw;
	String output_path = "Result.txt";
	
	File_IO(){
		try {
			fw = new FileWriter(output_path);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write_file(String str) {
		try {
			fw = new FileWriter(output_path, true);
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}