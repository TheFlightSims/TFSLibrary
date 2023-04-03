package org.apache.xerces.xni.grammars;

import org.apache.xerces.xs.XSModel;

public interface XSGrammar extends Grammar {
  XSModel toXSModel();
  
  XSModel toXSModel(XSGrammar[] paramArrayOfXSGrammar);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\grammars\XSGrammar.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */