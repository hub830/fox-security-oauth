package top.lemna.security.app.validate.code.sms;

public interface SmsCodeSender {
  
  void send(String mobile, String code);

}