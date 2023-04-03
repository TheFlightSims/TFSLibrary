package gnu.trove.map;

import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.iterator.TFloatShortIterator;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.procedure.TFloatShortProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TFloatSet;
import java.util.Map;

public interface TFloatShortMap {
  float getNoEntryKey();
  
  short getNoEntryValue();
  
  short put(float paramFloat, short paramShort);
  
  short putIfAbsent(float paramFloat, short paramShort);
  
  void putAll(Map<? extends Float, ? extends Short> paramMap);
  
  void putAll(TFloatShortMap paramTFloatShortMap);
  
  short get(float paramFloat);
  
  void clear();
  
  boolean isEmpty();
  
  short remove(float paramFloat);
  
  int size();
  
  TFloatSet keySet();
  
  float[] keys();
  
  float[] keys(float[] paramArrayOffloat);
  
  TShortCollection valueCollection();
  
  short[] values();
  
  short[] values(short[] paramArrayOfshort);
  
  boolean containsValue(short paramShort);
  
  boolean containsKey(float paramFloat);
  
  TFloatShortIterator iterator();
  
  boolean forEachKey(TFloatProcedure paramTFloatProcedure);
  
  boolean forEachValue(TShortProcedure paramTShortProcedure);
  
  boolean forEachEntry(TFloatShortProcedure paramTFloatShortProcedure);
  
  void transformValues(TShortFunction paramTShortFunction);
  
  boolean retainEntries(TFloatShortProcedure paramTFloatShortProcedure);
  
  boolean increment(float paramFloat);
  
  boolean adjustValue(float paramFloat, short paramShort);
  
  short adjustOrPutValue(float paramFloat, short paramShort1, short paramShort2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TFloatShortMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */