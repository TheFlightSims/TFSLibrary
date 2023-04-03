package gnu.trove.map;

import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.iterator.TIntLongIterator;
import gnu.trove.procedure.TIntLongProcedure;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TIntSet;
import java.util.Map;

public interface TIntLongMap {
  int getNoEntryKey();
  
  long getNoEntryValue();
  
  long put(int paramInt, long paramLong);
  
  long putIfAbsent(int paramInt, long paramLong);
  
  void putAll(Map<? extends Integer, ? extends Long> paramMap);
  
  void putAll(TIntLongMap paramTIntLongMap);
  
  long get(int paramInt);
  
  void clear();
  
  boolean isEmpty();
  
  long remove(int paramInt);
  
  int size();
  
  TIntSet keySet();
  
  int[] keys();
  
  int[] keys(int[] paramArrayOfint);
  
  TLongCollection valueCollection();
  
  long[] values();
  
  long[] values(long[] paramArrayOflong);
  
  boolean containsValue(long paramLong);
  
  boolean containsKey(int paramInt);
  
  TIntLongIterator iterator();
  
  boolean forEachKey(TIntProcedure paramTIntProcedure);
  
  boolean forEachValue(TLongProcedure paramTLongProcedure);
  
  boolean forEachEntry(TIntLongProcedure paramTIntLongProcedure);
  
  void transformValues(TLongFunction paramTLongFunction);
  
  boolean retainEntries(TIntLongProcedure paramTIntLongProcedure);
  
  boolean increment(int paramInt);
  
  boolean adjustValue(int paramInt, long paramLong);
  
  long adjustOrPutValue(int paramInt, long paramLong1, long paramLong2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TIntLongMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */