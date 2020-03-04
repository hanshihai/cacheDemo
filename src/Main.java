import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private boolean enableCache = true;

    private Map<String, String> cache = new HashMap<>();

    public String get(String key) {
        if(enableCache) {
            String result = cache.get(key);
            if(result == null) {
                result = generate(key);
                cache.put(key, result);
            }
            return result;
        }else {
            return generate(key);
        }
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    private String generate(String key) {
        try {
            Thread.sleep(3000);
            byte[] buffer = key.getBytes(StandardCharsets.UTF_8);
            return Base64.getUrlEncoder().encodeToString(buffer);
        }catch(Exception e) {
            return e.getMessage() + key;
        }
    }
    public static void main(String[] args) {
       Main main = new Main();
       if(args != null && args.length > 0) {
           main.enableCache = false;
       }
       Scanner input = new Scanner(System.in);
       System.out.print("please input the key (quit, plesae input quit): ");
       while(input.hasNext()) {
           String inputKey = input.next();
           if("quit".equals(inputKey)) {
               System.exit(0);
           }
           String value = main.get(inputKey);
           System.out.println("the key-value is: " + inputKey + " | " + value);
           System.out.println("Please input more: ");
       }
    }
}
