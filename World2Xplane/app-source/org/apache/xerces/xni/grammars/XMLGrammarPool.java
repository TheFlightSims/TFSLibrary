package org.apache.xerces.xni.grammars;

public interface XMLGrammarPool {
  Grammar[] retrieveInitialGrammarSet(String paramString);
  
  void cacheGrammars(String paramString, Grammar[] paramArrayOfGrammar);
  
  Grammar retrieveGrammar(XMLGrammarDescription paramXMLGrammarDescription);
  
  void lockPool();
  
  void unlockPool();
  
  void clear();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\grammars\XMLGrammarPool.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */