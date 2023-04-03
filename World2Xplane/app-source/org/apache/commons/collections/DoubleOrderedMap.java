/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ 
/*      */ public final class DoubleOrderedMap extends AbstractMap {
/*      */   private static final int KEY = 0;
/*      */   
/*      */   private static final int VALUE = 1;
/*      */   
/*      */   private static final int SUM_OF_INDICES = 1;
/*      */   
/*      */   private static final int FIRST_INDEX = 0;
/*      */   
/*      */   private static final int NUMBER_OF_INDICES = 2;
/*      */   
/*  115 */   private static final String[] dataName = new String[] { "key", "value" };
/*      */   
/*  117 */   private Node[] rootNode = new Node[] { null, null };
/*      */   
/*  118 */   private int nodeCount = 0;
/*      */   
/*  119 */   private int modifications = 0;
/*      */   
/*  120 */   private Set[] setOfKeys = new Set[] { null, null };
/*      */   
/*  121 */   private Set[] setOfEntries = new Set[] { null, null };
/*      */   
/*  122 */   private Collection[] collectionOfValues = new Collection[] { null, null };
/*      */   
/*      */   public DoubleOrderedMap(Map map) throws ClassCastException, NullPointerException, IllegalArgumentException {
/*  150 */     putAll(map);
/*      */   }
/*      */   
/*      */   public Object getKeyForValue(Object value) throws ClassCastException, NullPointerException {
/*  168 */     return doGet((Comparable)value, 1);
/*      */   }
/*      */   
/*      */   public Object removeValue(Object value) {
/*  180 */     return doRemove((Comparable)value, 1);
/*      */   }
/*      */   
/*      */   public Set entrySetByValue() {
/*  204 */     if (this.setOfEntries[1] == null)
/*  205 */       this.setOfEntries[1] = new AbstractSet(this) {
/*      */           private final DoubleOrderedMap this$0;
/*      */           
/*      */           public Iterator iterator() {
/*  209 */             return new DoubleOrderedMap.DoubleOrderedMapIterator(this, 1) {
/*      */                 private final DoubleOrderedMap.null this$1;
/*      */                 
/*      */                 protected Object doGetNext() {
/*  212 */                   return this.lastReturnedNode;
/*      */                 }
/*      */               };
/*      */           }
/*      */           
/*      */           public boolean contains(Object o) {
/*  219 */             if (!(o instanceof Map.Entry))
/*  220 */               return false; 
/*  223 */             Map.Entry entry = (Map.Entry)o;
/*  224 */             Object key = entry.getKey();
/*  225 */             DoubleOrderedMap.Node node = this.this$0.lookup((Comparable)entry.getValue(), 1);
/*  228 */             return (node != null && node.getData(0).equals(key));
/*      */           }
/*      */           
/*      */           public boolean remove(Object o) {
/*  233 */             if (!(o instanceof Map.Entry))
/*  234 */               return false; 
/*  237 */             Map.Entry entry = (Map.Entry)o;
/*  238 */             Object key = entry.getKey();
/*  239 */             DoubleOrderedMap.Node node = this.this$0.lookup((Comparable)entry.getValue(), 1);
/*  242 */             if (node != null && node.getData(0).equals(key)) {
/*  243 */               this.this$0.doRedBlackDelete(node);
/*  245 */               return true;
/*      */             } 
/*  248 */             return false;
/*      */           }
/*      */           
/*      */           public int size() {
/*  252 */             return this.this$0.size();
/*      */           }
/*      */           
/*      */           public void clear() {
/*  256 */             this.this$0.clear();
/*      */           }
/*      */         }; 
/*  261 */     return this.setOfEntries[1];
/*      */   }
/*      */   
/*      */   public Set keySetByValue() {
/*  284 */     if (this.setOfKeys[1] == null)
/*  285 */       this.setOfKeys[1] = new AbstractSet(this) {
/*      */           private final DoubleOrderedMap this$0;
/*      */           
/*      */           public Iterator iterator() {
/*  289 */             return new DoubleOrderedMap.DoubleOrderedMapIterator(this, 1) {
/*      */                 private final DoubleOrderedMap.null this$1;
/*      */                 
/*      */                 protected Object doGetNext() {
/*  292 */                   return this.lastReturnedNode.getData(0);
/*      */                 }
/*      */               };
/*      */           }
/*      */           
/*      */           public int size() {
/*  298 */             return this.this$0.size();
/*      */           }
/*      */           
/*      */           public boolean contains(Object o) {
/*  302 */             return this.this$0.containsKey(o);
/*      */           }
/*      */           
/*      */           public boolean remove(Object o) {
/*  307 */             int oldnodeCount = this.this$0.nodeCount;
/*  309 */             this.this$0.remove(o);
/*  311 */             return (this.this$0.nodeCount != oldnodeCount);
/*      */           }
/*      */           
/*      */           public void clear() {
/*  315 */             this.this$0.clear();
/*      */           }
/*      */         }; 
/*  320 */     return this.setOfKeys[1];
/*      */   }
/*      */   
/*      */   public Collection valuesByValue() {
/*  343 */     if (this.collectionOfValues[1] == null)
/*  344 */       this.collectionOfValues[1] = new AbstractCollection(this) {
/*      */           private final DoubleOrderedMap this$0;
/*      */           
/*      */           public Iterator iterator() {
/*  348 */             return new DoubleOrderedMap.DoubleOrderedMapIterator(this, 1) {
/*      */                 private final DoubleOrderedMap.null this$1;
/*      */                 
/*      */                 protected Object doGetNext() {
/*  351 */                   return this.lastReturnedNode.getData(1);
/*      */                 }
/*      */               };
/*      */           }
/*      */           
/*      */           public int size() {
/*  357 */             return this.this$0.size();
/*      */           }
/*      */           
/*      */           public boolean contains(Object o) {
/*  361 */             return this.this$0.containsValue(o);
/*      */           }
/*      */           
/*      */           public boolean remove(Object o) {
/*  366 */             int oldnodeCount = this.this$0.nodeCount;
/*  368 */             this.this$0.removeValue(o);
/*  370 */             return (this.this$0.nodeCount != oldnodeCount);
/*      */           }
/*      */           
/*      */           public boolean removeAll(Collection c) {
/*  375 */             boolean modified = false;
/*  376 */             Iterator iter = c.iterator();
/*  378 */             while (iter.hasNext()) {
/*  379 */               if (this.this$0.removeValue(iter.next()) != null)
/*  380 */                 modified = true; 
/*      */             } 
/*  384 */             return modified;
/*      */           }
/*      */           
/*      */           public void clear() {
/*  388 */             this.this$0.clear();
/*      */           }
/*      */         }; 
/*  393 */     return this.collectionOfValues[1];
/*      */   }
/*      */   
/*      */   private Object doRemove(Comparable o, int index) {
/*  408 */     Node node = lookup(o, index);
/*  409 */     Object rval = null;
/*  411 */     if (node != null) {
/*  412 */       rval = node.getData(oppositeIndex(index));
/*  414 */       doRedBlackDelete(node);
/*      */     } 
/*  417 */     return rval;
/*      */   }
/*      */   
/*      */   private Object doGet(Comparable o, int index) {
/*  432 */     checkNonNullComparable(o, index);
/*  434 */     Node node = lookup(o, index);
/*  436 */     return (node == null) ? null : node.getData(oppositeIndex(index));
/*      */   }
/*      */   
/*      */   private int oppositeIndex(int index) {
/*  453 */     return 1 - index;
/*      */   }
/*      */   
/*      */   private Node lookup(Comparable data, int index) {
/*  467 */     Node rval = null;
/*  468 */     Node node = this.rootNode[index];
/*  470 */     while (node != null) {
/*  471 */       int cmp = compare(data, node.getData(index));
/*  473 */       if (cmp == 0) {
/*  474 */         rval = node;
/*      */         break;
/*      */       } 
/*  478 */       node = (cmp < 0) ? node.getLeft(index) : node.getRight(index);
/*      */     } 
/*  484 */     return rval;
/*      */   }
/*      */   
/*      */   private static int compare(Comparable o1, Comparable o2) {
/*  497 */     return o1.compareTo(o2);
/*      */   }
/*      */   
/*      */   private static Node leastNode(Node node, int index) {
/*  512 */     Node rval = node;
/*  514 */     if (rval != null)
/*  515 */       while (rval.getLeft(index) != null)
/*  516 */         rval = rval.getLeft(index);  
/*  520 */     return rval;
/*      */   }
/*      */   
/*      */   private Node nextGreater(Node node, int index) {
/*  533 */     Node rval = null;
/*  535 */     if (node == null) {
/*  536 */       rval = null;
/*  537 */     } else if (node.getRight(index) != null) {
/*  541 */       rval = leastNode(node.getRight(index), index);
/*      */     } else {
/*  550 */       Node parent = node.getParent(index);
/*  551 */       Node child = node;
/*  553 */       while (parent != null && child == parent.getRight(index)) {
/*  554 */         child = parent;
/*  555 */         parent = parent.getParent(index);
/*      */       } 
/*  558 */       rval = parent;
/*      */     } 
/*  561 */     return rval;
/*      */   }
/*      */   
/*      */   private static void copyColor(Node from, Node to, int index) {
/*  575 */     if (to != null)
/*  576 */       if (from == null) {
/*  579 */         to.setBlack(index);
/*      */       } else {
/*  581 */         to.copyColor(from, index);
/*      */       }  
/*      */   }
/*      */   
/*      */   private static boolean isRed(Node node, int index) {
/*  595 */     return (node == null) ? false : node.isRed(index);
/*      */   }
/*      */   
/*      */   private static boolean isBlack(Node node, int index) {
/*  609 */     return (node == null) ? true : node.isBlack(index);
/*      */   }
/*      */   
/*      */   private static void makeRed(Node node, int index) {
/*  622 */     if (node != null)
/*  623 */       node.setRed(index); 
/*      */   }
/*      */   
/*      */   private static void makeBlack(Node node, int index) {
/*  635 */     if (node != null)
/*  636 */       node.setBlack(index); 
/*      */   }
/*      */   
/*      */   private static Node getGrandParent(Node node, int index) {
/*  648 */     return getParent(getParent(node, index), index);
/*      */   }
/*      */   
/*      */   private static Node getParent(Node node, int index) {
/*  660 */     return (node == null) ? null : node.getParent(index);
/*      */   }
/*      */   
/*      */   private static Node getRightChild(Node node, int index) {
/*  674 */     return (node == null) ? null : node.getRight(index);
/*      */   }
/*      */   
/*      */   private static Node getLeftChild(Node node, int index) {
/*  688 */     return (node == null) ? null : node.getLeft(index);
/*      */   }
/*      */   
/*      */   private static boolean isLeftChild(Node node, int index) {
/*  706 */     return (node == null) ? true : ((node.getParent(index) == null) ? false : ((node == node.getParent(index).getLeft(index))));
/*      */   }
/*      */   
/*      */   private static boolean isRightChild(Node node, int index) {
/*  726 */     return (node == null) ? true : ((node.getParent(index) == null) ? false : ((node == node.getParent(index).getRight(index))));
/*      */   }
/*      */   
/*      */   private void rotateLeft(Node node, int index) {
/*  741 */     Node rightChild = node.getRight(index);
/*  743 */     node.setRight(rightChild.getLeft(index), index);
/*  745 */     if (rightChild.getLeft(index) != null)
/*  746 */       rightChild.getLeft(index).setParent(node, index); 
/*  749 */     rightChild.setParent(node.getParent(index), index);
/*  751 */     if (node.getParent(index) == null) {
/*  754 */       this.rootNode[index] = rightChild;
/*  755 */     } else if (node.getParent(index).getLeft(index) == node) {
/*  756 */       node.getParent(index).setLeft(rightChild, index);
/*      */     } else {
/*  758 */       node.getParent(index).setRight(rightChild, index);
/*      */     } 
/*  761 */     rightChild.setLeft(node, index);
/*  762 */     node.setParent(rightChild, index);
/*      */   }
/*      */   
/*      */   private void rotateRight(Node node, int index) {
/*  773 */     Node leftChild = node.getLeft(index);
/*  775 */     node.setLeft(leftChild.getRight(index), index);
/*  777 */     if (leftChild.getRight(index) != null)
/*  778 */       leftChild.getRight(index).setParent(node, index); 
/*  781 */     leftChild.setParent(node.getParent(index), index);
/*  783 */     if (node.getParent(index) == null) {
/*  786 */       this.rootNode[index] = leftChild;
/*  787 */     } else if (node.getParent(index).getRight(index) == node) {
/*  788 */       node.getParent(index).setRight(leftChild, index);
/*      */     } else {
/*  790 */       node.getParent(index).setLeft(leftChild, index);
/*      */     } 
/*  793 */     leftChild.setRight(node, index);
/*  794 */     node.setParent(leftChild, index);
/*      */   }
/*      */   
/*      */   private void doRedBlackInsert(Node insertedNode, int index) {
/*  806 */     Node currentNode = insertedNode;
/*  808 */     makeRed(currentNode, index);
/*  811 */     while (currentNode != null && currentNode != this.rootNode[index] && isRed(currentNode.getParent(index), index)) {
/*  812 */       if (isLeftChild(getParent(currentNode, index), index)) {
/*  813 */         Node node = getRightChild(getGrandParent(currentNode, index), index);
/*  816 */         if (isRed(node, index)) {
/*  817 */           makeBlack(getParent(currentNode, index), index);
/*  818 */           makeBlack(node, index);
/*  819 */           makeRed(getGrandParent(currentNode, index), index);
/*  821 */           currentNode = getGrandParent(currentNode, index);
/*      */           continue;
/*      */         } 
/*  823 */         if (isRightChild(currentNode, index)) {
/*  824 */           currentNode = getParent(currentNode, index);
/*  826 */           rotateLeft(currentNode, index);
/*      */         } 
/*  829 */         makeBlack(getParent(currentNode, index), index);
/*  830 */         makeRed(getGrandParent(currentNode, index), index);
/*  832 */         if (getGrandParent(currentNode, index) != null)
/*  833 */           rotateRight(getGrandParent(currentNode, index), index); 
/*      */         continue;
/*      */       } 
/*  840 */       Node y = getLeftChild(getGrandParent(currentNode, index), index);
/*  843 */       if (isRed(y, index)) {
/*  844 */         makeBlack(getParent(currentNode, index), index);
/*  845 */         makeBlack(y, index);
/*  846 */         makeRed(getGrandParent(currentNode, index), index);
/*  848 */         currentNode = getGrandParent(currentNode, index);
/*      */         continue;
/*      */       } 
/*  850 */       if (isLeftChild(currentNode, index)) {
/*  851 */         currentNode = getParent(currentNode, index);
/*  853 */         rotateRight(currentNode, index);
/*      */       } 
/*  856 */       makeBlack(getParent(currentNode, index), index);
/*  857 */       makeRed(getGrandParent(currentNode, index), index);
/*  859 */       if (getGrandParent(currentNode, index) != null)
/*  860 */         rotateLeft(getGrandParent(currentNode, index), index); 
/*      */     } 
/*  866 */     makeBlack(this.rootNode[index], index);
/*      */   }
/*      */   
/*      */   private void doRedBlackDelete(Node deletedNode) {
/*  877 */     for (int index = 0; index < 2; index++) {
/*  881 */       if (deletedNode.getLeft(index) != null && deletedNode.getRight(index) != null)
/*  883 */         swapPosition(nextGreater(deletedNode, index), deletedNode, index); 
/*  887 */       Node replacement = (deletedNode.getLeft(index) != null) ? deletedNode.getLeft(index) : deletedNode.getRight(index);
/*  891 */       if (replacement != null) {
/*  892 */         replacement.setParent(deletedNode.getParent(index), index);
/*  894 */         if (deletedNode.getParent(index) == null) {
/*  895 */           this.rootNode[index] = replacement;
/*  896 */         } else if (deletedNode == deletedNode.getParent(index).getLeft(index)) {
/*  898 */           deletedNode.getParent(index).setLeft(replacement, index);
/*      */         } else {
/*  900 */           deletedNode.getParent(index).setRight(replacement, index);
/*      */         } 
/*  903 */         deletedNode.setLeft(null, index);
/*  904 */         deletedNode.setRight(null, index);
/*  905 */         deletedNode.setParent(null, index);
/*  907 */         if (isBlack(deletedNode, index))
/*  908 */           doRedBlackDeleteFixup(replacement, index); 
/*  913 */       } else if (deletedNode.getParent(index) == null) {
/*  916 */         this.rootNode[index] = null;
/*      */       } else {
/*  920 */         if (isBlack(deletedNode, index))
/*  921 */           doRedBlackDeleteFixup(deletedNode, index); 
/*  924 */         if (deletedNode.getParent(index) != null) {
/*  925 */           if (deletedNode == deletedNode.getParent(index).getLeft(index)) {
/*  928 */             deletedNode.getParent(index).setLeft(null, index);
/*      */           } else {
/*  930 */             deletedNode.getParent(index).setRight(null, index);
/*      */           } 
/*  934 */           deletedNode.setParent(null, index);
/*      */         } 
/*      */       } 
/*      */     } 
/*  940 */     shrink();
/*      */   }
/*      */   
/*      */   private void doRedBlackDeleteFixup(Node replacementNode, int index) {
/*  955 */     Node currentNode = replacementNode;
/*  958 */     while (currentNode != this.rootNode[index] && isBlack(currentNode, index)) {
/*  959 */       if (isLeftChild(currentNode, index)) {
/*  960 */         Node node = getRightChild(getParent(currentNode, index), index);
/*  963 */         if (isRed(node, index)) {
/*  964 */           makeBlack(node, index);
/*  965 */           makeRed(getParent(currentNode, index), index);
/*  966 */           rotateLeft(getParent(currentNode, index), index);
/*  968 */           node = getRightChild(getParent(currentNode, index), index);
/*      */         } 
/*  971 */         if (isBlack(getLeftChild(node, index), index) && isBlack(getRightChild(node, index), index)) {
/*  974 */           makeRed(node, index);
/*  976 */           currentNode = getParent(currentNode, index);
/*      */           continue;
/*      */         } 
/*  978 */         if (isBlack(getRightChild(node, index), index)) {
/*  979 */           makeBlack(getLeftChild(node, index), index);
/*  980 */           makeRed(node, index);
/*  981 */           rotateRight(node, index);
/*  983 */           node = getRightChild(getParent(currentNode, index), index);
/*      */         } 
/*  987 */         copyColor(getParent(currentNode, index), node, index);
/*  989 */         makeBlack(getParent(currentNode, index), index);
/*  990 */         makeBlack(getRightChild(node, index), index);
/*  991 */         rotateLeft(getParent(currentNode, index), index);
/*  993 */         currentNode = this.rootNode[index];
/*      */         continue;
/*      */       } 
/*  996 */       Node siblingNode = getLeftChild(getParent(currentNode, index), index);
/*  998 */       if (isRed(siblingNode, index)) {
/*  999 */         makeBlack(siblingNode, index);
/* 1000 */         makeRed(getParent(currentNode, index), index);
/* 1001 */         rotateRight(getParent(currentNode, index), index);
/* 1003 */         siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */       } 
/* 1006 */       if (isBlack(getRightChild(siblingNode, index), index) && isBlack(getLeftChild(siblingNode, index), index)) {
/* 1008 */         makeRed(siblingNode, index);
/* 1010 */         currentNode = getParent(currentNode, index);
/*      */         continue;
/*      */       } 
/* 1012 */       if (isBlack(getLeftChild(siblingNode, index), index)) {
/* 1013 */         makeBlack(getRightChild(siblingNode, index), index);
/* 1014 */         makeRed(siblingNode, index);
/* 1015 */         rotateLeft(siblingNode, index);
/* 1017 */         siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */       } 
/* 1021 */       copyColor(getParent(currentNode, index), siblingNode, index);
/* 1023 */       makeBlack(getParent(currentNode, index), index);
/* 1024 */       makeBlack(getLeftChild(siblingNode, index), index);
/* 1025 */       rotateRight(getParent(currentNode, index), index);
/* 1027 */       currentNode = this.rootNode[index];
/*      */     } 
/* 1032 */     makeBlack(currentNode, index);
/*      */   }
/*      */   
/*      */   private void swapPosition(Node x, Node y, int index) {
/* 1047 */     Node xFormerParent = x.getParent(index);
/* 1048 */     Node xFormerLeftChild = x.getLeft(index);
/* 1049 */     Node xFormerRightChild = x.getRight(index);
/* 1050 */     Node yFormerParent = y.getParent(index);
/* 1051 */     Node yFormerLeftChild = y.getLeft(index);
/* 1052 */     Node yFormerRightChild = y.getRight(index);
/* 1053 */     boolean xWasLeftChild = (x.getParent(index) != null && x == x.getParent(index).getLeft(index));
/* 1056 */     boolean yWasLeftChild = (y.getParent(index) != null && y == y.getParent(index).getLeft(index));
/* 1061 */     if (x == yFormerParent) {
/* 1062 */       x.setParent(y, index);
/* 1064 */       if (yWasLeftChild) {
/* 1065 */         y.setLeft(x, index);
/* 1066 */         y.setRight(xFormerRightChild, index);
/*      */       } else {
/* 1068 */         y.setRight(x, index);
/* 1069 */         y.setLeft(xFormerLeftChild, index);
/*      */       } 
/*      */     } else {
/* 1072 */       x.setParent(yFormerParent, index);
/* 1074 */       if (yFormerParent != null)
/* 1075 */         if (yWasLeftChild) {
/* 1076 */           yFormerParent.setLeft(x, index);
/*      */         } else {
/* 1078 */           yFormerParent.setRight(x, index);
/*      */         }  
/* 1082 */       y.setLeft(xFormerLeftChild, index);
/* 1083 */       y.setRight(xFormerRightChild, index);
/*      */     } 
/* 1086 */     if (y == xFormerParent) {
/* 1087 */       y.setParent(x, index);
/* 1089 */       if (xWasLeftChild) {
/* 1090 */         x.setLeft(y, index);
/* 1091 */         x.setRight(yFormerRightChild, index);
/*      */       } else {
/* 1093 */         x.setRight(y, index);
/* 1094 */         x.setLeft(yFormerLeftChild, index);
/*      */       } 
/*      */     } else {
/* 1097 */       y.setParent(xFormerParent, index);
/* 1099 */       if (xFormerParent != null)
/* 1100 */         if (xWasLeftChild) {
/* 1101 */           xFormerParent.setLeft(y, index);
/*      */         } else {
/* 1103 */           xFormerParent.setRight(y, index);
/*      */         }  
/* 1107 */       x.setLeft(yFormerLeftChild, index);
/* 1108 */       x.setRight(yFormerRightChild, index);
/*      */     } 
/* 1112 */     if (x.getLeft(index) != null)
/* 1113 */       x.getLeft(index).setParent(x, index); 
/* 1116 */     if (x.getRight(index) != null)
/* 1117 */       x.getRight(index).setParent(x, index); 
/* 1120 */     if (y.getLeft(index) != null)
/* 1121 */       y.getLeft(index).setParent(y, index); 
/* 1124 */     if (y.getRight(index) != null)
/* 1125 */       y.getRight(index).setParent(y, index); 
/* 1128 */     x.swapColors(y, index);
/* 1131 */     if (this.rootNode[index] == x) {
/* 1132 */       this.rootNode[index] = y;
/* 1133 */     } else if (this.rootNode[index] == y) {
/* 1134 */       this.rootNode[index] = x;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void checkNonNullComparable(Object o, int index) {
/* 1152 */     if (o == null)
/* 1153 */       throw new NullPointerException(dataName[index] + " cannot be null"); 
/* 1157 */     if (!(o instanceof Comparable))
/* 1158 */       throw new ClassCastException(dataName[index] + " must be Comparable"); 
/*      */   }
/*      */   
/*      */   private static void checkKey(Object key) {
/* 1172 */     checkNonNullComparable(key, 0);
/*      */   }
/*      */   
/*      */   private static void checkValue(Object value) {
/* 1184 */     checkNonNullComparable(value, 1);
/*      */   }
/*      */   
/*      */   private static void checkKeyAndValue(Object key, Object value) {
/* 1199 */     checkKey(key);
/* 1200 */     checkValue(value);
/*      */   }
/*      */   
/*      */   private void modify() {
/* 1209 */     this.modifications++;
/*      */   }
/*      */   
/*      */   private void grow() {
/* 1217 */     modify();
/* 1219 */     this.nodeCount++;
/*      */   }
/*      */   
/*      */   private void shrink() {
/* 1227 */     modify();
/* 1229 */     this.nodeCount--;
/*      */   }
/*      */   
/*      */   private void insertValue(Node newNode) throws IllegalArgumentException {
/* 1243 */     Node node = this.rootNode[1];
/*      */     while (true) {
/* 1246 */       int cmp = compare(newNode.getData(1), node.getData(1));
/* 1248 */       if (cmp == 0)
/* 1249 */         throw new IllegalArgumentException("Cannot store a duplicate value (\"" + newNode.getData(1) + "\") in this Map"); 
/* 1252 */       if (cmp < 0) {
/* 1253 */         if (node.getLeft(1) != null) {
/* 1254 */           node = node.getLeft(1);
/*      */           continue;
/*      */         } 
/* 1256 */         node.setLeft(newNode, 1);
/* 1257 */         newNode.setParent(node, 1);
/* 1258 */         doRedBlackInsert(newNode, 1);
/*      */         break;
/*      */       } 
/* 1263 */       if (node.getRight(1) != null) {
/* 1264 */         node = node.getRight(1);
/*      */         continue;
/*      */       } 
/* 1266 */       node.setRight(newNode, 1);
/* 1267 */       newNode.setParent(node, 1);
/* 1268 */       doRedBlackInsert(newNode, 1);
/*      */       break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int size() {
/* 1286 */     return this.nodeCount;
/*      */   }
/*      */   
/*      */   public boolean containsKey(Object key) throws ClassCastException, NullPointerException {
/* 1305 */     checkKey(key);
/* 1307 */     return (lookup((Comparable)key, 0) != null);
/*      */   }
/*      */   
/*      */   public boolean containsValue(Object value) {
/* 1321 */     checkValue(value);
/* 1323 */     return (lookup((Comparable)value, 1) != null);
/*      */   }
/*      */   
/*      */   public Object get(Object key) throws ClassCastException, NullPointerException {
/* 1341 */     return doGet((Comparable)key, 0);
/*      */   }
/*      */   
/*      */   public Object put(Object key, Object value) throws ClassCastException, NullPointerException, IllegalArgumentException {
/* 1368 */     checkKeyAndValue(key, value);
/* 1370 */     Node node = this.rootNode[0];
/* 1372 */     if (node == null) {
/* 1373 */       Node root = new Node((Comparable)key, (Comparable)value);
/* 1375 */       this.rootNode[0] = root;
/* 1376 */       this.rootNode[1] = root;
/* 1378 */       grow();
/*      */     } else {
/*      */       while (true) {
/* 1381 */         int cmp = compare((Comparable)key, node.getData(0));
/* 1383 */         if (cmp == 0)
/* 1384 */           throw new IllegalArgumentException("Cannot store a duplicate key (\"" + key + "\") in this Map"); 
/* 1387 */         if (cmp < 0) {
/* 1388 */           if (node.getLeft(0) != null) {
/* 1389 */             node = node.getLeft(0);
/*      */             continue;
/*      */           } 
/* 1391 */           Node node1 = new Node((Comparable)key, (Comparable)value);
/* 1394 */           insertValue(node1);
/* 1395 */           node.setLeft(node1, 0);
/* 1396 */           node1.setParent(node, 0);
/* 1397 */           doRedBlackInsert(node1, 0);
/* 1398 */           grow();
/*      */           break;
/*      */         } 
/* 1403 */         if (node.getRight(0) != null) {
/* 1404 */           node = node.getRight(0);
/*      */           continue;
/*      */         } 
/* 1406 */         Node newNode = new Node((Comparable)key, (Comparable)value);
/* 1409 */         insertValue(newNode);
/* 1410 */         node.setRight(newNode, 0);
/* 1411 */         newNode.setParent(node, 0);
/* 1412 */         doRedBlackInsert(newNode, 0);
/* 1413 */         grow();
/*      */         break;
/*      */       } 
/*      */     } 
/* 1421 */     return null;
/*      */   }
/*      */   
/*      */   public Object remove(Object key) {
/* 1433 */     return doRemove((Comparable)key, 0);
/*      */   }
/*      */   
/*      */   public void clear() {
/* 1441 */     modify();
/* 1443 */     this.nodeCount = 0;
/* 1444 */     this.rootNode[0] = null;
/* 1445 */     this.rootNode[1] = null;
/*      */   }
/*      */   
/*      */   public Set keySet() {
/* 1462 */     if (this.setOfKeys[0] == null)
/* 1463 */       this.setOfKeys[0] = new AbstractSet(this) {
/*      */           private final DoubleOrderedMap this$0;
/*      */           
/*      */           public Iterator iterator() {
/* 1467 */             return new DoubleOrderedMap.DoubleOrderedMapIterator(this, 0) {
/*      */                 private final DoubleOrderedMap.null this$1;
/*      */                 
/*      */                 protected Object doGetNext() {
/* 1470 */                   return this.lastReturnedNode.getData(0);
/*      */                 }
/*      */               };
/*      */           }
/*      */           
/*      */           public int size() {
/* 1476 */             return this.this$0.size();
/*      */           }
/*      */           
/*      */           public boolean contains(Object o) {
/* 1480 */             return this.this$0.containsKey(o);
/*      */           }
/*      */           
/*      */           public boolean remove(Object o) {
/* 1485 */             int oldNodeCount = this.this$0.nodeCount;
/* 1487 */             this.this$0.remove(o);
/* 1489 */             return (this.this$0.nodeCount != oldNodeCount);
/*      */           }
/*      */           
/*      */           public void clear() {
/* 1493 */             this.this$0.clear();
/*      */           }
/*      */         }; 
/* 1498 */     return this.setOfKeys[0];
/*      */   }
/*      */   
/*      */   public Collection values() {
/* 1516 */     if (this.collectionOfValues[0] == null)
/* 1517 */       this.collectionOfValues[0] = new AbstractCollection(this) {
/*      */           private final DoubleOrderedMap this$0;
/*      */           
/*      */           public Iterator iterator() {
/* 1521 */             return new DoubleOrderedMap.DoubleOrderedMapIterator(this, 0) {
/*      */                 private final DoubleOrderedMap.null this$1;
/*      */                 
/*      */                 protected Object doGetNext() {
/* 1524 */                   return this.lastReturnedNode.getData(1);
/*      */                 }
/*      */               };
/*      */           }
/*      */           
/*      */           public int size() {
/* 1530 */             return this.this$0.size();
/*      */           }
/*      */           
/*      */           public boolean contains(Object o) {
/* 1534 */             return this.this$0.containsValue(o);
/*      */           }
/*      */           
/*      */           public boolean remove(Object o) {
/* 1539 */             int oldNodeCount = this.this$0.nodeCount;
/* 1541 */             this.this$0.removeValue(o);
/* 1543 */             return (this.this$0.nodeCount != oldNodeCount);
/*      */           }
/*      */           
/*      */           public boolean removeAll(Collection c) {
/* 1548 */             boolean modified = false;
/* 1549 */             Iterator iter = c.iterator();
/* 1551 */             while (iter.hasNext()) {
/* 1552 */               if (this.this$0.removeValue(iter.next()) != null)
/* 1553 */                 modified = true; 
/*      */             } 
/* 1557 */             return modified;
/*      */           }
/*      */           
/*      */           public void clear() {
/* 1561 */             this.this$0.clear();
/*      */           }
/*      */         }; 
/* 1566 */     return this.collectionOfValues[0];
/*      */   }
/*      */   
/*      */   public Set entrySet() {
/* 1587 */     if (this.setOfEntries[0] == null)
/* 1588 */       this.setOfEntries[0] = new AbstractSet(this) {
/*      */           private final DoubleOrderedMap this$0;
/*      */           
/*      */           public Iterator iterator() {
/* 1592 */             return new DoubleOrderedMap.DoubleOrderedMapIterator(this, 0) {
/*      */                 private final DoubleOrderedMap.null this$1;
/*      */                 
/*      */                 protected Object doGetNext() {
/* 1595 */                   return this.lastReturnedNode;
/*      */                 }
/*      */               };
/*      */           }
/*      */           
/*      */           public boolean contains(Object o) {
/* 1602 */             if (!(o instanceof Map.Entry))
/* 1603 */               return false; 
/* 1606 */             Map.Entry entry = (Map.Entry)o;
/* 1607 */             Object value = entry.getValue();
/* 1608 */             DoubleOrderedMap.Node node = this.this$0.lookup((Comparable)entry.getKey(), 0);
/* 1611 */             return (node != null && node.getData(1).equals(value));
/*      */           }
/*      */           
/*      */           public boolean remove(Object o) {
/* 1617 */             if (!(o instanceof Map.Entry))
/* 1618 */               return false; 
/* 1621 */             Map.Entry entry = (Map.Entry)o;
/* 1622 */             Object value = entry.getValue();
/* 1623 */             DoubleOrderedMap.Node node = this.this$0.lookup((Comparable)entry.getKey(), 0);
/* 1626 */             if (node != null && node.getData(1).equals(value)) {
/* 1627 */               this.this$0.doRedBlackDelete(node);
/* 1629 */               return true;
/*      */             } 
/* 1632 */             return false;
/*      */           }
/*      */           
/*      */           public int size() {
/* 1636 */             return this.this$0.size();
/*      */           }
/*      */           
/*      */           public void clear() {
/* 1640 */             this.this$0.clear();
/*      */           }
/*      */         }; 
/* 1645 */     return this.setOfEntries[0];
/*      */   }
/*      */   
/*      */   public DoubleOrderedMap() {}
/*      */   
/*      */   private abstract class DoubleOrderedMapIterator implements Iterator {
/*      */     private int expectedModifications;
/*      */     
/*      */     protected DoubleOrderedMap.Node lastReturnedNode;
/*      */     
/*      */     private DoubleOrderedMap.Node nextNode;
/*      */     
/*      */     private int iteratorType;
/*      */     
/*      */     private final DoubleOrderedMap this$0;
/*      */     
/*      */     DoubleOrderedMapIterator(DoubleOrderedMap this$0, int type) {
/* 1661 */       this.this$0 = this$0;
/* 1663 */       this.iteratorType = type;
/* 1664 */       this.expectedModifications = this$0.modifications;
/* 1665 */       this.lastReturnedNode = null;
/* 1666 */       this.nextNode = DoubleOrderedMap.leastNode(this$0.rootNode[this.iteratorType], this.iteratorType);
/*      */     }
/*      */     
/*      */     protected abstract Object doGetNext();
/*      */     
/*      */     public final boolean hasNext() {
/* 1682 */       return (this.nextNode != null);
/*      */     }
/*      */     
/*      */     public final Object next() throws NoSuchElementException, ConcurrentModificationException {
/* 1700 */       if (this.nextNode == null)
/* 1701 */         throw new NoSuchElementException(); 
/* 1704 */       if (this.this$0.modifications != this.expectedModifications)
/* 1705 */         throw new ConcurrentModificationException(); 
/* 1708 */       this.lastReturnedNode = this.nextNode;
/* 1709 */       this.nextNode = this.this$0.nextGreater(this.nextNode, this.iteratorType);
/* 1711 */       return doGetNext();
/*      */     }
/*      */     
/*      */     public final void remove() throws IllegalStateException, ConcurrentModificationException {
/* 1737 */       if (this.lastReturnedNode == null)
/* 1738 */         throw new IllegalStateException(); 
/* 1741 */       if (this.this$0.modifications != this.expectedModifications)
/* 1742 */         throw new ConcurrentModificationException(); 
/* 1745 */       this.this$0.doRedBlackDelete(this.lastReturnedNode);
/* 1747 */       this.expectedModifications++;
/* 1749 */       this.lastReturnedNode = null;
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class Node implements Map.Entry, KeyValue {
/*      */     private Comparable[] data;
/*      */     
/*      */     private Node[] leftNode;
/*      */     
/*      */     private Node[] rightNode;
/*      */     
/*      */     private Node[] parentNode;
/*      */     
/*      */     private boolean[] blackColor;
/*      */     
/*      */     private int hashcodeValue;
/*      */     
/*      */     private boolean calculatedHashCode;
/*      */     
/*      */     Node(Comparable key, Comparable value) {
/* 1775 */       this.data = new Comparable[] { key, value };
/* 1776 */       this.leftNode = new Node[] { null, null };
/* 1777 */       this.rightNode = new Node[] { null, null };
/* 1778 */       this.parentNode = new Node[] { null, null };
/* 1779 */       this.blackColor = new boolean[] { true, true };
/* 1780 */       this.calculatedHashCode = false;
/*      */     }
/*      */     
/*      */     private Comparable getData(int index) {
/* 1791 */       return this.data[index];
/*      */     }
/*      */     
/*      */     private void setLeft(Node node, int index) {
/* 1801 */       this.leftNode[index] = node;
/*      */     }
/*      */     
/*      */     private Node getLeft(int index) {
/* 1812 */       return this.leftNode[index];
/*      */     }
/*      */     
/*      */     private void setRight(Node node, int index) {
/* 1822 */       this.rightNode[index] = node;
/*      */     }
/*      */     
/*      */     private Node getRight(int index) {
/* 1833 */       return this.rightNode[index];
/*      */     }
/*      */     
/*      */     private void setParent(Node node, int index) {
/* 1843 */       this.parentNode[index] = node;
/*      */     }
/*      */     
/*      */     private Node getParent(int index) {
/* 1854 */       return this.parentNode[index];
/*      */     }
/*      */     
/*      */     private void swapColors(Node node, int index) {
/* 1866 */       this.blackColor[index] = this.blackColor[index] ^ node.blackColor[index];
/* 1867 */       node.blackColor[index] = node.blackColor[index] ^ this.blackColor[index];
/* 1868 */       this.blackColor[index] = this.blackColor[index] ^ node.blackColor[index];
/*      */     }
/*      */     
/*      */     private boolean isBlack(int index) {
/* 1879 */       return this.blackColor[index];
/*      */     }
/*      */     
/*      */     private boolean isRed(int index) {
/* 1890 */       return !this.blackColor[index];
/*      */     }
/*      */     
/*      */     private void setBlack(int index) {
/* 1899 */       this.blackColor[index] = true;
/*      */     }
/*      */     
/*      */     private void setRed(int index) {
/* 1908 */       this.blackColor[index] = false;
/*      */     }
/*      */     
/*      */     private void copyColor(Node node, int index) {
/* 1918 */       this.blackColor[index] = node.blackColor[index];
/*      */     }
/*      */     
/*      */     public Object getKey() {
/* 1927 */       return this.data[0];
/*      */     }
/*      */     
/*      */     public Object getValue() {
/* 1934 */       return this.data[1];
/*      */     }
/*      */     
/*      */     public Object setValue(Object ignored) throws UnsupportedOperationException {
/* 1949 */       throw new UnsupportedOperationException("Map.Entry.setValue is not supported");
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/* 1965 */       if (this == o)
/* 1966 */         return true; 
/* 1969 */       if (!(o instanceof Map.Entry))
/* 1970 */         return false; 
/* 1973 */       Map.Entry e = (Map.Entry)o;
/* 1975 */       return (this.data[0].equals(e.getKey()) && this.data[1].equals(e.getValue()));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1984 */       if (!this.calculatedHashCode) {
/* 1985 */         this.hashcodeValue = this.data[0].hashCode() ^ this.data[1].hashCode();
/* 1987 */         this.calculatedHashCode = true;
/*      */       } 
/* 1990 */       return this.hashcodeValue;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\DoubleOrderedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */