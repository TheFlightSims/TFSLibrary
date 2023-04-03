/*      */ package scala.collection.immutable;
/*      */ 
/*      */ import scala.MatchError;
/*      */ import scala.Predef$;
/*      */ import scala.compat.Platform$;
/*      */ import scala.math.package$;
/*      */ import scala.runtime.BoxesRunTime;
/*      */ 
/*      */ public abstract class VectorPointer$class {
/*      */   public static void $init$(VectorPointer $this) {}
/*      */   
/*      */   public static final void initFrom(VectorPointer $this, VectorPointer that) {
/*  748 */     $this.initFrom(that, that.depth());
/*      */   }
/*      */   
/*      */   public static final void initFrom(VectorPointer $this, VectorPointer that, int depth) {
/*  751 */     $this.depth_$eq(depth);
/*  752 */     int i = depth - 1;
/*  752 */     switch (i) {
/*      */       default:
/*  752 */         throw new MatchError(BoxesRunTime.boxToInteger(i));
/*      */       case 5:
/*  775 */         $this.display5_$eq(that.display5());
/*  776 */         $this.display4_$eq(that.display4());
/*  777 */         $this.display3_$eq(that.display3());
/*  778 */         $this.display2_$eq(that.display2());
/*  779 */         $this.display1_$eq(that.display1());
/*  780 */         $this.display0_$eq(that.display0());
/*      */         break;
/*      */       case 4:
/*      */         $this.display4_$eq(that.display4());
/*      */         $this.display3_$eq(that.display3());
/*      */         $this.display2_$eq(that.display2());
/*      */         $this.display1_$eq(that.display1());
/*      */         $this.display0_$eq(that.display0());
/*      */         break;
/*      */       case 3:
/*      */         $this.display3_$eq(that.display3());
/*      */         $this.display2_$eq(that.display2());
/*      */         $this.display1_$eq(that.display1());
/*      */         $this.display0_$eq(that.display0());
/*      */         break;
/*      */       case 2:
/*      */         $this.display2_$eq(that.display2());
/*      */         $this.display1_$eq(that.display1());
/*      */         $this.display0_$eq(that.display0());
/*      */         break;
/*      */       case 1:
/*      */         $this.display1_$eq(that.display1());
/*      */         $this.display0_$eq(that.display0());
/*      */         break;
/*      */       case 0:
/*      */         $this.display0_$eq(that.display0());
/*      */         break;
/*      */       case -1:
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static final Object getElem(VectorPointer $this, int index, int xor) {
/*  799 */     if (xor < 33554432) {
/*      */     
/*      */     } else {
/*  802 */       if (xor < 1073741824)
/*  803 */         return ((Object[])((Object[])((Object[])((Object[])((Object[])$this.display5()[index >> 25 & 0x1F])[index >> 20 & 0x1F])[index >> 15 & 0x1F])[index >> 10 & 0x1F])[index >> 5 & 0x1F])[index & 0x1F]; 
/*  805 */       throw new IllegalArgumentException();
/*      */     } 
/*      */     return (xor < 32) ? $this.display0()[index & 0x1F] : ((xor < 1024) ? ((Object[])$this.display1()[index >> 5 & 0x1F])[index & 0x1F] : ((xor < 32768) ? ((Object[])((Object[])$this.display2()[index >> 10 & 0x1F])[index >> 5 & 0x1F])[index & 0x1F] : ((xor < 1048576) ? ((Object[])((Object[])((Object[])$this.display3()[index >> 15 & 0x1F])[index >> 10 & 0x1F])[index >> 5 & 0x1F])[index & 0x1F] : "JD-Core does not support Kotlin")));
/*      */   }
/*      */   
/*      */   public static final void gotoPos(VectorPointer $this, int index, int xor) {
/*  814 */     if (xor >= 32)
/*  816 */       if (xor < 1024) {
/*  817 */         $this.display0_$eq((Object[])$this.display1()[index >> 5 & 0x1F]);
/*  819 */       } else if (xor < 32768) {
/*  820 */         $this.display1_$eq((Object[])$this.display2()[index >> 10 & 0x1F]);
/*  821 */         $this.display0_$eq((Object[])$this.display1()[index >> 5 & 0x1F]);
/*  823 */       } else if (xor < 1048576) {
/*  824 */         $this.display2_$eq((Object[])$this.display3()[index >> 15 & 0x1F]);
/*  825 */         $this.display1_$eq((Object[])$this.display2()[index >> 10 & 0x1F]);
/*  826 */         $this.display0_$eq((Object[])$this.display1()[index >> 5 & 0x1F]);
/*  828 */       } else if (xor < 33554432) {
/*  829 */         $this.display3_$eq((Object[])$this.display4()[index >> 20 & 0x1F]);
/*  830 */         $this.display2_$eq((Object[])$this.display3()[index >> 15 & 0x1F]);
/*  831 */         $this.display1_$eq((Object[])$this.display2()[index >> 10 & 0x1F]);
/*  832 */         $this.display0_$eq((Object[])$this.display1()[index >> 5 & 0x1F]);
/*      */       } else {
/*  834 */         if (xor < 1073741824) {
/*  835 */           $this.display4_$eq((Object[])$this.display5()[index >> 25 & 0x1F]);
/*  836 */           $this.display3_$eq((Object[])$this.display4()[index >> 20 & 0x1F]);
/*  837 */           $this.display2_$eq((Object[])$this.display3()[index >> 15 & 0x1F]);
/*  838 */           $this.display1_$eq((Object[])$this.display2()[index >> 10 & 0x1F]);
/*  839 */           $this.display0_$eq((Object[])$this.display1()[index >> 5 & 0x1F]);
/*      */           return;
/*      */         } 
/*  841 */         throw new IllegalArgumentException();
/*      */       }  
/*      */   }
/*      */   
/*      */   public static final void gotoNextBlockStart(VectorPointer $this, int index, int xor) {
/*  851 */     if (xor < 1024) {
/*  852 */       $this.display0_$eq((Object[])$this.display1()[index >> 5 & 0x1F]);
/*  854 */     } else if (xor < 32768) {
/*  855 */       $this.display1_$eq((Object[])$this.display2()[index >> 10 & 0x1F]);
/*  856 */       $this.display0_$eq((Object[])$this.display1()[0]);
/*  858 */     } else if (xor < 1048576) {
/*  859 */       $this.display2_$eq((Object[])$this.display3()[index >> 15 & 0x1F]);
/*  860 */       $this.display1_$eq((Object[])$this.display2()[0]);
/*  861 */       $this.display0_$eq((Object[])$this.display1()[0]);
/*  863 */     } else if (xor < 33554432) {
/*  864 */       $this.display3_$eq((Object[])$this.display4()[index >> 20 & 0x1F]);
/*  865 */       $this.display2_$eq((Object[])$this.display3()[0]);
/*  866 */       $this.display1_$eq((Object[])$this.display2()[0]);
/*  867 */       $this.display0_$eq((Object[])$this.display1()[0]);
/*      */     } else {
/*  869 */       if (xor < 1073741824) {
/*  870 */         $this.display4_$eq((Object[])$this.display5()[index >> 25 & 0x1F]);
/*  871 */         $this.display3_$eq((Object[])$this.display4()[0]);
/*  872 */         $this.display2_$eq((Object[])$this.display3()[0]);
/*  873 */         $this.display1_$eq((Object[])$this.display2()[0]);
/*  874 */         $this.display0_$eq((Object[])$this.display1()[0]);
/*      */         return;
/*      */       } 
/*  876 */       throw new IllegalArgumentException();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static final void gotoNextBlockStartWritable(VectorPointer $this, int index, int xor) {
/*  884 */     if (xor < 1024) {
/*  885 */       if ($this.depth() == 1) {
/*  885 */         $this.display1_$eq(new Object[32]);
/*  885 */         $this.display1()[0] = $this.display0();
/*  885 */         $this.depth_$eq($this.depth() + 1);
/*      */       } 
/*  886 */       $this.display0_$eq(new Object[32]);
/*  887 */       $this.display1()[index >> 5 & 0x1F] = $this.display0();
/*  889 */     } else if (xor < 32768) {
/*  890 */       if ($this.depth() == 2) {
/*  890 */         $this.display2_$eq(new Object[32]);
/*  890 */         $this.display2()[0] = $this.display1();
/*  890 */         $this.depth_$eq($this.depth() + 1);
/*      */       } 
/*  891 */       $this.display0_$eq(new Object[32]);
/*  892 */       $this.display1_$eq(new Object[32]);
/*  893 */       $this.display1()[index >> 5 & 0x1F] = $this.display0();
/*  894 */       $this.display2()[index >> 10 & 0x1F] = $this.display1();
/*  896 */     } else if (xor < 1048576) {
/*  897 */       if ($this.depth() == 3) {
/*  897 */         $this.display3_$eq(new Object[32]);
/*  897 */         $this.display3()[0] = $this.display2();
/*  897 */         $this.depth_$eq($this.depth() + 1);
/*      */       } 
/*  898 */       $this.display0_$eq(new Object[32]);
/*  899 */       $this.display1_$eq(new Object[32]);
/*  900 */       $this.display2_$eq(new Object[32]);
/*  901 */       $this.display1()[index >> 5 & 0x1F] = $this.display0();
/*  902 */       $this.display2()[index >> 10 & 0x1F] = $this.display1();
/*  903 */       $this.display3()[index >> 15 & 0x1F] = $this.display2();
/*  905 */     } else if (xor < 33554432) {
/*  906 */       if ($this.depth() == 4) {
/*  906 */         $this.display4_$eq(new Object[32]);
/*  906 */         $this.display4()[0] = $this.display3();
/*  906 */         $this.depth_$eq($this.depth() + 1);
/*      */       } 
/*  907 */       $this.display0_$eq(new Object[32]);
/*  908 */       $this.display1_$eq(new Object[32]);
/*  909 */       $this.display2_$eq(new Object[32]);
/*  910 */       $this.display3_$eq(new Object[32]);
/*  911 */       $this.display1()[index >> 5 & 0x1F] = $this.display0();
/*  912 */       $this.display2()[index >> 10 & 0x1F] = $this.display1();
/*  913 */       $this.display3()[index >> 15 & 0x1F] = $this.display2();
/*  914 */       $this.display4()[index >> 20 & 0x1F] = $this.display3();
/*      */     } else {
/*  916 */       if (xor < 1073741824) {
/*  917 */         if ($this.depth() == 5) {
/*  917 */           $this.display5_$eq(new Object[32]);
/*  917 */           $this.display5()[0] = $this.display4();
/*  917 */           $this.depth_$eq($this.depth() + 1);
/*      */         } 
/*  918 */         $this.display0_$eq(new Object[32]);
/*  919 */         $this.display1_$eq(new Object[32]);
/*  920 */         $this.display2_$eq(new Object[32]);
/*  921 */         $this.display3_$eq(new Object[32]);
/*  922 */         $this.display4_$eq(new Object[32]);
/*  923 */         $this.display1()[index >> 5 & 0x1F] = $this.display0();
/*  924 */         $this.display2()[index >> 10 & 0x1F] = $this.display1();
/*  925 */         $this.display3()[index >> 15 & 0x1F] = $this.display2();
/*  926 */         $this.display4()[index >> 20 & 0x1F] = $this.display3();
/*  927 */         $this.display5()[index >> 25 & 0x1F] = $this.display4();
/*      */         return;
/*      */       } 
/*  929 */       throw new IllegalArgumentException();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static final Object[] copyOf(VectorPointer $this, Object[] a) {
/*  939 */     if (a == null)
/*  939 */       Predef$.MODULE$.println("NULL"); 
/*  940 */     Object[] b = new Object[a.length];
/*  941 */     int i = a.length;
/*  941 */     Platform$ platform$ = Platform$.MODULE$;
/*  941 */     System.arraycopy(a, 0, b, 0, i);
/*  942 */     return b;
/*      */   }
/*      */   
/*      */   public static final Object[] nullSlotAndCopy(VectorPointer $this, Object[] array, int index) {
/*  947 */     Object x = array[index];
/*  948 */     array[index] = null;
/*  949 */     return $this.copyOf((Object[])x);
/*      */   }
/*      */   
/*      */   public static final void stabilize(VectorPointer $this, int index) {
/*  957 */     int i = $this.depth() - 1;
/*  957 */     switch (i) {
/*      */       default:
/*  957 */         throw new MatchError(BoxesRunTime.boxToInteger(i));
/*      */       case 1:
/*  991 */         $this.display1_$eq($this.copyOf($this.display1()));
/*  992 */         $this.display1()[index >> 5 & 0x1F] = $this.display0();
/*      */         break;
/*      */       case 2:
/*      */         $this.display2_$eq($this.copyOf($this.display2()));
/*      */         $this.display1_$eq($this.copyOf($this.display1()));
/*      */         $this.display2()[index >> 10 & 0x1F] = $this.display1();
/*      */         $this.display1()[index >> 5 & 0x1F] = $this.display0();
/*      */         break;
/*      */       case 3:
/*      */         $this.display3_$eq($this.copyOf($this.display3()));
/*      */         $this.display2_$eq($this.copyOf($this.display2()));
/*      */         $this.display1_$eq($this.copyOf($this.display1()));
/*      */         $this.display3()[index >> 15 & 0x1F] = $this.display2();
/*      */         $this.display2()[index >> 10 & 0x1F] = $this.display1();
/*      */         $this.display1()[index >> 5 & 0x1F] = $this.display0();
/*      */         break;
/*      */       case 4:
/*      */         $this.display4_$eq($this.copyOf($this.display4()));
/*      */         $this.display3_$eq($this.copyOf($this.display3()));
/*      */         $this.display2_$eq($this.copyOf($this.display2()));
/*      */         $this.display1_$eq($this.copyOf($this.display1()));
/*      */         $this.display4()[index >> 20 & 0x1F] = $this.display3();
/*      */         $this.display3()[index >> 15 & 0x1F] = $this.display2();
/*      */         $this.display2()[index >> 10 & 0x1F] = $this.display1();
/*      */         $this.display1()[index >> 5 & 0x1F] = $this.display0();
/*      */         break;
/*      */       case 5:
/*      */         $this.display5_$eq($this.copyOf($this.display5()));
/*      */         $this.display4_$eq($this.copyOf($this.display4()));
/*      */         $this.display3_$eq($this.copyOf($this.display3()));
/*      */         $this.display2_$eq($this.copyOf($this.display2()));
/*      */         $this.display1_$eq($this.copyOf($this.display1()));
/*      */         $this.display5()[index >> 25 & 0x1F] = $this.display4();
/*      */         $this.display4()[index >> 20 & 0x1F] = $this.display3();
/*      */         $this.display3()[index >> 15 & 0x1F] = $this.display2();
/*      */         $this.display2()[index >> 10 & 0x1F] = $this.display1();
/*      */         $this.display1()[index >> 5 & 0x1F] = $this.display0();
/*      */         break;
/*      */       case 0:
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static final void gotoPosWritable0(VectorPointer $this, int newIndex, int xor) {
/* 1004 */     int i = $this.depth() - 1;
/* 1004 */     switch (i) {
/*      */       default:
/* 1004 */         throw new MatchError(BoxesRunTime.boxToInteger(i));
/*      */       case 0:
/* 1031 */         $this.display0_$eq($this.copyOf($this.display0()));
/*      */         return;
/*      */       case 1:
/*      */         $this.display1_$eq($this.copyOf($this.display1()));
/*      */         $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
/*      */         return;
/*      */       case 2:
/*      */         $this.display2_$eq($this.copyOf($this.display2()));
/*      */         $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
/*      */         $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
/*      */         return;
/*      */       case 3:
/*      */         $this.display3_$eq($this.copyOf($this.display3()));
/*      */         $this.display2_$eq($this.nullSlotAndCopy($this.display3(), newIndex >> 15 & 0x1F));
/*      */         $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
/*      */         $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
/*      */         return;
/*      */       case 4:
/*      */         $this.display4_$eq($this.copyOf($this.display4()));
/*      */         $this.display3_$eq($this.nullSlotAndCopy($this.display4(), newIndex >> 20 & 0x1F));
/*      */         $this.display2_$eq($this.nullSlotAndCopy($this.display3(), newIndex >> 15 & 0x1F));
/*      */         $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
/*      */         $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
/*      */         return;
/*      */       case 5:
/*      */         break;
/*      */     } 
/*      */     $this.display5_$eq($this.copyOf($this.display5()));
/*      */     $this.display4_$eq($this.nullSlotAndCopy($this.display5(), newIndex >> 25 & 0x1F));
/*      */     $this.display3_$eq($this.nullSlotAndCopy($this.display4(), newIndex >> 20 & 0x1F));
/*      */     $this.display2_$eq($this.nullSlotAndCopy($this.display3(), newIndex >> 15 & 0x1F));
/*      */     $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
/*      */     $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
/*      */   }
/*      */   
/*      */   public static final void gotoPosWritable1(VectorPointer $this, int oldIndex, int newIndex, int xor) {
/* 1038 */     if (xor < 32) {
/* 1039 */       $this.display0_$eq($this.copyOf($this.display0()));
/* 1041 */     } else if (xor < 1024) {
/* 1042 */       $this.display1_$eq($this.copyOf($this.display1()));
/* 1043 */       $this.display1()[oldIndex >> 5 & 0x1F] = $this.display0();
/* 1044 */       $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
/* 1046 */     } else if (xor < 32768) {
/* 1047 */       $this.display1_$eq($this.copyOf($this.display1()));
/* 1048 */       $this.display2_$eq($this.copyOf($this.display2()));
/* 1049 */       $this.display1()[oldIndex >> 5 & 0x1F] = $this.display0();
/* 1050 */       $this.display2()[oldIndex >> 10 & 0x1F] = $this.display1();
/* 1051 */       $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
/* 1052 */       $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
/* 1054 */     } else if (xor < 1048576) {
/* 1055 */       $this.display1_$eq($this.copyOf($this.display1()));
/* 1056 */       $this.display2_$eq($this.copyOf($this.display2()));
/* 1057 */       $this.display3_$eq($this.copyOf($this.display3()));
/* 1058 */       $this.display1()[oldIndex >> 5 & 0x1F] = $this.display0();
/* 1059 */       $this.display2()[oldIndex >> 10 & 0x1F] = $this.display1();
/* 1060 */       $this.display3()[oldIndex >> 15 & 0x1F] = $this.display2();
/* 1061 */       $this.display2_$eq($this.nullSlotAndCopy($this.display3(), newIndex >> 15 & 0x1F));
/* 1062 */       $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
/* 1063 */       $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
/* 1065 */     } else if (xor < 33554432) {
/* 1066 */       $this.display1_$eq($this.copyOf($this.display1()));
/* 1067 */       $this.display2_$eq($this.copyOf($this.display2()));
/* 1068 */       $this.display3_$eq($this.copyOf($this.display3()));
/* 1069 */       $this.display4_$eq($this.copyOf($this.display4()));
/* 1070 */       $this.display1()[oldIndex >> 5 & 0x1F] = $this.display0();
/* 1071 */       $this.display2()[oldIndex >> 10 & 0x1F] = $this.display1();
/* 1072 */       $this.display3()[oldIndex >> 15 & 0x1F] = $this.display2();
/* 1073 */       $this.display4()[oldIndex >> 20 & 0x1F] = $this.display3();
/* 1074 */       $this.display3_$eq($this.nullSlotAndCopy($this.display4(), newIndex >> 20 & 0x1F));
/* 1075 */       $this.display2_$eq($this.nullSlotAndCopy($this.display3(), newIndex >> 15 & 0x1F));
/* 1076 */       $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
/* 1077 */       $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
/*      */     } else {
/* 1079 */       if (xor < 1073741824) {
/* 1080 */         $this.display1_$eq($this.copyOf($this.display1()));
/* 1081 */         $this.display2_$eq($this.copyOf($this.display2()));
/* 1082 */         $this.display3_$eq($this.copyOf($this.display3()));
/* 1083 */         $this.display4_$eq($this.copyOf($this.display4()));
/* 1084 */         $this.display5_$eq($this.copyOf($this.display5()));
/* 1085 */         $this.display1()[oldIndex >> 5 & 0x1F] = $this.display0();
/* 1086 */         $this.display2()[oldIndex >> 10 & 0x1F] = $this.display1();
/* 1087 */         $this.display3()[oldIndex >> 15 & 0x1F] = $this.display2();
/* 1088 */         $this.display4()[oldIndex >> 20 & 0x1F] = $this.display3();
/* 1089 */         $this.display5()[oldIndex >> 25 & 0x1F] = $this.display4();
/* 1090 */         $this.display4_$eq($this.nullSlotAndCopy($this.display5(), newIndex >> 25 & 0x1F));
/* 1091 */         $this.display3_$eq($this.nullSlotAndCopy($this.display4(), newIndex >> 20 & 0x1F));
/* 1092 */         $this.display2_$eq($this.nullSlotAndCopy($this.display3(), newIndex >> 15 & 0x1F));
/* 1093 */         $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
/* 1094 */         $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
/*      */         return;
/*      */       } 
/* 1096 */       throw new IllegalArgumentException();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static final Object[] copyRange(VectorPointer $this, Object[] array, int oldLeft, int newLeft) {
/* 1104 */     Object[] elems = new Object[32];
/* 1105 */     int i = 32 - package$.MODULE$.max(newLeft, oldLeft);
/* 1105 */     Platform$ platform$ = Platform$.MODULE$;
/* 1105 */     System.arraycopy(array, oldLeft, elems, newLeft, i);
/* 1106 */     return elems;
/*      */   }
/*      */   
/*      */   public static final void gotoFreshPosWritable0(VectorPointer $this, int oldIndex, int newIndex, int xor) {
/* 1118 */     if (xor >= 32)
/* 1121 */       if (xor < 1024) {
/* 1122 */         if ($this.depth() == 1) {
/* 1123 */           $this.display1_$eq(new Object[32]);
/* 1124 */           $this.display1()[oldIndex >> 5 & 0x1F] = $this.display0();
/* 1125 */           $this.depth_$eq($this.depth() + 1);
/*      */         } 
/* 1127 */         $this.display0_$eq(new Object[32]);
/* 1129 */       } else if (xor < 32768) {
/* 1130 */         if ($this.depth() == 2) {
/* 1131 */           $this.display2_$eq(new Object[32]);
/* 1132 */           $this.display2()[oldIndex >> 10 & 0x1F] = $this.display1();
/* 1133 */           $this.depth_$eq($this.depth() + 1);
/*      */         } 
/* 1135 */         $this.display1_$eq((Object[])$this.display2()[newIndex >> 10 & 0x1F]);
/* 1136 */         if ($this.display1() == null)
/* 1136 */           $this.display1_$eq(new Object[32]); 
/* 1137 */         $this.display0_$eq(new Object[32]);
/* 1139 */       } else if (xor < 1048576) {
/* 1140 */         if ($this.depth() == 3) {
/* 1141 */           $this.display3_$eq(new Object[32]);
/* 1142 */           $this.display3()[oldIndex >> 15 & 0x1F] = $this.display2();
/* 1143 */           $this.display2_$eq(new Object[32]);
/* 1144 */           $this.display1_$eq(new Object[32]);
/* 1145 */           $this.depth_$eq($this.depth() + 1);
/*      */         } 
/* 1147 */         $this.display2_$eq((Object[])$this.display3()[newIndex >> 15 & 0x1F]);
/* 1148 */         if ($this.display2() == null)
/* 1148 */           $this.display2_$eq(new Object[32]); 
/* 1149 */         $this.display1_$eq((Object[])$this.display2()[newIndex >> 10 & 0x1F]);
/* 1150 */         if ($this.display1() == null)
/* 1150 */           $this.display1_$eq(new Object[32]); 
/* 1151 */         $this.display0_$eq(new Object[32]);
/* 1153 */       } else if (xor < 33554432) {
/* 1154 */         if ($this.depth() == 4) {
/* 1155 */           $this.display4_$eq(new Object[32]);
/* 1156 */           $this.display4()[oldIndex >> 20 & 0x1F] = $this.display3();
/* 1157 */           $this.display3_$eq(new Object[32]);
/* 1158 */           $this.display2_$eq(new Object[32]);
/* 1159 */           $this.display1_$eq(new Object[32]);
/* 1160 */           $this.depth_$eq($this.depth() + 1);
/*      */         } 
/* 1162 */         $this.display3_$eq((Object[])$this.display4()[newIndex >> 20 & 0x1F]);
/* 1163 */         if ($this.display3() == null)
/* 1163 */           $this.display3_$eq(new Object[32]); 
/* 1164 */         $this.display2_$eq((Object[])$this.display3()[newIndex >> 15 & 0x1F]);
/* 1165 */         if ($this.display2() == null)
/* 1165 */           $this.display2_$eq(new Object[32]); 
/* 1166 */         $this.display1_$eq((Object[])$this.display2()[newIndex >> 10 & 0x1F]);
/* 1167 */         if ($this.display1() == null)
/* 1167 */           $this.display1_$eq(new Object[32]); 
/* 1168 */         $this.display0_$eq(new Object[32]);
/*      */       } else {
/* 1170 */         if (xor < 1073741824) {
/* 1171 */           if ($this.depth() == 5) {
/* 1172 */             $this.display5_$eq(new Object[32]);
/* 1173 */             $this.display5()[oldIndex >> 25 & 0x1F] = $this.display4();
/* 1174 */             $this.display4_$eq(new Object[32]);
/* 1175 */             $this.display3_$eq(new Object[32]);
/* 1176 */             $this.display2_$eq(new Object[32]);
/* 1177 */             $this.display1_$eq(new Object[32]);
/* 1178 */             $this.depth_$eq($this.depth() + 1);
/*      */           } 
/* 1180 */           $this.display4_$eq((Object[])$this.display5()[newIndex >> 20 & 0x1F]);
/* 1181 */           if ($this.display4() == null)
/* 1181 */             $this.display4_$eq(new Object[32]); 
/* 1182 */           $this.display3_$eq((Object[])$this.display4()[newIndex >> 20 & 0x1F]);
/* 1183 */           if ($this.display3() == null)
/* 1183 */             $this.display3_$eq(new Object[32]); 
/* 1184 */           $this.display2_$eq((Object[])$this.display3()[newIndex >> 15 & 0x1F]);
/* 1185 */           if ($this.display2() == null)
/* 1185 */             $this.display2_$eq(new Object[32]); 
/* 1186 */           $this.display1_$eq((Object[])$this.display2()[newIndex >> 10 & 0x1F]);
/* 1187 */           if ($this.display1() == null)
/* 1187 */             $this.display1_$eq(new Object[32]); 
/* 1188 */           $this.display0_$eq(new Object[32]);
/*      */           return;
/*      */         } 
/* 1190 */         throw new IllegalArgumentException();
/*      */       }  
/*      */   }
/*      */   
/*      */   public static final void gotoFreshPosWritable1(VectorPointer $this, int oldIndex, int newIndex, int xor) {
/* 1198 */     $this.stabilize(oldIndex);
/* 1199 */     $this.gotoFreshPosWritable0(oldIndex, newIndex, xor);
/*      */   }
/*      */   
/*      */   public static void debug(VectorPointer $this) {}
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\VectorPointer$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */