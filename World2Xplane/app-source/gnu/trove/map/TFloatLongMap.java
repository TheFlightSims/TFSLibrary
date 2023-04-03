package gnu.trove.map;

import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.iterator.TFloatLongIterator;
import gnu.trove.procedure.TFloatLongProcedure;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TFloatSet;
import java.util.Map;

public interface TFloatLongMap {
  float getNoEntryKey();
  
  long getNoEntryValue();
  
  long put(float paramFloat, long paramLong);
  
  long putIfAbsent(float paramFloat, long paramLong);
  
  void putAll(Map<? extends Float, ? extends Long> paramMap);
  
  void putAll(TFloatLongMap paramTFloatLongMap);
  
  long get(float paramFloat);
  
  void clear();
  
  boolean isEmpty();
  
  long remove(float paramFloat);
  
  int size();
  
  TFloatSet keySet();
  
  float[] keys();
  
  float[] keys(float[] paramArrayOffloat);
  
  TLongCollection valueCollection();
  
  long[] values();
  
  long[] values(long[] paramArrayOflong);
  
  boolean containsValue(long paramLong);
  
  boolean containsKey(float paramFloat);
  
  TFloatLongIterator iterator();
  
  boolean forEachKey(TFloatProcedure paramTFloatProcedure);
  
  boolean forEachValue(TLongProcedure paramTLongProcedure);
  
  boolean forEachEntry(TFloatLongProcedure paramTFloatLongProcedure);
  
  void transformValues(TLongFunction paramTLongFunction);
  
  boolean retainEntries(TFloatLongProcedure paramTFloatLongProcedure);
  
  boolean increment(float paramFloat);
  
  boolean adjustValue(float paramFloat, long paramLong);
  
  long adjustOrPutValue(float paramFloat, long paramLong1, long paramLong2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TFloatLongMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */