package top.lemna.security.app.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ValidateCode implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1588203828504660915L;

  private String code;

  private LocalDateTime expireTime;


  public ValidateCode(String code, int expireIn) {
    this.code = code;
    this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
  }

  public ValidateCode(String code, LocalDateTime expireTime) {
    this.code = code;
    this.expireTime = expireTime;
  }

  @JsonIgnore
  public boolean isExpried() {
    return LocalDateTime.now().isAfter(expireTime);
  }


}
