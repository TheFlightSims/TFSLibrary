package org.apache.xerces.dom3.as;

public interface CharacterDataEditAS extends NodeEditAS {
  boolean getIsWhitespaceOnly();
  
  boolean canSetData(int paramInt1, int paramInt2);
  
  boolean canAppendData(String paramString);
  
  boolean canReplaceData(int paramInt1, int paramInt2, String paramString);
  
  boolean canInsertData(int paramInt, String paramString);
  
  boolean canDeleteData(int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom3\as\CharacterDataEditAS.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */