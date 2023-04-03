package org.hsqldb;

import org.hsqldb.index.Index;

class ConstraintCore {
  HsqlNameManager.HsqlName refName;
  
  HsqlNameManager.HsqlName mainName;
  
  HsqlNameManager.HsqlName uniqueName;
  
  HsqlNameManager.HsqlName refTableName;
  
  HsqlNameManager.HsqlName mainTableName;
  
  Table mainTable;
  
  int[] mainCols;
  
  Index mainIndex;
  
  Table refTable;
  
  int[] refCols;
  
  Index refIndex;
  
  int deleteAction;
  
  int updateAction;
  
  boolean hasUpdateAction;
  
  boolean hasDeleteAction;
  
  int matchType;
  
  ConstraintCore duplicate() {
    ConstraintCore constraintCore = new ConstraintCore();
    constraintCore.refName = this.refName;
    constraintCore.mainName = this.mainName;
    constraintCore.uniqueName = this.uniqueName;
    constraintCore.mainTable = this.mainTable;
    constraintCore.mainCols = this.mainCols;
    constraintCore.mainIndex = this.mainIndex;
    constraintCore.refTable = this.refTable;
    constraintCore.refCols = this.refCols;
    constraintCore.refIndex = this.refIndex;
    constraintCore.deleteAction = this.deleteAction;
    constraintCore.updateAction = this.updateAction;
    constraintCore.matchType = this.matchType;
    return constraintCore;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ConstraintCore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */