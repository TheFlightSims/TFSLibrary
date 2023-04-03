package gnu.trove.map;

import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.iterator.TDoubleShortIterator;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.procedure.TDoubleShortProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TDoubleSet;
import java.util.Map;

public interface TDoubleShortMap {
  double getNoEntryKey();
  
  short getNoEntryValue();
  
  short put(double paramDouble, short paramShort);
  
  short putIfAbsent(double paramDouble, short paramShort);
  
  void putAll(Map<? extends Double, ? extends Short> paramMap);
  
  void putAll(TDoubleShortMap paramTDoubleShortMap);
  
  short get(double paramDouble);
  
  void clear();
  
  boolean isEmpty();
  
  short remove(double paramDouble);
  
  int size();
  
  TDoubleSet keySet();
  
  double[] keys();
  
  double[] keys(double[] paramArrayOfdouble);
  
  TShortCollection valueCollection();
  
  short[] values();
  
  short[] values(short[] paramArrayOfshort);
  
  boolean containsValue(short paramShort);
  
  boolean containsKey(double paramDouble);
  
  TDoubleShortIterator iterator();
  
  boolean forEachKey(TDoubleProcedure paramTDoubleProcedure);
  
  boolean forEachValue(TShortProcedure paramTShortProcedure);
  
  boolean forEachEntry(TDoubleShortProcedure paramTDoubleShortProcedure);
  
  void transformValues(TShortFunction paramTShortFunction);
  
  boolean retainEntries(TDoubleShortProcedure paramTDoubleShortProcedure);
  
  boolean increment(double paramDouble);
  
  boolean adjustValue(double paramDouble, short paramShort);
  
  short adjustOrPutValue(double paramDouble, short paramShort1, short paramShort2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TDoubleShortMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */