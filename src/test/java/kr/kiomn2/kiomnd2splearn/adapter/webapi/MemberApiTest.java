package kr.kiomn2.kiomnd2splearn.adapter.webapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.kiomn2.kiomnd2splearn.application.member.provided.MemberRegister;
import kr.kiomn2.kiomnd2splearn.domain.MemberFixture;
import kr.kiomn2.kiomnd2splearn.domain.member.Member;
import kr.kiomn2.kiomnd2splearn.domain.member.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
@WebMvcTest(MemberApi.class)
class MemberApiTest {
    final MockMvcTester mvcTester;
    final ObjectMapper objectMapper;

    @MockitoBean
    private MemberRegister memberRegister;

    @Test
    void register() throws Exception{
        Member member = MemberFixture.createMember(1L);
        when(memberRegister.register(any())).thenReturn(member);

        MemberRegisterRequest request = MemberFixture.createMemberRegisterRequest();
        String requestJson = objectMapper.writeValueAsString(request);

        assertThat(mvcTester.post().uri("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)).hasStatusOk()
                .bodyJson()
                .extractingPath("$.memberId").asNumber().isEqualTo(1);

        verify(memberRegister, times(1)).register(request);
    }

    @Test
    void registerFail() throws Exception{
        MemberRegisterRequest request = MemberFixture.createMemberRegisterRequest("testemail");
        String requestJson = objectMapper.writeValueAsString(request);

        assertThat(mvcTester.post().uri("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)).hasStatus(HttpStatus.BAD_REQUEST);

    }
}