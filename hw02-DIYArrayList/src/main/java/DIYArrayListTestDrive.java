import ru.otus.kasymbekovPN.HW02.DIYArrayList;

import java.util.Collections;
import java.util.List;

public class DIYArrayListTestDrive
{
    static public void main(String... args)
    {
        List<Integer> diy1 = new DIYArrayList<>();
        System.out.println("\nInit diy1, constructor without args");
        System.out.println("diy1 : " + diy1);

        List<Integer> diy2 = new DIYArrayList<>(30);
        System.out.println("\nInit diy2, constructor with iniCapacity = 30");
        System.out.println("diy2 : " + diy2);

        int begin = 0;
        int end = 50;
        for (int i = begin; i <= end; i++)
        {
            diy1.add(i);
        }
        System.out.println("\nElements with values from "+ begin +" to " + end + " was sequence add into diy1");
        System.out.println("diy1 : " + diy1);

        List<Integer> diy3 = new DIYArrayList<>(diy1);
        System.out.println("\nInit diy3, diy1 is arguments of constructor");
        System.out.println("diy3 : " + diy3);

        Collections.addAll(diy1, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);
        Collections.addAll(diy2, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);
        Collections.addAll(diy3, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);

        System.out.println("\nElements with values from 1 to 23 was add into diy1");
        System.out.println("diy1 : " + diy1);
        System.out.println("\nElements with values from 1 to 23 was add into diy2");
        System.out.println("diy2 : " + diy2);
        System.out.println("\nElements with values from 1 to 23 was add into diy3");
        System.out.println("diy3 : " + diy3);

        List<Integer> diy5 = new DIYArrayList<>();
        for (int i = begin; i <= end; i++)
        {
            diy5.add(100 + i);
        }
        System.out.println("\nElements with values from "+ (begin + 100) +" to " + (end + 100) + " was sequence add into diy5");
        System.out.println("diy5 : " + diy5);

        Collections.copy(diy3, diy5);
        System.out.println("\ndiy5 copy into diy3");
        System.out.println("diy3 : " + diy3);

        Collections.shuffle(diy3);
        System.out.println("\ndiy3 after shuffle");
        System.out.println("diy3 : " + diy3);

        Collections.sort(diy3);
        System.out.println("\ndiy3 after sort");
        System.out.println("diy3 : " + diy3);
    }
}
