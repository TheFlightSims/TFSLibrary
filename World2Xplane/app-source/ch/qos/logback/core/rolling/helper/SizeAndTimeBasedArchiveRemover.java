/*    */ package ch.qos.logback.core.rolling.helper;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class SizeAndTimeBasedArchiveRemover extends DefaultArchiveRemover {
/*    */   public SizeAndTimeBasedArchiveRemover(FileNamePattern fileNamePattern, RollingCalendar rc) {
/* 23 */     super(fileNamePattern, rc);
/*    */   }
/*    */   
/*    */   public void cleanByPeriodOffset(Date now, int periodOffset) {
/* 27 */     Date dateOfPeriodToClean = this.rc.getRelativeDate(now, periodOffset);
/* 29 */     String regex = this.fileNamePattern.toRegexForFixedDate(dateOfPeriodToClean);
/* 30 */     String stemRegex = FileFilterUtil.afterLastSlash(regex);
/* 31 */     File archive0 = new File(this.fileNamePattern.convertMultipleArguments(new Object[] { dateOfPeriodToClean, Integer.valueOf(0) }));
/* 35 */     archive0 = archive0.getAbsoluteFile();
/* 37 */     File parentDir = archive0.getAbsoluteFile().getParentFile();
/* 38 */     File[] matchingFileArray = FileFilterUtil.filesInFolderMatchingStemRegex(parentDir, stemRegex);
/* 41 */     for (File f : matchingFileArray)
/* 42 */       f.delete(); 
/* 45 */     if (this.parentClean)
/* 46 */       removeFolderIfEmpty(parentDir); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\rolling\helper\SizeAndTimeBasedArchiveRemover.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */