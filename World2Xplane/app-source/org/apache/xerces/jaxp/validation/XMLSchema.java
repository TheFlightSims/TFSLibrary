package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xni.grammars.XMLGrammarPool;

final class XMLSchema extends AbstractXMLSchema {
  private final XMLGrammarPool fGrammarPool;
  
  private final boolean fFullyComposed;
  
  public XMLSchema(XMLGrammarPool paramXMLGrammarPool) {
    this(paramXMLGrammarPool, true);
  }
  
  public XMLSchema(XMLGrammarPool paramXMLGrammarPool, boolean paramBoolean) {
    this.fGrammarPool = paramXMLGrammarPool;
    this.fFullyComposed = paramBoolean;
  }
  
  public XMLGrammarPool getGrammarPool() {
    return this.fGrammarPool;
  }
  
  public boolean isFullyComposed() {
    return this.fFullyComposed;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\XMLSchema.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */