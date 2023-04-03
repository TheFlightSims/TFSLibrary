package gnu.trove.map;

import gnu.trove.TIntCollection;
import gnu.trove.function.TIntFunction;
import gnu.trove.iterator.TByteIntIterator;
import gnu.trove.procedure.TByteIntProcedure;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.set.TByteSet;
import java.util.Map;

public interface TByteIntMap {
  byte getNoEntryKey();
  
  int getNoEntryValue();
  
  int put(byte paramByte, int paramInt);
  
  int putIfAbsent(byte paramByte, int paramInt);
  
  void putAll(Map<? extends Byte, ? extends Integer> paramMap);
  
  void putAll(TByteIntMap paramTByteIntMap);
  
  int get(byte paramByte);
  
  void clear();
  
  boolean isEmpty();
  
  int remove(byte paramByte);
  
  int size();
  
  TByteSet keySet();
  
  byte[] keys();
  
  byte[] keys(byte[] paramArrayOfbyte);
  
  TIntCollection valueCollection();
  
  int[] values();
  
  int[] values(int[] paramArrayOfint);
  
  boolean containsValue(int paramInt);
  
  boolean containsKey(byte paramByte);
  
  TByteIntIterator iterator();
  
  boolean forEachKey(TByteProcedure paramTByteProcedure);
  
  boolean forEachValue(TIntProcedure paramTIntProcedure);
  
  boolean forEachEntry(TByteIntProcedure paramTByteIntProcedure);
  
  void transformValues(TIntFunction paramTIntFunction);
  
  boolean retainEntries(TByteIntProcedure paramTByteIntProcedure);
  
  boolean increment(byte paramByte);
  
  boolean adjustValue(byte paramByte, int paramInt);
  
  int adjustOrPutValue(byte paramByte, int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TByteIntMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */