package gnu.trove.map;

import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.iterator.TCharShortIterator;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TCharShortProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TCharSet;
import java.util.Map;

public interface TCharShortMap {
  char getNoEntryKey();
  
  short getNoEntryValue();
  
  short put(char paramChar, short paramShort);
  
  short putIfAbsent(char paramChar, short paramShort);
  
  void putAll(Map<? extends Character, ? extends Short> paramMap);
  
  void putAll(TCharShortMap paramTCharShortMap);
  
  short get(char paramChar);
  
  void clear();
  
  boolean isEmpty();
  
  short remove(char paramChar);
  
  int size();
  
  TCharSet keySet();
  
  char[] keys();
  
  char[] keys(char[] paramArrayOfchar);
  
  TShortCollection valueCollection();
  
  short[] values();
  
  short[] values(short[] paramArrayOfshort);
  
  boolean containsValue(short paramShort);
  
  boolean containsKey(char paramChar);
  
  TCharShortIterator iterator();
  
  boolean forEachKey(TCharProcedure paramTCharProcedure);
  
  boolean forEachValue(TShortProcedure paramTShortProcedure);
  
  boolean forEachEntry(TCharShortProcedure paramTCharShortProcedure);
  
  void transformValues(TShortFunction paramTShortFunction);
  
  boolean retainEntries(TCharShortProcedure paramTCharShortProcedure);
  
  boolean increment(char paramChar);
  
  boolean adjustValue(char paramChar, short paramShort);
  
  short adjustOrPutValue(char paramChar, short paramShort1, short paramShort2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\TCharShortMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */