/*     */ package org.geotools.resources;
/*     */ 
/*     */ public final class CharUtilities {
/*     */   public static boolean isSuperScript(char c) {
/*  48 */     switch (c) {
/*     */       case 'ⁱ':
/*     */       case '⁲':
/*     */       case '⁳':
/*  51 */         return false;
/*     */       case '²':
/*     */       case '³':
/*     */       case '¹':
/*  54 */         return true;
/*     */     } 
/*  56 */     return (c >= '⁰' && c <= 'ⁿ');
/*     */   }
/*     */   
/*     */   public static boolean isSubScript(char c) {
/*  70 */     return (c >= '₀' && c <= '₎');
/*     */   }
/*     */   
/*     */   public static char toSuperScript(char c) {
/*  83 */     switch (c) {
/*     */       case '1':
/*  84 */         return '¹';
/*     */       case '2':
/*  85 */         return '²';
/*     */       case '3':
/*  86 */         return '³';
/*     */       case '+':
/*  87 */         return '⁺';
/*     */       case '-':
/*  88 */         return '⁻';
/*     */       case '=':
/*  89 */         return '⁼';
/*     */       case '(':
/*  90 */         return '⁽';
/*     */       case ')':
/*  91 */         return '⁾';
/*     */       case 'n':
/*  92 */         return 'ⁿ';
/*     */     } 
/*  94 */     if (c >= '0' && c <= '9')
/*  95 */       return (char)(c + 8256); 
/*  97 */     return c;
/*     */   }
/*     */   
/*     */   public static char toSubScript(char c) {
/* 110 */     switch (c) {
/*     */       case '+':
/* 111 */         return '₊';
/*     */       case '-':
/* 112 */         return '₋';
/*     */       case '=':
/* 113 */         return '₌';
/*     */       case '(':
/* 114 */         return '₍';
/*     */       case ')':
/* 115 */         return '₎';
/*     */     } 
/* 117 */     if (c >= '0' && c <= '9')
/* 118 */       return (char)(c + 8272); 
/* 120 */     return c;
/*     */   }
/*     */   
/*     */   public static char toNormalScript(char c) {
/* 127 */     switch (c) {
/*     */       case '¹':
/* 128 */         return '1';
/*     */       case '²':
/* 129 */         return '2';
/*     */       case '³':
/* 130 */         return '3';
/*     */       case 'ⁱ':
/* 131 */         return c;
/*     */       case '⁲':
/* 132 */         return c;
/*     */       case '⁳':
/* 133 */         return c;
/*     */       case '⁺':
/* 134 */         return '+';
/*     */       case '⁻':
/* 135 */         return '-';
/*     */       case '⁼':
/* 136 */         return '=';
/*     */       case '⁽':
/* 137 */         return '(';
/*     */       case '⁾':
/* 138 */         return ')';
/*     */       case 'ⁿ':
/* 139 */         return 'n';
/*     */       case '₊':
/* 140 */         return '+';
/*     */       case '₋':
/* 141 */         return '-';
/*     */       case '₌':
/* 142 */         return '=';
/*     */       case '₍':
/* 143 */         return '(';
/*     */       case '₎':
/* 144 */         return ')';
/*     */     } 
/* 146 */     if (c >= '⁰' && c <= '⁹')
/* 146 */       return (char)(c - 8256); 
/* 147 */     if (c >= '₀' && c <= '₉')
/* 147 */       return (char)(c - 8272); 
/* 148 */     return c;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\CharUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */