package org.apache.xerces.util;

public final class SecurityManager {
  private static final int DEFAULT_ENTITY_EXPANSION_LIMIT = 100000;
  
  private static final int DEFAULT_MAX_OCCUR_NODE_LIMIT = 3000;
  
  private int entityExpansionLimit = 100000;
  
  private int maxOccurLimit = 3000;
  
  public void setEntityExpansionLimit(int paramInt) {
    this.entityExpansionLimit = paramInt;
  }
  
  public int getEntityExpansionLimit() {
    return this.entityExpansionLimit;
  }
  
  public void setMaxOccurNodeLimit(int paramInt) {
    this.maxOccurLimit = paramInt;
  }
  
  public int getMaxOccurNodeLimit() {
    return this.maxOccurLimit;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\SecurityManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */