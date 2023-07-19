package filter;

import payload.response.SuccessRole;
import service.Login.Loginservice;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/login")
public class CustomFilter implements Filter {
    private Loginservice loginservice = new Loginservice();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }
    public void AuthFilter(HttpServletRequest req, HttpServletResponse resp, String email, String password, String remeber) throws IOException,ServletException{
        SuccessRole isSuccess = loginservice.checkEmailPasswordUser(email, password);
        if(isSuccess.getiSsuccess()){
            if(remeber != null){
                HttpSession session = req.getSession();
                session.setAttribute("role_id",isSuccess.getRole_id());
                session.setAttribute("username",email);
                session.setAttribute("password",password);
            }
            resp.sendRedirect("/CRM_project/profile");
        }else
        {
            resp.sendRedirect("/CRM_project/login");
        }
    }
    @Override
    public void destroy() {
//        Filter.super.destroy();
    }
}
