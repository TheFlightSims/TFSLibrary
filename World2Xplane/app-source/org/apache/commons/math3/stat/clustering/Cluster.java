/*    */ package org.apache.commons.math3.stat.clustering;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Cluster<T extends Clusterable<T>> implements Serializable {
/*    */   private static final long serialVersionUID = -3442297081515880464L;
/*    */   
/*    */   private final List<T> points;
/*    */   
/*    */   private final T center;
/*    */   
/*    */   public Cluster(T center) {
/* 46 */     this.center = center;
/* 47 */     this.points = new ArrayList<T>();
/*    */   }
/*    */   
/*    */   public void addPoint(T point) {
/* 55 */     this.points.add(point);
/*    */   }
/*    */   
/*    */   public List<T> getPoints() {
/* 63 */     return this.points;
/*    */   }
/*    */   
/*    */   public T getCenter() {
/* 71 */     return this.center;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\clustering\Cluster.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */