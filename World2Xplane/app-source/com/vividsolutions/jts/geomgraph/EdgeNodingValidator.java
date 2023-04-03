/*    */ package com.vividsolutions.jts.geomgraph;
/*    */ 
/*    */ import com.vividsolutions.jts.noding.BasicSegmentString;
/*    */ import com.vividsolutions.jts.noding.FastNodingValidator;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class EdgeNodingValidator {
/*    */   private FastNodingValidator nv;
/*    */   
/*    */   public static void checkValid(Collection edges) {
/* 58 */     EdgeNodingValidator validator = new EdgeNodingValidator(edges);
/* 59 */     validator.checkValid();
/*    */   }
/*    */   
/*    */   public static Collection toSegmentStrings(Collection edges) {
/* 65 */     Collection<BasicSegmentString> segStrings = new ArrayList();
/* 66 */     for (Iterator<Edge> i = edges.iterator(); i.hasNext(); ) {
/* 67 */       Edge e = i.next();
/* 68 */       segStrings.add(new BasicSegmentString(e.getCoordinates(), e));
/*    */     } 
/* 70 */     return segStrings;
/*    */   }
/*    */   
/*    */   public EdgeNodingValidator(Collection edges) {
/* 82 */     this.nv = new FastNodingValidator(toSegmentStrings(edges));
/*    */   }
/*    */   
/*    */   public void checkValid() {
/* 94 */     this.nv.checkValid();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\EdgeNodingValidator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */