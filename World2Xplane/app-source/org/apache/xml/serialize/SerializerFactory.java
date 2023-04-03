package org.apache.xml.serialize;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Hashtable;
import java.util.StringTokenizer;

public abstract class SerializerFactory {
  public static final String FactoriesProperty = "org.apache.xml.serialize.factories";
  
  private static Hashtable _factories = new Hashtable();
  
  public static void registerSerializerFactory(SerializerFactory paramSerializerFactory) {
    synchronized (_factories) {
      String str = paramSerializerFactory.getSupportedMethod();
      _factories.put(str, paramSerializerFactory);
    } 
  }
  
  public static SerializerFactory getSerializerFactory(String paramString) {
    return (SerializerFactory)_factories.get(paramString);
  }
  
  protected abstract String getSupportedMethod();
  
  public abstract Serializer makeSerializer(OutputFormat paramOutputFormat);
  
  public abstract Serializer makeSerializer(Writer paramWriter, OutputFormat paramOutputFormat);
  
  public abstract Serializer makeSerializer(OutputStream paramOutputStream, OutputFormat paramOutputFormat) throws UnsupportedEncodingException;
  
  static {
    SerializerFactoryImpl serializerFactoryImpl = new SerializerFactoryImpl("xml");
    registerSerializerFactory(serializerFactoryImpl);
    serializerFactoryImpl = new SerializerFactoryImpl("html");
    registerSerializerFactory(serializerFactoryImpl);
    serializerFactoryImpl = new SerializerFactoryImpl("xhtml");
    registerSerializerFactory(serializerFactoryImpl);
    serializerFactoryImpl = new SerializerFactoryImpl("text");
    registerSerializerFactory(serializerFactoryImpl);
    String str = SecuritySupport.getSystemProperty("org.apache.xml.serialize.factories");
    if (str != null) {
      StringTokenizer stringTokenizer = new StringTokenizer(str, " ;,:");
      while (stringTokenizer.hasMoreTokens()) {
        String str1 = stringTokenizer.nextToken();
        try {
          SerializerFactory serializerFactory = (SerializerFactory)ObjectFactory.newInstance(str1, SerializerFactory.class.getClassLoader(), true);
          if (_factories.containsKey(serializerFactory.getSupportedMethod()))
            _factories.put(serializerFactory.getSupportedMethod(), serializerFactory); 
        } catch (Exception exception) {}
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xml\serialize\SerializerFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */