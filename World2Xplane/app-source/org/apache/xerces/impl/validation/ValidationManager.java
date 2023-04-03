package org.apache.xerces.impl.validation;

import java.util.Vector;

public class ValidationManager {
  protected final Vector fVSs = new Vector();
  
  protected boolean fGrammarFound = false;
  
  protected boolean fCachedDTD = false;
  
  public final void addValidationState(ValidationState paramValidationState) {
    this.fVSs.addElement(paramValidationState);
  }
  
  public final void setEntityState(EntityState paramEntityState) {
    for (int i = this.fVSs.size() - 1; i >= 0; i--)
      ((ValidationState)this.fVSs.elementAt(i)).setEntityState(paramEntityState); 
  }
  
  public final void setGrammarFound(boolean paramBoolean) {
    this.fGrammarFound = paramBoolean;
  }
  
  public final boolean isGrammarFound() {
    return this.fGrammarFound;
  }
  
  public final void setCachedDTD(boolean paramBoolean) {
    this.fCachedDTD = paramBoolean;
  }
  
  public final boolean isCachedDTD() {
    return this.fCachedDTD;
  }
  
  public final void reset() {
    this.fVSs.removeAllElements();
    this.fGrammarFound = false;
    this.fCachedDTD = false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\validation\ValidationManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */