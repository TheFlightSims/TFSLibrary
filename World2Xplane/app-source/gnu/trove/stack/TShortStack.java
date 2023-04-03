package gnu.trove.stack;

public interface TShortStack {
  short getNoEntryValue();
  
  void push(short paramShort);
  
  short pop();
  
  short peek();
  
  int size();
  
  void clear();
  
  short[] toArray();
  
  void toArray(short[] paramArrayOfshort);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\stack\TShortStack.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */