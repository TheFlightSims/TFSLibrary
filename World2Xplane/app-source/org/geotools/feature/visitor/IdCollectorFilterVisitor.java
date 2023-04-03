/*    */ package org.geotools.feature.visitor;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.geotools.filter.visitor.DefaultFilterVisitor;
/*    */ import org.opengis.filter.Id;
/*    */ 
/*    */ public class IdCollectorFilterVisitor extends DefaultFilterVisitor {
/* 34 */   public static final IdCollectorFilterVisitor ID_COLLECTOR = new IdCollectorFilterVisitor(true);
/*    */   
/* 35 */   public static final IdCollectorFilterVisitor IDENTIFIER_COLLECTOR = new IdCollectorFilterVisitor(false);
/*    */   
/*    */   private final boolean mCollectStringIds;
/*    */   
/*    */   protected IdCollectorFilterVisitor() {
/* 42 */     this.mCollectStringIds = true;
/*    */   }
/*    */   
/*    */   protected IdCollectorFilterVisitor(boolean collectStringIds) {
/* 46 */     this.mCollectStringIds = collectStringIds;
/*    */   }
/*    */   
/*    */   public Object visit(Id filter, Object data) {
/* 53 */     if (this.mCollectStringIds) {
/* 54 */       Set set1 = (Set)data;
/* 55 */       set1.addAll(filter.getIDs());
/* 56 */       return set1;
/*    */     } 
/* 58 */     Set set = (Set)data;
/* 59 */     set.addAll(filter.getIdentifiers());
/* 60 */     return set;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\IdCollectorFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */