package gnu.trove.iterator;

public interface TShortObjectIterator<V> extends TAdvancingIterator {
  short key();
  
  V value();
  
  V setValue(V paramV);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\iterator\TShortObjectIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */