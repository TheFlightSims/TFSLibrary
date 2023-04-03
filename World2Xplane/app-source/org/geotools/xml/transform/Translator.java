package org.geotools.xml.transform;

import org.xml.sax.helpers.NamespaceSupport;

public interface Translator {
  NamespaceSupport getNamespaceSupport();
  
  String getDefaultNamespace();
  
  String getDefaultPrefix();
  
  void encode(Object paramObject) throws IllegalArgumentException;
  
  TransformerBase.SchemaLocationSupport getSchemaLocationSupport();
  
  void abort();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\xml\transform\Translator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */