package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

class EncodeAndDecodeTinyURL {

    public static void main(String[] args) {
        Codec codec = new Codec();
        String longUrl = "https://leetcode.com/problems/design-tinyurl";

        String encode = codec.encode(longUrl);
        System.out.println(encode);
        String decode = codec.decode(encode);
        System.out.println(decode);
        codec.encode(longUrl);
    }
}

class Codec {

    Map<String, String> siteMap = new HashMap<>();
    static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String BASE = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (siteMap.containsValue(longUrl)) {
            return longUrl;
        }

        var codec = getCodec();
        while (siteMap.containsKey(codec)) {
            codec = getCodec();
        }

        String key = BASE + codec;
        siteMap.put(key, longUrl);
        return key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return siteMap.get(shortUrl);
    }

    private String getCodec() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }
}

