import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class task4 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Ощибка: нужно указать путь к файлу.");
            return;
        }

        String fileName = args[0];

        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Ошибка: файл не найден - " + fileName);
            return;
        }

        List<Integer> list = new ArrayList<>();
        String line;
        String[] parts;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                parts = line.split(" ");
                for (String part : parts) {
                    list.add(Integer.parseInt(part));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат числа в файле");
            return;
        }

        if (list.isEmpty()) {
            System.out.println("Файл пуст!");
            return;
        }

        int[] nums = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }

        Arrays.sort(nums);

        int median = nums[nums.length / 2];

        int moves = 0;

        for (int i = 0; i < nums.length; i++) {
            moves += Math.abs(nums[i] - median);
        }

        if (moves <= 20) {
            System.out.println(moves);
        } else {
            System.out.println("20 ходов недостаточно для " +
                    "приведения всех элементов массива к одному числу");
        }
    }
}
