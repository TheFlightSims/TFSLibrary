/*     */ package org.geotools.metadata.iso.citation;
/*     */ 
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.Address;
/*     */ import org.opengis.metadata.citation.Contact;
/*     */ import org.opengis.metadata.citation.OnLineResource;
/*     */ import org.opengis.metadata.citation.Telephone;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class ContactImpl extends MetadataEntity implements Contact {
/*     */   private static final long serialVersionUID = 3283637180253117382L;
/*     */   
/*     */   public static final Contact OGC;
/*     */   
/*     */   public static final Contact OPEN_GIS;
/*     */   
/*     */   public static final Contact EPSG;
/*     */   
/*     */   public static final Contact GEOTIFF;
/*     */   
/*     */   public static final Contact ESRI;
/*     */   
/*     */   public static final Contact ORACLE;
/*     */   
/*     */   public static final Contact POSTGIS;
/*     */   
/*     */   public static final Contact SUN_MICROSYSTEMS;
/*     */   
/*     */   public static final Contact GEOTOOLS;
/*     */   
/*     */   private InternationalString contactInstructions;
/*     */   
/*     */   private InternationalString hoursOfService;
/*     */   
/*     */   private OnLineResource onLineResource;
/*     */   
/*     */   private Address address;
/*     */   
/*     */   private Telephone phone;
/*     */   
/*     */   static {
/*  55 */     ContactImpl c = new ContactImpl(OnLineResourceImpl.OGC);
/*  56 */     c.freeze();
/*  57 */     OGC = c;
/*  67 */     c = new ContactImpl(OnLineResourceImpl.OPEN_GIS);
/*  68 */     c.freeze();
/*  69 */     OPEN_GIS = c;
/*  80 */     c = new ContactImpl(OnLineResourceImpl.EPSG);
/*  81 */     c.freeze();
/*  82 */     EPSG = c;
/*  93 */     c = new ContactImpl(OnLineResourceImpl.GEOTIFF);
/*  94 */     c.freeze();
/*  95 */     GEOTIFF = c;
/* 105 */     c = new ContactImpl(OnLineResourceImpl.ESRI);
/* 106 */     c.freeze();
/* 107 */     ESRI = c;
/* 117 */     c = new ContactImpl(OnLineResourceImpl.ORACLE);
/* 118 */     c.freeze();
/* 119 */     ORACLE = c;
/* 131 */     c = new ContactImpl(OnLineResourceImpl.POSTGIS);
/* 132 */     c.freeze();
/* 133 */     POSTGIS = c;
/* 145 */     c = new ContactImpl(OnLineResourceImpl.SUN_MICROSYSTEMS);
/* 146 */     c.freeze();
/* 147 */     SUN_MICROSYSTEMS = c;
/* 157 */     c = new ContactImpl(OnLineResourceImpl.GEOTOOLS);
/* 158 */     c.freeze();
/* 159 */     GEOTOOLS = c;
/*     */   }
/*     */   
/*     */   public ContactImpl() {}
/*     */   
/*     */   public ContactImpl(Contact source) {
/* 202 */     super(source);
/*     */   }
/*     */   
/*     */   public ContactImpl(OnLineResource resource) {
/* 209 */     setOnLineResource(resource);
/*     */   }
/*     */   
/*     */   public Address getAddress() {
/* 217 */     return this.address;
/*     */   }
/*     */   
/*     */   public synchronized void setAddress(Address newValue) {
/* 224 */     checkWritePermission();
/* 225 */     this.address = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getContactInstructions() {
/* 233 */     return this.contactInstructions;
/*     */   }
/*     */   
/*     */   public synchronized void setContactInstructions(InternationalString newValue) {
/* 240 */     checkWritePermission();
/* 241 */     this.contactInstructions = newValue;
/*     */   }
/*     */   
/*     */   public OnLineResource getOnLineResource() {
/* 249 */     return this.onLineResource;
/*     */   }
/*     */   
/*     */   public synchronized void setOnLineResource(OnLineResource newValue) {
/* 256 */     checkWritePermission();
/* 257 */     this.onLineResource = newValue;
/*     */   }
/*     */   
/*     */   public Telephone getPhone() {
/* 265 */     return this.phone;
/*     */   }
/*     */   
/*     */   public synchronized void setPhone(Telephone newValue) {
/* 272 */     checkWritePermission();
/* 273 */     this.phone = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getHoursOfService() {
/* 282 */     return this.hoursOfService;
/*     */   }
/*     */   
/*     */   public synchronized void setHoursOfService(InternationalString newValue) {
/* 290 */     checkWritePermission();
/* 291 */     this.hoursOfService = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\citation\ContactImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */