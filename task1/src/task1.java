public class task1 {

    private static String buidPath(int[] arr, int m) {
        String path = "";
        int index = 0;

        while (true) {
            path += arr[index];
            index = (index + m - 1) % arr.length;
            if(index == 0) {
                break;
            }
        }

        return path;
    }

    public static void main(String[] args) {
        if(args.length < 4) {
            System.out.println("Ошибка: нужно 4 аргумента: n1 m1 n2 m2");
            return;
        }

        try {
            int n1 = Integer.parseInt(args[0]);
            int m1 = Integer.parseInt(args[1]);
            int n2 = Integer.parseInt(args[2]);
            int m2 = Integer.parseInt(args[3]);

            if (n1 <= 0 || m1 <= 0 || n2 <= 0 || m2 <= 0) {
                System.out.println("Ошибка: n и m должны быть положительными числами");
                return;
            }

            int[] arr1 = new int[n1];
            int[] arr2 = new int[n2];

            for(int i = 0; i < arr1.length; i++) {
                arr1[i] = i + 1;
            }

            for(int i = 0; i < arr2.length; i++) {
                arr2[i] = i + 1;
            }

            String[] path = new String[2];

            Thread t1 = new Thread(() -> { path[0] = buidPath(arr1, m1);});
            Thread t2 = new Thread(() -> { path[1] = buidPath(arr2, m2);});

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println(path[0] + path[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат входных данных");
        }

    }
}
