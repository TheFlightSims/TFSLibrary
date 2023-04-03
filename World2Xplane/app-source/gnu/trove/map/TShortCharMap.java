package gnu.trove.map;

import gnu.trove.TCharCollection;
import gnu.trove.function.TCharFunction;
import gnu.trove.iterator.TShortCharIterator;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TShortCharProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TShortSet;
import java.util.Map;

public interface TShortCharMap {
  short getNoEntryKey();
  
  char getNoEntryValue();
  
  char put(short paramShort, char paramChar);
  
  char putIfAbsent(short paramShort, char paramChar);
  
  void putAll(Map<? extends Short, ? extends Character> paramMap);
  
  void putAll(TShortCharMap paramTShortCharMap);
  
  char get(short paramShort);
  
  void clear();
  
  boolean isEmpty();
  
  char remove(short paramShort);
  
  int size();
  
  TShortSet keySet();
  
  short[] keys();
  
  short[] keys(short[] paramArrayOfshort);
  
  TCharCollection valueCollection();
  
  char[] values();
  
  char[] values(char[] paramArrayOfchar);
  
  boolean containsValue(char paramChar);
  
  boolean containsKey(short paramShort);
  
  TShortCharIterator iterator();
  
  boolean forEachKey(TShortProcedure paramTShortProcedure);
  
  boolean forEachValue(TCharProcedure paramTCharProcedure);
  
  boolean forEachEntry(TShortCharProcedure paramTShortCharProcedure);
  
  void transformValues(TCharFunction paramTCharFunction);
  
  boolean retainEntries(TShortCharProcedure paramTShortCharProcedure);
  
  boolean increment(short paramShort);
  
  boolean adjustValue(short paramShort, char paramChar);
  
  char adjustOrPutValue(short paramShort, char paramChar1, char paramChar2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TShortCharMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */