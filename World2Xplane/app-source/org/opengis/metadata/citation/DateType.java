/*    */ package org.opengis.metadata.citation;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.opengis.annotation.Obligation;
/*    */ import org.opengis.annotation.Specification;
/*    */ import org.opengis.annotation.UML;
/*    */ import org.opengis.util.CodeList;
/*    */ 
/*    */ @UML(identifier = "CI_DateTypeCode", specification = Specification.ISO_19115)
/*    */ public final class DateType extends CodeList<DateType> {
/*    */   private static final long serialVersionUID = 9031571038833329544L;
/*    */   
/* 43 */   private static final List<DateType> VALUES = new ArrayList<DateType>(3);
/*    */   
/*    */   @UML(identifier = "creation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 49 */   public static final DateType CREATION = new DateType("CREATION");
/*    */   
/*    */   @UML(identifier = "publication", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 55 */   public static final DateType PUBLICATION = new DateType("PUBLICATION");
/*    */   
/*    */   @UML(identifier = "revision", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 61 */   public static final DateType REVISION = new DateType("REVISION");
/*    */   
/*    */   private DateType(String name) {
/* 70 */     super(name, VALUES);
/*    */   }
/*    */   
/*    */   public static DateType[] values() {
/* 79 */     synchronized (VALUES) {
/* 80 */       return VALUES.<DateType>toArray(new DateType[VALUES.size()]);
/*    */     } 
/*    */   }
/*    */   
/*    */   public DateType[] family() {
/* 88 */     return values();
/*    */   }
/*    */   
/*    */   public static DateType valueOf(String code) {
/* 99 */     return (DateType)valueOf(DateType.class, code);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\DateType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */