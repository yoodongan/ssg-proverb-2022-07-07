package com.ll.proverb;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    public String url;
    public String path;
    Map<String, String> queryParams;    // 해시맵 도입. 명령어에서 삭제?id=1   삭제 / id=1 이렇게 나눠서 저장.

    public Rq(String url) {
        this.url = url;
        String[] urlBits = url.split("\\?", 2);
        this.path = urlBits[0];   // '삭제'와 같이 명령 부분만 저장.
        queryParams = new HashMap<>();      // 위에 선언하지 않아서 NPE 오류 뜸...
        if (urlBits.length == 2) {   // 삭제? 뒤에 값이 있어야 한다 !!

            String queryStr = urlBits[1];
            String[] paramBits = queryStr.split("&");

            for (String paramBit : paramBits) {
                String[] paramNameAndValue = paramBit.split("=", 2);
                if (paramNameAndValue.length == 1) {
                    continue;
                }
                String paramName = paramNameAndValue[0].trim();
                String paramValue = paramNameAndValue[1].trim();
                queryParams.put(paramName, paramValue);
            }
        }



    }
    public int getIntParam(String paramName, int defaultValue) {
        if (queryParams.containsKey(paramName) == false) {   // HashMap 의 paramName 키 값이 없다면, false ...
            return defaultValue;
        }

        String paramValue = queryParams.get(paramName);

        if (paramValue.length() == 0) {   // 예외처리. 만약 paramValue 값에 아무것도 없다면, defaultValue 출력.
            return defaultValue;
        }

        return Integer.parseInt(paramValue);
    }

    public String getPath() {
        return path;
    }
}
