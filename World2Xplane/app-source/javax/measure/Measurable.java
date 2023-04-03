package javax.measure;

import javax.measure.unit.Unit;

public interface Measurable<Q extends javax.measure.quantity.Quantity> extends Comparable<Measurable<Q>> {
  double doubleValue(Unit<Q> paramUnit);
  
  long longValue(Unit<Q> paramUnit) throws ArithmeticException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\Measurable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */