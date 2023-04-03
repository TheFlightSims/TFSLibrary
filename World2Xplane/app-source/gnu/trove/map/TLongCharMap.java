package gnu.trove.map;

import gnu.trove.TCharCollection;
import gnu.trove.function.TCharFunction;
import gnu.trove.iterator.TLongCharIterator;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TLongCharProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TLongSet;
import java.util.Map;

public interface TLongCharMap {
  long getNoEntryKey();
  
  char getNoEntryValue();
  
  char put(long paramLong, char paramChar);
  
  char putIfAbsent(long paramLong, char paramChar);
  
  void putAll(Map<? extends Long, ? extends Character> paramMap);
  
  void putAll(TLongCharMap paramTLongCharMap);
  
  char get(long paramLong);
  
  void clear();
  
  boolean isEmpty();
  
  char remove(long paramLong);
  
  int size();
  
  TLongSet keySet();
  
  long[] keys();
  
  long[] keys(long[] paramArrayOflong);
  
  TCharCollection valueCollection();
  
  char[] values();
  
  char[] values(char[] paramArrayOfchar);
  
  boolean containsValue(char paramChar);
  
  boolean containsKey(long paramLong);
  
  TLongCharIterator iterator();
  
  boolean forEachKey(TLongProcedure paramTLongProcedure);
  
  boolean forEachValue(TCharProcedure paramTCharProcedure);
  
  boolean forEachEntry(TLongCharProcedure paramTLongCharProcedure);
  
  void transformValues(TCharFunction paramTCharFunction);
  
  boolean retainEntries(TLongCharProcedure paramTLongCharProcedure);
  
  boolean increment(long paramLong);
  
  boolean adjustValue(long paramLong, char paramChar);
  
  char adjustOrPutValue(long paramLong, char paramChar1, char paramChar2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TLongCharMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */