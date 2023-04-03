/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class RobustDeterminant {
/*     */   public static int signOfDet2x2(double x1, double y1, double x2, double y2) {
/* 123 */     long count = 0L;
/* 127 */     int sign = 1;
/* 132 */     if (x1 == 0.0D || y2 == 0.0D) {
/* 133 */       if (y1 == 0.0D || x2 == 0.0D)
/* 134 */         return 0; 
/* 136 */       if (y1 > 0.0D) {
/* 137 */         if (x2 > 0.0D)
/* 138 */           return -sign; 
/* 141 */         return sign;
/*     */       } 
/* 145 */       if (x2 > 0.0D)
/* 146 */         return sign; 
/* 149 */       return -sign;
/*     */     } 
/* 153 */     if (y1 == 0.0D || x2 == 0.0D) {
/* 154 */       if (y2 > 0.0D) {
/* 155 */         if (x1 > 0.0D)
/* 156 */           return sign; 
/* 159 */         return -sign;
/*     */       } 
/* 163 */       if (x1 > 0.0D)
/* 164 */         return -sign; 
/* 167 */       return sign;
/*     */     } 
/* 178 */     if (0.0D < y1) {
/* 179 */       if (0.0D < y2) {
/* 180 */         if (y1 > y2) {
/* 184 */           sign = -sign;
/* 185 */           double swap = x1;
/* 186 */           x1 = x2;
/* 187 */           x2 = swap;
/* 188 */           swap = y1;
/* 189 */           y1 = y2;
/* 190 */           y2 = swap;
/*     */         } 
/* 194 */       } else if (y1 <= -y2) {
/* 195 */         sign = -sign;
/* 196 */         x2 = -x2;
/* 197 */         y2 = -y2;
/*     */       } else {
/* 200 */         double swap = x1;
/* 201 */         x1 = -x2;
/* 202 */         x2 = swap;
/* 203 */         swap = y1;
/* 204 */         y1 = -y2;
/* 205 */         y2 = swap;
/*     */       } 
/* 210 */     } else if (0.0D < y2) {
/* 211 */       if (-y1 <= y2) {
/* 212 */         sign = -sign;
/* 213 */         x1 = -x1;
/* 214 */         y1 = -y1;
/*     */       } else {
/* 217 */         double swap = -x1;
/* 218 */         x1 = x2;
/* 219 */         x2 = swap;
/* 220 */         swap = -y1;
/* 221 */         y1 = y2;
/* 222 */         y2 = swap;
/*     */       } 
/* 226 */     } else if (y1 >= y2) {
/* 227 */       x1 = -x1;
/* 228 */       y1 = -y1;
/* 229 */       x2 = -x2;
/* 230 */       y2 = -y2;
/*     */     } else {
/* 234 */       sign = -sign;
/* 235 */       double swap = -x1;
/* 236 */       x1 = -x2;
/* 237 */       x2 = swap;
/* 238 */       swap = -y1;
/* 239 */       y1 = -y2;
/* 240 */       y2 = swap;
/*     */     } 
/* 251 */     if (0.0D < x1) {
/* 252 */       if (0.0D < x2) {
/* 253 */         if (x1 > x2)
/* 257 */           return sign; 
/*     */       } else {
/* 261 */         return sign;
/*     */       } 
/*     */     } else {
/* 265 */       if (0.0D < x2)
/* 266 */         return -sign; 
/* 269 */       if (x1 >= x2) {
/* 270 */         sign = -sign;
/* 271 */         x1 = -x1;
/* 272 */         x2 = -x2;
/*     */       } else {
/* 276 */         return -sign;
/*     */       } 
/*     */     } 
/*     */     while (true) {
/* 285 */       count++;
/* 288 */       double k = Math.floor(x2 / x1);
/* 289 */       x2 -= k * x1;
/* 290 */       y2 -= k * y1;
/* 295 */       if (y2 < 0.0D)
/* 296 */         return -sign; 
/* 298 */       if (y2 > y1)
/* 299 */         return sign; 
/* 305 */       if (x1 > x2 + x2) {
/* 306 */         if (y1 < y2 + y2)
/* 307 */           return sign; 
/*     */       } else {
/* 311 */         if (y1 > y2 + y2)
/* 312 */           return -sign; 
/* 315 */         x2 = x1 - x2;
/* 316 */         y2 = y1 - y2;
/* 317 */         sign = -sign;
/*     */       } 
/* 320 */       if (y2 == 0.0D) {
/* 321 */         if (x2 == 0.0D)
/* 322 */           return 0; 
/* 325 */         return -sign;
/*     */       } 
/* 328 */       if (x2 == 0.0D)
/* 329 */         return sign; 
/* 337 */       k = Math.floor(x1 / x2);
/* 338 */       x1 -= k * x2;
/* 339 */       y1 -= k * y2;
/* 344 */       if (y1 < 0.0D)
/* 345 */         return sign; 
/* 347 */       if (y1 > y2)
/* 348 */         return -sign; 
/* 354 */       if (x2 > x1 + x1) {
/* 355 */         if (y2 < y1 + y1)
/* 356 */           return -sign; 
/*     */       } else {
/* 360 */         if (y2 > y1 + y1)
/* 361 */           return sign; 
/* 364 */         x1 = x2 - x1;
/* 365 */         y1 = y2 - y1;
/* 366 */         sign = -sign;
/*     */       } 
/* 369 */       if (y1 == 0.0D) {
/* 370 */         if (x1 == 0.0D)
/* 371 */           return 0; 
/* 374 */         return sign;
/*     */       } 
/* 377 */       if (x1 == 0.0D)
/* 378 */         return -sign; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int orientationIndex(Coordinate p1, Coordinate p2, Coordinate q) {
/* 417 */     double dx1 = p2.x - p1.x;
/* 418 */     double dy1 = p2.y - p1.y;
/* 419 */     double dx2 = q.x - p2.x;
/* 420 */     double dy2 = q.y - p2.y;
/* 421 */     return signOfDet2x2(dx1, dy1, dx2, dy2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\RobustDeterminant.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */