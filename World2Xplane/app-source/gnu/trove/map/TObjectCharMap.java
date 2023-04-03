package gnu.trove.map;

import gnu.trove.TCharCollection;
import gnu.trove.function.TCharFunction;
import gnu.trove.iterator.TObjectCharIterator;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TObjectCharProcedure;
import gnu.trove.procedure.TObjectProcedure;
import java.util.Map;
import java.util.Set;

public interface TObjectCharMap<K> {
  char getNoEntryValue();
  
  int size();
  
  boolean isEmpty();
  
  boolean containsKey(Object paramObject);
  
  boolean containsValue(char paramChar);
  
  char get(Object paramObject);
  
  char put(K paramK, char paramChar);
  
  char putIfAbsent(K paramK, char paramChar);
  
  char remove(Object paramObject);
  
  void putAll(Map<? extends K, ? extends Character> paramMap);
  
  void putAll(TObjectCharMap<? extends K> paramTObjectCharMap);
  
  void clear();
  
  Set<K> keySet();
  
  Object[] keys();
  
  K[] keys(K[] paramArrayOfK);
  
  TCharCollection valueCollection();
  
  char[] values();
  
  char[] values(char[] paramArrayOfchar);
  
  TObjectCharIterator<K> iterator();
  
  boolean increment(K paramK);
  
  boolean adjustValue(K paramK, char paramChar);
  
  char adjustOrPutValue(K paramK, char paramChar1, char paramChar2);
  
  boolean forEachKey(TObjectProcedure<? super K> paramTObjectProcedure);
  
  boolean forEachValue(TCharProcedure paramTCharProcedure);
  
  boolean forEachEntry(TObjectCharProcedure<? super K> paramTObjectCharProcedure);
  
  void transformValues(TCharFunction paramTCharFunction);
  
  boolean retainEntries(TObjectCharProcedure<? super K> paramTObjectCharProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TObjectCharMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */