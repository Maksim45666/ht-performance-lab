import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class task2 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Ошибка: нужно 2 аргумента: файл_эллипса файл_точек");
            return;
        }

        String[] inputFiles = {args[0], args[1]};
        File file;
        for (String fileName : inputFiles) {
            file = new File(fileName);
            if (!file.exists()) {
                System.out.println("Ошибка: файл " + fileName + " не найден!");
                return;
            }
        }

        String[] parts;
        double x;
        double y;
        double value;

        try (BufferedReader reader1 = new BufferedReader(new FileReader(args[0]));
            BufferedReader reader2 = new BufferedReader(new FileReader(args[1]))) {


            String line;

            String centerLine = null;

            while((line = reader1.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    centerLine = line;
                    break;
                }
            }

            if (centerLine == null) {
                System.out.println("Ошибка: файл " + args[0] + " не содержит данных!" );
                return;
            }
            String[] center = centerLine.trim().split(" ");

            double x0 = Double.parseDouble(center[0]);
            double y0 = Double.parseDouble(center[1]);

            String radiusLine = null;

            while((line = reader1.readLine()) != null ) {
                if(!line.trim().isEmpty()) {
                    radiusLine = line;
                    break;
                }
            }

            if (radiusLine == null) {
                System.out.println("Ошибка: файл " + args[0] + " не содержит строки с радиусом!" );
                return;
            }

            String[] radius = radiusLine.trim().split(" ");
            double rx = Double.parseDouble(radius[0]);
            double ry = Double.parseDouble(radius[1]);


            if (rx == 0 || ry == 0) {
                System.out.println("Ошибка: радиус не может быть равен 0");
                return;
            }

            while ((line = reader2.readLine()) != null) {
                line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                parts = line.split(" ");

                x = Double.parseDouble(parts[0]);
                y = Double.parseDouble(parts[1]);

                value = Math.pow((x - x0), 2) / Math.pow(rx, 2)
                        + Math.pow((y - y0), 2) / Math.pow(ry, 2);

                if (value == 1.0) {
                    System.out.println(0);
                } else if (value < 1.0) {
                    System.out.println(1);
                } else {
                    System.out.println(2);
                }
             }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат числа в файле");
        }
    }
}
