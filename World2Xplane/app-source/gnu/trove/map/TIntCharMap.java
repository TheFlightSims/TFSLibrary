package gnu.trove.map;

import gnu.trove.TCharCollection;
import gnu.trove.function.TCharFunction;
import gnu.trove.iterator.TIntCharIterator;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TIntCharProcedure;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.set.TIntSet;
import java.util.Map;

public interface TIntCharMap {
  int getNoEntryKey();
  
  char getNoEntryValue();
  
  char put(int paramInt, char paramChar);
  
  char putIfAbsent(int paramInt, char paramChar);
  
  void putAll(Map<? extends Integer, ? extends Character> paramMap);
  
  void putAll(TIntCharMap paramTIntCharMap);
  
  char get(int paramInt);
  
  void clear();
  
  boolean isEmpty();
  
  char remove(int paramInt);
  
  int size();
  
  TIntSet keySet();
  
  int[] keys();
  
  int[] keys(int[] paramArrayOfint);
  
  TCharCollection valueCollection();
  
  char[] values();
  
  char[] values(char[] paramArrayOfchar);
  
  boolean containsValue(char paramChar);
  
  boolean containsKey(int paramInt);
  
  TIntCharIterator iterator();
  
  boolean forEachKey(TIntProcedure paramTIntProcedure);
  
  boolean forEachValue(TCharProcedure paramTCharProcedure);
  
  boolean forEachEntry(TIntCharProcedure paramTIntCharProcedure);
  
  void transformValues(TCharFunction paramTCharFunction);
  
  boolean retainEntries(TIntCharProcedure paramTIntCharProcedure);
  
  boolean increment(int paramInt);
  
  boolean adjustValue(int paramInt, char paramChar);
  
  char adjustOrPutValue(int paramInt, char paramChar1, char paramChar2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TIntCharMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */