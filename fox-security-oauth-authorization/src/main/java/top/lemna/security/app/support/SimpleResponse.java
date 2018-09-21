package top.lemna.security.app.support;

import lombok.Data;

@Data
public class SimpleResponse {
  private Object content;

  public SimpleResponse(Object content) {
    super();
    this.content = content;
  }
}
