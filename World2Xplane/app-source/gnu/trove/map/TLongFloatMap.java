package gnu.trove.map;

import gnu.trove.TFloatCollection;
import gnu.trove.function.TFloatFunction;
import gnu.trove.iterator.TLongFloatIterator;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.procedure.TLongFloatProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TLongSet;
import java.util.Map;

public interface TLongFloatMap {
  long getNoEntryKey();
  
  float getNoEntryValue();
  
  float put(long paramLong, float paramFloat);
  
  float putIfAbsent(long paramLong, float paramFloat);
  
  void putAll(Map<? extends Long, ? extends Float> paramMap);
  
  void putAll(TLongFloatMap paramTLongFloatMap);
  
  float get(long paramLong);
  
  void clear();
  
  boolean isEmpty();
  
  float remove(long paramLong);
  
  int size();
  
  TLongSet keySet();
  
  long[] keys();
  
  long[] keys(long[] paramArrayOflong);
  
  TFloatCollection valueCollection();
  
  float[] values();
  
  float[] values(float[] paramArrayOffloat);
  
  boolean containsValue(float paramFloat);
  
  boolean containsKey(long paramLong);
  
  TLongFloatIterator iterator();
  
  boolean forEachKey(TLongProcedure paramTLongProcedure);
  
  boolean forEachValue(TFloatProcedure paramTFloatProcedure);
  
  boolean forEachEntry(TLongFloatProcedure paramTLongFloatProcedure);
  
  void transformValues(TFloatFunction paramTFloatFunction);
  
  boolean retainEntries(TLongFloatProcedure paramTLongFloatProcedure);
  
  boolean increment(long paramLong);
  
  boolean adjustValue(long paramLong, float paramFloat);
  
  float adjustOrPutValue(long paramLong, float paramFloat1, float paramFloat2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TLongFloatMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */