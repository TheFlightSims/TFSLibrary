/*    */ package com.google.common.collect;
/*    */ 
/*    */ import com.google.common.annotations.GwtCompatible;
/*    */ 
/*    */ @GwtCompatible(serializable = true)
/*    */ class EmptyImmutableListMultimap extends ImmutableListMultimap<Object, Object> {
/* 28 */   static final EmptyImmutableListMultimap INSTANCE = new EmptyImmutableListMultimap();
/*    */   
/*    */   private static final long serialVersionUID = 0L;
/*    */   
/*    */   private EmptyImmutableListMultimap() {
/* 32 */     super(ImmutableMap.of(), 0);
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 36 */     return INSTANCE;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\collect\EmptyImmutableListMultimap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */