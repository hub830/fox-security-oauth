package top.lemna.security.app.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

  /*
   * (non-Javadoc)
   * 
   * @see com.imooc.security.core.validate.code.sms.SmsCodeSender#send(java.lang.String,
   * java.lang.String)
   */
  @Override
  public void send(String mobile, String code) {
    log.warn("向手机:{} 发送短信验证码:{}", mobile, code);
  }

}
