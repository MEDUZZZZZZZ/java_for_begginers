package java_for_begginers.lesson_1;
import java.util.Scanner;

public class hometask_3 {
    public static void main(String[] args) { 
        int[] cords = take_args();
        int count = find_routes(cords);
        System.out.printf("Количество возможных путей: %s", count);       
    }

    public static int[] take_args(){
        int cords[] = new int[4];
        Scanner cons_scanner = new Scanner(System.in);
        System.out.print("Введите длинну: ");
        cords[0] = cons_scanner.nextInt();
        System.out.print("Введите ширину: ");
        cords[1] = cons_scanner.nextInt();
        System.out.print("Введите x точки старта: ");
        cords[2] = cons_scanner.nextInt();
        System.out.print("Введите y точки старта: ");
        cords[3] = cons_scanner.nextInt();
        cons_scanner.close();
        return cords;
    }


    public static int find_routes(int[] cords_arr){ 
        int m = cords_arr[0];
        int n = cords_arr[1];
        int x = cords_arr[2];
        int y = cords_arr[3];
        int[][] res_arr = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                 res_arr[i][j] = 1;
                }   
            }
            for (int i = y; i < m; i++) {
                for (int j = x; j < n; j++) {
                    res_arr[i][j] = res_arr[i-1][j] + res_arr[i][j-1];    
                } 
            }
        print_array(res_arr, cords_arr);   
        int count = res_arr[m - 1][n - 1];
        return count;
    }

    public static void print_array(int[][] tmp_arr, int[] cords_arr) {
        int m = cords_arr[0];
        int n = cords_arr[1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(tmp_arr[i][j] + "\t");
            }
            System.out.println();     
        }
    }
}


