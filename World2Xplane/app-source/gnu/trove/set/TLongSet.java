package gnu.trove.set;

import gnu.trove.TLongCollection;
import gnu.trove.iterator.TLongIterator;
import gnu.trove.procedure.TLongProcedure;
import java.util.Collection;

public interface TLongSet extends TLongCollection {
  long getNoEntryValue();
  
  int size();
  
  boolean isEmpty();
  
  boolean contains(long paramLong);
  
  TLongIterator iterator();
  
  long[] toArray();
  
  long[] toArray(long[] paramArrayOflong);
  
  boolean add(long paramLong);
  
  boolean remove(long paramLong);
  
  boolean containsAll(Collection<?> paramCollection);
  
  boolean containsAll(TLongCollection paramTLongCollection);
  
  boolean containsAll(long[] paramArrayOflong);
  
  boolean addAll(Collection<? extends Long> paramCollection);
  
  boolean addAll(TLongCollection paramTLongCollection);
  
  boolean addAll(long[] paramArrayOflong);
  
  boolean retainAll(Collection<?> paramCollection);
  
  boolean retainAll(TLongCollection paramTLongCollection);
  
  boolean retainAll(long[] paramArrayOflong);
  
  boolean removeAll(Collection<?> paramCollection);
  
  boolean removeAll(TLongCollection paramTLongCollection);
  
  boolean removeAll(long[] paramArrayOflong);
  
  void clear();
  
  boolean forEach(TLongProcedure paramTLongProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\set\TLongSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */