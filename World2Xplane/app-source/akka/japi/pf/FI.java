package akka.japi.pf;

public final class FI {
  static interface Predicate {
    boolean defined(Object param1Object);
  }
  
  public static interface UnitApplyVoid {
    void apply() throws Exception;
  }
  
  public static interface UnitApply3<I1, I2, I3> {
    void apply(I1 param1I1, I2 param1I2, I3 param1I3) throws Exception;
  }
  
  public static interface UnitApply2<I1, I2> {
    void apply(I1 param1I1, I2 param1I2) throws Exception;
  }
  
  public static interface UnitApply<I> {
    void apply(I param1I) throws Exception;
  }
  
  public static interface TypedPredicate2<T, U> {
    boolean defined(T param1T, U param1U);
  }
  
  public static interface TypedPredicate<T> {
    boolean defined(T param1T);
  }
  
  public static interface Apply2<I1, I2, R> {
    R apply(I1 param1I1, I2 param1I2) throws Exception;
  }
  
  public static interface Apply<I, R> {
    R apply(I param1I) throws Exception;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\FI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */