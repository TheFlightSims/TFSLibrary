package org.apache.xerces.impl.dv.dtd;

import java.util.Hashtable;
import org.apache.xerces.impl.dv.DTDDVFactory;
import org.apache.xerces.impl.dv.DatatypeValidator;

public class DTDDVFactoryImpl extends DTDDVFactory {
  static final Hashtable fBuiltInTypes = new Hashtable();
  
  public DatatypeValidator getBuiltInDV(String paramString) {
    return (DatatypeValidator)fBuiltInTypes.get(paramString);
  }
  
  public Hashtable getBuiltInTypes() {
    return (Hashtable)fBuiltInTypes.clone();
  }
  
  static void createBuiltInTypes() {
    fBuiltInTypes.put("string", new StringDatatypeValidator());
    fBuiltInTypes.put("ID", new IDDatatypeValidator());
    IDREFDatatypeValidator iDREFDatatypeValidator = new IDREFDatatypeValidator();
    fBuiltInTypes.put("IDREF", iDREFDatatypeValidator);
    fBuiltInTypes.put("IDREFS", new ListDatatypeValidator(iDREFDatatypeValidator));
    ENTITYDatatypeValidator eNTITYDatatypeValidator = new ENTITYDatatypeValidator();
    fBuiltInTypes.put("ENTITY", new ENTITYDatatypeValidator());
    fBuiltInTypes.put("ENTITIES", new ListDatatypeValidator(eNTITYDatatypeValidator));
    fBuiltInTypes.put("NOTATION", new NOTATIONDatatypeValidator());
    NMTOKENDatatypeValidator nMTOKENDatatypeValidator = new NMTOKENDatatypeValidator();
    fBuiltInTypes.put("NMTOKEN", nMTOKENDatatypeValidator);
    fBuiltInTypes.put("NMTOKENS", new ListDatatypeValidator(nMTOKENDatatypeValidator));
  }
  
  static {
    createBuiltInTypes();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\dtd\DTDDVFactoryImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */