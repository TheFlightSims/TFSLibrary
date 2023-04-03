/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Paint;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ 
/*     */ public class PaintUtilities {
/*     */   public static boolean equal(Paint p1, Paint p2) {
/*  79 */     if (p1 == null)
/*  80 */       return (p2 == null); 
/*  82 */     if (p2 == null)
/*  83 */       return false; 
/*  86 */     boolean result = false;
/*  88 */     if (p1 instanceof GradientPaint && p2 instanceof GradientPaint) {
/*  89 */       GradientPaint gp1 = (GradientPaint)p1;
/*  90 */       GradientPaint gp2 = (GradientPaint)p2;
/*  91 */       result = (gp1.getColor1().equals(gp2.getColor1()) && gp1.getColor2().equals(gp2.getColor2()) && gp1.getPoint1().equals(gp2.getPoint1()) && gp1.getPoint2().equals(gp2.getPoint2()) && gp1.isCyclic() == gp2.isCyclic() && gp1.getTransparency() == gp1.getTransparency());
/*     */     } else {
/*  99 */       result = p1.equals(p2);
/*     */     } 
/* 101 */     return result;
/*     */   }
/*     */   
/*     */   public static String colorToString(Color c) {
/*     */     try {
/* 116 */       Field[] fields = Color.class.getFields();
/* 117 */       for (int j = 0; j < fields.length; j++) {
/* 118 */         Field f = fields[j];
/* 119 */         if (Modifier.isPublic(f.getModifiers()) && Modifier.isFinal(f.getModifiers()) && Modifier.isStatic(f.getModifiers())) {
/* 122 */           String name = f.getName();
/* 123 */           Object oColor = f.get(null);
/* 124 */           if (oColor instanceof Color && 
/* 125 */             c.equals(oColor))
/* 126 */             return name; 
/*     */         } 
/*     */       } 
/* 132 */     } catch (Exception e) {}
/* 137 */     String color = Integer.toHexString(c.getRGB() & 0xFFFFFF);
/* 138 */     StringBuffer retval = new StringBuffer(7);
/* 139 */     retval.append("#");
/* 141 */     int fillUp = 6 - color.length();
/* 142 */     for (int i = 0; i < fillUp; i++)
/* 143 */       retval.append("0"); 
/* 146 */     retval.append(color);
/* 147 */     return retval.toString();
/*     */   }
/*     */   
/*     */   public static Color stringToColor(String value) {
/* 158 */     if (value == null)
/* 159 */       return Color.black; 
/*     */     try {
/* 163 */       return Color.decode(value);
/* 165 */     } catch (NumberFormatException nfe) {
/*     */       try {
/* 169 */         Field f = Color.class.getField(value);
/* 171 */         return (Color)f.get(null);
/* 173 */       } catch (Exception ce) {
/* 174 */         Log.info("No such Color : " + value);
/* 176 */         return Color.black;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\PaintUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */