/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.CaselessStringArrayTable;
/*     */ import java.io.Serializable;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class ParameterListImpl implements ParameterList, Serializable {
/*     */   private ParameterListDescriptor pld;
/*     */   
/*     */   private CaselessStringArrayTable paramIndices;
/*     */   
/*     */   private Object[] paramValues;
/*     */   
/*     */   private Class[] paramClasses;
/*     */   
/*     */   public ParameterListImpl(ParameterListDescriptor descriptor) {
/*  69 */     if (descriptor == null)
/*  70 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  73 */     this.pld = descriptor;
/*  75 */     int numParams = this.pld.getNumParameters();
/*  77 */     if (numParams > 0) {
/*  79 */       Object[] paramDefaults = this.pld.getParamDefaults();
/*  81 */       this.paramClasses = this.pld.getParamClasses();
/*  82 */       this.paramIndices = new CaselessStringArrayTable(this.pld.getParamNames());
/*  83 */       this.paramValues = new Object[numParams];
/*  85 */       for (int i = 0; i < numParams; i++)
/*  86 */         this.paramValues[i] = paramDefaults[i]; 
/*     */     } else {
/*  90 */       this.paramClasses = null;
/*  91 */       this.paramIndices = null;
/*  92 */       this.paramValues = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParameterListDescriptor getParameterListDescriptor() {
/* 100 */     return this.pld;
/*     */   }
/*     */   
/*     */   private ParameterList setParameter0(String paramName, Object obj) {
/* 122 */     int index = this.paramIndices.indexOf(paramName);
/* 124 */     if (obj != null && !this.paramClasses[index].isInstance(obj))
/* 125 */       throw new IllegalArgumentException(formatMsg(JaiI18N.getString("ParameterListImpl0"), new Object[] { obj.getClass().getName(), this.paramClasses[index].getName(), paramName })); 
/* 131 */     if (!this.pld.isParameterValueValid(paramName, obj))
/* 132 */       throw new IllegalArgumentException(paramName + ":" + JaiI18N.getString("ParameterListImpl1")); 
/* 135 */     this.paramValues[index] = obj;
/* 137 */     return this;
/*     */   }
/*     */   
/*     */   public ParameterList setParameter(String paramName, byte b) {
/* 156 */     return setParameter0(paramName, new Byte(b));
/*     */   }
/*     */   
/*     */   public ParameterList setParameter(String paramName, boolean b) {
/* 175 */     return setParameter0(paramName, new Boolean(b));
/*     */   }
/*     */   
/*     */   public ParameterList setParameter(String paramName, char c) {
/* 194 */     return setParameter0(paramName, new Character(c));
/*     */   }
/*     */   
/*     */   public ParameterList setParameter(String paramName, short s) {
/* 213 */     return setParameter0(paramName, new Short(s));
/*     */   }
/*     */   
/*     */   public ParameterList setParameter(String paramName, int i) {
/* 232 */     return setParameter0(paramName, new Integer(i));
/*     */   }
/*     */   
/*     */   public ParameterList setParameter(String paramName, long l) {
/* 251 */     return setParameter0(paramName, new Long(l));
/*     */   }
/*     */   
/*     */   public ParameterList setParameter(String paramName, float f) {
/* 270 */     return setParameter0(paramName, new Float(f));
/*     */   }
/*     */   
/*     */   public ParameterList setParameter(String paramName, double d) {
/* 289 */     return setParameter0(paramName, new Double(d));
/*     */   }
/*     */   
/*     */   public ParameterList setParameter(String paramName, Object obj) {
/* 306 */     return setParameter0(paramName, obj);
/*     */   }
/*     */   
/*     */   private Object getObjectParameter0(String paramName) {
/* 321 */     Object obj = this.paramValues[this.paramIndices.indexOf(paramName)];
/* 323 */     if (obj == ParameterListDescriptor.NO_PARAMETER_DEFAULT)
/* 324 */       throw new IllegalStateException(paramName + ":" + JaiI18N.getString("ParameterListImpl2")); 
/* 327 */     return obj;
/*     */   }
/*     */   
/*     */   public Object getObjectParameter(String paramName) {
/* 345 */     return getObjectParameter0(paramName);
/*     */   }
/*     */   
/*     */   public byte getByteParameter(String paramName) {
/* 361 */     return ((Byte)getObjectParameter0(paramName)).byteValue();
/*     */   }
/*     */   
/*     */   public boolean getBooleanParameter(String paramName) {
/* 377 */     return ((Boolean)getObjectParameter0(paramName)).booleanValue();
/*     */   }
/*     */   
/*     */   public char getCharParameter(String paramName) {
/* 393 */     return ((Character)getObjectParameter0(paramName)).charValue();
/*     */   }
/*     */   
/*     */   public short getShortParameter(String paramName) {
/* 409 */     return ((Short)getObjectParameter0(paramName)).shortValue();
/*     */   }
/*     */   
/*     */   public int getIntParameter(String paramName) {
/* 425 */     return ((Integer)getObjectParameter0(paramName)).intValue();
/*     */   }
/*     */   
/*     */   public long getLongParameter(String paramName) {
/* 441 */     return ((Long)getObjectParameter0(paramName)).longValue();
/*     */   }
/*     */   
/*     */   public float getFloatParameter(String paramName) {
/* 457 */     return ((Float)getObjectParameter0(paramName)).floatValue();
/*     */   }
/*     */   
/*     */   public double getDoubleParameter(String paramName) {
/* 473 */     return ((Double)getObjectParameter0(paramName)).doubleValue();
/*     */   }
/*     */   
/*     */   private String formatMsg(String key, Object[] args) {
/* 481 */     MessageFormat mf = new MessageFormat(key);
/* 482 */     mf.setLocale(Locale.getDefault());
/* 484 */     return mf.format(args);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ParameterListImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */