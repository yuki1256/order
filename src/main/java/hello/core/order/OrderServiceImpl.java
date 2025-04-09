package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //구현체에 대해 전혀 모른 상태
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //Config에 의해서 꽂아 넣어질 구멍역할(생성자) => 의존관계 주입 당하기
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    
    //이렇게 되면 구현체는 인터페이스만 보면서 기능만 실행하면 된다
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
