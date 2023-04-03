package gnu.trove.map;

import gnu.trove.TByteCollection;
import gnu.trove.function.TByteFunction;
import gnu.trove.iterator.TObjectByteIterator;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TObjectByteProcedure;
import gnu.trove.procedure.TObjectProcedure;
import java.util.Map;
import java.util.Set;

public interface TObjectByteMap<K> {
  byte getNoEntryValue();
  
  int size();
  
  boolean isEmpty();
  
  boolean containsKey(Object paramObject);
  
  boolean containsValue(byte paramByte);
  
  byte get(Object paramObject);
  
  byte put(K paramK, byte paramByte);
  
  byte putIfAbsent(K paramK, byte paramByte);
  
  byte remove(Object paramObject);
  
  void putAll(Map<? extends K, ? extends Byte> paramMap);
  
  void putAll(TObjectByteMap<? extends K> paramTObjectByteMap);
  
  void clear();
  
  Set<K> keySet();
  
  Object[] keys();
  
  K[] keys(K[] paramArrayOfK);
  
  TByteCollection valueCollection();
  
  byte[] values();
  
  byte[] values(byte[] paramArrayOfbyte);
  
  TObjectByteIterator<K> iterator();
  
  boolean increment(K paramK);
  
  boolean adjustValue(K paramK, byte paramByte);
  
  byte adjustOrPutValue(K paramK, byte paramByte1, byte paramByte2);
  
  boolean forEachKey(TObjectProcedure<? super K> paramTObjectProcedure);
  
  boolean forEachValue(TByteProcedure paramTByteProcedure);
  
  boolean forEachEntry(TObjectByteProcedure<? super K> paramTObjectByteProcedure);
  
  void transformValues(TByteFunction paramTByteFunction);
  
  boolean retainEntries(TObjectByteProcedure<? super K> paramTObjectByteProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TObjectByteMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */