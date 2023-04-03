package gnu.trove.map;

import gnu.trove.TDoubleCollection;
import gnu.trove.function.TDoubleFunction;
import gnu.trove.iterator.TCharDoubleIterator;
import gnu.trove.procedure.TCharDoubleProcedure;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.set.TCharSet;
import java.util.Map;

public interface TCharDoubleMap {
  char getNoEntryKey();
  
  double getNoEntryValue();
  
  double put(char paramChar, double paramDouble);
  
  double putIfAbsent(char paramChar, double paramDouble);
  
  void putAll(Map<? extends Character, ? extends Double> paramMap);
  
  void putAll(TCharDoubleMap paramTCharDoubleMap);
  
  double get(char paramChar);
  
  void clear();
  
  boolean isEmpty();
  
  double remove(char paramChar);
  
  int size();
  
  TCharSet keySet();
  
  char[] keys();
  
  char[] keys(char[] paramArrayOfchar);
  
  TDoubleCollection valueCollection();
  
  double[] values();
  
  double[] values(double[] paramArrayOfdouble);
  
  boolean containsValue(double paramDouble);
  
  boolean containsKey(char paramChar);
  
  TCharDoubleIterator iterator();
  
  boolean forEachKey(TCharProcedure paramTCharProcedure);
  
  boolean forEachValue(TDoubleProcedure paramTDoubleProcedure);
  
  boolean forEachEntry(TCharDoubleProcedure paramTCharDoubleProcedure);
  
  void transformValues(TDoubleFunction paramTDoubleFunction);
  
  boolean retainEntries(TCharDoubleProcedure paramTCharDoubleProcedure);
  
  boolean increment(char paramChar);
  
  boolean adjustValue(char paramChar, double paramDouble);
  
  double adjustOrPutValue(char paramChar, double paramDouble1, double paramDouble2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TCharDoubleMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */