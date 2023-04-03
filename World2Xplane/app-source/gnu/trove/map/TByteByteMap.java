package gnu.trove.map;

import gnu.trove.TByteCollection;
import gnu.trove.function.TByteFunction;
import gnu.trove.iterator.TByteByteIterator;
import gnu.trove.procedure.TByteByteProcedure;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.set.TByteSet;
import java.util.Map;

public interface TByteByteMap {
  byte getNoEntryKey();
  
  byte getNoEntryValue();
  
  byte put(byte paramByte1, byte paramByte2);
  
  byte putIfAbsent(byte paramByte1, byte paramByte2);
  
  void putAll(Map<? extends Byte, ? extends Byte> paramMap);
  
  void putAll(TByteByteMap paramTByteByteMap);
  
  byte get(byte paramByte);
  
  void clear();
  
  boolean isEmpty();
  
  byte remove(byte paramByte);
  
  int size();
  
  TByteSet keySet();
  
  byte[] keys();
  
  byte[] keys(byte[] paramArrayOfbyte);
  
  TByteCollection valueCollection();
  
  byte[] values();
  
  byte[] values(byte[] paramArrayOfbyte);
  
  boolean containsValue(byte paramByte);
  
  boolean containsKey(byte paramByte);
  
  TByteByteIterator iterator();
  
  boolean forEachKey(TByteProcedure paramTByteProcedure);
  
  boolean forEachValue(TByteProcedure paramTByteProcedure);
  
  boolean forEachEntry(TByteByteProcedure paramTByteByteProcedure);
  
  void transformValues(TByteFunction paramTByteFunction);
  
  boolean retainEntries(TByteByteProcedure paramTByteByteProcedure);
  
  boolean increment(byte paramByte);
  
  boolean adjustValue(byte paramByte1, byte paramByte2);
  
  byte adjustOrPutValue(byte paramByte1, byte paramByte2, byte paramByte3);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TByteByteMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */