/*    */ package org.geotools.util;
/*    */ 
/*    */ import org.opengis.util.InternationalString;
/*    */ import org.opengis.util.ProgressListener;
/*    */ 
/*    */ public class DelegateProgressListener implements ProgressListener {
/*    */   protected ProgressListener delegate;
/*    */   
/*    */   private InternationalString task;
/*    */   
/*    */   private boolean isCanceled;
/*    */   
/*    */   public DelegateProgressListener(ProgressListener progress) {
/* 32 */     if (progress == null)
/* 32 */       progress = new NullProgressListener(); 
/* 33 */     this.delegate = progress;
/*    */   }
/*    */   
/*    */   public void started() {
/* 37 */     this.delegate.started();
/*    */   }
/*    */   
/*    */   public void complete() {
/* 40 */     this.delegate.complete();
/*    */   }
/*    */   
/*    */   public void dispose() {
/* 44 */     this.delegate.dispose();
/* 45 */     this.delegate = null;
/*    */   }
/*    */   
/*    */   public void exceptionOccurred(Throwable exception) {
/* 49 */     this.delegate.exceptionOccurred(exception);
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 53 */     return this.delegate.getDescription();
/*    */   }
/*    */   
/*    */   public InternationalString getTask() {
/* 57 */     return this.delegate.getTask();
/*    */   }
/*    */   
/*    */   public boolean isCanceled() {
/* 61 */     return this.delegate.isCanceled();
/*    */   }
/*    */   
/*    */   public void progress(float progress) {
/* 65 */     this.delegate.progress(progress);
/*    */   }
/*    */   
/*    */   public float getProgress() {
/* 69 */     return this.delegate.getProgress();
/*    */   }
/*    */   
/*    */   public void setCanceled(boolean cancel) {
/* 73 */     this.delegate.setCanceled(cancel);
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 78 */     this.delegate.setDescription(description);
/*    */   }
/*    */   
/*    */   public void setTask(InternationalString task) {
/* 82 */     this.delegate.setTask(task);
/*    */   }
/*    */   
/*    */   public void warningOccurred(String source, String location, String warning) {
/* 86 */     this.delegate.warningOccurred(source, location, warning);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\DelegateProgressListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */