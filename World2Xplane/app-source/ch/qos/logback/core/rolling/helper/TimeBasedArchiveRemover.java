/*    */ package ch.qos.logback.core.rolling.helper;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class TimeBasedArchiveRemover extends DefaultArchiveRemover {
/*    */   public TimeBasedArchiveRemover(FileNamePattern fileNamePattern, RollingCalendar rc) {
/* 23 */     super(fileNamePattern, rc);
/*    */   }
/*    */   
/*    */   protected void cleanByPeriodOffset(Date now, int periodOffset) {
/* 27 */     Date date2delete = this.rc.getRelativeDate(now, periodOffset);
/* 28 */     String filename = this.fileNamePattern.convert(date2delete);
/* 29 */     File file2Delete = new File(filename);
/* 30 */     if (file2Delete.exists() && file2Delete.isFile()) {
/* 31 */       file2Delete.delete();
/* 32 */       addInfo("deleting " + file2Delete);
/* 33 */       if (this.parentClean)
/* 34 */         removeFolderIfEmpty(file2Delete.getParentFile()); 
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 40 */     return "c.q.l.core.rolling.helper.TimeBasedArchiveRemover";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\rolling\helper\TimeBasedArchiveRemover.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */