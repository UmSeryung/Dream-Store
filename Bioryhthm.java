package _4��_���̿�������_��¥���;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class Bioryhthm {
	File_IO fio;
	int[] days = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	// �������
	int b_year;
	int b_month;
	int b_day;
	// ���� ��¥
	int t_year;
	int t_month;
	int t_day;

	int count = 0;
	int count_year = 0; // ���� ��
	int count_days = 0; // ���� ���ñ����� ��¥ ��

	public Bioryhthm() {
		fio = new File_IO();
		input_data();
		// calculator();
		test();
		print_result();
	}

	public void test() {
		GregorianCalendar today = new GregorianCalendar();
		GregorianCalendar birthday = new GregorianCalendar();
		birthday.set(b_year, b_month - 1, b_day);
		for (int i = 0; i < days[t_month - 1] - t_day + 1; i++) {
			today.set(t_year, t_month, t_day + i);
			long b_temp = birthday.getTimeInMillis() / (24 * 60 * 60 * 1000);// 24�ð� 60�� 60�� 1000
			long t_temp = today.getTimeInMillis() / (24 * 60 * 60 * 1000);
			long temp = t_temp - b_temp;
			int days = (int) temp;

			int[] bios = new int[3];
			bios[0] = (int) (Math.sin(2 * Math.PI * days / 23) * 100);
			bios[1] = (int) (Math.sin(2 * Math.PI * days / 28) * 100);
			bios[2] = (int) (Math.sin(2 * Math.PI * days / 33) * 100);
			fio.write_file(t_year + "-" + t_month + "-" + (t_day + i) + "\t" + bios[0] + "\t" + bios[1] + "\t" + bios[2]
					+ "\r\n");
		}
	}

	public void input_data() {
		Scanner sc = new Scanner(System.in);
		System.out.println("**���̿� ����**");
		System.out.print("���� year : ");
		b_year = sc.nextInt();
		System.out.print("���� month : ");
		b_month = sc.nextInt();
		System.out.print("���� day : ");
		b_day = sc.nextInt();
		System.out.print("���� year : ");
		t_year = sc.nextInt();
		System.out.print("���� month : ");
		t_month = sc.nextInt();
		System.out.print("���� day : ");
		t_day = sc.nextInt();
	}

	public void calculator() {
		set_count(); // �����ϼ� ���ϱ�. ���� �ϼ� �����Ѱɷ�
		for (int i = 0; i < days[t_month - 1] - t_day + 1; i++) {
			count = count + t_day + i; // ���� �ϼ� ���ϱ�
			// ��ü, ����, ����
			int bio1 = (int) (Math.sin((count / 23) * 2 * Math.PI) * 100);
			int bio2 = (int) (Math.sin((count / 28) * 2 * Math.PI) * 100);
			int bio3 = (int) (Math.sin((count / 33) * 2 * Math.PI) * 100);
			fio.write_file(
					t_year + "-" + t_month + "-" + (t_day + i) + "\t" + bio1 + "\t" + bio2 + "\t" + bio3 + "\r\n");
			count = count - t_day - i;
		}
	}

	public void set_count() {
		for (int i = b_year; i < t_year; i++) {
			if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) { // ����Ȯ��
				count_year++;
			}
		}
		if (t_year % 4 == 0 && t_year % 100 != 0 || t_year % 400 == 0) {
			days[1] = 29;
		}
		for (int i = 1; i < t_month; i++) {
			count_days += days[i - 1];
		}
		count = (t_year - b_year - 1) * 365 + (12 - b_month) * 30 + (35 - b_day) + count_year + count_days; // �̹� �� //
																											// �����ϼ��� ����
	}

	public void print_result() {
		System.out.println("*** ������ 0 �� �� ***");
		String str = fio.read_file();
		String[] temp = str.split("\r\n");
		for (int i = 0; i < temp.length; i++) {
			String[] temp2 = temp[i].split("\t");
			String date = temp2[0];
			int b1 = Integer.parseInt(temp2[1]); // ��ü
			int b2 = Integer.parseInt(temp2[2]); // ����
			int b3 = Integer.parseInt(temp2[3]); // ����
			if (b1 == 0 || b2 == 0 || b3 == 0) {
				System.out.println(date + " �� ���� : " + b1 + ", " + b2 + ", " + b3);
			}
		}
	}
}
