/*    */ package org.geotools.factory;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.geotools.util.Utilities;
/*    */ 
/*    */ final class FactoryComparator {
/*    */   private final Factory f1;
/*    */   
/*    */   private final Factory f2;
/*    */   
/*    */   FactoryComparator(Factory f1, Factory f2) {
/* 43 */     this.f1 = f1;
/* 44 */     this.f2 = f2;
/*    */   }
/*    */   
/*    */   boolean compare(Set<FactoryComparator> done) {
/* 53 */     if (done.add(this)) {
/* 54 */       Map<RenderingHints.Key, ?> m1 = this.f1.getImplementationHints();
/* 55 */       Map<RenderingHints.Key, ?> m2 = this.f2.getImplementationHints();
/* 56 */       if (m1.size() != m2.size())
/* 57 */         return false; 
/* 59 */       for (Map.Entry<RenderingHints.Key, ?> entry : m1.entrySet()) {
/* 60 */         Object key = entry.getKey();
/* 61 */         Object v1 = entry.getValue();
/* 62 */         Object v2 = m2.get(key);
/* 63 */         if (v1 == v2)
/*    */           continue; 
/* 64 */         if (v1 instanceof Factory) {
/* 65 */           if (v2 == null || !v1.getClass().equals(v2.getClass()) || !(new FactoryComparator((Factory)v1, (Factory)v2)).compare(done))
/* 68 */             return false; 
/*    */           continue;
/*    */         } 
/* 70 */         if (!Utilities.equals(v1, v2))
/* 71 */           return false; 
/*    */       } 
/*    */     } 
/* 75 */     return true;
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 84 */     if (object instanceof FactoryComparator) {
/* 85 */       FactoryComparator that = (FactoryComparator)object;
/* 86 */       return ((this.f1 == that.f1 && this.f2 == that.f2) || (this.f1 == that.f2 && this.f2 == that.f1));
/*    */     } 
/* 89 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 99 */     return System.identityHashCode(this.f1) + System.identityHashCode(this.f2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\FactoryComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */