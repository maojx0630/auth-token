package com.github.maojx0630.auth_token.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * @author 毛家兴
 * @since 2022/10/19 15:30
 */
public abstract class ResponseUtils {

  private ResponseUtils() {}

  public static void writeStr(HttpServletResponse response, int httpCode, String data) {
    response.setStatus(httpCode);
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-type", "application/Json;charset=UTF-8");
    try (ServletOutputStream outputStream = response.getOutputStream()) {
      outputStream.write(data.getBytes(StandardCharsets.UTF_8));
    } catch (Exception ignored) {
    }
  }
}
