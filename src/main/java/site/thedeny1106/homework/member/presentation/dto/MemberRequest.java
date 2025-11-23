package site.thedeny1106.homework.member.presentation.dto;

import site.thedeny1106.homework.member.application.dto.MemberCommand;

public record MemberRequest(
        String email,
        String name,
        String password,
        String phone,
        String saltKey,
        String flag
) {
    public MemberCommand toCommand() {
        return new MemberCommand(
                this.email,
                this.name,
                this.password,
                this.phone,
                this.saltKey,
                this.flag
        );
    }
}
