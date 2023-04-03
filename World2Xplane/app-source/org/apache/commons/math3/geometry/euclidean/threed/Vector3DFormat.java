/*     */ package org.apache.commons.math3.geometry.euclidean.threed;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.math3.exception.MathParseException;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.geometry.VectorFormat;
/*     */ import org.apache.commons.math3.util.CompositeFormat;
/*     */ 
/*     */ public class Vector3DFormat extends VectorFormat<Euclidean3D> {
/*     */   public Vector3DFormat() {
/*  51 */     super("{", "}", "; ", CompositeFormat.getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   public Vector3DFormat(NumberFormat format) {
/*  60 */     super("{", "}", "; ", format);
/*     */   }
/*     */   
/*     */   public Vector3DFormat(String prefix, String suffix, String separator) {
/*  71 */     super(prefix, suffix, separator, CompositeFormat.getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   public Vector3DFormat(String prefix, String suffix, String separator, NumberFormat format) {
/*  84 */     super(prefix, suffix, separator, format);
/*     */   }
/*     */   
/*     */   public static Vector3DFormat getInstance() {
/*  92 */     return getInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static Vector3DFormat getInstance(Locale locale) {
/* 101 */     return new Vector3DFormat(CompositeFormat.getDefaultNumberFormat(locale));
/*     */   }
/*     */   
/*     */   public StringBuffer format(Vector<Euclidean3D> vector, StringBuffer toAppendTo, FieldPosition pos) {
/* 115 */     Vector3D v3 = (Vector3D)vector;
/* 116 */     return format(toAppendTo, pos, new double[] { v3.getX(), v3.getY(), v3.getZ() });
/*     */   }
/*     */   
/*     */   public Vector3D parse(String source) {
/* 128 */     ParsePosition parsePosition = new ParsePosition(0);
/* 129 */     Vector3D result = parse(source, parsePosition);
/* 130 */     if (parsePosition.getIndex() == 0)
/* 131 */       throw new MathParseException(source, parsePosition.getErrorIndex(), Vector3D.class); 
/* 135 */     return result;
/*     */   }
/*     */   
/*     */   public Vector3D parse(String source, ParsePosition pos) {
/* 146 */     double[] coordinates = parseCoordinates(3, source, pos);
/* 147 */     if (coordinates == null)
/* 148 */       return null; 
/* 150 */     return new Vector3D(coordinates[0], coordinates[1], coordinates[2]);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\threed\Vector3DFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */