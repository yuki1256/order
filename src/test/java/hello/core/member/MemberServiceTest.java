package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void setUp() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member1 = new Member(1L, "SpringA", Grade.BASIC);
        Member member2 = new Member(2L, "SpringB", Grade.VIP);
        //when
        memberService.join(member1);
        memberService.join(member2);

        Member findMemberA = memberService.findMember(member1.getId());
        Member findMemberB = memberService.findMember(member2.getId());

        //then
        Assertions.assertThat(findMemberA).isEqualTo(member1);
        Assertions.assertThat(findMemberB).isEqualTo(member2);
    }
}
