package task8;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task8 {

    int i = 1;
    Lock lock = new ReentrantLock();

    public AutoCloseable method(String filename) {
        AutoCloseable autoCloseable = lock::unlock;
        lock.lock();
        try (FileWriter fileWriter = new FileWriter(filename, true)) {
            fileWriter.write("Поток №" + i + " получил доступ к файлу: ");
            for (int j = 0; j <= 100; j++) {
                fileWriter.write(" " + j + " ");
            }
            fileWriter.write("\n");
        } catch (IOException e) {
            System.err.println("Не могу найти файл!");
        }
        i++;
        return autoCloseable;
    }

    public static void main(String[] args) {
        Task8 task8 = new Task8();
        Runnable runnable = () -> {
            AutoCloseable autoCloseable = task8.method("task8.txt");
            try {
                autoCloseable.close();
            } catch (Exception e) {
                System.err.println("Ошибка при закрытии!");
            }
        };

        Thread[] t = new Thread[5];
        for (int i = 0; i <= 4; i++){
            t[i] = new Thread(runnable);
            t[i].start();
        }
    }
}
