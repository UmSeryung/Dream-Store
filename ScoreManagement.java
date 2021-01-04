package _20210104_ScoreManagement;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ScoreManagement extends JFrame implements ActionListener {
	File_IO fio;
	Container ct = getContentPane();
	JPanel jp1 = new JPanel();
	String[] title = new String[] { "Name", "Korean", "English", "Math", "Avg" };
	String[][] text = new String[0][5]; // 내용은 비어잇음
	DefaultTableModel dtable = new DefaultTableModel(text, title); // 모델과 데이터 연결
	JTable jtable = new JTable(dtable); // 삽입삭제 하기위해 사용
	JScrollPane jsp = new JScrollPane(jtable);

	JPanel jp2 = new JPanel();
	JLabel[] jl = new JLabel[5];
	String[] jl_text = new String[] { "Name", "Korean", "English", "Math", "Avg" };
	JTextField[] jtf = new JTextField[4];
	JButton bt = new JButton("Add");

	int num = 0;

	public ScoreManagement() {
		set_component();
		set_layout();
		setTitle("Score Management Program");
		setSize(500, 400); // 크기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public void set_component() {
		fio = new File_IO();
		add(jsp);
		for (int i = 0; i < 5; i++)
			jl[i] = new JLabel(jl_text[i], JLabel.CENTER);
		for (int i = 0; i < 4; i++)
			jtf[i] = new JTextField();
		bt.addActionListener(this);
	}

	public void set_layout() {
		jp1.setLayout(new BorderLayout());
		jp2.setLayout(new GridLayout(2, 5));
		for (int i = 0; i < 5; i++)
			jp2.add(jl[i]);
		for (int i = 0; i < 4; i++)
			jp2.add(jtf[i]);
		jp2.add(bt);
		ct.setLayout(new BorderLayout());
		ct.add(jsp, new BorderLayout().CENTER);
		ct.add(jp2, new BorderLayout().SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel m = (DefaultTableModel) jtable.getModel();
		String name = jtf[0].getText();
		int korean = Integer.parseInt(jtf[1].getText());
		int english = Integer.parseInt(jtf[2].getText());
		int math = Integer.parseInt(jtf[3].getText());
		int average = (korean + english + math) / 3;
		m.insertRow(num, new Object[] { name, korean, english, math, average });
		jtable.updateUI();
		num++;
		fio.write_file(name + "\t" + korean + "\t" + english + "\t" + math + "\t" + average + "\r\n");
		for (int i = 0; i < 4; i++)
			jtf[i].setText("");
	}
}
