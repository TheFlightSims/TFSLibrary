package org.apache.xml.serialize;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import org.apache.xerces.dom.DOMMessageFormatter;

final class SerializerFactoryImpl extends SerializerFactory {
  private String _method;
  
  SerializerFactoryImpl(String paramString) {
    this._method = paramString;
    if (!this._method.equals("xml") && !this._method.equals("html") && !this._method.equals("xhtml") && !this._method.equals("text")) {
      String str = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "MethodNotSupported", new Object[] { paramString });
      throw new IllegalArgumentException(str);
    } 
  }
  
  public Serializer makeSerializer(OutputFormat paramOutputFormat) {
    Serializer serializer = getSerializer(paramOutputFormat);
    serializer.setOutputFormat(paramOutputFormat);
    return serializer;
  }
  
  public Serializer makeSerializer(Writer paramWriter, OutputFormat paramOutputFormat) {
    Serializer serializer = getSerializer(paramOutputFormat);
    serializer.setOutputCharStream(paramWriter);
    return serializer;
  }
  
  public Serializer makeSerializer(OutputStream paramOutputStream, OutputFormat paramOutputFormat) throws UnsupportedEncodingException {
    Serializer serializer = getSerializer(paramOutputFormat);
    serializer.setOutputByteStream(paramOutputStream);
    return serializer;
  }
  
  private Serializer getSerializer(OutputFormat paramOutputFormat) {
    if (this._method.equals("xml"))
      return new XMLSerializer(paramOutputFormat); 
    if (this._method.equals("html"))
      return new HTMLSerializer(paramOutputFormat); 
    if (this._method.equals("xhtml"))
      return new XHTMLSerializer(paramOutputFormat); 
    if (this._method.equals("text"))
      return new TextSerializer(); 
    String str = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "MethodNotSupported", new Object[] { this._method });
    throw new IllegalStateException(str);
  }
  
  protected String getSupportedMethod() {
    return this._method;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xml\serialize\SerializerFactoryImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */