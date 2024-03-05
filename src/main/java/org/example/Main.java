package 임시;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

// 스터디룸을 하나의 클래스로 만듦
class StudyRoom {
	public boolean time[] = new boolean[13]; // 0은 오전 10시, 12는 오후 10시
	
	StudyRoom() {
	}
}

// 문의 내역을 남길 유저의 아이디와 내용을 클래스로 만듦
class User {
	public String id = "";
	public String inquiry = ""; 
	
	User() {
		
	}
}

public class Main {
	static boolean power = true; // 시스템 종료 여부 식별
	
	static StudyRoom a = new StudyRoom(); 
	static StudyRoom b = new StudyRoom(); 
	static StudyRoom c = new StudyRoom();
	
	static ArrayList<User> inquires = new ArrayList<>(); // User 클래스의 ArrayList로 내역을 저장, 이부분 전체수정

    public static void main(String[] args) {
    	Scanner s = new Scanner(System.in);

    	printList();
    	
    	while(s.hasNextInt()) {
    		int num = s.nextInt();
    		System.out.println("\n------------------------\n");
    		switch(num) {
    			case 1: // 스터디룸 예약
    				try {
    				reservation(s);
	    			} catch(Exception e) {
	    				System.out.println(e.getMessage());
	    			}
    			break;
    			
    			case 2: show(); // 예약 현황 조회
    			break;
    			
    			case 3: makeIqr(s); // 문의 남기기
    			break;
    			
    			case 4: showIqr(); // 문의 리스트 보기 
    			break;
    			
    			case 5: power = false; // 프로그램 종료
    					System.out.println("프로그램을 종료합니다.");
    			break;
    		}
    		if(!power) break;
    		printList();
    	}
    }
    
    // 작업 선택 목록을 출력하는 함수
    public static void printList() {
    	System.out.println("작업을 선택하세요.\n");
		System.out.println("1. 스터디룸 예약");
		System.out.println("2. 예약 현황 조회");
		System.out.println("3. 문의 남기기");
		System.out.println("4. 문의 리스트 보기");
		System.out.println("5. 프로그램 종료");
    }
    
    // 예약 현황을 보여주는 함수
    public static void show() {
    	System.out.println("스터디룸 예약 현황입니다.\n");
        System.out.println("스터디룸 | A | B | C");

        for(int i = 0; i < 13; i++) {
            int time = i + 10;
            String tempA, tempB, tempC;
            tempA = a.time[i] ? "O" : "X";
            tempB = b.time[i] ? "O" : "X";
            tempC = c.time[i] ? "O" : "X";
            System.out.println(time + "시   | " + tempA + " | " + tempB + " | " + tempC);
        }
        System.out.println("\n------------------------\n");
    }
    
    // 스터디룸 예약을 진행하는 함수
    public static StudyRoom reservation(Scanner s) throws Exception {
    	System.out.print("예약할 스터디룸을 입력하세요 : ");
    	String room = s.next();
    	System.out.print("예약 시작 시간을 입력하세요 : ");
    	int start = s.nextInt();
    	System.out.print("예약 종료 시간을 입력하세요 : ");
    	int end = s.nextInt();
    	
    	if(room.equals("A") || room.equals("a")) { // A룸을 예약할 경우
    		return isReserved(a, start, end);
    	} else if(room.equals("B") || room.equals("b")) {
    		return isReserved(b, start, end);
    		
    	} else if(room.equals("C") || room.equals("c")) {
    		return isReserved(c, start, end);
    	} else {		
    		throw (new Exception("\n존재하지 않는 스터디룸입니다.\n"));
    	}
    	
    }
    
    // 예약되었는지 확인하는 함수
    public static StudyRoom isReserved(StudyRoom r, int start, int end) throws Exception {
    	for (int i = start - 10; i < end - 10; i++) {
			if(r.time[i]) {
				throw (new Exception("이미 예약된 시간입니다."));
			}
		}
		for (int i = start - 10; i < end - 10; i++) {
			r.time[i] = true;
		}
		System.out.println("\n예약이 완료되었습니다.");
        System.out.println("\n------------------------\n");
		return r;
    }
    
    // 문의 저장하는 함수
    public static void makeIqr(Scanner s) {
    	User newUser = new User();
    	System.out.print("아이디를 입력하세요 : ");
    	newUser.id = s.next();
    	System.out.print("문의할 내용을 작성하세요 : ");
    	newUser.inquiry = s.next();
    
    	inquires.add(newUser);
    	System.out.println("\n성공적으로 저장되었습니다.");
    	System.out.println("\n------------------------\n");
    	
    }
    
    // 문의 내역 보여주는 함수
    public static void showIqr() {
    	for(int i = 0; i < inquires.size(); i++) {
    		System.out.println((i + 1) + ". " + inquires.get(i).id + ": " + inquires.get(i).inquiry);
    	}

    	System.out.println("\n------------------------\n");
    }
}
