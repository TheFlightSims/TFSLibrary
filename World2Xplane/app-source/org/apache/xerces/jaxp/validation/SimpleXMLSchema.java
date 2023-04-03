package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xni.grammars.XMLGrammarPool;

final class SimpleXMLSchema extends AbstractXMLSchema implements XMLGrammarPool {
  private static final Grammar[] ZERO_LENGTH_GRAMMAR_ARRAY = new Grammar[0];
  
  private final Grammar fGrammar;
  
  private final Grammar[] fGrammars;
  
  private final XMLGrammarDescription fGrammarDescription;
  
  public SimpleXMLSchema(Grammar paramGrammar) {
    this.fGrammar = paramGrammar;
    this.fGrammars = new Grammar[] { paramGrammar };
    this.fGrammarDescription = paramGrammar.getGrammarDescription();
  }
  
  public Grammar[] retrieveInitialGrammarSet(String paramString) {
    return "http://www.w3.org/2001/XMLSchema".equals(paramString) ? (Grammar[])this.fGrammars.clone() : ZERO_LENGTH_GRAMMAR_ARRAY;
  }
  
  public void cacheGrammars(String paramString, Grammar[] paramArrayOfGrammar) {}
  
  public Grammar retrieveGrammar(XMLGrammarDescription paramXMLGrammarDescription) {
    return this.fGrammarDescription.equals(paramXMLGrammarDescription) ? this.fGrammar : null;
  }
  
  public void lockPool() {}
  
  public void unlockPool() {}
  
  public void clear() {}
  
  public XMLGrammarPool getGrammarPool() {
    return this;
  }
  
  public boolean isFullyComposed() {
    return true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\SimpleXMLSchema.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */