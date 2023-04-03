/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.style.ColorMap;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ 
/*     */ public class ColorMapImpl implements ColorMap {
/*     */   private final Function function;
/*     */   
/*  38 */   private List<ColorMapEntry> list = new ArrayList<ColorMapEntry>();
/*     */   
/*  39 */   private int type = 1;
/*     */   
/*     */   private boolean extendedColors;
/*     */   
/*     */   public ColorMapImpl() {
/*  43 */     this.function = null;
/*     */   }
/*     */   
/*     */   public ColorMapImpl(Function function) {
/*  47 */     this.function = function;
/*     */   }
/*     */   
/*     */   public void addColorMapEntry(ColorMapEntry entry) {
/*  51 */     this.list.add(entry);
/*     */   }
/*     */   
/*     */   public ColorMapEntry[] getColorMapEntries() {
/*  55 */     return this.list.<ColorMapEntry>toArray(new ColorMapEntry[0]);
/*     */   }
/*     */   
/*     */   public ColorMapEntry getColorMapEntry(int index) {
/*  59 */     return this.list.get(index);
/*     */   }
/*     */   
/*     */   public int getType() {
/*  66 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(int type) {
/*  73 */     if (type < 1 || type > 3)
/*  74 */       throw new IllegalArgumentException(); 
/*  76 */     this.type = type;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/*  80 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public boolean getExtendedColors() {
/*  84 */     return this.extendedColors;
/*     */   }
/*     */   
/*     */   public void setExtendedColors(boolean extended) {
/*  88 */     this.extendedColors = extended;
/*     */   }
/*     */   
/*     */   public Function getFunction() {
/*  92 */     return this.function;
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/*  96 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 102 */     int PRIME = 1000003;
/* 103 */     int result = 0;
/* 105 */     if (this.function != null)
/* 106 */       result = 1000003 * result + this.function.hashCode(); 
/* 109 */     if (this.list != null)
/* 110 */       result = 1000003 * result + this.list.hashCode(); 
/* 113 */     result = 1000003 * result + this.type;
/* 114 */     result = 1000003 * result + (this.extendedColors ? 1 : 0);
/* 116 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 121 */     if (this == obj)
/* 122 */       return true; 
/* 125 */     if (obj instanceof ColorMapImpl) {
/* 126 */       ColorMapImpl other = (ColorMapImpl)obj;
/* 128 */       return (Utilities.equals(this.function, other.function) && Utilities.equals(this.list, other.list) && Utilities.equals(this.type, other.type) && Utilities.equals(this.extendedColors, other.extendedColors));
/*     */     } 
/* 134 */     return false;
/*     */   }
/*     */   
/*     */   static ColorMapImpl cast(ColorMap colorMap) {
/* 138 */     if (colorMap == null)
/* 139 */       return null; 
/* 141 */     if (colorMap instanceof ColorMapImpl)
/* 142 */       return (ColorMapImpl)colorMap; 
/* 145 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ColorMapImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */