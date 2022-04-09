package student_project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentProject {
	//¸Þ´º : ¼ºÀûÀÔ·Â, °³º°¼ºÀûÁ¶È¸, ÀüÃ¼¼ºÀûÁ¶È¸, ¼ºÀû¼øÁ¤·Ä, ¼ºÀû¼öÁ¤, ¼ºÀû»èÁ¦, Á¾·á
	public static final int INSERT=1, SEARCH=2, PRINT=3, SORT=4, UPDATE=5, DELETE=6, EXIT=7;
	
	//½ºÄ³³Ê »ý¼ºÇÏ±â
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		int menu = 0;
		boolean flag = false;
		
		//¸Þ´º ¼±ÅÃ : ¼ºÀûÀÔ·Â, °³º°¼ºÀûÁ¶È¸, ÀüÃ¼¼ºÀûÁ¶È¸, ¼ºÀû¼øÁ¤·Ä, ¼ºÀû¼öÁ¤, ¼ºÀû»èÁ¦, Á¾·á
		while(!flag) {
			//¸Þ´º ¼±ÅÃ ÇÔ¼ö È£Ãâ
			menu = selectMenu();
			
			switch(menu) {
			case INSERT: studentInsert(); break;
			case SEARCH: studentSearch(); break;
			case PRINT: studentPrint(); break;
			case SORT: studentSort(); break;
			case UPDATE: studentUpdate(); break;
			case DELETE: studentDelete(); break;
			case EXIT: 
				System.out.println("¼ºÀû ÇÁ·Î±×·¥ Á¾·á.");
				flag = true; break;
			}
		}
	}
	
	//¸Þ´º ¼±ÅÃ
	private static int selectMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¼ºÀû°ü¸®ÇÁ·Î±×·¥¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ");
			System.out.println("1. ¼ºÀû ÀÔ·Â\t\t 2. °³º° ¼ºÀû Á¶È¸\t\t 3. ÀüÃ¼ ¼ºÀû Á¶È¸");
			System.out.println("4. ¼ºÀû Á¤·Ä\t\t 5. ¼ºÀû ¼öÁ¤\t\t 6. ¼ºÀû »èÁ¦");
			System.out.println("7. ÇÁ·Î±×·¥ Á¾·á");
			System.out.println("¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ");
			System.out.print("¸Þ´º ¼±ÅÃ >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("¼ýÀÚ¸¦ ÀÔ·Â ¹Ù¶÷.");
				continue;
			} catch(Exception e) {
				System.out.println("¼ýÀÚ¸¦ ÀÔ·Â ¹Ù¶÷.");
				continue;
			}
			
			if(menu>0 && menu<8) flag = true;
			else System.out.println("(1~7) ¼ýÀÚ ÀÔ·Â ¹Ù¶÷.");
		}
		return menu;
	}
	
	//¼ºÀû µ¥ÀÌÅÍ ÀÔ·Â
	private static void studentInsert() {
		//¸â¹öº¯¼ö : ÇÐ¹ø,ÀÌ¸§,¼ºº°,±¹¾î,¿µ¾î,¼öÇÐ,ÃÑÁ¡,Æò±Õ,µî±Þ
		int id = 0;
		String name = null;
		String gender = null;
		int kor = 0;
		int eng = 0;
		int math = 0;
		int total = 0;
		double avr = 0.0;
		char grade = '\u0000';
		
		//ÇÐ¹ø ÀÔ·Â ÇÔ¼ö È£Ãâ
		id = inputID();
		
		//µ¥ÀÌÅÍ ÀüÃ¼ Á¶È¸
		List<StudentData> list = new ArrayList<StudentData>();
		list = DBController.dataSelect();
		
		//id Áßº¹ È®ÀÎ
		if(list.size()>0) {
			for(StudentData data : list) {
				if(id==data.getId()) {
					System.out.println("Áßº¹ µÈ ÇÐ¹øÀÔ´Ï´Ù ! ");
					return;
				}
			}
		}
		
		//ÀÌ¸§ ÀÔ·Â
		while(true) {
			System.out.println("ÀÌ¸§À» ÀÔ·Â ¹Ù¶÷. ");
			System.out.print(">> ");
			name = scan.nextLine();
			
			Pattern pattern = Pattern.compile("^[°¡-ÆR]*$");
			Matcher matcher = pattern.matcher(name);
			
			if(matcher.matches()) break;
			else System.out.println("´Ù½Ã ÀÔ·Â ¹Ù¶÷.");
		}
		
		//¼ºº° ÀÔ·Â
		while(true) {
			System.out.println("¼ºº°À» ÀÔ·ÂÇØ ÁÖ¼¼¿ä.(³²¼º)/¿©¼º)");
			System.out.print(">> ");
			gender = scan.nextLine();
			
			if(gender.equals("³²¼º")||gender.equals("¿©¼º")) break;
			else System.out.println("Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
		
		//±¹¾î,¿µ¾î,¼öÇÐ ¼ºÀû ÀÔ·Â : ¼ºÀûÀÔ·Â ÇÔ¼ö È£Ãâ
		kor = getScore("±¹¾î");
		eng = getScore("¿µ¾î");
		math = getScore("¼öÇÐ");
		
		//StudentData °´Ã¼ »ý¼º
		StudentData sd = new StudentData(id, name, gender, kor, eng, math);
		
		//ÃÑÇÕ, Æò±Õ, µî±Þ °è»ê ÇÔ¼ö È£Ãâ(StudentData)
		total = sd.calTotal();
		sd.setTotal(total);
		avr = sd.calAvr();
		sd.setAvr(avr);
		grade = sd.calGrade(avr);
		sd.setGrade(grade);
		
		//µ¥ÀÌÅÍº£ÀÌ½º¿¡ ÀúÀå 
		int result = DBController.dataInsert(sd);
		
		if(result!=0) System.out.println(name+"´ÔÀÇ ¼ºÀû ÀÔ·Â¿Ï·á.");
		else System.out.println(name+"´ÔÀÇ ¼ºÀû ÀÔ·Â½ÇÆÐ.");
	}
	
	//ÇÐ¹ø ÀÔ·Â : 4ÀÚ¸® ¼ýÀÚ
	private static int inputID() {
		boolean flag = false;
		int id = 0;
		
		while(!flag) {
			System.out.println("ÇÐ¹øÀ» ÀÔ·Â¹Ù¶÷.");
			System.out.print(">> ");
			try	{
				id = Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("¼ýÀÚ ÀÔ·Â ¹Ù¶÷.");
				continue;
			}catch(Exception e) {
				System.out.println("¼ýÀÚ ÀÔ·Â ¹Ù¶÷.");
				continue;
			}
			
			if(id>999 && id<10000) flag = true;
			else System.out.println("XXXX ÀÔ·Â ¹Ù¶÷.");
		}
		
		return id;
	}
	
	//¼ºÀû ÀÔ·Â: ¸Å°³º¯¼ö °ú¸ñ(±¹¾î, ¿µ¾î, ¼öÇÐ)
	private static int getScore(String subject) {
		boolean flag = false;
		int score = 0;
		
		while(!flag) {
			System.out.println(subject+" Á¡¼ö ÀÔ·Â¹Ù¶÷.");
			System.out.print(">> ");
			try	{
				score = Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("¼ýÀÚ ÀÔ·Â¹Ù¶÷.");
				continue;
			}catch(Exception e) {
				System.out.println("¼ýÀÚ ÀÔ·Â¹Ù¶÷.");
				continue;
			}
			
			if(score>=0 && score<=100) flag = true;
			else System.out.println("´Ù½Ã ÀÔ·Â¹Ù¶÷.");
		}
		
		return score;
	}
	
	//°³º° ¼ºÀû Á¶È¸ : ÇÐ¹ø,ÀÌ¸§À¸·Î °Ë»ö
	private static void studentSearch() {
		final int ID=1, NAME=2, EXIT=3;
		int menu = 0;
		int id = 0;
		String name = null;
		String searchData = null;
		int num = 0;
		
		//Á¶È¸ ¸Þ´º ¼±ÅÃ
		menu = searchMenu();
		
		switch(menu) {
		case ID: 
			id = inputID();
			searchData = String.valueOf(id);
			num = ID;
			break;
		case NAME: 
			while(true) {
				System.out.println("Ã£À» ÀÌ¸§ ¼±ÅÃ ¹Ù¶÷.");
				System.out.print(">> ");
				name = scan.nextLine();
				
				Pattern pattern = Pattern.compile("^[°¡-ÆR]*$");
				Matcher matcher = pattern.matcher(name);
				
				if(matcher.matches()) break;
				else System.out.println("´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			}
			
			searchData = name;
			num = NAME;
			break;
		case EXIT: 
			System.out.println("°Ë»öÀ» Ãë¼Ò.");
			return;
		}
		
		//µ¥ÀÌÅÍº£ÀÌ½º Á¶È¸ : °³º° µ¥ÀÌÅÍ
		List<StudentData> list = new ArrayList<StudentData>();
		list = DBController.dataSearch(searchData, num);
		
		if(list.size()<=0) {
			System.out.println(searchData+"Ã£À» ¼ö ¾øÀ½.");
			return;
		}
		
		//Á¶È¸ °á°ú Ãâ·Â
		dataPrint(list);
	}

	//°³º° ¼ºÀû Á¶È¸ ¸Þ´º ¼±ÅÃ : ÇÐ¹ø,ÀÌ¸§
	private static int searchMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ");
			System.out.println("   1. ÇÐ¹øÀ¸·Î °Ë»ö\t\t 2. ÀÌ¸§À¸·Î °Ë»ö\t\t 3. °Ë»ö Ãë¼Ò");
			System.out.println("¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ");
			System.out.print("¸Þ´º ¼±ÅÃ >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("¼ýÀÚ ÀÔ·Â¹Ù¶÷.");
				continue;
			} catch(Exception e) {
				System.out.println("¼ýÀÚ¸¦ ÀÔ·Â¹Ù¶÷.");
				continue;
			}
			
			if(menu>0 && menu<4) flag = true;
			else System.out.println("1~3 »çÀÌÀÇ ¼ýÀÚ ÀÔ·Â¹Ù¶÷.");
		}
		
		return menu;
	}
	
	//ÀüÃ¼ ¼ºÀû Á¶È¸ 
	private static void studentPrint() {
		List<StudentData> list = new ArrayList<StudentData>();
		
		//µ¥ÀÌÅÍº£ÀÌ½º Á¶È¸ : ÀüÃ¼ µ¥ÀÌÅÍ
		list = DBController.dataSelect();
		
		if(list.size()<=0) {
			System.out.println("ÀÔ·ÂµÈ µ¥ÀÌÅÍ°¡ ¾øÀ½.");
			return;
		}
		
		//µ¥ÀÌÅÍ Ãâ·Â
		dataPrint(list);
		
		//ÀüÃ¼ ÇÐ»ý¼ö, ÀüÃ¼ ÃÑÁ¡, ÀüÃ¼ Æò±Õ °è»ê, °ú¸ñº° Æò±Õ
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
		
		System.out.println("¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ");
		System.out.println("[ÀüÃ¼ ÇÐ»ý¼ö] "+count +"¸í \t\t [ÀüÃ¼ ÃÑÁ¡] "+sum+"Á¡ \t [ÀüÃ¼ Æò±Õ] "+
							String.format("%.2f", totalAvr)+"Á¡");
		System.out.println("[±¹¾î Æò±Õ] "+String.format("%.2f", korAvr)+"Á¡ \t [¿µ¾î Æò±Õ] "+String.format("%.2f", engAvr)
							+"Á¡ \t [¼öÇÐ Æò±Õ] "+String.format("%.2f", mathAvr)+"Á¡");
		System.out.println("");
	}
	
	//¼ºÀû Á¤·Ä : ÇÐ¹ø¼ø,ÀÌ¸§¼ø,¼ºÀû¼ø
	private static void studentSort() {
		//Á¶È¸ ¸Þ´º ¼±ÅÃ
		int menu = sortMenu();
		
		if(menu==4) {
			System.out.println("Á¤·ÄÀ» Ãë¼Ò.");
			return;
		}

		//µ¥ÀÌÅÍº£ÀÌ½º Á¶È¸ : µ¥ÀÌÅÍ Á¤·Ä
		List<StudentData> list = new ArrayList<StudentData>();
		list = DBController.dataSort(menu);
		
		if(list.size()<=0) {
			System.out.println("ÀÔ·ÂµÈ µ¥ÀÌÅÍ°¡ ¾øÀ½.");
			return;
		}
		
		//Á¤·ÄµÈ ¼ºÀû Ãâ·Â
		dataPrint(list);
	}
	
	//¼ºÀû Á¤·Ä ¸Þ´º ¼±ÅÃ : ÇÐ¹ø, ÀÌ¸§, ÃÑÁ¡
	private static int sortMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ");
			System.out.println("   1. ÇÐ¹ø¼ø Á¤·Ä\t 2. ÀÌ¸§¼ø Á¤·Ä\t 3. ¼ºÀû¼ø Á¤·Ä");
			System.out.println("   4. Á¤·Ä Ãë¼Ò");
			System.out.println("¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ");
			System.out.print("¸Þ´º ¼±ÅÃ >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("¼ýÀÚ¸¦ ÀÔ·Â¹Ù¶÷.");
				continue;
			} catch(Exception e) {
				System.out.println("¼ýÀÚ¸¦ ÀÔ·Â¹Ù¶÷.");
				continue;
			}
			
			if(menu>0 && menu<5) flag = true;
			else System.out.println("1~4 »çÀÌÀÇ ¼ýÀÚ ÀÔ·Â¹Ù¶÷.");
		}
		
		return menu;
	}
	
	//µ¥ÀÌÅÍ Ãâ·Â
	private static void dataPrint(List<StudentData> list) {
		System.out.println("¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ");
		System.out.println("ÇÐ¹ø\t"+"ÀÌ¸§\t"+"¼ºº°\t"+" ±¹¾î\t"+" ¿µ¾î\t"+" ¼öÇÐ\t"+" ÃÑÁ¡\t"+"  Æò±Õ\t"+" µî±Þ");
		System.out.println("¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ");
		for(StudentData data : list) System.out.println(data);
	}
	
	//¼ºÀû ¼öÁ¤ : ÇÐ¹øÀ¸·Î Á¶È¸, °ú¸ñ(±¹¾î,¿µ¾î,¼öÇÐ) ¼±ÅÃÇÏ¿© ¼öÁ¤
	private static void studentUpdate() {
		final int KOR=1, ENG=2, MATH=3, EXIT=4;
		int id = 0;
		int menu = 0;
		int kor = 0;
		int eng = 0;
		int math = 0;
		int result = 0;
		
		//ÇÐ¹øÀÔ·Â
		id = inputID();
		
		//¼öÁ¤ Àü¿¡ µ¥ÀÌÅÍ È®ÀÎ : ÇÐ¹øÀ¸·Î µ¥ÀÌÅÍ Á¶È¸
		List<StudentData> list = new ArrayList<StudentData>();
		list = DBController.dataSearch(String.valueOf(id), 1);
		
		if(list.size()<=0) {
			System.out.println("ÀÔ·ÂµÈ µ¥ÀÌÅÍ°¡ ¾øÀ½.");
			return;
		}
		
		System.out.println("ÇöÀç ¼ºÀû >>>");
		dataPrint(list);
		
		//StudentData °´Ã¼·Î ÀúÀå
		StudentData sd = list.get(0);
		
		//¼öÁ¤ÇÒ ¼ºÀû ¼±ÅÃ
		menu = updateMenu();
		
		switch(menu) {
		case KOR : 
			kor = getScore("±¹¾î");
			sd.setKor(kor); break;
		case ENG : 
			eng = getScore("¿µ¾î"); 
			sd.setEng(eng);	break;
		case MATH : 
			math = getScore("¼öÇÐ"); 
			sd.setMath(math); break;
		case EXIT : 
			System.out.println("¼öÁ¤ Ãë¼Ò");
			return;
		}
		
		//ÃÑÁ¡,Æò±Õ,µî±Þ °è»ê
		int total = sd.calTotal();
		sd.setTotal(total);
		double avr = sd.calAvr();
		sd.setAvr(avr);
		char grade = sd.calGrade(avr);
		sd.setGrade(grade);
		
		//µ¥ÀÌÅÍº£ÀÌ½º ¼öÁ¤
		result = DBController.dataUpdate(sd, menu);
		
		if(result!=0) System.out.println("¼ºÀû ¼öÁ¤ ¿Ï·á.");
		else System.out.println("¼ºÀû ¼öÁ¤ ½ÇÆÐ.");
	}
	
	//¼öÁ¤ÇÒ °ú¸ñ ¼±ÅÃ
	private static int updateMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ");
			System.out.println("   1. ±¹¾î¼ºÀû ¼öÁ¤\t 2. ¿µ¾î¼ºÀû ¼öÁ¤\t 3. ¼öÇÐ¼ºÀû ¼öÁ¤");
			System.out.println("   4. ¼öÁ¤ Ãë¼Ò");
			System.out.println("¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ¤Ñ");
			System.out.print("¸Þ´º ¼±ÅÃ >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("¼ýÀÚ¸¦ ÀÔ·Â¹Ù¶÷.");
				continue;
			} catch(Exception e) {
				System.out.println("¼ýÀÚ¸¦ ÀÔ·Â¹Ù¶÷.");
				continue;
			}
			
			if(menu>0 && menu<5) flag = true;
			else System.out.println("1~4 »çÀÌÀÇ ¼ýÀÚ¸¦ ÀÔ·Â¹Ù¶÷.");
		}
		
		return menu;
	}

	//¼ºÀû »èÁ¦ : ÇÐ¹øÀ¸·Î °Ë»ö
	private static void studentDelete() {
		int id = 0;
		int result = 0;
		
		//ÇÐ¹ø ÀÔ·Â
		id = inputID();
		
		//µ¥ÀÌÅÍº£ÀÌ½º »èÁ¦
		result = DBController.dataDelete(id);

		if(result!=0) System.out.println("¼ºÀû »èÁ¦ ¿Ï·á.");
		else System.out.println("¼ºÀû »èÁ¦ ½ÇÆÐ.");
	}
}