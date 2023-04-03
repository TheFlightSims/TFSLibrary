/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class KeyToGroupMap implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -2228169345475318082L;
/*     */   
/*     */   private Comparable defaultGroup;
/*     */   
/*     */   private List groups;
/*     */   
/*     */   private Map keyToGroupMap;
/*     */   
/*     */   public KeyToGroupMap() {
/*  83 */     this("Default Group");
/*     */   }
/*     */   
/*     */   public KeyToGroupMap(Comparable defaultGroup) {
/*  92 */     if (defaultGroup == null)
/*  93 */       throw new IllegalArgumentException("Null 'defaultGroup' argument."); 
/*  95 */     this.defaultGroup = defaultGroup;
/*  96 */     this.groups = new ArrayList();
/*  97 */     this.keyToGroupMap = new HashMap();
/*     */   }
/*     */   
/*     */   public int getGroupCount() {
/* 106 */     return this.groups.size() + 1;
/*     */   }
/*     */   
/*     */   public List getGroups() {
/* 117 */     List result = new ArrayList();
/* 118 */     result.add(this.defaultGroup);
/* 119 */     Iterator iterator = this.groups.iterator();
/* 120 */     while (iterator.hasNext()) {
/* 121 */       Comparable group = iterator.next();
/* 122 */       if (!result.contains(group))
/* 123 */         result.add(group); 
/*     */     } 
/* 126 */     return result;
/*     */   }
/*     */   
/*     */   public int getGroupIndex(Comparable group) {
/* 138 */     int result = this.groups.indexOf(group);
/* 139 */     if (result < 0) {
/* 140 */       if (this.defaultGroup.equals(group))
/* 141 */         result = 0; 
/*     */     } else {
/* 145 */       result++;
/*     */     } 
/* 147 */     return result;
/*     */   }
/*     */   
/*     */   public Comparable getGroup(Comparable key) {
/* 159 */     if (key == null)
/* 160 */       throw new IllegalArgumentException("Null 'key' argument."); 
/* 162 */     Comparable result = this.defaultGroup;
/* 163 */     Comparable group = (Comparable)this.keyToGroupMap.get(key);
/* 164 */     if (group != null)
/* 165 */       result = group; 
/* 167 */     return result;
/*     */   }
/*     */   
/*     */   public void mapKeyToGroup(Comparable key, Comparable group) {
/* 178 */     if (key == null)
/* 179 */       throw new IllegalArgumentException("Null 'key' argument."); 
/* 181 */     Comparable currentGroup = getGroup(key);
/* 182 */     if (!currentGroup.equals(this.defaultGroup) && 
/* 183 */       !currentGroup.equals(group)) {
/* 184 */       int count = getKeyCount(currentGroup);
/* 185 */       if (count == 1)
/* 186 */         this.groups.remove(currentGroup); 
/*     */     } 
/* 190 */     if (group == null) {
/* 191 */       this.keyToGroupMap.remove(key);
/*     */     } else {
/* 194 */       if (!this.groups.contains(group) && 
/* 195 */         !this.defaultGroup.equals(group))
/* 196 */         this.groups.add(group); 
/* 199 */       this.keyToGroupMap.put(key, group);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getKeyCount(Comparable group) {
/* 213 */     if (group == null)
/* 214 */       throw new IllegalArgumentException("Null 'group' argument."); 
/* 216 */     int result = 0;
/* 217 */     Iterator iterator = this.keyToGroupMap.values().iterator();
/* 218 */     while (iterator.hasNext()) {
/* 219 */       Comparable g = iterator.next();
/* 220 */       if (group.equals(g))
/* 221 */         result++; 
/*     */     } 
/* 224 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 235 */     if (obj == this)
/* 236 */       return true; 
/* 238 */     if (!(obj instanceof KeyToGroupMap))
/* 239 */       return false; 
/* 241 */     KeyToGroupMap that = (KeyToGroupMap)obj;
/* 242 */     if (!ObjectUtilities.equal(this.defaultGroup, that.defaultGroup))
/* 243 */       return false; 
/* 245 */     if (!this.keyToGroupMap.equals(that.keyToGroupMap))
/* 246 */       return false; 
/* 248 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 260 */     KeyToGroupMap result = (KeyToGroupMap)super.clone();
/* 261 */     result.defaultGroup = (Comparable)clone(this.defaultGroup);
/* 263 */     result.groups = (List)clone(this.groups);
/* 264 */     result.keyToGroupMap = (Map)clone(this.keyToGroupMap);
/* 265 */     return result;
/*     */   }
/*     */   
/*     */   private static Object clone(Object object) {
/* 276 */     if (object == null)
/* 277 */       return null; 
/* 279 */     Class c = object.getClass();
/* 280 */     Object result = null;
/*     */     try {
/* 282 */       Method m = c.getMethod("clone", (Class[])null);
/* 283 */       if (Modifier.isPublic(m.getModifiers()))
/*     */         try {
/* 285 */           result = m.invoke(object, (Object[])null);
/* 287 */         } catch (Exception e) {
/* 288 */           e.printStackTrace();
/*     */         }  
/* 292 */     } catch (NoSuchMethodException e) {
/* 293 */       result = object;
/*     */     } 
/* 295 */     return result;
/*     */   }
/*     */   
/*     */   private static Collection clone(Collection list) throws CloneNotSupportedException {
/* 309 */     Collection result = null;
/* 310 */     if (list != null)
/*     */       try {
/* 312 */         List clone = (List)list.getClass().newInstance();
/* 313 */         Iterator iterator = list.iterator();
/* 314 */         while (iterator.hasNext())
/* 315 */           clone.add(clone(iterator.next())); 
/* 317 */         result = clone;
/* 319 */       } catch (Exception e) {
/* 320 */         throw new CloneNotSupportedException("Exception.");
/*     */       }  
/* 323 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\KeyToGroupMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */