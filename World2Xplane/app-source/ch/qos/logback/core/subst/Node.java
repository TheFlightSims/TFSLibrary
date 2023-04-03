/*     */ package ch.qos.logback.core.subst;
/*     */ 
/*     */ public class Node {
/*     */   Type type;
/*     */   
/*     */   Object payload;
/*     */   
/*     */   Object defaultPart;
/*     */   
/*     */   Node next;
/*     */   
/*     */   enum Type {
/*  18 */     LITERAL, VARIABLE;
/*     */   }
/*     */   
/*     */   public Node(Type type, Object payload) {
/*  27 */     this.type = type;
/*  28 */     this.payload = payload;
/*     */   }
/*     */   
/*     */   public Node(Type type, Object payload, Object defaultPart) {
/*  33 */     this.type = type;
/*  34 */     this.payload = payload;
/*  35 */     this.defaultPart = defaultPart;
/*     */   }
/*     */   
/*     */   void append(Node newNode) {
/*  39 */     if (newNode == null)
/*     */       return; 
/*  41 */     Node n = this;
/*     */     while (true) {
/*  43 */       if (n.next == null) {
/*  44 */         n.next = newNode;
/*     */         return;
/*     */       } 
/*  47 */       n = n.next;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     StringBuilder payloadBuf;
/*     */     StringBuilder defaultPartBuf2;
/*     */     String r;
/*  53 */     switch (this.type) {
/*     */       case LITERAL:
/*  55 */         return "Node{type=" + this.type + ", payload='" + this.payload + "'}";
/*     */       case VARIABLE:
/*  60 */         payloadBuf = new StringBuilder();
/*  61 */         defaultPartBuf2 = new StringBuilder();
/*  62 */         if (this.defaultPart != null)
/*  63 */           recursive((Node)this.defaultPart, defaultPartBuf2); 
/*  65 */         recursive((Node)this.payload, payloadBuf);
/*  66 */         r = "Node{type=" + this.type + ", payload='" + payloadBuf.toString() + "'";
/*  69 */         if (this.defaultPart != null)
/*  70 */           r = r + ", defaultPart=" + defaultPartBuf2.toString(); 
/*  71 */         r = r + '}';
/*  72 */         return r;
/*     */     } 
/*  74 */     return null;
/*     */   }
/*     */   
/*     */   public void dump() {
/*  78 */     System.out.print(toString());
/*  79 */     System.out.print(" -> ");
/*  80 */     if (this.next != null) {
/*  81 */       this.next.dump();
/*     */     } else {
/*  83 */       System.out.print(" null");
/*     */     } 
/*     */   }
/*     */   
/*     */   void recursive(Node n, StringBuilder sb) {
/*  88 */     Node c = n;
/*  89 */     while (c != null) {
/*  90 */       sb.append(c.toString()).append(" --> ");
/*  91 */       c = c.next;
/*     */     } 
/*  93 */     sb.append("null ");
/*     */   }
/*     */   
/*     */   public void setNext(Node n) {
/*  97 */     this.next = n;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 102 */     if (this == o)
/* 102 */       return true; 
/* 103 */     if (o == null || getClass() != o.getClass())
/* 103 */       return false; 
/* 105 */     Node node = (Node)o;
/* 107 */     if (this.type != node.type)
/* 107 */       return false; 
/* 108 */     if ((this.payload != null) ? !this.payload.equals(node.payload) : (node.payload != null))
/* 108 */       return false; 
/* 109 */     if ((this.defaultPart != null) ? !this.defaultPart.equals(node.defaultPart) : (node.defaultPart != null))
/* 109 */       return false; 
/* 110 */     if ((this.next != null) ? !this.next.equals(node.next) : (node.next != null))
/* 110 */       return false; 
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 118 */     int result = (this.type != null) ? this.type.hashCode() : 0;
/* 119 */     result = 31 * result + ((this.payload != null) ? this.payload.hashCode() : 0);
/* 120 */     result = 31 * result + ((this.defaultPart != null) ? this.defaultPart.hashCode() : 0);
/* 121 */     result = 31 * result + ((this.next != null) ? this.next.hashCode() : 0);
/* 122 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\subst\Node.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */