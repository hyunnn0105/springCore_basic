      
회원    
- 회원 가입     
- 회원 등급   
- 자체  DB, 외부시스템과 연동     
     
주문과 할인 정책     
- 회원은 상품 주문 O  
- 회원 등급에 따라 할인 적용  
- 할인정책은 모든 VIP 1000원 할인 - 나중에 변경가능   
- 할인정책 변경가능성 high   
   
인터페이스 만들고 구현체를 설계함   
   
클라이언트 -> 회원서비스 -> 메모리 회원 저장소   
   
클래스다이어그램 동적 /   
  
미확정인 할인정책   
클라이언트 --> 회원ID, 상품명, 가격 --> 주문서비스 -> 회원등듭을 가져옴 -> 할인여부 할인정책 역할조회     
=> 클라이언트에게 주문결과 반환      
     
도메인을 역할/ 역할 + 구현으로 그림 나눠그릴 수 있음 
할인정책 유연하게 그려야함 (바꿔끼울 수 있음)  
   
- 주문도메인 클래스 다이어그램   
   
 *역할들의 협력관계를 그대로 재사용할 수 있다.*     
 
## 관심사의 분리 
- appconfig를 통해 관심사를 확실하게 분리함
- appconfig는 구현클래스를 선택하고 에플리케이션의 동작에 대한 전체구성을 책임진다.
- serviceImpl은 *기능을 실행하는 책임*만 지면된다. 
