/*     */ package org.geotools.metadata;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.swing.tree.DefaultTreeModel;
/*     */ import javax.swing.tree.TreeModel;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public final class MetadataStandard {
/*  63 */   public static final MetadataStandard ISO_19111 = new MetadataStandard("org.opengis.referencing.");
/*     */   
/*  70 */   public static final MetadataStandard ISO_19115 = new MetadataStandard("org.opengis.metadata.");
/*     */   
/*  79 */   public static final MetadataStandard ISO_19119 = new MetadataStandard("org.opengis.service.");
/*     */   
/*     */   private final String interfacePackage;
/*     */   
/*  89 */   private final Map<Class<?>, PropertyAccessor> accessors = new HashMap<Class<?>, PropertyAccessor>();
/*     */   
/*  95 */   private final ThreadLocal<PropertyTree> treeBuilders = new ThreadLocal<PropertyTree>() {
/*     */       protected PropertyTree initialValue() {
/*  98 */         return new PropertyTree(MetadataStandard.this);
/*     */       }
/*     */     };
/*     */   
/*     */   public MetadataStandard(String interfacePackage) {
/* 110 */     if (!interfacePackage.endsWith("."))
/* 111 */       interfacePackage = interfacePackage + '.'; 
/* 113 */     this.interfacePackage = interfacePackage;
/*     */   }
/*     */   
/*     */   private PropertyAccessor getAccessor(Class<?> implementation) throws ClassCastException {
/* 125 */     PropertyAccessor accessor = getAccessorOptional(implementation);
/* 126 */     if (accessor == null)
/* 127 */       throw new ClassCastException(Errors.format(187, implementation.getName())); 
/* 130 */     return accessor;
/*     */   }
/*     */   
/*     */   final PropertyAccessor getAccessorOptional(Class<?> implementation) {
/* 137 */     synchronized (this.accessors) {
/* 138 */       PropertyAccessor accessor = this.accessors.get(implementation);
/* 139 */       if (accessor == null) {
/* 140 */         Class<?> type = getType(implementation);
/* 141 */         if (type != null) {
/* 142 */           accessor = new PropertyAccessor(implementation, type);
/* 143 */           this.accessors.put(implementation, accessor);
/*     */         } 
/*     */       } 
/* 146 */       return accessor;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Class<?> getType(Class<?> implementation) {
/* 158 */     return PropertyAccessor.getType(implementation, this.interfacePackage);
/*     */   }
/*     */   
/*     */   public Class<?> getInterface(Class<?> implementation) throws ClassCastException {
/* 172 */     return (getAccessor(implementation)).type;
/*     */   }
/*     */   
/*     */   public Map<String, Object> asMap(Object metadata) throws ClassCastException {
/* 193 */     return new PropertyMap(metadata, getAccessor(metadata.getClass()));
/*     */   }
/*     */   
/*     */   public TreeModel asTree(Object metadata) throws ClassCastException {
/* 212 */     PropertyTree builder = this.treeBuilders.get();
/* 213 */     return new DefaultTreeModel(builder.asTree(metadata), true);
/*     */   }
/*     */   
/*     */   final boolean isModifiable(Class<?> implementation) throws ClassCastException {
/* 226 */     return getAccessor(implementation).isModifiable();
/*     */   }
/*     */   
/*     */   final void freeze(Object metadata) throws ClassCastException {
/* 239 */     getAccessor(metadata.getClass()).freeze(metadata);
/*     */   }
/*     */   
/*     */   public void shallowCopy(Object source, Object target, boolean skipNulls) throws ClassCastException, UnmodifiableMetadataException {
/* 259 */     ensureNonNull("target", target);
/* 260 */     PropertyAccessor accessor = getAccessor(target.getClass());
/* 261 */     if (!accessor.type.isInstance(source)) {
/* 262 */       ensureNonNull("source", source);
/* 263 */       throw new ClassCastException(Errors.format(61, source.getClass(), accessor.type));
/*     */     } 
/* 266 */     if (!accessor.shallowCopy(source, target, skipNulls))
/* 267 */       throw new UnmodifiableMetadataException(Errors.format(190)); 
/*     */   }
/*     */   
/*     */   public boolean shallowEquals(Object metadata1, Object metadata2, boolean skipNulls) throws ClassCastException {
/* 296 */     if (metadata1 == metadata2)
/* 297 */       return true; 
/* 299 */     if (metadata1 == null || metadata2 == null)
/* 300 */       return false; 
/* 302 */     PropertyAccessor accessor = getAccessor(metadata1.getClass());
/* 303 */     if (!accessor.type.equals(getType(metadata2.getClass())))
/* 304 */       return false; 
/* 306 */     return accessor.shallowEquals(metadata1, metadata2, skipNulls);
/*     */   }
/*     */   
/*     */   public int hashCode(Object metadata) throws ClassCastException {
/* 323 */     return getAccessor(metadata.getClass()).hashCode(metadata);
/*     */   }
/*     */   
/*     */   public String toString(Object metadata) throws ClassCastException {
/* 337 */     PropertyTree builder = this.treeBuilders.get();
/* 338 */     return PropertyTree.toString(builder.asTree(metadata));
/*     */   }
/*     */   
/*     */   private static void ensureNonNull(String name, Object value) {
/* 345 */     if (value == null)
/* 346 */       throw new NullPointerException(Errors.format(143, name)); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\MetadataStandard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */