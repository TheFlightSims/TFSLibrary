/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.referencing.operation.transform.AffineTransform2D;
/*     */ import org.geotools.referencing.operation.transform.ProjectiveTransform;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class WorldFileWriter {
/*     */   public static final int DEFAULT_BUFFER_SIZE = 4096;
/*     */   
/*     */   private static AffineTransform checkTransform(AffineTransform transform) {
/*  83 */     if (transform == null)
/*  84 */       throw new IllegalArgumentException(Errors.format(143, "transform")); 
/*  85 */     return transform;
/*     */   }
/*     */   
/*     */   private static MathTransform checkMathTransform(MathTransform transform) {
/*  89 */     if (transform == null)
/*  90 */       throw new IllegalArgumentException(Errors.format(143, "transform")); 
/*  91 */     return transform;
/*     */   }
/*     */   
/*  97 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data.data");
/*     */   
/*     */   public WorldFileWriter(File outLocation, AffineTransform transform) throws IOException {
/* 109 */     this(outLocation, (MathTransform)ProjectiveTransform.create(checkTransform(transform)), 4096);
/*     */   }
/*     */   
/*     */   public WorldFileWriter(File outLocation, AffineTransform transform, int buffSize) throws IOException {
/* 124 */     this(outLocation, (MathTransform)ProjectiveTransform.create(checkTransform(transform)), buffSize);
/*     */   }
/*     */   
/*     */   public WorldFileWriter(OutputStream outLocation, AffineTransform transform) throws IOException {
/* 136 */     this(outLocation, (MathTransform)ProjectiveTransform.create(checkTransform(transform)));
/*     */   }
/*     */   
/*     */   public WorldFileWriter(OutputStream outLocation, AffineTransform transform, int buffSize) throws IOException {
/* 149 */     this(outLocation, (MathTransform)ProjectiveTransform.create(checkTransform(transform)), buffSize);
/*     */   }
/*     */   
/*     */   public WorldFileWriter(OutputStream outLocation, MathTransform transform) throws IOException {
/* 164 */     this(outLocation, transform, 4096);
/*     */   }
/*     */   
/*     */   public WorldFileWriter(OutputStream outLocation, MathTransform transform, int buffSize) throws IOException {
/* 180 */     if (outLocation == null)
/* 181 */       throw new NullPointerException(Errors.format(143, "outLocation")); 
/* 182 */     if (transform.getSourceDimensions() != 2 || transform.getTargetDimensions() != 2)
/* 183 */       throw new IllegalArgumentException(Errors.format(94, "transform", Integer.valueOf(transform.getSourceDimensions()), Integer.valueOf(2))); 
/* 184 */     if (buffSize <= 0)
/* 185 */       throw new IllegalArgumentException(Errors.format(58, "buffSize", Integer.valueOf(buffSize))); 
/* 186 */     write(new BufferedWriter(new OutputStreamWriter(outLocation), buffSize), transform);
/*     */   }
/*     */   
/*     */   private void write(BufferedWriter writer, MathTransform transform) {
/*     */     try {
/* 200 */       if (transform instanceof org.geotools.referencing.operation.transform.IdentityTransform) {
/* 201 */         writer.write("1");
/* 202 */         writer.newLine();
/* 203 */         writer.write("0");
/* 204 */         writer.newLine();
/* 205 */         writer.write("0");
/* 206 */         writer.newLine();
/* 207 */         writer.write("1");
/* 208 */         writer.newLine();
/* 209 */         writer.write("0");
/* 210 */         writer.newLine();
/* 211 */         writer.write("0");
/* 212 */         close(writer);
/*     */         return;
/*     */       } 
/* 215 */       if (transform instanceof AffineTransform2D) {
/* 216 */         AffineTransform2D affine = (AffineTransform2D)transform;
/* 217 */         writer.write(Double.toString(affine.getScaleX()));
/* 218 */         writer.newLine();
/* 219 */         writer.write(Double.toString(affine.getShearX()));
/* 220 */         writer.newLine();
/* 221 */         writer.write(Double.toString(affine.getShearY()));
/* 222 */         writer.newLine();
/* 223 */         writer.write(Double.toString(affine.getScaleY()));
/* 224 */         writer.newLine();
/* 225 */         writer.write(Double.toString(affine.getTranslateX()));
/* 226 */         writer.newLine();
/* 227 */         writer.write(Double.toString(affine.getTranslateY()));
/* 228 */         close(writer);
/*     */         return;
/*     */       } 
/* 231 */       assert false : transform;
/* 232 */     } catch (IOException e) {
/* 233 */       if (LOGGER.isLoggable(Level.SEVERE))
/* 234 */         LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e); 
/*     */     } finally {
/* 236 */       close(writer);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void close(BufferedWriter writer) {
/*     */     try {
/* 244 */       if (writer != null)
/* 245 */         writer.close(); 
/* 246 */     } catch (Throwable t) {
/* 247 */       if (LOGGER.isLoggable(Level.FINE))
/* 248 */         LOGGER.log(Level.FINE, t.getLocalizedMessage(), t); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public WorldFileWriter(File outLocation, MathTransform transform, int buffSize) throws IOException {
/* 267 */     if (outLocation == null)
/* 268 */       throw new NullPointerException(Errors.format(143, "outLocation")); 
/* 269 */     checkMathTransform(transform);
/* 270 */     if (transform.getSourceDimensions() != 2 || transform.getTargetDimensions() != 2)
/* 271 */       throw new IllegalArgumentException(Errors.format(94, "transform", Integer.valueOf(transform.getSourceDimensions()), Integer.valueOf(2))); 
/* 272 */     if (!outLocation.exists() && 
/* 273 */       !outLocation.createNewFile())
/* 274 */       throw new IOException(Errors.format(57, "Unable to create file " + outLocation)); 
/* 275 */     if (!outLocation.canWrite() || !outLocation.isFile())
/* 277 */       throw new IllegalArgumentException(Errors.format(50, outLocation)); 
/* 279 */     write(new BufferedWriter(new FileWriter(outLocation), buffSize), transform);
/*     */   }
/*     */   
/*     */   public WorldFileWriter(File outLocation, MathTransform transform) throws IOException {
/* 295 */     this(outLocation, transform, 4096);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\WorldFileWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */