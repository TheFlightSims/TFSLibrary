package gnu.trove.map;

import gnu.trove.TDoubleCollection;
import gnu.trove.function.TDoubleFunction;
import gnu.trove.iterator.TIntDoubleIterator;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.procedure.TIntDoubleProcedure;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.set.TIntSet;
import java.util.Map;

public interface TIntDoubleMap {
  int getNoEntryKey();
  
  double getNoEntryValue();
  
  double put(int paramInt, double paramDouble);
  
  double putIfAbsent(int paramInt, double paramDouble);
  
  void putAll(Map<? extends Integer, ? extends Double> paramMap);
  
  void putAll(TIntDoubleMap paramTIntDoubleMap);
  
  double get(int paramInt);
  
  void clear();
  
  boolean isEmpty();
  
  double remove(int paramInt);
  
  int size();
  
  TIntSet keySet();
  
  int[] keys();
  
  int[] keys(int[] paramArrayOfint);
  
  TDoubleCollection valueCollection();
  
  double[] values();
  
  double[] values(double[] paramArrayOfdouble);
  
  boolean containsValue(double paramDouble);
  
  boolean containsKey(int paramInt);
  
  TIntDoubleIterator iterator();
  
  boolean forEachKey(TIntProcedure paramTIntProcedure);
  
  boolean forEachValue(TDoubleProcedure paramTDoubleProcedure);
  
  boolean forEachEntry(TIntDoubleProcedure paramTIntDoubleProcedure);
  
  void transformValues(TDoubleFunction paramTDoubleFunction);
  
  boolean retainEntries(TIntDoubleProcedure paramTIntDoubleProcedure);
  
  boolean increment(int paramInt);
  
  boolean adjustValue(int paramInt, double paramDouble);
  
  double adjustOrPutValue(int paramInt, double paramDouble1, double paramDouble2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TIntDoubleMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */