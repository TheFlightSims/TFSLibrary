package gnu.trove.map;

import gnu.trove.function.TObjectFunction;
import gnu.trove.iterator.TDoubleObjectIterator;
import gnu.trove.procedure.TDoubleObjectProcedure;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.set.TDoubleSet;
import java.util.Collection;
import java.util.Map;

public interface TDoubleObjectMap<V> {
  double getNoEntryKey();
  
  int size();
  
  boolean isEmpty();
  
  boolean containsKey(double paramDouble);
  
  boolean containsValue(Object paramObject);
  
  V get(double paramDouble);
  
  V put(double paramDouble, V paramV);
  
  V putIfAbsent(double paramDouble, V paramV);
  
  V remove(double paramDouble);
  
  void putAll(Map<? extends Double, ? extends V> paramMap);
  
  void putAll(TDoubleObjectMap<? extends V> paramTDoubleObjectMap);
  
  void clear();
  
  TDoubleSet keySet();
  
  double[] keys();
  
  double[] keys(double[] paramArrayOfdouble);
  
  Collection<V> valueCollection();
  
  Object[] values();
  
  V[] values(V[] paramArrayOfV);
  
  TDoubleObjectIterator<V> iterator();
  
  boolean forEachKey(TDoubleProcedure paramTDoubleProcedure);
  
  boolean forEachValue(TObjectProcedure<? super V> paramTObjectProcedure);
  
  boolean forEachEntry(TDoubleObjectProcedure<? super V> paramTDoubleObjectProcedure);
  
  void transformValues(TObjectFunction<V, V> paramTObjectFunction);
  
  boolean retainEntries(TDoubleObjectProcedure<? super V> paramTDoubleObjectProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TDoubleObjectMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */