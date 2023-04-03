package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.persist.DataFileCache;
import org.hsqldb.persist.DataSpaceManager;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.persist.TableSpaceManager;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rights.User;
import org.hsqldb.scriptio.ScriptWriterText;

public class StatementCommand extends Statement {
  Object[] parameters;
  
  StatementCommand(int paramInt, Object[] paramArrayOfObject) {
    this(paramInt, paramArrayOfObject, (HsqlNameManager.HsqlName[])null, (HsqlNameManager.HsqlName[])null);
  }
  
  StatementCommand(int paramInt, Object[] paramArrayOfObject, HsqlNameManager.HsqlName[] paramArrayOfHsqlName1, HsqlNameManager.HsqlName[] paramArrayOfHsqlName2) {
    super(paramInt);
    String str;
    this.isTransactionStatement = true;
    this.parameters = paramArrayOfObject;
    if (paramArrayOfHsqlName1 != null)
      this.readTableNames = paramArrayOfHsqlName1; 
    if (paramArrayOfHsqlName2 != null)
      this.writeTableNames = paramArrayOfHsqlName2; 
    switch (paramInt) {
      case 1215:
        this.group = 2014;
        return;
      case 1191:
        this.group = 2009;
        this.statementReturnType = 2;
        this.isTransactionStatement = false;
        this.isLogged = false;
        return;
      case 1002:
        this.group = 2014;
        this.isLogged = false;
        return;
      case 1004:
        str = (String)this.parameters[0];
        if (str == null)
          this.statementReturnType = 2; 
        this.group = 2014;
        this.isLogged = false;
        return;
      case 1001:
        this.group = 2014;
        this.isLogged = false;
        return;
      case 1016:
      case 1032:
      case 1033:
      case 1055:
        this.isTransactionStatement = false;
        this.group = 2013;
        return;
      case 1011:
      case 1012:
      case 1013:
      case 1014:
      case 1015:
      case 1017:
      case 1018:
      case 1020:
      case 1021:
      case 1022:
      case 1025:
      case 1026:
      case 1031:
      case 1034:
      case 1035:
      case 1036:
      case 1037:
      case 1039:
      case 1040:
      case 1046:
      case 1047:
      case 1049:
      case 1050:
      case 1051:
      case 1052:
      case 1053:
      case 1054:
        this.group = 2013;
        return;
      case 1198:
      case 1199:
      case 1200:
        this.group = 2012;
        return;
      case 1196:
        this.isLogged = false;
      case 1195:
        this.group = 2012;
        return;
      case 1194:
        this.group = 2012;
        return;
      case 1003:
        this.group = 2014;
        this.isTransactionStatement = false;
        this.isLogged = false;
        return;
      case 1197:
        this.group = 2012;
        return;
      case 1193:
        this.group = 2013;
        this.isTransactionStatement = false;
        this.isLogged = false;
        return;
      case 1060:
      case 1061:
      case 1062:
        this.group = 2013;
        this.isTransactionStatement = false;
        return;
      case 1005:
        this.group = 2011;
        this.isTransactionStatement = false;
        this.isLogged = false;
        return;
    } 
    throw Error.runtimeError(201, "StatementCommand");
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
    try {
      if (this.isLogged)
        paramSession.database.logger.writeOtherStatement(paramSession, this.sql); 
    } catch (Throwable throwable) {
      return Result.newErrorResult(throwable, this.sql);
    } 
    return result;
  }
  
  Result getResult(Session paramSession) {
    Statement statement;
    String str2;
    boolean bool1;
    String str1;
    HsqlNameManager.HsqlName hsqlName;
    Integer integer;
    int i;
    ScriptWriterText scriptWriterText;
    User user;
    boolean bool3;
    String str3;
    boolean bool2;
    boolean bool4;
    int j;
    boolean bool5;
    boolean bool6;
    if (this.isExplain)
      return Result.newSingleColumnStringResult("OPERATION", describe(paramSession)); 
    switch (this.type) {
      case 1215:
        return getTruncateResult(paramSession);
      case 1191:
        statement = (Statement)this.parameters[0];
        return Result.newSingleColumnStringResult("OPERATION", statement.describe(paramSession));
      case 1001:
        str2 = (String)this.parameters[0];
        bool3 = ((Boolean)this.parameters[1]).booleanValue();
        bool4 = ((Boolean)this.parameters[2]).booleanValue();
        bool5 = ((Boolean)this.parameters[3]).booleanValue();
        bool6 = ((Boolean)this.parameters[4]).booleanValue();
        try {
          paramSession.checkAdmin();
          if (!paramSession.database.getType().equals("file:"))
            throw Error.error(459); 
          if (paramSession.database.isFilesReadOnly())
            throw Error.error(455); 
          if (paramSession.database.logger.isStoredFileAccess())
            throw Error.error(457); 
          paramSession.database.logger.backup(str2, bool4, bool3, bool5, bool6);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1002:
        bool1 = ((Boolean)this.parameters[0]).booleanValue();
        paramSession.database.lobManager.lock();
        try {
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          paramSession.database.logger.checkpoint(bool1);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } finally {
          paramSession.database.lobManager.unlock();
        } 
      case 1011:
        try {
          bool1 = ((Boolean)this.parameters[0]).booleanValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          paramSession.database.logger.setIncrementBackup(bool1);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1012:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          bool3 = (this.parameters[1] == null);
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          if (bool3 && !paramSession.database.getProperties().validateProperty("hsqldb.cache_rows", k))
            throw Error.error(5556); 
          paramSession.database.logger.setCacheMaxRows(k);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1013:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          bool3 = (this.parameters[1] == null);
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          if (bool3 && !paramSession.database.getProperties().validateProperty("hsqldb.cache_size", k))
            throw Error.error(5556); 
          paramSession.database.logger.setCacheSize(k);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1014:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          paramSession.database.logger.setFilesCheck(k);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1017:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          if (paramSession.isProcessingScript()) {
            paramSession.database.logger.setLobFileScaleNoCheck(k);
          } else {
            paramSession.database.logger.setLobFileScale(k);
          } 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1018:
        try {
          bool1 = ((Boolean)this.parameters[0]).booleanValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          if (paramSession.isProcessingScript()) {
            paramSession.database.logger.setLobFileCompressedNoCheck(bool1);
          } else {
            paramSession.database.logger.setLobFileCompressed(bool1);
          } 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1025:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          if (paramSession.isProcessingScript()) {
            paramSession.database.logger.setDataFileScaleNoCheck(k);
          } else {
            paramSession.database.logger.setDataFileScale(k);
          } 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1031:
        try {
          bool1 = ((Boolean)this.parameters[0]).booleanValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          if (paramSession.database.getType().equals("res:"))
            return Result.updateZeroResult; 
          if (paramSession.database.isFilesReadOnly())
            return Result.updateZeroResult; 
          paramSession.database.logger.setDataFileSpaces(bool1);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1015:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          if (!paramSession.database.getProperties().validateProperty("hsqldb.defrag_limit", k))
            throw Error.error(5556); 
          paramSession.database.logger.setDefagLimit(k);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1016:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          bool3 = ((Boolean)this.parameters[1]).booleanValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          paramSession.database.logger.setEventLogLevel(k, bool3);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1022:
        try {
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          Object object = this.parameters[0];
          if (object instanceof Boolean) {
            bool3 = ((Boolean)this.parameters[0]).booleanValue();
            paramSession.database.logger.setNioDataFile(bool3);
          } else {
            int k = ((Integer)this.parameters[0]).intValue();
            paramSession.database.logger.setNioMaxSize(k);
          } 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1020:
        try {
          bool1 = ((Boolean)this.parameters[0]).booleanValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          paramSession.database.logger.setLogData(bool1);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1021:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          paramSession.database.logger.setLogSize(k);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1032:
        try {
          String str = (String)this.parameters[0];
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1026:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          paramSession.database.logger.setScriptType(k);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1033:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          paramSession.database.logger.setWriteDelay(k);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1036:
        try {
          Routine routine = (Routine)this.parameters[0];
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          paramSession.database.userManager.setExtAuthenticationFunction(routine);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1040:
        try {
          Routine routine = (Routine)this.parameters[0];
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          paramSession.database.userManager.setPasswordCheckFunction(routine);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1047:
        try {
          String str = (String)this.parameters[0];
          bool3 = ((Boolean)this.parameters[1]).booleanValue();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          paramSession.database.collation.setCollation(str, Boolean.valueOf(bool3));
          paramSession.database.schemaManager.setSchemaChangeTimestamp();
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1049:
        bool1 = ((Boolean)this.parameters[0]).booleanValue();
        paramSession.checkAdmin();
        paramSession.checkDDLWrite();
        paramSession.database.setReferentialIntegrity(bool1);
        return Result.updateZeroResult;
      case 1050:
        str1 = (String)this.parameters[0];
        bool3 = ((Boolean)this.parameters[1]).booleanValue();
        j = ((Number)this.parameters[2]).intValue();
        paramSession.checkAdmin();
        paramSession.checkDDLWrite();
        if (str1 == "sql.enforce_names") {
          paramSession.database.setStrictNames(bool3);
        } else if (str1 == "sql.regular_names") {
          paramSession.database.setRegularNames(bool3);
        } else if (str1 == "sql.enforce_size") {
          paramSession.database.setStrictColumnSize(bool3);
        } else if (str1 == "sql.enforce_types") {
          paramSession.database.setStrictTypes(bool3);
        } else if (str1 == "sql.enforce_refs") {
          paramSession.database.setStrictReferences(bool3);
        } else if (str1 == "sql.enforce_tdc_delete") {
          paramSession.database.setStrictTDCD(bool3);
        } else if (str1 == "sql.enforce_tdc_update") {
          paramSession.database.setStrictTDCU(bool3);
        } else if (str1 == "jdbc.translate_tti_types") {
          paramSession.database.setTranslateTTI(bool3);
        } else if (str1 == "sql.concat_nulls") {
          paramSession.database.setConcatNulls(bool3);
        } else if (str1 == "sql.nulls_first") {
          paramSession.database.setNullsFirst(bool3);
        } else if (str1 == "sql.nulls_order") {
          paramSession.database.setNullsOrder(bool3);
        } else if (str1 == "sql.unique_nulls") {
          paramSession.database.setUniqueNulls(bool3);
        } else if (str1 == "sql.convert_trunc") {
          paramSession.database.setConvertTrunc(bool3);
        } else if (str1 == "sql.avg_scale") {
          paramSession.database.setAvgScale(j);
        } else if (str1 == "sql.double_nan") {
          paramSession.database.setDoubleNaN(bool3);
        } else if (str1 == "sql.longvar_is_lob") {
          paramSession.database.setLongVarIsLob(bool3);
        } else if (str1 == "sql.ignore_case") {
          paramSession.database.setIgnoreCase(bool3);
          paramSession.setIgnoreCase(bool3);
        } else if (str1 == "sql.syntax_db2") {
          paramSession.database.setSyntaxDb2(bool3);
        } else if (str1 == "sql.syntax_mss") {
          paramSession.database.setSyntaxMss(bool3);
        } else if (str1 == "sql.syntax_mys") {
          paramSession.database.setSyntaxMys(bool3);
        } else if (str1 == "sql.syntax_ora") {
          paramSession.database.setSyntaxOra(bool3);
        } else if (str1 == "sql.syntax_pgs") {
          paramSession.database.setSyntaxPgs(bool3);
        } 
        return Result.updateZeroResult;
      case 1034:
        hsqlName = (HsqlNameManager.HsqlName)this.parameters[0];
        paramSession.checkAdmin();
        paramSession.checkDDLWrite();
        paramSession.database.schemaManager.setDefaultSchemaHsqlName(hsqlName);
        paramSession.database.schemaManager.setSchemaChangeTimestamp();
        return Result.updateZeroResult;
      case 1035:
        integer = (Integer)this.parameters[0];
        paramSession.checkAdmin();
        paramSession.checkDDLWrite();
        paramSession.database.schemaManager.setDefaultTableType(integer.intValue());
        return Result.updateZeroResult;
      case 1052:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          paramSession.checkAdmin();
          paramSession.database.txManager.setTransactionControl(paramSession, k);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1053:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          paramSession.checkAdmin();
          paramSession.database.defaultIsolationLevel = k;
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1054:
        try {
          boolean bool = ((Boolean)this.parameters[0]).booleanValue();
          paramSession.checkAdmin();
          paramSession.database.txConflictRollback = bool;
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1037:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          paramSession.checkAdmin();
          JavaSystem.gcFrequency = k;
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1039:
        try {
          String str = (String)this.parameters[0];
          Object object = this.parameters[1];
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1046:
        i = ((Integer)this.parameters[0]).intValue();
        paramSession.checkAdmin();
        paramSession.database.setResultMaxMemoryRows(i);
        return Result.updateZeroResult;
      case 1051:
        try {
          String str = (String)this.parameters[0];
          HsqlProperties hsqlProperties = null;
          paramSession.checkAdmin();
          if (str.length() > 0) {
            hsqlProperties = HsqlProperties.delimitedArgPairsToProps(str, "=", ";", null);
            if ((hsqlProperties.getErrorKeys()).length > 0)
              throw Error.error(482, hsqlProperties.getErrorKeys()[0]); 
            paramSession.database.logger.setDefaultTextTableProperties(str, hsqlProperties);
          } 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1055:
        try {
          String str = (String)this.parameters[0];
          paramSession.checkAdmin();
          paramSession.database.setUniqueName(str);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1004:
        scriptWriterText = null;
        str3 = (String)this.parameters[0];
        try {
          paramSession.checkAdmin();
          if (str3 == null)
            return paramSession.database.getScript(false); 
          scriptWriterText = new ScriptWriterText(paramSession.database, str3, true, true, true);
          scriptWriterText.writeAll();
          scriptWriterText.close();
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1003:
        try {
          int k = ((Integer)this.parameters[0]).intValue();
          paramSession.checkAdmin();
          paramSession.database.close(k);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1199:
        try {
          HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)this.parameters[0];
          Table table = paramSession.database.schemaManager.getTable(paramSession, hsqlName1.name, hsqlName1.schema.name);
          DataFileCache dataFileCache = paramSession.database.logger.getCache();
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          if (!paramSession.database.logger.isDataFileSpaces())
            throw Error.error(457); 
          if (table.getSpaceID() != 7)
            return Result.updateZeroResult; 
          if (dataFileCache == null)
            return Result.updateZeroResult; 
          DataSpaceManager dataSpaceManager = dataFileCache.spaceManager;
          int k = dataSpaceManager.getNewTableSpaceID();
          table.setSpaceID(k);
          if (!table.isCached())
            return Result.updateZeroResult; 
          TableSpaceManager tableSpaceManager = dataSpaceManager.getTableSpace(k);
          PersistentStore persistentStore = table.getRowStore(paramSession);
          persistentStore.setSpaceManager(tableSpaceManager);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1200:
        try {
          HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)this.parameters[0];
          int k = ((Integer)this.parameters[1]).intValue();
          Table table = paramSession.database.schemaManager.getTable(paramSession, hsqlName1.name, hsqlName1.schema.name);
          DataFileCache dataFileCache = paramSession.database.logger.getCache();
          if (!paramSession.isProcessingScript())
            return Result.updateZeroResult; 
          if (table.getTableType() != 5)
            return Result.updateZeroResult; 
          if (dataFileCache == null)
            return Result.updateZeroResult; 
          if (table.getSpaceID() != 7)
            return Result.updateZeroResult; 
          table.setSpaceID(k);
          DataSpaceManager dataSpaceManager = dataFileCache.spaceManager;
          TableSpaceManager tableSpaceManager = dataSpaceManager.getTableSpace(k);
          PersistentStore persistentStore = table.getRowStore(paramSession);
          persistentStore.setSpaceManager(tableSpaceManager);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1198:
        try {
          HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)this.parameters[0];
          int[] arrayOfInt = (int[])this.parameters[1];
          Table table = paramSession.database.schemaManager.getTable(paramSession, hsqlName1.name, hsqlName1.schema.name);
          StatementSchema.checkSchemaUpdateAuthorisation(paramSession, table.getSchemaName());
          if (!table.isCached() && !table.isText())
            throw Error.error(457); 
          Index index = table.getIndexForColumns(paramSession, arrayOfInt);
          if (index != null) {
            Index[] arrayOfIndex = table.getIndexList();
            for (byte b = 0; b < arrayOfIndex.length; b++)
              arrayOfIndex[b].setClustered(false); 
            index.setClustered(true);
          } 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1193:
        try {
          HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)this.parameters[0];
          str3 = (String)this.parameters[1];
          Table table = paramSession.database.schemaManager.getTable(paramSession, hsqlName1.name, hsqlName1.schema.name);
          if (paramSession.isProcessingScript())
            table.setIndexRoots(paramSession, str3); 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1194:
        try {
          HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)this.parameters[0];
          Table table = paramSession.database.schemaManager.getTable(paramSession, hsqlName1.name, hsqlName1.schema.name);
          boolean bool = ((Boolean)this.parameters[1]).booleanValue();
          StatementSchema.checkSchemaUpdateAuthorisation(paramSession, table.getSchemaName());
          table.setDataReadOnly(bool);
          paramSession.database.schemaManager.setSchemaChangeTimestamp();
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1195:
      case 1196:
        try {
          HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)this.parameters[0];
          Table table = paramSession.database.schemaManager.getTable(paramSession, hsqlName1.name, hsqlName1.schema.name);
          StatementSchema.checkSchemaUpdateAuthorisation(paramSession, table.getSchemaName());
          if (!table.isText()) {
            HsqlException hsqlException = Error.error(321);
            return Result.newErrorResult(hsqlException, this.sql);
          } 
          if (this.parameters[1] != null) {
            boolean bool = ((Boolean)this.parameters[1]).booleanValue();
            if (bool) {
              ((TextTable)table).connect(paramSession);
            } else {
              ((TextTable)table).disconnect();
            } 
            paramSession.database.schemaManager.setSchemaChangeTimestamp();
            return Result.updateZeroResult;
          } 
          String str = (String)this.parameters[2];
          bool5 = ((Boolean)this.parameters[3]).booleanValue();
          bool6 = ((Boolean)this.parameters[4]).booleanValue();
          if (bool6) {
            ((TextTable)table).setHeader(str);
          } else {
            ((TextTable)table).setDataSource(paramSession, str, bool5, false);
          } 
          return Result.updateZeroResult;
        } catch (Throwable throwable) {
          if (!(throwable instanceof HsqlException))
            throwable = Error.error(467, throwable.toString()); 
          if (paramSession.isProcessingLog() || paramSession.isProcessingScript()) {
            paramSession.addWarning((HsqlException)throwable);
            paramSession.database.logger.logWarningEvent("Problem processing SET TABLE SOURCE", throwable);
            return Result.updateZeroResult;
          } 
          return Result.newErrorResult(throwable, this.sql);
        } 
      case 1197:
        try {
          HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)this.parameters[0];
          int k = ((Integer)this.parameters[1]).intValue();
          Table table = paramSession.database.schemaManager.getUserTable(paramSession, hsqlName1.name, hsqlName1.schema.name);
          if (hsqlName1.schema != SqlInvariants.LOBS_SCHEMA_HSQLNAME)
            StatementSchema.checkSchemaUpdateAuthorisation(paramSession, table.getSchemaName()); 
          TableWorks tableWorks = new TableWorks(paramSession, table);
          tableWorks.setTableType(paramSession, k);
          paramSession.database.schemaManager.setSchemaChangeTimestamp();
          if (hsqlName1.schema == SqlInvariants.LOBS_SCHEMA_HSQLNAME)
            paramSession.database.lobManager.compileStatements(); 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1060:
        user = (User)this.parameters[0];
        bool2 = ((Boolean)this.parameters[1]).booleanValue();
        paramSession.checkAdmin();
        paramSession.checkDDLWrite();
        user.isLocalOnly = bool2;
        paramSession.database.schemaManager.setSchemaChangeTimestamp();
        return Result.updateZeroResult;
      case 1061:
        try {
          user = (User)this.parameters[0];
          HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)this.parameters[1];
          paramSession.checkDDLWrite();
          if (user == null) {
            user = paramSession.getUser();
          } else {
            paramSession.checkAdmin();
            paramSession.checkDDLWrite();
            user = paramSession.database.userManager.get(user.getName().getNameString());
          } 
          if (hsqlName1 != null)
            hsqlName1 = paramSession.database.schemaManager.getSchemaHsqlName(hsqlName1.name); 
          user.setInitialSchema(hsqlName1);
          paramSession.database.schemaManager.setSchemaChangeTimestamp();
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1062:
        try {
          user = (this.parameters[0] == null) ? paramSession.getUser() : (User)this.parameters[0];
          String str = (String)this.parameters[1];
          boolean bool = ((Boolean)this.parameters[2]).booleanValue();
          paramSession.checkDDLWrite();
          paramSession.database.userManager.setPassword(paramSession, user, str, bool);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1005:
        try {
          long l = ((Number)this.parameters[0]).longValue();
          j = ((Number)this.parameters[1]).intValue();
          Session session = paramSession.database.sessionManager.getSession(l);
          if (session == null)
            throw Error.error(4500); 
          switch (j) {
            case 2:
              session.resetSession();
              break;
            case 278:
              session.sessionData.persistentStoreCollection.clearAllTables();
              break;
            case 236:
              session.sessionData.closeAllNavigators();
              break;
            case 39:
              session.abortTransaction = true;
              session.latch.setCount(0);
              session.close();
              break;
            case 233:
              session.abortTransaction = true;
              session.latch.setCount(0);
              break;
          } 
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
        return Result.updateZeroResult;
    } 
    throw Error.runtimeError(201, "StatemntCommand");
  }
  
  Result getTruncateResult(Session paramSession) {
    try {
      Table[] arrayOfTable;
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)this.parameters[0];
      boolean bool1 = ((Boolean)this.parameters[1]).booleanValue();
      boolean bool2 = ((Boolean)this.parameters[2]).booleanValue();
      if (hsqlName.type == 3) {
        Table table = paramSession.database.schemaManager.getUserTable(paramSession, hsqlName);
        arrayOfTable = new Table[] { table };
        paramSession.getGrantee().checkDelete(table);
        if (!bool2)
          for (byte b1 = 0; b1 < table.fkMainConstraints.length; b1++) {
            if (table.fkMainConstraints[b1].getRef() != table) {
              HsqlNameManager.HsqlName hsqlName1 = table.fkMainConstraints[b1].getRef().getName();
              Table table1 = paramSession.database.schemaManager.getUserTable(paramSession, hsqlName1);
              if (!table1.isEmpty(paramSession))
                throw Error.error(8, (table1.getName()).name); 
            } 
          }  
      } else {
        paramSession.database.schemaManager.getSchemaHsqlName(hsqlName.name);
        HashMappedList hashMappedList = paramSession.database.schemaManager.getTables(hsqlName.name);
        arrayOfTable = new Table[hashMappedList.size()];
        hashMappedList.toValuesArray((Object[])arrayOfTable);
        StatementSchema.checkSchemaUpdateAuthorisation(paramSession, hsqlName);
        if (!bool2) {
          OrderedHashSet orderedHashSet = new OrderedHashSet();
          paramSession.database.schemaManager.getCascadingReferencesToSchema(hsqlName, orderedHashSet);
          for (byte b1 = 0; b1 < orderedHashSet.size(); b1++) {
            HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)orderedHashSet.get(b1);
            if (hsqlName1.type == 5 && hsqlName1.parent.type == 3) {
              Table table = paramSession.database.schemaManager.getUserTable(paramSession, hsqlName1.parent);
              if (!table.isEmpty(paramSession))
                throw Error.error(8, (table.getName()).name); 
            } 
          } 
        } 
        if (bool1) {
          Iterator iterator = paramSession.database.schemaManager.databaseObjectIterator(hsqlName.name, 7);
          while (iterator.hasNext()) {
            NumberSequence numberSequence = (NumberSequence)iterator.next();
            numberSequence.reset();
          } 
        } 
      } 
      for (byte b = 0; b < arrayOfTable.length; b++) {
        Table table = arrayOfTable[b];
        PersistentStore persistentStore = table.getRowStore(paramSession);
        persistentStore.removeAll();
        if (bool1 && table.identitySequence != null)
          table.identitySequence.reset(); 
      } 
      return Result.updateZeroResult;
    } catch (HsqlException hsqlException) {
      return Result.newErrorResult(hsqlException, this.sql);
    } 
  }
  
  public ResultMetaData getResultMetaData() {
    switch (this.type) {
      case 1191:
        return ResultMetaData.newSingleColumnMetaData("OPERATION");
      case 1004:
        if (this.statementReturnType == 2)
          return ResultMetaData.newSingleColumnMetaData("COMMANDS"); 
        break;
    } 
    return super.getResultMetaData();
  }
  
  public boolean isAutoCommitStatement() {
    return this.isTransactionStatement;
  }
  
  public String describe(Session paramSession) {
    return this.sql;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */