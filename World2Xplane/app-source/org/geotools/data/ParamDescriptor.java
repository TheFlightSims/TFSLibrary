/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.geotools.parameter.DefaultParameterDescriptor;
/*     */ import org.geotools.parameter.FloatParameter;
/*     */ import org.geotools.parameter.Parameter;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ 
/*     */ class ParamDescriptor extends DefaultParameterDescriptor {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   DataAccessFactory.Param param;
/*     */   
/*     */   public ParamDescriptor(DataAccessFactory.Param param) {
/* 194 */     super((ParameterDescriptor)DefaultParameterDescriptor.create(param.key, (CharSequence)param.description, param.type, param.sample, param.required));
/* 195 */     this.param = param;
/*     */   }
/*     */   
/*     */   public ParameterValue createValue() {
/* 198 */     if (double.class.equals(getValueClass()))
/* 199 */       return (ParameterValue)new FloatParameter((ParameterDescriptor)this) {
/*     */           protected Object valueOf(String text) throws IOException {
/* 201 */             return ParamDescriptor.this.param.handle(text);
/*     */           }
/*     */         }; 
/* 205 */     return (ParameterValue)new Parameter((ParameterDescriptor)this) {
/*     */         protected Object valueOf(String text) throws IOException {
/* 207 */           return ParamDescriptor.this.param.handle(text);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ParamDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */