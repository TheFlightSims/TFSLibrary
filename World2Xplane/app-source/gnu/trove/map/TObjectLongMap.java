package gnu.trove.map;

import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.iterator.TObjectLongIterator;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.procedure.TObjectLongProcedure;
import gnu.trove.procedure.TObjectProcedure;
import java.util.Map;
import java.util.Set;

public interface TObjectLongMap<K> {
  long getNoEntryValue();
  
  int size();
  
  boolean isEmpty();
  
  boolean containsKey(Object paramObject);
  
  boolean containsValue(long paramLong);
  
  long get(Object paramObject);
  
  long put(K paramK, long paramLong);
  
  long putIfAbsent(K paramK, long paramLong);
  
  long remove(Object paramObject);
  
  void putAll(Map<? extends K, ? extends Long> paramMap);
  
  void putAll(TObjectLongMap<? extends K> paramTObjectLongMap);
  
  void clear();
  
  Set<K> keySet();
  
  Object[] keys();
  
  K[] keys(K[] paramArrayOfK);
  
  TLongCollection valueCollection();
  
  long[] values();
  
  long[] values(long[] paramArrayOflong);
  
  TObjectLongIterator<K> iterator();
  
  boolean increment(K paramK);
  
  boolean adjustValue(K paramK, long paramLong);
  
  long adjustOrPutValue(K paramK, long paramLong1, long paramLong2);
  
  boolean forEachKey(TObjectProcedure<? super K> paramTObjectProcedure);
  
  boolean forEachValue(TLongProcedure paramTLongProcedure);
  
  boolean forEachEntry(TObjectLongProcedure<? super K> paramTObjectLongProcedure);
  
  void transformValues(TLongFunction paramTLongFunction);
  
  boolean retainEntries(TObjectLongProcedure<? super K> paramTObjectLongProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TObjectLongMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */