package org.apache.xerces.impl.validation;

import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.NamespaceContext;

public class ValidationState implements ValidationContext {
  private boolean fExtraChecking = true;
  
  private boolean fFacetChecking = true;
  
  private boolean fNormalize = true;
  
  private boolean fNamespaces = true;
  
  private EntityState fEntityState = null;
  
  private NamespaceContext fNamespaceContext = null;
  
  private SymbolTable fSymbolTable = null;
  
  private final Hashtable fIdTable = new Hashtable();
  
  private final Hashtable fIdRefTable = new Hashtable();
  
  private static final Object fNullValue = new Object();
  
  public void setExtraChecking(boolean paramBoolean) {
    this.fExtraChecking = paramBoolean;
  }
  
  public void setFacetChecking(boolean paramBoolean) {
    this.fFacetChecking = paramBoolean;
  }
  
  public void setNormalizationRequired(boolean paramBoolean) {
    this.fNormalize = paramBoolean;
  }
  
  public void setUsingNamespaces(boolean paramBoolean) {
    this.fNamespaces = paramBoolean;
  }
  
  public void setEntityState(EntityState paramEntityState) {
    this.fEntityState = paramEntityState;
  }
  
  public void setNamespaceSupport(NamespaceContext paramNamespaceContext) {
    this.fNamespaceContext = paramNamespaceContext;
  }
  
  public void setSymbolTable(SymbolTable paramSymbolTable) {
    this.fSymbolTable = paramSymbolTable;
  }
  
  public String checkIDRefID() {
    Enumeration enumeration = this.fIdRefTable.keys();
    while (enumeration.hasMoreElements()) {
      String str = enumeration.nextElement();
      if (!this.fIdTable.containsKey(str))
        return str; 
    } 
    return null;
  }
  
  public void reset() {
    this.fExtraChecking = true;
    this.fFacetChecking = true;
    this.fNamespaces = true;
    this.fIdTable.clear();
    this.fIdRefTable.clear();
    this.fEntityState = null;
    this.fNamespaceContext = null;
    this.fSymbolTable = null;
  }
  
  public void resetIDTables() {
    this.fIdTable.clear();
    this.fIdRefTable.clear();
  }
  
  public boolean needExtraChecking() {
    return this.fExtraChecking;
  }
  
  public boolean needFacetChecking() {
    return this.fFacetChecking;
  }
  
  public boolean needToNormalize() {
    return this.fNormalize;
  }
  
  public boolean useNamespaces() {
    return this.fNamespaces;
  }
  
  public boolean isEntityDeclared(String paramString) {
    return (this.fEntityState != null) ? this.fEntityState.isEntityDeclared(getSymbol(paramString)) : false;
  }
  
  public boolean isEntityUnparsed(String paramString) {
    return (this.fEntityState != null) ? this.fEntityState.isEntityUnparsed(getSymbol(paramString)) : false;
  }
  
  public boolean isIdDeclared(String paramString) {
    return this.fIdTable.containsKey(paramString);
  }
  
  public void addId(String paramString) {
    this.fIdTable.put(paramString, fNullValue);
  }
  
  public void addIdRef(String paramString) {
    this.fIdRefTable.put(paramString, fNullValue);
  }
  
  public String getSymbol(String paramString) {
    return (this.fSymbolTable != null) ? this.fSymbolTable.addSymbol(paramString) : paramString.intern();
  }
  
  public String getURI(String paramString) {
    return (this.fNamespaceContext != null) ? this.fNamespaceContext.getURI(paramString) : null;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\validation\ValidationState.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */