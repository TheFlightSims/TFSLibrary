/*    */ package scala;
/*    */ 
/*    */ import scala.collection.AbstractIterator;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001M2q!\001\002\021\002\007\005QAA\004Qe>$Wo\031;\013\003\r\tQa]2bY\006\034\001aE\002\001\r)\001\"a\002\005\016\003\tI!!\003\002\003\007\005s\027\020\005\002\b\027%\021AB\001\002\007\013F,\030\r\\:\t\0139\001A\021A\b\002\r\021Jg.\033;%)\005\001\002CA\004\022\023\t\021\"A\001\003V]&$\b\"\002\013\001\r\003)\022A\0049s_\022,8\r^#mK6,g\016\036\013\003\rYAQaF\nA\002a\t\021A\034\t\003\017eI!A\007\002\003\007%sG\017C\003\035\001\031\005Q$\001\007qe>$Wo\031;Be&$\0300F\001\031\021\025y\002\001\"\001!\003=\001(o\0343vGRLE/\032:bi>\024X#A\021\021\007\t*cA\004\002\bG%\021AEA\001\ba\006\0347.Y4f\023\t1sE\001\005Ji\026\024\030\r^8s\025\t!#\001C\003*\001\021\005!&A\007qe>$Wo\031;Qe\0264\027\016_\013\002WA\021A&M\007\002[)\021afL\001\005Y\006twMC\0011\003\021Q\027M^1\n\005Ij#AB*ue&tw\r")
/*    */ public interface Product extends Equals {
/*    */   Object productElement(int paramInt);
/*    */   
/*    */   int productArity();
/*    */   
/*    */   Iterator<Object> productIterator();
/*    */   
/*    */   String productPrefix();
/*    */   
/*    */   public class Product$$anon$1 extends AbstractIterator<Object> {
/*    */     private int c;
/*    */     
/*    */     private final int cmax;
/*    */     
/*    */     public Product$$anon$1(Product $outer) {
/* 39 */       this.c = 0;
/* 40 */       this.cmax = $outer.productArity();
/*    */     }
/*    */     
/*    */     private int c() {
/*    */       return this.c;
/*    */     }
/*    */     
/*    */     private void c_$eq(int x$1) {
/*    */       this.c = x$1;
/*    */     }
/*    */     
/*    */     private int cmax() {
/* 40 */       return this.cmax;
/*    */     }
/*    */     
/*    */     public boolean hasNext() {
/* 41 */       return (c() < cmax());
/*    */     }
/*    */     
/*    */     public Object next() {
/* 42 */       Object result = this.$outer.productElement(c());
/* 42 */       c_$eq(c() + 1);
/* 42 */       return result;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Product.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */