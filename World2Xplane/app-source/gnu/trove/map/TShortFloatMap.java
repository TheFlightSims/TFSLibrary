package gnu.trove.map;

import gnu.trove.TFloatCollection;
import gnu.trove.function.TFloatFunction;
import gnu.trove.iterator.TShortFloatIterator;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.procedure.TShortFloatProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TShortSet;
import java.util.Map;

public interface TShortFloatMap {
  short getNoEntryKey();
  
  float getNoEntryValue();
  
  float put(short paramShort, float paramFloat);
  
  float putIfAbsent(short paramShort, float paramFloat);
  
  void putAll(Map<? extends Short, ? extends Float> paramMap);
  
  void putAll(TShortFloatMap paramTShortFloatMap);
  
  float get(short paramShort);
  
  void clear();
  
  boolean isEmpty();
  
  float remove(short paramShort);
  
  int size();
  
  TShortSet keySet();
  
  short[] keys();
  
  short[] keys(short[] paramArrayOfshort);
  
  TFloatCollection valueCollection();
  
  float[] values();
  
  float[] values(float[] paramArrayOffloat);
  
  boolean containsValue(float paramFloat);
  
  boolean containsKey(short paramShort);
  
  TShortFloatIterator iterator();
  
  boolean forEachKey(TShortProcedure paramTShortProcedure);
  
  boolean forEachValue(TFloatProcedure paramTFloatProcedure);
  
  boolean forEachEntry(TShortFloatProcedure paramTShortFloatProcedure);
  
  void transformValues(TFloatFunction paramTFloatFunction);
  
  boolean retainEntries(TShortFloatProcedure paramTShortFloatProcedure);
  
  boolean increment(short paramShort);
  
  boolean adjustValue(short paramShort, float paramFloat);
  
  float adjustOrPutValue(short paramShort, float paramFloat1, float paramFloat2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TShortFloatMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */