/*     */ package org.geotools.metadata.iso.citation;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.citation.OnLineFunction;
/*     */ import org.opengis.metadata.citation.PresentationForm;
/*     */ import org.opengis.metadata.citation.ResponsibleParty;
/*     */ import org.opengis.metadata.citation.Role;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public final class Citations {
/*     */   public static final Citation OGC;
/*     */   
/*     */   public static final Citation OPEN_GIS;
/*     */   
/*     */   public static final Citation ESRI;
/*     */   
/*     */   public static final Citation ORACLE;
/*     */   
/*     */   public static final Citation POSTGIS;
/*     */   
/*     */   public static final Citation GEOTOOLS;
/*     */   
/*     */   public static final Citation WMS;
/*     */   
/*     */   public static final Citation GEOTIFF;
/*     */   
/*     */   public static final Citation JAI;
/*     */   
/*     */   public static final Citation EPSG;
/*     */   
/*     */   public static final Citation AUTO;
/*     */   
/*     */   public static final Citation AUTO2;
/*     */   
/*     */   public static final Citation CRS;
/*     */   
/*     */   public static final Citation URN_OGC;
/*     */   
/*     */   public static final Citation HTTP_OGC;
/*     */   
/*     */   public static final Citation HTTP_URI_OGC;
/*     */   
/*     */   static {
/*  86 */     CitationImpl c = new CitationImpl(ResponsiblePartyImpl.OGC);
/*  87 */     c.getAlternateTitles().add(new SimpleInternationalString("OGC"));
/*  89 */     c.getPresentationForm().add(PresentationForm.DOCUMENT_DIGITAL);
/*  90 */     c.freeze();
/*  91 */     OGC = c;
/* 105 */     c = new CitationImpl(ResponsiblePartyImpl.OPEN_GIS);
/* 106 */     Collection<InternationalString> alt = c.getAlternateTitles();
/* 107 */     alt.add(new SimpleInternationalString("OpenGIS"));
/* 108 */     alt.addAll(OGC.getAlternateTitles());
/* 109 */     c.getPresentationForm().add(PresentationForm.DOCUMENT_DIGITAL);
/* 110 */     c.freeze();
/* 111 */     OPEN_GIS = c;
/* 121 */     c = new CitationImpl(ResponsiblePartyImpl.ESRI);
/* 122 */     c.addAuthority("ESRI", true);
/* 123 */     c.freeze();
/* 124 */     ESRI = c;
/* 134 */     c = new CitationImpl(ResponsiblePartyImpl.ORACLE);
/* 135 */     c.freeze();
/* 136 */     ORACLE = c;
/* 148 */     c = new CitationImpl(ResponsiblePartyImpl.POSTGIS);
/* 149 */     c.freeze();
/* 150 */     POSTGIS = c;
/* 160 */     c = new CitationImpl(ResponsiblePartyImpl.GEOTOOLS);
/* 161 */     c.freeze();
/* 162 */     GEOTOOLS = c;
/* 188 */     c = new CitationImpl("Web Map Service");
/* 189 */     Collection<InternationalString> titles = c.getAlternateTitles();
/* 190 */     titles.add(new SimpleInternationalString("WMS"));
/* 191 */     titles.add(new SimpleInternationalString("WMS 1.3.0"));
/* 192 */     titles.add(new SimpleInternationalString("OGC 04-024"));
/* 193 */     titles.add(new SimpleInternationalString("ISO 19128"));
/* 195 */     Collection<ResponsibleParty> collection1 = c.getCitedResponsibleParties();
/* 196 */     collection1.add(ResponsiblePartyImpl.OGC);
/* 197 */     collection1.add(ResponsiblePartyImpl.OGC(Role.PUBLISHER, OnLineResourceImpl.WMS));
/* 204 */     c.getPresentationForm().add(PresentationForm.DOCUMENT_DIGITAL);
/* 205 */     c.freeze();
/* 206 */     WMS = c;
/* 216 */     c = new CitationImpl(ResponsiblePartyImpl.GEOTIFF);
/* 217 */     c.getPresentationForm().add(PresentationForm.DOCUMENT_DIGITAL);
/* 218 */     c.freeze();
/* 219 */     GEOTIFF = c;
/* 231 */     c = new CitationImpl("Java Advanced Imaging");
/* 232 */     c.getAlternateTitles().add(new SimpleInternationalString("JAI"));
/* 233 */     c.getCitedResponsibleParties().add(ResponsiblePartyImpl.SUN_MICROSYSTEMS);
/* 234 */     c.freeze();
/* 235 */     JAI = c;
/* 269 */     c = new CitationImpl(ResponsiblePartyImpl.EPSG);
/* 270 */     c.addAuthority("EPSG", true);
/* 271 */     c.getPresentationForm().add(PresentationForm.TABLE_DIGITAL);
/* 272 */     c.freeze();
/* 273 */     EPSG = c;
/* 302 */     c = new CitationImpl("Automatic Projections");
/* 303 */     c.addAuthority("AUTO", false);
/* 309 */     Collection<ResponsibleParty> parties = c.getCitedResponsibleParties();
/* 310 */     parties.add(ResponsiblePartyImpl.OGC);
/* 311 */     parties.add(ResponsiblePartyImpl.OGC(Role.PUBLISHER, OnLineFunction.DOWNLOAD, "http://www.opengis.org/docs/01-068r3.pdf"));
/* 313 */     c.getPresentationForm().add(PresentationForm.DOCUMENT_DIGITAL);
/* 314 */     c.freeze();
/* 315 */     AUTO = c;
/* 346 */     c = new CitationImpl("Automatic Projections");
/* 347 */     c.addAuthority("AUTO2", false);
/* 353 */     parties = c.getCitedResponsibleParties();
/* 354 */     parties.add(ResponsiblePartyImpl.OGC);
/* 355 */     parties.add(ResponsiblePartyImpl.OGC(Role.PUBLISHER, OnLineResourceImpl.WMS));
/* 356 */     c.getPresentationForm().add(PresentationForm.DOCUMENT_DIGITAL);
/* 357 */     c.freeze();
/* 358 */     AUTO2 = c;
/* 373 */     c = new CitationImpl("Web Map Service CRS");
/* 374 */     c.addAuthority("CRS", false);
/* 375 */     c.getCitedResponsibleParties().addAll(AUTO2.getCitedResponsibleParties());
/* 376 */     c.getPresentationForm().add(PresentationForm.DOCUMENT_DIGITAL);
/* 377 */     c.freeze();
/* 378 */     CRS = c;
/* 390 */     c = new CitationImpl("URN in OGC namespace");
/* 391 */     c.addAuthority("urn:ogc:def", false);
/* 392 */     c.addAuthority("urn:x-ogc:def", false);
/* 393 */     c.getCitedResponsibleParties().add(ResponsiblePartyImpl.OGC);
/* 394 */     c.getPresentationForm().add(PresentationForm.DOCUMENT_DIGITAL);
/* 395 */     c.freeze();
/* 396 */     URN_OGC = c;
/* 408 */     c = new CitationImpl("URL in OGC namespace");
/* 409 */     c.addAuthority("http://www.opengis.net/gml", false);
/* 410 */     c.getCitedResponsibleParties().add(ResponsiblePartyImpl.OGC);
/* 411 */     c.getPresentationForm().add(PresentationForm.DOCUMENT_DIGITAL);
/* 412 */     c.freeze();
/* 413 */     HTTP_OGC = c;
/* 425 */     c = new CitationImpl("HTTP URI in OGC namespace");
/* 426 */     c.addAuthority("http://www.opengis.net/def", false);
/* 427 */     c.getCitedResponsibleParties().add(ResponsiblePartyImpl.OGC);
/* 428 */     c.getPresentationForm().add(PresentationForm.DOCUMENT_DIGITAL);
/* 429 */     c.freeze();
/* 430 */     HTTP_URI_OGC = c;
/*     */   }
/*     */   
/* 445 */   private static final Citation[] AUTHORITIES = new Citation[] { 
/* 445 */       OGC, OPEN_GIS, ESRI, ORACLE, GEOTOOLS, WMS, GEOTIFF, JAI, EPSG, AUTO, 
/* 445 */       AUTO2, CRS };
/*     */   
/*     */   public static Citation fromName(String title) {
/* 460 */     for (int i = 0; i < AUTHORITIES.length; i++) {
/* 461 */       Citation citation = AUTHORITIES[i];
/* 462 */       if (titleMatches(citation, title))
/* 463 */         return citation; 
/*     */     } 
/* 466 */     return new CitationImpl(title);
/*     */   }
/*     */   
/*     */   public static boolean titleMatches(Citation c1, Citation c2) {
/* 480 */     InternationalString candidate = c2.getTitle();
/* 481 */     Iterator<? extends InternationalString> iterator = null;
/*     */     while (true) {
/* 484 */       String asString = candidate.toString(null);
/* 485 */       if (titleMatches(c1, asString))
/* 486 */         return true; 
/* 488 */       String asLocalized = candidate.toString();
/* 489 */       if (asLocalized != asString && titleMatches(c1, asLocalized))
/* 490 */         return true; 
/* 492 */       if (iterator == null) {
/* 493 */         Collection<? extends InternationalString> titles = c2.getAlternateTitles();
/* 494 */         if (titles == null)
/*     */           break; 
/* 497 */         iterator = titles.iterator();
/*     */       } 
/* 499 */       if (!iterator.hasNext())
/*     */         break; 
/* 502 */       candidate = iterator.next();
/*     */     } 
/* 504 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean titleMatches(Citation citation, String title) {
/* 518 */     title = title.trim();
/* 519 */     InternationalString candidate = citation.getTitle();
/* 520 */     Iterator<? extends InternationalString> iterator = null;
/*     */     while (true) {
/* 523 */       String asString = candidate.toString(null);
/* 524 */       if (asString.trim().equalsIgnoreCase(title))
/* 525 */         return true; 
/* 527 */       String asLocalized = candidate.toString();
/* 528 */       if (asLocalized != asString && asLocalized.trim().equalsIgnoreCase(title))
/* 529 */         return true; 
/* 531 */       if (iterator == null) {
/* 532 */         Collection<? extends InternationalString> titles = citation.getAlternateTitles();
/* 533 */         if (titles == null)
/*     */           break; 
/* 536 */         iterator = titles.iterator();
/*     */       } 
/* 538 */       if (!iterator.hasNext())
/*     */         break; 
/* 541 */       candidate = iterator.next();
/*     */     } 
/* 543 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean identifierMatches(Citation c1, Citation c2) {
/* 566 */     Iterator<? extends Identifier> iterator = c2.getIdentifiers().iterator();
/* 567 */     if (!iterator.hasNext()) {
/* 568 */       iterator = c1.getIdentifiers().iterator();
/* 569 */       if (!iterator.hasNext())
/* 570 */         return titleMatches(c1, c2); 
/* 572 */       c1 = c2;
/* 573 */       c2 = null;
/*     */     } 
/*     */     while (true) {
/* 576 */       String id = ((Identifier)iterator.next()).getCode().trim();
/* 577 */       if (identifierMatches(c1, id))
/* 578 */         return true; 
/* 580 */       if (!iterator.hasNext())
/* 581 */         return false; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean identifierMatches(Citation citation, String identifier) {
/* 597 */     identifier = identifier.trim();
/* 598 */     Collection<? extends Identifier> identifiers = citation.getIdentifiers();
/* 599 */     for (Identifier id : identifiers) {
/* 600 */       String code = id.getCode().trim();
/* 601 */       if (identifier.equalsIgnoreCase(code))
/* 602 */         return true; 
/*     */     } 
/* 605 */     if (identifiers.isEmpty())
/* 606 */       return titleMatches(citation, identifier); 
/* 608 */     return false;
/*     */   }
/*     */   
/*     */   public static String getIdentifier(Citation citation) {
/* 623 */     String identifier = null;
/* 624 */     for (Identifier id : citation.getIdentifiers()) {
/* 625 */       String candidate = id.getCode().trim();
/* 626 */       int length = candidate.length();
/* 627 */       if (length != 0 && (
/* 628 */         identifier == null || length < identifier.length()))
/* 629 */         identifier = candidate; 
/*     */     } 
/* 633 */     if (identifier == null)
/* 634 */       identifier = String.valueOf(citation.getTitle()); 
/* 636 */     return identifier;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\citation\Citations.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */