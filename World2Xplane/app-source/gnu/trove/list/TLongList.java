package gnu.trove.list;

import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.procedure.TLongProcedure;
import java.util.Random;

public interface TLongList extends TLongCollection {
  long getNoEntryValue();
  
  int size();
  
  boolean isEmpty();
  
  boolean add(long paramLong);
  
  void add(long[] paramArrayOflong);
  
  void add(long[] paramArrayOflong, int paramInt1, int paramInt2);
  
  void insert(int paramInt, long paramLong);
  
  void insert(int paramInt, long[] paramArrayOflong);
  
  void insert(int paramInt1, long[] paramArrayOflong, int paramInt2, int paramInt3);
  
  long get(int paramInt);
  
  long set(int paramInt, long paramLong);
  
  void set(int paramInt, long[] paramArrayOflong);
  
  void set(int paramInt1, long[] paramArrayOflong, int paramInt2, int paramInt3);
  
  long replace(int paramInt, long paramLong);
  
  void clear();
  
  boolean remove(long paramLong);
  
  long removeAt(int paramInt);
  
  void remove(int paramInt1, int paramInt2);
  
  void transformValues(TLongFunction paramTLongFunction);
  
  void reverse();
  
  void reverse(int paramInt1, int paramInt2);
  
  void shuffle(Random paramRandom);
  
  TLongList subList(int paramInt1, int paramInt2);
  
  long[] toArray();
  
  long[] toArray(int paramInt1, int paramInt2);
  
  long[] toArray(long[] paramArrayOflong);
  
  long[] toArray(long[] paramArrayOflong, int paramInt1, int paramInt2);
  
  long[] toArray(long[] paramArrayOflong, int paramInt1, int paramInt2, int paramInt3);
  
  boolean forEach(TLongProcedure paramTLongProcedure);
  
  boolean forEachDescending(TLongProcedure paramTLongProcedure);
  
  void sort();
  
  void sort(int paramInt1, int paramInt2);
  
  void fill(long paramLong);
  
  void fill(int paramInt1, int paramInt2, long paramLong);
  
  int binarySearch(long paramLong);
  
  int binarySearch(long paramLong, int paramInt1, int paramInt2);
  
  int indexOf(long paramLong);
  
  int indexOf(int paramInt, long paramLong);
  
  int lastIndexOf(long paramLong);
  
  int lastIndexOf(int paramInt, long paramLong);
  
  boolean contains(long paramLong);
  
  TLongList grep(TLongProcedure paramTLongProcedure);
  
  TLongList inverseGrep(TLongProcedure paramTLongProcedure);
  
  long max();
  
  long min();
  
  long sum();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\list\TLongList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */