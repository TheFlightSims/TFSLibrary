package org.codehaus.stax2.osgi;

import org.codehaus.stax2.validation.XMLValidationSchemaFactory;

public interface Stax2ValidationSchemaFactoryProvider {
  public static final String OSGI_SVC_PROP_IMPL_NAME = "org.codehaus.stax2.implName";
  
  public static final String OSGI_SVC_PROP_IMPL_VERSION = "org.codehaus.stax2.implVersion";
  
  public static final String OSGI_SVC_PROP_SCHEMA_TYPE = "org.codehaus.stax2.validation.schemaType";
  
  String getSchemaType();
  
  XMLValidationSchemaFactory createValidationSchemaFactory();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\osgi\Stax2ValidationSchemaFactoryProvider.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */