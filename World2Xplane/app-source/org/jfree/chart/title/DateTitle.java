/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import org.jfree.ui.HorizontalAlignment;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.VerticalAlignment;
/*     */ 
/*     */ public class DateTitle extends TextTitle implements Serializable {
/*     */   private static final long serialVersionUID = -465434812763159881L;
/*     */   
/*     */   public DateTitle() {
/*  92 */     this(1);
/*     */   }
/*     */   
/*     */   public DateTitle(int style) {
/* 107 */     this(style, Locale.getDefault(), new Font("Dialog", 0, 12), Color.black);
/*     */   }
/*     */   
/*     */   public DateTitle(int style, Locale locale, Font font, Paint paint) {
/* 129 */     this(style, locale, font, paint, RectangleEdge.BOTTOM, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, Title.DEFAULT_PADDING);
/*     */   }
/*     */   
/*     */   public DateTitle(int style, Locale locale, Font font, Paint paint, RectangleEdge position, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, RectangleInsets padding) {
/* 166 */     super(DateFormat.getDateInstance(style, locale).format(new Date()), font, paint, position, horizontalAlignment, verticalAlignment, padding);
/*     */   }
/*     */   
/*     */   public void setDateFormat(int style, Locale locale) {
/* 188 */     setText(DateFormat.getDateInstance(style, locale).format(new Date()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\title\DateTitle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */