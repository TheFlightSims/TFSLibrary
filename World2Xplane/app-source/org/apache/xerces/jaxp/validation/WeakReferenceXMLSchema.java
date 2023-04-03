package org.apache.xerces.jaxp.validation;

import java.lang.ref.WeakReference;
import org.apache.xerces.xni.grammars.XMLGrammarPool;

final class WeakReferenceXMLSchema extends AbstractXMLSchema {
  private WeakReference fGrammarPool = new WeakReference(null);
  
  public synchronized XMLGrammarPool getGrammarPool() {
    XMLGrammarPool xMLGrammarPool = this.fGrammarPool.get();
    if (xMLGrammarPool == null) {
      xMLGrammarPool = new SoftReferenceGrammarPool();
      this.fGrammarPool = new WeakReference(xMLGrammarPool);
    } 
    return xMLGrammarPool;
  }
  
  public boolean isFullyComposed() {
    return false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\WeakReferenceXMLSchema.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */