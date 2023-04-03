package gnu.trove.map;

import gnu.trove.TFloatCollection;
import gnu.trove.function.TFloatFunction;
import gnu.trove.iterator.TFloatFloatIterator;
import gnu.trove.procedure.TFloatFloatProcedure;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.set.TFloatSet;
import java.util.Map;

public interface TFloatFloatMap {
  float getNoEntryKey();
  
  float getNoEntryValue();
  
  float put(float paramFloat1, float paramFloat2);
  
  float putIfAbsent(float paramFloat1, float paramFloat2);
  
  void putAll(Map<? extends Float, ? extends Float> paramMap);
  
  void putAll(TFloatFloatMap paramTFloatFloatMap);
  
  float get(float paramFloat);
  
  void clear();
  
  boolean isEmpty();
  
  float remove(float paramFloat);
  
  int size();
  
  TFloatSet keySet();
  
  float[] keys();
  
  float[] keys(float[] paramArrayOffloat);
  
  TFloatCollection valueCollection();
  
  float[] values();
  
  float[] values(float[] paramArrayOffloat);
  
  boolean containsValue(float paramFloat);
  
  boolean containsKey(float paramFloat);
  
  TFloatFloatIterator iterator();
  
  boolean forEachKey(TFloatProcedure paramTFloatProcedure);
  
  boolean forEachValue(TFloatProcedure paramTFloatProcedure);
  
  boolean forEachEntry(TFloatFloatProcedure paramTFloatFloatProcedure);
  
  void transformValues(TFloatFunction paramTFloatFunction);
  
  boolean retainEntries(TFloatFloatProcedure paramTFloatFloatProcedure);
  
  boolean increment(float paramFloat);
  
  boolean adjustValue(float paramFloat1, float paramFloat2);
  
  float adjustOrPutValue(float paramFloat1, float paramFloat2, float paramFloat3);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TFloatFloatMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */