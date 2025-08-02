package kr.kiomn2.kiomnd2splearn.adapter.webapi;

import jakarta.validation.Valid;
import kr.kiomn2.kiomnd2splearn.adapter.webapi.dto.MemberRegisterResponse;
import kr.kiomn2.kiomnd2splearn.application.member.provided.MemberRegister;
import kr.kiomn2.kiomnd2splearn.domain.member.Member;
import kr.kiomn2.kiomnd2splearn.domain.member.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApi {
    private final MemberRegister memberRegister;

    @PostMapping("/api/members")
    public MemberRegisterResponse register(@RequestBody @Valid MemberRegisterRequest request) {
        Member member = memberRegister.register(request);

        return MemberRegisterResponse.of(member);
    }
}
