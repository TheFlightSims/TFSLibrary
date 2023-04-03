package org.apache.xerces.util;

import java.util.Enumeration;
import java.util.List;
import javax.xml.namespace.NamespaceContext;
import org.apache.xerces.xni.NamespaceContext;

public final class JAXPNamespaceContextWrapper implements NamespaceContext {
  private NamespaceContext fNamespaceContext;
  
  private SymbolTable fSymbolTable;
  
  private List fPrefixes;
  
  public JAXPNamespaceContextWrapper(SymbolTable paramSymbolTable) {
    setSymbolTable(paramSymbolTable);
  }
  
  public void setNamespaceContext(NamespaceContext paramNamespaceContext) {
    this.fNamespaceContext = paramNamespaceContext;
  }
  
  public NamespaceContext getNamespaceContext() {
    return this.fNamespaceContext;
  }
  
  public void setSymbolTable(SymbolTable paramSymbolTable) {
    this.fSymbolTable = paramSymbolTable;
  }
  
  public SymbolTable getSymbolTable() {
    return this.fSymbolTable;
  }
  
  public void setDeclaredPrefixes(List paramList) {
    this.fPrefixes = paramList;
  }
  
  public List getDeclaredPrefixes() {
    return this.fPrefixes;
  }
  
  public String getURI(String paramString) {
    if (this.fNamespaceContext != null) {
      String str = this.fNamespaceContext.getNamespaceURI(paramString);
      if (str != null && !"".equals(str))
        return (this.fSymbolTable != null) ? this.fSymbolTable.addSymbol(str) : str.intern(); 
    } 
    return null;
  }
  
  public String getPrefix(String paramString) {
    if (this.fNamespaceContext != null) {
      if (paramString == null)
        paramString = ""; 
      String str = this.fNamespaceContext.getPrefix(paramString);
      if (str == null)
        str = ""; 
      return (this.fSymbolTable != null) ? this.fSymbolTable.addSymbol(str) : str.intern();
    } 
    return null;
  }
  
  public Enumeration getAllPrefixes() {
    return new Enumeration(this) {
        private final JAXPNamespaceContextWrapper this$0;
        
        public boolean hasMoreElements() {
          return false;
        }
        
        public Object nextElement() {
          return null;
        }
      };
  }
  
  public void pushContext() {}
  
  public void popContext() {}
  
  public boolean declarePrefix(String paramString1, String paramString2) {
    return true;
  }
  
  public int getDeclaredPrefixCount() {
    return (this.fPrefixes != null) ? this.fPrefixes.size() : 0;
  }
  
  public String getDeclaredPrefixAt(int paramInt) {
    return this.fPrefixes.get(paramInt);
  }
  
  public void reset() {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\JAXPNamespaceContextWrapper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */