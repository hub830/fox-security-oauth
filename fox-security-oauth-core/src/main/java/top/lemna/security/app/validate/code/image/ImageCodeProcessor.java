package top.lemna.security.app.validate.code.image;

import javax.imageio.ImageIO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import top.lemna.security.app.validate.code.impl.AbstractValidateCodeProcessor;

/**
 * 图片验证码处理器
 * 
 * @author mux
 *
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

  /**
   * 发送图形验证码，将其写到响应中
   */
  @Override
  protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
    ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
  }

}
