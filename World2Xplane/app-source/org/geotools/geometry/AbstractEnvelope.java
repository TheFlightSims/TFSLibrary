/*     */ package org.geotools.geometry;
/*     */ 
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.Envelope;
/*     */ import org.opengis.geometry.MismatchedReferenceSystemException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public abstract class AbstractEnvelope implements Envelope {
/*     */   static CoordinateReferenceSystem getCoordinateReferenceSystem(DirectPosition minDP, DirectPosition maxDP) throws MismatchedReferenceSystemException {
/*  63 */     CoordinateReferenceSystem crs1 = minDP.getCoordinateReferenceSystem();
/*  64 */     CoordinateReferenceSystem crs2 = maxDP.getCoordinateReferenceSystem();
/*  65 */     if (crs1 == null)
/*  66 */       return crs2; 
/*  68 */     if (crs2 != null && !crs1.equals(crs2))
/*  69 */       throw new MismatchedReferenceSystemException(Errors.format(92)); 
/*  72 */     return crs1;
/*     */   }
/*     */   
/*     */   public DirectPosition getLowerCorner() {
/*  84 */     return new LowerCorner();
/*     */   }
/*     */   
/*     */   public DirectPosition getUpperCorner() {
/*  95 */     return new UpperCorner();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 110 */     return toString(this);
/*     */   }
/*     */   
/*     */   static String toString(Envelope envelope) {
/* 119 */     StringBuilder buffer = new StringBuilder(Classes.getShortClassName(envelope));
/* 120 */     int dimension = envelope.getDimension();
/* 121 */     if (dimension != 0) {
/* 122 */       String separator = "[(";
/*     */       int i;
/* 123 */       for (i = 0; i < dimension; i++) {
/* 124 */         buffer.append(separator).append(envelope.getMinimum(i));
/* 125 */         separator = ", ";
/*     */       } 
/* 127 */       separator = "), (";
/* 128 */       for (i = 0; i < dimension; i++) {
/* 129 */         buffer.append(separator).append(envelope.getMaximum(i));
/* 130 */         separator = ", ";
/*     */       } 
/* 132 */       buffer.append(")]");
/*     */     } 
/* 134 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 142 */     int dimension = getDimension();
/* 143 */     int code = 1;
/* 144 */     boolean p = true;
/*     */     while (true) {
/* 146 */       for (int i = 0; i < dimension; i++) {
/* 147 */         long bits = Double.doubleToLongBits(p ? getMinimum(i) : getMaximum(i));
/* 148 */         code = 31 * code + ((int)bits ^ (int)(bits >>> 32L));
/*     */       } 
/* 150 */       if (p = !p) {
/* 151 */         CoordinateReferenceSystem crs = getCoordinateReferenceSystem();
/* 152 */         if (crs != null)
/* 153 */           code += crs.hashCode(); 
/* 155 */         return code;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 171 */     if (object != null && object.getClass().equals(getClass())) {
/* 172 */       Envelope that = (Envelope)object;
/* 173 */       int dimension = getDimension();
/* 174 */       if (dimension == that.getDimension()) {
/* 175 */         for (int i = 0; i < dimension; i++) {
/* 176 */           if (!Utilities.equals(getMinimum(i), that.getMinimum(i)) || !Utilities.equals(getMaximum(i), that.getMaximum(i)))
/* 179 */             return false; 
/*     */         } 
/* 182 */         if (Utilities.equals(getCoordinateReferenceSystem(), that.getCoordinateReferenceSystem())) {
/* 185 */           assert hashCode() == that.hashCode() : this;
/* 186 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 190 */     return false;
/*     */   }
/*     */   
/*     */   private abstract class Corner extends AbstractDirectPosition {
/*     */     private Corner() {}
/*     */     
/*     */     public CoordinateReferenceSystem getCoordinateReferenceSystem() {
/* 200 */       return AbstractEnvelope.this.getCoordinateReferenceSystem();
/*     */     }
/*     */     
/*     */     public int getDimension() {
/* 205 */       return AbstractEnvelope.this.getDimension();
/*     */     }
/*     */     
/*     */     public void setOrdinate(int dimension, double value) {
/* 210 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   private final class LowerCorner extends Corner {
/*     */     private LowerCorner() {}
/*     */     
/*     */     public double getOrdinate(int dimension) throws IndexOutOfBoundsException {
/* 219 */       return AbstractEnvelope.this.getMinimum(dimension);
/*     */     }
/*     */   }
/*     */   
/*     */   private final class UpperCorner extends Corner {
/*     */     private UpperCorner() {}
/*     */     
/*     */     public double getOrdinate(int dimension) throws IndexOutOfBoundsException {
/* 228 */       return AbstractEnvelope.this.getMaximum(dimension);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\AbstractEnvelope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */