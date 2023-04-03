package org.hsqldb;

public interface Trigger {
  public static final int INSERT_AFTER = 0;
  
  public static final int DELETE_AFTER = 1;
  
  public static final int UPDATE_AFTER = 2;
  
  public static final int INSERT_AFTER_ROW = 3;
  
  public static final int DELETE_AFTER_ROW = 4;
  
  public static final int UPDATE_AFTER_ROW = 5;
  
  public static final int INSERT_BEFORE_ROW = 6;
  
  public static final int DELETE_BEFORE_ROW = 7;
  
  public static final int UPDATE_BEFORE_ROW = 8;
  
  void fire(int paramInt, String paramString1, String paramString2, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) throws HsqlException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\Trigger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */