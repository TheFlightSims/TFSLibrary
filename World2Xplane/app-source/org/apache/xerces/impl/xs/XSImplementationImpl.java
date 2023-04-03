package org.apache.xerces.impl.xs;

import org.apache.xerces.dom.CoreDOMImplementationImpl;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSException;
import org.apache.xerces.xs.XSImplementation;
import org.apache.xerces.xs.XSLoader;
import org.w3c.dom.DOMImplementation;

public class XSImplementationImpl extends CoreDOMImplementationImpl implements XSImplementation {
  static XSImplementationImpl singleton = new XSImplementationImpl();
  
  public static DOMImplementation getDOMImplementation() {
    return (DOMImplementation)singleton;
  }
  
  public boolean hasFeature(String paramString1, String paramString2) {
    return ((paramString1.equalsIgnoreCase("XS-Loader") && (paramString2 == null || paramString2.equals("1.0"))) || super.hasFeature(paramString1, paramString2));
  }
  
  public XSLoader createXSLoader(StringList paramStringList) throws XSException {
    XSLoaderImpl xSLoaderImpl = new XSLoaderImpl();
    if (paramStringList == null)
      return xSLoaderImpl; 
    for (byte b = 0; b < paramStringList.getLength(); b++) {
      if (!paramStringList.item(b).equals("1.0")) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { paramStringList.item(b) });
        throw new XSException((short)1, str);
      } 
    } 
    return xSLoaderImpl;
  }
  
  public StringList getRecognizedVersions() {
    return (StringList)new StringListImpl(new String[] { "1.0" }, 1);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\XSImplementationImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */