/*     */ package org.apache.commons.configuration.tree.xpath;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*     */ import org.apache.commons.configuration.tree.ExpressionEngine;
/*     */ import org.apache.commons.configuration.tree.NodeAddData;
/*     */ import org.apache.commons.jxpath.JXPathContext;
/*     */ import org.apache.commons.jxpath.ri.JXPathContextReferenceImpl;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class XPathExpressionEngine implements ExpressionEngine {
/*     */   static final String PATH_DELIMITER = "/";
/*     */   
/*     */   static final String ATTR_DELIMITER = "@";
/*     */   
/*     */   private static final String NODE_PATH_DELIMITERS = "/@";
/*     */   
/*     */   private static final String SPACE = " ";
/*     */   
/*     */   public List query(ConfigurationNode root, String key) {
/* 174 */     if (StringUtils.isEmpty(key)) {
/* 176 */       List list = new ArrayList(1);
/* 177 */       list.add(root);
/* 178 */       return list;
/*     */     } 
/* 182 */     JXPathContext context = createContext(root, key);
/* 183 */     List result = context.selectNodes(key);
/* 184 */     return (result != null) ? result : Collections.EMPTY_LIST;
/*     */   }
/*     */   
/*     */   public String nodeKey(ConfigurationNode node, String parentKey) {
/* 203 */     if (parentKey == null)
/* 206 */       return ""; 
/* 208 */     if (node.getName() == null)
/* 211 */       return parentKey; 
/* 216 */     StringBuffer buf = new StringBuffer(parentKey.length() + node.getName().length() + "/".length());
/* 218 */     if (parentKey.length() > 0) {
/* 220 */       buf.append(parentKey);
/* 221 */       buf.append("/");
/*     */     } 
/* 223 */     if (node.isAttribute())
/* 225 */       buf.append("@"); 
/* 227 */     buf.append(node.getName());
/* 228 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public NodeAddData prepareAdd(ConfigurationNode root, String key) {
/* 243 */     if (key == null)
/* 245 */       throw new IllegalArgumentException("prepareAdd: key must not be null!"); 
/* 249 */     String addKey = key;
/* 250 */     int index = findKeySeparator(addKey);
/* 251 */     if (index < 0) {
/* 253 */       addKey = generateKeyForAdd(root, addKey);
/* 254 */       index = findKeySeparator(addKey);
/*     */     } 
/* 257 */     List nodes = query(root, addKey.substring(0, index).trim());
/* 258 */     if (nodes.size() != 1)
/* 260 */       throw new IllegalArgumentException("prepareAdd: key must select exactly one target node!"); 
/* 264 */     NodeAddData data = new NodeAddData();
/* 265 */     data.setParent(nodes.get(0));
/* 266 */     initNodeAddData(data, addKey.substring(index).trim());
/* 267 */     return data;
/*     */   }
/*     */   
/*     */   protected JXPathContext createContext(ConfigurationNode root, String key) {
/* 281 */     JXPathContext context = JXPathContext.newContext(root);
/* 282 */     context.setLenient(true);
/* 283 */     return context;
/*     */   }
/*     */   
/*     */   protected void initNodeAddData(NodeAddData data, String path) {
/* 296 */     String lastComponent = null;
/* 297 */     boolean attr = false;
/* 298 */     boolean first = true;
/* 300 */     StringTokenizer tok = new StringTokenizer(path, "/@", true);
/* 302 */     while (tok.hasMoreTokens()) {
/* 304 */       String token = tok.nextToken();
/* 305 */       if ("/".equals(token)) {
/* 307 */         if (attr)
/* 309 */           invalidPath(path, " contains an attribute delimiter at an unallowed position."); 
/* 312 */         if (lastComponent == null)
/* 314 */           invalidPath(path, " contains a '/' at an unallowed position."); 
/* 317 */         data.addPathNode(lastComponent);
/* 318 */         lastComponent = null;
/* 321 */       } else if ("@".equals(token)) {
/* 323 */         if (attr)
/* 325 */           invalidPath(path, " contains multiple attribute delimiters."); 
/* 328 */         if (lastComponent == null && !first)
/* 330 */           invalidPath(path, " contains an attribute delimiter at an unallowed position."); 
/* 333 */         if (lastComponent != null)
/* 335 */           data.addPathNode(lastComponent); 
/* 337 */         attr = true;
/* 338 */         lastComponent = null;
/*     */       } else {
/* 343 */         lastComponent = token;
/*     */       } 
/* 345 */       first = false;
/*     */     } 
/* 348 */     if (lastComponent == null)
/* 350 */       invalidPath(path, "contains no components."); 
/* 352 */     data.setNewNodeName(lastComponent);
/* 353 */     data.setAttribute(attr);
/*     */   }
/*     */   
/*     */   private String generateKeyForAdd(ConfigurationNode root, String key) {
/* 369 */     int pos = key.lastIndexOf("/", key.length());
/* 371 */     while (pos >= 0) {
/* 373 */       String keyExisting = key.substring(0, pos);
/* 374 */       if (!query(root, keyExisting).isEmpty()) {
/* 376 */         StringBuffer buf = new StringBuffer(key.length() + 1);
/* 377 */         buf.append(keyExisting).append(" ");
/* 378 */         buf.append(key.substring(pos + 1));
/* 379 */         return buf.toString();
/*     */       } 
/* 381 */       pos = key.lastIndexOf("/", pos - 1);
/*     */     } 
/* 384 */     return " " + key;
/*     */   }
/*     */   
/*     */   private void invalidPath(String path, String msg) {
/* 395 */     throw new IllegalArgumentException("Invalid node path: \"" + path + "\" " + msg);
/*     */   }
/*     */   
/*     */   private static int findKeySeparator(String key) {
/* 408 */     int index = key.length() - 1;
/* 409 */     while (index >= 0 && !Character.isWhitespace(key.charAt(index)))
/* 411 */       index--; 
/* 413 */     return index;
/*     */   }
/*     */   
/*     */   static {
/* 419 */     JXPathContextReferenceImpl.addNodePointerFactory(new ConfigurationNodePointerFactory());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\xpath\XPathExpressionEngine.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */