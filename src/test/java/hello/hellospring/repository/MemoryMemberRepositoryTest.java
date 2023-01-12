package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));
        assertEquals(result, result);
        //alt+enter : 옵션 단축키
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {

        for (int i = 0; i < 10; i++) {
            Member member = new Member();
            member.setName("spring" + i);
            memberRepository.save(member);
        }

        Member result = memberRepository.findByName("spring8").get();
        System.out.println("result.getId() = " + result.getId());
        System.out.println("result.getName() = " + result.getName());

        Member result2 = memberRepository.findByName("spring9").get();
        System.out.println("result2.getId() = " + result2.getId());
        System.out.println("result2.getName() = " + result2.getName());
    }

    @Test
    public void findAll() {

        for (int i = 0; i < 10; i++) {
            Member member = new Member();
            member.setName("spring" + i);
            memberRepository.save(member);
        }

        List<Member> result = memberRepository.findAll();

        result.forEach(e -> {
            System.out.println("e.getId() = " + e.getId());
            System.out.println("e.getName() = " + e.getName() + "\n");
        });
    }
}
