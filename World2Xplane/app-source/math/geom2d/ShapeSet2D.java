package math.geom2d;

public interface ShapeSet2D<T extends Shape2D> extends Shape2D, Iterable<T> {
  boolean add(T paramT);
  
  void add(int paramInt, T paramT);
  
  T get(int paramInt);
  
  boolean remove(T paramT);
  
  T remove(int paramInt);
  
  boolean contains(T paramT);
  
  int indexOf(T paramT);
  
  int size();
  
  void clear();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\ShapeSet2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */