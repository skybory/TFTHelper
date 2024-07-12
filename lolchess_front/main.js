var champions = document.querySelectorAll("[id='champion']");
let selectedChildList = document.getElementById('selectedChildList');
let resultText = document.getElementById('result_text');
let clearButton = document.getElementById('clear_button');
let submitButton = document.getElementById('submit_button');
let selectedChampionList = [];
let url = 'http://localhost:8080/';

var request = new XMLHttpRequest();
request.open('GET', url+"data/");
request.send();
request.onload = function() {
    console.log("onLoad");
    if(selectedChampionList.length == 0){
    resultText.innerText = '😋 조합 대기 중!';
    }
    else{
        resultText.innerText = selectedChampionList.join();
    }
}


for(var i = 0; i < champions.length; i++) {
champions[i].addEventListener('click', function(){
        if(selectedChampionList.length < 10 && !selectedChampionList.includes(this.textContent)){
            var list = document.createElement('ul');
            list.classList.add('selected_champion_text');
            list.innerText =this.textContent;
            selectedChampionList.push(this.textContent);
            selectedChildList.appendChild(list);    
        }
    })
}
clearButton.addEventListener('click', function(){
    while (selectedChildList.hasChildNodes())
        {
            selectedChildList.removeChild( selectedChildList.firstChild );       
            selectedChampionList = [];
        }

})
submitButton.addEventListener('click', function(){
    if(selectedChampionList.length == 0){
        alert("챔피언을 선택해주세요.");
    }
    else{
        let jsonData = new Object();
        jsonData.champions = selectedChampionList;


        request.open("POST", url+"data/", true);

        request.setRequestHeader("Content-Type", "application/json");
        
        request.onreadystatechange = () => {
          if (request.readyState === XMLHttpRequest.DONE && request.status === 200) {
            // Request finished. Do processing here.
            console.log('DONE');
        
          }
        };
        request.send(jsonData);
    }
})