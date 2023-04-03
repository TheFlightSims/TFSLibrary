package org.apache.xerces.impl.dtd;

import org.apache.xerces.xni.parser.XMLComponentManager;

public class XML11DTDValidator extends XMLDTDValidator {
  protected static final String DTD_VALIDATOR_PROPERTY = "http://apache.org/xml/properties/internal/validator/dtd";
  
  public void reset(XMLComponentManager paramXMLComponentManager) {
    XMLDTDValidator xMLDTDValidator = null;
    if ((xMLDTDValidator = (XMLDTDValidator)paramXMLComponentManager.getProperty("http://apache.org/xml/properties/internal/validator/dtd")) != null && xMLDTDValidator != this)
      this.fGrammarBucket = xMLDTDValidator.getGrammarBucket(); 
    super.reset(paramXMLComponentManager);
  }
  
  protected void init() {
    if (this.fValidation || this.fDynamicValidation) {
      super.init();
      try {
        this.fValID = this.fDatatypeValidatorFactory.getBuiltInDV("XML11ID");
        this.fValIDRef = this.fDatatypeValidatorFactory.getBuiltInDV("XML11IDREF");
        this.fValIDRefs = this.fDatatypeValidatorFactory.getBuiltInDV("XML11IDREFS");
        this.fValNMTOKEN = this.fDatatypeValidatorFactory.getBuiltInDV("XML11NMTOKEN");
        this.fValNMTOKENS = this.fDatatypeValidatorFactory.getBuiltInDV("XML11NMTOKENS");
      } catch (Exception exception) {
        exception.printStackTrace(System.err);
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\XML11DTDValidator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */