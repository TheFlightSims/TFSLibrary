package gnu.trove.map;

import gnu.trove.TIntCollection;
import gnu.trove.function.TIntFunction;
import gnu.trove.iterator.TCharIntIterator;
import gnu.trove.procedure.TCharIntProcedure;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.set.TCharSet;
import java.util.Map;

public interface TCharIntMap {
  char getNoEntryKey();
  
  int getNoEntryValue();
  
  int put(char paramChar, int paramInt);
  
  int putIfAbsent(char paramChar, int paramInt);
  
  void putAll(Map<? extends Character, ? extends Integer> paramMap);
  
  void putAll(TCharIntMap paramTCharIntMap);
  
  int get(char paramChar);
  
  void clear();
  
  boolean isEmpty();
  
  int remove(char paramChar);
  
  int size();
  
  TCharSet keySet();
  
  char[] keys();
  
  char[] keys(char[] paramArrayOfchar);
  
  TIntCollection valueCollection();
  
  int[] values();
  
  int[] values(int[] paramArrayOfint);
  
  boolean containsValue(int paramInt);
  
  boolean containsKey(char paramChar);
  
  TCharIntIterator iterator();
  
  boolean forEachKey(TCharProcedure paramTCharProcedure);
  
  boolean forEachValue(TIntProcedure paramTIntProcedure);
  
  boolean forEachEntry(TCharIntProcedure paramTCharIntProcedure);
  
  void transformValues(TIntFunction paramTIntFunction);
  
  boolean retainEntries(TCharIntProcedure paramTCharIntProcedure);
  
  boolean increment(char paramChar);
  
  boolean adjustValue(char paramChar, int paramInt);
  
  int adjustOrPutValue(char paramChar, int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TCharIntMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */