package cz.muni.fi.PA165.mvc.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;


@WebFilter(urlPatterns = { "/user/detail/*" , "/user/list/*", "/event/*", "/result/*"})
public class ProtectFilter implements Filter {

    final static Logger log = LoggerFactory.getLogger(ProtectFilter.class);


    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;

        Object auth = request.getSession().getAttribute("authenticatedUser");
        if (auth != null) {
            chain.doFilter(request, response);
            return;
        }
        
       
            log.warn("authentication fail");
            response401(response,request);
            return;
        
    }


   
    private void response401(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.sendRedirect(request.getContextPath()+"/user/login_page");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
