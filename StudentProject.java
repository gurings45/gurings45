package student_project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentProject {
	//�޴� : �����Է�, ����������ȸ, ��ü������ȸ, ����������, ��������, ��������, ����
	public static final int INSERT=1, SEARCH=2, PRINT=3, SORT=4, UPDATE=5, DELETE=6, EXIT=7;
	
	//��ĳ�� �����ϱ�
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		int menu = 0;
		boolean flag = false;
		
		//�޴� ���� : �����Է�, ����������ȸ, ��ü������ȸ, ����������, ��������, ��������, ����
		while(!flag) {
			//�޴� ���� �Լ� ȣ��
			menu = selectMenu();
			
			switch(menu) {
			case INSERT: studentInsert(); break;
			case SEARCH: studentSearch(); break;
			case PRINT: studentPrint(); break;
			case SORT: studentSort(); break;
			case UPDATE: studentUpdate(); break;
			case DELETE: studentDelete(); break;
			case EXIT: 
				System.out.println("���� ���α׷� ����.");
				flag = true; break;
			}
		}
	}
	
	//�޴� ����
	private static int selectMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѼ����������α׷��ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
			System.out.println("1. ���� �Է�\t\t 2. ���� ���� ��ȸ\t\t 3. ��ü ���� ��ȸ");
			System.out.println("4. ���� ����\t\t 5. ���� ����\t\t 6. ���� ����");
			System.out.println("7. ���α׷� ����");
			System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
			System.out.print("�޴� ���� >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("���ڸ� �Է� �ٶ�.");
				continue;
			} catch(Exception e) {
				System.out.println("���ڸ� �Է� �ٶ�.");
				continue;
			}
			
			if(menu>0 && menu<8) flag = true;
			else System.out.println("(1~7) ���� �Է� �ٶ�.");
		}
		return menu;
	}
	
	//���� ������ �Է�
	private static void studentInsert() {
		//������� : �й�,�̸�,����,����,����,����,����,���,���
		int id = 0;
		String name = null;
		String gender = null;
		int kor = 0;
		int eng = 0;
		int math = 0;
		int total = 0;
		double avr = 0.0;
		char grade = '\u0000';
		
		//�й� �Է� �Լ� ȣ��
		id = inputID();
		
		//������ ��ü ��ȸ
		List<StudentData> list = new ArrayList<StudentData>();
		list = DBController.dataSelect();
		
		//id �ߺ� Ȯ��
		if(list.size()>0) {
			for(StudentData data : list) {
				if(id==data.getId()) {
					System.out.println("�ߺ� �� �й��Դϴ� ! ");
					return;
				}
			}
		}
		
		//�̸� �Է�
		while(true) {
			System.out.println("�̸��� �Է� �ٶ�. ");
			System.out.print(">> ");
			name = scan.nextLine();
			
			Pattern pattern = Pattern.compile("^[��-�R]*$");
			Matcher matcher = pattern.matcher(name);
			
			if(matcher.matches()) break;
			else System.out.println("�ٽ� �Է� �ٶ�.");
		}
		
		//���� �Է�
		while(true) {
			System.out.println("������ �Է��� �ּ���.(����)/����)");
			System.out.print(">> ");
			gender = scan.nextLine();
			
			if(gender.equals("����")||gender.equals("����")) break;
			else System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
		}
		
		//����,����,���� ���� �Է� : �����Է� �Լ� ȣ��
		kor = getScore("����");
		eng = getScore("����");
		math = getScore("����");
		
		//StudentData ��ü ����
		StudentData sd = new StudentData(id, name, gender, kor, eng, math);
		
		//����, ���, ��� ��� �Լ� ȣ��(StudentData)
		total = sd.calTotal();
		sd.setTotal(total);
		avr = sd.calAvr();
		sd.setAvr(avr);
		grade = sd.calGrade(avr);
		sd.setGrade(grade);
		
		//�����ͺ��̽��� ���� 
		int result = DBController.dataInsert(sd);
		
		if(result!=0) System.out.println(name+"���� ���� �Է¿Ϸ�.");
		else System.out.println(name+"���� ���� �Է½���.");
	}
	
	//�й� �Է� : 4�ڸ� ����
	private static int inputID() {
		boolean flag = false;
		int id = 0;
		
		while(!flag) {
			System.out.println("�й��� �Է¹ٶ�.");
			System.out.print(">> ");
			try	{
				id = Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("���� �Է� �ٶ�.");
				continue;
			}catch(Exception e) {
				System.out.println("���� �Է� �ٶ�.");
				continue;
			}
			
			if(id>999 && id<10000) flag = true;
			else System.out.println("XXXX �Է� �ٶ�.");
		}
		
		return id;
	}
	
	//���� �Է�: �Ű����� ����(����, ����, ����)
	private static int getScore(String subject) {
		boolean flag = false;
		int score = 0;
		
		while(!flag) {
			System.out.println(subject+" ���� �Է¹ٶ�.");
			System.out.print(">> ");
			try	{
				score = Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("���� �Է¹ٶ�.");
				continue;
			}catch(Exception e) {
				System.out.println("���� �Է¹ٶ�.");
				continue;
			}
			
			if(score>=0 && score<=100) flag = true;
			else System.out.println("�ٽ� �Է¹ٶ�.");
		}
		
		return score;
	}
	
	//���� ���� ��ȸ : �й�,�̸����� �˻�
	private static void studentSearch() {
		final int ID=1, NAME=2, EXIT=3;
		int menu = 0;
		int id = 0;
		String name = null;
		String searchData = null;
		int num = 0;
		
		//��ȸ �޴� ����
		menu = searchMenu();
		
		switch(menu) {
		case ID: 
			id = inputID();
			searchData = String.valueOf(id);
			num = ID;
			break;
		case NAME: 
			while(true) {
				System.out.println("ã�� �̸� ���� �ٶ�.");
				System.out.print(">> ");
				name = scan.nextLine();
				
				Pattern pattern = Pattern.compile("^[��-�R]*$");
				Matcher matcher = pattern.matcher(name);
				
				if(matcher.matches()) break;
				else System.out.println("�ٽ� �Է����ּ���.");
			}
			
			searchData = name;
			num = NAME;
			break;
		case EXIT: 
			System.out.println("�˻��� ���.");
			return;
		}
		
		//�����ͺ��̽� ��ȸ : ���� ������
		List<StudentData> list = new ArrayList<StudentData>();
		list = DBController.dataSearch(searchData, num);
		
		if(list.size()<=0) {
			System.out.println(searchData+"ã�� �� ����.");
			return;
		}
		
		//��ȸ ��� ���
		dataPrint(list);
	}

	//���� ���� ��ȸ �޴� ���� : �й�,�̸�
	private static int searchMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
			System.out.println("   1. �й����� �˻�\t\t 2. �̸����� �˻�\t\t 3. �˻� ���");
			System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
			System.out.print("�޴� ���� >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("���� �Է¹ٶ�.");
				continue;
			} catch(Exception e) {
				System.out.println("���ڸ� �Է¹ٶ�.");
				continue;
			}
			
			if(menu>0 && menu<4) flag = true;
			else System.out.println("1~3 ������ ���� �Է¹ٶ�.");
		}
		
		return menu;
	}
	
	//��ü ���� ��ȸ 
	private static void studentPrint() {
		List<StudentData> list = new ArrayList<StudentData>();
		
		//�����ͺ��̽� ��ȸ : ��ü ������
		list = DBController.dataSelect();
		
		if(list.size()<=0) {
			System.out.println("�Էµ� �����Ͱ� ����.");
			return;
		}
		
		//������ ���
		dataPrint(list);
		
		//��ü �л���, ��ü ����, ��ü ��� ���, ���� ���
		int count = list.size();
		int sum = 0;
		double totalAvr = 0.0;
		double korAvr = 0.0;
		double engAvr = 0.0;
		double mathAvr = 0.0;
		
		for(StudentData data : list) {
			sum += data.getTotal();
			totalAvr += data.getAvr();
			korAvr += data.getKor();
			engAvr += data.getEng();
			mathAvr += data.getMath();
		}
		
		totalAvr /= (double)count;
		korAvr /= (double)count;
		engAvr /= (double)count;
		mathAvr /= (double)count;
		
		System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
		System.out.println("[��ü �л���] "+count +"�� \t\t [��ü ����] "+sum+"�� \t [��ü ���] "+
							String.format("%.2f", totalAvr)+"��");
		System.out.println("[���� ���] "+String.format("%.2f", korAvr)+"�� \t [���� ���] "+String.format("%.2f", engAvr)
							+"�� \t [���� ���] "+String.format("%.2f", mathAvr)+"��");
		System.out.println("");
	}
	
	//���� ���� : �й���,�̸���,������
	private static void studentSort() {
		//��ȸ �޴� ����
		int menu = sortMenu();
		
		if(menu==4) {
			System.out.println("������ ���.");
			return;
		}

		//�����ͺ��̽� ��ȸ : ������ ����
		List<StudentData> list = new ArrayList<StudentData>();
		list = DBController.dataSort(menu);
		
		if(list.size()<=0) {
			System.out.println("�Էµ� �����Ͱ� ����.");
			return;
		}
		
		//���ĵ� ���� ���
		dataPrint(list);
	}
	
	//���� ���� �޴� ���� : �й�, �̸�, ����
	private static int sortMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
			System.out.println("   1. �й��� ����\t 2. �̸��� ����\t 3. ������ ����");
			System.out.println("   4. ���� ���");
			System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
			System.out.print("�޴� ���� >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("���ڸ� �Է¹ٶ�.");
				continue;
			} catch(Exception e) {
				System.out.println("���ڸ� �Է¹ٶ�.");
				continue;
			}
			
			if(menu>0 && menu<5) flag = true;
			else System.out.println("1~4 ������ ���� �Է¹ٶ�.");
		}
		
		return menu;
	}
	
	//������ ���
	private static void dataPrint(List<StudentData> list) {
		System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
		System.out.println("�й�\t"+"�̸�\t"+"����\t"+" ����\t"+" ����\t"+" ����\t"+" ����\t"+"  ���\t"+" ���");
		System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
		for(StudentData data : list) System.out.println(data);
	}
	
	//���� ���� : �й����� ��ȸ, ����(����,����,����) �����Ͽ� ����
	private static void studentUpdate() {
		final int KOR=1, ENG=2, MATH=3, EXIT=4;
		int id = 0;
		int menu = 0;
		int kor = 0;
		int eng = 0;
		int math = 0;
		int result = 0;
		
		//�й��Է�
		id = inputID();
		
		//���� ���� ������ Ȯ�� : �й����� ������ ��ȸ
		List<StudentData> list = new ArrayList<StudentData>();
		list = DBController.dataSearch(String.valueOf(id), 1);
		
		if(list.size()<=0) {
			System.out.println("�Էµ� �����Ͱ� ����.");
			return;
		}
		
		System.out.println("���� ���� >>>");
		dataPrint(list);
		
		//StudentData ��ü�� ����
		StudentData sd = list.get(0);
		
		//������ ���� ����
		menu = updateMenu();
		
		switch(menu) {
		case KOR : 
			kor = getScore("����");
			sd.setKor(kor); break;
		case ENG : 
			eng = getScore("����"); 
			sd.setEng(eng);	break;
		case MATH : 
			math = getScore("����"); 
			sd.setMath(math); break;
		case EXIT : 
			System.out.println("���� ���");
			return;
		}
		
		//����,���,��� ���
		int total = sd.calTotal();
		sd.setTotal(total);
		double avr = sd.calAvr();
		sd.setAvr(avr);
		char grade = sd.calGrade(avr);
		sd.setGrade(grade);
		
		//�����ͺ��̽� ����
		result = DBController.dataUpdate(sd, menu);
		
		if(result!=0) System.out.println("���� ���� �Ϸ�.");
		else System.out.println("���� ���� ����.");
	}
	
	//������ ���� ����
	private static int updateMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
			System.out.println("   1. ����� ����\t 2. ����� ����\t 3. ���м��� ����");
			System.out.println("   4. ���� ���");
			System.out.println("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
			System.out.print("�޴� ���� >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("���ڸ� �Է¹ٶ�.");
				continue;
			} catch(Exception e) {
				System.out.println("���ڸ� �Է¹ٶ�.");
				continue;
			}
			
			if(menu>0 && menu<5) flag = true;
			else System.out.println("1~4 ������ ���ڸ� �Է¹ٶ�.");
		}
		
		return menu;
	}

	//���� ���� : �й����� �˻�
	private static void studentDelete() {
		int id = 0;
		int result = 0;
		
		//�й� �Է�
		id = inputID();
		
		//�����ͺ��̽� ����
		result = DBController.dataDelete(id);

		if(result!=0) System.out.println("���� ���� �Ϸ�.");
		else System.out.println("���� ���� ����.");
	}
}