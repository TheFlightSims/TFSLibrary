package gnu.trove.map;

import gnu.trove.function.TObjectFunction;
import gnu.trove.iterator.TCharObjectIterator;
import gnu.trove.procedure.TCharObjectProcedure;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.set.TCharSet;
import java.util.Collection;
import java.util.Map;

public interface TCharObjectMap<V> {
  char getNoEntryKey();
  
  int size();
  
  boolean isEmpty();
  
  boolean containsKey(char paramChar);
  
  boolean containsValue(Object paramObject);
  
  V get(char paramChar);
  
  V put(char paramChar, V paramV);
  
  V putIfAbsent(char paramChar, V paramV);
  
  V remove(char paramChar);
  
  void putAll(Map<? extends Character, ? extends V> paramMap);
  
  void putAll(TCharObjectMap<? extends V> paramTCharObjectMap);
  
  void clear();
  
  TCharSet keySet();
  
  char[] keys();
  
  char[] keys(char[] paramArrayOfchar);
  
  Collection<V> valueCollection();
  
  Object[] values();
  
  V[] values(V[] paramArrayOfV);
  
  TCharObjectIterator<V> iterator();
  
  boolean forEachKey(TCharProcedure paramTCharProcedure);
  
  boolean forEachValue(TObjectProcedure<? super V> paramTObjectProcedure);
  
  boolean forEachEntry(TCharObjectProcedure<? super V> paramTCharObjectProcedure);
  
  void transformValues(TObjectFunction<V, V> paramTObjectFunction);
  
  boolean retainEntries(TCharObjectProcedure<? super V> paramTCharObjectProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TCharObjectMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */