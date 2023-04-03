package gnu.trove.map;

import gnu.trove.TByteCollection;
import gnu.trove.function.TByteFunction;
import gnu.trove.iterator.TDoubleByteIterator;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TDoubleByteProcedure;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.set.TDoubleSet;
import java.util.Map;

public interface TDoubleByteMap {
  double getNoEntryKey();
  
  byte getNoEntryValue();
  
  byte put(double paramDouble, byte paramByte);
  
  byte putIfAbsent(double paramDouble, byte paramByte);
  
  void putAll(Map<? extends Double, ? extends Byte> paramMap);
  
  void putAll(TDoubleByteMap paramTDoubleByteMap);
  
  byte get(double paramDouble);
  
  void clear();
  
  boolean isEmpty();
  
  byte remove(double paramDouble);
  
  int size();
  
  TDoubleSet keySet();
  
  double[] keys();
  
  double[] keys(double[] paramArrayOfdouble);
  
  TByteCollection valueCollection();
  
  byte[] values();
  
  byte[] values(byte[] paramArrayOfbyte);
  
  boolean containsValue(byte paramByte);
  
  boolean containsKey(double paramDouble);
  
  TDoubleByteIterator iterator();
  
  boolean forEachKey(TDoubleProcedure paramTDoubleProcedure);
  
  boolean forEachValue(TByteProcedure paramTByteProcedure);
  
  boolean forEachEntry(TDoubleByteProcedure paramTDoubleByteProcedure);
  
  void transformValues(TByteFunction paramTByteFunction);
  
  boolean retainEntries(TDoubleByteProcedure paramTDoubleByteProcedure);
  
  boolean increment(double paramDouble);
  
  boolean adjustValue(double paramDouble, byte paramByte);
  
  byte adjustOrPutValue(double paramDouble, byte paramByte1, byte paramByte2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TDoubleByteMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */