package org.apache.xerces.impl.xs.util;

import java.util.ArrayList;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.XSModelImpl;
import org.apache.xerces.util.XMLGrammarPoolImpl;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xs.XSModel;

public class XSGrammarPool extends XMLGrammarPoolImpl {
  public XSModel toXSModel() {
    ArrayList arrayList = new ArrayList();
    for (byte b = 0; b < this.fGrammars.length; b++) {
      for (XMLGrammarPoolImpl.Entry entry = this.fGrammars[b]; entry != null; entry = entry.next) {
        if (entry.desc.getGrammarType().equals("http://www.w3.org/2001/XMLSchema"))
          arrayList.add(entry.grammar); 
      } 
    } 
    int i = arrayList.size();
    if (i == 0)
      return null; 
    SchemaGrammar[] arrayOfSchemaGrammar = arrayList.<SchemaGrammar>toArray(new SchemaGrammar[i]);
    return (XSModel)new XSModelImpl(arrayOfSchemaGrammar);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\x\\util\XSGrammarPool.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */