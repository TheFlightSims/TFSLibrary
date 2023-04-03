package org.apache.xerces.impl.dtd;

import java.util.Hashtable;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;

public class DTDGrammarBucket {
  protected final Hashtable fGrammars = new Hashtable();
  
  protected DTDGrammar fActiveGrammar;
  
  protected boolean fIsStandalone;
  
  public void putGrammar(DTDGrammar paramDTDGrammar) {
    XMLDTDDescription xMLDTDDescription = (XMLDTDDescription)paramDTDGrammar.getGrammarDescription();
    this.fGrammars.put(xMLDTDDescription, paramDTDGrammar);
  }
  
  public DTDGrammar getGrammar(XMLGrammarDescription paramXMLGrammarDescription) {
    return (DTDGrammar)this.fGrammars.get(paramXMLGrammarDescription);
  }
  
  public void clear() {
    this.fGrammars.clear();
    this.fActiveGrammar = null;
    this.fIsStandalone = false;
  }
  
  void setStandalone(boolean paramBoolean) {
    this.fIsStandalone = paramBoolean;
  }
  
  boolean getStandalone() {
    return this.fIsStandalone;
  }
  
  void setActiveGrammar(DTDGrammar paramDTDGrammar) {
    this.fActiveGrammar = paramDTDGrammar;
  }
  
  DTDGrammar getActiveGrammar() {
    return this.fActiveGrammar;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\DTDGrammarBucket.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */