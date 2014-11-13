package edu.sjsu.cmpe.cache.client;
import com.google.common.hash.Hashing;
import java.util.*;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
        /*CacheServiceInterface cache = new DistributedCacheService(
                "http://localhost:3000");

        cache.put(1, "foo");
        System.out.println("put(1 => foo)");

        String value = cache.get(1);
        System.out.println("get(1) => " + value);*/

        char [] a={'0','a','b','c','d','e','f','g','h','i','j'};

        List <DistributedCacheService> b=new ArrayList<DistributedCacheService> ();
        b.add(new DistributedCacheService("http://localhost:3000"));

        b.add(new DistributedCacheService("http://localhost:3001"));

        b.add(new DistributedCacheService("http://localhost:3002"));

        int c,d;
System.out.println("Inserting the values");
        for(c=1; c<11; c++){
            int k=Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(c)),b.size());
            b.get(k).put(c, Character.toString(a[c]));
            System.out.println("Key- "+ c + " Value- "+ a[c]+ " Server- "+(k+1));
        }
        System.out.println("Retrieving the values");
        for(d=1; d<11;d++){
            int k1=Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(d)),b.size());
            System.out.println("Key- "+ d + " Value- "+ b.get(k1).get(d)+ " Server- "+(k1+1));
        }


        System.out.println("Existing Cache Client...");
    }

}
