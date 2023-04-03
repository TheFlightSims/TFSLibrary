/*     */ package org.apache.commons.math3.geometry.euclidean.twod;
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
/*     */ public class Vector2DFormat extends VectorFormat<Euclidean2D> {
/*     */   public Vector2DFormat() {
/*  52 */     super("{", "}", "; ", CompositeFormat.getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   public Vector2DFormat(NumberFormat format) {
/*  61 */     super("{", "}", "; ", format);
/*     */   }
/*     */   
/*     */   public Vector2DFormat(String prefix, String suffix, String separator) {
/*  72 */     super(prefix, suffix, separator, CompositeFormat.getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */   public Vector2DFormat(String prefix, String suffix, String separator, NumberFormat format) {
/*  85 */     super(prefix, suffix, separator, format);
/*     */   }
/*     */   
/*     */   public static Vector2DFormat getInstance() {
/*  93 */     return getInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public static Vector2DFormat getInstance(Locale locale) {
/* 102 */     return new Vector2DFormat(CompositeFormat.getDefaultNumberFormat(locale));
/*     */   }
/*     */   
/*     */   public StringBuffer format(Vector<Euclidean2D> vector, StringBuffer toAppendTo, FieldPosition pos) {
/* 109 */     Vector2D p2 = (Vector2D)vector;
/* 110 */     return format(toAppendTo, pos, new double[] { p2.getX(), p2.getY() });
/*     */   }
/*     */   
/*     */   public Vector2D parse(String source) {
/* 116 */     ParsePosition parsePosition = new ParsePosition(0);
/* 117 */     Vector2D result = parse(source, parsePosition);
/* 118 */     if (parsePosition.getIndex() == 0)
/* 119 */       throw new MathParseException(source, parsePosition.getErrorIndex(), Vector2D.class); 
/* 123 */     return result;
/*     */   }
/*     */   
/*     */   public Vector2D parse(String source, ParsePosition pos) {
/* 129 */     double[] coordinates = parseCoordinates(2, source, pos);
/* 130 */     if (coordinates == null)
/* 131 */       return null; 
/* 133 */     return new Vector2D(coordinates[0], coordinates[1]);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\twod\Vector2DFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */