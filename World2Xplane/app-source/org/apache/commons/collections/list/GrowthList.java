/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ public class GrowthList extends AbstractSerializableListDecorator {
/*     */   private static final long serialVersionUID = -3620001881672L;
/*     */   
/*     */   public static List decorate(List list) {
/*  70 */     return new GrowthList(list);
/*     */   }
/*     */   
/*     */   public GrowthList() {
/*  78 */     super(new ArrayList());
/*     */   }
/*     */   
/*     */   public GrowthList(int initialSize) {
/*  88 */     super(new ArrayList(initialSize));
/*     */   }
/*     */   
/*     */   protected GrowthList(List list) {
/*  98 */     super(list);
/*     */   }
/*     */   
/*     */   public void add(int index, Object element) {
/* 121 */     int size = getList().size();
/* 122 */     if (index > size)
/* 123 */       getList().addAll(Collections.nCopies(index - size, null)); 
/* 125 */     getList().add(index, element);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/* 149 */     int size = getList().size();
/* 150 */     boolean result = false;
/* 151 */     if (index > size) {
/* 152 */       getList().addAll(Collections.nCopies(index - size, null));
/* 153 */       result = true;
/*     */     } 
/* 155 */     return getList().addAll(index, coll) | result;
/*     */   }
/*     */   
/*     */   public Object set(int index, Object element) {
/* 179 */     int size = getList().size();
/* 180 */     if (index >= size)
/* 181 */       getList().addAll(Collections.nCopies(index - size + 1, null)); 
/* 183 */     return getList().set(index, element);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\GrowthList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */