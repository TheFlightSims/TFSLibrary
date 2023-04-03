/*     */ package org.apache.commons.math3.geometry.partitioning;
/*     */ 
/*     */ import org.apache.commons.math3.geometry.Space;
/*     */ 
/*     */ public class RegionFactory<S extends Space> {
/*  36 */   private final NodesCleaner nodeCleaner = new NodesCleaner();
/*     */   
/*     */   public Region<S> buildConvex(Hyperplane<S>... hyperplanes) {
/*  44 */     if (hyperplanes == null || hyperplanes.length == 0)
/*  45 */       return null; 
/*  49 */     Region<S> region = hyperplanes[0].wholeSpace();
/*  52 */     BSPTree<S> node = region.getTree(false);
/*  53 */     node.setAttribute(Boolean.TRUE);
/*  54 */     for (Hyperplane<S> hyperplane : hyperplanes) {
/*  55 */       if (node.insertCut(hyperplane)) {
/*  56 */         node.setAttribute(null);
/*  57 */         node.getPlus().setAttribute(Boolean.FALSE);
/*  58 */         node = node.getMinus();
/*  59 */         node.setAttribute(Boolean.TRUE);
/*     */       } 
/*     */     } 
/*  63 */     return region;
/*     */   }
/*     */   
/*     */   public Region<S> union(Region<S> region1, Region<S> region2) {
/*  75 */     BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new UnionMerger());
/*  77 */     tree.visit(this.nodeCleaner);
/*  78 */     return region1.buildNew(tree);
/*     */   }
/*     */   
/*     */   public Region<S> intersection(Region<S> region1, Region<S> region2) {
/*  89 */     BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new IntersectionMerger());
/*  91 */     tree.visit(this.nodeCleaner);
/*  92 */     return region1.buildNew(tree);
/*     */   }
/*     */   
/*     */   public Region<S> xor(Region<S> region1, Region<S> region2) {
/* 103 */     BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new XorMerger());
/* 105 */     tree.visit(this.nodeCleaner);
/* 106 */     return region1.buildNew(tree);
/*     */   }
/*     */   
/*     */   public Region<S> difference(Region<S> region1, Region<S> region2) {
/* 117 */     BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new DifferenceMerger());
/* 119 */     tree.visit(this.nodeCleaner);
/* 120 */     return region1.buildNew(tree);
/*     */   }
/*     */   
/*     */   public Region<S> getComplement(Region<S> region) {
/* 129 */     return region.buildNew(recurseComplement(region.getTree(false)));
/*     */   }
/*     */   
/*     */   private BSPTree<S> recurseComplement(BSPTree<S> node) {
/* 137 */     if (node.getCut() == null)
/* 138 */       return new BSPTree<S>(((Boolean)node.getAttribute()).booleanValue() ? Boolean.FALSE : Boolean.TRUE); 
/* 142 */     BoundaryAttribute<S> attribute = (BoundaryAttribute<S>)node.getAttribute();
/* 143 */     if (attribute != null) {
/* 144 */       SubHyperplane<S> plusOutside = (attribute.getPlusInside() == null) ? null : attribute.getPlusInside().copySelf();
/* 146 */       SubHyperplane<S> plusInside = (attribute.getPlusOutside() == null) ? null : attribute.getPlusOutside().copySelf();
/* 148 */       attribute = new BoundaryAttribute<S>(plusOutside, plusInside);
/*     */     } 
/* 151 */     return new BSPTree<S>(node.getCut().copySelf(), recurseComplement(node.getPlus()), recurseComplement(node.getMinus()), attribute);
/*     */   }
/*     */   
/*     */   private class UnionMerger implements BSPTree.LeafMerger<S> {
/*     */     private UnionMerger() {}
/*     */     
/*     */     public BSPTree<S> merge(BSPTree<S> leaf, BSPTree<S> tree, BSPTree<S> parentTree, boolean isPlusChild, boolean leafFromInstance) {
/* 164 */       if (((Boolean)leaf.getAttribute()).booleanValue()) {
/* 166 */         leaf.insertInTree(parentTree, isPlusChild);
/* 167 */         return leaf;
/*     */       } 
/* 170 */       tree.insertInTree(parentTree, isPlusChild);
/* 171 */       return tree;
/*     */     }
/*     */   }
/*     */   
/*     */   private class IntersectionMerger implements BSPTree.LeafMerger<S> {
/*     */     private IntersectionMerger() {}
/*     */     
/*     */     public BSPTree<S> merge(BSPTree<S> leaf, BSPTree<S> tree, BSPTree<S> parentTree, boolean isPlusChild, boolean leafFromInstance) {
/* 181 */       if (((Boolean)leaf.getAttribute()).booleanValue()) {
/* 183 */         tree.insertInTree(parentTree, isPlusChild);
/* 184 */         return tree;
/*     */       } 
/* 187 */       leaf.insertInTree(parentTree, isPlusChild);
/* 188 */       return leaf;
/*     */     }
/*     */   }
/*     */   
/*     */   private class XorMerger implements BSPTree.LeafMerger<S> {
/*     */     private XorMerger() {}
/*     */     
/*     */     public BSPTree<S> merge(BSPTree<S> leaf, BSPTree<S> tree, BSPTree<S> parentTree, boolean isPlusChild, boolean leafFromInstance) {
/* 198 */       BSPTree<S> t = tree;
/* 199 */       if (((Boolean)leaf.getAttribute()).booleanValue())
/* 201 */         t = RegionFactory.this.recurseComplement(t); 
/* 203 */       t.insertInTree(parentTree, isPlusChild);
/* 204 */       return t;
/*     */     }
/*     */   }
/*     */   
/*     */   private class DifferenceMerger implements BSPTree.LeafMerger<S> {
/*     */     private DifferenceMerger() {}
/*     */     
/*     */     public BSPTree<S> merge(BSPTree<S> leaf, BSPTree<S> tree, BSPTree<S> parentTree, boolean isPlusChild, boolean leafFromInstance) {
/* 214 */       if (((Boolean)leaf.getAttribute()).booleanValue()) {
/* 216 */         BSPTree<S> argTree = RegionFactory.this.recurseComplement(leafFromInstance ? tree : leaf);
/* 218 */         argTree.insertInTree(parentTree, isPlusChild);
/* 219 */         return argTree;
/*     */       } 
/* 222 */       BSPTree<S> instanceTree = leafFromInstance ? leaf : tree;
/* 224 */       instanceTree.insertInTree(parentTree, isPlusChild);
/* 225 */       return instanceTree;
/*     */     }
/*     */   }
/*     */   
/*     */   private class NodesCleaner implements BSPTreeVisitor<S> {
/*     */     private NodesCleaner() {}
/*     */     
/*     */     public BSPTreeVisitor.Order visitOrder(BSPTree<S> node) {
/* 234 */       return BSPTreeVisitor.Order.PLUS_SUB_MINUS;
/*     */     }
/*     */     
/*     */     public void visitInternalNode(BSPTree<S> node) {
/* 239 */       node.setAttribute(null);
/*     */     }
/*     */     
/*     */     public void visitLeafNode(BSPTree<S> node) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\RegionFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */