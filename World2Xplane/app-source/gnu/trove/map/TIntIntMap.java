package gnu.trove.map;

import gnu.trove.TIntCollection;
import gnu.trove.function.TIntFunction;
import gnu.trove.iterator.TIntIntIterator;
import gnu.trove.procedure.TIntIntProcedure;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.set.TIntSet;
import java.util.Map;

public interface TIntIntMap {
  int getNoEntryKey();
  
  int getNoEntryValue();
  
  int put(int paramInt1, int paramInt2);
  
  int putIfAbsent(int paramInt1, int paramInt2);
  
  void putAll(Map<? extends Integer, ? extends Integer> paramMap);
  
  void putAll(TIntIntMap paramTIntIntMap);
  
  int get(int paramInt);
  
  void clear();
  
  boolean isEmpty();
  
  int remove(int paramInt);
  
  int size();
  
  TIntSet keySet();
  
  int[] keys();
  
  int[] keys(int[] paramArrayOfint);
  
  TIntCollection valueCollection();
  
  int[] values();
  
  int[] values(int[] paramArrayOfint);
  
  boolean containsValue(int paramInt);
  
  boolean containsKey(int paramInt);
  
  TIntIntIterator iterator();
  
  boolean forEachKey(TIntProcedure paramTIntProcedure);
  
  boolean forEachValue(TIntProcedure paramTIntProcedure);
  
  boolean forEachEntry(TIntIntProcedure paramTIntIntProcedure);
  
  void transformValues(TIntFunction paramTIntFunction);
  
  boolean retainEntries(TIntIntProcedure paramTIntIntProcedure);
  
  boolean increment(int paramInt);
  
  boolean adjustValue(int paramInt1, int paramInt2);
  
  int adjustOrPutValue(int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TIntIntMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */