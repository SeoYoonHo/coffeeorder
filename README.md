# 커피주문시스템 개발

엔티티 설계서

<center><img src="/image/entity.png"></center><br>

ERD

<center><img src="/image/erd.png"></center>

1.인기메뉴 조회의 경우 메뉴-날짜별 주문건수 엔티티를 이용하여 해결
2.데이터 수집플랫폼 실시간 전송의 경우 WebClient를 이용하여 비동기 API 방식으로 해결함
3.음수입력, 없는 메뉴,회원 조희의 경우 Exception재정의하여 사용