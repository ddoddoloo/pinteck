package com.example.pinteck.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
		String token = getJwtFromRequest(request);

		if (token != null && tokenProvider.validateToken(token)) {
			String username = tokenProvider.getUsernameFromJwt(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (userDetails != null) {
				// UsernamePasswordAuthenticationToken을 사용하여 인증 객체 생성
				Authentication authentication = new UsernamePasswordAuthenticationToken(
					userDetails,
					null,
					userDetails.getAuthorities()
				);

				// UsernamePasswordAuthenticationToken으로 캐스팅하여 setDetails 사용
				if (authentication instanceof UsernamePasswordAuthenticationToken) {
					((UsernamePasswordAuthenticationToken) authentication)
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				}

				// SecurityContext에 인증 정보 설정
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
