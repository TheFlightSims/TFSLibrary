package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xni.grammars.XMLGrammarPool;

public interface XSGrammarPoolContainer {
  XMLGrammarPool getGrammarPool();
  
  boolean isFullyComposed();
  
  Boolean getFeature(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\XSGrammarPoolContainer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */