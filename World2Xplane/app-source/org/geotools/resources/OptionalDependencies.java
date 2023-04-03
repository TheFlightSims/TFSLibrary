/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.Enumeration;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.DefaultTreeModel;
/*     */ import javax.swing.tree.MutableTreeNode;
/*     */ import javax.swing.tree.TreeModel;
/*     */ import javax.swing.tree.TreeNode;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ public final class OptionalDependencies {
/*     */   private static Constructor treeNodeConstructor;
/*     */   
/*     */   private static boolean noNamedTreeNode = false;
/*     */   
/*     */   public static DefaultMutableTreeNode createTreeNode(String name, Object object, boolean allowsChildren) {
/*  88 */     if (!noNamedTreeNode)
/*     */       try {
/*  89 */         if (treeNodeConstructor == null)
/*  90 */           treeNodeConstructor = Class.forName("org.geotools.gui.swing.tree.NamedTreeNode").getConstructor(new Class[] { String.class, Object.class, boolean.class }); 
/*  93 */         return treeNodeConstructor.newInstance(new Object[] { name, object, Boolean.valueOf(allowsChildren) });
/*  95 */       } catch (Exception e) {
/* 103 */         noNamedTreeNode = true;
/*     */       }  
/* 105 */     return new DefaultMutableTreeNode(name, allowsChildren);
/*     */   }
/*     */   
/*     */   public static MutableTreeNode xmlToSwing(Node node) {
/* 118 */     String label = node.getNodeName();
/* 119 */     String value = node.getNodeValue();
/* 120 */     if (value != null)
/* 121 */       label = label + "=\"" + value + '"'; 
/* 123 */     DefaultMutableTreeNode root = createTreeNode(label, node, true);
/* 124 */     NamedNodeMap attributes = node.getAttributes();
/* 125 */     int length = attributes.getLength();
/* 126 */     for (int i = 0; i < length; i++) {
/* 127 */       Node attribute = attributes.item(i);
/* 128 */       if (attribute != null) {
/* 129 */         label = attribute.getNodeName() + "=\"" + attribute.getNodeValue() + '"';
/* 130 */         root.add(createTreeNode(label, attribute, false));
/*     */       } 
/*     */     } 
/* 133 */     for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling())
/* 134 */       root.add(xmlToSwing(child)); 
/* 136 */     return root;
/*     */   }
/*     */   
/*     */   public static MutableTreeNode copy(TreeNode node) {
/* 149 */     if (node == null)
/* 150 */       return null; 
/* 152 */     DefaultMutableTreeNode target = new DefaultMutableTreeNode(node.toString(), node.getAllowsChildren());
/* 154 */     Enumeration<? extends TreeNode> children = node.children();
/* 155 */     if (children != null)
/* 156 */       while (children.hasMoreElements()) {
/* 157 */         TreeNode child = children.nextElement();
/* 158 */         target.add(copy(child));
/*     */       }  
/* 161 */     return target;
/*     */   }
/*     */   
/*     */   private static boolean[] format(TreeModel model, Object node, Appendable buffer, int level, boolean[] last, String lineSeparator) throws IOException {
/* 179 */     for (int i = 0; i < level; i++) {
/* 180 */       if (i != level - 1) {
/* 181 */         buffer.append(last[i] ? 160 : 9474).append("   ");
/*     */       } else {
/* 183 */         buffer.append(last[i] ? 9492 : 9500).append("───");
/*     */       } 
/*     */     } 
/* 186 */     buffer.append(String.valueOf(node)).append(lineSeparator);
/* 187 */     if (level >= last.length)
/* 188 */       last = XArray.resize(last, level * 2); 
/* 190 */     int count = model.getChildCount(node);
/* 191 */     for (int j = 0; j < count; j++) {
/* 192 */       last[level] = (j == count - 1);
/* 193 */       last = format(model, model.getChild(node, j), buffer, level + 1, last, lineSeparator);
/*     */     } 
/* 195 */     return last;
/*     */   }
/*     */   
/*     */   public static void format(TreeModel tree, Appendable buffer, String lineSeparator) throws IOException {
/* 215 */     Object root = tree.getRoot();
/* 216 */     if (root != null) {
/* 217 */       if (lineSeparator == null)
/* 218 */         lineSeparator = System.getProperty("line.separator", "\n"); 
/* 220 */       format(tree, root, buffer, 0, new boolean[64], lineSeparator);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void format(TreeNode node, Appendable buffer, String lineSeparator) throws IOException {
/* 241 */     format(new DefaultTreeModel(node, true), buffer, lineSeparator);
/*     */   }
/*     */   
/*     */   public static String toString(TreeModel tree) {
/* 257 */     Object root = tree.getRoot();
/* 258 */     if (root == null)
/* 259 */       return null; 
/* 261 */     StringBuilder buffer = new StringBuilder();
/* 262 */     String lineSeparator = System.getProperty("line.separator", "\n");
/*     */     try {
/* 264 */       format(tree, root, buffer, 0, new boolean[64], lineSeparator);
/* 265 */     } catch (IOException e) {
/* 267 */       throw new AssertionError(e);
/*     */     } 
/* 269 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public static String toString(TreeNode node) {
/* 285 */     return toString(new DefaultTreeModel(node, true));
/*     */   }
/*     */   
/*     */   public static void show(TreeNode node, String title) {
/* 298 */     show(new DefaultTreeModel(node, true), title);
/*     */   }
/*     */   
/*     */   public static void show(TreeModel tree, String title) {
/* 311 */     JFrame frame = new JFrame(title);
/* 312 */     frame.setDefaultCloseOperation(2);
/* 313 */     frame.add(new JScrollPane(new JTree(tree)));
/* 314 */     frame.pack();
/* 315 */     frame.setVisible(true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\OptionalDependencies.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */