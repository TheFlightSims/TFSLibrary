package gnu.trove;

import gnu.trove.iterator.TCharIterator;
import gnu.trove.procedure.TCharProcedure;
import java.util.Collection;

public interface TCharCollection {
  public static final long serialVersionUID = 1L;
  
  char getNoEntryValue();
  
  int size();
  
  boolean isEmpty();
  
  boolean contains(char paramChar);
  
  TCharIterator iterator();
  
  char[] toArray();
  
  char[] toArray(char[] paramArrayOfchar);
  
  boolean add(char paramChar);
  
  boolean remove(char paramChar);
  
  boolean containsAll(Collection<?> paramCollection);
  
  boolean containsAll(TCharCollection paramTCharCollection);
  
  boolean containsAll(char[] paramArrayOfchar);
  
  boolean addAll(Collection<? extends Character> paramCollection);
  
  boolean addAll(TCharCollection paramTCharCollection);
  
  boolean addAll(char[] paramArrayOfchar);
  
  boolean retainAll(Collection<?> paramCollection);
  
  boolean retainAll(TCharCollection paramTCharCollection);
  
  boolean retainAll(char[] paramArrayOfchar);
  
  boolean removeAll(Collection<?> paramCollection);
  
  boolean removeAll(TCharCollection paramTCharCollection);
  
  boolean removeAll(char[] paramArrayOfchar);
  
  void clear();
  
  boolean forEach(TCharProcedure paramTCharProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\TCharCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */