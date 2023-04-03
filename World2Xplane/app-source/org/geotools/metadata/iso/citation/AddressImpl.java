/*     */ package org.geotools.metadata.iso.citation;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.Address;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class AddressImpl extends MetadataEntity implements Address {
/*     */   private static final long serialVersionUID = 2278687294173262546L;
/*     */   
/*     */   private InternationalString administrativeArea;
/*     */   
/*     */   private InternationalString city;
/*     */   
/*     */   private InternationalString country;
/*     */   
/*     */   private String postalCode;
/*     */   
/*     */   private Collection<String> deliveryPoints;
/*     */   
/*     */   private Collection<String> electronicMailAddresses;
/*     */   
/*     */   public AddressImpl() {}
/*     */   
/*     */   public AddressImpl(Address source) {
/*  88 */     super(source);
/*     */   }
/*     */   
/*     */   public InternationalString getAdministrativeArea() {
/*  96 */     return this.administrativeArea;
/*     */   }
/*     */   
/*     */   public synchronized void setAdministrativeArea(InternationalString newValue) {
/* 103 */     checkWritePermission();
/* 104 */     this.administrativeArea = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getCity() {
/* 112 */     return this.city;
/*     */   }
/*     */   
/*     */   public synchronized void setCity(InternationalString newValue) {
/* 119 */     checkWritePermission();
/* 120 */     this.city = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getCountry() {
/* 128 */     return this.country;
/*     */   }
/*     */   
/*     */   public synchronized void setCountry(InternationalString newValue) {
/* 135 */     checkWritePermission();
/* 136 */     this.country = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<String> getDeliveryPoints() {
/* 143 */     return this.deliveryPoints = nonNullCollection(this.deliveryPoints, String.class);
/*     */   }
/*     */   
/*     */   public synchronized void setDeliveryPoints(Collection<? extends String> newValues) {
/* 152 */     this.deliveryPoints = copyCollection(newValues, this.deliveryPoints, String.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<String> getElectronicMailAddresses() {
/* 159 */     return this.electronicMailAddresses = nonNullCollection(this.electronicMailAddresses, String.class);
/*     */   }
/*     */   
/*     */   public synchronized void setElectronicMailAddresses(Collection<? extends String> newValues) {
/* 168 */     this.electronicMailAddresses = copyCollection(newValues, this.electronicMailAddresses, String.class);
/*     */   }
/*     */   
/*     */   public String getPostalCode() {
/* 176 */     return this.postalCode;
/*     */   }
/*     */   
/*     */   public synchronized void setPostalCode(String newValue) {
/* 183 */     checkWritePermission();
/* 184 */     this.postalCode = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\citation\AddressImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */