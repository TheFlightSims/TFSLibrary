package gnu.trove.map;

import gnu.trove.TCharCollection;
import gnu.trove.function.TCharFunction;
import gnu.trove.iterator.TFloatCharIterator;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TFloatCharProcedure;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.set.TFloatSet;
import java.util.Map;

public interface TFloatCharMap {
  float getNoEntryKey();
  
  char getNoEntryValue();
  
  char put(float paramFloat, char paramChar);
  
  char putIfAbsent(float paramFloat, char paramChar);
  
  void putAll(Map<? extends Float, ? extends Character> paramMap);
  
  void putAll(TFloatCharMap paramTFloatCharMap);
  
  char get(float paramFloat);
  
  void clear();
  
  boolean isEmpty();
  
  char remove(float paramFloat);
  
  int size();
  
  TFloatSet keySet();
  
  float[] keys();
  
  float[] keys(float[] paramArrayOffloat);
  
  TCharCollection valueCollection();
  
  char[] values();
  
  char[] values(char[] paramArrayOfchar);
  
  boolean containsValue(char paramChar);
  
  boolean containsKey(float paramFloat);
  
  TFloatCharIterator iterator();
  
  boolean forEachKey(TFloatProcedure paramTFloatProcedure);
  
  boolean forEachValue(TCharProcedure paramTCharProcedure);
  
  boolean forEachEntry(TFloatCharProcedure paramTFloatCharProcedure);
  
  void transformValues(TCharFunction paramTCharFunction);
  
  boolean retainEntries(TFloatCharProcedure paramTFloatCharProcedure);
  
  boolean increment(float paramFloat);
  
  boolean adjustValue(float paramFloat, char paramChar);
  
  char adjustOrPutValue(float paramFloat, char paramChar1, char paramChar2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TFloatCharMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */