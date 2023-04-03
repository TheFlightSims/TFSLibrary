/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ public class Depth {
/*     */   private static final int NULL_VALUE = -1;
/*     */   
/*     */   public static int depthAtLocation(int location) {
/*  51 */     if (location == 2)
/*  51 */       return 0; 
/*  52 */     if (location == 0)
/*  52 */       return 1; 
/*  53 */     return -1;
/*     */   }
/*     */   
/*  56 */   private int[][] depth = new int[2][3];
/*     */   
/*     */   public Depth() {
/*  60 */     for (int i = 0; i < 2; i++) {
/*  61 */       for (int j = 0; j < 3; j++)
/*  62 */         this.depth[i][j] = -1; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getDepth(int geomIndex, int posIndex) {
/*  69 */     return this.depth[geomIndex][posIndex];
/*     */   }
/*     */   
/*     */   public void setDepth(int geomIndex, int posIndex, int depthValue) {
/*  73 */     this.depth[geomIndex][posIndex] = depthValue;
/*     */   }
/*     */   
/*     */   public int getLocation(int geomIndex, int posIndex) {
/*  77 */     if (this.depth[geomIndex][posIndex] <= 0)
/*  77 */       return 2; 
/*  78 */     return 0;
/*     */   }
/*     */   
/*     */   public void add(int geomIndex, int posIndex, int location) {
/*  82 */     if (location == 0)
/*  83 */       this.depth[geomIndex][posIndex] = this.depth[geomIndex][posIndex] + 1; 
/*     */   }
/*     */   
/*     */   public boolean isNull() {
/*  90 */     for (int i = 0; i < 2; i++) {
/*  91 */       for (int j = 0; j < 3; j++) {
/*  92 */         if (this.depth[i][j] != -1)
/*  93 */           return false; 
/*     */       } 
/*     */     } 
/*  96 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isNull(int geomIndex) {
/* 100 */     return (this.depth[geomIndex][1] == -1);
/*     */   }
/*     */   
/*     */   public boolean isNull(int geomIndex, int posIndex) {
/* 104 */     return (this.depth[geomIndex][posIndex] == -1);
/*     */   }
/*     */   
/*     */   public void add(Label lbl) {
/* 108 */     for (int i = 0; i < 2; i++) {
/* 109 */       for (int j = 1; j < 3; j++) {
/* 110 */         int loc = lbl.getLocation(i, j);
/* 111 */         if (loc == 2 || loc == 0)
/* 113 */           if (isNull(i, j)) {
/* 114 */             this.depth[i][j] = depthAtLocation(loc);
/*     */           } else {
/* 117 */             this.depth[i][j] = this.depth[i][j] + depthAtLocation(loc);
/*     */           }  
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getDelta(int geomIndex) {
/* 124 */     return this.depth[geomIndex][2] - this.depth[geomIndex][1];
/*     */   }
/*     */   
/*     */   public void normalize() {
/* 136 */     for (int i = 0; i < 2; i++) {
/* 137 */       if (!isNull(i)) {
/* 138 */         int minDepth = this.depth[i][1];
/* 139 */         if (this.depth[i][2] < minDepth)
/* 140 */           minDepth = this.depth[i][2]; 
/* 142 */         if (minDepth < 0)
/* 142 */           minDepth = 0; 
/* 143 */         for (int j = 1; j < 3; j++) {
/* 144 */           int newValue = 0;
/* 145 */           if (this.depth[i][j] > minDepth)
/* 146 */             newValue = 1; 
/* 147 */           this.depth[i][j] = newValue;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 155 */     return "A: " + this.depth[0][1] + "," + this.depth[0][2] + " B: " + this.depth[1][1] + "," + this.depth[1][2];
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\Depth.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */