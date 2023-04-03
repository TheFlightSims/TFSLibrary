package gnu.trove.map;

import gnu.trove.TDoubleCollection;
import gnu.trove.function.TDoubleFunction;
import gnu.trove.iterator.TLongDoubleIterator;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.procedure.TLongDoubleProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TLongSet;
import java.util.Map;

public interface TLongDoubleMap {
  long getNoEntryKey();
  
  double getNoEntryValue();
  
  double put(long paramLong, double paramDouble);
  
  double putIfAbsent(long paramLong, double paramDouble);
  
  void putAll(Map<? extends Long, ? extends Double> paramMap);
  
  void putAll(TLongDoubleMap paramTLongDoubleMap);
  
  double get(long paramLong);
  
  void clear();
  
  boolean isEmpty();
  
  double remove(long paramLong);
  
  int size();
  
  TLongSet keySet();
  
  long[] keys();
  
  long[] keys(long[] paramArrayOflong);
  
  TDoubleCollection valueCollection();
  
  double[] values();
  
  double[] values(double[] paramArrayOfdouble);
  
  boolean containsValue(double paramDouble);
  
  boolean containsKey(long paramLong);
  
  TLongDoubleIterator iterator();
  
  boolean forEachKey(TLongProcedure paramTLongProcedure);
  
  boolean forEachValue(TDoubleProcedure paramTDoubleProcedure);
  
  boolean forEachEntry(TLongDoubleProcedure paramTLongDoubleProcedure);
  
  void transformValues(TDoubleFunction paramTDoubleFunction);
  
  boolean retainEntries(TLongDoubleProcedure paramTLongDoubleProcedure);
  
  boolean increment(long paramLong);
  
  boolean adjustValue(long paramLong, double paramDouble);
  
  double adjustOrPutValue(long paramLong, double paramDouble1, double paramDouble2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TLongDoubleMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */