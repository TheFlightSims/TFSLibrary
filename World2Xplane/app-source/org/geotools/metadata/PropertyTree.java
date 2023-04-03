/*     */ package org.geotools.metadata;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.MutableTreeNode;
/*     */ import javax.swing.tree.TreeNode;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.OptionalDependencies;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.util.CodeList;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ final class PropertyTree {
/*     */   private static final int PRECISION = 12;
/*     */   
/*     */   private final MetadataStandard standard;
/*     */   
/*     */   private final Locale locale;
/*     */   
/*     */   private transient NumberFormat numberFormat;
/*     */   
/*     */   private transient DateFormat dateFormat;
/*     */   
/*     */   public PropertyTree(MetadataStandard standard) {
/*  84 */     this(standard, Locale.getDefault());
/*     */   }
/*     */   
/*     */   public PropertyTree(MetadataStandard standard, Locale locale) {
/*  95 */     this.standard = standard;
/*  96 */     this.locale = locale;
/*     */   }
/*     */   
/*     */   public MutableTreeNode asTree(Object metadata) {
/* 103 */     String name = Classes.getShortName(this.standard.getInterface(metadata.getClass()));
/* 104 */     DefaultMutableTreeNode root = OptionalDependencies.createTreeNode(localize(name), metadata, true);
/* 106 */     append(root, metadata);
/* 107 */     return root;
/*     */   }
/*     */   
/*     */   private void append(DefaultMutableTreeNode branch, Object value) {
/*     */     String asText;
/* 119 */     if (value instanceof Map) {
/* 120 */       appendMap(branch, (Map)value);
/*     */       return;
/*     */     } 
/* 123 */     if (value instanceof AbstractMetadata) {
/* 124 */       appendMap(branch, ((AbstractMetadata)value).asMap());
/*     */       return;
/*     */     } 
/* 127 */     if (value != null) {
/* 128 */       PropertyAccessor accessor = this.standard.getAccessorOptional(value.getClass());
/* 129 */       if (accessor != null) {
/* 130 */         appendMap(branch, new PropertyMap(value, accessor));
/*     */         return;
/*     */       } 
/*     */     } 
/* 134 */     if (value instanceof Collection) {
/* 135 */       for (Iterator it = ((Collection)value).iterator(); it.hasNext(); ) {
/* 136 */         Object element = it.next();
/* 137 */         if (!PropertyAccessor.isEmpty(element))
/* 138 */           append(branch, element); 
/*     */       } 
/*     */       return;
/*     */     } 
/* 144 */     if (value instanceof CodeList) {
/* 145 */       asText = localize((CodeList)value);
/* 146 */     } else if (value instanceof Date) {
/* 147 */       asText = format((Date)value);
/* 148 */     } else if (value instanceof Number) {
/* 149 */       asText = format((Number)value);
/* 150 */     } else if (value instanceof InternationalString) {
/* 151 */       asText = ((InternationalString)value).toString(this.locale);
/*     */     } else {
/* 153 */       asText = String.valueOf(value);
/*     */     } 
/* 155 */     branch.add(OptionalDependencies.createTreeNode(asText, value, false));
/*     */   }
/*     */   
/*     */   private void appendMap(DefaultMutableTreeNode branch, Map asMap) {
/* 165 */     for (Iterator<Map.Entry> it = asMap.entrySet().iterator(); it.hasNext(); ) {
/* 166 */       Map.Entry entry = it.next();
/* 167 */       Object value = entry.getValue();
/* 168 */       if (!PropertyAccessor.isEmpty(value)) {
/* 169 */         String name = localize((String)entry.getKey());
/* 170 */         DefaultMutableTreeNode child = OptionalDependencies.createTreeNode(name, value, true);
/* 172 */         append(child, value);
/* 173 */         branch.add(child);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private String format(Number value) {
/* 182 */     if (this.numberFormat == null) {
/* 183 */       this.numberFormat = NumberFormat.getNumberInstance(this.locale);
/* 184 */       this.numberFormat.setMinimumFractionDigits(0);
/*     */     } 
/* 186 */     int precision = 0;
/* 187 */     if (!Classes.isInteger(value.getClass())) {
/* 188 */       precision = 12;
/* 189 */       double v = Math.abs(value.doubleValue());
/* 190 */       if (v > 0.0D) {
/* 191 */         int digits = (int)Math.log10(v);
/* 192 */         if (Math.abs(digits) >= 12)
/* 194 */           return value.toString(); 
/* 196 */         if (digits >= 0)
/* 197 */           precision -= digits; 
/* 199 */         precision = Math.max(0, 12 - precision);
/*     */       } 
/*     */     } 
/* 202 */     this.numberFormat.setMaximumFractionDigits(precision);
/* 203 */     return this.numberFormat.format(value);
/*     */   }
/*     */   
/*     */   private String format(Date value) {
/* 210 */     if (this.dateFormat == null)
/* 211 */       this.dateFormat = DateFormat.getDateTimeInstance(1, 1, this.locale); 
/* 213 */     return this.dateFormat.format(value);
/*     */   }
/*     */   
/*     */   private String localize(String name) {
/* 221 */     name = name.trim();
/* 222 */     int length = name.length();
/* 223 */     if (length != 0) {
/* 224 */       StringBuilder buffer = new StringBuilder();
/* 225 */       buffer.append(Character.toUpperCase(name.charAt(0)));
/* 226 */       boolean previousIsUpper = true;
/* 227 */       int base = 1;
/* 228 */       for (int i = 1; i < length; i++) {
/* 229 */         boolean currentIsUpper = Character.isUpperCase(name.charAt(i));
/* 230 */         if (currentIsUpper != previousIsUpper) {
/* 236 */           int split = i;
/* 237 */           if (previousIsUpper)
/* 238 */             split--; 
/* 240 */           if (split > base) {
/* 241 */             buffer.append(name.substring(base, split)).append(' ');
/* 242 */             base = split;
/*     */           } 
/*     */         } 
/* 245 */         previousIsUpper = currentIsUpper;
/*     */       } 
/* 247 */       String candidate = buffer.append(name.substring(base)).toString();
/* 248 */       if (!candidate.equals(name))
/* 250 */         name = candidate; 
/*     */     } 
/* 253 */     return name;
/*     */   }
/*     */   
/*     */   private String localize(CodeList code) {
/* 261 */     return code.name().trim().replace('_', ' ').toLowerCase(this.locale);
/*     */   }
/*     */   
/*     */   public static String toString(TreeNode node) {
/* 268 */     StringBuilder buffer = new StringBuilder();
/* 269 */     toString(node, buffer, 0, System.getProperty("line.separator", "\n"));
/* 270 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   private static void toString(TreeNode node, StringBuilder buffer, int indent, String lineSeparator) {
/* 281 */     int count = node.getChildCount();
/* 282 */     if (count == 0) {
/* 283 */       if (node.isLeaf())
/* 290 */         buffer.append(Utilities.spaces(indent)).append(node).append(lineSeparator); 
/*     */       return;
/*     */     } 
/* 294 */     buffer.append(Utilities.spaces(indent)).append(node).append(':');
/* 295 */     if (count == 1) {
/* 296 */       TreeNode child = node.getChildAt(0);
/* 297 */       if (child.isLeaf()) {
/* 298 */         buffer.append(' ').append(child).append(lineSeparator);
/*     */         return;
/*     */       } 
/*     */     } 
/* 302 */     for (int i = 0; i < count; i++) {
/* 303 */       TreeNode child = node.getChildAt(i);
/* 304 */       if (i == 0)
/* 305 */         buffer.append(lineSeparator); 
/* 307 */       toString(child, buffer, indent + 2, lineSeparator);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\PropertyTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */