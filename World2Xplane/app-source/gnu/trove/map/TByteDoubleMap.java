package gnu.trove.map;

import gnu.trove.TDoubleCollection;
import gnu.trove.function.TDoubleFunction;
import gnu.trove.iterator.TByteDoubleIterator;
import gnu.trove.procedure.TByteDoubleProcedure;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.set.TByteSet;
import java.util.Map;

public interface TByteDoubleMap {
  byte getNoEntryKey();
  
  double getNoEntryValue();
  
  double put(byte paramByte, double paramDouble);
  
  double putIfAbsent(byte paramByte, double paramDouble);
  
  void putAll(Map<? extends Byte, ? extends Double> paramMap);
  
  void putAll(TByteDoubleMap paramTByteDoubleMap);
  
  double get(byte paramByte);
  
  void clear();
  
  boolean isEmpty();
  
  double remove(byte paramByte);
  
  int size();
  
  TByteSet keySet();
  
  byte[] keys();
  
  byte[] keys(byte[] paramArrayOfbyte);
  
  TDoubleCollection valueCollection();
  
  double[] values();
  
  double[] values(double[] paramArrayOfdouble);
  
  boolean containsValue(double paramDouble);
  
  boolean containsKey(byte paramByte);
  
  TByteDoubleIterator iterator();
  
  boolean forEachKey(TByteProcedure paramTByteProcedure);
  
  boolean forEachValue(TDoubleProcedure paramTDoubleProcedure);
  
  boolean forEachEntry(TByteDoubleProcedure paramTByteDoubleProcedure);
  
  void transformValues(TDoubleFunction paramTDoubleFunction);
  
  boolean retainEntries(TByteDoubleProcedure paramTByteDoubleProcedure);
  
  boolean increment(byte paramByte);
  
  boolean adjustValue(byte paramByte, double paramDouble);
  
  double adjustOrPutValue(byte paramByte, double paramDouble1, double paramDouble2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TByteDoubleMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */