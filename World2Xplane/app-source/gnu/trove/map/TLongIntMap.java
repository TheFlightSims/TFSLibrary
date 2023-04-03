package gnu.trove.map;

import gnu.trove.TIntCollection;
import gnu.trove.function.TIntFunction;
import gnu.trove.iterator.TLongIntIterator;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.procedure.TLongIntProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TLongSet;
import java.util.Map;

public interface TLongIntMap {
  long getNoEntryKey();
  
  int getNoEntryValue();
  
  int put(long paramLong, int paramInt);
  
  int putIfAbsent(long paramLong, int paramInt);
  
  void putAll(Map<? extends Long, ? extends Integer> paramMap);
  
  void putAll(TLongIntMap paramTLongIntMap);
  
  int get(long paramLong);
  
  void clear();
  
  boolean isEmpty();
  
  int remove(long paramLong);
  
  int size();
  
  TLongSet keySet();
  
  long[] keys();
  
  long[] keys(long[] paramArrayOflong);
  
  TIntCollection valueCollection();
  
  int[] values();
  
  int[] values(int[] paramArrayOfint);
  
  boolean containsValue(int paramInt);
  
  boolean containsKey(long paramLong);
  
  TLongIntIterator iterator();
  
  boolean forEachKey(TLongProcedure paramTLongProcedure);
  
  boolean forEachValue(TIntProcedure paramTIntProcedure);
  
  boolean forEachEntry(TLongIntProcedure paramTLongIntProcedure);
  
  void transformValues(TIntFunction paramTIntFunction);
  
  boolean retainEntries(TLongIntProcedure paramTLongIntProcedure);
  
  boolean increment(long paramLong);
  
  boolean adjustValue(long paramLong, int paramInt);
  
  int adjustOrPutValue(long paramLong, int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TLongIntMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */