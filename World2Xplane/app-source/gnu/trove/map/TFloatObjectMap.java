package gnu.trove.map;

import gnu.trove.function.TObjectFunction;
import gnu.trove.iterator.TFloatObjectIterator;
import gnu.trove.procedure.TFloatObjectProcedure;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.set.TFloatSet;
import java.util.Collection;
import java.util.Map;

public interface TFloatObjectMap<V> {
  float getNoEntryKey();
  
  int size();
  
  boolean isEmpty();
  
  boolean containsKey(float paramFloat);
  
  boolean containsValue(Object paramObject);
  
  V get(float paramFloat);
  
  V put(float paramFloat, V paramV);
  
  V putIfAbsent(float paramFloat, V paramV);
  
  V remove(float paramFloat);
  
  void putAll(Map<? extends Float, ? extends V> paramMap);
  
  void putAll(TFloatObjectMap<? extends V> paramTFloatObjectMap);
  
  void clear();
  
  TFloatSet keySet();
  
  float[] keys();
  
  float[] keys(float[] paramArrayOffloat);
  
  Collection<V> valueCollection();
  
  Object[] values();
  
  V[] values(V[] paramArrayOfV);
  
  TFloatObjectIterator<V> iterator();
  
  boolean forEachKey(TFloatProcedure paramTFloatProcedure);
  
  boolean forEachValue(TObjectProcedure<? super V> paramTObjectProcedure);
  
  boolean forEachEntry(TFloatObjectProcedure<? super V> paramTFloatObjectProcedure);
  
  void transformValues(TObjectFunction<V, V> paramTObjectFunction);
  
  boolean retainEntries(TFloatObjectProcedure<? super V> paramTFloatObjectProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TFloatObjectMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */