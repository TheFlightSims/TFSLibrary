/*    */ package org.openstreetmap.osmosis.core.lifecycle;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ReleasableContainer implements Releasable {
/* 26 */   private List<Releasable> objects = new ArrayList<Releasable>();
/*    */   
/*    */   public <T extends Releasable> T add(T object) {
/* 41 */     this.objects.add((Releasable)object);
/* 43 */     return object;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 51 */     this.objects.clear();
/*    */   }
/*    */   
/*    */   public void release() {
/* 60 */     for (Releasable object : this.objects)
/* 61 */       object.release(); 
/* 63 */     this.objects.clear();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\lifecycle\ReleasableContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */