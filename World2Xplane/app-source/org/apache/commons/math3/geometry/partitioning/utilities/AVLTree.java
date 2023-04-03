/*     */ package org.apache.commons.math3.geometry.partitioning.utilities;
/*     */ 
/*     */ public class AVLTree<T extends Comparable<T>> {
/*  55 */   private Node top = null;
/*     */   
/*     */   public void insert(T element) {
/*  62 */     if (element != null)
/*  63 */       if (this.top == null) {
/*  64 */         this.top = new Node(element, null);
/*     */       } else {
/*  66 */         this.top.insert(element);
/*     */       }  
/*     */   }
/*     */   
/*     */   public boolean delete(T element) {
/*  82 */     if (element != null)
/*  83 */       for (Node node = getNotSmaller(element); node != null; node = node.getNext()) {
/*  86 */         if (node.element == element) {
/*  87 */           node.delete();
/*  88 */           return true;
/*     */         } 
/*  89 */         if (node.element.compareTo(element) > 0)
/*  92 */           return false; 
/*     */       }  
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 103 */     return (this.top == null);
/*     */   }
/*     */   
/*     */   public int size() {
/* 111 */     return (this.top == null) ? 0 : this.top.size();
/*     */   }
/*     */   
/*     */   public Node getSmallest() {
/* 124 */     return (this.top == null) ? null : this.top.getSmallest();
/*     */   }
/*     */   
/*     */   public Node getLargest() {
/* 137 */     return (this.top == null) ? null : this.top.getLargest();
/*     */   }
/*     */   
/*     */   public Node getNotSmaller(T reference) {
/* 152 */     Node candidate = null;
/* 153 */     for (Node node = this.top; node != null; ) {
/* 154 */       if (node.element.compareTo(reference) < 0) {
/* 155 */         if (node.right == null)
/* 156 */           return candidate; 
/* 158 */         node = node.right;
/*     */         continue;
/*     */       } 
/* 160 */       candidate = node;
/* 161 */       if (node.left == null)
/* 162 */         return candidate; 
/* 164 */       node = node.left;
/*     */     } 
/* 167 */     return null;
/*     */   }
/*     */   
/*     */   public Node getNotLarger(T reference) {
/* 183 */     Node candidate = null;
/* 184 */     for (Node node = this.top; node != null; ) {
/* 185 */       if (node.element.compareTo(reference) > 0) {
/* 186 */         if (node.left == null)
/* 187 */           return candidate; 
/* 189 */         node = node.left;
/*     */         continue;
/*     */       } 
/* 191 */       candidate = node;
/* 192 */       if (node.right == null)
/* 193 */         return candidate; 
/* 195 */       node = node.right;
/*     */     } 
/* 198 */     return null;
/*     */   }
/*     */   
/*     */   private enum Skew {
/* 204 */     LEFT_HIGH, RIGHT_HIGH, BALANCED;
/*     */   }
/*     */   
/*     */   public class Node {
/*     */     private T element;
/*     */     
/*     */     private Node left;
/*     */     
/*     */     private Node right;
/*     */     
/*     */     private Node parent;
/*     */     
/*     */     private AVLTree.Skew skew;
/*     */     
/*     */     Node(T element, Node parent) {
/* 246 */       this.element = element;
/* 247 */       this.left = null;
/* 248 */       this.right = null;
/* 249 */       this.parent = parent;
/* 250 */       this.skew = AVLTree.Skew.BALANCED;
/*     */     }
/*     */     
/*     */     public T getElement() {
/* 257 */       return this.element;
/*     */     }
/*     */     
/*     */     int size() {
/* 264 */       return 1 + ((this.left == null) ? 0 : this.left.size()) + ((this.right == null) ? 0 : this.right.size());
/*     */     }
/*     */     
/*     */     Node getSmallest() {
/* 274 */       Node node = this;
/* 275 */       while (node.left != null)
/* 276 */         node = node.left; 
/* 278 */       return node;
/*     */     }
/*     */     
/*     */     Node getLargest() {
/* 288 */       Node node = this;
/* 289 */       while (node.right != null)
/* 290 */         node = node.right; 
/* 292 */       return node;
/*     */     }
/*     */     
/*     */     public Node getPrevious() {
/* 302 */       if (this.left != null) {
/* 303 */         Node node1 = this.left.getLargest();
/* 304 */         if (node1 != null)
/* 305 */           return node1; 
/*     */       } 
/* 309 */       for (Node node = this; node.parent != null; node = node.parent) {
/* 310 */         if (node != node.parent.left)
/* 311 */           return node.parent; 
/*     */       } 
/* 315 */       return null;
/*     */     }
/*     */     
/*     */     public Node getNext() {
/* 327 */       if (this.right != null) {
/* 328 */         Node node1 = this.right.getSmallest();
/* 329 */         if (node1 != null)
/* 330 */           return node1; 
/*     */       } 
/* 334 */       for (Node node = this; node.parent != null; node = node.parent) {
/* 335 */         if (node != node.parent.right)
/* 336 */           return node.parent; 
/*     */       } 
/* 340 */       return null;
/*     */     }
/*     */     
/*     */     boolean insert(T newElement) {
/* 349 */       if (newElement.compareTo(this.element) < 0) {
/* 351 */         if (this.left == null) {
/* 352 */           this.left = new Node(newElement, this);
/* 353 */           return rebalanceLeftGrown();
/*     */         } 
/* 355 */         return this.left.insert(newElement) ? rebalanceLeftGrown() : false;
/*     */       } 
/* 359 */       if (this.right == null) {
/* 360 */         this.right = new Node(newElement, this);
/* 361 */         return rebalanceRightGrown();
/*     */       } 
/* 363 */       return this.right.insert(newElement) ? rebalanceRightGrown() : false;
/*     */     }
/*     */     
/*     */     public void delete() {
/* 370 */       if (this.parent == null && this.left == null && this.right == null) {
/* 372 */         this.element = null;
/* 373 */         AVLTree.this.top = null;
/*     */       } else {
/*     */         Node child;
/*     */         boolean leftShrunk;
/* 379 */         if (this.left == null && this.right == null) {
/* 380 */           node = this;
/* 381 */           this.element = null;
/* 382 */           leftShrunk = (node == node.parent.left);
/* 383 */           child = null;
/*     */         } else {
/* 385 */           node = (this.left != null) ? this.left.getLargest() : this.right.getSmallest();
/* 386 */           this.element = node.element;
/* 387 */           leftShrunk = (node == node.parent.left);
/* 388 */           child = (node.left != null) ? node.left : node.right;
/*     */         } 
/* 391 */         Node node = node.parent;
/* 392 */         if (leftShrunk) {
/* 393 */           node.left = child;
/*     */         } else {
/* 395 */           node.right = child;
/*     */         } 
/* 397 */         if (child != null)
/* 398 */           child.parent = node; 
/* 401 */         while (leftShrunk ? node.rebalanceLeftShrunk() : node.rebalanceRightShrunk()) {
/* 402 */           if (node.parent == null)
/*     */             return; 
/* 405 */           leftShrunk = (node == node.parent.left);
/* 406 */           node = node.parent;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private boolean rebalanceLeftGrown() {
/* 416 */       switch (this.skew) {
/*     */         case LEFT_HIGH:
/* 418 */           if (this.left.skew == AVLTree.Skew.LEFT_HIGH) {
/* 419 */             rotateCW();
/* 420 */             this.skew = AVLTree.Skew.BALANCED;
/* 421 */             this.right.skew = AVLTree.Skew.BALANCED;
/*     */           } else {
/* 423 */             AVLTree.Skew s = this.left.right.skew;
/* 424 */             this.left.rotateCCW();
/* 425 */             rotateCW();
/* 426 */             switch (s) {
/*     */               case LEFT_HIGH:
/* 428 */                 this.left.skew = AVLTree.Skew.BALANCED;
/* 429 */                 this.right.skew = AVLTree.Skew.RIGHT_HIGH;
/*     */                 break;
/*     */               case RIGHT_HIGH:
/* 432 */                 this.left.skew = AVLTree.Skew.LEFT_HIGH;
/* 433 */                 this.right.skew = AVLTree.Skew.BALANCED;
/*     */                 break;
/*     */               default:
/* 436 */                 this.left.skew = AVLTree.Skew.BALANCED;
/* 437 */                 this.right.skew = AVLTree.Skew.BALANCED;
/*     */                 break;
/*     */             } 
/* 439 */             this.skew = AVLTree.Skew.BALANCED;
/*     */           } 
/* 441 */           return false;
/*     */         case RIGHT_HIGH:
/* 443 */           this.skew = AVLTree.Skew.BALANCED;
/* 444 */           return false;
/*     */       } 
/* 446 */       this.skew = AVLTree.Skew.LEFT_HIGH;
/* 447 */       return true;
/*     */     }
/*     */     
/*     */     private boolean rebalanceRightGrown() {
/* 455 */       switch (this.skew) {
/*     */         case LEFT_HIGH:
/* 457 */           this.skew = AVLTree.Skew.BALANCED;
/* 458 */           return false;
/*     */         case RIGHT_HIGH:
/* 460 */           if (this.right.skew == AVLTree.Skew.RIGHT_HIGH) {
/* 461 */             rotateCCW();
/* 462 */             this.skew = AVLTree.Skew.BALANCED;
/* 463 */             this.left.skew = AVLTree.Skew.BALANCED;
/*     */           } else {
/* 465 */             AVLTree.Skew s = this.right.left.skew;
/* 466 */             this.right.rotateCW();
/* 467 */             rotateCCW();
/* 468 */             switch (s) {
/*     */               case LEFT_HIGH:
/* 470 */                 this.left.skew = AVLTree.Skew.BALANCED;
/* 471 */                 this.right.skew = AVLTree.Skew.RIGHT_HIGH;
/*     */                 break;
/*     */               case RIGHT_HIGH:
/* 474 */                 this.left.skew = AVLTree.Skew.LEFT_HIGH;
/* 475 */                 this.right.skew = AVLTree.Skew.BALANCED;
/*     */                 break;
/*     */               default:
/* 478 */                 this.left.skew = AVLTree.Skew.BALANCED;
/* 479 */                 this.right.skew = AVLTree.Skew.BALANCED;
/*     */                 break;
/*     */             } 
/* 481 */             this.skew = AVLTree.Skew.BALANCED;
/*     */           } 
/* 483 */           return false;
/*     */       } 
/* 485 */       this.skew = AVLTree.Skew.RIGHT_HIGH;
/* 486 */       return true;
/*     */     }
/*     */     
/*     */     private boolean rebalanceLeftShrunk() {
/*     */       AVLTree.Skew s;
/* 494 */       switch (this.skew) {
/*     */         case LEFT_HIGH:
/* 496 */           this.skew = AVLTree.Skew.BALANCED;
/* 497 */           return true;
/*     */         case RIGHT_HIGH:
/* 499 */           if (this.right.skew == AVLTree.Skew.RIGHT_HIGH) {
/* 500 */             rotateCCW();
/* 501 */             this.skew = AVLTree.Skew.BALANCED;
/* 502 */             this.left.skew = AVLTree.Skew.BALANCED;
/* 503 */             return true;
/*     */           } 
/* 504 */           if (this.right.skew == AVLTree.Skew.BALANCED) {
/* 505 */             rotateCCW();
/* 506 */             this.skew = AVLTree.Skew.LEFT_HIGH;
/* 507 */             this.left.skew = AVLTree.Skew.RIGHT_HIGH;
/* 508 */             return false;
/*     */           } 
/* 510 */           s = this.right.left.skew;
/* 511 */           this.right.rotateCW();
/* 512 */           rotateCCW();
/* 513 */           switch (s) {
/*     */             case LEFT_HIGH:
/* 515 */               this.left.skew = AVLTree.Skew.BALANCED;
/* 516 */               this.right.skew = AVLTree.Skew.RIGHT_HIGH;
/* 526 */               this.skew = AVLTree.Skew.BALANCED;
/* 527 */               return true;
/*     */             case RIGHT_HIGH:
/*     */               this.left.skew = AVLTree.Skew.LEFT_HIGH;
/*     */               this.right.skew = AVLTree.Skew.BALANCED;
/*     */               this.skew = AVLTree.Skew.BALANCED;
/* 527 */               return true;
/*     */           } 
/*     */           this.left.skew = AVLTree.Skew.BALANCED;
/*     */           this.right.skew = AVLTree.Skew.BALANCED;
/*     */           this.skew = AVLTree.Skew.BALANCED;
/* 527 */           return true;
/*     */       } 
/* 530 */       this.skew = AVLTree.Skew.RIGHT_HIGH;
/* 531 */       return false;
/*     */     }
/*     */     
/*     */     private boolean rebalanceRightShrunk() {
/*     */       AVLTree.Skew s;
/* 539 */       switch (this.skew) {
/*     */         case RIGHT_HIGH:
/* 541 */           this.skew = AVLTree.Skew.BALANCED;
/* 542 */           return true;
/*     */         case LEFT_HIGH:
/* 544 */           if (this.left.skew == AVLTree.Skew.LEFT_HIGH) {
/* 545 */             rotateCW();
/* 546 */             this.skew = AVLTree.Skew.BALANCED;
/* 547 */             this.right.skew = AVLTree.Skew.BALANCED;
/* 548 */             return true;
/*     */           } 
/* 549 */           if (this.left.skew == AVLTree.Skew.BALANCED) {
/* 550 */             rotateCW();
/* 551 */             this.skew = AVLTree.Skew.RIGHT_HIGH;
/* 552 */             this.right.skew = AVLTree.Skew.LEFT_HIGH;
/* 553 */             return false;
/*     */           } 
/* 555 */           s = this.left.right.skew;
/* 556 */           this.left.rotateCCW();
/* 557 */           rotateCW();
/* 558 */           switch (s) {
/*     */             case LEFT_HIGH:
/* 560 */               this.left.skew = AVLTree.Skew.BALANCED;
/* 561 */               this.right.skew = AVLTree.Skew.RIGHT_HIGH;
/* 571 */               this.skew = AVLTree.Skew.BALANCED;
/* 572 */               return true;
/*     */             case RIGHT_HIGH:
/*     */               this.left.skew = AVLTree.Skew.LEFT_HIGH;
/*     */               this.right.skew = AVLTree.Skew.BALANCED;
/*     */               this.skew = AVLTree.Skew.BALANCED;
/* 572 */               return true;
/*     */           } 
/*     */           this.left.skew = AVLTree.Skew.BALANCED;
/*     */           this.right.skew = AVLTree.Skew.BALANCED;
/*     */           this.skew = AVLTree.Skew.BALANCED;
/* 572 */           return true;
/*     */       } 
/* 575 */       this.skew = AVLTree.Skew.LEFT_HIGH;
/* 576 */       return false;
/*     */     }
/*     */     
/*     */     private void rotateCW() {
/* 586 */       T tmpElt = this.element;
/* 587 */       this.element = this.left.element;
/* 588 */       this.left.element = tmpElt;
/* 590 */       Node tmpNode = this.left;
/* 591 */       this.left = tmpNode.left;
/* 592 */       tmpNode.left = tmpNode.right;
/* 593 */       tmpNode.right = this.right;
/* 594 */       this.right = tmpNode;
/* 596 */       if (this.left != null)
/* 597 */         this.left.parent = this; 
/* 599 */       if (this.right.right != null)
/* 600 */         this.right.right.parent = this.right; 
/*     */     }
/*     */     
/*     */     private void rotateCCW() {
/* 611 */       T tmpElt = this.element;
/* 612 */       this.element = this.right.element;
/* 613 */       this.right.element = tmpElt;
/* 615 */       Node tmpNode = this.right;
/* 616 */       this.right = tmpNode.right;
/* 617 */       tmpNode.right = tmpNode.left;
/* 618 */       tmpNode.left = this.left;
/* 619 */       this.left = tmpNode;
/* 621 */       if (this.right != null)
/* 622 */         this.right.parent = this; 
/* 624 */       if (this.left.left != null)
/* 625 */         this.left.left.parent = this.left; 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitionin\\utilities\AVLTree.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */