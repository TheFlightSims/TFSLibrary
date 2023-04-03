package org.apache.xerces.impl.dv;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DatatypeException extends Exception {
  static final long serialVersionUID = 1940805832730465578L;
  
  protected final String key;
  
  protected final Object[] args;
  
  public DatatypeException(String paramString, Object[] paramArrayOfObject) {
    super(paramString);
    this.key = paramString;
    this.args = paramArrayOfObject;
  }
  
  public String getKey() {
    return this.key;
  }
  
  public Object[] getArgs() {
    return this.args;
  }
  
  public String getMessage() {
    ResourceBundle resourceBundle = null;
    resourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLSchemaMessages");
    if (resourceBundle == null)
      throw new MissingResourceException("Property file not found!", "org.apache.xerces.impl.msg.XMLSchemaMessages", this.key); 
    String str = resourceBundle.getString(this.key);
    if (str == null) {
      str = resourceBundle.getString("BadMessageKey");
      throw new MissingResourceException(str, "org.apache.xerces.impl.msg.XMLSchemaMessages", this.key);
    } 
    if (this.args != null)
      try {
        str = MessageFormat.format(str, this.args);
      } catch (Exception exception) {
        str = resourceBundle.getString("FormatFailed");
        str = str + " " + resourceBundle.getString(this.key);
      }  
    return str;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\DatatypeException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */