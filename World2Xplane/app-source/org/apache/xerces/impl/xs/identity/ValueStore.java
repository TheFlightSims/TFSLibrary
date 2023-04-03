package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.xs.ShortList;

public interface ValueStore {
  void addValue(Field paramField, boolean paramBoolean, Object paramObject, short paramShort, ShortList paramShortList);
  
  void reportError(String paramString, Object[] paramArrayOfObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\identity\ValueStore.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */