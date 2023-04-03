/*    */ package org.geotools.util;
/*    */ 
/*    */ import org.opengis.util.InternationalString;
/*    */ 
/*    */ public class NullProgressListener implements ProgressListener {
/*    */   private String description;
/*    */   
/*    */   private boolean canceled = false;
/*    */   
/*    */   public String getDescription() {
/* 54 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 58 */     this.description = description;
/*    */   }
/*    */   
/*    */   public void started() {}
/*    */   
/*    */   public void progress(float percent) {}
/*    */   
/*    */   public float getProgress() {
/* 70 */     return 0.0F;
/*    */   }
/*    */   
/*    */   public void complete() {}
/*    */   
/*    */   public void dispose() {}
/*    */   
/*    */   public void setCanceled(boolean cancel) {
/* 82 */     this.canceled = cancel;
/*    */   }
/*    */   
/*    */   public boolean isCanceled() {
/* 86 */     return this.canceled;
/*    */   }
/*    */   
/*    */   public void warningOccurred(String source, String location, String warning) {}
/*    */   
/*    */   public void exceptionOccurred(Throwable exception) {}
/*    */   
/*    */   public InternationalString getTask() {
/* 99 */     return null;
/*    */   }
/*    */   
/*    */   public void setTask(InternationalString task) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\NullProgressListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */