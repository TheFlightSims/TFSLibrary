package gnu.trove.map;

import gnu.trove.TCharCollection;
import gnu.trove.function.TCharFunction;
import gnu.trove.iterator.TCharCharIterator;
import gnu.trove.procedure.TCharCharProcedure;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.set.TCharSet;
import java.util.Map;

public interface TCharCharMap {
  char getNoEntryKey();
  
  char getNoEntryValue();
  
  char put(char paramChar1, char paramChar2);
  
  char putIfAbsent(char paramChar1, char paramChar2);
  
  void putAll(Map<? extends Character, ? extends Character> paramMap);
  
  void putAll(TCharCharMap paramTCharCharMap);
  
  char get(char paramChar);
  
  void clear();
  
  boolean isEmpty();
  
  char remove(char paramChar);
  
  int size();
  
  TCharSet keySet();
  
  char[] keys();
  
  char[] keys(char[] paramArrayOfchar);
  
  TCharCollection valueCollection();
  
  char[] values();
  
  char[] values(char[] paramArrayOfchar);
  
  boolean containsValue(char paramChar);
  
  boolean containsKey(char paramChar);
  
  TCharCharIterator iterator();
  
  boolean forEachKey(TCharProcedure paramTCharProcedure);
  
  boolean forEachValue(TCharProcedure paramTCharProcedure);
  
  boolean forEachEntry(TCharCharProcedure paramTCharCharProcedure);
  
  void transformValues(TCharFunction paramTCharFunction);
  
  boolean retainEntries(TCharCharProcedure paramTCharCharProcedure);
  
  boolean increment(char paramChar);
  
  boolean adjustValue(char paramChar1, char paramChar2);
  
  char adjustOrPutValue(char paramChar1, char paramChar2, char paramChar3);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TCharCharMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */