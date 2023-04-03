package gnu.trove.map;

import gnu.trove.function.TObjectFunction;
import gnu.trove.iterator.TShortObjectIterator;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.procedure.TShortObjectProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TShortSet;
import java.util.Collection;
import java.util.Map;

public interface TShortObjectMap<V> {
  short getNoEntryKey();
  
  int size();
  
  boolean isEmpty();
  
  boolean containsKey(short paramShort);
  
  boolean containsValue(Object paramObject);
  
  V get(short paramShort);
  
  V put(short paramShort, V paramV);
  
  V putIfAbsent(short paramShort, V paramV);
  
  V remove(short paramShort);
  
  void putAll(Map<? extends Short, ? extends V> paramMap);
  
  void putAll(TShortObjectMap<? extends V> paramTShortObjectMap);
  
  void clear();
  
  TShortSet keySet();
  
  short[] keys();
  
  short[] keys(short[] paramArrayOfshort);
  
  Collection<V> valueCollection();
  
  Object[] values();
  
  V[] values(V[] paramArrayOfV);
  
  TShortObjectIterator<V> iterator();
  
  boolean forEachKey(TShortProcedure paramTShortProcedure);
  
  boolean forEachValue(TObjectProcedure<? super V> paramTObjectProcedure);
  
  boolean forEachEntry(TShortObjectProcedure<? super V> paramTShortObjectProcedure);
  
  void transformValues(TObjectFunction<V, V> paramTObjectFunction);
  
  boolean retainEntries(TShortObjectProcedure<? super V> paramTShortObjectProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TShortObjectMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */