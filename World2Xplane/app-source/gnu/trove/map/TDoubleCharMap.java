package gnu.trove.map;

import gnu.trove.TCharCollection;
import gnu.trove.function.TCharFunction;
import gnu.trove.iterator.TDoubleCharIterator;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TDoubleCharProcedure;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.set.TDoubleSet;
import java.util.Map;

public interface TDoubleCharMap {
  double getNoEntryKey();
  
  char getNoEntryValue();
  
  char put(double paramDouble, char paramChar);
  
  char putIfAbsent(double paramDouble, char paramChar);
  
  void putAll(Map<? extends Double, ? extends Character> paramMap);
  
  void putAll(TDoubleCharMap paramTDoubleCharMap);
  
  char get(double paramDouble);
  
  void clear();
  
  boolean isEmpty();
  
  char remove(double paramDouble);
  
  int size();
  
  TDoubleSet keySet();
  
  double[] keys();
  
  double[] keys(double[] paramArrayOfdouble);
  
  TCharCollection valueCollection();
  
  char[] values();
  
  char[] values(char[] paramArrayOfchar);
  
  boolean containsValue(char paramChar);
  
  boolean containsKey(double paramDouble);
  
  TDoubleCharIterator iterator();
  
  boolean forEachKey(TDoubleProcedure paramTDoubleProcedure);
  
  boolean forEachValue(TCharProcedure paramTCharProcedure);
  
  boolean forEachEntry(TDoubleCharProcedure paramTDoubleCharProcedure);
  
  void transformValues(TCharFunction paramTCharFunction);
  
  boolean retainEntries(TDoubleCharProcedure paramTDoubleCharProcedure);
  
  boolean increment(double paramDouble);
  
  boolean adjustValue(double paramDouble, char paramChar);
  
  char adjustOrPutValue(double paramDouble, char paramChar1, char paramChar2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TDoubleCharMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */