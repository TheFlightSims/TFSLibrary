/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.DerivedCRS;
/*     */ import org.opengis.referencing.crs.GeneralDerivedCRS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ 
/*     */ public class DefaultDerivedCRS extends AbstractDerivedCRS implements DerivedCRS {
/*     */   private static final long serialVersionUID = -8149602276542469876L;
/*     */   
/*     */   public DefaultDerivedCRS(DerivedCRS crs) {
/*  68 */     super((GeneralDerivedCRS)crs);
/*     */   }
/*     */   
/*     */   public DefaultDerivedCRS(String name, CoordinateReferenceSystem base, MathTransform baseToDerived, CoordinateSystem derivedCS) throws MismatchedDimensionException {
/*  96 */     this(Collections.singletonMap("name", name), base, baseToDerived, derivedCS);
/*     */   }
/*     */   
/*     */   public DefaultDerivedCRS(Map<String, ?> properties, CoordinateReferenceSystem base, MathTransform baseToDerived, CoordinateSystem derivedCS) throws MismatchedDimensionException {
/* 130 */     super(properties, base, baseToDerived, derivedCS);
/*     */   }
/*     */   
/*     */   public DefaultDerivedCRS(Map<String, ?> properties, OperationMethod method, CoordinateReferenceSystem base, MathTransform baseToDerived, CoordinateSystem derivedCS) throws MismatchedDimensionException {
/* 161 */     super(properties, method, base, baseToDerived, derivedCS);
/*     */   }
/*     */   
/*     */   public DefaultDerivedCRS(Map<String, ?> properties, Conversion conversionFromBase, CoordinateReferenceSystem base, MathTransform baseToDerived, CoordinateSystem derivedCS) throws MismatchedDimensionException {
/* 187 */     super(properties, conversionFromBase, base, baseToDerived, derivedCS);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 198 */     return 0x6DF8A90C ^ super.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\DefaultDerivedCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */