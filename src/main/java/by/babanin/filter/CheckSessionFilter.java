package by.babanin.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CheckSessionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper((HttpServletRequest) req);
        HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper((HttpServletResponse) resp);
        HttpSession session = httpServletRequestWrapper.getSession(false);
        if (session != null){
            chain.doFilter(req, resp);
        } else {
            httpServletResponseWrapper.sendRedirect(httpServletRequestWrapper.getContextPath() + "/");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
