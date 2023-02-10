// Написать программу вычисления n-ого треугольного числа
package java_for_begginers.lesson_1;
import java.util.Scanner;


public class hometask_1 {
    public static void main(String[] args) {
        int num = take_arg();
        int triang_num = triangular_calc(num);
        System.out.printf("Результрующее треугольное число: %s", triang_num);
    }


    public static int take_arg() {
        Scanner cons_scanner = new Scanner(System.in);
        System.out.print("Введите искомое число: ");
        int num = cons_scanner.nextInt();
        cons_scanner.close();
        return num;
    }

    
    public static int triangular_calc(int num) {
        if (num == 1) return 1;
        return triangular_calc(num - 1) + num;
    }
}
