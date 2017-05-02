package org.jc.allmoduledemo.widget.vlayout;

/**
 * 模型创建工程
 * Created by Jc on 2017/5/2.
 */

public class BeanCreateUtils {
    public static final String serverData = "{\n" +
            "    \"imgs\": [\n" +
            "        \"http://www.005.tv/uploads/allimg/160712/22-160G2102T3c7.jpg\",\n" +
            "        \"http://www.005.tv/uploads/allimg/160830/22-160S0111250139.jpg\",\n" +
            "        \"http://www.005.tv/uploads/allimg/160712/22-160G2102T3c7.jpg\",\n" +
            "        \"http://www.005.tv/uploads/allimg/160830/22-160S0111250139.jpg\"\n" +
            "    ],\n" +
            "    \"gridInfo\": [\n" +
            "        {\n" +
            "            \"name\": \"1\",\n" +
            "            \"url\": \"http://img.qq1234.org/uploads/allimg/150521/8_150521154216_1.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"2\",\n" +
            "            \"url\": \"http://img.qq1234.org/uploads/allimg/150521/8_150521154216_2.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"3\",\n" +
            "            \"url\": \"http://img.qq1234.org/uploads/allimg/150521/8_150521154216_3.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"4\",\n" +
            "            \"url\": \"http://img.qq1234.org/uploads/allimg/150521/8_150521154216_4.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"5\",\n" +
            "            \"url\": \"http://img.qq1234.org/uploads/allimg/150521/8_150521154216_5.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"6\",\n" +
            "            \"url\": \"http://img.qq1234.org/uploads/allimg/150521/8_150521154216_6.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"7\",\n" +
            "            \"url\": \"http://img.qq1234.org/uploads/allimg/150521/8_150521154216_7.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"8\",\n" +
            "            \"url\": \"http://img.qq1234.org/uploads/allimg/150521/8_150521154216_8.jpg\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"hot\": [\n" +
            "        {\n" +
            "            \"title\": \"必抢\",\n" +
            "            \"product\": [\n" +
            "                {\n" +
            "                    \"price\": \"153.0\",\n" +
            "                    \"url\": \"http://img.qq1234.org/uploads/allimg/161216/163933D36-0.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"price\": \"152.0\",\n" +
            "                    \"url\": \"http://img.qq1234.org/uploads/allimg/161216/1639331O5-1.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"price\": \"151.0\",\n" +
            "                    \"url\": \"http://img.qq1234.org/uploads/allimg/161216/1639333239-2.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"price\": \"150.0\",\n" +
            "                    \"url\": \"http://img.qq1234.org/uploads/allimg/161216/1639334126-5.jpg\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"必抢2\",\n" +
            "            \"product\": [\n" +
            "                {\n" +
            "                    \"price\": \"552.0\",\n" +
            "                    \"url\": \"http://img.qq1234.org/uploads/allimg/161216/1639331O5-1.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"price\": \"553.0\",\n" +
            "                    \"url\": \"http://img.qq1234.org/uploads/allimg/161216/163933D36-0.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"price\": \"550.0\",\n" +
            "                    \"url\": \"http://img.qq1234.org/uploads/allimg/161216/1639334126-5.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"price\": \"551.0\",\n" +
            "                    \"url\": \"http://img.qq1234.org/uploads/allimg/161216/1639333239-2.jpg\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    /** 存放当前最新版本的数据

     {
     "imgs": [
     "http://www.005.tv/uploads/allimg/160712/22-160G2102T3c7.jpg",
     "http://www.005.tv/uploads/allimg/160830/22-160S0111250139.jpg",
     "http://www.005.tv/uploads/allimg/160712/22-160G2102T3c7.jpg",
     "http://www.005.tv/uploads/allimg/160830/22-160S0111250139.jpg"
     ],
     "gridInfo": [
     {
     "name": "1",
     "url": "http://img.qq1234.org/uploads/allimg/150521/8_150521154216_1.jpg"
     },
     {
     "name": "2",
     "url": "http://img.qq1234.org/uploads/allimg/150521/8_150521154216_2.jpg"
     },
     {
     "name": "3",
     "url": "http://img.qq1234.org/uploads/allimg/150521/8_150521154216_3.jpg"
     },
     {
     "name": "4",
     "url": "http://img.qq1234.org/uploads/allimg/150521/8_150521154216_4.jpg"
     },
     {
     "name": "5",
     "url": "http://img.qq1234.org/uploads/allimg/150521/8_150521154216_5.jpg"
     },
     {
     "name": "6",
     "url": "http://img.qq1234.org/uploads/allimg/150521/8_150521154216_6.jpg"
     },
     {
     "name": "7",
     "url": "http://img.qq1234.org/uploads/allimg/150521/8_150521154216_7.jpg"
     },
     {
     "name": "8",
     "url": "http://img.qq1234.org/uploads/allimg/150521/8_150521154216_8.jpg"
     }
     ],
     "hot": [
     {
     "title": "必抢",
     "product": [
     {
     "price": "153.0",
     "url": "http://img.qq1234.org/uploads/allimg/161216/163933D36-0.jpg"
     },
     {
     "price": "152.0",
     "url": "http://img.qq1234.org/uploads/allimg/161216/1639331O5-1.jpg"
     },
     {
     "price": "151.0",
     "url": "http://img.qq1234.org/uploads/allimg/161216/1639333239-2.jpg"
     },
     {
     "price": "150.0",
     "url": "http://img.qq1234.org/uploads/allimg/161216/1639334126-5.jpg"
     }
     ]
     },
     {
     "title": "必抢2",
     "product": [
     {
     "price": "552.0",
     "url": "http://img.qq1234.org/uploads/allimg/161216/1639331O5-1.jpg"
     },
     {
     "price": "553.0",
     "url": "http://img.qq1234.org/uploads/allimg/161216/163933D36-0.jpg"
     },
     {
     "price": "550.0",
     "url": "http://img.qq1234.org/uploads/allimg/161216/1639334126-5.jpg"
     },
     {
     "price": "551.0",
     "url": "http://img.qq1234.org/uploads/allimg/161216/1639333239-2.jpg"
     }
     ]
     }
     ]
     }


     */
}
