/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class CoordinateList extends ArrayList {
/*  53 */   private static final Coordinate[] coordArrayType = new Coordinate[0];
/*     */   
/*     */   public CoordinateList() {}
/*     */   
/*     */   public CoordinateList(Coordinate[] coord) {
/*  71 */     ensureCapacity(coord.length);
/*  72 */     add(coord, true);
/*     */   }
/*     */   
/*     */   public CoordinateList(Coordinate[] coord, boolean allowRepeated) {
/*  84 */     ensureCapacity(coord.length);
/*  85 */     add(coord, allowRepeated);
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/*  88 */     return (Coordinate)get(i);
/*     */   }
/*     */   
/*     */   public boolean add(Coordinate[] coord, boolean allowRepeated, int start, int end) {
/* 101 */     int inc = 1;
/* 102 */     if (start > end)
/* 102 */       inc = -1; 
/*     */     int i;
/* 104 */     for (i = start; i != end; i += inc)
/* 105 */       add(coord[i], allowRepeated); 
/* 107 */     return true;
/*     */   }
/*     */   
/*     */   public boolean add(Coordinate[] coord, boolean allowRepeated, boolean direction) {
/* 119 */     if (direction) {
/* 120 */       for (int i = 0; i < coord.length; i++)
/* 121 */         add(coord[i], allowRepeated); 
/*     */     } else {
/* 125 */       for (int i = coord.length - 1; i >= 0; i--)
/* 126 */         add(coord[i], allowRepeated); 
/*     */     } 
/* 129 */     return true;
/*     */   }
/*     */   
/*     */   public boolean add(Coordinate[] coord, boolean allowRepeated) {
/* 141 */     add(coord, allowRepeated, true);
/* 142 */     return true;
/*     */   }
/*     */   
/*     */   public boolean add(Object obj, boolean allowRepeated) {
/* 153 */     add((Coordinate)obj, allowRepeated);
/* 154 */     return true;
/*     */   }
/*     */   
/*     */   public void add(Coordinate coord, boolean allowRepeated) {
/* 166 */     if (!allowRepeated && 
/* 167 */       size() >= 1) {
/* 168 */       Coordinate last = (Coordinate)get(size() - 1);
/* 169 */       if (last.equals2D(coord))
/*     */         return; 
/*     */     } 
/* 172 */     add((E)coord);
/*     */   }
/*     */   
/*     */   public void add(int i, Coordinate coord, boolean allowRepeated) {
/* 185 */     if (!allowRepeated) {
/* 186 */       int size = size();
/* 187 */       if (size > 0) {
/* 188 */         if (i > 0) {
/* 189 */           Coordinate prev = (Coordinate)get(i - 1);
/* 190 */           if (prev.equals2D(coord))
/*     */             return; 
/*     */         } 
/* 192 */         if (i < size) {
/* 193 */           Coordinate next = (Coordinate)get(i);
/* 194 */           if (next.equals2D(coord))
/*     */             return; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 198 */     add(i, (E)coord);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll, boolean allowRepeated) {
/* 208 */     boolean isChanged = false;
/* 209 */     for (Iterator<Coordinate> i = coll.iterator(); i.hasNext(); ) {
/* 210 */       add(i.next(), allowRepeated);
/* 211 */       isChanged = true;
/*     */     } 
/* 213 */     return isChanged;
/*     */   }
/*     */   
/*     */   public void closeRing() {
/* 221 */     if (size() > 0)
/* 222 */       add(new Coordinate((Coordinate)get(0)), false); 
/*     */   }
/*     */   
/*     */   public Coordinate[] toCoordinateArray() {
/* 231 */     return toArray(coordArrayType);
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 240 */     CoordinateList clone = (CoordinateList)super.clone();
/* 241 */     for (int i = 0; i < size(); i++)
/* 242 */       clone.add(i, (E)((Coordinate)get(i)).clone()); 
/* 244 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\CoordinateList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */