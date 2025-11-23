package site.thedeny1106.homework.member.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import site.thedeny1106.homework.member.doamin.Member;
import site.thedeny1106.homework.member.doamin.MemberRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryAdapter implements MemberRepository {

    private final MemberJpaRepository memberRepository;

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Override
    public Optional<Member> findById(UUID id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteById(UUID id) {
        memberRepository.deleteById(id);
    }
}
