package org.apache.xerces.impl.dv.dtd;

import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.xerces.impl.dv.DatatypeValidator;

public class XML11DTDDVFactoryImpl extends DTDDVFactoryImpl {
  static final Hashtable fXML11BuiltInTypes = new Hashtable();
  
  public DatatypeValidator getBuiltInDV(String paramString) {
    return (fXML11BuiltInTypes.get(paramString) != null) ? (DatatypeValidator)fXML11BuiltInTypes.get(paramString) : (DatatypeValidator)DTDDVFactoryImpl.fBuiltInTypes.get(paramString);
  }
  
  public Hashtable getBuiltInTypes() {
    Hashtable hashtable = (Hashtable)DTDDVFactoryImpl.fBuiltInTypes.clone();
    Enumeration enumeration = fXML11BuiltInTypes.keys();
    while (enumeration.hasMoreElements()) {
      Object object = enumeration.nextElement();
      hashtable.put(object, fXML11BuiltInTypes.get(object));
    } 
    return hashtable;
  }
  
  static {
    fXML11BuiltInTypes.put("XML11ID", new XML11IDDatatypeValidator());
    XML11IDREFDatatypeValidator xML11IDREFDatatypeValidator = new XML11IDREFDatatypeValidator();
    fXML11BuiltInTypes.put("XML11IDREF", xML11IDREFDatatypeValidator);
    fXML11BuiltInTypes.put("XML11IDREFS", new ListDatatypeValidator(xML11IDREFDatatypeValidator));
    XML11NMTOKENDatatypeValidator xML11NMTOKENDatatypeValidator = new XML11NMTOKENDatatypeValidator();
    fXML11BuiltInTypes.put("XML11NMTOKEN", xML11NMTOKENDatatypeValidator);
    fXML11BuiltInTypes.put("XML11NMTOKENS", new ListDatatypeValidator(xML11NMTOKENDatatypeValidator));
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\dtd\XML11DTDDVFactoryImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */