/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.parameter.DefaultParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ 
/*     */ final class ModifiedParameterDescriptor extends DefaultParameterDescriptor {
/*     */   private static final long serialVersionUID = -616587615222354457L;
/*     */   
/*     */   private final ParameterDescriptor original;
/*     */   
/*     */   private final Double defaultValue;
/*     */   
/*     */   public ModifiedParameterDescriptor(ParameterDescriptor original, double defaultValue) {
/*  65 */     super(original);
/*  66 */     this.original = original;
/*  67 */     this.defaultValue = Double.valueOf(defaultValue);
/*     */   }
/*     */   
/*     */   public Object getDefaultValue() {
/*  75 */     return this.defaultValue;
/*     */   }
/*     */   
/*     */   public static boolean contains(Collection<GeneralParameterDescriptor> set, ParameterDescriptor descriptor) {
/*  88 */     if (descriptor instanceof ModifiedParameterDescriptor)
/*  89 */       descriptor = ((ModifiedParameterDescriptor)descriptor).original; 
/*  91 */     for (GeneralParameterDescriptor candidate : set) {
/*     */       ParameterDescriptor parameterDescriptor;
/*  92 */       if (candidate instanceof ModifiedParameterDescriptor)
/*  93 */         parameterDescriptor = ((ModifiedParameterDescriptor)candidate).original; 
/*  95 */       if (descriptor.equals(parameterDescriptor))
/*  96 */         return true; 
/*     */     } 
/*  99 */     assert !set.contains(descriptor);
/* 100 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\ModifiedParameterDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */