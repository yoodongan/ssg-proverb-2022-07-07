package com.ll.proverb;

public class Rq {
    public String url;
    public String path;
    public String queryStr;

    public Rq(String url) {
        this.url = url;
        String[] urlBits = url.split("\\?", 2);
        this.path = urlBits[0];   // '삭제'와 같이 명령 부분만 저장.
        if (urlBits.length == 2) {
            this.queryStr = urlBits[1];   // url의 ? 뒤 나머지 부분을 queryStr 이라고 한다. 삭제?id=1&no=3
        }

    }
    public int getIntParam(String paramName, int defaultValue) {
        if (queryStr == null) {
            return defaultValue;
        }

        String[] bits = queryStr.split("&");

        for(String urlBit : bits) {
            String[] paramNameAndValue = urlBit.split("=", 2);
            String paramName_ = paramNameAndValue[0];
            String paramValue = paramNameAndValue[1];

            if (paramName.equals(paramName_)) {
                return Integer.parseInt(paramValue);
            }
        }
        return defaultValue;
    }

    public String getPath() {
        return path;
    }
}
