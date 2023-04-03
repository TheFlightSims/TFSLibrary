package gnu.trove.map;

import gnu.trove.TIntCollection;
import gnu.trove.function.TIntFunction;
import gnu.trove.iterator.TShortIntIterator;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.procedure.TShortIntProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TShortSet;
import java.util.Map;

public interface TShortIntMap {
  short getNoEntryKey();
  
  int getNoEntryValue();
  
  int put(short paramShort, int paramInt);
  
  int putIfAbsent(short paramShort, int paramInt);
  
  void putAll(Map<? extends Short, ? extends Integer> paramMap);
  
  void putAll(TShortIntMap paramTShortIntMap);
  
  int get(short paramShort);
  
  void clear();
  
  boolean isEmpty();
  
  int remove(short paramShort);
  
  int size();
  
  TShortSet keySet();
  
  short[] keys();
  
  short[] keys(short[] paramArrayOfshort);
  
  TIntCollection valueCollection();
  
  int[] values();
  
  int[] values(int[] paramArrayOfint);
  
  boolean containsValue(int paramInt);
  
  boolean containsKey(short paramShort);
  
  TShortIntIterator iterator();
  
  boolean forEachKey(TShortProcedure paramTShortProcedure);
  
  boolean forEachValue(TIntProcedure paramTIntProcedure);
  
  boolean forEachEntry(TShortIntProcedure paramTShortIntProcedure);
  
  void transformValues(TIntFunction paramTIntFunction);
  
  boolean retainEntries(TShortIntProcedure paramTShortIntProcedure);
  
  boolean increment(short paramShort);
  
  boolean adjustValue(short paramShort, int paramInt);
  
  int adjustOrPutValue(short paramShort, int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TShortIntMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */