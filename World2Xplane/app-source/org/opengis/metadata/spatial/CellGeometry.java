/*    */ package org.opengis.metadata.spatial;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.opengis.annotation.Obligation;
/*    */ import org.opengis.annotation.Specification;
/*    */ import org.opengis.annotation.UML;
/*    */ import org.opengis.util.CodeList;
/*    */ 
/*    */ @UML(identifier = "MD_CellGeometryCode", specification = Specification.ISO_19115)
/*    */ public final class CellGeometry extends CodeList<CellGeometry> {
/*    */   private static final long serialVersionUID = -1901029875497457189L;
/*    */   
/* 42 */   private static final List<CellGeometry> VALUES = new ArrayList<CellGeometry>(2);
/*    */   
/*    */   @UML(identifier = "point", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 48 */   public static final CellGeometry POINT = new CellGeometry("POINT");
/*    */   
/*    */   @UML(identifier = "area", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 54 */   public static final CellGeometry AREA = new CellGeometry("AREA");
/*    */   
/*    */   private CellGeometry(String name) {
/* 63 */     super(name, VALUES);
/*    */   }
/*    */   
/*    */   public static CellGeometry[] values() {
/* 72 */     synchronized (VALUES) {
/* 73 */       return VALUES.<CellGeometry>toArray(new CellGeometry[VALUES.size()]);
/*    */     } 
/*    */   }
/*    */   
/*    */   public CellGeometry[] family() {
/* 81 */     return values();
/*    */   }
/*    */   
/*    */   public static CellGeometry valueOf(String code) {
/* 92 */     return (CellGeometry)valueOf(CellGeometry.class, code);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\spatial\CellGeometry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */