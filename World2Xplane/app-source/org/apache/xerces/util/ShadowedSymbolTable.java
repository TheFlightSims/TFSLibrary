package org.apache.xerces.util;

public final class ShadowedSymbolTable extends SymbolTable {
  protected SymbolTable fSymbolTable;
  
  public ShadowedSymbolTable(SymbolTable paramSymbolTable) {
    this.fSymbolTable = paramSymbolTable;
  }
  
  public String addSymbol(String paramString) {
    return this.fSymbolTable.containsSymbol(paramString) ? this.fSymbolTable.addSymbol(paramString) : super.addSymbol(paramString);
  }
  
  public String addSymbol(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    return this.fSymbolTable.containsSymbol(paramArrayOfchar, paramInt1, paramInt2) ? this.fSymbolTable.addSymbol(paramArrayOfchar, paramInt1, paramInt2) : super.addSymbol(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public int hash(String paramString) {
    return this.fSymbolTable.hash(paramString);
  }
  
  public int hash(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    return this.fSymbolTable.hash(paramArrayOfchar, paramInt1, paramInt2);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\ShadowedSymbolTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */