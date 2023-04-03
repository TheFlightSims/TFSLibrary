/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ public class Label {
/*     */   public static Label toLineLabel(Label label) {
/*  69 */     Label lineLabel = new Label(-1);
/*  70 */     for (int i = 0; i < 2; i++)
/*  71 */       lineLabel.setLocation(i, label.getLocation(i)); 
/*  73 */     return lineLabel;
/*     */   }
/*     */   
/*  76 */   TopologyLocation[] elt = new TopologyLocation[2];
/*     */   
/*     */   public Label(int onLoc) {
/*  84 */     this.elt[0] = new TopologyLocation(onLoc);
/*  85 */     this.elt[1] = new TopologyLocation(onLoc);
/*     */   }
/*     */   
/*     */   public Label(int geomIndex, int onLoc) {
/*  93 */     this.elt[0] = new TopologyLocation(-1);
/*  94 */     this.elt[1] = new TopologyLocation(-1);
/*  95 */     this.elt[geomIndex].setLocation(onLoc);
/*     */   }
/*     */   
/*     */   public Label(int onLoc, int leftLoc, int rightLoc) {
/* 103 */     this.elt[0] = new TopologyLocation(onLoc, leftLoc, rightLoc);
/* 104 */     this.elt[1] = new TopologyLocation(onLoc, leftLoc, rightLoc);
/*     */   }
/*     */   
/*     */   public Label(int geomIndex, int onLoc, int leftLoc, int rightLoc) {
/* 112 */     this.elt[0] = new TopologyLocation(-1, -1, -1);
/* 113 */     this.elt[1] = new TopologyLocation(-1, -1, -1);
/* 114 */     this.elt[geomIndex].setLocations(onLoc, leftLoc, rightLoc);
/*     */   }
/*     */   
/*     */   public Label(Label lbl) {
/* 121 */     this.elt[0] = new TopologyLocation(lbl.elt[0]);
/* 122 */     this.elt[1] = new TopologyLocation(lbl.elt[1]);
/*     */   }
/*     */   
/*     */   public void flip() {
/* 127 */     this.elt[0].flip();
/* 128 */     this.elt[1].flip();
/*     */   }
/*     */   
/*     */   public int getLocation(int geomIndex, int posIndex) {
/* 131 */     return this.elt[geomIndex].get(posIndex);
/*     */   }
/*     */   
/*     */   public int getLocation(int geomIndex) {
/* 132 */     return this.elt[geomIndex].get(0);
/*     */   }
/*     */   
/*     */   public void setLocation(int geomIndex, int posIndex, int location) {
/* 135 */     this.elt[geomIndex].setLocation(posIndex, location);
/*     */   }
/*     */   
/*     */   public void setLocation(int geomIndex, int location) {
/* 139 */     this.elt[geomIndex].setLocation(0, location);
/*     */   }
/*     */   
/*     */   public void setAllLocations(int geomIndex, int location) {
/* 143 */     this.elt[geomIndex].setAllLocations(location);
/*     */   }
/*     */   
/*     */   public void setAllLocationsIfNull(int geomIndex, int location) {
/* 147 */     this.elt[geomIndex].setAllLocationsIfNull(location);
/*     */   }
/*     */   
/*     */   public void setAllLocationsIfNull(int location) {
/* 151 */     setAllLocationsIfNull(0, location);
/* 152 */     setAllLocationsIfNull(1, location);
/*     */   }
/*     */   
/*     */   public void merge(Label lbl) {
/* 160 */     for (int i = 0; i < 2; i++) {
/* 161 */       if (this.elt[i] == null && lbl.elt[i] != null) {
/* 162 */         this.elt[i] = new TopologyLocation(lbl.elt[i]);
/*     */       } else {
/* 165 */         this.elt[i].merge(lbl.elt[i]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getGeometryCount() {
/* 171 */     int count = 0;
/* 172 */     if (!this.elt[0].isNull())
/* 172 */       count++; 
/* 173 */     if (!this.elt[1].isNull())
/* 173 */       count++; 
/* 174 */     return count;
/*     */   }
/*     */   
/*     */   public boolean isNull(int geomIndex) {
/* 176 */     return this.elt[geomIndex].isNull();
/*     */   }
/*     */   
/*     */   public boolean isAnyNull(int geomIndex) {
/* 177 */     return this.elt[geomIndex].isAnyNull();
/*     */   }
/*     */   
/*     */   public boolean isArea() {
/* 179 */     return (this.elt[0].isArea() || this.elt[1].isArea());
/*     */   }
/*     */   
/*     */   public boolean isArea(int geomIndex) {
/* 187 */     return this.elt[geomIndex].isArea();
/*     */   }
/*     */   
/*     */   public boolean isLine(int geomIndex) {
/* 189 */     return this.elt[geomIndex].isLine();
/*     */   }
/*     */   
/*     */   public boolean isEqualOnSide(Label lbl, int side) {
/* 193 */     return (this.elt[0].isEqualOnSide(lbl.elt[0], side) && this.elt[1].isEqualOnSide(lbl.elt[1], side));
/*     */   }
/*     */   
/*     */   public boolean allPositionsEqual(int geomIndex, int loc) {
/* 199 */     return this.elt[geomIndex].allPositionsEqual(loc);
/*     */   }
/*     */   
/*     */   public void toLine(int geomIndex) {
/* 206 */     if (this.elt[geomIndex].isArea())
/* 207 */       this.elt[geomIndex] = new TopologyLocation((this.elt[geomIndex]).location[0]); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 211 */     StringBuffer buf = new StringBuffer();
/* 212 */     if (this.elt[0] != null) {
/* 213 */       buf.append("A:");
/* 214 */       buf.append(this.elt[0].toString());
/*     */     } 
/* 216 */     if (this.elt[1] != null) {
/* 217 */       buf.append(" B:");
/* 218 */       buf.append(this.elt[1].toString());
/*     */     } 
/* 220 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\Label.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */