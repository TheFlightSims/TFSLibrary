/*     */ package org.geotools.referencing.factory.gridshift;
/*     */ 
/*     */ import au.com.objectix.jgridshift.GridShiftFile;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.net.URL;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.BufferedFactory;
/*     */ import org.geotools.referencing.factory.ReferencingFactory;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.SoftValueHashMap;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ 
/*     */ public class NTv2GridShiftFactory extends ReferencingFactory implements BufferedFactory {
/*     */   private static final int GRID_CACHE_HARD_REFERENCES = 10;
/*     */   
/*  57 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.referencing");
/*     */   
/*     */   private SoftValueHashMap<String, GridShiftFile> ntv2GridCache;
/*     */   
/*     */   public NTv2GridShiftFactory() {
/*  69 */     this.ntv2GridCache = new SoftValueHashMap(10);
/*     */   }
/*     */   
/*     */   public NTv2GridShiftFactory(int priority) {
/*  80 */     super(priority);
/*  81 */     this.ntv2GridCache = new SoftValueHashMap(10);
/*     */   }
/*     */   
/*     */   public boolean isNTv2Grid(URL location) {
/*  91 */     if (location != null)
/*  92 */       return isNTv2GridFileValid(location); 
/*  94 */     return false;
/*     */   }
/*     */   
/*     */   public GridShiftFile createNTv2Grid(URL gridLocation) throws FactoryException {
/* 106 */     if (gridLocation == null)
/* 107 */       throw new FactoryException("The grid location must be not null"); 
/* 110 */     synchronized (this.ntv2GridCache) {
/* 111 */       GridShiftFile grid = (GridShiftFile)this.ntv2GridCache.get(gridLocation.toExternalForm());
/* 112 */       if (grid != null)
/* 113 */         return grid; 
/* 115 */       if (gridLocation != null) {
/* 116 */         grid = loadNTv2Grid(gridLocation);
/* 117 */         if (grid != null) {
/* 118 */           this.ntv2GridCache.put(gridLocation.toExternalForm(), grid);
/* 119 */           return grid;
/*     */         } 
/*     */       } 
/* 122 */       throw new FactoryException("NTv2 Grid " + gridLocation + " could not be created.");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isNTv2GridFileValid(URL url) {
/* 138 */     RandomAccessFile raf = null;
/* 139 */     InputStream is = null;
/*     */     try {
/* 145 */       if (url.getProtocol().equals("file")) {
/* 146 */         File file = DataUtilities.urlToFile(url);
/* 148 */         if (!file.exists() || !file.canRead())
/* 149 */           throw new IOException(Errors.format(50, file)); 
/* 152 */         raf = new RandomAccessFile(file, "r");
/* 155 */         (new GridShiftFile()).loadGridShiftFile(raf);
/*     */       } else {
/* 157 */         InputStream in = new BufferedInputStream(url.openConnection().getInputStream());
/* 160 */         (new GridShiftFile()).loadGridShiftFile(in, false);
/*     */       } 
/* 163 */       return true;
/* 164 */     } catch (IllegalArgumentException e) {
/* 167 */       LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
/* 168 */       return false;
/* 169 */     } catch (IOException e) {
/* 170 */       LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
/* 171 */       return false;
/*     */     } finally {
/*     */       try {
/* 174 */         if (raf != null)
/* 175 */           raf.close(); 
/* 176 */       } catch (IOException e) {}
/*     */       try {
/* 180 */         if (is != null)
/* 181 */           is.close(); 
/* 182 */       } catch (IOException e) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   private GridShiftFile loadNTv2Grid(URL location) throws FactoryException {
/* 197 */     InputStream in = null;
/*     */     try {
/* 199 */       GridShiftFile grid = new GridShiftFile();
/* 200 */       in = new BufferedInputStream(location.openStream());
/* 201 */       grid.loadGridShiftFile(in, false);
/* 202 */       in.close();
/* 203 */       return grid;
/* 204 */     } catch (FileNotFoundException e) {
/* 205 */       LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
/* 206 */       return null;
/* 207 */     } catch (IOException e) {
/* 208 */       LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
/* 209 */       return null;
/* 210 */     } catch (IllegalArgumentException e) {
/* 211 */       LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
/* 212 */       throw new FactoryException(e.getLocalizedMessage(), e);
/*     */     } finally {
/*     */       try {
/* 215 */         if (in != null)
/* 216 */           in.close(); 
/* 218 */       } catch (IOException e) {}
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\gridshift\NTv2GridShiftFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */