package com.TFTHelper.demo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TFTHelper {

    private static TFTHelper instance;

    private TFTHelper() {
        // 생성자를 private으로 만들어 외부에서 인스턴스 생성을 막습니다.
    }
	
    public static TFTHelper getInstance() {
        if (instance == null) {
            synchronized (TFTHelper.class) {
                if (instance == null) {
                    instance = new TFTHelper();
                }
            }
        }
        return instance;
    }
    
	public void init(int N, List<Deck> metaDecks) {
		
		// 크롬 드라이버 설정
		WebDriver driver = new ChromeDriver();
		
		try {
			// 웹페이지 열기
			driver.get("https://lolchess.gg/meta");

			// 전체 덱 정보를 저장할 리스트
//			List<Deck> metaDecks = new ArrayList<>();

			// 덱 이름 저장
			List<WebElement> decks = driver.findElements(By.cssSelector(
					"#__next > div > div.css-1x48m3k.eetc6ox0 > div.content > div > section > div.css-s9pipd.e2kj5ne0 > div > div > div > div.css-5x9ld.emls75t2 > div.css-1xsl2fm.emls75t4 > div"));

			 N = decks.size();
			
			for (int i = 1; i <= decks.size(); i++) {
				Deck deck = new Deck();
				// 새로운 덱 생성
				deck.setName(decks.get(i - 1).getText());
				System.out.println(i + "번째 덱 이름 : " + deck.getName());
				metaDecks.add(deck);
				
				// 챔피언 요소들 가져오기
				for (int j = 1; j <= 10; j++) {
					try {
//                        String champion = "#__next > div > div.css-1x48m3k.eetc6ox0 > div.content > div > section > div.css-s9pipd.e2kj5ne0 > div:nth-child(" + i + ") > div > div > div.css-1vo3wqf.emls75t3 > div.Champions.css-1z2so7.e1mdo1l0 > div:nth-child(" + j + ") > div.css-lf8n0t.e9927jh1 > div.css-yox8du.e9927jh2";
						String champion = "#__next > div > div.css-1x48m3k.eetc6ox0 > div.content > div > section > div.css-s9pipd.e2kj5ne0 > div:nth-child("
								+ i
								+ ") > div > div > div.css-1vo3wqf.emls75t3 > div.Champions.css-1z2so7.e1mdo1l0 > div:nth-child("
								+ j + ") > div > div.css-yox8du.e9927jh2";
						String championName = driver.findElement(By.cssSelector(champion)).getText();
						System.out.println(championName);
						deck.addChampion(championName);
					} catch (Exception e) {
						// 챔피언 요소를 찾을 수 없으면 예외를 무시하고 다음 챔피언으로 이동
						continue;
					}
				}

			}

		} finally {
			// 드라이버 종료
			driver.quit();
		}
	}
	
	// 유사도 비교 메서드 생성
	
	public Double calculateSimilarity(Set<String> myDeck, Set<String> metaDeck) {

	    // 교집합 크기를 계산
	    int intersectionSize = 0;
	    
	    for (String champion : myDeck) {
	        if (metaDeck.contains(champion)) {
	            intersectionSize++;
	        }
	    }

	    // 합집합 크기를 계산
	    int unionSize = myDeck.size() + metaDeck.size() - intersectionSize;

	    System.out.println("유사도는 : " + intersectionSize / unionSize );
	    // 자카드 유사도를 계산하여 반환
	    return (double) intersectionSize / unionSize;
	}
	
	
}