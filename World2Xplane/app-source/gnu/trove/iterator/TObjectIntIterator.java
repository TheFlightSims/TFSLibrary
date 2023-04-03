package gnu.trove.iterator;

public interface TObjectIntIterator<K> extends TAdvancingIterator {
  K key();
  
  int value();
  
  int setValue(int paramInt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\iterator\TObjectIntIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */