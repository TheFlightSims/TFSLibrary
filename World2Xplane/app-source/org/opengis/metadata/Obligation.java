/*    */ package org.opengis.metadata;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.opengis.annotation.Specification;
/*    */ import org.opengis.annotation.UML;
/*    */ import org.opengis.util.CodeList;
/*    */ 
/*    */ @UML(identifier = "MD_ObligationCode", specification = Specification.ISO_19115)
/*    */ public final class Obligation extends CodeList<Obligation> {
/*    */   private static final long serialVersionUID = -2135167450448770693L;
/*    */   
/* 41 */   private static final List<Obligation> VALUES = new ArrayList<Obligation>(3);
/*    */   
/*    */   @UML(identifier = "mandatory", obligation = org.opengis.annotation.Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 47 */   public static final Obligation MANDATORY = new Obligation("MANDATORY");
/*    */   
/*    */   @UML(identifier = "optional", obligation = org.opengis.annotation.Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 53 */   public static final Obligation OPTIONAL = new Obligation("OPTIONAL");
/*    */   
/*    */   @UML(identifier = "conditional", obligation = org.opengis.annotation.Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 59 */   public static final Obligation CONDITIONAL = new Obligation("CONDITIONAL");
/*    */   
/*    */   private Obligation(String name) {
/* 68 */     super(name, VALUES);
/*    */   }
/*    */   
/*    */   public static Obligation[] values() {
/* 77 */     synchronized (VALUES) {
/* 78 */       return VALUES.<Obligation>toArray(new Obligation[VALUES.size()]);
/*    */     } 
/*    */   }
/*    */   
/*    */   public Obligation[] family() {
/* 86 */     return values();
/*    */   }
/*    */   
/*    */   public static Obligation valueOf(String code) {
/* 97 */     return (Obligation)valueOf(Obligation.class, code);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\Obligation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */