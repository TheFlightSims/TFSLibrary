package gnu.trove.map;

import gnu.trove.TIntCollection;
import gnu.trove.function.TIntFunction;
import gnu.trove.iterator.TObjectIntIterator;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.procedure.TObjectIntProcedure;
import gnu.trove.procedure.TObjectProcedure;
import java.util.Map;
import java.util.Set;

public interface TObjectIntMap<K> {
  int getNoEntryValue();
  
  int size();
  
  boolean isEmpty();
  
  boolean containsKey(Object paramObject);
  
  boolean containsValue(int paramInt);
  
  int get(Object paramObject);
  
  int put(K paramK, int paramInt);
  
  int putIfAbsent(K paramK, int paramInt);
  
  int remove(Object paramObject);
  
  void putAll(Map<? extends K, ? extends Integer> paramMap);
  
  void putAll(TObjectIntMap<? extends K> paramTObjectIntMap);
  
  void clear();
  
  Set<K> keySet();
  
  Object[] keys();
  
  K[] keys(K[] paramArrayOfK);
  
  TIntCollection valueCollection();
  
  int[] values();
  
  int[] values(int[] paramArrayOfint);
  
  TObjectIntIterator<K> iterator();
  
  boolean increment(K paramK);
  
  boolean adjustValue(K paramK, int paramInt);
  
  int adjustOrPutValue(K paramK, int paramInt1, int paramInt2);
  
  boolean forEachKey(TObjectProcedure<? super K> paramTObjectProcedure);
  
  boolean forEachValue(TIntProcedure paramTIntProcedure);
  
  boolean forEachEntry(TObjectIntProcedure<? super K> paramTObjectIntProcedure);
  
  void transformValues(TIntFunction paramTIntFunction);
  
  boolean retainEntries(TObjectIntProcedure<? super K> paramTObjectIntProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TObjectIntMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */