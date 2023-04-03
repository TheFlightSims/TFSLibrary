package org.codehaus.stax2.osgi;

import org.codehaus.stax2.XMLInputFactory2;

public interface Stax2InputFactoryProvider {
  public static final String OSGI_SVC_PROP_IMPL_NAME = "org.codehaus.stax2.implName";
  
  public static final String OSGI_SVC_PROP_IMPL_VERSION = "org.codehaus.stax2.implVersion";
  
  XMLInputFactory2 createInputFactory();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\osgi\Stax2InputFactoryProvider.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */