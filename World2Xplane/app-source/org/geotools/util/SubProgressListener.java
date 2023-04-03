/*    */ package org.geotools.util;
/*    */ 
/*    */ import org.opengis.util.ProgressListener;
/*    */ 
/*    */ public class SubProgressListener extends DelegateProgressListener {
/*    */   float start;
/*    */   
/*    */   float amount;
/*    */   
/*    */   float scale;
/*    */   
/*    */   float progress;
/*    */   
/*    */   public SubProgressListener(ProgressListener progress, float amount) {
/* 60 */     super(progress);
/* 61 */     this.start = progress.getProgress();
/* 62 */     this.amount = (amount > 0.0F) ? amount : 0.0F;
/* 63 */     this.scale = this.amount / 100.0F;
/*    */   }
/*    */   
/*    */   public void started() {
/* 66 */     this.progress = 0.0F;
/*    */   }
/*    */   
/*    */   public void complete() {
/* 69 */     this.delegate.progress(this.start + this.amount);
/* 70 */     this.progress = 100.0F;
/*    */   }
/*    */   
/*    */   public float getProgress() {
/* 73 */     return this.progress;
/*    */   }
/*    */   
/*    */   public void progress(float progress) {
/* 76 */     this.progress = progress;
/* 77 */     super.progress(this.start + this.scale * progress);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\SubProgressListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */