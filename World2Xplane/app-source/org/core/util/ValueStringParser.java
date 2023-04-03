/*     */ package org.core.util;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public final class ValueStringParser {
/*  37 */   private static final Pattern DEC_POINT_PATTERN = Pattern.compile("^(\\-?\\d+)\\.(\\d+)$");
/*     */   
/*     */   public static final Float parseOsmDecimal(String value, boolean allowNegative) {
/*     */     try {
/*  45 */       int weight = Integer.parseInt(value);
/*  46 */       if (weight >= 0 || allowNegative)
/*  47 */         return Float.valueOf(weight); 
/*  50 */     } catch (NumberFormatException nfe) {}
/*  54 */     Matcher matcher = DEC_POINT_PATTERN.matcher(value);
/*  56 */     if (matcher.matches()) {
/*  58 */       String stringBeforePoint = matcher.group(1);
/*  59 */       String stringAfterPoint = matcher.group(2);
/*  61 */       if (stringBeforePoint.length() > 0 || stringAfterPoint.length() > 0)
/*     */         try {
/*  65 */           boolean negative = stringBeforePoint.startsWith("-");
/*  67 */           float beforePoint = Integer.parseInt(stringBeforePoint);
/*  68 */           float afterPoint = Integer.parseInt(stringAfterPoint);
/*  70 */           double result = FastMath.abs(beforePoint) + FastMath.pow(10.0D, -stringAfterPoint.length()) * afterPoint;
/*  72 */           if (negative)
/*  72 */             result = -result; 
/*  74 */           if (result >= 0.0D || allowNegative)
/*  75 */             return Float.valueOf((float)result); 
/*  78 */         } catch (NumberFormatException nfe) {} 
/*     */     } 
/*  83 */     return null;
/*     */   }
/*     */   
/*  86 */   private static final Pattern KMH_PATTERN = Pattern.compile("^(\\d+)\\s*km/h$");
/*     */   
/*  87 */   private static final Pattern MPH_PATTERN = Pattern.compile("^(\\d+)\\s*mph$");
/*     */   
/*     */   private static final float KM_PER_MILE = 1.609344F;
/*     */   
/*     */   public static final Float parseSpeed(String value) {
/* 100 */     Float speed = parseOsmDecimal(value, false);
/* 101 */     if (speed != null)
/* 102 */       return speed; 
/* 107 */     Matcher kmhMatcher = KMH_PATTERN.matcher(value);
/* 108 */     if (kmhMatcher.matches()) {
/* 109 */       String kmhString = kmhMatcher.group(1);
/*     */       try {
/* 111 */         return Float.valueOf(Integer.parseInt(kmhString));
/* 112 */       } catch (NumberFormatException nfe) {}
/*     */     } 
/* 117 */     Matcher mphMatcher = MPH_PATTERN.matcher(value);
/* 118 */     if (mphMatcher.matches()) {
/* 119 */       String mphString = mphMatcher.group(1);
/*     */       try {
/* 121 */         int mph = Integer.parseInt(mphString);
/* 122 */         return Float.valueOf(1.609344F * mph);
/* 123 */       } catch (NumberFormatException nfe) {}
/*     */     } 
/* 128 */     return null;
/*     */   }
/*     */   
/* 131 */   private static final Pattern M_PATTERN = Pattern.compile("^([\\d\\.]+)\\s*m$");
/*     */   
/* 132 */   private static final Pattern KM_PATTERN = Pattern.compile("^([\\d\\.]+)\\s*km$");
/*     */   
/* 133 */   private static final Pattern MI_PATTERN = Pattern.compile("^([\\d\\.]+)\\s*mi$");
/*     */   
/* 134 */   private static final Pattern FEET_INCHES_PATTERN = Pattern.compile("^([\\d]+)'\\s*([\\d]+)\"");
/*     */   
/*     */   private static final double M_PER_MI = 1609.344D;
/*     */   
/*     */   private static final double M_PER_INCH = 0.02539999969303608D;
/*     */   
/*     */   public static final Float parseMeasure(String value) {
/* 148 */     Float measure = parseOsmDecimal(value, false);
/* 149 */     if (measure != null)
/* 150 */       return measure; 
/* 155 */     Matcher mMatcher = M_PATTERN.matcher(value);
/* 156 */     if (mMatcher.matches()) {
/* 157 */       String mString = mMatcher.group(1);
/* 158 */       return parseOsmDecimal(mString, false);
/*     */     } 
/* 163 */     Matcher kmMatcher = KM_PATTERN.matcher(value);
/* 164 */     if (kmMatcher.matches()) {
/* 165 */       String kmString = kmMatcher.group(1);
/* 166 */       float km = parseOsmDecimal(kmString, false).floatValue();
/* 167 */       return Float.valueOf(1000.0F * km);
/*     */     } 
/* 172 */     Matcher miMatcher = MI_PATTERN.matcher(value);
/* 173 */     if (miMatcher.matches()) {
/* 174 */       String miString = miMatcher.group(1);
/* 175 */       float mi = parseOsmDecimal(miString, false).floatValue();
/* 176 */       return Float.valueOf((float)(1609.344D * mi));
/*     */     } 
/* 181 */     Matcher feetInchesMatcher = FEET_INCHES_PATTERN.matcher(value);
/* 182 */     if (feetInchesMatcher.matches()) {
/* 183 */       String feetString = feetInchesMatcher.group(1);
/* 184 */       String inchesString = feetInchesMatcher.group(2);
/*     */       try {
/* 186 */         int feet = Integer.parseInt(feetString);
/* 187 */         int inches = Integer.parseInt(inchesString);
/* 188 */         if (feet >= 0 && inches >= 0 && inches < 12)
/* 189 */           return Float.valueOf((float)(0.02539999969303608D * (12 * feet + inches))); 
/* 191 */       } catch (NumberFormatException nfe) {}
/*     */     } 
/* 196 */     return null;
/*     */   }
/*     */   
/* 199 */   private static final Pattern T_PATTERN = Pattern.compile("^([\\d\\.]+)\\s*t$");
/*     */   
/*     */   public static Float parseWeight(String value) {
/* 210 */     Float weight = parseOsmDecimal(value, false);
/* 211 */     if (weight != null)
/* 212 */       return weight; 
/* 217 */     Matcher tMatcher = T_PATTERN.matcher(value);
/* 218 */     if (tMatcher.matches()) {
/* 219 */       String tString = tMatcher.group(1);
/* 220 */       return parseOsmDecimal(tString, false);
/*     */     } 
/* 225 */     return null;
/*     */   }
/*     */   
/* 229 */   private static final Pattern INCLINE_PATTERN = Pattern.compile("^(\\-?\\d+(?:\\.\\d+)?)\\s*%$");
/*     */   
/*     */   public static final Float parseIncline(String value) {
/* 238 */     Matcher inclineMatcher = INCLINE_PATTERN.matcher(value);
/* 239 */     if (inclineMatcher.matches()) {
/* 240 */       String inclineString = inclineMatcher.group(1);
/* 241 */       return parseOsmDecimal(inclineString, true);
/*     */     } 
/* 244 */     return null;
/*     */   }
/*     */   
/*     */   public static final Float parseAngle(String value) {
/* 257 */     Float measure = parseOsmDecimal(value, false);
/* 258 */     if (measure != null)
/* 259 */       return Float.valueOf(measure.floatValue() % 360.0F); 
/* 264 */     if ("N".equals(value))
/* 264 */       return Float.valueOf(0.0F); 
/* 265 */     if ("NNE".equals(value))
/* 265 */       return Float.valueOf(22.5F); 
/* 266 */     if ("NE".equals(value))
/* 266 */       return Float.valueOf(45.0F); 
/* 267 */     if ("ENE".equals(value))
/* 267 */       return Float.valueOf(67.5F); 
/* 268 */     if ("E".equals(value))
/* 268 */       return Float.valueOf(90.0F); 
/* 269 */     if ("ESE".equals(value))
/* 269 */       return Float.valueOf(112.5F); 
/* 270 */     if ("SE".equals(value))
/* 270 */       return Float.valueOf(135.0F); 
/* 271 */     if ("SSE".equals(value))
/* 271 */       return Float.valueOf(157.5F); 
/* 272 */     if ("S".equals(value))
/* 272 */       return Float.valueOf(180.0F); 
/* 273 */     if ("SSW".equals(value))
/* 273 */       return Float.valueOf(202.5F); 
/* 274 */     if ("SW".equals(value))
/* 274 */       return Float.valueOf(225.0F); 
/* 275 */     if ("WSW".equals(value))
/* 275 */       return Float.valueOf(247.5F); 
/* 276 */     if ("W".equals(value))
/* 276 */       return Float.valueOf(270.0F); 
/* 277 */     if ("WNW".equals(value))
/* 277 */       return Float.valueOf(292.5F); 
/* 278 */     if ("NW".equals(value))
/* 278 */       return Float.valueOf(315.0F); 
/* 279 */     if ("NNW".equals(value))
/* 279 */       return Float.valueOf(337.5F); 
/* 281 */     return null;
/*     */   }
/*     */   
/*     */   public static final Color parseColor(String value) {
/*     */     try {
/* 292 */       return Color.decode(value);
/* 293 */     } catch (NumberFormatException e) {
/* 294 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\cor\\util\ValueStringParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */