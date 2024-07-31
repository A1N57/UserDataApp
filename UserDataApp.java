import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номертелефона пол");
        String input = scanner.nextLine();
        scanner.close();

        String[] parts = input.split(" ");

        // Проверка количества параметров
        if (parts.length != 6) {
            System.err.println("Ошибка: Введено неверное количество данных. Требуется 6 параметров.");
            return;
        }

        try {
            // Распаковка параметров
            String lastName = parts[0];
            String firstName = parts[1];
            String middleName = parts[2];
            String birthDate = parts[3];
            String phoneNumber = parts[4];
            char gender = parts[5].charAt(0);

            // Проверка формата даты
            if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                throw new IllegalArgumentException("Формат даты неверен. Ожидается dd.mm.yyyy.");
            }

            // Проверка формата номера телефона
            if (!phoneNumber.matches("\\d+")) {
                throw new IllegalArgumentException("Номер телефона должен быть целым беззнаковым числом.");
            }

            // Проверка пола
            if (gender != 'f' && gender != 'm') {
                throw new IllegalArgumentException("Пол должен быть 'f' или 'm'.");
            }

            // Создание строки для записи в файл
            String record = String.join(" ", lastName, firstName, middleName, birthDate, phoneNumber, String.valueOf(gender));

            // Запись в файл
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastName + ".txt", true))) {
                writer.write(record);
                writer.newLine();
                System.out.println("Данные успешно записаны в файл " + lastName + ".txt");
            } catch (IOException e) {
                System.err.println("Ошибка при записи в файл:");
                e.printStackTrace();
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}