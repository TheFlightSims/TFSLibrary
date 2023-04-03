package org.apache.xerces.impl.dv;

import java.util.Hashtable;

public abstract class DTDDVFactory {
  private static final String DEFAULT_FACTORY_CLASS = "org.apache.xerces.impl.dv.dtd.DTDDVFactoryImpl";
  
  public static final DTDDVFactory getInstance() throws DVFactoryException {
    return getInstance("org.apache.xerces.impl.dv.dtd.DTDDVFactoryImpl");
  }
  
  public static final DTDDVFactory getInstance(String paramString) throws DVFactoryException {
    try {
      return (DTDDVFactory)ObjectFactory.newInstance(paramString, ObjectFactory.findClassLoader(), true);
    } catch (ClassCastException classCastException) {
      throw new DVFactoryException("DTD factory class " + paramString + " does not extend from DTDDVFactory.");
    } 
  }
  
  public abstract DatatypeValidator getBuiltInDV(String paramString);
  
  public abstract Hashtable getBuiltInTypes();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\DTDDVFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */