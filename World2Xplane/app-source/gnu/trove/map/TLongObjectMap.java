package gnu.trove.map;

import gnu.trove.function.TObjectFunction;
import gnu.trove.iterator.TLongObjectIterator;
import gnu.trove.procedure.TLongObjectProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.set.TLongSet;
import java.util.Collection;
import java.util.Map;

public interface TLongObjectMap<V> {
  long getNoEntryKey();
  
  int size();
  
  boolean isEmpty();
  
  boolean containsKey(long paramLong);
  
  boolean containsValue(Object paramObject);
  
  V get(long paramLong);
  
  V put(long paramLong, V paramV);
  
  V putIfAbsent(long paramLong, V paramV);
  
  V remove(long paramLong);
  
  void putAll(Map<? extends Long, ? extends V> paramMap);
  
  void putAll(TLongObjectMap<? extends V> paramTLongObjectMap);
  
  void clear();
  
  TLongSet keySet();
  
  long[] keys();
  
  long[] keys(long[] paramArrayOflong);
  
  Collection<V> valueCollection();
  
  Object[] values();
  
  V[] values(V[] paramArrayOfV);
  
  TLongObjectIterator<V> iterator();
  
  boolean forEachKey(TLongProcedure paramTLongProcedure);
  
  boolean forEachValue(TObjectProcedure<? super V> paramTObjectProcedure);
  
  boolean forEachEntry(TLongObjectProcedure<? super V> paramTLongObjectProcedure);
  
  void transformValues(TObjectFunction<V, V> paramTObjectFunction);
  
  boolean retainEntries(TLongObjectProcedure<? super V> paramTLongObjectProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TLongObjectMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */