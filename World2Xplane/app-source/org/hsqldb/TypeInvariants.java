package org.hsqldb;

import org.hsqldb.types.CharacterType;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;
import org.hsqldb.types.UserTypeModifier;

public class TypeInvariants {
  public static final Type CARDINAL_NUMBER = (Type)new NumberType(25, 0L, 0);
  
  public static final Type YES_OR_NO = (Type)new CharacterType(12, 3L);
  
  public static final Type CHARACTER_DATA = (Type)new CharacterType(12, 65536L);
  
  public static final Type SQL_IDENTIFIER = (Type)new CharacterType(12, 128L);
  
  public static final Type TIME_STAMP = (Type)new DateTimeType(93, 93, 6);
  
  static {
    TIME_STAMP.userTypeModifier = new UserTypeModifier(hsqlName, 13, TIME_STAMP);
  }
  
  static {
    HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName("CARDINAL_NUMBER", false, 13);
  }
  
  static {
    CARDINAL_NUMBER.userTypeModifier = new UserTypeModifier(hsqlName, 13, CARDINAL_NUMBER);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("YES_OR_NO", false, 13);
  }
  
  static {
    YES_OR_NO.userTypeModifier = new UserTypeModifier(hsqlName, 13, YES_OR_NO);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("CHARACTER_DATA", false, 13);
  }
  
  static {
    CHARACTER_DATA.userTypeModifier = new UserTypeModifier(hsqlName, 13, CHARACTER_DATA);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("SQL_IDENTIFIER", false, 13);
  }
  
  static {
    SQL_IDENTIFIER.userTypeModifier = new UserTypeModifier(hsqlName, 13, SQL_IDENTIFIER);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("TIME_STAMP", false, 13);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TypeInvariants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */