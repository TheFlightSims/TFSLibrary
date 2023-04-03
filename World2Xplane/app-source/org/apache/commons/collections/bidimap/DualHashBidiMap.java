/*     */ package org.apache.commons.collections.bidimap;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.BidiMap;
/*     */ 
/*     */ public class DualHashBidiMap extends AbstractDualBidiMap implements Serializable {
/*     */   private static final long serialVersionUID = 721969328361808L;
/*     */   
/*     */   public DualHashBidiMap() {
/*  55 */     super(new HashMap(), new HashMap());
/*     */   }
/*     */   
/*     */   public DualHashBidiMap(Map map) {
/*  65 */     super(new HashMap(), new HashMap());
/*  66 */     putAll(map);
/*     */   }
/*     */   
/*     */   protected DualHashBidiMap(Map normalMap, Map reverseMap, BidiMap inverseBidiMap) {
/*  77 */     super(normalMap, reverseMap, inverseBidiMap);
/*     */   }
/*     */   
/*     */   protected BidiMap createBidiMap(Map normalMap, Map reverseMap, BidiMap inverseBidiMap) {
/*  89 */     return new DualHashBidiMap(normalMap, reverseMap, inverseBidiMap);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  95 */     out.defaultWriteObject();
/*  96 */     out.writeObject(this.maps[0]);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 100 */     in.defaultReadObject();
/* 101 */     this.maps[0] = new HashMap();
/* 102 */     this.maps[1] = new HashMap();
/* 103 */     Map map = (Map)in.readObject();
/* 104 */     putAll(map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bidimap\DualHashBidiMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */