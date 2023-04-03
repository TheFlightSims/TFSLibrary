package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.Type;

public final class ExpressionLike extends ExpressionLogical {
  private static final int ESCAPE = 2;
  
  private Like likeObject;
  
  ExpressionLike(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, boolean paramBoolean) {
    super(53);
    this.nodes[0] = paramExpression1;
    this.nodes[1] = paramExpression2;
    this.nodes[2] = paramExpression3;
    this.likeObject = new Like();
    this.noOptimisation = paramBoolean;
  }
  
  private ExpressionLike(ExpressionLike paramExpressionLike) {
    super(53);
    this.likeObject = paramExpressionLike.likeObject;
  }
  
  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        paramHsqlList = this.nodes[b].resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, paramHsqlList, paramBoolean); 
    } 
    return paramHsqlList;
  }
  
  public Object getValue(Session paramSession) {
    if (this.opType != 53)
      return super.getValue(paramSession); 
    Object object1 = this.nodes[0].getValue(paramSession);
    Object object2 = this.nodes[1].getValue(paramSession);
    Object object3 = (this.nodes[2] == null) ? null : this.nodes[2].getValue(paramSession);
    if (this.likeObject.isVariable)
      synchronized (this.likeObject) {
        this.likeObject.setPattern(paramSession, object2, object3, (this.nodes[2] != null));
        return this.likeObject.compare(paramSession, object1);
      }  
    return this.likeObject.compare(paramSession, object1);
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    if (this.opType != 53)
      return; 
    int i;
    for (i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null)
        this.nodes[i].resolveTypes(paramSession, this); 
    } 
    if (this.nodes[0].isUnresolvedParam() && this.nodes[1].isUnresolvedParam())
      (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
    if ((this.nodes[0]).dataType == null && (this.nodes[1]).dataType == null)
      throw Error.error(5567); 
    if (this.nodes[0].isUnresolvedParam()) {
      (this.nodes[0]).dataType = (this.nodes[1]).dataType.isBinaryType() ? (Type)Type.SQL_VARBINARY_DEFAULT : (Type)Type.SQL_VARCHAR_DEFAULT;
    } else if (this.nodes[1].isUnresolvedParam()) {
      (this.nodes[1]).dataType = (this.nodes[0]).dataType.isBinaryType() ? (Type)Type.SQL_VARBINARY_DEFAULT : (Type)Type.SQL_VARCHAR_DEFAULT;
    } 
    if ((this.nodes[0]).dataType == null || (this.nodes[1]).dataType == null)
      throw Error.error(5567); 
    i = (this.nodes[0]).dataType.typeComparisonGroup;
    if (i != 12)
      if (i == 61) {
        this.likeObject.isBinary = true;
      } else {
        if (paramSession.database.sqlEnforceTypes)
          throw Error.error(5562); 
        if (i == 1111)
          throw Error.error(5563); 
        this.nodes[0] = ExpressionOp.getCastExpression(paramSession, this.nodes[0], (Type)Type.SQL_VARCHAR_DEFAULT);
        i = 12;
      }  
    if ((this.nodes[1]).dataType.typeComparisonGroup != i || (this.nodes[2] != null && (this.nodes[2]).dataType.typeComparisonGroup != i))
      throw Error.error(5563); 
    if (i == 12) {
      boolean bool = (!(this.nodes[0]).dataType.getCollation().isCaseSensitive() || !(this.nodes[1]).dataType.getCollation().isCaseSensitive()) ? true : false;
      this.likeObject.setIgnoreCase(bool);
    } 
    this.likeObject.dataType = (this.nodes[0]).dataType;
    boolean bool1 = true;
    if (this.nodes[2] != null) {
      if (this.nodes[2].isUnresolvedParam())
        (this.nodes[2]).dataType = this.likeObject.isBinary ? (Type)Type.SQL_VARBINARY : (Type)Type.SQL_VARCHAR; 
      this.nodes[2].resolveTypes(paramSession, this);
      bool1 = ((this.nodes[2]).opType == 1) ? true : false;
      if (bool1) {
        this.nodes[2].setAsConstantValue(paramSession, paramExpression);
        if ((this.nodes[2]).dataType == null)
          throw Error.error(5567); 
        if ((this.nodes[2]).valueData != null) {
          long l;
          switch ((this.nodes[2]).dataType.typeCode) {
            case 1:
            case 12:
              l = ((String)(this.nodes[2]).valueData).length();
              break;
            case 60:
            case 61:
              l = ((BinaryData)(this.nodes[2]).valueData).length(paramSession);
              break;
            default:
              throw Error.error(5563);
          } 
          if (l != 1L)
            throw Error.error(3439); 
        } 
      } 
    } 
    boolean bool2 = ((this.nodes[1]).opType == 1) ? true : false;
    if (bool2 && bool1) {
      if ((this.nodes[0]).opType == 1) {
        setAsConstantValue(paramSession, paramExpression);
        this.likeObject = null;
        return;
      } 
      this.likeObject.isVariable = false;
    } 
    Object object1 = bool2 ? this.nodes[1].getValue(paramSession) : null;
    boolean bool3 = (bool1 && this.nodes[2] != null) ? true : false;
    Object object2 = bool3 ? this.nodes[2].getValue(paramSession) : null;
    this.likeObject.setPattern(paramSession, object1, object2, (this.nodes[2] != null));
    if (this.noOptimisation)
      return; 
    if (this.likeObject.isEquivalentToUnknownPredicate()) {
      setAsConstantValue(paramSession, paramExpression);
      this.likeObject = null;
      return;
    } 
    if (this.likeObject.isEquivalentToEqualsPredicate()) {
      this.opType = 40;
      this.nodes[1] = new ExpressionValue(this.likeObject.getRangeLow(), (Type)Type.SQL_VARCHAR);
      this.likeObject = null;
      setEqualityMode();
      return;
    } 
    if (this.likeObject.isEquivalentToNotNullPredicate()) {
      ExpressionLogical expressionLogical = new ExpressionLogical(47, this.nodes[0]);
      this.opType = 48;
      this.nodes = new Expression[1];
      this.nodes[0] = expressionLogical;
      this.likeObject = null;
      return;
    } 
    if ((this.nodes[0]).opType == 2) {
      ExpressionLike expressionLike = new ExpressionLike(this);
      ExpressionOp expressionOp1 = new ExpressionOp(37, this.nodes[1], this.nodes[2]);
      expressionOp1.resolveTypes(paramSession, null);
      ExpressionOp expressionOp2 = new ExpressionOp(84, this.nodes[0], expressionOp1);
      ExpressionLogical expressionLogical = new ExpressionLogical(40, expressionOp2, expressionOp1);
      expressionLogical = new ExpressionLogical(42, this.nodes[0], expressionOp1, expressionLogical);
      this.nodes = new Expression[2];
      this.likeObject = null;
      this.nodes[0] = expressionLogical;
      this.nodes[1] = expressionLike;
      this.opType = 49;
    } 
  }
  
  public String getSQL() {
    if (this.likeObject == null)
      return super.getSQL(); 
    String str1 = getContextSQL(this.nodes[0]);
    String str2 = getContextSQL(this.nodes[1]);
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(str1).append(' ').append("LIKE").append(' ');
    stringBuffer.append(str2);
    if (this.nodes[2] != null) {
      stringBuffer.append(' ').append("ESCAPE").append(' ');
      stringBuffer.append(this.nodes[2].getSQL());
      stringBuffer.append(' ');
    } 
    return stringBuffer.toString();
  }
  
  protected String describe(Session paramSession, int paramInt) {
    if (this.likeObject == null)
      return super.describe(paramSession, paramInt); 
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append('\n');
    for (byte b = 0; b < paramInt; b++)
      stringBuffer.append(' '); 
    stringBuffer.append("LIKE ");
    stringBuffer.append(this.likeObject.describe(paramSession));
    return stringBuffer.toString();
  }
  
  public Expression duplicate() {
    ExpressionLike expressionLike = (ExpressionLike)super.duplicate();
    if (this.likeObject != null)
      expressionLike.likeObject = this.likeObject.duplicate(); 
    return expressionLike;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ExpressionLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */