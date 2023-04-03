/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import java.io.File;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
/*    */ 
/*    */ public abstract class DatabaseTaskManagerFactory extends TaskManagerFactory {
/*    */   protected DatabaseLoginCredentials getDatabaseLoginCredentials(TaskConfiguration taskConfig) {
/* 26 */     DatabaseLoginCredentials loginCredentials = new DatabaseLoginCredentials("localhost", "osm", DatabaseConstants.TASK_DEFAULT_USER, DatabaseConstants.TASK_DEFAULT_PASSWORD, false, false, DatabaseConstants.TASK_DEFAULT_DB_TYPE);
/* 33 */     if (doesArgumentExist(taskConfig, "authFile")) {
/* 36 */       AuthenticationPropertiesLoader authLoader = new AuthenticationPropertiesLoader(new File(getStringArgument(taskConfig, "authFile")));
/* 39 */       authLoader.updateLoginCredentials(loginCredentials);
/*    */     } 
/* 44 */     loginCredentials.setHost(getStringArgument(taskConfig, "host", loginCredentials.getHost()));
/* 46 */     loginCredentials.setDatabase(getStringArgument(taskConfig, "database", loginCredentials.getDatabase()));
/* 48 */     loginCredentials.setUser(getStringArgument(taskConfig, "user", loginCredentials.getUser()));
/* 50 */     loginCredentials.setPassword(getStringArgument(taskConfig, "password", loginCredentials.getPassword()));
/* 52 */     loginCredentials.setForceUtf8(getBooleanArgument(taskConfig, "forceUtf8", loginCredentials.getForceUtf8()));
/* 54 */     loginCredentials.setProfileSql(getBooleanArgument(taskConfig, "profileSql", loginCredentials.getProfileSql()));
/* 56 */     loginCredentials.setDbType(getStringArgument(taskConfig, "dbType", loginCredentials.getDbType().toString()));
/* 59 */     return loginCredentials;
/*    */   }
/*    */   
/*    */   protected DatabasePreferences getDatabasePreferences(TaskConfiguration taskConfig) {
/* 72 */     DatabasePreferences preferences = new DatabasePreferences(true, false);
/* 77 */     preferences.setValidateSchemaVersion(getBooleanArgument(taskConfig, "validateSchemaVersion", preferences.getValidateSchemaVersion()));
/* 79 */     preferences.setAllowIncorrectSchemaVersion(getBooleanArgument(taskConfig, "allowIncorrectSchemaVersion", preferences.getAllowIncorrectSchemaVersion()));
/* 84 */     return preferences;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DatabaseTaskManagerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */