/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ public final class XmlChars {
/*     */   static final int SIZE = 394;
/*     */   
/*  14 */   static final int[] sXml10StartChars = new int[394];
/*     */   
/*     */   static {
/*  16 */     SETBITS(sXml10StartChars, 192, 214);
/*  17 */     SETBITS(sXml10StartChars, 216, 246);
/*  18 */     SETBITS(sXml10StartChars, 248, 255);
/*  19 */     SETBITS(sXml10StartChars, 256, 305);
/*  20 */     SETBITS(sXml10StartChars, 308, 318);
/*  21 */     SETBITS(sXml10StartChars, 321, 328);
/*  22 */     SETBITS(sXml10StartChars, 330, 382);
/*  23 */     SETBITS(sXml10StartChars, 384, 451);
/*  24 */     SETBITS(sXml10StartChars, 461, 496);
/*  25 */     SETBITS(sXml10StartChars, 500, 501);
/*  26 */     SETBITS(sXml10StartChars, 506, 535);
/*  27 */     SETBITS(sXml10StartChars, 592, 680);
/*  28 */     SETBITS(sXml10StartChars, 699, 705);
/*  29 */     SETBITS(sXml10StartChars, 902);
/*  30 */     SETBITS(sXml10StartChars, 904, 906);
/*  31 */     SETBITS(sXml10StartChars, 908);
/*  32 */     SETBITS(sXml10StartChars, 910, 929);
/*  33 */     SETBITS(sXml10StartChars, 931, 974);
/*  34 */     SETBITS(sXml10StartChars, 976, 982);
/*  35 */     SETBITS(sXml10StartChars, 986);
/*  36 */     SETBITS(sXml10StartChars, 988);
/*  37 */     SETBITS(sXml10StartChars, 990);
/*  38 */     SETBITS(sXml10StartChars, 992);
/*  39 */     SETBITS(sXml10StartChars, 994, 1011);
/*  40 */     SETBITS(sXml10StartChars, 1025, 1036);
/*  41 */     SETBITS(sXml10StartChars, 1038, 1103);
/*  42 */     SETBITS(sXml10StartChars, 1105, 1116);
/*  43 */     SETBITS(sXml10StartChars, 1118, 1153);
/*  44 */     SETBITS(sXml10StartChars, 1168, 1220);
/*  45 */     SETBITS(sXml10StartChars, 1223, 1224);
/*  46 */     SETBITS(sXml10StartChars, 1227, 1228);
/*  47 */     SETBITS(sXml10StartChars, 1232, 1259);
/*  48 */     SETBITS(sXml10StartChars, 1262, 1269);
/*  49 */     SETBITS(sXml10StartChars, 1272, 1273);
/*  51 */     SETBITS(sXml10StartChars, 1329, 1366);
/*  52 */     SETBITS(sXml10StartChars, 1369);
/*  53 */     SETBITS(sXml10StartChars, 1377, 1414);
/*  54 */     SETBITS(sXml10StartChars, 1488, 1514);
/*  55 */     SETBITS(sXml10StartChars, 1520, 1522);
/*  56 */     SETBITS(sXml10StartChars, 1569, 1594);
/*  57 */     SETBITS(sXml10StartChars, 1601, 1610);
/*  58 */     SETBITS(sXml10StartChars, 1649, 1719);
/*  59 */     SETBITS(sXml10StartChars, 1722, 1726);
/*  60 */     SETBITS(sXml10StartChars, 1728, 1742);
/*  61 */     SETBITS(sXml10StartChars, 1744, 1747);
/*  62 */     SETBITS(sXml10StartChars, 1749);
/*  64 */     SETBITS(sXml10StartChars, 1765, 1766);
/*  65 */     SETBITS(sXml10StartChars, 2309, 2361);
/*  66 */     SETBITS(sXml10StartChars, 2365);
/*  67 */     SETBITS(sXml10StartChars, 2392, 2401);
/*  68 */     SETBITS(sXml10StartChars, 2437, 2444);
/*  69 */     SETBITS(sXml10StartChars, 2447, 2448);
/*  70 */     SETBITS(sXml10StartChars, 2451, 2472);
/*  71 */     SETBITS(sXml10StartChars, 2474, 2480);
/*  72 */     SETBITS(sXml10StartChars, 2482);
/*  73 */     SETBITS(sXml10StartChars, 2486, 2489);
/*  74 */     SETBITS(sXml10StartChars, 2524);
/*  75 */     SETBITS(sXml10StartChars, 2525);
/*  76 */     SETBITS(sXml10StartChars, 2527, 2529);
/*  77 */     SETBITS(sXml10StartChars, 2544);
/*  77 */     SETBITS(sXml10StartChars, 2545);
/*  78 */     SETBITS(sXml10StartChars, 2565, 2570);
/*  79 */     SETBITS(sXml10StartChars, 2575);
/*  79 */     SETBITS(sXml10StartChars, 2576);
/*  80 */     SETBITS(sXml10StartChars, 2579, 2600);
/*  81 */     SETBITS(sXml10StartChars, 2602, 2608);
/*  82 */     SETBITS(sXml10StartChars, 2610);
/*  82 */     SETBITS(sXml10StartChars, 2611);
/*  83 */     SETBITS(sXml10StartChars, 2613);
/*  83 */     SETBITS(sXml10StartChars, 2614);
/*  84 */     SETBITS(sXml10StartChars, 2616);
/*  84 */     SETBITS(sXml10StartChars, 2617);
/*  85 */     SETBITS(sXml10StartChars, 2649, 2652);
/*  86 */     SETBITS(sXml10StartChars, 2654);
/*  87 */     SETBITS(sXml10StartChars, 2674, 2676);
/*  88 */     SETBITS(sXml10StartChars, 2693, 2699);
/*  89 */     SETBITS(sXml10StartChars, 2701);
/*  90 */     SETBITS(sXml10StartChars, 2703, 2705);
/*  91 */     SETBITS(sXml10StartChars, 2707, 2728);
/*  92 */     SETBITS(sXml10StartChars, 2730, 2736);
/*  93 */     SETBITS(sXml10StartChars, 2738, 2739);
/*  94 */     SETBITS(sXml10StartChars, 2741, 2745);
/*  95 */     SETBITS(sXml10StartChars, 2749);
/*  96 */     SETBITS(sXml10StartChars, 2784);
/*  97 */     SETBITS(sXml10StartChars, 2821, 2828);
/*  98 */     SETBITS(sXml10StartChars, 2831);
/*  98 */     SETBITS(sXml10StartChars, 2832);
/*  99 */     SETBITS(sXml10StartChars, 2835, 2856);
/* 101 */     SETBITS(sXml10StartChars, 2858, 2864);
/* 102 */     SETBITS(sXml10StartChars, 2866);
/* 102 */     SETBITS(sXml10StartChars, 2867);
/* 103 */     SETBITS(sXml10StartChars, 2870, 2873);
/* 104 */     SETBITS(sXml10StartChars, 2877);
/* 105 */     SETBITS(sXml10StartChars, 2908);
/* 105 */     SETBITS(sXml10StartChars, 2909);
/* 106 */     SETBITS(sXml10StartChars, 2911, 2913);
/* 107 */     SETBITS(sXml10StartChars, 2949, 2954);
/* 108 */     SETBITS(sXml10StartChars, 2958, 2960);
/* 110 */     SETBITS(sXml10StartChars, 2962, 2965);
/* 111 */     SETBITS(sXml10StartChars, 2969, 2970);
/* 112 */     SETBITS(sXml10StartChars, 2972);
/* 113 */     SETBITS(sXml10StartChars, 2974);
/* 113 */     SETBITS(sXml10StartChars, 2975);
/* 114 */     SETBITS(sXml10StartChars, 2979);
/* 114 */     SETBITS(sXml10StartChars, 2980);
/* 115 */     SETBITS(sXml10StartChars, 2984, 2986);
/* 116 */     SETBITS(sXml10StartChars, 2990, 2997);
/* 117 */     SETBITS(sXml10StartChars, 2999, 3001);
/* 118 */     SETBITS(sXml10StartChars, 3077, 3084);
/* 119 */     SETBITS(sXml10StartChars, 3086, 3088);
/* 121 */     SETBITS(sXml10StartChars, 3090, 3112);
/* 122 */     SETBITS(sXml10StartChars, 3114, 3123);
/* 123 */     SETBITS(sXml10StartChars, 3125, 3129);
/* 124 */     SETBITS(sXml10StartChars, 3168);
/* 124 */     SETBITS(sXml10StartChars, 3169);
/* 125 */     SETBITS(sXml10StartChars, 3205, 3212);
/* 126 */     SETBITS(sXml10StartChars, 3214, 3216);
/* 127 */     SETBITS(sXml10StartChars, 3218, 3240);
/* 128 */     SETBITS(sXml10StartChars, 3242, 3251);
/* 129 */     SETBITS(sXml10StartChars, 3253, 3257);
/* 130 */     SETBITS(sXml10StartChars, 3294);
/* 131 */     SETBITS(sXml10StartChars, 3296);
/* 131 */     SETBITS(sXml10StartChars, 3297);
/* 132 */     SETBITS(sXml10StartChars, 3333, 3340);
/* 133 */     SETBITS(sXml10StartChars, 3342, 3344);
/* 134 */     SETBITS(sXml10StartChars, 3346, 3368);
/* 135 */     SETBITS(sXml10StartChars, 3370, 3385);
/* 136 */     SETBITS(sXml10StartChars, 3424);
/* 136 */     SETBITS(sXml10StartChars, 3425);
/* 137 */     SETBITS(sXml10StartChars, 3585, 3630);
/* 138 */     SETBITS(sXml10StartChars, 3632);
/* 139 */     SETBITS(sXml10StartChars, 3634);
/* 139 */     SETBITS(sXml10StartChars, 3635);
/* 140 */     SETBITS(sXml10StartChars, 3648, 3653);
/* 141 */     SETBITS(sXml10StartChars, 3713);
/* 141 */     SETBITS(sXml10StartChars, 3714);
/* 142 */     SETBITS(sXml10StartChars, 3716);
/* 143 */     SETBITS(sXml10StartChars, 3719);
/* 143 */     SETBITS(sXml10StartChars, 3720);
/* 144 */     SETBITS(sXml10StartChars, 3722);
/* 144 */     SETBITS(sXml10StartChars, 3725);
/* 145 */     SETBITS(sXml10StartChars, 3732, 3735);
/* 146 */     SETBITS(sXml10StartChars, 3737, 3743);
/* 147 */     SETBITS(sXml10StartChars, 3745, 3747);
/* 148 */     SETBITS(sXml10StartChars, 3749);
/* 148 */     SETBITS(sXml10StartChars, 3751);
/* 149 */     SETBITS(sXml10StartChars, 3754);
/* 149 */     SETBITS(sXml10StartChars, 3755);
/* 150 */     SETBITS(sXml10StartChars, 3757);
/* 150 */     SETBITS(sXml10StartChars, 3758);
/* 151 */     SETBITS(sXml10StartChars, 3760);
/* 152 */     SETBITS(sXml10StartChars, 3762);
/* 152 */     SETBITS(sXml10StartChars, 3763);
/* 153 */     SETBITS(sXml10StartChars, 3773);
/* 155 */     SETBITS(sXml10StartChars, 3776, 3780);
/* 156 */     SETBITS(sXml10StartChars, 3904, 3911);
/* 157 */     SETBITS(sXml10StartChars, 3913, 3945);
/* 158 */     SETBITS(sXml10StartChars, 4256, 4293);
/* 159 */     SETBITS(sXml10StartChars, 4304, 4342);
/* 160 */     SETBITS(sXml10StartChars, 4352);
/* 161 */     SETBITS(sXml10StartChars, 4354, 4355);
/* 162 */     SETBITS(sXml10StartChars, 4357, 4359);
/* 163 */     SETBITS(sXml10StartChars, 4361);
/* 164 */     SETBITS(sXml10StartChars, 4363, 4364);
/* 165 */     SETBITS(sXml10StartChars, 4366, 4370);
/* 166 */     SETBITS(sXml10StartChars, 4412);
/* 167 */     SETBITS(sXml10StartChars, 4414);
/* 168 */     SETBITS(sXml10StartChars, 4416);
/* 169 */     SETBITS(sXml10StartChars, 4428);
/* 170 */     SETBITS(sXml10StartChars, 4430);
/* 171 */     SETBITS(sXml10StartChars, 4432);
/* 172 */     SETBITS(sXml10StartChars, 4436, 4437);
/* 173 */     SETBITS(sXml10StartChars, 4441);
/* 174 */     SETBITS(sXml10StartChars, 4447, 4449);
/* 175 */     SETBITS(sXml10StartChars, 4451);
/* 176 */     SETBITS(sXml10StartChars, 4453);
/* 177 */     SETBITS(sXml10StartChars, 4455);
/* 178 */     SETBITS(sXml10StartChars, 4457);
/* 179 */     SETBITS(sXml10StartChars, 4461, 4462);
/* 180 */     SETBITS(sXml10StartChars, 4466, 4467);
/* 181 */     SETBITS(sXml10StartChars, 4469);
/* 182 */     SETBITS(sXml10StartChars, 4510);
/* 183 */     SETBITS(sXml10StartChars, 4520);
/* 184 */     SETBITS(sXml10StartChars, 4523);
/* 185 */     SETBITS(sXml10StartChars, 4526, 4527);
/* 186 */     SETBITS(sXml10StartChars, 4535, 4536);
/* 187 */     SETBITS(sXml10StartChars, 4538);
/* 188 */     SETBITS(sXml10StartChars, 4540, 4546);
/* 189 */     SETBITS(sXml10StartChars, 4587);
/* 190 */     SETBITS(sXml10StartChars, 4592);
/* 191 */     SETBITS(sXml10StartChars, 4601);
/* 192 */     SETBITS(sXml10StartChars, 7680, 7835);
/* 193 */     SETBITS(sXml10StartChars, 7840, 7929);
/* 194 */     SETBITS(sXml10StartChars, 7936, 7957);
/* 195 */     SETBITS(sXml10StartChars, 7960, 7965);
/* 196 */     SETBITS(sXml10StartChars, 7968, 8005);
/* 197 */     SETBITS(sXml10StartChars, 8008, 8013);
/* 198 */     SETBITS(sXml10StartChars, 8016, 8023);
/* 199 */     SETBITS(sXml10StartChars, 8025);
/* 200 */     SETBITS(sXml10StartChars, 8027);
/* 201 */     SETBITS(sXml10StartChars, 8029);
/* 202 */     SETBITS(sXml10StartChars, 8031, 8061);
/* 203 */     SETBITS(sXml10StartChars, 8064, 8116);
/* 204 */     SETBITS(sXml10StartChars, 8118, 8124);
/* 205 */     SETBITS(sXml10StartChars, 8126);
/* 206 */     SETBITS(sXml10StartChars, 8130, 8132);
/* 207 */     SETBITS(sXml10StartChars, 8134, 8140);
/* 208 */     SETBITS(sXml10StartChars, 8144, 8147);
/* 209 */     SETBITS(sXml10StartChars, 8150, 8155);
/* 210 */     SETBITS(sXml10StartChars, 8160, 8172);
/* 211 */     SETBITS(sXml10StartChars, 8178, 8180);
/* 212 */     SETBITS(sXml10StartChars, 8182, 8188);
/* 213 */     SETBITS(sXml10StartChars, 8486);
/* 214 */     SETBITS(sXml10StartChars, 8490, 8491);
/* 215 */     SETBITS(sXml10StartChars, 8494);
/* 216 */     SETBITS(sXml10StartChars, 8576, 8578);
/* 217 */     SETBITS(sXml10StartChars, 12353, 12436);
/* 218 */     SETBITS(sXml10StartChars, 12449, 12538);
/* 219 */     SETBITS(sXml10StartChars, 12549, 12588);
/* 223 */     SETBITS(sXml10StartChars, 12295);
/* 224 */     SETBITS(sXml10StartChars, 12321, 12329);
/*     */   }
/*     */   
/* 227 */   static final int[] sXml10Chars = new int[394];
/*     */   
/*     */   static {
/* 230 */     System.arraycopy(sXml10StartChars, 0, sXml10Chars, 0, 394);
/* 233 */     SETBITS(sXml10Chars, 768, 837);
/* 234 */     SETBITS(sXml10Chars, 864, 865);
/* 235 */     SETBITS(sXml10Chars, 1155, 1158);
/* 236 */     SETBITS(sXml10Chars, 1425, 1441);
/* 237 */     SETBITS(sXml10Chars, 1443, 1465);
/* 238 */     SETBITS(sXml10Chars, 1467, 1469);
/* 239 */     SETBITS(sXml10Chars, 1471);
/* 241 */     SETBITS(sXml10Chars, 1473, 1474);
/* 242 */     SETBITS(sXml10Chars, 1476);
/* 243 */     SETBITS(sXml10Chars, 1611, 1618);
/* 244 */     SETBITS(sXml10Chars, 1648);
/* 245 */     SETBITS(sXml10Chars, 1750, 1756);
/* 246 */     SETBITS(sXml10Chars, 1757, 1759);
/* 247 */     SETBITS(sXml10Chars, 1760, 1764);
/* 248 */     SETBITS(sXml10Chars, 1767, 1768);
/* 249 */     SETBITS(sXml10Chars, 1770, 1773);
/* 251 */     SETBITS(sXml10Chars, 2305, 2307);
/* 252 */     SETBITS(sXml10Chars, 2364);
/* 253 */     SETBITS(sXml10Chars, 2366, 2380);
/* 254 */     SETBITS(sXml10Chars, 2381);
/* 255 */     SETBITS(sXml10Chars, 2385, 2388);
/* 256 */     SETBITS(sXml10Chars, 2402);
/* 256 */     SETBITS(sXml10Chars, 2403);
/* 257 */     SETBITS(sXml10Chars, 2433, 2435);
/* 258 */     SETBITS(sXml10Chars, 2492);
/* 259 */     SETBITS(sXml10Chars, 2494);
/* 259 */     SETBITS(sXml10Chars, 2495);
/* 260 */     SETBITS(sXml10Chars, 2496, 2500);
/* 261 */     SETBITS(sXml10Chars, 2503);
/* 261 */     SETBITS(sXml10Chars, 2504);
/* 262 */     SETBITS(sXml10Chars, 2507, 2509);
/* 263 */     SETBITS(sXml10Chars, 2519);
/* 264 */     SETBITS(sXml10Chars, 2530);
/* 264 */     SETBITS(sXml10Chars, 2531);
/* 265 */     SETBITS(sXml10Chars, 2562);
/* 266 */     SETBITS(sXml10Chars, 2620);
/* 267 */     SETBITS(sXml10Chars, 2622);
/* 267 */     SETBITS(sXml10Chars, 2623);
/* 268 */     SETBITS(sXml10Chars, 2624, 2626);
/* 269 */     SETBITS(sXml10Chars, 2631);
/* 269 */     SETBITS(sXml10Chars, 2632);
/* 270 */     SETBITS(sXml10Chars, 2635, 2637);
/* 271 */     SETBITS(sXml10Chars, 2672);
/* 271 */     SETBITS(sXml10Chars, 2673);
/* 272 */     SETBITS(sXml10Chars, 2689, 2691);
/* 273 */     SETBITS(sXml10Chars, 2748);
/* 274 */     SETBITS(sXml10Chars, 2750, 2757);
/* 275 */     SETBITS(sXml10Chars, 2759, 2761);
/* 276 */     SETBITS(sXml10Chars, 2763, 2765);
/* 277 */     SETBITS(sXml10Chars, 2817, 2819);
/* 278 */     SETBITS(sXml10Chars, 2876);
/* 279 */     SETBITS(sXml10Chars, 2878, 2883);
/* 280 */     SETBITS(sXml10Chars, 2887);
/* 280 */     SETBITS(sXml10Chars, 2888);
/* 281 */     SETBITS(sXml10Chars, 2891, 2893);
/* 282 */     SETBITS(sXml10Chars, 2902);
/* 282 */     SETBITS(sXml10Chars, 2903);
/* 283 */     SETBITS(sXml10Chars, 2946);
/* 283 */     SETBITS(sXml10Chars, 2947);
/* 284 */     SETBITS(sXml10Chars, 3006, 3010);
/* 285 */     SETBITS(sXml10Chars, 3014, 3016);
/* 286 */     SETBITS(sXml10Chars, 3018, 3021);
/* 287 */     SETBITS(sXml10Chars, 3031);
/* 288 */     SETBITS(sXml10Chars, 3073, 3075);
/* 289 */     SETBITS(sXml10Chars, 3134, 3140);
/* 290 */     SETBITS(sXml10Chars, 3142, 3144);
/* 291 */     SETBITS(sXml10Chars, 3146, 3149);
/* 292 */     SETBITS(sXml10Chars, 3157, 3158);
/* 293 */     SETBITS(sXml10Chars, 3202, 3203);
/* 294 */     SETBITS(sXml10Chars, 3262, 3268);
/* 295 */     SETBITS(sXml10Chars, 3270, 3272);
/* 296 */     SETBITS(sXml10Chars, 3274, 3277);
/* 297 */     SETBITS(sXml10Chars, 3285, 3286);
/* 298 */     SETBITS(sXml10Chars, 3330, 3331);
/* 299 */     SETBITS(sXml10Chars, 3390, 3395);
/* 300 */     SETBITS(sXml10Chars, 3398, 3400);
/* 301 */     SETBITS(sXml10Chars, 3402, 3405);
/* 302 */     SETBITS(sXml10Chars, 3415);
/* 303 */     SETBITS(sXml10Chars, 3633);
/* 304 */     SETBITS(sXml10Chars, 3636, 3642);
/* 305 */     SETBITS(sXml10Chars, 3655, 3662);
/* 306 */     SETBITS(sXml10Chars, 3761);
/* 307 */     SETBITS(sXml10Chars, 3764, 3769);
/* 308 */     SETBITS(sXml10Chars, 3771, 3772);
/* 309 */     SETBITS(sXml10Chars, 3784, 3789);
/* 310 */     SETBITS(sXml10Chars, 3864, 3865);
/* 311 */     SETBITS(sXml10Chars, 3893);
/* 311 */     SETBITS(sXml10Chars, 3895);
/* 312 */     SETBITS(sXml10Chars, 3897);
/* 313 */     SETBITS(sXml10Chars, 3902);
/* 313 */     SETBITS(sXml10Chars, 3903);
/* 314 */     SETBITS(sXml10Chars, 3953, 3972);
/* 315 */     SETBITS(sXml10Chars, 3974, 3979);
/* 316 */     SETBITS(sXml10Chars, 3984, 3989);
/* 317 */     SETBITS(sXml10Chars, 3991);
/* 318 */     SETBITS(sXml10Chars, 3993, 4013);
/* 319 */     SETBITS(sXml10Chars, 4017, 4023);
/* 320 */     SETBITS(sXml10Chars, 4025);
/* 321 */     SETBITS(sXml10Chars, 8400, 8412);
/* 322 */     SETBITS(sXml10Chars, 8417);
/* 323 */     SETBITS(sXml10Chars, 12330, 12335);
/* 324 */     SETBITS(sXml10Chars, 12441);
/* 324 */     SETBITS(sXml10Chars, 12442);
/* 326 */     SETBITS(sXml10Chars, 1632, 1641);
/* 327 */     SETBITS(sXml10Chars, 1776, 1785);
/* 328 */     SETBITS(sXml10Chars, 2406, 2415);
/* 329 */     SETBITS(sXml10Chars, 2534, 2543);
/* 330 */     SETBITS(sXml10Chars, 2662, 2671);
/* 331 */     SETBITS(sXml10Chars, 2790, 2799);
/* 332 */     SETBITS(sXml10Chars, 2918, 2927);
/* 333 */     SETBITS(sXml10Chars, 3047, 3055);
/* 334 */     SETBITS(sXml10Chars, 3174, 3183);
/* 335 */     SETBITS(sXml10Chars, 3302, 3311);
/* 336 */     SETBITS(sXml10Chars, 3430, 3439);
/* 337 */     SETBITS(sXml10Chars, 3664, 3673);
/* 338 */     SETBITS(sXml10Chars, 3792, 3801);
/* 339 */     SETBITS(sXml10Chars, 3872, 3881);
/* 342 */     SETBITS(sXml10Chars, 183);
/* 343 */     SETBITS(sXml10Chars, 720);
/* 344 */     SETBITS(sXml10Chars, 721);
/* 345 */     SETBITS(sXml10Chars, 903);
/* 346 */     SETBITS(sXml10Chars, 1600);
/* 347 */     SETBITS(sXml10Chars, 3654);
/* 348 */     SETBITS(sXml10Chars, 3782);
/* 349 */     SETBITS(sXml10Chars, 12293);
/* 350 */     SETBITS(sXml10Chars, 12337, 12341);
/* 351 */     SETBITS(sXml10Chars, 12445, 12446);
/* 352 */     SETBITS(sXml10Chars, 12540, 12542);
/*     */   }
/*     */   
/*     */   public static final boolean is10NameStartChar(char c) {
/* 360 */     if (c > 'ㄬ') {
/* 361 */       if (c < '가')
/* 362 */         return (c >= '一' && c <= '龥'); 
/* 364 */       if (c <= '힣')
/* 365 */         return true; 
/* 371 */       return (c <= '?' && c >= '?');
/*     */     } 
/* 374 */     int ix = c;
/* 375 */     return ((sXml10StartChars[ix >> 5] & 1 << (ix & 0x1F)) != 0);
/*     */   }
/*     */   
/*     */   public static final boolean is10NameChar(char c) {
/* 381 */     if (c > 'ㄬ') {
/* 382 */       if (c < '가')
/* 383 */         return (c >= '一' && c <= '龥'); 
/* 385 */       if (c <= '힣')
/* 386 */         return true; 
/* 392 */       return (c >= '?' && c <= '?');
/*     */     } 
/* 395 */     int ix = c;
/* 396 */     return ((sXml10Chars[ix >> 5] & 1 << (ix & 0x1F)) != 0);
/*     */   }
/*     */   
/*     */   public static final boolean is11NameStartChar(char c) {
/* 402 */     if (c <= '⿯') {
/* 403 */       if (c < '̀') {
/* 404 */         if (c < 'À')
/* 405 */           return false; 
/* 408 */         return (c != '×' && c != '÷');
/*     */       } 
/* 410 */       if (c >= 'Ⰰ')
/* 412 */         return true; 
/* 414 */       if (c < 'Ͱ' || c > '↏')
/* 416 */         return false; 
/* 418 */       if (c < ' ')
/* 420 */         return (c != ';'); 
/* 422 */       if (c >= '⁰')
/* 424 */         return (c <= '↏'); 
/* 427 */       return (c == '‌' || c == '‍');
/*     */     } 
/* 431 */     if (c >= '、') {
/* 437 */       if (c <= '?')
/* 439 */         return true; 
/* 441 */       if (c >= '豈' && c <= '�')
/* 445 */         return (c <= '﷏' || c >= 'ﷰ'); 
/*     */     } 
/* 449 */     return false;
/*     */   }
/*     */   
/*     */   public static final boolean is11NameChar(char c) {
/* 455 */     if (c <= '⿯') {
/* 456 */       if (c < ' ')
/* 457 */         return ((c >= 'À' && c != ';') || c == '·'); 
/* 459 */       if (c >= 'Ⰰ')
/* 461 */         return true; 
/* 463 */       if (c < '‌' || c > '↏')
/* 465 */         return false; 
/* 467 */       if (c >= '⁰')
/* 469 */         return true; 
/* 472 */       return (c == '‌' || c == '‍' || c == '‿' || c == '⁀');
/*     */     } 
/* 477 */     if (c >= '、') {
/* 484 */       if (c <= '?')
/* 486 */         return true; 
/* 488 */       if (c >= '豈' && c <= '�')
/* 492 */         return (c <= '﷏' || c >= 'ﷰ'); 
/*     */     } 
/* 496 */     return false;
/*     */   }
/*     */   
/*     */   private static void SETBITS(int[] array, int start, int end) {
/* 501 */     int bit1 = start & 0x1F;
/* 502 */     int bit2 = end & 0x1F;
/* 503 */     start >>= 5;
/* 504 */     end >>= 5;
/* 509 */     if (start == end) {
/* 510 */       for (; bit1 <= bit2; bit1++)
/* 511 */         array[start] = array[start] | 1 << bit1; 
/*     */     } else {
/*     */       int bit;
/* 514 */       for (bit = bit1; bit <= 31; bit++)
/* 515 */         array[start] = array[start] | 1 << bit; 
/* 517 */       while (++start < end)
/* 518 */         array[start] = -1; 
/* 520 */       for (bit = 0; bit <= bit2; bit++)
/* 521 */         array[end] = array[end] | 1 << bit; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void SETBITS(int[] array, int point) {
/* 527 */     int ix = point >> 5;
/* 528 */     int bit = point & 0x1F;
/* 530 */     array[ix] = array[ix] | 1 << bit;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\XmlChars.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */