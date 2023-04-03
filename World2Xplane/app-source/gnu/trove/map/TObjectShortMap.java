package gnu.trove.map;

import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.iterator.TObjectShortIterator;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.procedure.TObjectShortProcedure;
import gnu.trove.procedure.TShortProcedure;
import java.util.Map;
import java.util.Set;

public interface TObjectShortMap<K> {
  short getNoEntryValue();
  
  int size();
  
  boolean isEmpty();
  
  boolean containsKey(Object paramObject);
  
  boolean containsValue(short paramShort);
  
  short get(Object paramObject);
  
  short put(K paramK, short paramShort);
  
  short putIfAbsent(K paramK, short paramShort);
  
  short remove(Object paramObject);
  
  void putAll(Map<? extends K, ? extends Short> paramMap);
  
  void putAll(TObjectShortMap<? extends K> paramTObjectShortMap);
  
  void clear();
  
  Set<K> keySet();
  
  Object[] keys();
  
  K[] keys(K[] paramArrayOfK);
  
  TShortCollection valueCollection();
  
  short[] values();
  
  short[] values(short[] paramArrayOfshort);
  
  TObjectShortIterator<K> iterator();
  
  boolean increment(K paramK);
  
  boolean adjustValue(K paramK, short paramShort);
  
  short adjustOrPutValue(K paramK, short paramShort1, short paramShort2);
  
  boolean forEachKey(TObjectProcedure<? super K> paramTObjectProcedure);
  
  boolean forEachValue(TShortProcedure paramTShortProcedure);
  
  boolean forEachEntry(TObjectShortProcedure<? super K> paramTObjectShortProcedure);
  
  void transformValues(TShortFunction paramTShortFunction);
  
  boolean retainEntries(TObjectShortProcedure<? super K> paramTObjectShortProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TObjectShortMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */