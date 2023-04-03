/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.naming.InitialContext;
/*     */ import javax.sql.DataSource;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public abstract class AbstractEpsgMediator extends AbstractAuthorityMediator {
/*     */   public static final int PRIORITY = 40;
/*     */   
/*  58 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.referencing.factory");
/*     */   
/*     */   protected DataSource datasource;
/*     */   
/*     */   public AbstractEpsgMediator() {}
/*     */   
/*     */   public AbstractEpsgMediator(Hints hints) throws FactoryException {
/*  69 */     this(hints, lookupDataSource(hints));
/*     */   }
/*     */   
/*     */   static DataSource lookupDataSource(Hints hints) throws FactoryException {
/*  83 */     Object hint = hints.get(Hints.EPSG_DATA_SOURCE);
/*  84 */     if (hint instanceof DataSource)
/*  85 */       return (DataSource)hint; 
/*  87 */     if (hint instanceof String) {
/*  88 */       String name = (String)hint;
/*     */       try {
/*  91 */         InitialContext context = GeoTools.getInitialContext(hints);
/*  93 */         return (DataSource)context.lookup(name);
/*  94 */       } catch (Exception e) {
/*  95 */         throw new FactoryException("EPSG_DATA_SOURCE '" + name + "' not found:" + e, e);
/*     */       } 
/*     */     } 
/*  98 */     throw new FactoryException("EPSG_DATA_SOURCE must be provided");
/*     */   }
/*     */   
/*     */   public AbstractEpsgMediator(Hints hints, DataSource datasource) {
/* 103 */     super(40, hints);
/* 105 */     if (datasource != null) {
/* 106 */       this.datasource = datasource;
/*     */     } else {
/*     */       try {
/* 110 */         this.datasource = lookupDataSource(hints);
/* 111 */       } catch (FactoryException lookupFailed) {
/* 112 */         throw (NullPointerException)(new NullPointerException("DataSource not provided:" + lookupFailed)).initCause(lookupFailed);
/*     */       } 
/*     */     } 
/* 115 */     hints.put(Hints.EPSG_DATA_SOURCE, this.datasource);
/*     */   }
/*     */   
/*     */   protected Connection getConnection() throws SQLException {
/*     */     try {
/* 120 */       return this.datasource.getConnection();
/* 121 */     } catch (SQLException e) {
/* 122 */       LOGGER.log(Level.SEVERE, "Connection failed", e);
/* 123 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 128 */     return Citations.EPSG;
/*     */   }
/*     */   
/*     */   public void dispose() throws FactoryException {
/* 132 */     super.dispose();
/* 133 */     this.datasource = null;
/*     */   }
/*     */   
/*     */   public boolean isConnected() {
/* 137 */     return (this.datasource != null && super.isConnected());
/*     */   }
/*     */   
/*     */   public InternationalString getDescriptionText(String code) throws FactoryException {
/* 150 */     IdentifiedObject identifiedObject = createObject(code);
/* 151 */     ReferenceIdentifier referenceIdentifier = identifiedObject.getName();
/* 152 */     if (referenceIdentifier instanceof GenericName)
/* 153 */       return ((GenericName)referenceIdentifier).toInternationalString(); 
/* 155 */     return (InternationalString)new SimpleInternationalString(referenceIdentifier.getCode());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\AbstractEpsgMediator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */