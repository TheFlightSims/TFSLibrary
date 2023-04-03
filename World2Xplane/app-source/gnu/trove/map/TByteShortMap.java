package gnu.trove.map;

import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.iterator.TByteShortIterator;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TByteShortProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TByteSet;
import java.util.Map;

public interface TByteShortMap {
  byte getNoEntryKey();
  
  short getNoEntryValue();
  
  short put(byte paramByte, short paramShort);
  
  short putIfAbsent(byte paramByte, short paramShort);
  
  void putAll(Map<? extends Byte, ? extends Short> paramMap);
  
  void putAll(TByteShortMap paramTByteShortMap);
  
  short get(byte paramByte);
  
  void clear();
  
  boolean isEmpty();
  
  short remove(byte paramByte);
  
  int size();
  
  TByteSet keySet();
  
  byte[] keys();
  
  byte[] keys(byte[] paramArrayOfbyte);
  
  TShortCollection valueCollection();
  
  short[] values();
  
  short[] values(short[] paramArrayOfshort);
  
  boolean containsValue(short paramShort);
  
  boolean containsKey(byte paramByte);
  
  TByteShortIterator iterator();
  
  boolean forEachKey(TByteProcedure paramTByteProcedure);
  
  boolean forEachValue(TShortProcedure paramTShortProcedure);
  
  boolean forEachEntry(TByteShortProcedure paramTByteShortProcedure);
  
  void transformValues(TShortFunction paramTShortFunction);
  
  boolean retainEntries(TByteShortProcedure paramTByteShortProcedure);
  
  boolean increment(byte paramByte);
  
  boolean adjustValue(byte paramByte, short paramShort);
  
  short adjustOrPutValue(byte paramByte, short paramShort1, short paramShort2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TByteShortMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */