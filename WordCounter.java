package _20210105_WordCounter;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;

public class WordCounter extends JFrame implements ActionListener {
	File_IO fio;
	Container ct;
	
	JPanel jp1 = new JPanel();
	JTextArea jta = new JTextArea();
	DefaultListModel dlm = new DefaultListModel();
	JList jlist = new JList(dlm);
	JScrollPane jsp = new JScrollPane(jlist); // �׳� �߰��Ѱ���

	JPanel jp2 = new JPanel();
	JTextField jtf = new JTextField();
	JButton bt_read = new JButton("���� �ҷ�����");
	JButton bt_count = new JButton("�ܾ ���� ����");
	JButton bt_print = new JButton("��� ���� ��������");

	String text = "";
	int list_count = 0; //����Ʈ ���� ����
	String[] str_list; // �ܾ� ���� �迭
	int[] str_count; // �ܾ� ���� ���� �迭

	public WordCounter() {
		set_component();
		set_layout();

		setTitle("Word Counter");
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public void set_component() {
		ct = getContentPane();
		fio = new File_IO();
		
		add(jsp);

		bt_read.addActionListener(this);
		bt_count.addActionListener(this);
		bt_print.addActionListener(this);
	}

	public void set_layout() {
		jp1.setLayout(new GridLayout(1, 2));
		jp1.add(jta);
		jp1.add(jsp);

		jp2.setLayout(new GridLayout(2, 2));
		jp2.add(jtf);
		jp2.add(bt_read);
		jp2.add(bt_count);
		jp2.add(bt_print);

		ct.setLayout(new BorderLayout());
		ct.add(jp1, new BorderLayout().CENTER);
		ct.add(jp2, new BorderLayout().SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton temp = (JButton) e.getSource();

		if (temp == bt_read) {
			text = fio.read_file(jtf.getText());
			jta.setText(text);
		} else if (temp == bt_count) {
			set_strlist(); // ����Ʈ�� ���� �迭 ����
			summary();// ����Ʈ �߰��� ���ÿ� ����Ʈ ���� ��ȯ
		} else if (temp == bt_print) {
			for (int i = 0; i < list_count; i++) {
				jlist.setSelectedIndex(i);
				String s = (String) jlist.getSelectedValue();
				fio.write_file(s + "\r\n");
			}
		}
	}

	public void set_strlist() {
		StringTokenizer st = new StringTokenizer(text, "() ,.\r\n*-/");
		str_list = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			str_list[i] = st.nextToken();
			i++;
		}
		str_count = new int[str_list.length];
	}

	public void summary() {
		ListModel model = jlist.getModel();
		int i = 0;
		while (i < str_list.length) {
			if (str_list[i].equals("")) {
				i++;
				continue;
			}
			str_count[i] = 1;
			for (int j = i + 1; j < str_list.length; j++) {
				if (str_list[i].equals(str_list[j])) {
					str_count[i]++;
					str_list[j] = "";
				}		
			}
			list_count++; //����Ʈ ���� �����ϴ°�
			if (str_count[i] == 0) {
				i++;
				continue;
			}
			dlm.addElement(str_list[i] + " " + str_count[i]);
			i++;
		}
	}

}