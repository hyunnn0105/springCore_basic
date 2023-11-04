package hello.core.singleton;

public class StatefulService {

    private int price; // 상태를 유지하는 필드

    /*
    public void order(String name, int price){
        System.out.println("name= " + name + "price= " + price);
        this.price = price; // 여기서 문제 발생
        // 동시에 요청이 들어오는 경우 int price 값을 변동시켜 다른 결과값이 도출됨
    }
    */

    /* 이렇게 작성해야됨 */
    public int order(String name, int price){
        System.out.println("name= " + name + "price= " + price);
        return price;
    }

    /*
    public int getPrice(){
        return price;
    }
    */
}
