/*     */ package org.geotools.metadata.iso.citation;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.metadata.citation.Contact;
/*     */ import org.opengis.metadata.citation.OnLineFunction;
/*     */ import org.opengis.metadata.citation.OnLineResource;
/*     */ import org.opengis.metadata.citation.ResponsibleParty;
/*     */ import org.opengis.metadata.citation.Role;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class ResponsiblePartyImpl extends MetadataEntity implements ResponsibleParty {
/*     */   private static final long serialVersionUID = -2477962229031486552L;
/*     */   
/*  58 */   static final InternationalString OGC_NAME = (InternationalString)new SimpleInternationalString("Open Geospatial Consortium");
/*     */   
/*     */   public static ResponsibleParty OGC;
/*     */   
/*     */   public static ResponsibleParty OPEN_GIS;
/*     */   
/*     */   public static ResponsibleParty EPSG;
/*     */   
/*     */   public static ResponsibleParty GEOTIFF;
/*     */   
/*     */   public static ResponsibleParty ESRI;
/*     */   
/*     */   public static ResponsibleParty ORACLE;
/*     */   
/*     */   public static ResponsibleParty POSTGIS;
/*     */   
/*     */   public static ResponsibleParty SUN_MICROSYSTEMS;
/*     */   
/*     */   public static ResponsibleParty GEOTOOLS;
/*     */   
/*     */   private String individualName;
/*     */   
/*     */   private InternationalString organisationName;
/*     */   
/*     */   private InternationalString positionName;
/*     */   
/*     */   private Contact contactInfo;
/*     */   
/*     */   private Role role;
/*     */   
/*     */   public static ResponsibleParty OGC(Role role, OnLineResource resource) {
/*  72 */     ContactImpl contact = new ContactImpl(resource);
/*  73 */     contact.freeze();
/*  75 */     ResponsiblePartyImpl ogc = new ResponsiblePartyImpl(role);
/*  76 */     ogc.setOrganisationName(OGC_NAME);
/*  77 */     ogc.setContactInfo(contact);
/*  78 */     ogc.freeze();
/*  80 */     return ogc;
/*     */   }
/*     */   
/*     */   public static ResponsibleParty OGC(Role role, OnLineFunction function, URI onlineResource) {
/*  96 */     OnLineResourceImpl resource = new OnLineResourceImpl(onlineResource);
/*  97 */     resource.setFunction(function);
/*  98 */     resource.freeze();
/*  99 */     return OGC(role, resource);
/*     */   }
/*     */   
/*     */   static ResponsibleParty OGC(Role role, OnLineFunction function, String onlineResource) {
/*     */     try {
/* 116 */       return OGC(role, function, new URI(onlineResource));
/* 118 */     } catch (URISyntaxException badContact) {
/* 119 */       Logging.unexpectedException("org.geotools.metadata.iso", ResponsibleParty.class, "OGC", badContact);
/* 121 */       return OGC;
/*     */     } 
/*     */   }
/*     */   
/*     */   static {
/* 133 */     ResponsiblePartyImpl r = new ResponsiblePartyImpl(Role.RESOURCE_PROVIDER);
/* 134 */     r.setOrganisationName(OGC_NAME);
/* 135 */     r.setContactInfo(ContactImpl.OGC);
/* 136 */     r.freeze();
/* 137 */     OGC = r;
/* 148 */     r = new ResponsiblePartyImpl(Role.RESOURCE_PROVIDER);
/* 149 */     r.setOrganisationName((InternationalString)new SimpleInternationalString("OpenGIS consortium"));
/* 150 */     r.setContactInfo(ContactImpl.OPEN_GIS);
/* 151 */     r.freeze();
/* 152 */     OPEN_GIS = r;
/* 162 */     r = new ResponsiblePartyImpl(Role.PRINCIPAL_INVESTIGATOR);
/* 163 */     r.setOrganisationName((InternationalString)new SimpleInternationalString("European Petroleum Survey Group"));
/* 164 */     r.setContactInfo(ContactImpl.EPSG);
/* 165 */     r.freeze();
/* 166 */     EPSG = r;
/* 177 */     r = new ResponsiblePartyImpl(Role.PRINCIPAL_INVESTIGATOR);
/* 178 */     r.setOrganisationName((InternationalString)new SimpleInternationalString("GeoTIFF"));
/* 179 */     r.setContactInfo(ContactImpl.GEOTIFF);
/* 180 */     r.freeze();
/* 181 */     GEOTIFF = r;
/* 191 */     r = new ResponsiblePartyImpl(Role.OWNER);
/* 192 */     r.setOrganisationName((InternationalString)new SimpleInternationalString("ESRI"));
/* 193 */     r.setContactInfo(ContactImpl.ESRI);
/* 194 */     r.freeze();
/* 195 */     ESRI = r;
/* 205 */     r = new ResponsiblePartyImpl(Role.OWNER);
/* 206 */     r.setOrganisationName((InternationalString)new SimpleInternationalString("Oracle"));
/* 207 */     r.setContactInfo(ContactImpl.ORACLE);
/* 208 */     r.freeze();
/* 209 */     ORACLE = r;
/* 221 */     r = new ResponsiblePartyImpl(Role.PRINCIPAL_INVESTIGATOR);
/* 222 */     r.setOrganisationName((InternationalString)new SimpleInternationalString("PostGIS"));
/* 223 */     r.setContactInfo(ContactImpl.POSTGIS);
/* 224 */     r.freeze();
/* 225 */     POSTGIS = r;
/* 237 */     r = new ResponsiblePartyImpl(Role.PRINCIPAL_INVESTIGATOR);
/* 238 */     r.setOrganisationName((InternationalString)new SimpleInternationalString("Sun Microsystems"));
/* 239 */     r.setContactInfo(ContactImpl.SUN_MICROSYSTEMS);
/* 240 */     r.freeze();
/* 241 */     SUN_MICROSYSTEMS = r;
/* 251 */     r = new ResponsiblePartyImpl(Role.PRINCIPAL_INVESTIGATOR);
/* 252 */     r.setOrganisationName((InternationalString)new SimpleInternationalString("Geotools"));
/* 253 */     r.setContactInfo(ContactImpl.GEOTOOLS);
/* 254 */     r.freeze();
/* 255 */     GEOTOOLS = r;
/*     */   }
/*     */   
/*     */   public ResponsiblePartyImpl() {}
/*     */   
/*     */   public ResponsiblePartyImpl(ResponsibleParty source) {
/* 297 */     super(source);
/*     */   }
/*     */   
/*     */   public ResponsiblePartyImpl(Role role) {
/* 304 */     setRole(role);
/*     */   }
/*     */   
/*     */   public String getIndividualName() {
/* 313 */     return this.individualName;
/*     */   }
/*     */   
/*     */   public synchronized void setIndividualName(String newValue) {
/* 322 */     checkWritePermission();
/* 323 */     this.individualName = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getOrganisationName() {
/* 332 */     return this.organisationName;
/*     */   }
/*     */   
/*     */   public synchronized void setOrganisationName(InternationalString newValue) {
/* 341 */     checkWritePermission();
/* 342 */     this.organisationName = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getPositionName() {
/* 352 */     return this.positionName;
/*     */   }
/*     */   
/*     */   public synchronized void setPositionName(InternationalString newValue) {
/* 362 */     checkWritePermission();
/* 363 */     this.positionName = newValue;
/*     */   }
/*     */   
/*     */   public Contact getContactInfo() {
/* 370 */     return this.contactInfo;
/*     */   }
/*     */   
/*     */   public synchronized void setContactInfo(Contact newValue) {
/* 377 */     checkWritePermission();
/* 378 */     this.contactInfo = newValue;
/*     */   }
/*     */   
/*     */   public Role getRole() {
/* 385 */     return this.role;
/*     */   }
/*     */   
/*     */   public synchronized void setRole(Role newValue) {
/* 392 */     checkWritePermission();
/* 393 */     this.role = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\citation\ResponsiblePartyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */