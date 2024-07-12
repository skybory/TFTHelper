package com.TFTHelper.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@PostMapping("/data/")
	public ResponseEntity<List<Double>> compare(@RequestBody FrontRequest request){
		System.out.println("비교 시작");

		TFTHelper tftHelper = TFTHelper.getInstance();
		InputService inputService = InputService.getInstance();
		Player player = Player.getInstance();

		
		Set<String> myDeck = new HashSet<>(request.getChampionList());
		Set<String> compareDeck = new HashSet<>(player.getDeck().getChampions());
		
		int size = request.countSize();
		
		// 비교본 만들기
        List<Double> result = new ArrayList<>();
        
        
		for (int i=0; i<request.countSize(); i++) {
			System.out.println(i + "번째 시행");
			Double similarity = tftHelper.calculateSimilarity(compareDeck, myDeck);
			result.add(similarity);
		}
		return ResponseEntity.ok(result);
	}
	
}
