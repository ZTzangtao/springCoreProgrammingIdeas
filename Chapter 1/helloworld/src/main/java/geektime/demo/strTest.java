package geektime.demo;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * @author Tommy
 * @date 2020/7/13 8:40 下午
 */
public class strTest {

    public static void main(String[] args) throws Exception {
//        String s1 = "abc";
//        String s2 = new String ("abc");
//        s2.intern();
//        System.out.println(s1 ==s2.intern());

//        try{
//            if(false){
//                while (true){
//                }
//            }else {
//                System.exit(1);
//            }
//
//
//        }finally {
//            System.out.println("in finially");
//        }
//        try
//            {
//                throwNPE();
//        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
//            System.out.println(e.getMessage());
//        }
//
//    }
//
//    private static void throwNPE() {
//        throw new NullPointerException("throw");
//    }
//        List<Integer> values = new CopyOnWriteArrayList<>(asList(1,2,3));
//            remove(values);
//        System.out.println(values);

        String s = "abc";
        Field field = String.class.getDeclaredField("value");

        field.setAccessible(true);
        char[] value = (char[]) field.get(s);
        value[0] = 'a';
        value[1] =  'z';
        value[2] = 'v';

        System.out.println(s);

//        Map<String ,Integer> map = new HashMap<>();
//        map.put("A",1);
//        action(map);
//        exectiveService.shutdown();
    }

    private static void remove(Collection<Integer> values) {
        for (Integer i : values){
            values.remove(i);
        }
    }

static ExecutorService exectiveService = newSingleThreadExecutor();

    private static void action(Map<String,Integer> map) throws Exception{
        Future<Integer> future = exectiveService.submit(()->map.get("A"));

        Integer value = future.get();
        System.out.println(value == 1);
    }
}