package gnu.trove.map;

import gnu.trove.TDoubleCollection;
import gnu.trove.function.TDoubleFunction;
import gnu.trove.iterator.TFloatDoubleIterator;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.procedure.TFloatDoubleProcedure;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.set.TFloatSet;
import java.util.Map;

public interface TFloatDoubleMap {
  float getNoEntryKey();
  
  double getNoEntryValue();
  
  double put(float paramFloat, double paramDouble);
  
  double putIfAbsent(float paramFloat, double paramDouble);
  
  void putAll(Map<? extends Float, ? extends Double> paramMap);
  
  void putAll(TFloatDoubleMap paramTFloatDoubleMap);
  
  double get(float paramFloat);
  
  void clear();
  
  boolean isEmpty();
  
  double remove(float paramFloat);
  
  int size();
  
  TFloatSet keySet();
  
  float[] keys();
  
  float[] keys(float[] paramArrayOffloat);
  
  TDoubleCollection valueCollection();
  
  double[] values();
  
  double[] values(double[] paramArrayOfdouble);
  
  boolean containsValue(double paramDouble);
  
  boolean containsKey(float paramFloat);
  
  TFloatDoubleIterator iterator();
  
  boolean forEachKey(TFloatProcedure paramTFloatProcedure);
  
  boolean forEachValue(TDoubleProcedure paramTDoubleProcedure);
  
  boolean forEachEntry(TFloatDoubleProcedure paramTFloatDoubleProcedure);
  
  void transformValues(TDoubleFunction paramTDoubleFunction);
  
  boolean retainEntries(TFloatDoubleProcedure paramTFloatDoubleProcedure);
  
  boolean increment(float paramFloat);
  
  boolean adjustValue(float paramFloat, double paramDouble);
  
  double adjustOrPutValue(float paramFloat, double paramDouble1, double paramDouble2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TFloatDoubleMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */