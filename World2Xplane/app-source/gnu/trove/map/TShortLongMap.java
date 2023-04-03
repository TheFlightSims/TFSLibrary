package gnu.trove.map;

import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.iterator.TShortLongIterator;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.procedure.TShortLongProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TShortSet;
import java.util.Map;

public interface TShortLongMap {
  short getNoEntryKey();
  
  long getNoEntryValue();
  
  long put(short paramShort, long paramLong);
  
  long putIfAbsent(short paramShort, long paramLong);
  
  void putAll(Map<? extends Short, ? extends Long> paramMap);
  
  void putAll(TShortLongMap paramTShortLongMap);
  
  long get(short paramShort);
  
  void clear();
  
  boolean isEmpty();
  
  long remove(short paramShort);
  
  int size();
  
  TShortSet keySet();
  
  short[] keys();
  
  short[] keys(short[] paramArrayOfshort);
  
  TLongCollection valueCollection();
  
  long[] values();
  
  long[] values(long[] paramArrayOflong);
  
  boolean containsValue(long paramLong);
  
  boolean containsKey(short paramShort);
  
  TShortLongIterator iterator();
  
  boolean forEachKey(TShortProcedure paramTShortProcedure);
  
  boolean forEachValue(TLongProcedure paramTLongProcedure);
  
  boolean forEachEntry(TShortLongProcedure paramTShortLongProcedure);
  
  void transformValues(TLongFunction paramTLongFunction);
  
  boolean retainEntries(TShortLongProcedure paramTShortLongProcedure);
  
  boolean increment(short paramShort);
  
  boolean adjustValue(short paramShort, long paramLong);
  
  long adjustOrPutValue(short paramShort, long paramLong1, long paramLong2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TShortLongMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */