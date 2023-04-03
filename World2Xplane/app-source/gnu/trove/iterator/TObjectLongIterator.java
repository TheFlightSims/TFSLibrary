package gnu.trove.iterator;

public interface TObjectLongIterator<K> extends TAdvancingIterator {
  K key();
  
  long value();
  
  long setValue(long paramLong);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\iterator\TObjectLongIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */