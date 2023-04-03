/*    */ package org.apache.commons.compress.changes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ChangeSetResults {
/* 28 */   private final List<String> addedFromChangeSet = new ArrayList<String>();
/*    */   
/* 29 */   private final List<String> addedFromStream = new ArrayList<String>();
/*    */   
/* 30 */   private final List<String> deleted = new ArrayList<String>();
/*    */   
/*    */   void deleted(String fileName) {
/* 37 */     this.deleted.add(fileName);
/*    */   }
/*    */   
/*    */   void addedFromStream(String fileName) {
/* 46 */     this.addedFromStream.add(fileName);
/*    */   }
/*    */   
/*    */   void addedFromChangeSet(String fileName) {
/* 55 */     this.addedFromChangeSet.add(fileName);
/*    */   }
/*    */   
/*    */   public List<String> getAddedFromChangeSet() {
/* 63 */     return this.addedFromChangeSet;
/*    */   }
/*    */   
/*    */   public List<String> getAddedFromStream() {
/* 71 */     return this.addedFromStream;
/*    */   }
/*    */   
/*    */   public List<String> getDeleted() {
/* 79 */     return this.deleted;
/*    */   }
/*    */   
/*    */   boolean hasBeenAdded(String filename) {
/* 88 */     if (this.addedFromChangeSet.contains(filename) || this.addedFromStream.contains(filename))
/* 89 */       return true; 
/* 91 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\changes\ChangeSetResults.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */