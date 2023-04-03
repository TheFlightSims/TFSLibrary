package gnu.trove.map;

import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.iterator.TLongShortIterator;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.procedure.TLongShortProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TLongSet;
import java.util.Map;

public interface TLongShortMap {
  long getNoEntryKey();
  
  short getNoEntryValue();
  
  short put(long paramLong, short paramShort);
  
  short putIfAbsent(long paramLong, short paramShort);
  
  void putAll(Map<? extends Long, ? extends Short> paramMap);
  
  void putAll(TLongShortMap paramTLongShortMap);
  
  short get(long paramLong);
  
  void clear();
  
  boolean isEmpty();
  
  short remove(long paramLong);
  
  int size();
  
  TLongSet keySet();
  
  long[] keys();
  
  long[] keys(long[] paramArrayOflong);
  
  TShortCollection valueCollection();
  
  short[] values();
  
  short[] values(short[] paramArrayOfshort);
  
  boolean containsValue(short paramShort);
  
  boolean containsKey(long paramLong);
  
  TLongShortIterator iterator();
  
  boolean forEachKey(TLongProcedure paramTLongProcedure);
  
  boolean forEachValue(TShortProcedure paramTShortProcedure);
  
  boolean forEachEntry(TLongShortProcedure paramTLongShortProcedure);
  
  void transformValues(TShortFunction paramTShortFunction);
  
  boolean retainEntries(TLongShortProcedure paramTLongShortProcedure);
  
  boolean increment(long paramLong);
  
  boolean adjustValue(long paramLong, short paramShort);
  
  short adjustOrPutValue(long paramLong, short paramShort1, short paramShort2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TLongShortMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */