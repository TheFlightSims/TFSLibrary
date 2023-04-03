/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Location;
/*     */ 
/*     */ public class TopologyLocation {
/*     */   int[] location;
/*     */   
/*     */   public TopologyLocation(int[] location) {
/*  70 */     init(location.length);
/*     */   }
/*     */   
/*     */   public TopologyLocation(int on, int left, int right) {
/*  80 */     init(3);
/*  81 */     this.location[0] = on;
/*  82 */     this.location[1] = left;
/*  83 */     this.location[2] = right;
/*     */   }
/*     */   
/*     */   public TopologyLocation(int on) {
/*  87 */     init(1);
/*  88 */     this.location[0] = on;
/*     */   }
/*     */   
/*     */   public TopologyLocation(TopologyLocation gl) {
/*  91 */     init(gl.location.length);
/*  92 */     if (gl != null)
/*  93 */       for (int i = 0; i < this.location.length; i++)
/*  94 */         this.location[i] = gl.location[i];  
/*     */   }
/*     */   
/*     */   private void init(int size) {
/* 100 */     this.location = new int[size];
/* 101 */     setAllLocations(-1);
/*     */   }
/*     */   
/*     */   public int get(int posIndex) {
/* 105 */     if (posIndex < this.location.length)
/* 105 */       return this.location[posIndex]; 
/* 106 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean isNull() {
/* 113 */     for (int i = 0; i < this.location.length; i++) {
/* 114 */       if (this.location[i] != -1)
/* 114 */         return false; 
/*     */     } 
/* 116 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isAnyNull() {
/* 123 */     for (int i = 0; i < this.location.length; i++) {
/* 124 */       if (this.location[i] == -1)
/* 124 */         return true; 
/*     */     } 
/* 126 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEqualOnSide(TopologyLocation le, int locIndex) {
/* 130 */     return (this.location[locIndex] == le.location[locIndex]);
/*     */   }
/*     */   
/*     */   public boolean isArea() {
/* 132 */     return (this.location.length > 1);
/*     */   }
/*     */   
/*     */   public boolean isLine() {
/* 133 */     return (this.location.length == 1);
/*     */   }
/*     */   
/*     */   public void flip() {
/* 137 */     if (this.location.length <= 1)
/*     */       return; 
/* 138 */     int temp = this.location[1];
/* 139 */     this.location[1] = this.location[2];
/* 140 */     this.location[2] = temp;
/*     */   }
/*     */   
/*     */   public void setAllLocations(int locValue) {
/* 146 */     for (int i = 0; i < this.location.length; i++)
/* 147 */       this.location[i] = locValue; 
/*     */   }
/*     */   
/*     */   public void setAllLocationsIfNull(int locValue) {
/* 152 */     for (int i = 0; i < this.location.length; i++) {
/* 153 */       if (this.location[i] == -1)
/* 153 */         this.location[i] = locValue; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setLocation(int locIndex, int locValue) {
/* 159 */     this.location[locIndex] = locValue;
/*     */   }
/*     */   
/*     */   public void setLocation(int locValue) {
/* 163 */     setLocation(0, locValue);
/*     */   }
/*     */   
/*     */   public int[] getLocations() {
/* 165 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocations(int on, int left, int right) {
/* 167 */     this.location[0] = on;
/* 168 */     this.location[1] = left;
/* 169 */     this.location[2] = right;
/*     */   }
/*     */   
/*     */   public boolean allPositionsEqual(int loc) {
/* 173 */     for (int i = 0; i < this.location.length; i++) {
/* 174 */       if (this.location[i] != loc)
/* 174 */         return false; 
/*     */     } 
/* 176 */     return true;
/*     */   }
/*     */   
/*     */   public void merge(TopologyLocation gl) {
/* 186 */     if (gl.location.length > this.location.length) {
/* 187 */       int[] newLoc = new int[3];
/* 188 */       newLoc[0] = this.location[0];
/* 189 */       newLoc[1] = -1;
/* 190 */       newLoc[2] = -1;
/* 191 */       this.location = newLoc;
/*     */     } 
/* 193 */     for (int i = 0; i < this.location.length; i++) {
/* 194 */       if (this.location[i] == -1 && i < gl.location.length)
/* 195 */         this.location[i] = gl.location[i]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 201 */     StringBuffer buf = new StringBuffer();
/* 202 */     if (this.location.length > 1)
/* 202 */       buf.append(Location.toLocationSymbol(this.location[1])); 
/* 203 */     buf.append(Location.toLocationSymbol(this.location[0]));
/* 204 */     if (this.location.length > 1)
/* 204 */       buf.append(Location.toLocationSymbol(this.location[2])); 
/* 205 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\TopologyLocation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */