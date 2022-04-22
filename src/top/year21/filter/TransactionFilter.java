package top.year21.filter;

import top.year21.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/4/1 8:52
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();//无异常,提交事务
        } catch (Exception e) {
            JDBCUtils.rollBack();//有异常，回滚事务
            e.printStackTrace();
            //抛出异常信息，让tomcat服务器捕获，不能在此处try-catch处理了，否则tomcat配置的error-page页面不产生效果
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
