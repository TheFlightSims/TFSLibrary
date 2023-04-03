/*     */ package org.apache.commons.math3.geometry.partitioning;
/*     */ 
/*     */ import org.apache.commons.math3.geometry.Space;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class BSPTree<S extends Space> {
/*     */   private SubHyperplane<S> cut;
/*     */   
/*     */   private BSPTree<S> plus;
/*     */   
/*     */   private BSPTree<S> minus;
/*     */   
/*     */   private BSPTree<S> parent;
/*     */   
/*     */   private Object attribute;
/*     */   
/*     */   public BSPTree() {
/*  82 */     this.cut = null;
/*  83 */     this.plus = null;
/*  84 */     this.minus = null;
/*  85 */     this.parent = null;
/*  86 */     this.attribute = null;
/*     */   }
/*     */   
/*     */   public BSPTree(Object attribute) {
/*  93 */     this.cut = null;
/*  94 */     this.plus = null;
/*  95 */     this.minus = null;
/*  96 */     this.parent = null;
/*  97 */     this.attribute = attribute;
/*     */   }
/*     */   
/*     */   public BSPTree(SubHyperplane<S> cut, BSPTree<S> plus, BSPTree<S> minus, Object attribute) {
/* 115 */     this.cut = cut;
/* 116 */     this.plus = plus;
/* 117 */     this.minus = minus;
/* 118 */     this.parent = null;
/* 119 */     this.attribute = attribute;
/* 120 */     plus.parent = this;
/* 121 */     minus.parent = this;
/*     */   }
/*     */   
/*     */   public boolean insertCut(Hyperplane<S> hyperplane) {
/* 149 */     if (this.cut != null) {
/* 150 */       this.plus.parent = null;
/* 151 */       this.minus.parent = null;
/*     */     } 
/* 154 */     SubHyperplane<S> chopped = fitToCell(hyperplane.wholeHyperplane());
/* 155 */     if (chopped.isEmpty()) {
/* 156 */       this.cut = null;
/* 157 */       this.plus = null;
/* 158 */       this.minus = null;
/* 159 */       return false;
/*     */     } 
/* 162 */     this.cut = chopped;
/* 163 */     this.plus = new BSPTree();
/* 164 */     this.plus.parent = this;
/* 165 */     this.minus = new BSPTree();
/* 166 */     this.minus.parent = this;
/* 167 */     return true;
/*     */   }
/*     */   
/*     */   public BSPTree<S> copySelf() {
/* 180 */     if (this.cut == null)
/* 181 */       return new BSPTree(this.attribute); 
/* 184 */     return new BSPTree(this.cut.copySelf(), this.plus.copySelf(), this.minus.copySelf(), this.attribute);
/*     */   }
/*     */   
/*     */   public SubHyperplane<S> getCut() {
/* 193 */     return this.cut;
/*     */   }
/*     */   
/*     */   public BSPTree<S> getPlus() {
/* 201 */     return this.plus;
/*     */   }
/*     */   
/*     */   public BSPTree<S> getMinus() {
/* 209 */     return this.minus;
/*     */   }
/*     */   
/*     */   public BSPTree<S> getParent() {
/* 216 */     return this.parent;
/*     */   }
/*     */   
/*     */   public void setAttribute(Object attribute) {
/* 224 */     this.attribute = attribute;
/*     */   }
/*     */   
/*     */   public Object getAttribute() {
/* 234 */     return this.attribute;
/*     */   }
/*     */   
/*     */   public void visit(BSPTreeVisitor<S> visitor) {
/* 241 */     if (this.cut == null) {
/* 242 */       visitor.visitLeafNode(this);
/*     */     } else {
/* 244 */       switch (visitor.visitOrder(this)) {
/*     */         case PLUS:
/* 246 */           this.plus.visit(visitor);
/* 247 */           this.minus.visit(visitor);
/* 248 */           visitor.visitInternalNode(this);
/*     */           return;
/*     */         case MINUS:
/* 251 */           this.plus.visit(visitor);
/* 252 */           visitor.visitInternalNode(this);
/* 253 */           this.minus.visit(visitor);
/*     */           return;
/*     */         case BOTH:
/* 256 */           this.minus.visit(visitor);
/* 257 */           this.plus.visit(visitor);
/* 258 */           visitor.visitInternalNode(this);
/*     */           return;
/*     */         case null:
/* 261 */           this.minus.visit(visitor);
/* 262 */           visitor.visitInternalNode(this);
/* 263 */           this.plus.visit(visitor);
/*     */           return;
/*     */         case null:
/* 266 */           visitor.visitInternalNode(this);
/* 267 */           this.plus.visit(visitor);
/* 268 */           this.minus.visit(visitor);
/*     */           return;
/*     */         case null:
/* 271 */           visitor.visitInternalNode(this);
/* 272 */           this.minus.visit(visitor);
/* 273 */           this.plus.visit(visitor);
/*     */           return;
/*     */       } 
/* 276 */       throw new RuntimeException("internal error");
/*     */     } 
/*     */   }
/*     */   
/*     */   private SubHyperplane<S> fitToCell(SubHyperplane<S> sub) {
/* 291 */     SubHyperplane<S> s = sub;
/* 292 */     for (BSPTree<S> tree = this; tree.parent != null; tree = tree.parent) {
/* 293 */       if (tree == tree.parent.plus) {
/* 294 */         s = s.split(tree.parent.cut.getHyperplane()).getPlus();
/*     */       } else {
/* 296 */         s = s.split(tree.parent.cut.getHyperplane()).getMinus();
/*     */       } 
/*     */     } 
/* 299 */     return s;
/*     */   }
/*     */   
/*     */   public BSPTree<S> getCell(Vector<S> point) {
/* 311 */     if (this.cut == null)
/* 312 */       return this; 
/* 316 */     double offset = this.cut.getHyperplane().getOffset(point);
/* 318 */     if (FastMath.abs(offset) < 1.0E-10D)
/* 319 */       return this; 
/* 320 */     if (offset <= 0.0D)
/* 322 */       return this.minus.getCell(point); 
/* 325 */     return this.plus.getCell(point);
/*     */   }
/*     */   
/*     */   private void condense() {
/* 335 */     if (this.cut != null && this.plus.cut == null && this.minus.cut == null && ((this.plus.attribute == null && this.minus.attribute == null) || (this.plus.attribute != null && this.plus.attribute.equals(this.minus.attribute)))) {
/* 338 */       this.attribute = (this.plus.attribute == null) ? this.minus.attribute : this.plus.attribute;
/* 339 */       this.cut = null;
/* 340 */       this.plus = null;
/* 341 */       this.minus = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public BSPTree<S> merge(BSPTree<S> tree, LeafMerger<S> leafMerger) {
/* 364 */     return merge(tree, leafMerger, null, false);
/*     */   }
/*     */   
/*     */   private BSPTree<S> merge(BSPTree<S> tree, LeafMerger<S> leafMerger, BSPTree<S> parentTree, boolean isPlusChild) {
/* 384 */     if (this.cut == null)
/* 386 */       return leafMerger.merge(this, tree, parentTree, isPlusChild, true); 
/* 387 */     if (tree.cut == null)
/* 389 */       return leafMerger.merge(tree, this, parentTree, isPlusChild, false); 
/* 392 */     BSPTree<S> merged = tree.split(this.cut);
/* 393 */     if (parentTree != null) {
/* 394 */       merged.parent = parentTree;
/* 395 */       if (isPlusChild) {
/* 396 */         parentTree.plus = merged;
/*     */       } else {
/* 398 */         parentTree.minus = merged;
/*     */       } 
/*     */     } 
/* 403 */     this.plus.merge(merged.plus, leafMerger, merged, true);
/* 404 */     this.minus.merge(merged.minus, leafMerger, merged, false);
/* 405 */     merged.condense();
/* 406 */     if (merged.cut != null)
/* 407 */       merged.cut = merged.fitToCell(merged.cut.getHyperplane().wholeHyperplane()); 
/* 411 */     return merged;
/*     */   }
/*     */   
/*     */   public BSPTree<S> split(SubHyperplane<S> sub) {
/*     */     BSPTree<S> split;
/*     */     SubHyperplane.SplitSubHyperplane<S> cutParts, subParts;
/*     */     BSPTree<S> bSPTree1, tmp;
/* 488 */     if (this.cut == null)
/* 489 */       return new BSPTree(sub, copySelf(), new BSPTree(this.attribute), null); 
/* 493 */     Hyperplane<S> cHyperplane = this.cut.getHyperplane();
/* 494 */     Hyperplane<S> sHyperplane = sub.getHyperplane();
/* 495 */     switch (sub.side(cHyperplane)) {
/*     */       case PLUS:
/* 498 */         split = this.plus.split(sub);
/* 499 */         if (this.cut.side(sHyperplane) == Side.PLUS) {
/* 500 */           split.plus = new BSPTree(this.cut.copySelf(), split.plus, this.minus.copySelf(), this.attribute);
/* 502 */           split.plus.condense();
/* 503 */           split.plus.parent = split;
/*     */         } else {
/* 505 */           split.minus = new BSPTree(this.cut.copySelf(), split.minus, this.minus.copySelf(), this.attribute);
/* 507 */           split.minus.condense();
/* 508 */           split.minus.parent = split;
/*     */         } 
/* 510 */         return split;
/*     */       case MINUS:
/* 514 */         split = this.minus.split(sub);
/* 515 */         if (this.cut.side(sHyperplane) == Side.PLUS) {
/* 516 */           split.plus = new BSPTree(this.cut.copySelf(), this.plus.copySelf(), split.plus, this.attribute);
/* 518 */           split.plus.condense();
/* 519 */           split.plus.parent = split;
/*     */         } else {
/* 521 */           split.minus = new BSPTree(this.cut.copySelf(), this.plus.copySelf(), split.minus, this.attribute);
/* 523 */           split.minus.condense();
/* 524 */           split.minus.parent = split;
/*     */         } 
/* 526 */         return split;
/*     */       case BOTH:
/* 530 */         cutParts = this.cut.split(sHyperplane);
/* 531 */         subParts = sub.split(cHyperplane);
/* 532 */         bSPTree1 = new BSPTree(sub, this.plus.split(subParts.getPlus()), this.minus.split(subParts.getMinus()), null);
/* 535 */         bSPTree1.plus.cut = cutParts.getPlus();
/* 536 */         bSPTree1.minus.cut = cutParts.getMinus();
/* 537 */         tmp = bSPTree1.plus.minus;
/* 538 */         bSPTree1.plus.minus = bSPTree1.minus.plus;
/* 539 */         bSPTree1.plus.minus.parent = bSPTree1.plus;
/* 540 */         bSPTree1.minus.plus = tmp;
/* 541 */         bSPTree1.minus.plus.parent = bSPTree1.minus;
/* 542 */         bSPTree1.plus.condense();
/* 543 */         bSPTree1.minus.condense();
/* 544 */         return bSPTree1;
/*     */     } 
/* 547 */     return cHyperplane.sameOrientationAs(sHyperplane) ? new BSPTree(sub, this.plus.copySelf(), this.minus.copySelf(), this.attribute) : new BSPTree(sub, this.minus.copySelf(), this.plus.copySelf(), this.attribute);
/*     */   }
/*     */   
/*     */   public void insertInTree(BSPTree<S> parentTree, boolean isPlusChild) {
/* 566 */     this.parent = parentTree;
/* 567 */     if (parentTree != null)
/* 568 */       if (isPlusChild) {
/* 569 */         parentTree.plus = this;
/*     */       } else {
/* 571 */         parentTree.minus = this;
/*     */       }  
/* 576 */     if (this.cut != null) {
/* 579 */       for (BSPTree<S> tree = this; tree.parent != null; tree = tree.parent) {
/* 582 */         Hyperplane<S> hyperplane = tree.parent.cut.getHyperplane();
/* 586 */         if (tree == tree.parent.plus) {
/* 587 */           this.cut = this.cut.split(hyperplane).getPlus();
/* 588 */           this.plus.chopOffMinus(hyperplane);
/* 589 */           this.minus.chopOffMinus(hyperplane);
/*     */         } else {
/* 591 */           this.cut = this.cut.split(hyperplane).getMinus();
/* 592 */           this.plus.chopOffPlus(hyperplane);
/* 593 */           this.minus.chopOffPlus(hyperplane);
/*     */         } 
/*     */       } 
/* 600 */       condense();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void chopOffMinus(Hyperplane<S> hyperplane) {
/* 613 */     if (this.cut != null) {
/* 614 */       this.cut = this.cut.split(hyperplane).getPlus();
/* 615 */       this.plus.chopOffMinus(hyperplane);
/* 616 */       this.minus.chopOffMinus(hyperplane);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void chopOffPlus(Hyperplane<S> hyperplane) {
/* 627 */     if (this.cut != null) {
/* 628 */       this.cut = this.cut.split(hyperplane).getMinus();
/* 629 */       this.plus.chopOffPlus(hyperplane);
/* 630 */       this.minus.chopOffPlus(hyperplane);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface LeafMerger<S extends Space> {
/*     */     BSPTree<S> merge(BSPTree<S> param1BSPTree1, BSPTree<S> param1BSPTree2, BSPTree<S> param1BSPTree3, boolean param1Boolean1, boolean param1Boolean2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\BSPTree.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */