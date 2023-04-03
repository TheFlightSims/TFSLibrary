package gnu.trove.map;

import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.iterator.TLongLongIterator;
import gnu.trove.procedure.TLongLongProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TLongSet;
import java.util.Map;

public interface TLongLongMap {
  long getNoEntryKey();
  
  long getNoEntryValue();
  
  long put(long paramLong1, long paramLong2);
  
  long putIfAbsent(long paramLong1, long paramLong2);
  
  void putAll(Map<? extends Long, ? extends Long> paramMap);
  
  void putAll(TLongLongMap paramTLongLongMap);
  
  long get(long paramLong);
  
  void clear();
  
  boolean isEmpty();
  
  long remove(long paramLong);
  
  int size();
  
  TLongSet keySet();
  
  long[] keys();
  
  long[] keys(long[] paramArrayOflong);
  
  TLongCollection valueCollection();
  
  long[] values();
  
  long[] values(long[] paramArrayOflong);
  
  boolean containsValue(long paramLong);
  
  boolean containsKey(long paramLong);
  
  TLongLongIterator iterator();
  
  boolean forEachKey(TLongProcedure paramTLongProcedure);
  
  boolean forEachValue(TLongProcedure paramTLongProcedure);
  
  boolean forEachEntry(TLongLongProcedure paramTLongLongProcedure);
  
  void transformValues(TLongFunction paramTLongFunction);
  
  boolean retainEntries(TLongLongProcedure paramTLongLongProcedure);
  
  boolean increment(long paramLong);
  
  boolean adjustValue(long paramLong1, long paramLong2);
  
  long adjustOrPutValue(long paramLong1, long paramLong2, long paramLong3);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TLongLongMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */