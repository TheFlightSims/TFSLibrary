package gnu.trove.list;

import gnu.trove.TCharCollection;
import gnu.trove.function.TCharFunction;
import gnu.trove.procedure.TCharProcedure;
import java.util.Random;

public interface TCharList extends TCharCollection {
  char getNoEntryValue();
  
  int size();
  
  boolean isEmpty();
  
  boolean add(char paramChar);
  
  void add(char[] paramArrayOfchar);
  
  void add(char[] paramArrayOfchar, int paramInt1, int paramInt2);
  
  void insert(int paramInt, char paramChar);
  
  void insert(int paramInt, char[] paramArrayOfchar);
  
  void insert(int paramInt1, char[] paramArrayOfchar, int paramInt2, int paramInt3);
  
  char get(int paramInt);
  
  char set(int paramInt, char paramChar);
  
  void set(int paramInt, char[] paramArrayOfchar);
  
  void set(int paramInt1, char[] paramArrayOfchar, int paramInt2, int paramInt3);
  
  char replace(int paramInt, char paramChar);
  
  void clear();
  
  boolean remove(char paramChar);
  
  char removeAt(int paramInt);
  
  void remove(int paramInt1, int paramInt2);
  
  void transformValues(TCharFunction paramTCharFunction);
  
  void reverse();
  
  void reverse(int paramInt1, int paramInt2);
  
  void shuffle(Random paramRandom);
  
  TCharList subList(int paramInt1, int paramInt2);
  
  char[] toArray();
  
  char[] toArray(int paramInt1, int paramInt2);
  
  char[] toArray(char[] paramArrayOfchar);
  
  char[] toArray(char[] paramArrayOfchar, int paramInt1, int paramInt2);
  
  char[] toArray(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3);
  
  boolean forEach(TCharProcedure paramTCharProcedure);
  
  boolean forEachDescending(TCharProcedure paramTCharProcedure);
  
  void sort();
  
  void sort(int paramInt1, int paramInt2);
  
  void fill(char paramChar);
  
  void fill(int paramInt1, int paramInt2, char paramChar);
  
  int binarySearch(char paramChar);
  
  int binarySearch(char paramChar, int paramInt1, int paramInt2);
  
  int indexOf(char paramChar);
  
  int indexOf(int paramInt, char paramChar);
  
  int lastIndexOf(char paramChar);
  
  int lastIndexOf(int paramInt, char paramChar);
  
  boolean contains(char paramChar);
  
  TCharList grep(TCharProcedure paramTCharProcedure);
  
  TCharList inverseGrep(TCharProcedure paramTCharProcedure);
  
  char max();
  
  char min();
  
  char sum();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\list\TCharList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */