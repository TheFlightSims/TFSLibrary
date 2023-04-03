package org.apache.xerces.impl.validation;

public final class ConfigurableValidationState extends ValidationState {
  private boolean fIdIdrefChecking = true;
  
  private boolean fUnparsedEntityChecking = true;
  
  public void setIdIdrefChecking(boolean paramBoolean) {
    this.fIdIdrefChecking = paramBoolean;
  }
  
  public void setUnparsedEntityChecking(boolean paramBoolean) {
    this.fUnparsedEntityChecking = paramBoolean;
  }
  
  public String checkIDRefID() {
    return this.fIdIdrefChecking ? super.checkIDRefID() : null;
  }
  
  public boolean isIdDeclared(String paramString) {
    return this.fIdIdrefChecking ? super.isIdDeclared(paramString) : false;
  }
  
  public boolean isEntityDeclared(String paramString) {
    return this.fUnparsedEntityChecking ? super.isEntityDeclared(paramString) : true;
  }
  
  public boolean isEntityUnparsed(String paramString) {
    return this.fUnparsedEntityChecking ? super.isEntityUnparsed(paramString) : true;
  }
  
  public void addId(String paramString) {
    if (this.fIdIdrefChecking)
      super.addId(paramString); 
  }
  
  public void addIdRef(String paramString) {
    if (this.fIdIdrefChecking)
      super.addIdRef(paramString); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\validation\ConfigurableValidationState.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */