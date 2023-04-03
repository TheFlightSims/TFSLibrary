package gnu.trove.map;

import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.iterator.TCharLongIterator;
import gnu.trove.procedure.TCharLongProcedure;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TCharSet;
import java.util.Map;

public interface TCharLongMap {
  char getNoEntryKey();
  
  long getNoEntryValue();
  
  long put(char paramChar, long paramLong);
  
  long putIfAbsent(char paramChar, long paramLong);
  
  void putAll(Map<? extends Character, ? extends Long> paramMap);
  
  void putAll(TCharLongMap paramTCharLongMap);
  
  long get(char paramChar);
  
  void clear();
  
  boolean isEmpty();
  
  long remove(char paramChar);
  
  int size();
  
  TCharSet keySet();
  
  char[] keys();
  
  char[] keys(char[] paramArrayOfchar);
  
  TLongCollection valueCollection();
  
  long[] values();
  
  long[] values(long[] paramArrayOflong);
  
  boolean containsValue(long paramLong);
  
  boolean containsKey(char paramChar);
  
  TCharLongIterator iterator();
  
  boolean forEachKey(TCharProcedure paramTCharProcedure);
  
  boolean forEachValue(TLongProcedure paramTLongProcedure);
  
  boolean forEachEntry(TCharLongProcedure paramTCharLongProcedure);
  
  void transformValues(TLongFunction paramTLongFunction);
  
  boolean retainEntries(TCharLongProcedure paramTCharLongProcedure);
  
  boolean increment(char paramChar);
  
  boolean adjustValue(char paramChar, long paramLong);
  
  long adjustOrPutValue(char paramChar, long paramLong1, long paramLong2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TCharLongMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */