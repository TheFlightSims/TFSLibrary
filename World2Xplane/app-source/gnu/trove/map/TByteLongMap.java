package gnu.trove.map;

import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.iterator.TByteLongIterator;
import gnu.trove.procedure.TByteLongProcedure;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TByteSet;
import java.util.Map;

public interface TByteLongMap {
  byte getNoEntryKey();
  
  long getNoEntryValue();
  
  long put(byte paramByte, long paramLong);
  
  long putIfAbsent(byte paramByte, long paramLong);
  
  void putAll(Map<? extends Byte, ? extends Long> paramMap);
  
  void putAll(TByteLongMap paramTByteLongMap);
  
  long get(byte paramByte);
  
  void clear();
  
  boolean isEmpty();
  
  long remove(byte paramByte);
  
  int size();
  
  TByteSet keySet();
  
  byte[] keys();
  
  byte[] keys(byte[] paramArrayOfbyte);
  
  TLongCollection valueCollection();
  
  long[] values();
  
  long[] values(long[] paramArrayOflong);
  
  boolean containsValue(long paramLong);
  
  boolean containsKey(byte paramByte);
  
  TByteLongIterator iterator();
  
  boolean forEachKey(TByteProcedure paramTByteProcedure);
  
  boolean forEachValue(TLongProcedure paramTLongProcedure);
  
  boolean forEachEntry(TByteLongProcedure paramTByteLongProcedure);
  
  void transformValues(TLongFunction paramTLongFunction);
  
  boolean retainEntries(TByteLongProcedure paramTByteLongProcedure);
  
  boolean increment(byte paramByte);
  
  boolean adjustValue(byte paramByte, long paramLong);
  
  long adjustOrPutValue(byte paramByte, long paramLong1, long paramLong2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TByteLongMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */