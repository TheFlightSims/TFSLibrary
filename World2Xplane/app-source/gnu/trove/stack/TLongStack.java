package gnu.trove.stack;

public interface TLongStack {
  long getNoEntryValue();
  
  void push(long paramLong);
  
  long pop();
  
  long peek();
  
  int size();
  
  void clear();
  
  long[] toArray();
  
  void toArray(long[] paramArrayOflong);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\stack\TLongStack.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */