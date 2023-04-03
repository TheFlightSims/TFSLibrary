package org.apache.xerces.impl.xs;

public class XMLSchemaException extends Exception {
  static final long serialVersionUID = -9096984648537046218L;
  
  String key;
  
  Object[] args;
  
  public XMLSchemaException(String paramString, Object[] paramArrayOfObject) {
    this.key = paramString;
    this.args = paramArrayOfObject;
  }
  
  public String getKey() {
    return this.key;
  }
  
  public Object[] getArgs() {
    return this.args;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\XMLSchemaException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */