/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ 
/*     */ public abstract class AbstractParameterDescriptor extends AbstractIdentifiedObject implements GeneralParameterDescriptor {
/*     */   private static final long serialVersionUID = -2630644278783845276L;
/*     */   
/*     */   private final int minimumOccurs;
/*     */   
/*     */   protected AbstractParameterDescriptor(GeneralParameterDescriptor descriptor) {
/*  65 */     super((IdentifiedObject)descriptor);
/*  66 */     this.minimumOccurs = descriptor.getMinimumOccurs();
/*     */   }
/*     */   
/*     */   protected AbstractParameterDescriptor(Map<String, ?> properties, int minimumOccurs, int maximumOccurs) {
/*  85 */     super(properties);
/*  86 */     this.minimumOccurs = minimumOccurs;
/*  87 */     if (minimumOccurs < 0 || maximumOccurs < minimumOccurs)
/*  88 */       throw new IllegalArgumentException(Errors.format(14, Integer.valueOf(minimumOccurs), Integer.valueOf(maximumOccurs))); 
/*     */   }
/*     */   
/*     */   public abstract GeneralParameterValue createValue();
/*     */   
/*     */   public int getMinimumOccurs() {
/* 114 */     return this.minimumOccurs;
/*     */   }
/*     */   
/*     */   public abstract int getMaximumOccurs();
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 137 */     if (super.equals(object, compareMetadata)) {
/* 138 */       AbstractParameterDescriptor that = (AbstractParameterDescriptor)object;
/* 139 */       return (this.minimumOccurs == that.minimumOccurs);
/*     */     } 
/* 141 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 152 */     return 0x58ACDC64 ^ this.minimumOccurs;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 166 */     formatter.setInvalidWKT(GeneralParameterDescriptor.class);
/* 167 */     return "PARAMETER";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\AbstractParameterDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */