package gnu.trove.map;

import gnu.trove.TFloatCollection;
import gnu.trove.function.TFloatFunction;
import gnu.trove.iterator.TIntFloatIterator;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.procedure.TIntFloatProcedure;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.set.TIntSet;
import java.util.Map;

public interface TIntFloatMap {
  int getNoEntryKey();
  
  float getNoEntryValue();
  
  float put(int paramInt, float paramFloat);
  
  float putIfAbsent(int paramInt, float paramFloat);
  
  void putAll(Map<? extends Integer, ? extends Float> paramMap);
  
  void putAll(TIntFloatMap paramTIntFloatMap);
  
  float get(int paramInt);
  
  void clear();
  
  boolean isEmpty();
  
  float remove(int paramInt);
  
  int size();
  
  TIntSet keySet();
  
  int[] keys();
  
  int[] keys(int[] paramArrayOfint);
  
  TFloatCollection valueCollection();
  
  float[] values();
  
  float[] values(float[] paramArrayOffloat);
  
  boolean containsValue(float paramFloat);
  
  boolean containsKey(int paramInt);
  
  TIntFloatIterator iterator();
  
  boolean forEachKey(TIntProcedure paramTIntProcedure);
  
  boolean forEachValue(TFloatProcedure paramTFloatProcedure);
  
  boolean forEachEntry(TIntFloatProcedure paramTIntFloatProcedure);
  
  void transformValues(TFloatFunction paramTFloatFunction);
  
  boolean retainEntries(TIntFloatProcedure paramTIntFloatProcedure);
  
  boolean increment(int paramInt);
  
  boolean adjustValue(int paramInt, float paramFloat);
  
  float adjustOrPutValue(int paramInt, float paramFloat1, float paramFloat2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TIntFloatMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */