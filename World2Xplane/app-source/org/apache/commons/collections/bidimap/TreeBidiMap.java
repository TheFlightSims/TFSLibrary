/*      */ package org.apache.commons.collections.bidimap;
/*      */ 
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections.BidiMap;
/*      */ import org.apache.commons.collections.KeyValue;
/*      */ import org.apache.commons.collections.MapIterator;
/*      */ import org.apache.commons.collections.OrderedBidiMap;
/*      */ import org.apache.commons.collections.OrderedIterator;
/*      */ import org.apache.commons.collections.OrderedMapIterator;
/*      */ import org.apache.commons.collections.iterators.EmptyOrderedMapIterator;
/*      */ import org.apache.commons.collections.keyvalue.UnmodifiableMapEntry;
/*      */ 
/*      */ public class TreeBidiMap implements OrderedBidiMap {
/*      */   private static final int KEY = 0;
/*      */   
/*      */   private static final int VALUE = 1;
/*      */   
/*      */   private static final int MAPENTRY = 2;
/*      */   
/*      */   private static final int INVERSEMAPENTRY = 3;
/*      */   
/*      */   private static final int SUM_OF_INDICES = 1;
/*      */   
/*      */   private static final int FIRST_INDEX = 0;
/*      */   
/*      */   private static final int NUMBER_OF_INDICES = 2;
/*      */   
/*   85 */   private static final String[] dataName = new String[] { "key", "value" };
/*      */   
/*   87 */   private Node[] rootNode = new Node[2];
/*      */   
/*   88 */   private int nodeCount = 0;
/*      */   
/*   89 */   private int modifications = 0;
/*      */   
/*      */   private Set keySet;
/*      */   
/*      */   private Set valuesSet;
/*      */   
/*      */   private Set entrySet;
/*      */   
/*   93 */   private Inverse inverse = null;
/*      */   
/*      */   public TreeBidiMap(Map map) {
/*  113 */     putAll(map);
/*      */   }
/*      */   
/*      */   public int size() {
/*  123 */     return this.nodeCount;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  132 */     return (this.nodeCount == 0);
/*      */   }
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  146 */     checkKey(key);
/*  147 */     return (lookup((Comparable)key, 0) != null);
/*      */   }
/*      */   
/*      */   public boolean containsValue(Object value) {
/*  161 */     checkValue(value);
/*  162 */     return (lookup((Comparable)value, 1) != null);
/*      */   }
/*      */   
/*      */   public Object get(Object key) {
/*  178 */     return doGet((Comparable)key, 0);
/*      */   }
/*      */   
/*      */   public Object put(Object key, Object value) {
/*  206 */     return doPut((Comparable)key, (Comparable)value, 0);
/*      */   }
/*      */   
/*      */   public void putAll(Map map) {
/*  217 */     Iterator it = map.entrySet().iterator();
/*  218 */     while (it.hasNext()) {
/*  219 */       Map.Entry entry = it.next();
/*  220 */       put(entry.getKey(), entry.getValue());
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object remove(Object key) {
/*  236 */     return doRemove((Comparable)key, 0);
/*      */   }
/*      */   
/*      */   public void clear() {
/*  243 */     modify();
/*  245 */     this.nodeCount = 0;
/*  246 */     this.rootNode[0] = null;
/*  247 */     this.rootNode[1] = null;
/*      */   }
/*      */   
/*      */   public Object getKey(Object value) {
/*  264 */     return doGet((Comparable)value, 1);
/*      */   }
/*      */   
/*      */   public Object removeValue(Object value) {
/*  279 */     return doRemove((Comparable)value, 1);
/*      */   }
/*      */   
/*      */   public Object firstKey() {
/*  290 */     if (this.nodeCount == 0)
/*  291 */       throw new NoSuchElementException("Map is empty"); 
/*  293 */     return leastNode(this.rootNode[0], 0).getKey();
/*      */   }
/*      */   
/*      */   public Object lastKey() {
/*  303 */     if (this.nodeCount == 0)
/*  304 */       throw new NoSuchElementException("Map is empty"); 
/*  306 */     return greatestNode(this.rootNode[0], 0).getKey();
/*      */   }
/*      */   
/*      */   public Object nextKey(Object key) {
/*  318 */     checkKey(key);
/*  319 */     Node node = nextGreater(lookup((Comparable)key, 0), 0);
/*  320 */     return (node == null) ? null : node.getKey();
/*      */   }
/*      */   
/*      */   public Object previousKey(Object key) {
/*  332 */     checkKey(key);
/*  333 */     Node node = nextSmaller(lookup((Comparable)key, 0), 0);
/*  334 */     return (node == null) ? null : node.getKey();
/*      */   }
/*      */   
/*      */   public Set keySet() {
/*  351 */     if (this.keySet == null)
/*  352 */       this.keySet = new View(this, 0, 0); 
/*  354 */     return this.keySet;
/*      */   }
/*      */   
/*      */   public Collection values() {
/*  372 */     if (this.valuesSet == null)
/*  373 */       this.valuesSet = new View(this, 0, 1); 
/*  375 */     return this.valuesSet;
/*      */   }
/*      */   
/*      */   public Set entrySet() {
/*  394 */     if (this.entrySet == null)
/*  395 */       return new EntryView(this, 0, 2); 
/*  397 */     return this.entrySet;
/*      */   }
/*      */   
/*      */   public MapIterator mapIterator() {
/*  409 */     if (isEmpty())
/*  410 */       return (MapIterator)EmptyOrderedMapIterator.INSTANCE; 
/*  412 */     return (MapIterator)new ViewMapIterator(this, 0);
/*      */   }
/*      */   
/*      */   public OrderedMapIterator orderedMapIterator() {
/*  423 */     if (isEmpty())
/*  424 */       return EmptyOrderedMapIterator.INSTANCE; 
/*  426 */     return new ViewMapIterator(this, 0);
/*      */   }
/*      */   
/*      */   public BidiMap inverseBidiMap() {
/*  436 */     return (BidiMap)inverseOrderedBidiMap();
/*      */   }
/*      */   
/*      */   public OrderedBidiMap inverseOrderedBidiMap() {
/*  445 */     if (this.inverse == null)
/*  446 */       this.inverse = new Inverse(this); 
/*  448 */     return this.inverse;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/*  459 */     return doEquals(obj, 0);
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  468 */     return doHashCode(0);
/*      */   }
/*      */   
/*      */   public String toString() {
/*  477 */     return doToString(0);
/*      */   }
/*      */   
/*      */   private Object doGet(Comparable obj, int index) {
/*  491 */     checkNonNullComparable(obj, index);
/*  492 */     Node node = lookup(obj, index);
/*  493 */     return (node == null) ? null : node.getData(oppositeIndex(index));
/*      */   }
/*      */   
/*      */   private Object doPut(Comparable key, Comparable value, int index) {
/*  505 */     checkKeyAndValue(key, value);
/*  508 */     Object prev = (index == 0) ? doGet(key, 0) : doGet(value, 1);
/*  509 */     doRemove(key, 0);
/*  510 */     doRemove(value, 1);
/*  512 */     Node node = this.rootNode[0];
/*  513 */     if (node == null) {
/*  515 */       Node root = new Node(key, value);
/*  516 */       this.rootNode[0] = root;
/*  517 */       this.rootNode[1] = root;
/*  518 */       grow();
/*      */     } else {
/*      */       while (true) {
/*  523 */         int cmp = compare(key, node.getData(0));
/*  525 */         if (cmp == 0)
/*  527 */           throw new IllegalArgumentException("Cannot store a duplicate key (\"" + key + "\") in this Map"); 
/*  528 */         if (cmp < 0) {
/*  529 */           if (node.getLeft(0) != null) {
/*  530 */             node = node.getLeft(0);
/*      */             continue;
/*      */           } 
/*  532 */           Node node1 = new Node(key, value);
/*  534 */           insertValue(node1);
/*  535 */           node.setLeft(node1, 0);
/*  536 */           node1.setParent(node, 0);
/*  537 */           doRedBlackInsert(node1, 0);
/*  538 */           grow();
/*      */           break;
/*      */         } 
/*  543 */         if (node.getRight(0) != null) {
/*  544 */           node = node.getRight(0);
/*      */           continue;
/*      */         } 
/*  546 */         Node newNode = new Node(key, value);
/*  548 */         insertValue(newNode);
/*  549 */         node.setRight(newNode, 0);
/*  550 */         newNode.setParent(node, 0);
/*  551 */         doRedBlackInsert(newNode, 0);
/*  552 */         grow();
/*      */         break;
/*      */       } 
/*      */     } 
/*  559 */     return prev;
/*      */   }
/*      */   
/*      */   private Object doRemove(Comparable o, int index) {
/*  573 */     Node node = lookup(o, index);
/*  574 */     Object rval = null;
/*  575 */     if (node != null) {
/*  576 */       rval = node.getData(oppositeIndex(index));
/*  577 */       doRedBlackDelete(node);
/*      */     } 
/*  579 */     return rval;
/*      */   }
/*      */   
/*      */   private Node lookup(Comparable data, int index) {
/*  591 */     Node rval = null;
/*  592 */     Node node = this.rootNode[index];
/*  594 */     while (node != null) {
/*  595 */       int cmp = compare(data, node.getData(index));
/*  596 */       if (cmp == 0) {
/*  597 */         rval = node;
/*      */         break;
/*      */       } 
/*  600 */       node = (cmp < 0) ? node.getLeft(index) : node.getRight(index);
/*      */     } 
/*  604 */     return rval;
/*      */   }
/*      */   
/*      */   private Node nextGreater(Node node, int index) {
/*  615 */     Node rval = null;
/*  616 */     if (node == null) {
/*  617 */       rval = null;
/*  618 */     } else if (node.getRight(index) != null) {
/*  621 */       rval = leastNode(node.getRight(index), index);
/*      */     } else {
/*  629 */       Node parent = node.getParent(index);
/*  630 */       Node child = node;
/*  632 */       while (parent != null && child == parent.getRight(index)) {
/*  633 */         child = parent;
/*  634 */         parent = parent.getParent(index);
/*      */       } 
/*  636 */       rval = parent;
/*      */     } 
/*  638 */     return rval;
/*      */   }
/*      */   
/*      */   private Node nextSmaller(Node node, int index) {
/*  649 */     Node rval = null;
/*  650 */     if (node == null) {
/*  651 */       rval = null;
/*  652 */     } else if (node.getLeft(index) != null) {
/*  655 */       rval = greatestNode(node.getLeft(index), index);
/*      */     } else {
/*  663 */       Node parent = node.getParent(index);
/*  664 */       Node child = node;
/*  666 */       while (parent != null && child == parent.getLeft(index)) {
/*  667 */         child = parent;
/*  668 */         parent = parent.getParent(index);
/*      */       } 
/*  670 */       rval = parent;
/*      */     } 
/*  672 */     return rval;
/*      */   }
/*      */   
/*      */   private static int oppositeIndex(int index) {
/*  686 */     return 1 - index;
/*      */   }
/*      */   
/*      */   private static int compare(Comparable o1, Comparable o2) {
/*  699 */     return o1.compareTo(o2);
/*      */   }
/*      */   
/*      */   private static Node leastNode(Node node, int index) {
/*  711 */     Node rval = node;
/*  712 */     if (rval != null)
/*  713 */       while (rval.getLeft(index) != null)
/*  714 */         rval = rval.getLeft(index);  
/*  717 */     return rval;
/*      */   }
/*      */   
/*      */   private static Node greatestNode(Node node, int index) {
/*  728 */     Node rval = node;
/*  729 */     if (rval != null)
/*  730 */       while (rval.getRight(index) != null)
/*  731 */         rval = rval.getRight(index);  
/*  734 */     return rval;
/*      */   }
/*      */   
/*      */   private static void copyColor(Node from, Node to, int index) {
/*  746 */     if (to != null)
/*  747 */       if (from == null) {
/*  749 */         to.setBlack(index);
/*      */       } else {
/*  751 */         to.copyColor(from, index);
/*      */       }  
/*      */   }
/*      */   
/*      */   private static boolean isRed(Node node, int index) {
/*  764 */     return (node == null) ? false : node.isRed(index);
/*      */   }
/*      */   
/*      */   private static boolean isBlack(Node node, int index) {
/*  775 */     return (node == null) ? true : node.isBlack(index);
/*      */   }
/*      */   
/*      */   private static void makeRed(Node node, int index) {
/*  785 */     if (node != null)
/*  786 */       node.setRed(index); 
/*      */   }
/*      */   
/*      */   private static void makeBlack(Node node, int index) {
/*  797 */     if (node != null)
/*  798 */       node.setBlack(index); 
/*      */   }
/*      */   
/*      */   private static Node getGrandParent(Node node, int index) {
/*  810 */     return getParent(getParent(node, index), index);
/*      */   }
/*      */   
/*      */   private static Node getParent(Node node, int index) {
/*  821 */     return (node == null) ? null : node.getParent(index);
/*      */   }
/*      */   
/*      */   private static Node getRightChild(Node node, int index) {
/*  832 */     return (node == null) ? null : node.getRight(index);
/*      */   }
/*      */   
/*      */   private static Node getLeftChild(Node node, int index) {
/*  843 */     return (node == null) ? null : node.getLeft(index);
/*      */   }
/*      */   
/*      */   private static boolean isLeftChild(Node node, int index) {
/*  858 */     return (node == null) ? true : ((node.getParent(index) == null) ? false : ((node == node.getParent(index).getLeft(index))));
/*      */   }
/*      */   
/*      */   private static boolean isRightChild(Node node, int index) {
/*  876 */     return (node == null) ? true : ((node.getParent(index) == null) ? false : ((node == node.getParent(index).getRight(index))));
/*      */   }
/*      */   
/*      */   private void rotateLeft(Node node, int index) {
/*  889 */     Node rightChild = node.getRight(index);
/*  890 */     node.setRight(rightChild.getLeft(index), index);
/*  892 */     if (rightChild.getLeft(index) != null)
/*  893 */       rightChild.getLeft(index).setParent(node, index); 
/*  895 */     rightChild.setParent(node.getParent(index), index);
/*  897 */     if (node.getParent(index) == null) {
/*  899 */       this.rootNode[index] = rightChild;
/*  900 */     } else if (node.getParent(index).getLeft(index) == node) {
/*  901 */       node.getParent(index).setLeft(rightChild, index);
/*      */     } else {
/*  903 */       node.getParent(index).setRight(rightChild, index);
/*      */     } 
/*  906 */     rightChild.setLeft(node, index);
/*  907 */     node.setParent(rightChild, index);
/*      */   }
/*      */   
/*      */   private void rotateRight(Node node, int index) {
/*  917 */     Node leftChild = node.getLeft(index);
/*  918 */     node.setLeft(leftChild.getRight(index), index);
/*  919 */     if (leftChild.getRight(index) != null)
/*  920 */       leftChild.getRight(index).setParent(node, index); 
/*  922 */     leftChild.setParent(node.getParent(index), index);
/*  924 */     if (node.getParent(index) == null) {
/*  926 */       this.rootNode[index] = leftChild;
/*  927 */     } else if (node.getParent(index).getRight(index) == node) {
/*  928 */       node.getParent(index).setRight(leftChild, index);
/*      */     } else {
/*  930 */       node.getParent(index).setLeft(leftChild, index);
/*      */     } 
/*  933 */     leftChild.setRight(node, index);
/*  934 */     node.setParent(leftChild, index);
/*      */   }
/*      */   
/*      */   private void doRedBlackInsert(Node insertedNode, int index) {
/*  945 */     Node currentNode = insertedNode;
/*  946 */     makeRed(currentNode, index);
/*  950 */     while (currentNode != null && currentNode != this.rootNode[index] && isRed(currentNode.getParent(index), index)) {
/*  951 */       if (isLeftChild(getParent(currentNode, index), index)) {
/*  952 */         Node node = getRightChild(getGrandParent(currentNode, index), index);
/*  954 */         if (isRed(node, index)) {
/*  955 */           makeBlack(getParent(currentNode, index), index);
/*  956 */           makeBlack(node, index);
/*  957 */           makeRed(getGrandParent(currentNode, index), index);
/*  959 */           currentNode = getGrandParent(currentNode, index);
/*      */           continue;
/*      */         } 
/*  961 */         if (isRightChild(currentNode, index)) {
/*  962 */           currentNode = getParent(currentNode, index);
/*  964 */           rotateLeft(currentNode, index);
/*      */         } 
/*  967 */         makeBlack(getParent(currentNode, index), index);
/*  968 */         makeRed(getGrandParent(currentNode, index), index);
/*  970 */         if (getGrandParent(currentNode, index) != null)
/*  971 */           rotateRight(getGrandParent(currentNode, index), index); 
/*      */         continue;
/*      */       } 
/*  977 */       Node y = getLeftChild(getGrandParent(currentNode, index), index);
/*  979 */       if (isRed(y, index)) {
/*  980 */         makeBlack(getParent(currentNode, index), index);
/*  981 */         makeBlack(y, index);
/*  982 */         makeRed(getGrandParent(currentNode, index), index);
/*  984 */         currentNode = getGrandParent(currentNode, index);
/*      */         continue;
/*      */       } 
/*  986 */       if (isLeftChild(currentNode, index)) {
/*  987 */         currentNode = getParent(currentNode, index);
/*  989 */         rotateRight(currentNode, index);
/*      */       } 
/*  992 */       makeBlack(getParent(currentNode, index), index);
/*  993 */       makeRed(getGrandParent(currentNode, index), index);
/*  995 */       if (getGrandParent(currentNode, index) != null)
/*  996 */         rotateLeft(getGrandParent(currentNode, index), index); 
/*      */     } 
/* 1002 */     makeBlack(this.rootNode[index], index);
/*      */   }
/*      */   
/*      */   private void doRedBlackDelete(Node deletedNode) {
/* 1012 */     for (int index = 0; index < 2; index++) {
/* 1015 */       if (deletedNode.getLeft(index) != null && deletedNode.getRight(index) != null)
/* 1016 */         swapPosition(nextGreater(deletedNode, index), deletedNode, index); 
/* 1019 */       Node replacement = (deletedNode.getLeft(index) != null) ? deletedNode.getLeft(index) : deletedNode.getRight(index);
/* 1022 */       if (replacement != null) {
/* 1023 */         replacement.setParent(deletedNode.getParent(index), index);
/* 1025 */         if (deletedNode.getParent(index) == null) {
/* 1026 */           this.rootNode[index] = replacement;
/* 1027 */         } else if (deletedNode == deletedNode.getParent(index).getLeft(index)) {
/* 1028 */           deletedNode.getParent(index).setLeft(replacement, index);
/*      */         } else {
/* 1030 */           deletedNode.getParent(index).setRight(replacement, index);
/*      */         } 
/* 1033 */         deletedNode.setLeft(null, index);
/* 1034 */         deletedNode.setRight(null, index);
/* 1035 */         deletedNode.setParent(null, index);
/* 1037 */         if (isBlack(deletedNode, index))
/* 1038 */           doRedBlackDeleteFixup(replacement, index); 
/* 1043 */       } else if (deletedNode.getParent(index) == null) {
/* 1046 */         this.rootNode[index] = null;
/*      */       } else {
/* 1050 */         if (isBlack(deletedNode, index))
/* 1051 */           doRedBlackDeleteFixup(deletedNode, index); 
/* 1054 */         if (deletedNode.getParent(index) != null) {
/* 1055 */           if (deletedNode == deletedNode.getParent(index).getLeft(index)) {
/* 1056 */             deletedNode.getParent(index).setLeft(null, index);
/*      */           } else {
/* 1058 */             deletedNode.getParent(index).setRight(null, index);
/*      */           } 
/* 1061 */           deletedNode.setParent(null, index);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1066 */     shrink();
/*      */   }
/*      */   
/*      */   private void doRedBlackDeleteFixup(Node replacementNode, int index) {
/* 1079 */     Node currentNode = replacementNode;
/* 1081 */     while (currentNode != this.rootNode[index] && isBlack(currentNode, index)) {
/* 1082 */       if (isLeftChild(currentNode, index)) {
/* 1083 */         Node node = getRightChild(getParent(currentNode, index), index);
/* 1085 */         if (isRed(node, index)) {
/* 1086 */           makeBlack(node, index);
/* 1087 */           makeRed(getParent(currentNode, index), index);
/* 1088 */           rotateLeft(getParent(currentNode, index), index);
/* 1090 */           node = getRightChild(getParent(currentNode, index), index);
/*      */         } 
/* 1093 */         if (isBlack(getLeftChild(node, index), index) && isBlack(getRightChild(node, index), index)) {
/* 1095 */           makeRed(node, index);
/* 1097 */           currentNode = getParent(currentNode, index);
/*      */           continue;
/*      */         } 
/* 1099 */         if (isBlack(getRightChild(node, index), index)) {
/* 1100 */           makeBlack(getLeftChild(node, index), index);
/* 1101 */           makeRed(node, index);
/* 1102 */           rotateRight(node, index);
/* 1104 */           node = getRightChild(getParent(currentNode, index), index);
/*      */         } 
/* 1107 */         copyColor(getParent(currentNode, index), node, index);
/* 1108 */         makeBlack(getParent(currentNode, index), index);
/* 1109 */         makeBlack(getRightChild(node, index), index);
/* 1110 */         rotateLeft(getParent(currentNode, index), index);
/* 1112 */         currentNode = this.rootNode[index];
/*      */         continue;
/*      */       } 
/* 1115 */       Node siblingNode = getLeftChild(getParent(currentNode, index), index);
/* 1117 */       if (isRed(siblingNode, index)) {
/* 1118 */         makeBlack(siblingNode, index);
/* 1119 */         makeRed(getParent(currentNode, index), index);
/* 1120 */         rotateRight(getParent(currentNode, index), index);
/* 1122 */         siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */       } 
/* 1125 */       if (isBlack(getRightChild(siblingNode, index), index) && isBlack(getLeftChild(siblingNode, index), index)) {
/* 1127 */         makeRed(siblingNode, index);
/* 1129 */         currentNode = getParent(currentNode, index);
/*      */         continue;
/*      */       } 
/* 1131 */       if (isBlack(getLeftChild(siblingNode, index), index)) {
/* 1132 */         makeBlack(getRightChild(siblingNode, index), index);
/* 1133 */         makeRed(siblingNode, index);
/* 1134 */         rotateLeft(siblingNode, index);
/* 1136 */         siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */       } 
/* 1139 */       copyColor(getParent(currentNode, index), siblingNode, index);
/* 1140 */       makeBlack(getParent(currentNode, index), index);
/* 1141 */       makeBlack(getLeftChild(siblingNode, index), index);
/* 1142 */       rotateRight(getParent(currentNode, index), index);
/* 1144 */       currentNode = this.rootNode[index];
/*      */     } 
/* 1149 */     makeBlack(currentNode, index);
/*      */   }
/*      */   
/*      */   private void swapPosition(Node x, Node y, int index) {
/* 1163 */     Node xFormerParent = x.getParent(index);
/* 1164 */     Node xFormerLeftChild = x.getLeft(index);
/* 1165 */     Node xFormerRightChild = x.getRight(index);
/* 1166 */     Node yFormerParent = y.getParent(index);
/* 1167 */     Node yFormerLeftChild = y.getLeft(index);
/* 1168 */     Node yFormerRightChild = y.getRight(index);
/* 1169 */     boolean xWasLeftChild = (x.getParent(index) != null && x == x.getParent(index).getLeft(index));
/* 1170 */     boolean yWasLeftChild = (y.getParent(index) != null && y == y.getParent(index).getLeft(index));
/* 1173 */     if (x == yFormerParent) {
/* 1174 */       x.setParent(y, index);
/* 1176 */       if (yWasLeftChild) {
/* 1177 */         y.setLeft(x, index);
/* 1178 */         y.setRight(xFormerRightChild, index);
/*      */       } else {
/* 1180 */         y.setRight(x, index);
/* 1181 */         y.setLeft(xFormerLeftChild, index);
/*      */       } 
/*      */     } else {
/* 1184 */       x.setParent(yFormerParent, index);
/* 1186 */       if (yFormerParent != null)
/* 1187 */         if (yWasLeftChild) {
/* 1188 */           yFormerParent.setLeft(x, index);
/*      */         } else {
/* 1190 */           yFormerParent.setRight(x, index);
/*      */         }  
/* 1194 */       y.setLeft(xFormerLeftChild, index);
/* 1195 */       y.setRight(xFormerRightChild, index);
/*      */     } 
/* 1198 */     if (y == xFormerParent) {
/* 1199 */       y.setParent(x, index);
/* 1201 */       if (xWasLeftChild) {
/* 1202 */         x.setLeft(y, index);
/* 1203 */         x.setRight(yFormerRightChild, index);
/*      */       } else {
/* 1205 */         x.setRight(y, index);
/* 1206 */         x.setLeft(yFormerLeftChild, index);
/*      */       } 
/*      */     } else {
/* 1209 */       y.setParent(xFormerParent, index);
/* 1211 */       if (xFormerParent != null)
/* 1212 */         if (xWasLeftChild) {
/* 1213 */           xFormerParent.setLeft(y, index);
/*      */         } else {
/* 1215 */           xFormerParent.setRight(y, index);
/*      */         }  
/* 1219 */       x.setLeft(yFormerLeftChild, index);
/* 1220 */       x.setRight(yFormerRightChild, index);
/*      */     } 
/* 1224 */     if (x.getLeft(index) != null)
/* 1225 */       x.getLeft(index).setParent(x, index); 
/* 1228 */     if (x.getRight(index) != null)
/* 1229 */       x.getRight(index).setParent(x, index); 
/* 1232 */     if (y.getLeft(index) != null)
/* 1233 */       y.getLeft(index).setParent(y, index); 
/* 1236 */     if (y.getRight(index) != null)
/* 1237 */       y.getRight(index).setParent(y, index); 
/* 1240 */     x.swapColors(y, index);
/* 1243 */     if (this.rootNode[index] == x) {
/* 1244 */       this.rootNode[index] = y;
/* 1245 */     } else if (this.rootNode[index] == y) {
/* 1246 */       this.rootNode[index] = x;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void checkNonNullComparable(Object o, int index) {
/* 1262 */     if (o == null)
/* 1263 */       throw new NullPointerException(dataName[index] + " cannot be null"); 
/* 1265 */     if (!(o instanceof Comparable))
/* 1266 */       throw new ClassCastException(dataName[index] + " must be Comparable"); 
/*      */   }
/*      */   
/*      */   private static void checkKey(Object key) {
/* 1279 */     checkNonNullComparable(key, 0);
/*      */   }
/*      */   
/*      */   private static void checkValue(Object value) {
/* 1291 */     checkNonNullComparable(value, 1);
/*      */   }
/*      */   
/*      */   private static void checkKeyAndValue(Object key, Object value) {
/* 1305 */     checkKey(key);
/* 1306 */     checkValue(value);
/*      */   }
/*      */   
/*      */   private void modify() {
/* 1315 */     this.modifications++;
/*      */   }
/*      */   
/*      */   private void grow() {
/* 1322 */     modify();
/* 1323 */     this.nodeCount++;
/*      */   }
/*      */   
/*      */   private void shrink() {
/* 1330 */     modify();
/* 1331 */     this.nodeCount--;
/*      */   }
/*      */   
/*      */   private void insertValue(Node newNode) throws IllegalArgumentException {
/* 1343 */     Node node = this.rootNode[1];
/*      */     while (true) {
/* 1346 */       int cmp = compare(newNode.getData(1), node.getData(1));
/* 1348 */       if (cmp == 0)
/* 1349 */         throw new IllegalArgumentException("Cannot store a duplicate value (\"" + newNode.getData(1) + "\") in this Map"); 
/* 1351 */       if (cmp < 0) {
/* 1352 */         if (node.getLeft(1) != null) {
/* 1353 */           node = node.getLeft(1);
/*      */           continue;
/*      */         } 
/* 1355 */         node.setLeft(newNode, 1);
/* 1356 */         newNode.setParent(node, 1);
/* 1357 */         doRedBlackInsert(newNode, 1);
/*      */         break;
/*      */       } 
/* 1362 */       if (node.getRight(1) != null) {
/* 1363 */         node = node.getRight(1);
/*      */         continue;
/*      */       } 
/* 1365 */       node.setRight(newNode, 1);
/* 1366 */       newNode.setParent(node, 1);
/* 1367 */       doRedBlackInsert(newNode, 1);
/*      */       break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean doEquals(Object obj, int type) {
/* 1384 */     if (obj == this)
/* 1385 */       return true; 
/* 1387 */     if (!(obj instanceof Map))
/* 1388 */       return false; 
/* 1390 */     Map other = (Map)obj;
/* 1391 */     if (other.size() != size())
/* 1392 */       return false; 
/* 1395 */     if (this.nodeCount > 0)
/*      */       try {
/* 1397 */         for (ViewMapIterator viewMapIterator = new ViewMapIterator(this, type); viewMapIterator.hasNext(); ) {
/* 1398 */           Object key = viewMapIterator.next();
/* 1399 */           Object value = viewMapIterator.getValue();
/* 1400 */           if (!value.equals(other.get(key)))
/* 1401 */             return false; 
/*      */         } 
/* 1404 */       } catch (ClassCastException ex) {
/* 1405 */         return false;
/* 1406 */       } catch (NullPointerException ex) {
/* 1407 */         return false;
/*      */       }  
/* 1410 */     return true;
/*      */   }
/*      */   
/*      */   private int doHashCode(int type) {
/* 1420 */     int total = 0;
/* 1421 */     if (this.nodeCount > 0)
/* 1422 */       for (ViewMapIterator viewMapIterator = new ViewMapIterator(this, type); viewMapIterator.hasNext(); ) {
/* 1423 */         Object key = viewMapIterator.next();
/* 1424 */         Object value = viewMapIterator.getValue();
/* 1425 */         total += key.hashCode() ^ value.hashCode();
/*      */       }  
/* 1428 */     return total;
/*      */   }
/*      */   
/*      */   private String doToString(int type) {
/* 1438 */     if (this.nodeCount == 0)
/* 1439 */       return "{}"; 
/* 1441 */     StringBuffer buf = new StringBuffer(this.nodeCount * 32);
/* 1442 */     buf.append('{');
/* 1443 */     ViewMapIterator viewMapIterator = new ViewMapIterator(this, type);
/* 1444 */     boolean hasNext = viewMapIterator.hasNext();
/* 1445 */     while (hasNext) {
/* 1446 */       Object key = viewMapIterator.next();
/* 1447 */       Object value = viewMapIterator.getValue();
/* 1448 */       buf.append((key == this) ? "(this Map)" : key).append('=').append((value == this) ? "(this Map)" : value);
/* 1452 */       hasNext = viewMapIterator.hasNext();
/* 1453 */       if (hasNext)
/* 1454 */         buf.append(", "); 
/*      */     } 
/* 1458 */     buf.append('}');
/* 1459 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public TreeBidiMap() {}
/*      */   
/*      */   static class View extends AbstractSet {
/*      */     protected final TreeBidiMap main;
/*      */     
/*      */     protected final int orderType;
/*      */     
/*      */     protected final int dataType;
/*      */     
/*      */     View(TreeBidiMap main, int orderType, int dataType) {
/* 1484 */       this.main = main;
/* 1485 */       this.orderType = orderType;
/* 1486 */       this.dataType = dataType;
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/* 1490 */       return (Iterator)new TreeBidiMap.ViewIterator(this.main, this.orderType, this.dataType);
/*      */     }
/*      */     
/*      */     public int size() {
/* 1494 */       return this.main.size();
/*      */     }
/*      */     
/*      */     public boolean contains(Object obj) {
/* 1498 */       TreeBidiMap.checkNonNullComparable(obj, this.dataType);
/* 1499 */       return (this.main.lookup((Comparable)obj, this.dataType) != null);
/*      */     }
/*      */     
/*      */     public boolean remove(Object obj) {
/* 1503 */       return (this.main.doRemove((Comparable)obj, this.dataType) != null);
/*      */     }
/*      */     
/*      */     public void clear() {
/* 1507 */       this.main.clear();
/*      */     }
/*      */   }
/*      */   
/*      */   static class ViewIterator implements OrderedIterator {
/*      */     protected final TreeBidiMap main;
/*      */     
/*      */     protected final int orderType;
/*      */     
/*      */     protected final int dataType;
/*      */     
/*      */     protected TreeBidiMap.Node lastReturnedNode;
/*      */     
/*      */     protected TreeBidiMap.Node nextNode;
/*      */     
/*      */     protected TreeBidiMap.Node previousNode;
/*      */     
/*      */     private int expectedModifications;
/*      */     
/*      */     ViewIterator(TreeBidiMap main, int orderType, int dataType) {
/* 1541 */       this.main = main;
/* 1542 */       this.orderType = orderType;
/* 1543 */       this.dataType = dataType;
/* 1544 */       this.expectedModifications = main.modifications;
/* 1545 */       this.nextNode = TreeBidiMap.leastNode(main.rootNode[orderType], orderType);
/* 1546 */       this.lastReturnedNode = null;
/* 1547 */       this.previousNode = null;
/*      */     }
/*      */     
/*      */     public final boolean hasNext() {
/* 1551 */       return (this.nextNode != null);
/*      */     }
/*      */     
/*      */     public final Object next() {
/* 1555 */       if (this.nextNode == null)
/* 1556 */         throw new NoSuchElementException(); 
/* 1558 */       if (this.main.modifications != this.expectedModifications)
/* 1559 */         throw new ConcurrentModificationException(); 
/* 1561 */       this.lastReturnedNode = this.nextNode;
/* 1562 */       this.previousNode = this.nextNode;
/* 1563 */       this.nextNode = this.main.nextGreater(this.nextNode, this.orderType);
/* 1564 */       return doGetData();
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/* 1568 */       return (this.previousNode != null);
/*      */     }
/*      */     
/*      */     public Object previous() {
/* 1572 */       if (this.previousNode == null)
/* 1573 */         throw new NoSuchElementException(); 
/* 1575 */       if (this.main.modifications != this.expectedModifications)
/* 1576 */         throw new ConcurrentModificationException(); 
/* 1578 */       this.nextNode = this.lastReturnedNode;
/* 1579 */       if (this.nextNode == null)
/* 1580 */         this.nextNode = this.main.nextGreater(this.previousNode, this.orderType); 
/* 1582 */       this.lastReturnedNode = this.previousNode;
/* 1583 */       this.previousNode = this.main.nextSmaller(this.previousNode, this.orderType);
/* 1584 */       return doGetData();
/*      */     }
/*      */     
/*      */     protected Object doGetData() {
/* 1592 */       switch (this.dataType) {
/*      */         case 0:
/* 1594 */           return this.lastReturnedNode.getKey();
/*      */         case 1:
/* 1596 */           return this.lastReturnedNode.getValue();
/*      */         case 2:
/* 1598 */           return this.lastReturnedNode;
/*      */         case 3:
/* 1600 */           return new UnmodifiableMapEntry(this.lastReturnedNode.getValue(), this.lastReturnedNode.getKey());
/*      */       } 
/* 1602 */       return null;
/*      */     }
/*      */     
/*      */     public final void remove() {
/* 1606 */       if (this.lastReturnedNode == null)
/* 1607 */         throw new IllegalStateException(); 
/* 1609 */       if (this.main.modifications != this.expectedModifications)
/* 1610 */         throw new ConcurrentModificationException(); 
/* 1612 */       this.main.doRedBlackDelete(this.lastReturnedNode);
/* 1613 */       this.expectedModifications++;
/* 1614 */       this.lastReturnedNode = null;
/* 1615 */       if (this.nextNode == null) {
/* 1616 */         this.previousNode = TreeBidiMap.greatestNode(this.main.rootNode[this.orderType], this.orderType);
/*      */       } else {
/* 1618 */         this.previousNode = this.main.nextSmaller(this.nextNode, this.orderType);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static class ViewMapIterator extends ViewIterator implements OrderedMapIterator {
/*      */     private final int oppositeType;
/*      */     
/*      */     ViewMapIterator(TreeBidiMap main, int orderType) {
/* 1638 */       super(main, orderType, orderType);
/* 1639 */       this.oppositeType = TreeBidiMap.oppositeIndex(this.dataType);
/*      */     }
/*      */     
/*      */     public Object getKey() {
/* 1643 */       if (this.lastReturnedNode == null)
/* 1644 */         throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()"); 
/* 1646 */       return this.lastReturnedNode.getData(this.dataType);
/*      */     }
/*      */     
/*      */     public Object getValue() {
/* 1650 */       if (this.lastReturnedNode == null)
/* 1651 */         throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()"); 
/* 1653 */       return this.lastReturnedNode.getData(this.oppositeType);
/*      */     }
/*      */     
/*      */     public Object setValue(Object obj) {
/* 1657 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */   
/*      */   static class EntryView extends View {
/*      */     private final int oppositeType;
/*      */     
/*      */     EntryView(TreeBidiMap main, int orderType, int dataType) {
/* 1677 */       super(main, orderType, dataType);
/* 1678 */       this.oppositeType = TreeBidiMap.oppositeIndex(orderType);
/*      */     }
/*      */     
/*      */     public boolean contains(Object obj) {
/* 1682 */       if (!(obj instanceof Map.Entry))
/* 1683 */         return false; 
/* 1685 */       Map.Entry entry = (Map.Entry)obj;
/* 1686 */       Object value = entry.getValue();
/* 1687 */       TreeBidiMap.Node node = this.main.lookup((Comparable)entry.getKey(), this.orderType);
/* 1688 */       return (node != null && node.getData(this.oppositeType).equals(value));
/*      */     }
/*      */     
/*      */     public boolean remove(Object obj) {
/* 1692 */       if (!(obj instanceof Map.Entry))
/* 1693 */         return false; 
/* 1695 */       Map.Entry entry = (Map.Entry)obj;
/* 1696 */       Object value = entry.getValue();
/* 1697 */       TreeBidiMap.Node node = this.main.lookup((Comparable)entry.getKey(), this.orderType);
/* 1698 */       if (node != null && node.getData(this.oppositeType).equals(value)) {
/* 1699 */         this.main.doRedBlackDelete(node);
/* 1700 */         return true;
/*      */       } 
/* 1702 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   static class Node implements Map.Entry, KeyValue {
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
/* 1729 */       this.data = new Comparable[] { key, value };
/* 1730 */       this.leftNode = new Node[2];
/* 1731 */       this.rightNode = new Node[2];
/* 1732 */       this.parentNode = new Node[2];
/* 1733 */       this.blackColor = new boolean[] { true, true };
/* 1734 */       this.calculatedHashCode = false;
/*      */     }
/*      */     
/*      */     private Comparable getData(int index) {
/* 1744 */       return this.data[index];
/*      */     }
/*      */     
/*      */     private void setLeft(Node node, int index) {
/* 1754 */       this.leftNode[index] = node;
/*      */     }
/*      */     
/*      */     private Node getLeft(int index) {
/* 1764 */       return this.leftNode[index];
/*      */     }
/*      */     
/*      */     private void setRight(Node node, int index) {
/* 1774 */       this.rightNode[index] = node;
/*      */     }
/*      */     
/*      */     private Node getRight(int index) {
/* 1784 */       return this.rightNode[index];
/*      */     }
/*      */     
/*      */     private void setParent(Node node, int index) {
/* 1794 */       this.parentNode[index] = node;
/*      */     }
/*      */     
/*      */     private Node getParent(int index) {
/* 1804 */       return this.parentNode[index];
/*      */     }
/*      */     
/*      */     private void swapColors(Node node, int index) {
/* 1815 */       this.blackColor[index] = this.blackColor[index] ^ node.blackColor[index];
/* 1816 */       node.blackColor[index] = node.blackColor[index] ^ this.blackColor[index];
/* 1817 */       this.blackColor[index] = this.blackColor[index] ^ node.blackColor[index];
/*      */     }
/*      */     
/*      */     private boolean isBlack(int index) {
/* 1827 */       return this.blackColor[index];
/*      */     }
/*      */     
/*      */     private boolean isRed(int index) {
/* 1837 */       return !this.blackColor[index];
/*      */     }
/*      */     
/*      */     private void setBlack(int index) {
/* 1846 */       this.blackColor[index] = true;
/*      */     }
/*      */     
/*      */     private void setRed(int index) {
/* 1855 */       this.blackColor[index] = false;
/*      */     }
/*      */     
/*      */     private void copyColor(Node node, int index) {
/* 1865 */       this.blackColor[index] = node.blackColor[index];
/*      */     }
/*      */     
/*      */     public Object getKey() {
/* 1875 */       return this.data[0];
/*      */     }
/*      */     
/*      */     public Object getValue() {
/* 1884 */       return this.data[1];
/*      */     }
/*      */     
/*      */     public Object setValue(Object ignored) throws UnsupportedOperationException {
/* 1896 */       throw new UnsupportedOperationException("Map.Entry.setValue is not supported");
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/* 1909 */       if (obj == this)
/* 1910 */         return true; 
/* 1912 */       if (!(obj instanceof Map.Entry))
/* 1913 */         return false; 
/* 1915 */       Map.Entry e = (Map.Entry)obj;
/* 1916 */       return (this.data[0].equals(e.getKey()) && this.data[1].equals(e.getValue()));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1923 */       if (!this.calculatedHashCode) {
/* 1924 */         this.hashcodeValue = this.data[0].hashCode() ^ this.data[1].hashCode();
/* 1925 */         this.calculatedHashCode = true;
/*      */       } 
/* 1927 */       return this.hashcodeValue;
/*      */     }
/*      */   }
/*      */   
/*      */   static class Inverse implements OrderedBidiMap {
/*      */     private final TreeBidiMap main;
/*      */     
/*      */     private Set keySet;
/*      */     
/*      */     private Set valuesSet;
/*      */     
/*      */     private Set entrySet;
/*      */     
/*      */     Inverse(TreeBidiMap main) {
/* 1952 */       this.main = main;
/*      */     }
/*      */     
/*      */     public int size() {
/* 1956 */       return this.main.size();
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 1960 */       return this.main.isEmpty();
/*      */     }
/*      */     
/*      */     public Object get(Object key) {
/* 1964 */       return this.main.getKey(key);
/*      */     }
/*      */     
/*      */     public Object getKey(Object value) {
/* 1968 */       return this.main.get(value);
/*      */     }
/*      */     
/*      */     public boolean containsKey(Object key) {
/* 1972 */       return this.main.containsValue(key);
/*      */     }
/*      */     
/*      */     public boolean containsValue(Object value) {
/* 1976 */       return this.main.containsKey(value);
/*      */     }
/*      */     
/*      */     public Object firstKey() {
/* 1980 */       if (this.main.nodeCount == 0)
/* 1981 */         throw new NoSuchElementException("Map is empty"); 
/* 1983 */       return TreeBidiMap.leastNode(this.main.rootNode[1], 1).getValue();
/*      */     }
/*      */     
/*      */     public Object lastKey() {
/* 1987 */       if (this.main.nodeCount == 0)
/* 1988 */         throw new NoSuchElementException("Map is empty"); 
/* 1990 */       return TreeBidiMap.greatestNode(this.main.rootNode[1], 1).getValue();
/*      */     }
/*      */     
/*      */     public Object nextKey(Object key) {
/* 1994 */       TreeBidiMap.checkKey(key);
/* 1995 */       TreeBidiMap.Node node = this.main.nextGreater(this.main.lookup((Comparable)key, 1), 1);
/* 1996 */       return (node == null) ? null : node.getValue();
/*      */     }
/*      */     
/*      */     public Object previousKey(Object key) {
/* 2000 */       TreeBidiMap.checkKey(key);
/* 2001 */       TreeBidiMap.Node node = this.main.nextSmaller(this.main.lookup((Comparable)key, 1), 1);
/* 2002 */       return (node == null) ? null : node.getValue();
/*      */     }
/*      */     
/*      */     public Object put(Object key, Object value) {
/* 2006 */       return this.main.doPut((Comparable)value, (Comparable)key, 1);
/*      */     }
/*      */     
/*      */     public void putAll(Map map) {
/* 2010 */       Iterator it = map.entrySet().iterator();
/* 2011 */       while (it.hasNext()) {
/* 2012 */         Map.Entry entry = it.next();
/* 2013 */         put(entry.getKey(), entry.getValue());
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object remove(Object key) {
/* 2018 */       return this.main.removeValue(key);
/*      */     }
/*      */     
/*      */     public Object removeValue(Object value) {
/* 2022 */       return this.main.remove(value);
/*      */     }
/*      */     
/*      */     public void clear() {
/* 2026 */       this.main.clear();
/*      */     }
/*      */     
/*      */     public Set keySet() {
/* 2030 */       if (this.keySet == null)
/* 2031 */         this.keySet = new TreeBidiMap.View(this.main, 1, 1); 
/* 2033 */       return this.keySet;
/*      */     }
/*      */     
/*      */     public Collection values() {
/* 2037 */       if (this.valuesSet == null)
/* 2038 */         this.valuesSet = new TreeBidiMap.View(this.main, 1, 0); 
/* 2040 */       return this.valuesSet;
/*      */     }
/*      */     
/*      */     public Set entrySet() {
/* 2044 */       if (this.entrySet == null)
/* 2045 */         return new TreeBidiMap.EntryView(this.main, 1, 3); 
/* 2047 */       return this.entrySet;
/*      */     }
/*      */     
/*      */     public MapIterator mapIterator() {
/* 2051 */       if (isEmpty())
/* 2052 */         return (MapIterator)EmptyOrderedMapIterator.INSTANCE; 
/* 2054 */       return (MapIterator)new TreeBidiMap.ViewMapIterator(this.main, 1);
/*      */     }
/*      */     
/*      */     public OrderedMapIterator orderedMapIterator() {
/* 2058 */       if (isEmpty())
/* 2059 */         return EmptyOrderedMapIterator.INSTANCE; 
/* 2061 */       return new TreeBidiMap.ViewMapIterator(this.main, 1);
/*      */     }
/*      */     
/*      */     public BidiMap inverseBidiMap() {
/* 2065 */       return (BidiMap)this.main;
/*      */     }
/*      */     
/*      */     public OrderedBidiMap inverseOrderedBidiMap() {
/* 2069 */       return this.main;
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/* 2073 */       return this.main.doEquals(obj, 1);
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 2077 */       return this.main.doHashCode(1);
/*      */     }
/*      */     
/*      */     public String toString() {
/* 2081 */       return this.main.doToString(1);
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bidimap\TreeBidiMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */