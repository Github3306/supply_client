package com.qtu.api_gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author Hu Shengkai
 * @create 2020-01-08 9:29
 */
@Component
public class LoginFilter extends ZuulFilter {

    //过滤器类型(前置过滤，在路由之前过滤)
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    //过滤器执行顺序，越小越先执行
    @Override
    public int filterOrder() {
        return 0;
    }

    //决定过滤器是否生效（如果返回false，那就向后放行，如果返回true，那就是拦截处理）
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();
        requestURI = requestURI.substring(0, requestURI.lastIndexOf("/")+1);
        if (requestURI.equalsIgnoreCase("/gate/prod/goods/")) {
            return true;
        }
        return false;
    }

    //当请求被拦截时，
    @Override
    public Object run() throws ZuulException {
        System.out.println("拦截请求……………………");
        return null;
    }
}
