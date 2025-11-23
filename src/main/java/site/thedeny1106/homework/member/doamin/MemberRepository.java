package site.thedeny1106.homework.member.doamin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {
    Page<Member> findAll(Pageable pageable);

    Optional<Member> findById(UUID id);

    Member save(Member member);

    void deleteById(UUID id);
}
