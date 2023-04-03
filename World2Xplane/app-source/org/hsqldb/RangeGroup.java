package org.hsqldb;

public interface RangeGroup {
  public static final RangeGroup emptyGroup = new RangeGroupEmpty();
  
  public static final RangeGroup[] emptyArray = new RangeGroup[] { emptyGroup };
  
  RangeVariable[] getRangeVariables();
  
  void setCorrelated();
  
  boolean isVariable();
  
  public static class RangeGroupEmpty implements RangeGroup {
    public RangeVariable[] getRangeVariables() {
      return RangeVariable.emptyArray;
    }
    
    public void setCorrelated() {}
    
    public boolean isVariable() {
      return false;
    }
  }
  
  public static class RangeGroupSimple implements RangeGroup {
    RangeVariable[] ranges = RangeVariable.emptyArray;
    
    RangeGroup baseGroup;
    
    TableDerived table;
    
    boolean isVariable;
    
    public RangeGroupSimple(TableDerived param1TableDerived) {
      this.table = param1TableDerived;
    }
    
    public RangeGroupSimple(RangeVariable[] param1ArrayOfRangeVariable, RangeGroup param1RangeGroup) {
      this.baseGroup = param1RangeGroup;
    }
    
    public RangeGroupSimple(RangeVariable[] param1ArrayOfRangeVariable, boolean param1Boolean) {
      this.isVariable = param1Boolean;
    }
    
    public RangeVariable[] getRangeVariables() {
      return this.ranges;
    }
    
    public void setCorrelated() {
      if (this.baseGroup != null)
        this.baseGroup.setCorrelated(); 
      if (this.table != null)
        this.table.setCorrelated(); 
    }
    
    public boolean isVariable() {
      return this.isVariable;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\RangeGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */