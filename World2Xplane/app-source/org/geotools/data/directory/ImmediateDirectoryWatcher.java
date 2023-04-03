/*    */ package org.geotools.data.directory;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ class ImmediateDirectoryWatcher implements DirectoryWatcher {
/*    */   File directory;
/*    */   
/*    */   Long lastUpdated;
/*    */   
/*    */   public ImmediateDirectoryWatcher(File directory) {
/* 36 */     this.directory = directory;
/*    */   }
/*    */   
/*    */   public synchronized boolean isStale() {
/* 40 */     return (this.lastUpdated == null || this.lastUpdated.longValue() < this.directory.lastModified());
/*    */   }
/*    */   
/*    */   public synchronized void mark() {
/* 44 */     this.lastUpdated = Long.valueOf(this.directory.lastModified());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\directory\ImmediateDirectoryWatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */