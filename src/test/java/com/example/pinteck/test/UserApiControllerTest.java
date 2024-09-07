package com.example.pinteck.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.pinteck.test.User;
import com.example.pinteck.test.SignupRequest;
import com.example.pinteck.test.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	public void mockMvcSetup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		userRepository.deleteAll();  // 테스트 전 데이터 초기화
	}

	@DisplayName("회원가입: 사용자 등록에 성공한다")
	@Test
	public void addUser() throws Exception {

		// given: 회원가입에 필요한 요청 객체 준비
		final String url = "/api/auth/signup";
		final SignupRequest signupRequest = new SignupRequest();
		signupRequest.setUsername("testuser");
		signupRequest.setPassword("password123");
		signupRequest.setEmail("testuser@example.com");

		final String requestBody = objectMapper.writeValueAsString(signupRequest);

		// when: 회원가입 API에 POST 요청
		ResultActions result = mockMvc.perform(post(url)
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody));

		// then: 응답 상태 코드 확인 및 데이터베이스 검증
		result.andExpect(status().isCreated());

		List<User> users = userRepository.findAll();
		assertThat(users.size()).isEqualTo(1);
		assertThat(users.get(0).getUsername()).isEqualTo("testuser");
		assertThat(users.get(0).getEmail()).isEqualTo("testuser@example.com");
	}

	@DisplayName("로그인: 성공적으로 로그인할 수 있다")
	@Test
	public void loginUser() throws Exception {

		// given: 테스트를 위한 사용자 등록
		User user = new User();
		user.setUsername("testuser");
		user.setPassword("password123"); // 실제로는 암호화된 비밀번호를 사용해야 함
		user.setEmail("testuser@example.com");
		userRepository.save(user);

		// 로그인 요청 객체 준비
		final String url = "/api/auth/login";
		final String loginRequestBody = objectMapper.writeValueAsString(
			Map.of("username", "testuser", "password", "password123")
		);

		// when: 로그인 API에 POST 요청
		ResultActions result = mockMvc.perform(post(url)
			.contentType(MediaType.APPLICATION_JSON)
			.content(loginRequestBody));

		// then: 응답 상태 코드 확인 (로그인 성공 시 200 OK 반환)
		result.andExpect(status().isOk());
	}
}
