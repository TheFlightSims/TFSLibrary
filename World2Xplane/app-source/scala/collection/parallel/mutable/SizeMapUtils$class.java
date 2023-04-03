/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.runtime.RichInt$;
/*    */ 
/*    */ public abstract class SizeMapUtils$class {
/*    */   public static void $init$(SizeMapUtils $this) {}
/*    */   
/*    */   public static int calcNumElems(SizeMapUtils $this, int from, int until, int tableLength, int sizeMapBucketSize) {
/* 27 */     int fbindex = from / sizeMapBucketSize;
/* 30 */     int lbindex = until / sizeMapBucketSize;
/* 40 */     int i = (fbindex + 1) * sizeMapBucketSize;
/* 40 */     Predef$ predef$ = Predef$.MODULE$;
/* 40 */     int fbuntil = RichInt$.MODULE$.min$extension(i, tableLength);
/* 41 */     int fbcount = $this.countElems(from, fbuntil);
/* 42 */     int lbstart = lbindex * sizeMapBucketSize;
/* 43 */     int lbcount = $this.countElems(lbstart, until);
/* 46 */     int inbetween = $this.countBucketSizes(fbindex + 1, lbindex);
/* 49 */     return (fbindex == lbindex) ? $this.countElems(from, until) : (fbcount + inbetween + lbcount);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\SizeMapUtils$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */