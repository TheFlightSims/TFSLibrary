package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.map.ValuePool;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.result.Result;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.GranteeManager;
import org.hsqldb.rights.Right;
import org.hsqldb.rights.User;
import org.hsqldb.types.Charset;
import org.hsqldb.types.Collation;
import org.hsqldb.types.Type;

public class StatementSchema extends Statement {
  int order;
  
  Object[] arguments = ValuePool.emptyObjectArray;
  
  boolean isSchemaDefinition;
  
  Token[] statementTokens;
  
  StatementSchema(int paramInt1, int paramInt2) {
    super(paramInt1, paramInt2);
  }
  
  StatementSchema(String paramString, int paramInt) {
    this(paramString, paramInt, (Object[])null, (HsqlNameManager.HsqlName[])null, (HsqlNameManager.HsqlName[])null);
  }
  
  StatementSchema(String paramString, int paramInt, Object[] paramArrayOfObject, HsqlNameManager.HsqlName[] paramArrayOfHsqlName1, HsqlNameManager.HsqlName[] paramArrayOfHsqlName2) {
    super(paramInt);
    this.sql = paramString;
    if (paramArrayOfObject != null)
      this.arguments = paramArrayOfObject; 
    if (paramArrayOfHsqlName1 != null)
      this.readTableNames = paramArrayOfHsqlName1; 
    if (paramArrayOfHsqlName2 != null)
      this.writeTableNames = paramArrayOfHsqlName2; 
    switch (paramInt) {
      case 1192:
        this.group = 2002;
        return;
      case 3:
      case 4:
      case 17:
      case 60:
      case 127:
      case 134:
      case 1069:
      case 1070:
        this.group = 2002;
        return;
      case 24:
      case 25:
      case 26:
      case 27:
      case 29:
      case 30:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case 78:
      case 115:
      case 116:
      case 135:
      case 1076:
      case 1077:
      case 1078:
      case 1079:
        this.group = 2002;
        return;
      case 48:
        this.group = 2002;
        this.order = 10;
        return;
      case 49:
        this.group = 2002;
        this.order = 10;
        return;
      case 59:
      case 129:
        this.group = 2002;
        return;
      case 64:
        this.group = 2001;
        return;
      case 61:
        this.group = 2001;
        this.order = 1;
        return;
      case 14:
        this.group = 2001;
        this.order = 7;
        return;
      case 133:
        this.group = 2001;
        this.order = 1;
        return;
      case 77:
        this.group = 2001;
        this.order = 2;
        return;
      case 117:
        this.group = 2001;
        this.order = 1;
        return;
      case 79:
        this.group = 2001;
        this.order = 1;
        return;
      case 80:
        this.group = 2001;
        this.order = 7;
        return;
      case 52:
        this.group = 2001;
        this.order = 2;
        return;
      case 83:
        this.group = 2001;
        this.order = 1;
        return;
      case 114:
        this.group = 2001;
        this.order = 1;
        return;
      case 84:
        this.group = 2001;
        this.order = 5;
        return;
      case 1074:
        this.group = 2001;
        this.order = 1;
        return;
      case 6:
        this.group = 2001;
        this.order = 9;
        return;
      case 8:
        this.group = 2001;
        this.order = 1;
        return;
      case 10:
        this.group = 2001;
        this.order = 1;
        return;
      case 23:
        this.group = 2001;
        this.order = 1;
        return;
      case 1072:
        this.group = 2001;
        this.order = 8;
        return;
      case 1073:
        this.group = 2002;
        this.order = 4;
        return;
      case 1071:
        this.group = 2002;
        this.order = 11;
        return;
      case 1214:
        this.group = 2002;
        this.statementTokens = (Token[])paramArrayOfObject[0];
        return;
      case 1201:
        this.group = 2002;
        return;
    } 
    throw Error.runtimeError(201, "StatemntSchema");
  }
  
  public Result execute(Session paramSession) {
    Result result;
    try {
      result = getResult(paramSession);
    } catch (Throwable throwable) {
      result = Result.newErrorResult(throwable, null);
    } 
    if (result.isError()) {
      result.getException().setStatementType(this.group, this.type);
      return result;
    } 
    paramSession.database.schemaManager.setSchemaChangeTimestamp();
    try {
      if (this.isLogged)
        paramSession.database.logger.writeOtherStatement(paramSession, this.sql); 
    } catch (Throwable throwable) {
      return Result.newErrorResult(throwable, this.sql);
    } 
    return result;
  }
  
  Result getResult(Session paramSession) {
    HsqlNameManager.HsqlName hsqlName4;
    Table table3;
    Routine routine2;
    View view2;
    Charset charset;
    Collation collation;
    HsqlNameManager.HsqlName hsqlName3;
    Routine routine1;
    HsqlNameManager.HsqlName hsqlName2;
    NumberSequence numberSequence;
    Type type2;
    Table table2;
    TriggerDef triggerDef;
    Type type1;
    View view1;
    Table table1;
    HsqlNameManager.HsqlName hsqlName1;
    HsqlNameManager.HsqlName hsqlName6;
    int[] arrayOfInt1;
    String str2;
    Grantee grantee1;
    Routine[] arrayOfRoutine;
    Boolean bool1;
    Constraint[] arrayOfConstraint;
    HsqlArrayList hsqlArrayList1;
    HsqlNameManager.HsqlName hsqlName5;
    String str1;
    HsqlNameManager.HsqlName hsqlName7;
    Grantee grantee2;
    StatementDMQL statementDMQL;
    int[] arrayOfInt2;
    Table table5;
    RoutineSchema routineSchema;
    Table table4;
    boolean bool4;
    Boolean bool3;
    boolean bool2;
    int i;
    boolean bool5;
    HsqlArrayList hsqlArrayList2;
    ColumnSchema columnSchema;
    SchemaManager schemaManager = paramSession.database.schemaManager;
    if (this.isExplain)
      return Result.newSingleColumnStringResult("OPERATION", describe(paramSession)); 
    switch (this.type) {
      case 1192:
        hsqlName4 = (HsqlNameManager.HsqlName)this.arguments[0];
        hsqlName6 = (HsqlNameManager.HsqlName)this.arguments[1];
        if (hsqlName4.type == 1) {
          try {
            paramSession.checkAdmin();
            paramSession.checkDDLWrite();
            hsqlName4.rename(hsqlName6);
          } catch (HsqlException hsqlException) {
            return Result.newErrorResult(hsqlException, this.sql);
          } 
        } else if (hsqlName4.type == 2) {
          checkSchemaUpdateAuthorisation(paramSession, hsqlName4);
          schemaManager.checkSchemaNameCanChange(hsqlName4);
          schemaManager.renameSchema(hsqlName4, hsqlName6);
        } else {
          try {
            SchemaObject schemaObject;
            HsqlNameManager.HsqlName hsqlName;
            Table table;
            hsqlName4.setSchemaIfNull(paramSession.getCurrentSchemaHsqlName());
            if (hsqlName4.type == 9) {
              Table table6 = schemaManager.getUserTable(paramSession, hsqlName4.parent);
              int j = table6.getColumnIndex(hsqlName4.name);
              schemaObject = table6.getColumn(j);
            } else {
              schemaObject = schemaManager.getSchemaObject(hsqlName4);
              if (schemaObject == null)
                throw Error.error(5501, hsqlName4.name); 
              hsqlName4 = schemaObject.getName();
            } 
            checkSchemaUpdateAuthorisation(paramSession, hsqlName4.schema);
            hsqlName6.setSchemaIfNull(hsqlName4.schema);
            if (hsqlName4.schema != hsqlName6.schema) {
              HsqlException hsqlException = Error.error(5505);
              return Result.newErrorResult(hsqlException, this.sql);
            } 
            hsqlName6.parent = hsqlName4.parent;
            switch (schemaObject.getType()) {
              case 9:
                hsqlName = (schemaObject.getName()).parent;
                schemaManager.checkColumnIsReferenced(hsqlName, schemaObject.getName());
                table = schemaManager.getUserTable(paramSession, hsqlName);
                table.renameColumn((ColumnSchema)schemaObject, hsqlName6);
            } 
            schemaManager.renameSchemaObject(hsqlName4, hsqlName6);
          } catch (HsqlException hsqlException) {
            return Result.newErrorResult(hsqlException, this.sql);
          } 
        } 
      case 1069:
        table3 = (Table)this.arguments[0];
        arrayOfInt1 = (int[])this.arguments[1];
        hsqlName7 = (HsqlNameManager.HsqlName)this.arguments[2];
        try {
          Index index = (Index)paramSession.database.schemaManager.getSchemaObject(hsqlName7);
          TableWorks tableWorks = new TableWorks(paramSession, table3);
          tableWorks.alterIndex(index, arrayOfInt1);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 134:
        try {
          NumberSequence numberSequence1 = (NumberSequence)this.arguments[0];
          NumberSequence numberSequence2 = (NumberSequence)this.arguments[1];
          checkSchemaUpdateAuthorisation(paramSession, numberSequence1.getSchemaName());
          numberSequence1.reset(numberSequence2);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 3:
        try {
          Constraint constraint;
          Expression expression;
          HsqlNameManager.HsqlName hsqlName;
          int j = ((Integer)this.arguments[0]).intValue();
          Type type = (Type)this.arguments[1];
          switch (j) {
            case 1082:
              constraint = (Constraint)this.arguments[2];
              paramSession.database.schemaManager.checkSchemaObjectNotExists(constraint.getName());
              type.userTypeModifier.addConstraint(constraint);
              paramSession.database.schemaManager.addSchemaObject(constraint);
              break;
            case 1083:
              expression = (Expression)this.arguments[2];
              type.userTypeModifier.setDefaultClause(expression);
              break;
            case 1078:
              hsqlName = (HsqlNameManager.HsqlName)this.arguments[2];
              paramSession.database.schemaManager.removeSchemaObject(hsqlName);
              break;
            case 1080:
              type.userTypeModifier.removeDefaultClause();
              break;
          } 
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 4:
        try {
          Constraint constraint;
          ColumnSchema columnSchema1;
          TableWorks tableWorks1;
          int n;
          Type type;
          int m;
          boolean bool;
          int k;
          HsqlArrayList hsqlArrayList;
          ColumnSchema columnSchema2;
          NumberSequence numberSequence1;
          TableWorks tableWorks3;
          Expression expression;
          TableWorks tableWorks2;
          TableWorks tableWorks5;
          ColumnSchema columnSchema3;
          TableWorks tableWorks4;
          TableWorks tableWorks6;
          int j = ((Integer)this.arguments[0]).intValue();
          Table table = (Table)this.arguments[1];
          switch (j) {
            case 1082:
              constraint = (Constraint)this.arguments[2];
              switch (constraint.getConstraintType()) {
                case 4:
                  tableWorks1 = new TableWorks(paramSession, table);
                  tableWorks1.addPrimaryKey(constraint);
                  break;
                case 2:
                  tableWorks1 = new TableWorks(paramSession, table);
                  tableWorks1.addUniqueConstraint(constraint);
                  break;
                case 0:
                  tableWorks1 = new TableWorks(paramSession, table);
                  tableWorks1.addForeignKey(constraint);
                  break;
                case 3:
                  tableWorks1 = new TableWorks(paramSession, table);
                  tableWorks1.addCheckConstraint(constraint);
                  break;
              } 
              break;
            case 1081:
              columnSchema1 = (ColumnSchema)this.arguments[2];
              n = ((Integer)this.arguments[3]).intValue();
              hsqlArrayList = (HsqlArrayList)this.arguments[4];
              tableWorks5 = new TableWorks(paramSession, table);
              tableWorks5.addColumn(columnSchema1, n, hsqlArrayList);
              break;
            case 1084:
              columnSchema1 = (ColumnSchema)this.arguments[2];
              type = (Type)this.arguments[3];
              columnSchema2 = columnSchema1.duplicate();
              columnSchema2.setType(type);
              tableWorks5 = new TableWorks(paramSession, table);
              tableWorks5.retypeColumn(columnSchema1, columnSchema2);
              break;
            case 1090:
              columnSchema1 = (ColumnSchema)this.arguments[2];
              type = (Type)this.arguments[3];
              numberSequence1 = (NumberSequence)this.arguments[4];
              columnSchema3 = columnSchema1.duplicate();
              columnSchema3.setType(type);
              columnSchema3.setIdentity(numberSequence1);
              tableWorks6 = new TableWorks(paramSession, table);
              tableWorks6.retypeColumn(columnSchema1, columnSchema3);
              break;
            case 1085:
              columnSchema1 = (ColumnSchema)this.arguments[2];
              m = ((Integer)this.arguments[3]).intValue();
              numberSequence1 = (NumberSequence)this.arguments[4];
              if (columnSchema1.isIdentity()) {
                columnSchema1.getIdentitySequence().reset(numberSequence1);
                break;
              } 
              columnSchema1.setIdentity(numberSequence1);
              table.setColumnTypeVars(m);
              break;
            case 1086:
              columnSchema1 = (ColumnSchema)this.arguments[2];
              bool = ((Boolean)this.arguments[3]).booleanValue();
              tableWorks3 = new TableWorks(paramSession, table);
              tableWorks3.setColNullability(columnSchema1, bool);
              break;
            case 1087:
              columnSchema1 = (ColumnSchema)this.arguments[2];
              k = ((Integer)this.arguments[3]).intValue();
              expression = (Expression)this.arguments[4];
              tableWorks4 = new TableWorks(paramSession, table);
              tableWorks4.setColDefaultExpression(k, expression);
              break;
            case 1088:
              columnSchema1 = (ColumnSchema)this.arguments[2];
              k = ((Integer)this.arguments[3]).intValue();
              tableWorks2 = new TableWorks(paramSession, table);
              tableWorks2.setColDefaultExpression(k, null);
              table.setColumnTypeVars(k);
              break;
            case 1089:
              columnSchema1 = (ColumnSchema)this.arguments[2];
              k = ((Integer)this.arguments[3]).intValue();
              columnSchema1.setIdentity((NumberSequence)null);
              table.setColumnTypeVars(k);
              break;
          } 
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 17:
        routine2 = (Routine)this.arguments[0];
        try {
          routine2.resolveReferences(paramSession);
          Routine routine = (Routine)schemaManager.getSchemaObject(routine2.getSpecificName());
          schemaManager.replaceReferences(routine, routine2);
          routine.setAsAlteredRoutine(routine2);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 60:
      case 127:
        throw Error.runtimeError(201, "StatementSchema");
      case 1070:
        view2 = (View)this.arguments[0];
        try {
          checkSchemaUpdateAuthorisation(paramSession, view2.getSchemaName());
          View view = (View)schemaManager.getSchemaObject(view2.getName());
          if (view == null)
            throw Error.error(5501, (view2.getName()).name); 
          view2.setName(view.getName());
          view2.compile(paramSession, (SchemaObject)null);
          OrderedHashSet orderedHashSet1 = schemaManager.getReferencesTo(view.getName());
          if (orderedHashSet1.getCommonElementCount((Set)view2.getReferences()) > 0)
            throw Error.error(5502); 
          int j = schemaManager.getTableIndex(view);
          schemaManager.setTable(j, view2);
          OrderedHashSet orderedHashSet2 = new OrderedHashSet();
          orderedHashSet2.add(view2);
          try {
            schemaManager.recompileDependentObjects(orderedHashSet2);
          } catch (HsqlException hsqlException) {
            schemaManager.setTable(j, view);
            schemaManager.recompileDependentObjects(orderedHashSet2);
          } 
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1076:
        try {
          HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)this.arguments[0];
          int j = ((Integer)this.arguments[1]).intValue();
          boolean bool6 = ((Boolean)this.arguments[2]).booleanValue();
          boolean bool7 = ((Boolean)this.arguments[3]).booleanValue();
          Table table = schemaManager.getUserTable(paramSession, hsqlName.parent);
          int k = table.getColumnIndex(hsqlName.name);
          if (table.getColumnCount() == 1)
            throw Error.error(5591); 
          checkSchemaUpdateAuthorisation(paramSession, table.getSchemaName());
          TableWorks tableWorks = new TableWorks(paramSession, table);
          tableWorks.dropColumn(k, bool6);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 24:
      case 25:
      case 26:
      case 27:
      case 29:
      case 30:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case 78:
      case 115:
      case 116:
      case 135:
      case 1077:
      case 1078:
      case 1079:
        try {
          SchemaObject schemaObject;
          HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)this.arguments[0];
          int j = ((Integer)this.arguments[1]).intValue();
          boolean bool6 = ((Boolean)this.arguments[2]).booleanValue();
          boolean bool7 = ((Boolean)this.arguments[3]).booleanValue();
          switch (this.type) {
            case 29:
            case 1079:
              paramSession.checkAdmin();
              paramSession.checkDDLWrite();
              break;
            case 31:
              checkSchemaUpdateAuthorisation(paramSession, hsqlName);
              if (!schemaManager.schemaExists(hsqlName.name) && bool7)
                return Result.updateZeroResult; 
              break;
            default:
              if (hsqlName.schema == null) {
                hsqlName.schema = paramSession.getCurrentSchemaHsqlName();
              } else if (!schemaManager.schemaExists(hsqlName.schema.name) && bool7) {
                return Result.updateZeroResult;
              } 
              hsqlName.schema = schemaManager.getUserSchemaHsqlName(hsqlName.schema.name);
              checkSchemaUpdateAuthorisation(paramSession, hsqlName.schema);
              schemaObject = schemaManager.getSchemaObject(hsqlName);
              if (schemaObject == null) {
                if (bool7)
                  return Result.updateZeroResult; 
                throw Error.error(5501, hsqlName.name);
              } 
              if (hsqlName.type == 24) {
                hsqlName = ((Routine)schemaObject).getSpecificName();
                break;
              } 
              hsqlName = schemaObject.getName();
              break;
          } 
          if (!bool6)
            schemaManager.checkObjectIsReferenced(hsqlName); 
          switch (this.type) {
            case 29:
              dropRole(paramSession, hsqlName, bool6);
              break;
            case 1079:
              dropUser(paramSession, hsqlName, bool6);
              break;
            case 31:
              dropSchema(paramSession, hsqlName, bool6);
              break;
            case 25:
            case 26:
            case 34:
            case 135:
              dropObject(paramSession, hsqlName, bool6);
              break;
            case 35:
              dropType(paramSession, hsqlName, bool6);
              break;
            case 27:
              dropDomain(paramSession, hsqlName, bool6);
              break;
            case 30:
              dropRoutine(paramSession, hsqlName, bool6);
              break;
            case 32:
            case 36:
              dropTable(paramSession, hsqlName, bool6);
              break;
            case 1077:
              checkSchemaUpdateAuthorisation(paramSession, hsqlName.schema);
              schemaManager.dropIndex(paramSession, hsqlName);
              break;
            case 1078:
              checkSchemaUpdateAuthorisation(paramSession, hsqlName.schema);
              schemaManager.dropConstraint(paramSession, hsqlName, bool6);
              break;
          } 
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 48:
      case 59:
        try {
          Table table;
          boolean bool = (this.type == 48) ? true : false;
          OrderedHashSet orderedHashSet = (OrderedHashSet)this.arguments[0];
          hsqlName7 = (HsqlNameManager.HsqlName)this.arguments[1];
          setSchemaName(paramSession, (HsqlNameManager.HsqlName)null, hsqlName7);
          hsqlName7 = schemaManager.getSchemaObjectName(hsqlName7.schema, hsqlName7.name, hsqlName7.type, true);
          SchemaObject schemaObject = schemaManager.getSchemaObject(hsqlName7);
          Right right = (Right)this.arguments[2];
          Grantee grantee = (Grantee)this.arguments[3];
          boolean bool6 = ((Boolean)this.arguments[4]).booleanValue();
          boolean bool7 = ((Boolean)this.arguments[5]).booleanValue();
          if (grantee == null)
            grantee = this.isSchemaDefinition ? this.schemaName.owner : paramSession.getGrantee(); 
          GranteeManager granteeManager = paramSession.database.granteeManager;
          switch (schemaObject.getType()) {
            case 3:
            case 4:
              table = (Table)schemaObject;
              right.setColumns(table);
              if (table.getTableType() == 3 && !right.isFull())
                return Result.newErrorResult(Error.error(5595), this.sql); 
              break;
          } 
          if (bool) {
            granteeManager.grant(orderedHashSet, schemaObject, right, grantee, bool7);
          } else {
            granteeManager.revoke(orderedHashSet, schemaObject, right, grantee, bool7, bool6);
          } 
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 49:
      case 129:
        try {
          boolean bool = (this.type == 49) ? true : false;
          OrderedHashSet orderedHashSet1 = (OrderedHashSet)this.arguments[0];
          OrderedHashSet orderedHashSet2 = (OrderedHashSet)this.arguments[1];
          Grantee grantee = (Grantee)this.arguments[2];
          boolean bool6 = ((Boolean)this.arguments[3]).booleanValue();
          GranteeManager granteeManager = paramSession.database.granteeManager;
          granteeManager.checkGranteeList(orderedHashSet1);
          byte b;
          for (b = 0; b < orderedHashSet1.size(); b++) {
            String str = (String)orderedHashSet1.get(b);
            granteeManager.checkRoleList(str, orderedHashSet2, grantee, bool);
          } 
          if (bool) {
            for (b = 0; b < orderedHashSet1.size(); b++) {
              String str = (String)orderedHashSet1.get(b);
              for (byte b1 = 0; b1 < orderedHashSet2.size(); b1++) {
                String str3 = (String)orderedHashSet2.get(b1);
                granteeManager.grant(str, str3, grantee);
              } 
            } 
          } else {
            for (b = 0; b < orderedHashSet1.size(); b++) {
              String str = (String)orderedHashSet1.get(b);
              for (byte b1 = 0; b1 < orderedHashSet2.size(); b1++)
                granteeManager.revoke(str, (String)orderedHashSet2.get(b1), grantee); 
            } 
          } 
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 6:
        return Result.updateZeroResult;
      case 8:
        charset = (Charset)this.arguments[0];
        try {
          setOrCheckObjectName(paramSession, (HsqlNameManager.HsqlName)null, charset.getName(), true);
          schemaManager.addSchemaObject((SchemaObject)charset);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 10:
        collation = (Collation)this.arguments[0];
        try {
          setOrCheckObjectName(paramSession, (HsqlNameManager.HsqlName)null, collation.getName(), true);
          schemaManager.addSchemaObject((SchemaObject)collation);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 61:
        try {
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)this.arguments[0];
          paramSession.database.getGranteeManager().addRole(hsqlName);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1074:
        hsqlName3 = (HsqlNameManager.HsqlName)this.arguments[0];
        str2 = (String)this.arguments[1];
        grantee2 = (Grantee)this.arguments[2];
        bool4 = ((Boolean)this.arguments[3]).booleanValue();
        bool5 = ((Boolean)this.arguments[4]).booleanValue();
        try {
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          paramSession.database.getUserManager().createUser(paramSession, hsqlName3, str2, bool5);
          if (bool4)
            paramSession.database.getGranteeManager().grant(hsqlName3.name, "DBA", grantee2); 
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 64:
        hsqlName3 = (HsqlNameManager.HsqlName)this.arguments[0];
        grantee1 = (Grantee)this.arguments[1];
        try {
          paramSession.checkDDLWrite();
          if (schemaManager.schemaExists(hsqlName3.name)) {
            if (!paramSession.isProcessingScript() || !"PUBLIC".equals(hsqlName3.name))
              throw Error.error(5504, hsqlName3.name); 
          } else {
            schemaManager.createSchema(hsqlName3, grantee1);
            Schema schema = schemaManager.findSchema(hsqlName3.name);
            this.sql = schema.getSQL();
            if (paramSession.isProcessingScript() && paramSession.database.getProperties().isVersion18())
              paramSession.setCurrentSchemaHsqlName(schema.getName()); 
          } 
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 14:
        routine1 = (Routine)this.arguments[0];
        try {
          routine1.resolve(paramSession);
          setOrCheckObjectName(paramSession, (HsqlNameManager.HsqlName)null, routine1.getName(), false);
          schemaManager.addSchemaObject(routine1);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1072:
        hsqlName2 = (HsqlNameManager.HsqlName)this.arguments[0];
        arrayOfRoutine = (Routine[])this.arguments[1];
        try {
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          if (hsqlName2 != null)
            for (byte b = 0; b < arrayOfRoutine.length; b++) {
              arrayOfRoutine[b].setName(hsqlName2);
              schemaManager.addSchemaObject(arrayOfRoutine[b]);
            }  
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 133:
        numberSequence = (NumberSequence)this.arguments[0];
        bool1 = (Boolean)this.arguments[1];
        try {
          setOrCheckObjectName(paramSession, (HsqlNameManager.HsqlName)null, numberSequence.getName(), true);
          schemaManager.addSchemaObject(numberSequence);
        } catch (HsqlException hsqlException) {
          return (bool1 != null && bool1.booleanValue()) ? Result.updateZeroResult : Result.newErrorResult(hsqlException, this.sql);
        } 
      case 23:
        type2 = (Type)this.arguments[0];
        arrayOfConstraint = type2.userTypeModifier.getConstraints();
        try {
          setOrCheckObjectName(paramSession, (HsqlNameManager.HsqlName)null, type2.getName(), true);
          for (byte b = 0; b < arrayOfConstraint.length; b++) {
            Constraint constraint = arrayOfConstraint[b];
            setOrCheckObjectName(paramSession, type2.getName(), constraint.getName(), true);
            schemaManager.addSchemaObject(constraint);
          } 
          schemaManager.addSchemaObject((SchemaObject)type2);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 77:
        table2 = (Table)this.arguments[0];
        hsqlArrayList1 = (HsqlArrayList)this.arguments[1];
        statementDMQL = (StatementDMQL)this.arguments[2];
        bool3 = (Boolean)this.arguments[3];
        hsqlArrayList2 = null;
        try {
          setOrCheckObjectName(paramSession, (HsqlNameManager.HsqlName)null, table2.getName(), true);
        } catch (HsqlException hsqlException) {
          return (bool3 != null && bool3.booleanValue()) ? Result.updateZeroResult : Result.newErrorResult(hsqlException, this.sql);
        } 
        try {
          if (this.isSchemaDefinition)
            hsqlArrayList2 = new HsqlArrayList(); 
          if (hsqlArrayList1.size() != 0) {
            table2 = ParserDDL.addTableConstraintDefinitions(paramSession, table2, hsqlArrayList1, hsqlArrayList2, true);
            this.arguments[1] = hsqlArrayList2;
          } 
          table2.compile(paramSession, (SchemaObject)null);
          schemaManager.addSchemaObject(table2);
          if (statementDMQL != null) {
            Result result = statementDMQL.execute(paramSession);
            table2.insertIntoTable(paramSession, result);
          } 
          if (table2.hasLobColumn) {
            RowIterator rowIterator = table2.rowIterator(paramSession);
            while (rowIterator.hasNext()) {
              Row row = rowIterator.getNextRow();
              Object[] arrayOfObject = row.getData();
              paramSession.sessionData.adjustLobUsageCount(table2, arrayOfObject, 1);
            } 
          } 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          schemaManager.removeExportedKeys(table2);
          schemaManager.removeDependentObjects(table2.getName());
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 117:
        return Result.updateZeroResult;
      case 79:
        return Result.updateZeroResult;
      case 80:
        triggerDef = (TriggerDef)this.arguments[0];
        hsqlName5 = (HsqlNameManager.HsqlName)this.arguments[1];
        try {
          checkSchemaUpdateAuthorisation(paramSession, triggerDef.getSchemaName());
          schemaManager.checkSchemaObjectNotExists(triggerDef.getName());
          if (hsqlName5 != null && schemaManager.getSchemaObject(hsqlName5) == null)
            throw Error.error(5501, hsqlName5.name); 
          triggerDef.table.addTrigger(triggerDef, hsqlName5);
          schemaManager.addSchemaObject(triggerDef);
          triggerDef.start();
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 52:
        return Result.updateZeroResult;
      case 83:
        type1 = (Type)this.arguments[0];
        try {
          setOrCheckObjectName(paramSession, (HsqlNameManager.HsqlName)null, type1.getName(), true);
          schemaManager.addSchemaObject((SchemaObject)type1);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 114:
        return Result.updateZeroResult;
      case 84:
        view1 = (View)this.arguments[0];
        try {
          checkSchemaUpdateAuthorisation(paramSession, view1.getSchemaName());
          schemaManager.checkSchemaObjectNotExists(view1.getName());
          view1.compile(paramSession, (SchemaObject)null);
          schemaManager.addSchemaObject(view1);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1073:
        table1 = (Table)this.arguments[0];
        arrayOfInt2 = (int[])this.arguments[1];
        hsqlName5 = (HsqlNameManager.HsqlName)this.arguments[2];
        bool2 = ((Boolean)this.arguments[3]).booleanValue();
        try {
          setOrCheckObjectName(paramSession, table1.getName(), hsqlName5, true);
          TableWorks tableWorks = new TableWorks(paramSession, table1);
          tableWorks.addIndex(arrayOfInt2, hsqlName5, bool2);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1071:
        hsqlName1 = (HsqlNameManager.HsqlName)this.arguments[0];
        str1 = (String)this.arguments[1];
        switch (hsqlName1.type) {
          case 9:
            table5 = (Table)schemaManager.getSchemaObject(hsqlName1.parent.name, hsqlName1.parent.schema.name, 3);
            if (!paramSession.getGrantee().isFullyAccessibleByRole(table5.getName()))
              throw Error.error(5501); 
            i = table5.getColumnIndex(hsqlName1.name);
            if (i < 0)
              throw Error.error(5501); 
            columnSchema = table5.getColumn(i);
            (columnSchema.getName()).comment = str1;
            break;
          case 18:
            routineSchema = (RoutineSchema)schemaManager.getSchemaObject(hsqlName1.name, hsqlName1.schema.name, 18);
            if (!paramSession.getGrantee().isFullyAccessibleByRole(routineSchema.getName()))
              throw Error.error(5501); 
            (routineSchema.getName()).comment = str1;
            break;
          case 3:
            table4 = (Table)schemaManager.getSchemaObject(hsqlName1.name, hsqlName1.schema.name, 3);
            if (!paramSession.getGrantee().isFullyAccessibleByRole(table4.getName()))
              throw Error.error(5501); 
            (table4.getName()).comment = str1;
            break;
        } 
      case 1201:
        return Result.updateZeroResult;
    } 
    throw Error.runtimeError(201, "StatementSchema");
  }
  
  private void dropType(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean) {
    checkSchemaUpdateAuthorisation(paramSession, paramHsqlName.schema);
    Type type = (Type)paramSession.database.schemaManager.getSchemaObject(paramHsqlName);
    paramSession.database.schemaManager.removeSchemaObject(paramHsqlName, paramBoolean);
    type.userTypeModifier = null;
  }
  
  private static void dropDomain(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean) {
    Type type = (Type)paramSession.database.schemaManager.getSchemaObject(paramHsqlName);
    OrderedHashSet orderedHashSet = paramSession.database.schemaManager.getReferencesTo(type.getName());
    if (!paramBoolean && orderedHashSet.size() > 0) {
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(0);
      throw Error.error(5502, hsqlName.getSchemaQualifiedStatementName());
    } 
    Constraint[] arrayOfConstraint = type.userTypeModifier.getConstraints();
    orderedHashSet = new OrderedHashSet();
    for (byte b = 0; b < arrayOfConstraint.length; b++)
      orderedHashSet.add(arrayOfConstraint[b].getName()); 
    paramSession.database.schemaManager.removeSchemaObjects(orderedHashSet);
    paramSession.database.schemaManager.removeSchemaObject(type.getName(), paramBoolean);
    type.userTypeModifier = null;
  }
  
  private static void dropRole(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean) {
    Grantee grantee = paramSession.database.getGranteeManager().getRole(paramHsqlName.name);
    if (!paramBoolean && paramSession.database.schemaManager.hasSchemas(grantee)) {
      HsqlArrayList hsqlArrayList = paramSession.database.schemaManager.getSchemas(grantee);
      Schema schema = (Schema)hsqlArrayList.get(0);
      throw Error.error(5502, (schema.getName()).statementName);
    } 
    paramSession.database.schemaManager.dropSchemas(paramSession, grantee, paramBoolean);
    paramSession.database.getGranteeManager().dropRole(paramHsqlName.name);
  }
  
  private static void dropUser(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean) {
    User user = paramSession.database.getUserManager().get(paramHsqlName.name);
    if (paramSession.database.getSessionManager().isUserActive(paramHsqlName.name))
      throw Error.error(5539); 
    if (!paramBoolean && paramSession.database.schemaManager.hasSchemas((Grantee)user)) {
      HsqlArrayList hsqlArrayList = paramSession.database.schemaManager.getSchemas((Grantee)user);
      Schema schema = (Schema)hsqlArrayList.get(0);
      throw Error.error(5502, (schema.getName()).statementName);
    } 
    paramSession.database.schemaManager.dropSchemas(paramSession, (Grantee)user, paramBoolean);
    paramSession.database.getUserManager().dropUser(paramHsqlName.name);
  }
  
  private void dropSchema(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean) {
    HsqlNameManager.HsqlName hsqlName = paramSession.database.schemaManager.getUserSchemaHsqlName(paramHsqlName.name);
    checkSchemaUpdateAuthorisation(paramSession, hsqlName);
    paramSession.database.schemaManager.dropSchema(paramSession, paramHsqlName.name, paramBoolean);
  }
  
  private void dropRoutine(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean) {
    checkSchemaUpdateAuthorisation(paramSession, paramHsqlName.schema);
    paramSession.database.schemaManager.removeSchemaObject(paramHsqlName, paramBoolean);
  }
  
  private void dropObject(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean) {
    paramHsqlName = paramSession.database.schemaManager.getSchemaObjectName(paramHsqlName.schema, paramHsqlName.name, paramHsqlName.type, true);
    paramSession.database.schemaManager.removeSchemaObject(paramHsqlName, paramBoolean);
  }
  
  private void dropTable(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean) {
    Table table = paramSession.database.schemaManager.findUserTable(paramSession, paramHsqlName.name, paramHsqlName.schema.name);
    paramSession.database.schemaManager.dropTableOrView(paramSession, table, paramBoolean);
  }
  
  static void checkSchemaUpdateAuthorisation(Session paramSession, HsqlNameManager.HsqlName paramHsqlName) {
    if (paramSession.isProcessingLog())
      return; 
    if (SqlInvariants.isSystemSchemaName(paramHsqlName.name))
      throw Error.error(5503); 
    if (paramSession.parser.isSchemaDefinition) {
      if (paramHsqlName == paramSession.getCurrentSchemaHsqlName())
        return; 
      Error.error(5505, paramHsqlName.name);
    } 
    paramSession.getGrantee().checkSchemaUpdateOrGrantRights(paramHsqlName.name);
    paramSession.checkDDLWrite();
  }
  
  void setOrCheckObjectName(Session paramSession, HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2, boolean paramBoolean) {
    if (paramHsqlName2.schema == null) {
      paramHsqlName2.schema = (this.schemaName == null) ? paramSession.getCurrentSchemaHsqlName() : this.schemaName;
    } else {
      paramHsqlName2.schema = paramSession.getSchemaHsqlName(paramHsqlName2.schema.name);
      if (paramHsqlName2.schema == null)
        throw Error.error(5505); 
      if (this.isSchemaDefinition && this.schemaName != paramHsqlName2.schema)
        throw Error.error(5505); 
    } 
    paramHsqlName2.parent = paramHsqlName1;
    if (!this.isSchemaDefinition)
      checkSchemaUpdateAuthorisation(paramSession, paramHsqlName2.schema); 
    if (paramBoolean)
      paramSession.database.schemaManager.checkSchemaObjectNotExists(paramHsqlName2); 
  }
  
  void setSchemaName(Session paramSession, HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2) {
    if (paramHsqlName2.schema == null) {
      paramHsqlName2.schema = (this.schemaName == null) ? paramSession.getCurrentSchemaHsqlName() : this.schemaName;
    } else {
      paramHsqlName2.schema = paramSession.getSchemaHsqlName(paramHsqlName2.schema.name);
      if (paramHsqlName2.schema == null)
        throw Error.error(5505); 
      if (this.isSchemaDefinition && this.schemaName != paramHsqlName2.schema)
        throw Error.error(5505); 
    } 
  }
  
  public boolean isAutoCommitStatement() {
    return true;
  }
  
  public String describe(Session paramSession) {
    return this.sql;
  }
  
  public Object[] getArguments() {
    return this.arguments;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */