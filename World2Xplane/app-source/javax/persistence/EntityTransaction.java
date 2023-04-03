package javax.persistence;

public interface EntityTransaction {
  void begin();
  
  void commit();
  
  void rollback();
  
  void setRollbackOnly();
  
  boolean getRollbackOnly();
  
  boolean isActive();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\persistence\EntityTransaction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */