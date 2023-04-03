/*    */ package org.openstreetmap.osmosis.core.lifecycle;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CompletableContainer implements Completable {
/* 23 */   private List<Completable> objects = new ArrayList<Completable>();
/*    */   
/*    */   public <T extends Completable> T add(T object) {
/* 38 */     this.objects.add((Completable)object);
/* 40 */     return object;
/*    */   }
/*    */   
/*    */   public void complete() {
/* 49 */     for (Completable object : this.objects)
/* 50 */       object.complete(); 
/*    */   }
/*    */   
/*    */   public void release() {
/* 60 */     for (Releasable object : this.objects)
/* 61 */       object.release(); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\lifecycle\CompletableContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */