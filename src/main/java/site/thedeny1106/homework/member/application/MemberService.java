package site.thedeny1106.homework.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import site.thedeny1106.homework.common.ResponseEntity;
import site.thedeny1106.homework.member.application.dto.MemberCommand;
import site.thedeny1106.homework.member.presentation.dto.MemberInfo;
import site.thedeny1106.homework.member.doamin.Member;
import site.thedeny1106.homework.member.doamin.MemberRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public ResponseEntity<List<MemberInfo>> findAll(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        List<MemberInfo> members = page.stream()
                .map(Member::toResponse)
                .toList();
        return new ResponseEntity<>(HttpStatus.OK.value(), members, page.getTotalElements());
    }

    public ResponseEntity<MemberInfo> create(MemberCommand command) {
        MemberInfo result = Member.fromCommand(command).toResponse();
        return new ResponseEntity<>(HttpStatus.OK.value(), result, 1);
    }

    public ResponseEntity<MemberInfo> update(String id, MemberCommand command) {
        Member found = memberRepository.findById(UUID.fromString(id)).orElse(null);
        if (found == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND.value(), null, 0);
        MemberInfo result = memberRepository.save(Member.fromCommand(command)).toResponse();
        return new ResponseEntity<>(HttpStatus.OK.value(), result, 1);
    }

    public ResponseEntity<?> delete(String id) {
        memberRepository.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK.value(), 1, 1);
    }
}
