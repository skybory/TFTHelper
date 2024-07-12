package com.TFTHelper.demo;

import java.util.Scanner;

public class InputService {

	// 싱글톤 인스턴스를 위한 private static 변수
	private static Scanner sc;
	private int N;
	
	
    public static InputService getInstance() {
        if (instance == null) {
            synchronized (InputService.class) {
                if (instance == null) {
                    instance = new InputService();
                }
            }
        }
        return instance;
    }
    
	// 싱글톤 인스턴스를 반환하는 public static 메소드
	public static Scanner getScannerInstance() {
		if (sc == null) {
			sc = new Scanner(System.in);
		}
		return sc;
	}
	
    private static InputService instance;

    private InputService() {
    }



	public int scanNumber() {
		Scanner sc = InputService.getScannerInstance();

		System.out.println("가지고 있는 챔피언의 수를 입력해주세요.");

		N = sc.nextInt();

		return N;
	}
	
	public Deck scanDeck(int number) {
		
		Deck myDeck = new Deck();
		System.out.println("챔피언을 입력하세요");

        // 초기 버퍼 비우기
        sc.nextLine();
		
		for (int i=1; i<=number; i++) {
			String myChampion = sc.nextLine();
			myDeck.addChampion(myChampion);			
		}
		
		System.out.println("입력이 완료되었습니다. 당신이 입력한 챔피언은 : ");
		
		for (int i=1; i<=myDeck.getChampions().size(); i++) {
			System.out.println(i + "번째 챔피언 : " + myDeck.getChampions().get(i-1));
		}
		return myDeck;
	}
	
}
