# ♟ java-chess
체스 게임 구현을 위한 저장소

<br>

## 1단계 - 체스판 초기화

- [x] 게임 시작 안내문을 출력한다.
- [x] `end` 를 입력하면 종료한다.
- [x] `start` 를 입력하면 게임을 시작한다.
- [x] 체스 게임 초기화
    - [x] 플레이어 생성
		- [x] 흑 팀 플레이어 생성
		- [x] 백 팀 플레이어 생성
    - [x] 체스판 생성
      - [x] 각 열을 File, 각 행을 Rank라고 한다.
      - [x] 체스판의 위치는 File과 Rank를 가진다.
      - [x] 체스판의 1칸은 고유한 1개의 위치를 가진다.
      - [x] 위치는 캐싱 되어있다.
      - [x] 8 * 8 의 빈 칸들로 이루어진 체스 판을 생성한다.
    - [x] 기물 생성
      - [x] 기물들을 종류와 색깔 별로 하나씩 생성한다.
      - [x] 기물들은 캐싱 되어있다.
    - [x] 기물 배치
      - [x] 배치 방식은 공식 체스 룰을 따른다.
      - [x] 흑의 기물들을 상단에, 백의 기물들을 하단에 배치한다.
      - [x] 기물들을 플레이어들에게 나눠준다.
      - [x] 기물들을 체스판에 배치한다.
  - [x] 체스 판 출력
    - [x] 체스 판 전체를 출력한다.
    - [x] 흑의 기물들은 대문자로 표기하고, 백의 기물들은 소문자로 표기한다.
    - [x] `end` 외의 명령어가 입력 될 때 마다, 체스 판을 출력한다.

<br>

## 2단계 - 말 이동

- [x] `move ${현재위치} ${도착위치}` 를 입력받는다.
  - [x] 위의 형식에 맞지 않으면, 예외를 발생시킨다.
  - [x] `${현재위치}` 와 `${도착위치}` 의 File, Rank가 유효하지 않으면, 이동할 수 없다.
- [x] `${현재위치}` 에 자신의 기물이 없으면, 예외를 발생시킨다.
  - [x] 비어있는 칸인 경우
  - [x] 적의 기물이 있는 칸인 경우
- [x] 보드에서 `${현재위치}` 에 있는 기물을 `${도착위치}` 로 이동시킨다.
- [x] 기물이 `${도착위치}` 에 이동할 수 있으면, 이동시킨다.
  - [x] 이동할 수 없으면, 예외를 발생시킨다.
  - [x] 보드의 기물 상태를 갱신한다.
  - [x] 플레이어들의 기물 상태를 갱신한다.
- [x] 말 이동 가능 여부 판단
  - [x] 말 이동 방식은 공식 체스 룰을 따른다.
  - [x] Rook, Bishop, Queen, King
    - [x] `${현재위치}` 부터  `${도착위치}` 까지의 방향(기울기)을 구한다.
    - [x] 이동 명령의 방향이, 해당 기물이 이동할 수 없는 방향이면, 예외를 발생시킨다.
    - [x] 이동 명령 방향으로 이동할 수 있으면, 한 칸씩 `${도착위치}` 까지 검사한다.
    - [x] 이동 경로 중간(`${출발위치}` 다음부터 `${도착위치}` 직전까지)에 기물이 존재하면, 이동할 수 없다.
    - [x] `${도착위치}` 에 아군의 기물이 있으면, 이동할 수 없다.
  - [x] Knight
    - [x] `${현재위치}` 부터  `${도착위치}` 까지의 방향을 구한다.
    - [x] 이동 명령의 방향이, 해당 기물이 이동할 수 없는 방향이면, 예외를 발생시킨다.
    - [x] 이동 경로 중간(`${출발위치}` 다음부터 `${도착위치}` 직전까지)에 기물이 존재해도, 이동할 수 있다.
    - [x] `${도착위치}` 에 아군 기물이 있으면, 이동할 수 없다.
  - [x] Pawn
    - [x] `${현재위치}` 부터  `${도착위치}` 까지의 방향(기울기)을 구한다.
    - [x] 이동 명령의 방향이, 해당 기물이 이동할 수 없는 방향이면, 예외를 발생시킨다.
      - [x] 흑 팀일 때 : LEFT_DOWN, DOWN, RIGHT_DOWN
      - [x] 백 팀일 때 : LEFT_UP, UP, RIGHT_UP
    - [x] 이동 명령 방향이 수직 방향 일 때
      - [x] 앞으로 한 칸 이동할 수 있다.
      - [x] 처음 위치일 때
        - [x] 두 칸 이동할 수 있다.
          - [x] 이동 경로 중간(`${출발위치}` 다음부터 `${도착위치}` 직전까지)에 기물이 존재하면, 이동할 수 없다.
      - [x] `${도착위치}` 에 기물이 있으면, 이동할 수 없다.
    - [x] 이동 명령 방향이 대각선 방향 일 때
      - [x] `${도착위치}` 에 적의 기물이 없으면, 이동할 수 없다.

<br>

## 3단계 - 승패 및 점수

- [x] King이 잡힌 경우
  - [x] King이 잡히는 팀이 진다.
  - [x] 잡히는 즉시, 체스 판을 출력하고, 게임을 종료한다.
  - [x] `${승리한 팀}이 이겼습니다.` 를 출력한다.
- [x] King이 잡히지 않은 상태
  - [x] `status` 명령 입력
    - [x] 각 팀의 현재 점수를 출력한다.
- [x] 점수 계산
  - [x] 각 기물의 점수는, King은 0점, Queen은 9점, Rook은 5점, Bishop은 3점, Knight는 2.5점 이다.
  - [x] Pawn의 기본 점수는 1점이다. 하지만 한 File(열)에 같은 팀의 Pawn이 있는 경우, 1점이 아닌 0.5점으로 계산한다.
  - [x] 각 팀의 점수를 따로 계산한다. (요구사항)
  - [x] Pawn을 제외한 기물들 점수 계산
    - [x] 기물들의 점수를 모두 더한다.
  - [x] Pawn 점수 계산
    - [x] 각 File(열)별로 점수를 계산해 합친다.
      - [x] 한 File(열)에 Pawn이 2개 이상이면, 각 Pawn의 점수를 0.5점으로 계산한다.
      - [x] 그 외에는 각 Pawn의 점수를 1점으로 계산한다.

<br>