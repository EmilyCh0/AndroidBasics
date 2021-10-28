## Project: Lemonade App

### 👉 [구체적인 학습 내용 정리](https://velog.io/@emily2307/Unit-1-Kotlin-basics-4)  👈

<img src="./images/lemonade.png" width="100%"/>

**Lemonade 앱 개요**

4가지 state가 있다. 처음 앱은 SELECT 상태로 시작된다.

SELECT 상태에서 이미지를 클릭하면, SQUEEZE 화면으로 바뀌고, squeezeCount가 0으로 초기화되고, lemonSize가 2~4 사이 랜덤 숫자로 지정된다.

SQUEEZE 상태에서 이미지를 클릭하면, 클릭할 때 마다. squeezeCount는 +1, lemonSize는 -1이 되어, lemonSize가 0이 되는 순간 DRINK 상태로 전환한다.

DRINK 상태에서 이미지를 클릭하면 RESTART 상태로 전환하고, lemonSize=-1이 된다.

RESTART 상태에서 이미지를 클릭하면 SELECT로 돌아간다.

