package tech.ganyaozi.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取HTTP请求的一些工具类
 *
 * @author Derek.P.Dai[dp419936514@gmail.com]
 * @date 2017/9/14 17:08
 **/
public class RequestUtil {

    public static final int DEFAULT_HTTP_PORT = 80;
    public static final int DEFAULT_HTTPS_PROT = 443;
    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";

    private static final String HEADER_NAME_X_REQUESTED_WITH = "X-Requested-With";
    private static final String XHR_XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String HEADER_NAME_X_FORWARDED_FOR = "x-forwarded-for";
    private static final String HEADER_NAME_PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String HEADER_NAME_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    public static String getDomain(HttpServletRequest request) {
        String domain = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != DEFAULT_HTTP_PORT) {
            domain += ":" + request.getServerPort();
        }
        return domain;
    }

    public static String getUrlWithoutQueryString(HttpServletRequest request) {
        String domain = getDomain(request);
        String url = request.getRequestURI();
        return domain + url;
    }

    public static boolean isPost(HttpServletRequest request) {
        return METHOD_POST.equalsIgnoreCase(request.getMethod());
    }

    public static boolean isGet(HttpServletRequest request) {
        return METHOD_GET.equalsIgnoreCase(request.getMethod());
    }

    public static String getRequestUrl(HttpServletRequest request) {
        String url = getRequestUrl(request);
        String queryString = request.getQueryString();
        if (StringUtils.isNotEmpty(queryString)) {
            url += "?" + queryString;
        }
        return url;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader(HEADER_NAME_X_REQUESTED_WITH);
        return StringUtils.contains(header, XHR_XML_HTTP_REQUEST);
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader(HEADER_NAME_X_FORWARDED_FOR);
        if (StringUtils.isEmpty(ip) || StringConsts.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HEADER_NAME_PROXY_CLIENT_IP);
        }
        if (StringUtils.isEmpty(ip) || StringConsts.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HEADER_NAME_WL_PROXY_CLIENT_IP);
        }
        if (StringUtils.isEmpty(ip) || StringConsts.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.isNotEmpty(ip)) {
            return ip.split(",")[0];
        }
        return StringConsts.UNKNOWN;
    }

}
