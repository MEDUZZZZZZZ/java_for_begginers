package java_for_begginers.lesson_6;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Random;
public class labirinth_final {
    public static void main(String[] args) {
        var lab_interface = new consoleInterface();
        var tmp = new Fields();
        // tmp.get_unic_field(30);  Использование генератора случайной карты 
        // int [][] field = tmp.get_field(); Использование генератора случайной карты 
        tmp.get_default_field(); // Стандартная карта
        int [][] field = tmp.get_field(); // Стандартная карта
        int [][] point_cords = lab_interface.get_cords(field);
        var startPoint = new Points(point_cords[0][0], point_cords[0][1]);
        var finishPoint = new Points(point_cords[1][0], point_cords[1][1]);
        var alg = new leeAlgorithm(field);
        alg.search_start(startPoint,finishPoint);
        System.out.print(tmp.field_to_string_converter());
        alg.get_way(finishPoint, startPoint);
        System.out.print(tmp.beuty_field());
    }


    static class Points{
        int x;
        int y;
        
        
        public int get_x(){
            return x;
        }
        
        
        public int get_y(){
            return y;
        }
        
        
        public Points(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        
        public void set_x(int x){
            this.x = x;
        }
       
       
        public void set_y(int y){
            this.y = y;
        }
        
        
        public void display_info() {
            System.out.printf("Для данной точки x = %d, y = %d", x, y);
        }
    }


    static class Fields{
        int[][] field;
        int row;
        int col;
        int obstacles_count;


        public void get_default_field(){
            int[][] map = {
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, 00, 00, -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, -1, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, -1, -1, -1, -1, -1, 00, 00, -1, -1, -1, 00, 00, -1 },
                { -1, 00, -1, 00, -1, 00, 00, 00, 00, -1, 00, -1, 00, 00, -1 },
                { -1, 00, -1, 00, -1, -1, 00, 00, 00, -1, 00, -1, 00, 00, -1 },
                { -1, 00, -1, 00, 00, 00, 00, 00, 00, -1, 00, 00, 00, 00, -1 },
                { -1, 00, -1, -1, -1, 00, 00, 00, 00, -1, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, -1, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, -1, 00, 00, -1, -1, -1, -1, 00, 00, -1 },
                { -1, 00, -1, 00, 00, -1, 00, 00, 00, 00, 00, -1, 00, 00, -1 },
                { -1, -1, 00, 00, 00, -1, -1, -1, 00, 00, 00, -1, 00, -1, -1 },
                { -1, 00, -1, 00, 00, -1, 00, 00, -1, 00, 00, -1, 00, -1, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1, 00, 00, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
            };
            this.field = map;
            this.col = field[0].length;
            this.row = field.length;
        }
        

        public void get_unic_field(int obstacles_count){
            int[][] map = {
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
            };
            Random r = new Random();
            for (int i = 0; i <= obstacles_count; i++){
                int x = r.nextInt(13) + 1;
                int y = r.nextInt(13) + 1;
                map[y][x] = -1;
            }
            this.field = map;
            this.col = field[0].length;
            this.row = field.length;
        }

        public int[][] get_field(){
            return field;
        }


        public String field_to_string_converter(){
           StringBuilder tmp = new StringBuilder();
           tmp.append("Результат выполнения волнового алгоритма: \n");
            for (int i = 0; i < row; i++){
                for (int j = 0; j < col; j++){
                    tmp.append(String.format("%4d", field[i][j]));
                }
                tmp.append("\n");
            }
            tmp.append("\n");
            return tmp.toString();
        }
    

        public String beuty_field(){
            String red = "\u001B[31m";
            String white = "\u001B[0m";
            String purple = "\u001B[35m";
            StringBuilder tmp = new StringBuilder();
            tmp.append("Кратчайший путь до выхода: \n");
            for (int i = 0; i < row; i++){
                for (int j = 0; j < col; j++){
                    if (field[i][j] == -3){
                        tmp.append(purple + "^^" + white);
                    }
                    else if (field[i][j] == -2){
                        tmp.append(purple + "EX" + white);
                    }
                    else if (field[i][j] < 0){
                        tmp.append("[]");
                    }
                    else if (field[i][j] == 99){
                        tmp.append(red + " *" + white );
                    }
                    else {
                        tmp.append("  ");
                    }
                }
                tmp.append("\n");
            }
            return tmp.toString();
        }
    }


    static class leeAlgorithm{
        int[][] field;


        public leeAlgorithm(int[][] field){
            this.field = field;
        }
        

        public void search_start(Points startPoint, Points finishPoint){ 
            Queue<Points> serach_queue = new LinkedList<Points>();
            serach_queue.add(startPoint);
            field[startPoint.y][startPoint.x] = 1;
            field[finishPoint.y][finishPoint.x] = 0;
            while (serach_queue.size() != 0) {
                Points curr_p = serach_queue.remove();
                if (field[curr_p.y][curr_p.x + 1] == 0) {
                    serach_queue.add(new Points(curr_p.x + 1, curr_p.y));
                    field[curr_p.y][curr_p.x + 1] = field[curr_p.y][curr_p.x] + 1; 
                }
                if (field[curr_p.y + 1][curr_p.x] == 0) {
                    serach_queue.add(new Points(curr_p.x, curr_p.y + 1));
                    field[curr_p.y + 1][curr_p.x] = field[curr_p.y][curr_p.x] + 1; 
                }
                if (field[curr_p.y][curr_p.x - 1] == 0) {
                    serach_queue.add(new Points(curr_p.x - 1, curr_p.y));
                    field[curr_p.y][curr_p.x - 1] = field[curr_p.y][curr_p.x] + 1; 
                }
                if (field[curr_p.y - 1][curr_p.x] == 0) {
                    serach_queue.add(new Points(curr_p.x, curr_p.y - 1));
                    field[curr_p.y - 1][curr_p.x] = field[curr_p.y][curr_p.x] + 1;    
                }
            }
        }


        public void get_way(Points finishPoint, Points startPoint){ 
            Queue<Points> way_queue = new LinkedList<Points>();
            way_queue.add(finishPoint);
            while (way_queue.size() != 0) {
                Points curr_p = way_queue.remove();
                if (field[curr_p.y][curr_p.x + 1] == field[curr_p.y][curr_p.x] - 1) {
                    way_queue.add(new Points(curr_p.x + 1, curr_p.y));
                    field[curr_p.y][curr_p.x] = 99;
                }
                else if (field[curr_p.y + 1][curr_p.x] ==  field[curr_p.y][curr_p.x] - 1) {
                    way_queue.add(new Points(curr_p.x, curr_p.y + 1));
                    field[curr_p.y][curr_p.x] = 99;
                }
                else if (field[curr_p.y][curr_p.x - 1] == field[curr_p.y][curr_p.x] - 1) {
                    way_queue.add(new Points(curr_p.x - 1, curr_p.y));
                    field[curr_p.y][curr_p.x] = 99;
                }
                else if (field[curr_p.y - 1][curr_p.x] == field[curr_p.y][curr_p.x] - 1){
                    way_queue.add(new Points(curr_p.x, curr_p.y - 1));
                    field[curr_p.y][curr_p.x] = 99;
                }
            }
            field[startPoint.y][startPoint.x] = -3;
            field[finishPoint.y][finishPoint.x] = -2;
        }
    }


    static class consoleInterface{
        int[][] cords_arr;


        public consoleInterface(){
            Scanner cord_scanner = new Scanner(System.in);
            int [][] tmp_arr = new int[2][2];
            System.out.print("Введите x точки старта от 1 до 13: ");
            tmp_arr[0][0] = cord_scanner.nextInt();
            System.out.print("Введите y точки старта от 1 до 13: ");
            tmp_arr[0][1] = cord_scanner.nextInt();
            System.out.print("Введите x точки выхода от 1 до 13: ");
            tmp_arr[1][0] = cord_scanner.nextInt();
            System.out.print("Введите y точки выхода от 1 до 13: ");
            tmp_arr[1][1] = cord_scanner.nextInt();
            cord_scanner.close();
            this.cords_arr = tmp_arr;
        }
        

        public int[][] get_cords(int[][] map){
            map[cords_arr[0][1]][cords_arr[0][0]] = 0;
            map[cords_arr[1][1]][cords_arr[1][0]] = 0;
            return cords_arr;
        }
    }
}
