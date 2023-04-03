/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.geotools.geometry.DirectPosition2D;
/*     */ import org.geotools.geometry.GeneralDirectPosition;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class MappedPosition implements Serializable {
/*     */   private static final long serialVersionUID = 3262172371858749543L;
/*     */   
/*     */   private final DirectPosition source;
/*     */   
/*     */   private final DirectPosition target;
/*     */   
/*  68 */   private double accuracy = Double.NaN;
/*     */   
/*     */   private String comments;
/*     */   
/*     */   public MappedPosition(int dimension) {
/*  81 */     if (dimension == 2) {
/*  82 */       this.source = (DirectPosition)new DirectPosition2D();
/*  83 */       this.target = (DirectPosition)new DirectPosition2D();
/*     */     } else {
/*  85 */       this.source = (DirectPosition)new GeneralDirectPosition(dimension);
/*  86 */       this.target = (DirectPosition)new GeneralDirectPosition(dimension);
/*     */     } 
/*     */   }
/*     */   
/*     */   public MappedPosition(DirectPosition source, DirectPosition target) {
/*  97 */     ensureNonNull("source", source);
/*  98 */     ensureNonNull("target", target);
/*  99 */     this.source = source;
/* 100 */     this.target = target;
/*     */   }
/*     */   
/*     */   private static void ensureNonNull(String name, Object object) throws IllegalArgumentException {
/* 113 */     if (object == null)
/* 114 */       throw new IllegalArgumentException(Errors.format(143, name)); 
/*     */   }
/*     */   
/*     */   public DirectPosition getSource() {
/* 125 */     return this.source;
/*     */   }
/*     */   
/*     */   public void setSource(DirectPosition point) {
/* 132 */     if (this.source instanceof DirectPosition2D) {
/* 133 */       ((DirectPosition2D)this.source).setLocation(point);
/*     */     } else {
/* 135 */       ((GeneralDirectPosition)this.source).setLocation(point);
/*     */     } 
/*     */   }
/*     */   
/*     */   public DirectPosition getTarget() {
/* 146 */     return this.target;
/*     */   }
/*     */   
/*     */   public void setTarget(DirectPosition point) {
/* 153 */     if (this.source instanceof DirectPosition2D) {
/* 154 */       ((DirectPosition2D)this.target).setLocation(point);
/*     */     } else {
/* 156 */       ((GeneralDirectPosition)this.target).setLocation(point);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getComments() {
/* 164 */     return this.comments;
/*     */   }
/*     */   
/*     */   public void setComments(String comments) {
/* 171 */     this.comments = comments;
/*     */   }
/*     */   
/*     */   public double getAccuracy() {
/* 179 */     return this.accuracy;
/*     */   }
/*     */   
/*     */   public void setAccuracy(double accuracy) {
/* 186 */     this.accuracy = accuracy;
/*     */   }
/*     */   
/*     */   final double getError(MathTransform transform, DirectPosition buffer) throws TransformException {
/* 201 */     return distance(transform.transform(this.source, buffer), this.target);
/*     */   }
/*     */   
/*     */   private static double distance(DirectPosition source, DirectPosition target) {
/* 208 */     int otherDim = source.getDimension();
/* 209 */     int dimension = target.getDimension();
/* 210 */     if (otherDim != dimension)
/* 211 */       throw new MismatchedDimensionException(Errors.format(93, Integer.valueOf(otherDim), Integer.valueOf(dimension))); 
/* 214 */     double sum = 0.0D;
/* 215 */     for (int i = 0; i < dimension; i++) {
/* 216 */       double delta = source.getOrdinate(i) - target.getOrdinate(i);
/* 217 */       sum += delta * delta;
/*     */     } 
/* 219 */     return Math.sqrt(sum / dimension);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 226 */     return this.source.hashCode() + 37 * this.target.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 233 */     if (object != null && object.getClass().equals(getClass())) {
/* 234 */       MappedPosition that = (MappedPosition)object;
/* 235 */       return (Utilities.equals(this.source, that.source) && Utilities.equals(this.target, that.target) && Utilities.equals(this.comments, that.comments) && Utilities.equals(this.accuracy, that.accuracy));
/*     */     } 
/* 240 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 249 */     TableWriter table = new TableWriter(null, " ");
/* 250 */     table.write(Vocabulary.format(201));
/* 251 */     table.write(58);
/* 252 */     int dimension = this.source.getDimension();
/*     */     int i;
/* 253 */     for (i = 0; i < dimension; i++) {
/* 254 */       table.nextColumn();
/* 255 */       table.write(String.valueOf(this.source.getOrdinate(i)));
/*     */     } 
/* 257 */     table.nextLine();
/* 258 */     table.write(Vocabulary.format(212));
/* 259 */     table.write(58);
/* 260 */     dimension = this.target.getDimension();
/* 261 */     for (i = 0; i < dimension; i++) {
/* 262 */       table.nextColumn();
/* 263 */       table.write(String.valueOf(this.target.getOrdinate(i)));
/*     */     } 
/* 265 */     return table.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\MappedPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */