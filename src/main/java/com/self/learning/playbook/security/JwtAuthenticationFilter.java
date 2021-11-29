package com.self.learning.playbook.security;


import com.self.learning.playbook.constant.Constant;
import com.self.learning.playbook.constant.SecurityConstant;
import com.self.learning.playbook.dto.internal.ValidateToken;
import com.self.learning.playbook.entity.User;
import com.self.learning.playbook.service.UserService;
import com.self.learning.playbook.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(httpServletRequest);
            if (StringUtils.hasText(token)) {
                ValidateToken validateToken = JwtTokenUtil.validateToken(token, SecurityConstant.AUTH_TOKEN_SECRET, httpServletRequest);
                if (!validateToken.getStatus()) throw new Exception(validateToken.getMessage());
                User user = userService.findUserByEmail(validateToken.getEmail());
                if (null == user) {
                    httpServletRequest.setAttribute(Constant.SERVLET_MESSAGE, "User not found");
                    throw new Exception("User not found");
                }
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context " + ex.getMessage());
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstant.HEADER_STRING);
        if (StringUtils.hasText(token) && token.startsWith(SecurityConstant.TOKEN_PREFIX)) {
            return token.substring(7);
        }
        return null;
    }
}
