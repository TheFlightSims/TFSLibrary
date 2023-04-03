package gnu.trove.list;

import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.procedure.TShortProcedure;
import java.util.Random;

public interface TShortList extends TShortCollection {
  short getNoEntryValue();
  
  int size();
  
  boolean isEmpty();
  
  boolean add(short paramShort);
  
  void add(short[] paramArrayOfshort);
  
  void add(short[] paramArrayOfshort, int paramInt1, int paramInt2);
  
  void insert(int paramInt, short paramShort);
  
  void insert(int paramInt, short[] paramArrayOfshort);
  
  void insert(int paramInt1, short[] paramArrayOfshort, int paramInt2, int paramInt3);
  
  short get(int paramInt);
  
  short set(int paramInt, short paramShort);
  
  void set(int paramInt, short[] paramArrayOfshort);
  
  void set(int paramInt1, short[] paramArrayOfshort, int paramInt2, int paramInt3);
  
  short replace(int paramInt, short paramShort);
  
  void clear();
  
  boolean remove(short paramShort);
  
  short removeAt(int paramInt);
  
  void remove(int paramInt1, int paramInt2);
  
  void transformValues(TShortFunction paramTShortFunction);
  
  void reverse();
  
  void reverse(int paramInt1, int paramInt2);
  
  void shuffle(Random paramRandom);
  
  TShortList subList(int paramInt1, int paramInt2);
  
  short[] toArray();
  
  short[] toArray(int paramInt1, int paramInt2);
  
  short[] toArray(short[] paramArrayOfshort);
  
  short[] toArray(short[] paramArrayOfshort, int paramInt1, int paramInt2);
  
  short[] toArray(short[] paramArrayOfshort, int paramInt1, int paramInt2, int paramInt3);
  
  boolean forEach(TShortProcedure paramTShortProcedure);
  
  boolean forEachDescending(TShortProcedure paramTShortProcedure);
  
  void sort();
  
  void sort(int paramInt1, int paramInt2);
  
  void fill(short paramShort);
  
  void fill(int paramInt1, int paramInt2, short paramShort);
  
  int binarySearch(short paramShort);
  
  int binarySearch(short paramShort, int paramInt1, int paramInt2);
  
  int indexOf(short paramShort);
  
  int indexOf(int paramInt, short paramShort);
  
  int lastIndexOf(short paramShort);
  
  int lastIndexOf(int paramInt, short paramShort);
  
  boolean contains(short paramShort);
  
  TShortList grep(TShortProcedure paramTShortProcedure);
  
  TShortList inverseGrep(TShortProcedure paramTShortProcedure);
  
  short max();
  
  short min();
  
  short sum();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\list\TShortList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */