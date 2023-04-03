/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.referencing.operation.matrix.GeneralMatrix;
/*     */ import org.geotools.referencing.operation.transform.ProjectiveTransform;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class WorldFileReader {
/*     */   public static final int DEFAULT_BUFFER_SIZE = 4096;
/*     */   
/*  79 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data.data");
/*     */   
/*  83 */   private double xPixelSize = 0.0D;
/*     */   
/*  86 */   private double rotationX = 0.0D;
/*     */   
/*  89 */   private double rotationY = 0.0D;
/*     */   
/*  92 */   private double yPixelSize = 0.0D;
/*     */   
/*  95 */   private double xULC = 0.0D;
/*     */   
/*  98 */   private double yULC = 0.0D;
/*     */   
/*     */   private GeneralMatrix transform;
/*     */   
/*     */   public WorldFileReader(File inFile) throws IOException {
/* 110 */     this(inFile, 4096);
/*     */   }
/*     */   
/*     */   public WorldFileReader(File worldfile, int bufferSize) throws IOException {
/* 122 */     if (worldfile == null)
/* 123 */       throw new IllegalArgumentException(Errors.format(143, "worldfile")); 
/* 124 */     if (!worldfile.isFile() || !worldfile.canRead())
/* 125 */       throw new IllegalArgumentException(Errors.format(50, worldfile)); 
/* 126 */     if (bufferSize <= 0)
/* 127 */       throw new IllegalArgumentException(Errors.format(58, "bufferSize", Integer.valueOf(bufferSize))); 
/* 128 */     parseWorldFile(new BufferedReader(new FileReader(worldfile)));
/*     */   }
/*     */   
/*     */   public WorldFileReader(URL worldfile, int bufferSize) throws IOException {
/* 141 */     if (worldfile == null)
/* 142 */       throw new IllegalArgumentException(Errors.format(143, "inFile")); 
/* 143 */     if (bufferSize <= 0)
/* 144 */       throw new IllegalArgumentException(Errors.format(58, "bufferSize", Integer.valueOf(bufferSize))); 
/* 145 */     parseWorldFile(new BufferedReader(new InputStreamReader(worldfile.openStream())));
/*     */   }
/*     */   
/*     */   public WorldFileReader(URL worldfile) throws IOException {
/* 158 */     this(worldfile, 4096);
/*     */   }
/*     */   
/*     */   private void parseWorldFile(BufferedReader bufferedreader) throws IOException, DataSourceException {
/* 163 */     int index = 0;
/*     */     try {
/*     */       String str;
/* 166 */       while ((str = bufferedreader.readLine()) != null) {
/* 168 */         double value = 0.0D;
/*     */         try {
/* 171 */           value = Double.parseDouble(str.trim());
/* 172 */         } catch (Throwable t) {
/* 174 */           if (LOGGER.isLoggable(Level.FINE))
/* 175 */             LOGGER.log(Level.FINE, t.getLocalizedMessage(), t); 
/*     */           continue;
/*     */         } 
/* 179 */         switch (index) {
/*     */           case 0:
/* 181 */             this.xPixelSize = value;
/*     */             break;
/*     */           case 1:
/* 186 */             this.rotationX = value;
/*     */             break;
/*     */           case 2:
/* 191 */             this.rotationY = value;
/*     */             break;
/*     */           case 3:
/* 196 */             this.yPixelSize = value;
/*     */             break;
/*     */           case 4:
/* 201 */             this.xULC = value;
/*     */             break;
/*     */           case 5:
/* 206 */             this.yULC = value;
/*     */             break;
/*     */         } 
/* 214 */         index++;
/*     */       } 
/* 216 */     } catch (Exception e) {
/*     */       try {
/* 220 */         bufferedreader.close();
/* 221 */       } catch (Throwable t) {
/* 223 */         if (LOGGER.isLoggable(Level.FINE))
/* 224 */           LOGGER.log(Level.FINE, t.getLocalizedMessage(), t); 
/*     */       } 
/*     */     } finally {
/*     */       try {
/*     */         bufferedreader.close();
/*     */       } catch (Throwable t) {
/*     */         if (LOGGER.isLoggable(Level.FINE))
/* 224 */           LOGGER.log(Level.FINE, t.getLocalizedMessage(), t); 
/*     */       } 
/*     */     } 
/* 230 */     if (index < 5)
/* 231 */       throw new DataSourceException("Not all the values were found for this world file!"); 
/*     */   }
/*     */   
/*     */   public double getRotationX() {
/* 236 */     return this.rotationX;
/*     */   }
/*     */   
/*     */   public double getRotationY() {
/* 240 */     return this.rotationY;
/*     */   }
/*     */   
/*     */   public double getXPixelSize() {
/* 244 */     return this.xPixelSize;
/*     */   }
/*     */   
/*     */   public double getXULC() {
/* 248 */     return this.xULC;
/*     */   }
/*     */   
/*     */   public double getYPixelSize() {
/* 252 */     return this.yPixelSize;
/*     */   }
/*     */   
/*     */   public double getYULC() {
/* 256 */     return this.yULC;
/*     */   }
/*     */   
/*     */   public synchronized MathTransform getTransform() {
/* 260 */     initTransform();
/* 261 */     return (MathTransform)ProjectiveTransform.create((Matrix)this.transform);
/*     */   }
/*     */   
/*     */   private void initTransform() {
/* 265 */     if (this.transform == null) {
/* 267 */       GeneralMatrix gm = new GeneralMatrix(3);
/* 270 */       gm.setElement(0, 0, this.xPixelSize);
/* 271 */       gm.setElement(1, 1, this.yPixelSize);
/* 272 */       gm.setElement(0, 1, this.rotationX);
/* 273 */       gm.setElement(1, 0, this.rotationY);
/* 275 */       gm.setElement(0, 2, this.xULC);
/* 276 */       gm.setElement(1, 2, this.yULC);
/* 279 */       this.transform = gm;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized AffineTransform getAffineTransform() {
/* 290 */     initTransform();
/* 291 */     return this.transform.toAffineTransform2D();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\WorldFileReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */