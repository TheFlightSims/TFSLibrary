/*     */ package org.apache.commons.math3.geometry.euclidean.oned;
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
/*     */ public class Vector1DFormat extends VectorFormat<Euclidean1D> {
/*     */   public Vector1DFormat() {
/*  52 */     super("{", "}", "; ", CompositeFormat.getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   public Vector1DFormat(NumberFormat format) {
/*  61 */     super("{", "}", "; ", format);
/*     */   }
/*     */   
/*     */   public Vector1DFormat(String prefix, String suffix) {
/*  70 */     super(prefix, suffix, "; ", CompositeFormat.getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   public Vector1DFormat(String prefix, String suffix, NumberFormat format) {
/*  82 */     super(prefix, suffix, "; ", format);
/*     */   }
/*     */   
/*     */   public static Vector1DFormat getInstance() {
/*  90 */     return getInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static Vector1DFormat getInstance(Locale locale) {
/*  99 */     return new Vector1DFormat(CompositeFormat.getDefaultNumberFormat(locale));
/*     */   }
/*     */   
/*     */   public StringBuffer format(Vector<Euclidean1D> vector, StringBuffer toAppendTo, FieldPosition pos) {
/* 106 */     Vector1D p1 = (Vector1D)vector;
/* 107 */     return format(toAppendTo, pos, new double[] { p1.getX() });
/*     */   }
/*     */   
/*     */   public Vector1D parse(String source) {
/* 113 */     ParsePosition parsePosition = new ParsePosition(0);
/* 114 */     Vector1D result = parse(source, parsePosition);
/* 115 */     if (parsePosition.getIndex() == 0)
/* 116 */       throw new MathParseException(source, parsePosition.getErrorIndex(), Vector1D.class); 
/* 120 */     return result;
/*     */   }
/*     */   
/*     */   public Vector1D parse(String source, ParsePosition pos) {
/* 126 */     double[] coordinates = parseCoordinates(1, source, pos);
/* 127 */     if (coordinates == null)
/* 128 */       return null; 
/* 130 */     return new Vector1D(coordinates[0]);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\oned\Vector1DFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */