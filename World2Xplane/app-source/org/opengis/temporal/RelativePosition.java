/*    */ package org.opengis.temporal;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.opengis.annotation.Specification;
/*    */ import org.opengis.annotation.UML;
/*    */ import org.opengis.util.CodeList;
/*    */ 
/*    */ @UML(identifier = "TM_RelativePosition", specification = Specification.ISO_19108)
/*    */ public final class RelativePosition extends CodeList<RelativePosition> {
/*    */   private static final long serialVersionUID = -2918422623747953495L;
/*    */   
/* 46 */   private static final List<RelativePosition> VALUES = new ArrayList<RelativePosition>(13);
/*    */   
/* 48 */   public static final RelativePosition BEFORE = new RelativePosition("BEFORE");
/*    */   
/* 49 */   public static final RelativePosition AFTER = new RelativePosition("AFTER");
/*    */   
/* 50 */   public static final RelativePosition BEGINS = new RelativePosition("BEGINS");
/*    */   
/* 51 */   public static final RelativePosition ENDS = new RelativePosition("ENDS");
/*    */   
/* 52 */   public static final RelativePosition DURING = new RelativePosition("DURING");
/*    */   
/* 53 */   public static final RelativePosition EQUALS = new RelativePosition("EQUALS");
/*    */   
/* 54 */   public static final RelativePosition CONTAINS = new RelativePosition("CONTAINS");
/*    */   
/* 55 */   public static final RelativePosition OVERLAPS = new RelativePosition("OVERLAPS");
/*    */   
/* 56 */   public static final RelativePosition MEETS = new RelativePosition("MEETS");
/*    */   
/* 57 */   public static final RelativePosition OVERLAPPED_BY = new RelativePosition("OVERLAPPED_BY");
/*    */   
/* 58 */   public static final RelativePosition MET_BY = new RelativePosition("MET_BY");
/*    */   
/* 59 */   public static final RelativePosition BEGUN_BY = new RelativePosition("BEGUN_BY");
/*    */   
/* 60 */   public static final RelativePosition ENDED_BY = new RelativePosition("ENDED_BY");
/*    */   
/*    */   private RelativePosition(String name) {
/* 69 */     super(name, VALUES);
/*    */   }
/*    */   
/*    */   public static RelativePosition[] values() {
/* 78 */     synchronized (VALUES) {
/* 79 */       return VALUES.<RelativePosition>toArray(new RelativePosition[VALUES.size()]);
/*    */     } 
/*    */   }
/*    */   
/*    */   public RelativePosition[] family() {
/* 87 */     return values();
/*    */   }
/*    */   
/*    */   public static RelativePosition valueOf(String code) {
/* 98 */     return (RelativePosition)valueOf(RelativePosition.class, code);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\RelativePosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */