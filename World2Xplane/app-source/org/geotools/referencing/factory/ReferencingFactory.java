/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.AbstractFactory;
/*     */ import org.geotools.metadata.iso.citation.CitationImpl;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.parameter.InvalidParameterValueException;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.Factory;
/*     */ 
/*     */ public class ReferencingFactory extends AbstractFactory implements Factory {
/*  69 */   public static final Logger LOGGER = Logging.getLogger("org.geotools.referencing.factory");
/*     */   
/*     */   static final Citation ALL;
/*     */   
/*     */   static {
/*  80 */     CitationImpl citation = new CitationImpl(Vocabulary.format(4));
/*  81 */     citation.freeze();
/*  82 */     ALL = (Citation)citation;
/*     */   }
/*     */   
/*     */   protected ReferencingFactory() {}
/*     */   
/*     */   protected ReferencingFactory(int priority) {
/* 100 */     super(priority);
/*     */   }
/*     */   
/*     */   public Citation getVendor() {
/* 111 */     return Citations.GEOTOOLS;
/*     */   }
/*     */   
/*     */   protected static void ensureNonNull(String name, Object object) throws InvalidParameterValueException {
/* 125 */     if (object == null)
/* 126 */       throw new InvalidParameterValueException(Errors.format(143, name), name, object); 
/*     */   }
/*     */   
/*     */   Collection<? super AuthorityFactory> dependencies() {
/* 139 */     return Collections.emptySet();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\ReferencingFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */