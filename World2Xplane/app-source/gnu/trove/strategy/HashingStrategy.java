package gnu.trove.strategy;

import java.io.Serializable;

public interface HashingStrategy<T> extends Serializable {
  public static final long serialVersionUID = 5674097166776615540L;
  
  int computeHashCode(T paramT);
  
  boolean equals(T paramT1, T paramT2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\strategy\HashingStrategy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */