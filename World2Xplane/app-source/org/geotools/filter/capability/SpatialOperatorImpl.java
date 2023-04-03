/*    */ package org.geotools.filter.capability;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ import org.opengis.filter.capability.GeometryOperand;
/*    */ import org.opengis.filter.capability.SpatialOperator;
/*    */ 
/*    */ public class SpatialOperatorImpl extends OperatorImpl implements SpatialOperator {
/*    */   HashSet<GeometryOperand> geometryOperands;
/*    */   
/*    */   public SpatialOperatorImpl(String name) {
/* 41 */     super(name);
/* 42 */     this.geometryOperands = new HashSet<GeometryOperand>();
/*    */   }
/*    */   
/*    */   public SpatialOperatorImpl(String name, Collection<GeometryOperand> geometryOperands) {
/* 45 */     super(name);
/* 46 */     this.geometryOperands = new HashSet<GeometryOperand>(geometryOperands);
/*    */   }
/*    */   
/*    */   public SpatialOperatorImpl(String name, GeometryOperand[] geometryOperands) {
/* 49 */     super(name);
/* 50 */     this.geometryOperands = new HashSet<GeometryOperand>();
/* 51 */     if (geometryOperands != null)
/* 52 */       this.geometryOperands.addAll(Arrays.asList(geometryOperands)); 
/*    */   }
/*    */   
/*    */   public SpatialOperatorImpl(SpatialOperator copy) {
/* 56 */     this(copy.getName());
/* 57 */     this.geometryOperands = new HashSet<GeometryOperand>();
/* 58 */     if (copy.getGeometryOperands() != null)
/* 59 */       this.geometryOperands.addAll(copy.getGeometryOperands()); 
/*    */   }
/*    */   
/*    */   public void setGeometryOperands(Collection<GeometryOperand> geometryOperands) {
/* 63 */     this.geometryOperands = new HashSet<GeometryOperand>(geometryOperands);
/*    */   }
/*    */   
/*    */   public Collection<GeometryOperand> getGeometryOperands() {
/* 66 */     return this.geometryOperands;
/*    */   }
/*    */   
/*    */   public void addAll(SpatialOperator copy) {
/* 70 */     if (copy == null)
/*    */       return; 
/* 71 */     if (copy.getGeometryOperands() != null)
/* 72 */       for (GeometryOperand operand : copy.getGeometryOperands())
/* 73 */         this.geometryOperands.add(operand);  
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\SpatialOperatorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */