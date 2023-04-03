/*     */ package org.geotools.metadata.iso.citation;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.OnLineFunction;
/*     */ import org.opengis.metadata.citation.OnLineResource;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class OnLineResourceImpl extends MetadataEntity implements OnLineResource {
/*     */   private static final long serialVersionUID = 5412370008274334799L;
/*     */   
/*     */   public static final OnLineResource OGC;
/*     */   
/*     */   public static final OnLineResource OPEN_GIS;
/*     */   
/*     */   public static final OnLineResource EPSG;
/*     */   
/*     */   public static final OnLineResource GEOTIFF;
/*     */   
/*     */   public static final OnLineResource ESRI;
/*     */   
/*     */   public static final OnLineResource ORACLE;
/*     */   
/*     */   public static final OnLineResource POSTGIS;
/*     */   
/*     */   public static final OnLineResource SUN_MICROSYSTEMS;
/*     */   
/*     */   public static final OnLineResource GEOTOOLS;
/*     */   
/*     */   public static final OnLineResource WMS;
/*     */   
/*     */   private String applicationProfile;
/*     */   
/*     */   private InternationalString description;
/*     */   
/*     */   private OnLineFunction function;
/*     */   
/*     */   private URI linkage;
/*     */   
/*     */   private String name;
/*     */   
/*     */   static {
/*  57 */     OnLineResourceImpl r = new OnLineResourceImpl("http://www.opengeospatial.org/");
/*  58 */     r.freeze();
/*  70 */     OPEN_GIS = r = new OnLineResourceImpl("http://www.opengis.org");
/*  71 */     r.freeze();
/*  81 */     EPSG = r = new OnLineResourceImpl("http://www.epsg.org");
/*  82 */     r.freeze();
/*  92 */     GEOTIFF = r = new OnLineResourceImpl("http://www.remotesensing.org/geotiff");
/*  93 */     r.freeze();
/* 102 */     ESRI = r = new OnLineResourceImpl("http://www.esri.com");
/* 103 */     r.freeze();
/* 112 */     ORACLE = r = new OnLineResourceImpl("http://www.oracle.com");
/* 113 */     r.freeze();
/* 124 */     POSTGIS = r = new OnLineResourceImpl("http://postgis.refractions.net");
/* 125 */     r.freeze();
/* 137 */     SUN_MICROSYSTEMS = r = new OnLineResourceImpl("http://java.sun.com");
/* 138 */     r.freeze();
/* 147 */     GEOTOOLS = r = new OnLineResourceImpl("http://www.geotools.org");
/* 148 */     r.freeze();
/* 161 */     WMS = r = new OnLineResourceImpl("http://portal.opengis.org/files/?artifact_id=5316");
/* 162 */     r.setFunction(OnLineFunction.DOWNLOAD);
/* 163 */     r.freeze();
/*     */   }
/*     */   
/*     */   public OnLineResourceImpl() {}
/*     */   
/*     */   public OnLineResourceImpl(OnLineResource source) {
/* 204 */     super(source);
/*     */   }
/*     */   
/*     */   private OnLineResourceImpl(String linkage) {
/*     */     try {
/* 216 */       setLinkage(new URI(linkage));
/* 217 */     } catch (URISyntaxException exception) {
/* 218 */       throw new IllegalArgumentException(exception);
/*     */     } 
/* 220 */     setFunction(OnLineFunction.INFORMATION);
/*     */   }
/*     */   
/*     */   public OnLineResourceImpl(URI linkage) {
/* 227 */     setLinkage(linkage);
/*     */   }
/*     */   
/*     */   public String getApplicationProfile() {
/* 235 */     return this.applicationProfile;
/*     */   }
/*     */   
/*     */   public synchronized void setApplicationProfile(String newValue) {
/* 242 */     checkWritePermission();
/* 243 */     this.applicationProfile = newValue;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 252 */     return this.name;
/*     */   }
/*     */   
/*     */   public synchronized void setName(String newValue) {
/* 261 */     checkWritePermission();
/* 262 */     this.name = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getDescription() {
/* 270 */     return this.description;
/*     */   }
/*     */   
/*     */   public synchronized void setDescription(InternationalString newValue) {
/* 277 */     checkWritePermission();
/* 278 */     this.description = newValue;
/*     */   }
/*     */   
/*     */   public OnLineFunction getFunction() {
/* 286 */     return this.function;
/*     */   }
/*     */   
/*     */   public synchronized void setFunction(OnLineFunction newValue) {
/* 293 */     checkWritePermission();
/* 294 */     this.function = newValue;
/*     */   }
/*     */   
/*     */   public URI getLinkage() {
/* 302 */     return this.linkage;
/*     */   }
/*     */   
/*     */   public synchronized void setLinkage(URI newValue) {
/* 310 */     checkWritePermission();
/* 311 */     this.linkage = newValue;
/*     */   }
/*     */   
/*     */   public String getProtocol() {
/* 319 */     URI linkage = this.linkage;
/* 320 */     return (linkage != null) ? linkage.getScheme() : null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\citation\OnLineResourceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */