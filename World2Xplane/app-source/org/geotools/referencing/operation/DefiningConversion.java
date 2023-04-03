/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ 
/*     */ public class DefiningConversion extends DefaultConversion {
/*     */   private static final long serialVersionUID = 7399026512478064721L;
/*     */   
/*     */   private final ParameterValueGroup parameters;
/*     */   
/*     */   public DefiningConversion(String name, ParameterValueGroup parameters) {
/*  68 */     this(Collections.singletonMap("name", name), getOperationMethod(parameters), parameters);
/*     */   }
/*     */   
/*     */   private static OperationMethod getOperationMethod(ParameterValueGroup parameters) {
/*  76 */     ensureNonNull("parameters", parameters);
/*  77 */     ParameterDescriptorGroup descriptor = parameters.getDescriptor();
/*  78 */     return new DefaultOperationMethod(getProperties((IdentifiedObject)descriptor, null), 2, 2, descriptor);
/*     */   }
/*     */   
/*     */   public DefiningConversion(Map<String, ?> properties, OperationMethod method, ParameterValueGroup parameters) {
/*  93 */     super(properties, (CoordinateReferenceSystem)null, (CoordinateReferenceSystem)null, (MathTransform)null, method);
/*  94 */     ensureNonNull("parameters", parameters);
/*  95 */     this.parameters = parameters.clone();
/*     */   }
/*     */   
/*     */   public DefiningConversion(Map<String, ?> properties, OperationMethod method, MathTransform transform) {
/* 113 */     super(properties, (CoordinateReferenceSystem)null, (CoordinateReferenceSystem)null, transform, method);
/* 114 */     this.parameters = null;
/*     */   }
/*     */   
/*     */   void validate() throws IllegalArgumentException {
/* 124 */     if (this.transform == null)
/* 125 */       super.validate(); 
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 134 */     return (this.parameters != null) ? this.parameters.clone() : super.getParameterValues();
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 142 */     String name = super.formatWKT(formatter);
/* 143 */     formatter.append((GeneralParameterValue)this.parameters);
/* 144 */     return name;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\DefiningConversion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */