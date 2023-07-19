package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user","/user/*"})
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if(session.getAttribute("role_id")==null){
            resp.sendRedirect("/CRM_project/login");
        }
        if((int) session.getAttribute("role_id")==1){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            resp.sendRedirect("/CRM_project/inValid");
        }
    }

    @Override
    public void destroy() {

    }
}
