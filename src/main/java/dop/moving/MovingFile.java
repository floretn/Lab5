package dop.moving;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class MovingFile {

    private static File[] inputFilesName() throws IOException {
        System.out.print("Введите полное имя перемещаемого файла и затем полное имя нового файла через пробел: ");
        Scanner sc = new Scanner(System.in);
        String pathFrom = sc.next();
        String pathTo = sc.next();

        int index = pathFrom.lastIndexOf('/');
        File dir = new File(pathFrom.substring(0, index));
        if (!dir.exists() || !dir.isDirectory()){
            throw new FileNotFoundException("Не найдена директория " + dir.getAbsolutePath());
        }
        File fileFrom = new File(dir, pathFrom.substring(index + 1));
        if (!fileFrom.exists() || fileFrom.isDirectory()){
            throw new FileNotFoundException("Файл с именем " +
                   fileFrom.getAbsolutePath() + " не существует!");
        }

        index = pathTo.lastIndexOf('/');
        dir = new File(pathTo.substring(0, index));
        if (!dir.exists() || !dir.isDirectory()){
            throw new FileNotFoundException("Не найдена директория " + dir.getAbsolutePath());
        }
        File fileTo = new File(dir, pathTo.substring(index + 1));
        /*
        try {
            if (!fileTo.createNewFile()) {
                throw new FileAlreadyExistsException("Не могу создать файл! Файл с именем " +
                        fileTo.getAbsolutePath() + " уже существует!");
            }
        }catch (SecurityException e){
            throw new MyException("У вас недостаточно прав для создания файла в директории " + dir.getAbsolutePath()
                    + ". Обратитесь к своему системному администратору!");
        }

         */
        return new File[] {fileFrom, fileTo};
    }

    private static void copyWithExceptionHandling(File fileFrom, File fileTo) throws IOException, MemoryException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileFrom));
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileTo)))
        {
            int i;
            while ((i = br.read()) != 0){
                bw.write(i);
            }
        }
        try(FileInputStream is = new FileInputStream(fileFrom);
            FileOutputStream os = new FileOutputStream(fileTo)) {
            byte[] buffer;
            byte[] bufferDop;
            long sizeOfLastArray;
            long numOfIter;
            if (fileFrom.length() >= 2147483647) {
                buffer = new byte[2147483647];
                long size = fileFrom.length();
                sizeOfLastArray = size % 2147483647;
                bufferDop = new byte[(int) sizeOfLastArray];
                numOfIter = (size - sizeOfLastArray) / 2147483647;
                for (long i = 0; i < numOfIter; i++){
                    int length = is.read(buffer);
                    os.write(buffer, 0, length);
                }
                int length = is.read(bufferDop);
                os.write(bufferDop, 0, length);
            }else {
                buffer = new byte[(int) fileFrom.length()];
                int length = is.read(buffer);
                os.write(buffer, 0, length);
            }
        }
    }

    private static void deleteFileBecauseException(File file, Exception ex) throws Exception {
        DeleteException deleteException = null;
        try {
            if (!file.delete()) {
                deleteException = new DeleteException("Не могу удалить созданный файл " + file.getAbsolutePath()
                        + " (Возможно, недостаточно прав доступа)");
            }
        }catch(SecurityException sf){
            ex.addSuppressed(sf);
        }

            if (deleteException != null) {
                ex.addSuppressed(deleteException);
            }
        throw ex;
    }


    public static void copy() throws Exception {
        File[] files = inputFilesName();

        double freeSpace = (new File(files[1].getAbsolutePath().substring(0, 3))).getUsableSpace();
        double fileLength = files[0].length();
        if (fileLength > freeSpace) {
            throw new MemoryException("На диске " + files[1].getAbsolutePath().substring(0, 3) +
                    " недостаточно места для переноса файла! Не могу выполнить операцию!");
        }

        System.out.println("Начинаю перенос файла!");

        for(int j = 0; j <= 5; j++) {
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(files[0]));
                 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(files[1]))) {
                int i;
                long time1 = System.nanoTime();
                while ((i = bis.read()) != -1) {
                    bos.write(i);
                    //System.out.println(i);
                }
                long time2 = System.nanoTime();
                System.out.println(time2 - time1);
            }

            files[0].delete();

            int i;
            long time1 = System.nanoTime();
            //Files.copy(files[1].toPath(), files[0].toPath());
            //copyWithExceptionHandling(files[1], files[0]);
            try (FileInputStream is = new FileInputStream(files[1]);
                 FileOutputStream os = new FileOutputStream(files[0])) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) > 0) {
                    os.write(buffer, 0, buffer.length);
                }
            }
            long time2 = System.nanoTime();
            System.out.println(time2 - time1);

            files[0].delete();
            time1 = System.nanoTime();
            Files.copy(files[1].toPath(), files[0].toPath());
            time2 = System.nanoTime();
            System.out.println(time2 - time1);

            files[0].delete();
            time1 = System.nanoTime();
            copyWithExceptionHandling(files[1], files[0]);
            time2 = System.nanoTime();
            System.out.println(time2 - time1);

            if (j != 5) {
                files[1].delete();
            }
        }

        if (!files[0].delete()){
            deleteFileBecauseException(files[1], new DeleteException("Не могу удалить старый файл " + files[0].getAbsolutePath()
                    + " (Возможно, недостаточно прав доступа)"));
        }
    }

    public static void copy(File fileFrom, File fileTo) throws Exception {

        double freeSpace = (new File(fileTo.getAbsolutePath().substring(0, 3))).getUsableSpace();
        double fileLength = fileFrom.length();

        if (fileLength > freeSpace) {
            throw new MemoryException("На диске " + fileTo.getAbsolutePath().substring(0, 3) +
                    " недостаточно места для переноса файла! Не могу выполнить операцию!");
        }

        System.out.println("Начинаю перенос файла!");

        Files.copy(fileFrom.toPath(), fileTo.toPath());

        try {
            if (!fileFrom.delete()) {
                deleteFileBecauseException(fileTo, new DeleteException("Не могу удалить старый файл " + fileFrom.getAbsolutePath()
                        + " (Возможно, недостаточно прав доступа)"));
            }
        }catch (SecurityException sf){
            deleteFileBecauseException(fileTo, sf);
        }
    }

    public static void main(String[] args) {  // C://Test/2.txt C://Test/1.txt C://Test/2.txt C://Моя_дорогая.jpg C://Test/Моя_дорогая.jpg C://Моя_дорогая.jpg
        while (true){                           // D://Downloads/test100Mb.db C://test100Mb.db D://Downloads/test100Mb.db
            System.out.print("> ");
            Scanner sc = new Scanner(System.in);
            String choice = sc.next();

            if (choice.equals("exit")){
                break;
            }

            if (choice.equals("start1")){
                try {
                    copy();
                    System.out.println("Операция перемещения успешно выполнена!");
                } catch (Exception e) {
                    e.printStackTrace();
                    for (Throwable throwable : e.getSuppressed()){
                        throwable.printStackTrace();
                        System.out.println("Повторите попытку!");
                    }
                }
                continue;
            }

            if (choice.equals("start2")){
                try {
                    File[] files = inputFilesName();
                    copy(files[0], files[1]);
                    System.out.println("Операция перемещения успешно выполнена!");
                } catch (Exception e) {
                    e.printStackTrace();
                    for (Throwable throwable : e.getSuppressed()){
                        throwable.printStackTrace();
                        System.out.println("Повторите попытку!");
                    }
                }
            }else{
                System.out.println("Нет такой команды, повторите!");
            }
        }
    }
}