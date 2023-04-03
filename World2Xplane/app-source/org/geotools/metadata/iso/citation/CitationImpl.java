/*     */ package org.geotools.metadata.iso.citation;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import org.geotools.metadata.iso.IdentifierImpl;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.citation.CitationDate;
/*     */ import org.opengis.metadata.citation.PresentationForm;
/*     */ import org.opengis.metadata.citation.ResponsibleParty;
/*     */ import org.opengis.metadata.citation.Series;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class CitationImpl extends MetadataEntity implements Citation {
/*     */   private static final long serialVersionUID = -4415559967618358778L;
/*     */   
/*     */   private InternationalString title;
/*     */   
/*     */   private Collection<InternationalString> alternateTitles;
/*     */   
/*     */   private Collection<CitationDate> dates;
/*     */   
/*     */   private InternationalString edition;
/*     */   
/*     */   private long editionDate;
/*     */   
/*     */   private Collection<Identifier> identifiers;
/*     */   
/*     */   private Collection<ResponsibleParty> citedResponsibleParties;
/*     */   
/*     */   private Collection<PresentationForm> presentationForm;
/*     */   
/*     */   private Series series;
/*     */   
/*     */   private InternationalString otherCitationDetails;
/*     */   
/*     */   private InternationalString collectiveTitle;
/*     */   
/*     */   private String ISBN;
/*     */   
/*     */   private String ISSN;
/*     */   
/*     */   public CitationImpl() {
/*  78 */     this.editionDate = Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public CitationImpl(Citation source) {
/* 138 */     super(source);
/*     */     this.editionDate = Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public CitationImpl(CharSequence title) {
/*     */     SimpleInternationalString simpleInternationalString;
/*     */     this.editionDate = Long.MIN_VALUE;
/* 148 */     if (title instanceof InternationalString) {
/* 149 */       InternationalString t = (InternationalString)title;
/*     */     } else {
/* 151 */       simpleInternationalString = new SimpleInternationalString(title.toString());
/*     */     } 
/* 153 */     setTitle((InternationalString)simpleInternationalString);
/*     */   }
/*     */   
/*     */   public CitationImpl(ResponsibleParty party) {
/*     */     SimpleInternationalString simpleInternationalString;
/*     */     this.editionDate = Long.MIN_VALUE;
/* 166 */     InternationalString title = party.getOrganisationName();
/* 167 */     if (title == null) {
/* 168 */       title = party.getPositionName();
/* 169 */       if (title == null) {
/* 170 */         String name = party.getIndividualName();
/* 171 */         if (name != null)
/* 172 */           simpleInternationalString = new SimpleInternationalString(name); 
/*     */       } 
/*     */     } 
/* 176 */     setTitle((InternationalString)simpleInternationalString);
/* 177 */     getCitedResponsibleParties().add(party);
/*     */   }
/*     */   
/*     */   final void addAuthority(String identifier, boolean asTitle) {
/* 186 */     if (asTitle)
/* 187 */       getAlternateTitles().add(new SimpleInternationalString(identifier)); 
/* 189 */     getIdentifiers().add(new IdentifierImpl(identifier));
/*     */   }
/*     */   
/*     */   public InternationalString getTitle() {
/* 196 */     return this.title;
/*     */   }
/*     */   
/*     */   public synchronized void setTitle(InternationalString newValue) {
/* 203 */     checkWritePermission();
/* 204 */     this.title = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<InternationalString> getAlternateTitles() {
/* 212 */     return this.alternateTitles = nonNullCollection(this.alternateTitles, InternationalString.class);
/*     */   }
/*     */   
/*     */   public synchronized void setAlternateTitles(Collection<? extends InternationalString> newValues) {
/* 221 */     this.alternateTitles = copyCollection(newValues, this.alternateTitles, InternationalString.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<CitationDate> getDates() {
/* 228 */     return this.dates = nonNullCollection(this.dates, CitationDate.class);
/*     */   }
/*     */   
/*     */   public synchronized void setDates(Collection<? extends CitationDate> newValues) {
/* 235 */     this.dates = copyCollection(newValues, this.dates, CitationDate.class);
/*     */   }
/*     */   
/*     */   public InternationalString getEdition() {
/* 242 */     return this.edition;
/*     */   }
/*     */   
/*     */   public synchronized void setEdition(InternationalString newValue) {
/* 249 */     checkWritePermission();
/* 250 */     this.edition = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Date getEditionDate() {
/* 257 */     return (this.editionDate != Long.MIN_VALUE) ? new Date(this.editionDate) : null;
/*     */   }
/*     */   
/*     */   public synchronized void setEditionDate(Date newValue) {
/* 266 */     checkWritePermission();
/* 267 */     this.editionDate = (newValue != null) ? newValue.getTime() : Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public synchronized Collection<Identifier> getIdentifiers() {
/* 275 */     return this.identifiers = nonNullCollection(this.identifiers, Identifier.class);
/*     */   }
/*     */   
/*     */   public synchronized void setIdentifiers(Collection<? extends Identifier> newValues) {
/* 283 */     this.identifiers = copyCollection(newValues, this.identifiers, Identifier.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<ResponsibleParty> getCitedResponsibleParties() {
/* 291 */     return this.citedResponsibleParties = nonNullCollection(this.citedResponsibleParties, ResponsibleParty.class);
/*     */   }
/*     */   
/*     */   public synchronized void setCitedResponsibleParties(Collection<? extends ResponsibleParty> newValues) {
/* 302 */     this.citedResponsibleParties = copyCollection(newValues, this.citedResponsibleParties, ResponsibleParty.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<PresentationForm> getPresentationForm() {
/* 310 */     return this.presentationForm = nonNullCollection(this.presentationForm, PresentationForm.class);
/*     */   }
/*     */   
/*     */   public synchronized void setPresentationForm(Collection<? extends PresentationForm> newValues) {
/* 320 */     this.presentationForm = copyCollection(newValues, this.presentationForm, PresentationForm.class);
/*     */   }
/*     */   
/*     */   public Series getSeries() {
/* 328 */     return this.series;
/*     */   }
/*     */   
/*     */   public synchronized void setSeries(Series newValue) {
/* 336 */     checkWritePermission();
/* 337 */     this.series = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getOtherCitationDetails() {
/* 345 */     return this.otherCitationDetails;
/*     */   }
/*     */   
/*     */   public synchronized void setOtherCitationDetails(InternationalString newValue) {
/* 353 */     checkWritePermission();
/* 354 */     this.otherCitationDetails = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getCollectiveTitle() {
/* 363 */     return this.collectiveTitle;
/*     */   }
/*     */   
/*     */   public synchronized void setCollectiveTitle(InternationalString newValue) {
/* 372 */     checkWritePermission();
/* 373 */     this.collectiveTitle = newValue;
/*     */   }
/*     */   
/*     */   public String getISBN() {
/* 380 */     return this.ISBN;
/*     */   }
/*     */   
/*     */   public synchronized void setISBN(String newValue) {
/* 387 */     checkWritePermission();
/* 388 */     this.ISBN = newValue;
/*     */   }
/*     */   
/*     */   public String getISSN() {
/* 395 */     return this.ISSN;
/*     */   }
/*     */   
/*     */   public synchronized void setISSN(String newValue) {
/* 402 */     checkWritePermission();
/* 403 */     this.ISSN = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\citation\CitationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */