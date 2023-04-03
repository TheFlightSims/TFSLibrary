package org.hsqldb.types;

import org.hsqldb.Constraint;
import org.hsqldb.Expression;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.Session;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.rights.Grantee;

public class UserTypeModifier {
  final HsqlNameManager.HsqlName name;
  
  final int schemaObjectType;
  
  final Type dataType;
  
  Constraint[] constraints = Constraint.emptyArray;
  
  Expression defaultExpression;
  
  boolean isNullable = true;
  
  public UserTypeModifier(HsqlNameManager.HsqlName paramHsqlName, int paramInt, Type paramType) {
    this.name = paramHsqlName;
    this.schemaObjectType = paramInt;
    this.dataType = paramType;
  }
  
  public int schemaObjectType() {
    return this.schemaObjectType;
  }
  
  public void addConstraint(Constraint paramConstraint) {
    int i = this.constraints.length;
    this.constraints = (Constraint[])ArrayUtil.resizeArray(this.constraints, i + 1);
    this.constraints[i] = paramConstraint;
    setNotNull();
  }
  
  public void removeConstraint(String paramString) {
    for (byte b = 0; b < this.constraints.length; b++) {
      if ((this.constraints[b].getName()).name.equals(paramString)) {
        this.constraints = (Constraint[])ArrayUtil.toAdjustedArray(this.constraints, null, b, -1);
        break;
      } 
    } 
    setNotNull();
  }
  
  public Constraint getConstraint(String paramString) {
    for (byte b = 0; b < this.constraints.length; b++) {
      if ((this.constraints[b].getName()).name.equals(paramString))
        return this.constraints[b]; 
    } 
    return null;
  }
  
  public Constraint[] getConstraints() {
    return this.constraints;
  }
  
  public boolean isNullable() {
    return this.isNullable;
  }
  
  public Expression getDefaultClause() {
    return this.defaultExpression;
  }
  
  public void setDefaultClause(Expression paramExpression) {
    this.defaultExpression = paramExpression;
  }
  
  public void removeDefaultClause() {
    this.defaultExpression = null;
  }
  
  private void setNotNull() {
    this.isNullable = true;
    for (byte b = 0; b < this.constraints.length; b++) {
      if (this.constraints[b].isNotNull())
        this.isNullable = false; 
    } 
  }
  
  public int getType() {
    return this.schemaObjectType;
  }
  
  public HsqlNameManager.HsqlName getName() {
    return this.name;
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return this.name.schema;
  }
  
  public Grantee getOwner() {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    for (byte b = 0; b < this.constraints.length; b++) {
      OrderedHashSet orderedHashSet1 = this.constraints[b].getReferences();
      if (orderedHashSet1 != null)
        orderedHashSet.addAll((Collection)orderedHashSet1); 
    } 
    return orderedHashSet;
  }
  
  public final OrderedHashSet getComponents() {
    if (this.constraints == null)
      return null; 
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    orderedHashSet.addAll((Object[])this.constraints);
    return orderedHashSet;
  }
  
  public void compile(Session paramSession) {
    for (byte b = 0; b < this.constraints.length; b++)
      this.constraints[b].compile(paramSession, null); 
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    if (this.schemaObjectType == 12) {
      stringBuffer.append("CREATE").append(' ').append("TYPE").append(' ');
      stringBuffer.append(this.name.getSchemaQualifiedStatementName());
      stringBuffer.append(' ').append("AS").append(' ');
      stringBuffer.append(this.dataType.getDefinition());
      if (this.dataType.isCharacterType()) {
        Collation collation = this.dataType.getCollation();
        if (collation.isObjectCollation())
          stringBuffer.append(' ').append(collation.getCollateSQL()); 
      } 
    } else {
      stringBuffer.append("CREATE").append(' ').append("DOMAIN").append(' ');
      stringBuffer.append(this.name.getSchemaQualifiedStatementName());
      stringBuffer.append(' ').append("AS").append(' ');
      stringBuffer.append(this.dataType.getDefinition());
      if (this.defaultExpression != null) {
        stringBuffer.append(' ').append("DEFAULT").append(' ');
        stringBuffer.append(this.defaultExpression.getSQL());
      } 
      for (byte b = 0; b < this.constraints.length; b++) {
        stringBuffer.append(' ').append("CONSTRAINT").append(' ');
        stringBuffer.append((this.constraints[b].getName()).statementName).append(' ');
        stringBuffer.append("CHECK").append('(').append(this.constraints[b].getCheckSQL()).append(')');
      } 
    } 
    return stringBuffer.toString();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\UserTypeModifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */