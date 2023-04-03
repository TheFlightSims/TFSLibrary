package gnu.trove.map;

import gnu.trove.TIntCollection;
import gnu.trove.function.TIntFunction;
import gnu.trove.iterator.TFloatIntIterator;
import gnu.trove.procedure.TFloatIntProcedure;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.set.TFloatSet;
import java.util.Map;

public interface TFloatIntMap {
  float getNoEntryKey();
  
  int getNoEntryValue();
  
  int put(float paramFloat, int paramInt);
  
  int putIfAbsent(float paramFloat, int paramInt);
  
  void putAll(Map<? extends Float, ? extends Integer> paramMap);
  
  void putAll(TFloatIntMap paramTFloatIntMap);
  
  int get(float paramFloat);
  
  void clear();
  
  boolean isEmpty();
  
  int remove(float paramFloat);
  
  int size();
  
  TFloatSet keySet();
  
  float[] keys();
  
  float[] keys(float[] paramArrayOffloat);
  
  TIntCollection valueCollection();
  
  int[] values();
  
  int[] values(int[] paramArrayOfint);
  
  boolean containsValue(int paramInt);
  
  boolean containsKey(float paramFloat);
  
  TFloatIntIterator iterator();
  
  boolean forEachKey(TFloatProcedure paramTFloatProcedure);
  
  boolean forEachValue(TIntProcedure paramTIntProcedure);
  
  boolean forEachEntry(TFloatIntProcedure paramTFloatIntProcedure);
  
  void transformValues(TIntFunction paramTIntFunction);
  
  boolean retainEntries(TFloatIntProcedure paramTFloatIntProcedure);
  
  boolean increment(float paramFloat);
  
  boolean adjustValue(float paramFloat, int paramInt);
  
  int adjustOrPutValue(float paramFloat, int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TFloatIntMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */