package gnu.trove.map;

import gnu.trove.TByteCollection;
import gnu.trove.function.TByteFunction;
import gnu.trove.iterator.TLongByteIterator;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TLongByteProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TLongSet;
import java.util.Map;

public interface TLongByteMap {
  long getNoEntryKey();
  
  byte getNoEntryValue();
  
  byte put(long paramLong, byte paramByte);
  
  byte putIfAbsent(long paramLong, byte paramByte);
  
  void putAll(Map<? extends Long, ? extends Byte> paramMap);
  
  void putAll(TLongByteMap paramTLongByteMap);
  
  byte get(long paramLong);
  
  void clear();
  
  boolean isEmpty();
  
  byte remove(long paramLong);
  
  int size();
  
  TLongSet keySet();
  
  long[] keys();
  
  long[] keys(long[] paramArrayOflong);
  
  TByteCollection valueCollection();
  
  byte[] values();
  
  byte[] values(byte[] paramArrayOfbyte);
  
  boolean containsValue(byte paramByte);
  
  boolean containsKey(long paramLong);
  
  TLongByteIterator iterator();
  
  boolean forEachKey(TLongProcedure paramTLongProcedure);
  
  boolean forEachValue(TByteProcedure paramTByteProcedure);
  
  boolean forEachEntry(TLongByteProcedure paramTLongByteProcedure);
  
  void transformValues(TByteFunction paramTByteFunction);
  
  boolean retainEntries(TLongByteProcedure paramTLongByteProcedure);
  
  boolean increment(long paramLong);
  
  boolean adjustValue(long paramLong, byte paramByte);
  
  byte adjustOrPutValue(long paramLong, byte paramByte1, byte paramByte2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TLongByteMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */