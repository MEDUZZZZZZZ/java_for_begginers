package java_for_begginers.lesson_4;
import java.util.LinkedList;
import java.util.Queue;

class hometask_labirint_4 {
    public static void main(String[] args) {
        var startPoint = new Points(13, 13);
        var finishPoint = new Points(3, 2);
        int[][] field = {
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
        var alg = new leeAlgorithm(field);
        alg.search_start(startPoint,finishPoint);
        alg.print_map(field);
        alg.get_way(finishPoint);
        System.out.println("Кратчайший путь до точки выгода представлен ниже:");
        alg.print_map(field); 
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

        public void get_way(Points finishPoint){ 
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
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (field[i][j] > 0 && field[i][j] != 99) {
                        field[i][j] = 0;
                    }
                }
            }
        }
        public void print_map(int[][] field){
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    System.out.print(field[i][j] + "\t");
                }
                System.out.println();     
            }
    
        }
    }
}

    