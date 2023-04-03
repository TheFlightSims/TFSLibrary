/*    */ package org.opengis.temporal;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.opengis.annotation.Specification;
/*    */ import org.opengis.annotation.UML;
/*    */ import org.opengis.util.CodeList;
/*    */ 
/*    */ @UML(identifier = "TM_IndeterminateValue", specification = Specification.ISO_19108)
/*    */ public final class IndeterminateValue extends CodeList<IndeterminateValue> {
/*    */   private static final long serialVersionUID = 1399031922917754577L;
/*    */   
/* 45 */   private static final List<IndeterminateValue> VALUES = new ArrayList<IndeterminateValue>(4);
/*    */   
/* 47 */   public static final IndeterminateValue UNKNOWN = new IndeterminateValue("UNKNOWN");
/*    */   
/* 48 */   public static final IndeterminateValue NOW = new IndeterminateValue("NOW");
/*    */   
/* 49 */   public static final IndeterminateValue BEFORE = new IndeterminateValue("BEFORE");
/*    */   
/* 50 */   public static final IndeterminateValue AFTER = new IndeterminateValue("AFTER");
/*    */   
/*    */   private IndeterminateValue(String name) {
/* 59 */     super(name, VALUES);
/*    */   }
/*    */   
/*    */   public static IndeterminateValue[] values() {
/* 68 */     synchronized (VALUES) {
/* 69 */       return VALUES.<IndeterminateValue>toArray(new IndeterminateValue[VALUES.size()]);
/*    */     } 
/*    */   }
/*    */   
/*    */   public IndeterminateValue[] family() {
/* 77 */     return values();
/*    */   }
/*    */   
/*    */   public static IndeterminateValue valueOf(String code) {
/* 88 */     return (IndeterminateValue)valueOf(IndeterminateValue.class, code);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\IndeterminateValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */