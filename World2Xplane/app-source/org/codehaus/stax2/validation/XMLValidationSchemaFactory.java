/*     */ package org.codehaus.stax2.validation;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Properties;
/*     */ import javax.xml.stream.FactoryConfigurationError;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public abstract class XMLValidationSchemaFactory {
/*     */   public static final String INTERNAL_ID_SCHEMA_DTD = "dtd";
/*     */   
/*     */   public static final String INTERNAL_ID_SCHEMA_RELAXNG = "relaxng";
/*     */   
/*     */   public static final String INTERNAL_ID_SCHEMA_W3C = "w3c";
/*     */   
/*     */   public static final String INTERNAL_ID_SCHEMA_TREX = "trex";
/*     */   
/*  35 */   static final HashMap sSchemaIds = new HashMap();
/*     */   
/*     */   static final String JAXP_PROP_FILENAME = "jaxp.properties";
/*     */   
/*     */   public static final String SYSTEM_PROPERTY_FOR_IMPL = "org.codehaus.stax2.validation.XMLValidationSchemaFactory.";
/*     */   
/*     */   public static final String SERVICE_DEFINITION_PATH = "META-INF/services/org.codehaus.stax2.validation.XMLValidationSchemaFactory.";
/*     */   
/*     */   public static final String P_IS_NAMESPACE_AWARE = "org.codehaus2.stax2.validation.isNamespaceAware";
/*     */   
/*     */   public static final String P_ENABLE_CACHING = "org.codehaus2.stax2.validation.enableCaching";
/*     */   
/*     */   protected final String mSchemaType;
/*     */   
/*     */   static {
/*  37 */     sSchemaIds.put("http://www.w3.org/XML/1998/namespace", "dtd");
/*  38 */     sSchemaIds.put("http://relaxng.org/ns/structure/0.9", "relaxng");
/*  39 */     sSchemaIds.put("http://www.w3.org/2001/XMLSchema", "w3c");
/*  40 */     sSchemaIds.put("http://www.thaiopensource.com/trex", "trex");
/*     */   }
/*     */   
/*     */   protected XMLValidationSchemaFactory(String st) {
/*  98 */     this.mSchemaType = st;
/*     */   }
/*     */   
/*     */   public static XMLValidationSchemaFactory newInstance(String schemaType) throws FactoryConfigurationError {
/* 116 */     return newInstance(schemaType, Thread.currentThread().getContextClassLoader());
/*     */   }
/*     */   
/*     */   public static XMLValidationSchemaFactory newInstance(String schemaType, ClassLoader classLoader) throws FactoryConfigurationError {
/* 126 */     String internalId = (String)sSchemaIds.get(schemaType);
/* 127 */     if (internalId == null)
/* 128 */       throw new FactoryConfigurationError("Unrecognized schema type (id '" + schemaType + "')"); 
/* 131 */     String propertyId = "org.codehaus.stax2.validation.XMLValidationSchemaFactory." + internalId;
/* 132 */     SecurityException secEx = null;
/*     */     try {
/* 138 */       String clsName = System.getProperty(propertyId);
/* 139 */       if (clsName != null && clsName.length() > 0)
/* 140 */         return createNewInstance(classLoader, clsName); 
/* 142 */     } catch (SecurityException se) {
/* 144 */       secEx = se;
/*     */     } 
/*     */     try {
/* 152 */       String home = System.getProperty("java.home");
/* 153 */       File f = new File(home);
/* 156 */       f = new File(f, "lib");
/* 157 */       f = new File(f, "jaxp.properties");
/* 158 */       if (f.exists())
/*     */         try {
/* 160 */           Properties props = new Properties();
/* 161 */           props.load(new FileInputStream(f));
/* 162 */           String clsName = props.getProperty(propertyId);
/* 163 */           if (clsName != null && clsName.length() > 0)
/* 164 */             return createNewInstance(classLoader, clsName); 
/* 166 */         } catch (IOException ioe) {} 
/* 170 */     } catch (SecurityException se) {
/* 172 */       secEx = se;
/*     */     } 
/* 178 */     String path = "META-INF/services/org.codehaus.stax2.validation.XMLValidationSchemaFactory." + internalId;
/*     */     try {
/*     */       Enumeration en;
/* 182 */       if (classLoader == null) {
/* 183 */         en = ClassLoader.getSystemResources(path);
/*     */       } else {
/* 185 */         en = classLoader.getResources(path);
/*     */       } 
/* 188 */       if (en != null)
/* 189 */         while (en.hasMoreElements()) {
/* 190 */           URL url = en.nextElement();
/* 191 */           InputStream is = url.openStream();
/* 192 */           BufferedReader rd = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
/* 194 */           String clsName = null;
/*     */           try {
/*     */             String line;
/* 198 */             while ((line = rd.readLine()) != null) {
/* 199 */               line = line.trim();
/* 200 */               if (line.length() > 0 && line.charAt(0) != '#') {
/* 201 */                 clsName = line;
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } finally {
/* 206 */             rd.close();
/*     */           } 
/* 208 */           if (clsName != null && clsName.length() > 0)
/* 209 */             return createNewInstance(classLoader, clsName); 
/*     */         }  
/* 213 */     } catch (SecurityException se) {
/* 214 */       secEx = se;
/* 215 */     } catch (IOException ex) {}
/* 220 */     String msg = "No XMLValidationSchemaFactory implementation class specified or accessible (via system property '" + propertyId + "', or service definition under '" + path + "')";
/* 223 */     if (secEx != null)
/* 224 */       throw new FactoryConfigurationError(msg + " (possibly caused by: " + secEx + ")", secEx); 
/* 226 */     throw new FactoryConfigurationError(msg);
/*     */   }
/*     */   
/*     */   public XMLValidationSchema createSchema(InputStream in) throws XMLStreamException {
/* 234 */     return createSchema(in, null);
/*     */   }
/*     */   
/*     */   public XMLValidationSchema createSchema(InputStream in, String encoding) throws XMLStreamException {
/* 240 */     return createSchema(in, encoding, null, null);
/*     */   }
/*     */   
/*     */   public XMLValidationSchema createSchema(Reader r) throws XMLStreamException {
/* 250 */     return createSchema(r, null, null);
/*     */   }
/*     */   
/*     */   public final String getSchemaType() {
/* 287 */     return this.mSchemaType;
/*     */   }
/*     */   
/*     */   private static XMLValidationSchemaFactory createNewInstance(ClassLoader cloader, String clsName) throws FactoryConfigurationError {
/*     */     try {
/*     */       Class factoryClass;
/* 301 */       if (cloader == null) {
/* 302 */         factoryClass = Class.forName(clsName);
/*     */       } else {
/* 304 */         factoryClass = cloader.loadClass(clsName);
/*     */       } 
/* 306 */       return (XMLValidationSchemaFactory)factoryClass.newInstance();
/* 307 */     } catch (ClassNotFoundException x) {
/* 308 */       throw new FactoryConfigurationError("XMLValidationSchemaFactory implementation '" + clsName + "' not found (missing jar in classpath?)", x);
/* 310 */     } catch (Exception x) {
/* 311 */       throw new FactoryConfigurationError("XMLValidationSchemaFactory implementation '" + clsName + "' could not be instantiated: " + x, x);
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract XMLValidationSchema createSchema(InputStream paramInputStream, String paramString1, String paramString2, String paramString3) throws XMLStreamException;
/*     */   
/*     */   public abstract XMLValidationSchema createSchema(Reader paramReader, String paramString1, String paramString2) throws XMLStreamException;
/*     */   
/*     */   public abstract XMLValidationSchema createSchema(URL paramURL) throws XMLStreamException;
/*     */   
/*     */   public abstract XMLValidationSchema createSchema(File paramFile) throws XMLStreamException;
/*     */   
/*     */   public abstract boolean isPropertySupported(String paramString);
/*     */   
/*     */   public abstract boolean setProperty(String paramString, Object paramObject);
/*     */   
/*     */   public abstract Object getProperty(String paramString);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\validation\XMLValidationSchemaFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */