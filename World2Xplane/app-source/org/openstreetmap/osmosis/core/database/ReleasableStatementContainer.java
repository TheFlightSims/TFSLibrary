/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import org.openstreetmap.osmosis.core.lifecycle.Releasable;
/*    */ 
/*    */ public class ReleasableStatementContainer implements Releasable {
/* 22 */   private static final Logger LOG = Logger.getLogger(ReleasableStatementContainer.class.getName());
/*    */   
/* 31 */   private List<Statement> objects = new ArrayList<Statement>();
/*    */   
/*    */   public <T extends Statement> T add(T statement) {
/* 46 */     this.objects.add((Statement)statement);
/* 48 */     return statement;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 56 */     this.objects.clear();
/*    */   }
/*    */   
/*    */   public void release() {
/* 65 */     for (Statement statement : this.objects) {
/*    */       try {
/* 67 */         statement.close();
/* 68 */       } catch (SQLException e) {
/* 70 */         LOG.log(Level.WARNING, "Unable to close database statement.", e);
/*    */       } 
/*    */     } 
/* 73 */     this.objects.clear();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\ReleasableStatementContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */