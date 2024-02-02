package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class XUrlImpl implements XUrl{
    private Map<String, String> mp=new HashMap<>();
    private Map<String, String> mpr=new HashMap<>();
    private Map<String, Integer> hitCount =new HashMap<>();
    private int count=1;

    @Override
    public String registerNewUrl(String longUrl) {
        // TODO Auto-generated method stub
       
        String url="http://short.url/";
        String Alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
        StringBuilder sb= new StringBuilder();
        Random r=new Random();
        for(int i=0;i<9;i++){
            int dummy = r.nextInt(Alphanumeric.length());
            char dummyBox=Alphanumeric.charAt(dummy);
            sb.append(dummyBox);
        }
        String uniq=sb.toString();

        String outUrl=url+uniq;

        if(mp.containsKey(longUrl)){
            return mp.get(longUrl);
        }
        else{
        mp.put(longUrl, outUrl);
        mpr.put(outUrl, longUrl);
        }

        return outUrl;
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        // TODO Auto-generated method stub
        if(mpr.containsKey(shortUrl)){
            return null;
        }else{
        mp.put(longUrl, shortUrl);
        mpr.put(shortUrl, longUrl);
        return shortUrl;
        }
    }

    @Override
    public String getUrl(String shortUrl) {
        // TODO Auto-generated method stub
        if(mpr.containsKey(shortUrl)){
        String op = mpr.get(shortUrl);
        if (!hitCount.containsKey(op)) {
            hitCount.put(op, 0);
        }
        hitCount.put(op, hitCount.get(op)+1);
        return op;
        }
        return null;
    }

    @Override
    public Integer getHitCount(String longUrl) {
        // TODO Auto-generated method stub
        if (!hitCount.containsKey(longUrl)) {
            return 0;
        }
        return hitCount.get(longUrl);
    }

    @Override
    public String delete(String longUrl) {
        // TODO Auto-generated method stub
        if(mp.containsKey(longUrl)){
            String duplicate=mp.get(longUrl);
            mpr.remove(duplicate);
        }
        return null;
    }
    

}