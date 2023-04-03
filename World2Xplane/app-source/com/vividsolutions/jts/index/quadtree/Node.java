/*     */ package com.vividsolutions.jts.index.quadtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ public class Node extends NodeBase {
/*     */   private Envelope env;
/*     */   
/*     */   private double centrex;
/*     */   
/*     */   private double centrey;
/*     */   
/*     */   private int level;
/*     */   
/*     */   public static Node createNode(Envelope env) {
/*  51 */     Key key = new Key(env);
/*  52 */     Node node = new Node(key.getEnvelope(), key.getLevel());
/*  53 */     return node;
/*     */   }
/*     */   
/*     */   public static Node createExpanded(Node node, Envelope addEnv) {
/*  58 */     Envelope expandEnv = new Envelope(addEnv);
/*  59 */     if (node != null)
/*  59 */       expandEnv.expandToInclude(node.env); 
/*  61 */     Node largerNode = createNode(expandEnv);
/*  62 */     if (node != null)
/*  62 */       largerNode.insertNode(node); 
/*  63 */     return largerNode;
/*     */   }
/*     */   
/*     */   public Node(Envelope env, int level) {
/*  74 */     this.env = env;
/*  75 */     this.level = level;
/*  76 */     this.centrex = (env.getMinX() + env.getMaxX()) / 2.0D;
/*  77 */     this.centrey = (env.getMinY() + env.getMaxY()) / 2.0D;
/*     */   }
/*     */   
/*     */   public Envelope getEnvelope() {
/*  80 */     return this.env;
/*     */   }
/*     */   
/*     */   protected boolean isSearchMatch(Envelope searchEnv) {
/*  84 */     return this.env.intersects(searchEnv);
/*     */   }
/*     */   
/*     */   public Node getNode(Envelope searchEnv) {
/*  96 */     int subnodeIndex = getSubnodeIndex(searchEnv, this.centrex, this.centrey);
/*  98 */     if (subnodeIndex != -1) {
/* 100 */       Node node = getSubnode(subnodeIndex);
/* 102 */       return node.getNode(searchEnv);
/*     */     } 
/* 105 */     return this;
/*     */   }
/*     */   
/*     */   public NodeBase find(Envelope searchEnv) {
/* 115 */     int subnodeIndex = getSubnodeIndex(searchEnv, this.centrex, this.centrey);
/* 116 */     if (subnodeIndex == -1)
/* 117 */       return this; 
/* 118 */     if (this.subnode[subnodeIndex] != null) {
/* 120 */       Node node = this.subnode[subnodeIndex];
/* 121 */       return node.find(searchEnv);
/*     */     } 
/* 124 */     return this;
/*     */   }
/*     */   
/*     */   void insertNode(Node node) {
/* 129 */     Assert.isTrue((this.env == null || this.env.contains(node.env)));
/* 132 */     int index = getSubnodeIndex(node.env, this.centrex, this.centrey);
/* 134 */     if (node.level == this.level - 1) {
/* 135 */       this.subnode[index] = node;
/*     */     } else {
/* 141 */       Node childNode = createSubnode(index);
/* 142 */       childNode.insertNode(node);
/* 143 */       this.subnode[index] = childNode;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Node getSubnode(int index) {
/* 153 */     if (this.subnode[index] == null)
/* 154 */       this.subnode[index] = createSubnode(index); 
/* 156 */     return this.subnode[index];
/*     */   }
/*     */   
/*     */   private Node createSubnode(int index) {
/* 163 */     double minx = 0.0D;
/* 164 */     double maxx = 0.0D;
/* 165 */     double miny = 0.0D;
/* 166 */     double maxy = 0.0D;
/* 168 */     switch (index) {
/*     */       case 0:
/* 170 */         minx = this.env.getMinX();
/* 171 */         maxx = this.centrex;
/* 172 */         miny = this.env.getMinY();
/* 173 */         maxy = this.centrey;
/*     */         break;
/*     */       case 1:
/* 176 */         minx = this.centrex;
/* 177 */         maxx = this.env.getMaxX();
/* 178 */         miny = this.env.getMinY();
/* 179 */         maxy = this.centrey;
/*     */         break;
/*     */       case 2:
/* 182 */         minx = this.env.getMinX();
/* 183 */         maxx = this.centrex;
/* 184 */         miny = this.centrey;
/* 185 */         maxy = this.env.getMaxY();
/*     */         break;
/*     */       case 3:
/* 188 */         minx = this.centrex;
/* 189 */         maxx = this.env.getMaxX();
/* 190 */         miny = this.centrey;
/* 191 */         maxy = this.env.getMaxY();
/*     */         break;
/*     */     } 
/* 194 */     Envelope sqEnv = new Envelope(minx, maxx, miny, maxy);
/* 195 */     Node node = new Node(sqEnv, this.level - 1);
/* 196 */     return node;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\quadtree\Node.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */