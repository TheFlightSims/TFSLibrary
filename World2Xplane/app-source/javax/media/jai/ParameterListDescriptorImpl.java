/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.CaselessStringArrayTable;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.util.Range;
/*     */ 
/*     */ public class ParameterListDescriptorImpl implements ParameterListDescriptor, Serializable {
/*     */   private int numParams;
/*     */   
/*     */   private String[] paramNames;
/*     */   
/*     */   private Class[] paramClasses;
/*     */   
/*     */   private Object[] paramDefaults;
/*     */   
/*     */   private Object[] validParamValues;
/*     */   
/*     */   private CaselessStringArrayTable paramIndices;
/*     */   
/*     */   private Object descriptor;
/*     */   
/*     */   private boolean validParamsInitialized = false;
/*     */   
/*     */   public static Set getEnumeratedValues(Object descriptor, Class paramClass) {
/*  95 */     if (descriptor == null || paramClass == null)
/*  96 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  99 */     if (!EnumeratedParameter.class.isAssignableFrom(paramClass))
/* 100 */       throw new IllegalArgumentException(JaiI18N.formatMsg("ParameterListDescriptorImpl10", new Object[] { paramClass.getName() })); 
/* 104 */     Field[] fields = descriptor.getClass().getDeclaredFields();
/* 106 */     if (fields == null)
/* 107 */       return null; 
/* 110 */     int numFields = fields.length;
/* 111 */     Set valueSet = null;
/* 115 */     for (int j = 0; j < numFields; j++) {
/* 116 */       Field field = fields[j];
/* 117 */       int modifiers = field.getModifiers();
/* 118 */       if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
/* 121 */         Object fieldValue = null;
/*     */         try {
/* 123 */           fieldValue = field.get(null);
/* 124 */         } catch (Exception e) {}
/* 127 */         if (paramClass.isInstance(fieldValue)) {
/* 128 */           if (valueSet == null)
/* 129 */             valueSet = new HashSet(); 
/* 132 */           if (valueSet.contains(fieldValue))
/* 137 */             throw new UnsupportedOperationException(JaiI18N.getString("ParameterListDescriptorImpl0")); 
/* 141 */           valueSet.add(fieldValue);
/*     */         } 
/*     */       } 
/*     */     } 
/* 146 */     return valueSet;
/*     */   }
/*     */   
/*     */   private Object getValidParamValue(int index) {
/* 156 */     if (this.validParamsInitialized)
/* 157 */       return this.validParamValues[index]; 
/* 159 */     synchronized (this) {
/* 160 */       if (this.validParamValues == null)
/* 161 */         this.validParamValues = new Object[this.numParams]; 
/* 164 */       Class enumeratedClass = EnumeratedParameter.class;
/* 166 */       for (int i = 0; i < this.numParams; i++) {
/* 167 */         if (this.validParamValues[i] == null)
/* 170 */           if (enumeratedClass.isAssignableFrom(this.paramClasses[i]))
/* 171 */             this.validParamValues[i] = getEnumeratedValues(this.descriptor, this.paramClasses[i]);  
/*     */       } 
/*     */     } 
/* 177 */     this.validParamsInitialized = true;
/* 179 */     return this.validParamValues[index];
/*     */   }
/*     */   
/*     */   public ParameterListDescriptorImpl() {
/* 186 */     this.numParams = 0;
/* 187 */     this.paramNames = null;
/* 188 */     this.paramClasses = null;
/* 189 */     this.paramDefaults = null;
/* 190 */     this.paramIndices = new CaselessStringArrayTable();
/* 191 */     this.validParamValues = null;
/*     */   }
/*     */   
/*     */   public ParameterListDescriptorImpl(Object descriptor, String[] paramNames, Class[] paramClasses, Object[] paramDefaults, Object[] validParamValues) {
/* 239 */     int numParams = (paramNames == null) ? 0 : paramNames.length;
/* 241 */     if (paramDefaults != null && paramDefaults.length != numParams)
/* 242 */       throw new IllegalArgumentException("paramDefaults" + JaiI18N.getString("ParameterListDescriptorImpl1")); 
/* 245 */     if (validParamValues != null && validParamValues.length != numParams)
/* 246 */       throw new IllegalArgumentException("validParamValues" + JaiI18N.getString("ParameterListDescriptorImpl2")); 
/* 249 */     this.descriptor = descriptor;
/* 251 */     if (numParams == 0) {
/* 253 */       if (paramClasses != null && paramClasses.length != 0)
/* 254 */         throw new IllegalArgumentException("paramClasses" + JaiI18N.getString("ParameterListDescriptorImpl3")); 
/* 257 */       this.numParams = 0;
/* 258 */       this.paramNames = null;
/* 259 */       this.paramClasses = null;
/* 260 */       this.paramDefaults = null;
/* 261 */       this.paramIndices = new CaselessStringArrayTable();
/* 262 */       this.validParamValues = null;
/*     */     } else {
/* 266 */       if (paramClasses == null || paramClasses.length != numParams)
/* 267 */         throw new IllegalArgumentException("paramClasses" + JaiI18N.getString("ParameterListDescriptorImpl3")); 
/* 270 */       this.numParams = numParams;
/* 271 */       this.paramNames = paramNames;
/* 272 */       this.paramClasses = paramClasses;
/* 273 */       this.validParamValues = validParamValues;
/* 279 */       if (paramDefaults == null) {
/* 280 */         this.paramDefaults = new Object[numParams];
/* 282 */         for (int i = 0; i < numParams; i++)
/* 283 */           this.paramDefaults[i] = ParameterListDescriptor.NO_PARAMETER_DEFAULT; 
/*     */       } else {
/* 287 */         this.paramDefaults = paramDefaults;
/* 289 */         for (int i = 0; i < numParams; i++) {
/* 290 */           if (paramDefaults[i] != null && paramDefaults[i] != ParameterListDescriptor.NO_PARAMETER_DEFAULT)
/* 295 */             if (!paramClasses[i].isInstance(paramDefaults[i]))
/* 296 */               throw new IllegalArgumentException(JaiI18N.formatMsg("ParameterListDescriptorImpl4", new Object[] { paramDefaults[i].getClass().getName(), paramClasses[i].getName(), paramNames[i] }));  
/*     */         } 
/*     */       } 
/* 309 */       if (validParamValues != null) {
/* 311 */         Class enumeratedClass = EnumeratedParameter.class;
/* 313 */         for (int i = 0; i < numParams; i++) {
/* 315 */           if (validParamValues[i] != null)
/* 318 */             if (enumeratedClass.isAssignableFrom(paramClasses[i])) {
/* 322 */               if (!(validParamValues[i] instanceof Set))
/* 323 */                 throw new IllegalArgumentException(JaiI18N.formatMsg("ParameterListDescriptorImpl5", new Object[] { paramNames[i] })); 
/* 327 */             } else if (validParamValues[i] instanceof Range) {
/* 329 */               Range range = (Range)validParamValues[i];
/* 333 */               if (!paramClasses[i].isAssignableFrom(range.getElementClass()))
/* 335 */                 throw new IllegalArgumentException(JaiI18N.formatMsg("ParameterListDescriptorImpl6", new Object[] { range.getElementClass().getName(), paramClasses[i].getName(), paramNames[i] })); 
/* 346 */             } else if (!paramClasses[i].isInstance(validParamValues[i])) {
/* 347 */               throw new IllegalArgumentException(JaiI18N.formatMsg("ParameterListDescriptorImpl7", new Object[] { validParamValues[i].getClass().getName(), paramClasses[i].getName(), paramNames[i] }));
/*     */             }  
/*     */         } 
/*     */       } 
/* 357 */       this.paramIndices = new CaselessStringArrayTable(paramNames);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getNumParameters() {
/* 365 */     return this.numParams;
/*     */   }
/*     */   
/*     */   public Class[] getParamClasses() {
/* 374 */     return this.paramClasses;
/*     */   }
/*     */   
/*     */   public String[] getParamNames() {
/* 383 */     return this.paramNames;
/*     */   }
/*     */   
/*     */   public Object[] getParamDefaults() {
/* 394 */     return this.paramDefaults;
/*     */   }
/*     */   
/*     */   public Object getParamDefaultValue(String parameterName) {
/* 409 */     return this.paramDefaults[this.paramIndices.indexOf(parameterName)];
/*     */   }
/*     */   
/*     */   public Range getParamValueRange(String parameterName) {
/* 426 */     Object values = getValidParamValue(this.paramIndices.indexOf(parameterName));
/* 428 */     if (values == null || values instanceof Range)
/* 429 */       return (Range)values; 
/* 431 */     return null;
/*     */   }
/*     */   
/*     */   public String[] getEnumeratedParameterNames() {
/* 443 */     Vector v = new Vector();
/* 445 */     for (int i = 0; i < this.numParams; i++) {
/* 446 */       if (EnumeratedParameter.class.isAssignableFrom(this.paramClasses[i]))
/* 447 */         v.add(this.paramNames[i]); 
/*     */     } 
/* 450 */     if (v.size() <= 0)
/* 451 */       return null; 
/* 453 */     return v.<String>toArray(new String[0]);
/*     */   }
/*     */   
/*     */   public EnumeratedParameter[] getEnumeratedParameterValues(String parameterName) {
/* 476 */     int i = this.paramIndices.indexOf(parameterName);
/* 478 */     if (!EnumeratedParameter.class.isAssignableFrom(this.paramClasses[i]))
/* 479 */       throw new IllegalArgumentException(parameterName + ":" + JaiI18N.getString("ParameterListDescriptorImpl8")); 
/* 482 */     Set enumSet = (Set)getValidParamValue(i);
/* 484 */     if (enumSet == null)
/* 485 */       return null; 
/* 487 */     return (EnumeratedParameter[])enumSet.toArray((Object[])new EnumeratedParameter[0]);
/*     */   }
/*     */   
/*     */   public boolean isParameterValueValid(String parameterName, Object value) {
/* 508 */     int index = this.paramIndices.indexOf(parameterName);
/* 510 */     if (value == null && this.paramDefaults[index] == null)
/* 511 */       return true; 
/* 515 */     if (value != null && !this.paramClasses[index].isInstance(value))
/* 516 */       throw new IllegalArgumentException(JaiI18N.formatMsg("ParameterListDescriptorImpl9", new Object[] { value.getClass().getName(), this.paramClasses[index].getName(), parameterName })); 
/* 523 */     Object validValues = getValidParamValue(index);
/* 526 */     if (validValues == null)
/* 527 */       return true; 
/* 530 */     if (validValues instanceof Range)
/* 531 */       return ((Range)validValues).contains((Comparable)value); 
/* 535 */     if (validValues instanceof Set)
/* 536 */       return ((Set)validValues).contains(value); 
/* 539 */     return (value == validValues);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ParameterListDescriptorImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */