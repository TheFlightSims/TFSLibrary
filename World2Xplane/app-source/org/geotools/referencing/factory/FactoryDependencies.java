/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.MutableTreeNode;
/*     */ import javax.swing.tree.TreeNode;
/*     */ import org.geotools.factory.BufferedFactory;
/*     */ import org.geotools.factory.OptionalFactory;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.OptionalDependencies;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.Factory;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CRSFactory;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.CSFactory;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.referencing.datum.DatumFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*     */ 
/*     */ public class FactoryDependencies {
/*  67 */   private static final Class[] TYPES = new Class[] { 
/*  67 */       CRSFactory.class, CRSAuthorityFactory.class, CSFactory.class, CSAuthorityFactory.class, DatumFactory.class, DatumAuthorityFactory.class, CoordinateOperationFactory.class, CoordinateOperationAuthorityFactory.class, BufferedFactory.class, OptionalFactory.class, 
/*  67 */       Factory.class };
/*     */   
/*  84 */   private static final String[] TYPE_LABELS = new String[] { 
/*  84 */       "crs", "crs", "cs", "cs", "datum", "datum", "operation", "operation", "buffered", "optional", 
/*  84 */       "registered" };
/*     */   
/*  93 */   private static final int FACTORY_COUNT = TYPES.length - 3;
/*     */   
/*     */   private final Factory factory;
/*     */   
/*     */   private boolean colorEnabled;
/*     */   
/*     */   private boolean attributes;
/*     */   
/*     */   public FactoryDependencies(Factory factory) {
/* 116 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public boolean isColorEnabled() {
/* 124 */     return this.colorEnabled;
/*     */   }
/*     */   
/*     */   public void setColorEnabled(boolean enabled) {
/* 132 */     this.colorEnabled = enabled;
/*     */   }
/*     */   
/*     */   public boolean isAttributeEnabled() {
/* 140 */     return this.attributes;
/*     */   }
/*     */   
/*     */   public void setAttributeEnabled(boolean enabled) {
/* 150 */     this.attributes = enabled;
/*     */   }
/*     */   
/*     */   public void print(PrintWriter out) {
/* 157 */     out.print(OptionalDependencies.toString(asTree()));
/*     */   }
/*     */   
/*     */   public void print(Writer out) throws IOException {
/* 167 */     out.write(OptionalDependencies.toString(asTree()));
/*     */   }
/*     */   
/*     */   public TreeNode asTree() {
/* 174 */     return createTree(this.factory, new HashSet<Factory>());
/*     */   }
/*     */   
/*     */   private MutableTreeNode createTree(Factory factory, Set<Factory> done) {
/* 181 */     DefaultMutableTreeNode root = createNode(factory);
/* 182 */     if (factory instanceof ReferencingFactory) {
/* 183 */       Collection<?> dep = ((ReferencingFactory)factory).dependencies();
/* 184 */       if (dep != null)
/* 185 */         for (Object element : dep) {
/*     */           MutableTreeNode child;
/* 187 */           if (element instanceof Factory) {
/* 188 */             Factory candidate = (Factory)element;
/* 189 */             if (!done.add(candidate))
/*     */               continue; 
/* 192 */             child = createTree(candidate, done);
/* 193 */             if (!done.remove(candidate))
/* 194 */               throw new AssertionError(); 
/*     */           } else {
/* 197 */             child = OptionalDependencies.createTreeNode(String.valueOf(element), element, false);
/*     */           } 
/* 199 */           root.add(child);
/*     */         }  
/*     */     } 
/* 203 */     return root;
/*     */   }
/*     */   
/*     */   private DefaultMutableTreeNode createNode(Factory factory) {
/* 210 */     StringBuilder buffer = (new StringBuilder(Classes.getShortClassName(factory))).append('[');
/* 212 */     if (factory instanceof AuthorityFactory) {
/* 213 */       Citation authority = ((AuthorityFactory)factory).getAuthority();
/* 214 */       if (authority != null) {
/* 215 */         Collection<? extends Identifier> identifiers = authority.getIdentifiers();
/* 216 */         if (identifiers != null && !identifiers.isEmpty()) {
/* 217 */           boolean next = false;
/* 218 */           for (Identifier id : identifiers) {
/* 219 */             if (next)
/* 220 */               buffer.append(", "); 
/* 222 */             appendIdentifier(buffer, id.getCode());
/* 223 */             next = true;
/*     */           } 
/*     */         } else {
/* 226 */           appendIdentifier(buffer, (CharSequence)authority.getTitle());
/*     */         } 
/*     */       } 
/*     */     } else {
/* 230 */       if (this.colorEnabled)
/* 230 */         buffer.append("\033[31m"); 
/* 231 */       buffer.append("direct");
/* 232 */       if (this.colorEnabled)
/* 232 */         buffer.append("\033[39m"); 
/*     */     } 
/* 234 */     buffer.append(']');
/* 235 */     if (this.attributes) {
/* 236 */       boolean hasFound = false;
/* 237 */       for (int i = 0; i < TYPES.length; i++) {
/* 238 */         Class type = TYPES[i];
/* 239 */         if (type.isInstance(factory))
/* 242 */           if (!type.equals(Factory.class) || 
/* 243 */             ReferencingFactoryFinder.isRegistered(factory)) {
/* 247 */             buffer.append(hasFound ? ", " : " (");
/* 248 */             if (this.colorEnabled)
/* 249 */               buffer.append((i < FACTORY_COUNT) ? "\033[32m" : "\033[36m"); 
/* 251 */             buffer.append(TYPE_LABELS[i]);
/* 252 */             if (this.colorEnabled)
/* 253 */               buffer.append("\033[39m"); 
/* 255 */             hasFound = true;
/*     */           }  
/*     */       } 
/* 257 */       if (hasFound)
/* 258 */         buffer.append(')'); 
/*     */     } 
/* 261 */     return OptionalDependencies.createTreeNode(buffer.toString(), factory, true);
/*     */   }
/*     */   
/*     */   private void appendIdentifier(StringBuilder buffer, CharSequence identifier) {
/* 268 */     if (this.colorEnabled)
/* 268 */       buffer.append("\033[35m"); 
/* 269 */     buffer.append('"').append(identifier).append('"');
/* 270 */     if (this.colorEnabled)
/* 270 */       buffer.append("\033[39m"); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\FactoryDependencies.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */