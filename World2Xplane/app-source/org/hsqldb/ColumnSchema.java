package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.rights.Grantee;
import org.hsqldb.types.Type;
import org.hsqldb.types.Types;

public final class ColumnSchema extends ColumnBase implements SchemaObject {
  public static final ColumnSchema[] emptyArray = new ColumnSchema[0];
  
  private HsqlNameManager.HsqlName columnName;
  
  private boolean isPrimaryKey;
  
  private Expression defaultExpression;
  
  private Expression generatingExpression;
  
  private NumberSequence sequence;
  
  private OrderedHashSet references;
  
  private OrderedHashSet generatedColumnReferences;
  
  private Expression accessor;
  
  public ColumnSchema(HsqlNameManager.HsqlName paramHsqlName, Type paramType, boolean paramBoolean1, boolean paramBoolean2, Expression paramExpression) {
    this.columnName = paramHsqlName;
    this.nullability = paramBoolean1 ? 1 : 0;
    this.dataType = paramType;
    this.isPrimaryKey = paramBoolean2;
    this.defaultExpression = paramExpression;
    setReferences();
  }
  
  public int getType() {
    return this.columnName.type;
  }
  
  public HsqlNameManager.HsqlName getName() {
    return this.columnName;
  }
  
  public String getNameString() {
    return this.columnName.name;
  }
  
  public String getTableNameString() {
    return (this.columnName.parent == null) ? null : this.columnName.parent.name;
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return this.columnName.schema;
  }
  
  public String getSchemaNameString() {
    return (this.columnName.schema == null) ? null : this.columnName.schema.name;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return (this.columnName.schema == null) ? null : this.columnName.schema.schema;
  }
  
  public String getCatalogNameString() {
    return (this.columnName.schema == null) ? null : ((this.columnName.schema.schema == null) ? null : this.columnName.schema.schema.name);
  }
  
  public Grantee getOwner() {
    return (this.columnName.schema == null) ? null : this.columnName.schema.owner;
  }
  
  public OrderedHashSet getReferences() {
    return this.references;
  }
  
  public OrderedHashSet getComponents() {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {
    if (this.generatingExpression == null)
      return; 
    this.generatingExpression.resetColumnReferences();
    this.generatingExpression.resolveCheckOrGenExpression(paramSession, new RangeGroup.RangeGroupSimple(((Table)paramSchemaObject).getDefaultRanges(), false), false);
    if (this.dataType.typeComparisonGroup != (this.generatingExpression.getDataType()).typeComparisonGroup)
      throw Error.error(5561); 
    setReferences();
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    switch (this.parameterMode) {
      case 1:
        stringBuffer.append("IN").append(' ');
        break;
      case 4:
        stringBuffer.append("OUT").append(' ');
        break;
      case 2:
        stringBuffer.append("INOUT").append(' ');
        break;
    } 
    if (this.columnName != null) {
      stringBuffer.append(this.columnName.statementName);
      stringBuffer.append(' ');
    } 
    stringBuffer.append(this.dataType.getTypeDefinition());
    return stringBuffer.toString();
  }
  
  public long getChangeTimestamp() {
    return 0L;
  }
  
  public void setType(Type paramType) {
    this.dataType = paramType;
    setReferences();
  }
  
  public void setName(HsqlNameManager.HsqlName paramHsqlName) {
    this.columnName = paramHsqlName;
  }
  
  void setIdentity(NumberSequence paramNumberSequence) {
    this.sequence = paramNumberSequence;
    this.isIdentity = (paramNumberSequence != null);
  }
  
  void setType(ColumnSchema paramColumnSchema) {
    this.nullability = paramColumnSchema.nullability;
    this.dataType = paramColumnSchema.dataType;
  }
  
  public NumberSequence getIdentitySequence() {
    return this.sequence;
  }
  
  public boolean isNullable() {
    boolean bool = super.isNullable();
    return (bool && this.dataType.isDomainType()) ? this.dataType.userTypeModifier.isNullable() : bool;
  }
  
  public byte getNullability() {
    return this.isPrimaryKey ? 0 : super.getNullability();
  }
  
  public boolean isGenerated() {
    return (this.generatingExpression != null);
  }
  
  public boolean hasDefault() {
    return (getDefaultExpression() != null);
  }
  
  public boolean isWriteable() {
    return !isGenerated();
  }
  
  public void setWriteable(boolean paramBoolean) {
    throw Error.runtimeError(201, "ColumnSchema");
  }
  
  public boolean isSearchable() {
    return Types.isSearchable(this.dataType.typeCode);
  }
  
  public boolean isPrimaryKey() {
    return this.isPrimaryKey;
  }
  
  void setPrimaryKey(boolean paramBoolean) {
    this.isPrimaryKey = paramBoolean;
  }
  
  public Object getDefaultValue(Session paramSession) {
    return (this.defaultExpression == null) ? null : this.defaultExpression.getValue(paramSession, this.dataType);
  }
  
  public Object getGeneratedValue(Session paramSession) {
    return (this.generatingExpression == null) ? null : this.generatingExpression.getValue(paramSession, this.dataType);
  }
  
  public String getDefaultSQL() {
    null = null;
    return (this.defaultExpression == null) ? null : this.defaultExpression.getSQL();
  }
  
  Expression getDefaultExpression() {
    return (this.defaultExpression == null) ? (this.dataType.isDomainType() ? this.dataType.userTypeModifier.getDefaultClause() : null) : this.defaultExpression;
  }
  
  void setDefaultExpression(Expression paramExpression) {
    this.defaultExpression = paramExpression;
  }
  
  public Expression getGeneratingExpression() {
    return this.generatingExpression;
  }
  
  void setGeneratingExpression(Expression paramExpression) {
    this.generatingExpression = paramExpression;
  }
  
  public ColumnSchema duplicate() {
    ColumnSchema columnSchema = new ColumnSchema(this.columnName, this.dataType, true, this.isPrimaryKey, this.defaultExpression);
    columnSchema.setNullability(this.nullability);
    columnSchema.setGeneratingExpression(this.generatingExpression);
    columnSchema.setIdentity(this.sequence);
    return columnSchema;
  }
  
  public Expression getAccessor() {
    if (this.accessor == null)
      this.accessor = new ExpressionColumnAccessor(this); 
    return this.accessor;
  }
  
  public OrderedHashSet getGeneratedColumnReferences() {
    return this.generatedColumnReferences;
  }
  
  private void setReferences() {
    if (this.references != null)
      this.references.clear(); 
    if (this.generatedColumnReferences != null)
      this.generatedColumnReferences.clear(); 
    if (this.dataType.isDomainType() || this.dataType.isDistinctType()) {
      HsqlNameManager.HsqlName hsqlName = this.dataType.getName();
      if (this.references == null)
        this.references = new OrderedHashSet(); 
      this.references.add(hsqlName);
    } 
    if (this.generatingExpression != null) {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      this.generatingExpression.collectObjectNames((Set)orderedHashSet);
      Iterator iterator = orderedHashSet.iterator();
      while (iterator.hasNext()) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)iterator.next();
        if (hsqlName.type == 9 || hsqlName.type == 3) {
          if (hsqlName.type == 9) {
            if (this.generatedColumnReferences == null)
              this.generatedColumnReferences = new OrderedHashSet(); 
            this.generatedColumnReferences.add(hsqlName);
          } 
          continue;
        } 
        if (this.references == null)
          this.references = new OrderedHashSet(); 
        this.references.add(hsqlName);
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ColumnSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */