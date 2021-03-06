package student_project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentProject {
	//메뉴 : 성적입력, 개별성적조회, 전체성적조회, 성적순정렬, 성적수정, 성적삭제, 종료
	public static final int INSERT=1, SEARCH=2, PRINT=3, SORT=4, UPDATE=5, DELETE=6, EXIT=7;
	
	//스캐너 생성하기
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		int menu = 0;
		boolean flag = false;
		
		//메뉴 선택 : 성적입력, 개별성적조회, 전체성적조회, 성적순정렬, 성적수정, 성적삭제, 종료
		while(!flag) {
			//메뉴 선택 함수 호출
			menu = selectMenu();
			
			switch(menu) {
			case INSERT: studentInsert(); break;
			case SEARCH: studentSearch(); break;
			case PRINT: studentPrint(); break;
			case SORT: studentSort(); break;
			case UPDATE: studentUpdate(); break;
			case DELETE: studentDelete(); break;
			case EXIT: 
				System.out.println("성적 프로그램 종료.");
				flag = true; break;
			}
		}
	}
	
	//메뉴 선택
	private static int selectMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ성적관리프로그램ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("1. 성적 입력\t\t 2. 개별 성적 조회\t\t 3. 전체 성적 조회");
			System.out.println("4. 성적 정렬\t\t 5. 성적 수정\t\t 6. 성적 삭제");
			System.out.println("7. 프로그램 종료");
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.print("메뉴 선택 >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("숫자를 입력 바람.");
				continue;
			} catch(Exception e) {
				System.out.println("숫자를 입력 바람.");
				continue;
			}
			
			if(menu>0 && menu<8) flag = true;
			else System.out.println("(1~7) 숫자 입력 바람.");
		}
		return menu;
	}
	
	//성적 데이터 입력
	private static void studentInsert() {
		//멤버변수 : 학번,이름,성별,국어,영어,수학,총점,평균,등급
		int id = 0;
		String name = null;
		String gender = null;
		int kor = 0;
		int eng = 0;
		int math = 0;
		int total = 0;
		double avr = 0.0;
		char grade = '\u0000';
		
		//학번 입력 함수 호출
		id = inputID();
		
		//데이터 전체 조회
		List<StudentData> list = new ArrayList<StudentData>();
		list = DBController.dataSelect();
		
		//id 중복 확인
		if(list.size()>0) {
			for(StudentData data : list) {
				if(id==data.getId()) {
					System.out.println("중복 된 학번입니다 ! ");
					return;
				}
			}
		}
		
		//이름 입력
		while(true) {
			System.out.println("이름을 입력 바람. ");
			System.out.print(">> ");
			name = scan.nextLine();
			
			Pattern pattern = Pattern.compile("^[가-힣]*$");
			Matcher matcher = pattern.matcher(name);
			
			if(matcher.matches()) break;
			else System.out.println("다시 입력 바람.");
		}
		
		//성별 입력
		while(true) {
			System.out.println("성별을 입력해 주세요.(남성)/여성)");
			System.out.print(">> ");
			gender = scan.nextLine();
			
			if(gender.equals("남성")||gender.equals("여성")) break;
			else System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
		
		//국어,영어,수학 성적 입력 : 성적입력 함수 호출
		kor = getScore("국어");
		eng = getScore("영어");
		math = getScore("수학");
		
		//StudentData 객체 생성
		StudentData sd = new StudentData(id, name, gender, kor, eng, math);
		
		//총합, 평균, 등급 계산 함수 호출(StudentData)
		total = sd.calTotal();
		sd.setTotal(total);
		avr = sd.calAvr();
		sd.setAvr(avr);
		grade = sd.calGrade(avr);
		sd.setGrade(grade);
		
		//데이터베이스에 저장 
		int result = DBController.dataInsert(sd);
		
		if(result!=0) System.out.println(name+"님의 성적 입력완료.");
		else System.out.println(name+"님의 성적 입력실패.");
	}
	
	//학번 입력 : 4자리 숫자
	private static int inputID() {
		boolean flag = false;
		int id = 0;
		
		while(!flag) {
			System.out.println("학번을 입력바람.");
			System.out.print(">> ");
			try	{
				id = Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("숫자 입력 바람.");
				continue;
			}catch(Exception e) {
				System.out.println("숫자 입력 바람.");
				continue;
			}
			
			if(id>999 && id<10000) flag = true;
			else System.out.println("XXXX 입력 바람.");
		}
		
		return id;
	}
	
	//성적 입력: 매개변수 과목(국어, 영어, 수학)
	private static int getScore(String subject) {
		boolean flag = false;
		int score = 0;
		
		while(!flag) {
			System.out.println(subject+" 점수 입력바람.");
			System.out.print(">> ");
			try	{
				score = Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("숫자 입력바람.");
				continue;
			}catch(Exception e) {
				System.out.println("숫자 입력바람.");
				continue;
			}
			
			if(score>=0 && score<=100) flag = true;
			else System.out.println("다시 입력바람.");
		}
		
		return score;
	}
	
	//개별 성적 조회 : 학번,이름으로 검색
	private static void studentSearch() {
		final int ID=1, NAME=2, EXIT=3;
		int menu = 0;
		int id = 0;
		String name = null;
		String searchData = null;
		int num = 0;
		
		//조회 메뉴 선택
		menu = searchMenu();
		
		switch(menu) {
		case ID: 
			id = inputID();
			searchData = String.valueOf(id);
			num = ID;
			break;
		case NAME: 
			while(true) {
				System.out.println("찾을 이름 선택 바람.");
				System.out.print(">> ");
				name = scan.nextLine();
				
				Pattern pattern = Pattern.compile("^[가-힣]*$");
				Matcher matcher = pattern.matcher(name);
				
				if(matcher.matches()) break;
				else System.out.println("다시 입력해주세요.");
			}
			
			searchData = name;
			num = NAME;
			break;
		case EXIT: 
			System.out.println("검색을 취소.");
			return;
		}
		
		//데이터베이스 조회 : 개별 데이터
		List<StudentData> list = new ArrayList<StudentData>();
		list = DBController.dataSearch(searchData, num);
		
		if(list.size()<=0) {
			System.out.println(searchData+"찾을 수 없음.");
			return;
		}
		
		//조회 결과 출력
		dataPrint(list);
	}

	//개별 성적 조회 메뉴 선택 : 학번,이름
	private static int searchMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("   1. 학번으로 검색\t\t 2. 이름으로 검색\t\t 3. 검색 취소");
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.print("메뉴 선택 >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("숫자 입력바람.");
				continue;
			} catch(Exception e) {
				System.out.println("숫자를 입력바람.");
				continue;
			}
			
			if(menu>0 && menu<4) flag = true;
			else System.out.println("1~3 사이의 숫자 입력바람.");
		}
		
		return menu;
	}
	
	//전체 성적 조회 
	private static void studentPrint() {
		List<StudentData> list = new ArrayList<StudentData>();
		
		//데이터베이스 조회 : 전체 데이터
		list = DBController.dataSelect();
		
		if(list.size()<=0) {
			System.out.println("입력된 데이터가 없음.");
			return;
		}
		
		//데이터 출력
		dataPrint(list);
		
		//전체 학생수, 전체 총점, 전체 평균 계산, 과목별 평균
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
		
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println("[전체 학생수] "+count +"명 \t\t [전체 총점] "+sum+"점 \t [전체 평균] "+
							String.format("%.2f", totalAvr)+"점");
		System.out.println("[국어 평균] "+String.format("%.2f", korAvr)+"점 \t [영어 평균] "+String.format("%.2f", engAvr)
							+"점 \t [수학 평균] "+String.format("%.2f", mathAvr)+"점");
		System.out.println("");
	}
	
	//성적 정렬 : 학번순,이름순,성적순
	private static void studentSort() {
		//조회 메뉴 선택
		int menu = sortMenu();
		
		if(menu==4) {
			System.out.println("정렬을 취소.");
			return;
		}

		//데이터베이스 조회 : 데이터 정렬
		List<StudentData> list = new ArrayList<StudentData>();
		list = DBController.dataSort(menu);
		
		if(list.size()<=0) {
			System.out.println("입력된 데이터가 없음.");
			return;
		}
		
		//정렬된 성적 출력
		dataPrint(list);
	}
	
	//성적 정렬 메뉴 선택 : 학번, 이름, 총점
	private static int sortMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("   1. 학번순 정렬\t 2. 이름순 정렬\t 3. 성적순 정렬");
			System.out.println("   4. 정렬 취소");
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.print("메뉴 선택 >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("숫자를 입력바람.");
				continue;
			} catch(Exception e) {
				System.out.println("숫자를 입력바람.");
				continue;
			}
			
			if(menu>0 && menu<5) flag = true;
			else System.out.println("1~4 사이의 숫자 입력바람.");
		}
		
		return menu;
	}
	
	//데이터 출력
	private static void dataPrint(List<StudentData> list) {
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println("학번\t"+"이름\t"+"성별\t"+" 국어\t"+" 영어\t"+" 수학\t"+" 총점\t"+"  평균\t"+" 등급");
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		for(StudentData data : list) System.out.println(data);
	}
	
	//성적 수정 : 학번으로 조회, 과목(국어,영어,수학) 선택하여 수정
	private static void studentUpdate() {
		final int KOR=1, ENG=2, MATH=3, EXIT=4;
		int id = 0;
		int menu = 0;
		int kor = 0;
		int eng = 0;
		int math = 0;
		int result = 0;
		
		//학번입력
		id = inputID();
		
		//수정 전에 데이터 확인 : 학번으로 데이터 조회
		List<StudentData> list = new ArrayList<StudentData>();
		list = DBController.dataSearch(String.valueOf(id), 1);
		
		if(list.size()<=0) {
			System.out.println("입력된 데이터가 없음.");
			return;
		}
		
		System.out.println("현재 성적 >>>");
		dataPrint(list);
		
		//StudentData 객체로 저장
		StudentData sd = list.get(0);
		
		//수정할 성적 선택
		menu = updateMenu();
		
		switch(menu) {
		case KOR : 
			kor = getScore("국어");
			sd.setKor(kor); break;
		case ENG : 
			eng = getScore("영어"); 
			sd.setEng(eng);	break;
		case MATH : 
			math = getScore("수학"); 
			sd.setMath(math); break;
		case EXIT : 
			System.out.println("수정 취소");
			return;
		}
		
		//총점,평균,등급 계산
		int total = sd.calTotal();
		sd.setTotal(total);
		double avr = sd.calAvr();
		sd.setAvr(avr);
		char grade = sd.calGrade(avr);
		sd.setGrade(grade);
		
		//데이터베이스 수정
		result = DBController.dataUpdate(sd, menu);
		
		if(result!=0) System.out.println("성적 수정 완료.");
		else System.out.println("성적 수정 실패.");
	}
	
	//수정할 과목 선택
	private static int updateMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("   1. 국어성적 수정\t 2. 영어성적 수정\t 3. 수학성적 수정");
			System.out.println("   4. 수정 취소");
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.print("메뉴 선택 >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("숫자를 입력바람.");
				continue;
			} catch(Exception e) {
				System.out.println("숫자를 입력바람.");
				continue;
			}
			
			if(menu>0 && menu<5) flag = true;
			else System.out.println("1~4 사이의 숫자를 입력바람.");
		}
		
		return menu;
	}

	//성적 삭제 : 학번으로 검색
	private static void studentDelete() {
		int id = 0;
		int result = 0;
		
		//학번 입력
		id = inputID();
		
		//데이터베이스 삭제
		result = DBController.dataDelete(id);

		if(result!=0) System.out.println("성적 삭제 완료.");
		else System.out.println("성적 삭제 실패.");
	}
}