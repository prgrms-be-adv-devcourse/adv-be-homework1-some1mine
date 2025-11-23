package site.thedeny1106.homework.member.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import site.thedeny1106.homework.member.doamin.Member;

import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<Member, UUID> {
}
