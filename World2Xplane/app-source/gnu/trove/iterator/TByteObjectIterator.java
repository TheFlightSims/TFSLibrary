package gnu.trove.iterator;

public interface TByteObjectIterator<V> extends TAdvancingIterator {
  byte key();
  
  V value();
  
  V setValue(V paramV);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\iterator\TByteObjectIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */