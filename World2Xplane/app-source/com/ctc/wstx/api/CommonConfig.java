/*     */ package com.ctc.wstx.api;
/*     */ 
/*     */ import com.ctc.wstx.util.ArgUtil;
/*     */ import com.ctc.wstx.util.DataUtil;
/*     */ import java.util.HashMap;
/*     */ import org.codehaus.stax2.XMLStreamProperties;
/*     */ 
/*     */ abstract class CommonConfig implements XMLStreamProperties {
/*     */   protected static final String IMPL_NAME = "woodstox";
/*     */   
/*     */   protected static final String IMPL_VERSION = "4.1";
/*     */   
/*     */   static final int PROP_IMPL_NAME = 1;
/*     */   
/*     */   static final int PROP_IMPL_VERSION = 2;
/*     */   
/*     */   static final int PROP_SUPPORTS_XML11 = 3;
/*     */   
/*     */   static final int PROP_SUPPORT_XMLID = 4;
/*     */   
/*     */   static final int PROP_RETURN_NULL_FOR_DEFAULT_NAMESPACE = 5;
/*     */   
/*  60 */   static final HashMap sStdProperties = new HashMap(16);
/*     */   
/*     */   protected boolean mReturnNullForDefaultNamespace;
/*     */   
/*     */   static {
/*  63 */     sStdProperties.put("org.codehaus.stax2.implName", DataUtil.Integer(1));
/*  65 */     sStdProperties.put("org.codehaus.stax2.implVersion", DataUtil.Integer(2));
/*  69 */     sStdProperties.put("org.codehaus.stax2.supportsXml11", DataUtil.Integer(3));
/*  73 */     sStdProperties.put("org.codehaus.stax2.supportXmlId", DataUtil.Integer(4));
/*  76 */     sStdProperties.put("com.ctc.wstx.returnNullForDefaultNamespace", DataUtil.Integer(5));
/*  83 */     sStdProperties.put("http://java.sun.com/xml/stream/properties/implementation-name", DataUtil.Integer(1));
/*     */   }
/*     */   
/*     */   protected CommonConfig(CommonConfig base) {
/* 112 */     this.mReturnNullForDefaultNamespace = (base == null) ? Boolean.getBoolean("com.ctc.wstx.returnNullForDefaultNamespace") : base.mReturnNullForDefaultNamespace;
/*     */   }
/*     */   
/*     */   public Object getProperty(String propName) {
/* 129 */     int id = findPropertyId(propName);
/* 130 */     if (id >= 0)
/* 131 */       return getProperty(id); 
/* 133 */     id = findStdPropertyId(propName);
/* 134 */     if (id < 0) {
/* 135 */       reportUnknownProperty(propName);
/* 136 */       return null;
/*     */     } 
/* 138 */     return getStdProperty(id);
/*     */   }
/*     */   
/*     */   public boolean isPropertySupported(String propName) {
/* 143 */     return (findPropertyId(propName) >= 0 || findStdPropertyId(propName) >= 0);
/*     */   }
/*     */   
/*     */   public boolean setProperty(String propName, Object value) {
/* 153 */     int id = findPropertyId(propName);
/* 154 */     if (id >= 0)
/* 155 */       return setProperty(propName, id, value); 
/* 157 */     id = findStdPropertyId(propName);
/* 158 */     if (id < 0) {
/* 159 */       reportUnknownProperty(propName);
/* 160 */       return false;
/*     */     } 
/* 162 */     return setStdProperty(propName, id, value);
/*     */   }
/*     */   
/*     */   protected void reportUnknownProperty(String propName) {
/* 168 */     throw new IllegalArgumentException("Unrecognized property '" + propName + "'");
/*     */   }
/*     */   
/*     */   public final Object safeGetProperty(String propName) {
/* 179 */     int id = findPropertyId(propName);
/* 180 */     if (id >= 0)
/* 181 */       return getProperty(id); 
/* 183 */     id = findStdPropertyId(propName);
/* 184 */     if (id < 0)
/* 185 */       return null; 
/* 187 */     return getStdProperty(id);
/*     */   }
/*     */   
/*     */   public static String getImplName() {
/* 194 */     return "woodstox";
/*     */   }
/*     */   
/*     */   public static String getImplVersion() {
/* 200 */     return "4.1";
/*     */   }
/*     */   
/*     */   public boolean doesSupportXml11() {
/* 220 */     return true;
/*     */   }
/*     */   
/*     */   public boolean doesSupportXmlId() {
/* 227 */     return true;
/*     */   }
/*     */   
/*     */   public boolean returnNullForDefaultNamespace() {
/* 231 */     return this.mReturnNullForDefaultNamespace;
/*     */   }
/*     */   
/*     */   protected int findStdPropertyId(String propName) {
/* 246 */     Integer I = (Integer)sStdProperties.get(propName);
/* 247 */     return (I == null) ? -1 : I.intValue();
/*     */   }
/*     */   
/*     */   protected boolean setStdProperty(String propName, int id, Object value) {
/* 258 */     switch (id) {
/*     */       case 5:
/* 260 */         this.mReturnNullForDefaultNamespace = ArgUtil.convertToBoolean(propName, value);
/* 261 */         return true;
/*     */     } 
/* 263 */     return false;
/*     */   }
/*     */   
/*     */   protected Object getStdProperty(int id) {
/* 268 */     switch (id) {
/*     */       case 1:
/* 270 */         return "woodstox";
/*     */       case 2:
/* 272 */         return "4.1";
/*     */       case 3:
/* 274 */         return doesSupportXml11() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 4:
/* 276 */         return doesSupportXmlId() ? Boolean.TRUE : Boolean.FALSE;
/*     */       case 5:
/* 278 */         return returnNullForDefaultNamespace() ? Boolean.TRUE : Boolean.FALSE;
/*     */     } 
/* 280 */     throw new IllegalStateException("Internal error: no handler for property with internal id " + id + ".");
/*     */   }
/*     */   
/*     */   protected abstract int findPropertyId(String paramString);
/*     */   
/*     */   protected abstract Object getProperty(int paramInt);
/*     */   
/*     */   protected abstract boolean setProperty(String paramString, int paramInt, Object paramObject);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\api\CommonConfig.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */