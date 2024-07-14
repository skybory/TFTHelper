var champions = document.querySelectorAll("[id='champion']")
let selectedChildList = document.getElementById("selected_child_list")
let resultText = document.getElementById("result_text")
let clearButton = document.getElementById("clear_button")
let submitButton = document.getElementById("submit_button")
let championButtonSection = document.getElementById("champion_button_section")
let titleText = document.getElementById("title")
let selectedChampionList = []
let url = "http://localhost:8080/api/"

var request = new XMLHttpRequest()

if (selectedChampionList.length == 0) {
  request.open("GET", url + "data")
  request.send()
  request.onload = function () {
    if (request.status == 200) {
      console.log("get champion list success")
      // 서버에서 챔피언 불러와서 지정
      var championList = JSON.parse(request.response)
      for (let i = 0; i < championList.length; i++) {
        // 챔피언 버튼 생성
        var championButton = document.createElement("button")
        championButton.setAttribute("id", "champion")
        championButton.setAttribute("class", "champion_button")
        championButton.innerText = championList[i]
        championButtonSection.appendChild(championButton)

        // 챔피언 선택 기능 추가
        championButton.addEventListener("click", function () {
          if (
            selectedChampionList.length < 10 &&
            !selectedChampionList.includes(this.textContent)
          ) {
            var list = document.createElement("ul")
            list.classList.add("selected_champion_text")
            list.innerText = this.textContent
            selectedChampionList.push(this.textContent)
            selectedChildList.appendChild(list)
          }
          titleText.innerText = "선택한 챔피언 (" + selectedChampionList.length + " / 10)"
        })
      }
    }
  }
}

// 초기화 버튼 기능
clearButton.addEventListener("click", function () {
  while (selectedChildList.hasChildNodes()) {
    selectedChildList.removeChild(selectedChildList.firstChild)
    selectedChampionList = []
    titleText.innerText = "선택한 챔피언 (" + selectedChampionList.length + " / 10)"
  }
  clearResultText()
  var text = document.createElement("span")
  text.innerText = "😋 조합 대기 중!"
  resultText.appendChild(text)
})

function clearResultText() {
  while (resultText.hasChildNodes()) {
    resultText.removeChild(resultText.firstChild)
  }
}

// 챔피언 선택 후 입력 버튼 기능
submitButton.addEventListener("click", function () {
  if (selectedChampionList.length == 0) {
    alert("챔피언을 선택해주세요.")
  } else {
    let jsonData = new Object()
    jsonData.championList = selectedChampionList

    request.open("POST", url + "data", true)
    request.setRequestHeader("Content-Type", "application/json")
    request.send(JSON.stringify(jsonData))
    request.onreadystatechange = () => {
      if (request.readyState === XMLHttpRequest.DONE && request.status === 200) {
        console.log("get result success")
        let resultData = JSON.parse(request.responseText)

        clearResultText()
        var deckText = document.createElement("span")
        deckText.setAttribute("class", "selected_champion_result_text")
        deckText.innerText = resultData.deckName

        var recommendText = document.createElement("span")
        recommendText.innerText = " 덱을 추천드립니다!\n\n유사도는 약 "

        var similarityText = document.createElement("span")
        similarityText.setAttribute("class", "selected_champion_result_text")
        similarityNumber = Number(resultData.similarity)
        similarityNumber = similarityNumber.toFixed(2)
        similarityText.innerText = String(similarityNumber)

        var endText = document.createElement("span")
        endText.innerText = " % 입니다."

        resultText.appendChild(deckText)
        resultText.appendChild(recommendText)
        resultText.appendChild(similarityText)
        resultText.appendChild(endText)
        console.log(request.responseText)
      }
    }
  }
})
