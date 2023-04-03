package gnu.trove;

import gnu.trove.iterator.TShortIterator;
import gnu.trove.procedure.TShortProcedure;
import java.util.Collection;

public interface TShortCollection {
  public static final long serialVersionUID = 1L;
  
  short getNoEntryValue();
  
  int size();
  
  boolean isEmpty();
  
  boolean contains(short paramShort);
  
  TShortIterator iterator();
  
  short[] toArray();
  
  short[] toArray(short[] paramArrayOfshort);
  
  boolean add(short paramShort);
  
  boolean remove(short paramShort);
  
  boolean containsAll(Collection<?> paramCollection);
  
  boolean containsAll(TShortCollection paramTShortCollection);
  
  boolean containsAll(short[] paramArrayOfshort);
  
  boolean addAll(Collection<? extends Short> paramCollection);
  
  boolean addAll(TShortCollection paramTShortCollection);
  
  boolean addAll(short[] paramArrayOfshort);
  
  boolean retainAll(Collection<?> paramCollection);
  
  boolean retainAll(TShortCollection paramTShortCollection);
  
  boolean retainAll(short[] paramArrayOfshort);
  
  boolean removeAll(Collection<?> paramCollection);
  
  boolean removeAll(TShortCollection paramTShortCollection);
  
  boolean removeAll(short[] paramArrayOfshort);
  
  void clear();
  
  boolean forEach(TShortProcedure paramTShortProcedure);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\TShortCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */