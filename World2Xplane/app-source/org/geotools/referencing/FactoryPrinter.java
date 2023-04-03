/*     */ package org.geotools.referencing;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.geotools.factory.FactoryRegistry;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.Factory;
/*     */ 
/*     */ final class FactoryPrinter implements Comparator<Class<?>> {
/*     */   public int compare(Class<?> factory1, Class<?> factory2) {
/*  69 */     return Classes.getShortName(factory1).compareToIgnoreCase(Classes.getShortName(factory2));
/*     */   }
/*     */   
/*     */   public void list(FactoryRegistry registry, Writer out, Locale locale) throws IOException {
/*  90 */     List<Class<?>> categories = new ArrayList<Class<?>>();
/*  91 */     for (Iterator<Class<?>> it = registry.getCategories(); it.hasNext();)
/*  92 */       categories.add(it.next()); 
/*  94 */     Collections.sort(categories, this);
/*  98 */     Vocabulary resources = Vocabulary.getResources(locale);
/*  99 */     TableWriter table = new TableWriter(out, " │ ");
/* 100 */     table.setMultiLinesCells(true);
/* 101 */     table.writeHorizontalSeparator();
/* 102 */     table.write(resources.getString(69));
/* 103 */     table.nextColumn();
/* 104 */     table.write(resources.getString(6));
/* 105 */     table.nextColumn();
/* 106 */     table.write(resources.getString(237));
/* 107 */     table.nextColumn();
/* 108 */     table.write(resources.getString(107));
/* 109 */     table.nextLine();
/* 110 */     table.nextLine('═');
/* 111 */     StringBuilder vendors = new StringBuilder();
/* 112 */     StringBuilder implementations = new StringBuilder();
/* 113 */     for (Iterator<Class<?>> iterator1 = categories.iterator(); iterator1.hasNext(); ) {
/* 117 */       Class<?> category = iterator1.next();
/* 118 */       table.write(Classes.getShortName(category));
/* 119 */       table.nextColumn();
/* 123 */       Iterator<?> providers = registry.getServiceProviders(category, null, null);
/* 124 */       while (providers.hasNext()) {
/* 125 */         if (implementations.length() != 0) {
/* 126 */           table.write(10);
/* 127 */           vendors.append('\n');
/* 128 */           implementations.append('\n');
/*     */         } 
/* 130 */         Factory provider = (Factory)providers.next();
/* 131 */         Citation vendor = provider.getVendor();
/* 132 */         vendors.append(vendor.getTitle().toString(locale));
/* 133 */         implementations.append(Classes.getShortClassName(provider));
/* 134 */         if (provider instanceof AuthorityFactory) {
/* 135 */           Citation authority = ((AuthorityFactory)provider).getAuthority();
/* 136 */           Iterator<? extends Identifier> identifiers = authority.getIdentifiers().iterator();
/* 138 */           String identifier = identifiers.hasNext() ? ((Identifier)identifiers.next()).getCode().toString() : authority.getTitle().toString(locale);
/* 141 */           table.write(identifier);
/*     */         } 
/*     */       } 
/* 147 */       table.nextColumn();
/* 148 */       table.write(vendors.toString());
/* 149 */       vendors.setLength(0);
/* 153 */       table.nextColumn();
/* 154 */       table.write(implementations.toString());
/* 155 */       implementations.setLength(0);
/* 156 */       table.writeHorizontalSeparator();
/*     */     } 
/* 158 */     table.flush();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\FactoryPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */