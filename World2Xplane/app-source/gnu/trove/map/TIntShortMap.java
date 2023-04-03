package gnu.trove.map;

import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.iterator.TIntShortIterator;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.procedure.TIntShortProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TIntSet;
import java.util.Map;

public interface TIntShortMap {
  int getNoEntryKey();
  
  short getNoEntryValue();
  
  short put(int paramInt, short paramShort);
  
  short putIfAbsent(int paramInt, short paramShort);
  
  void putAll(Map<? extends Integer, ? extends Short> paramMap);
  
  void putAll(TIntShortMap paramTIntShortMap);
  
  short get(int paramInt);
  
  void clear();
  
  boolean isEmpty();
  
  short remove(int paramInt);
  
  int size();
  
  TIntSet keySet();
  
  int[] keys();
  
  int[] keys(int[] paramArrayOfint);
  
  TShortCollection valueCollection();
  
  short[] values();
  
  short[] values(short[] paramArrayOfshort);
  
  boolean containsValue(short paramShort);
  
  boolean containsKey(int paramInt);
  
  TIntShortIterator iterator();
  
  boolean forEachKey(TIntProcedure paramTIntProcedure);
  
  boolean forEachValue(TShortProcedure paramTShortProcedure);
  
  boolean forEachEntry(TIntShortProcedure paramTIntShortProcedure);
  
  void transformValues(TShortFunction paramTShortFunction);
  
  boolean retainEntries(TIntShortProcedure paramTIntShortProcedure);
  
  boolean increment(int paramInt);
  
  boolean adjustValue(int paramInt, short paramShort);
  
  short adjustOrPutValue(int paramInt, short paramShort1, short paramShort2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TIntShortMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */