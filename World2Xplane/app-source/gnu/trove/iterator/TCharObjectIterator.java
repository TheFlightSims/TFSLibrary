package gnu.trove.iterator;

public interface TCharObjectIterator<V> extends TAdvancingIterator {
  char key();
  
  V value();
  
  V setValue(V paramV);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\iterator\TCharObjectIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */