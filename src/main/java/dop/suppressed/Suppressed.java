package dop.suppressed;

import dop.battle.MyException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Suppressed {

    public static void method() throws MyException {
        try (Resource1 resource1 = new Resource1();
             Resource2 resource2 = new Resource2()) {
            System.out.println(resource1.msg);
            System.out.println(resource2.msg);
            System.out.println("Введите целое число: ");
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
        }
    }

    public static void method1() throws Exception {
        Exception exception = null;
        Resource1 resource1 = new Resource1();
        Resource2 resource2 = new Resource2();
        System.out.println(resource1.msg);
        System.out.println(resource2.msg);
        System.out.println("Введите целое число: ");
        Scanner sc = new Scanner(System.in);
        try {
            int a = sc.nextInt();
        }catch (InputMismatchException ex){
            exception = ex;
            throw exception;
        }finally {
            try{
                resource2.close();
            }catch (MyException myEx){
                if(exception == null){
                    exception = myEx;
                    throw exception;
                }else{
                    exception.addSuppressed(myEx);
                }
            }finally {
                try{
                    resource1.close();
                }catch (MyException myEx){
                    if(exception == null){
                        exception = myEx;
                        throw exception;
                    }else{
                        exception.addSuppressed(myEx);
                    }
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        //method();

        try{
            method();
        }catch (InputMismatchException ex){
            Throwable[] throwables = ex.getSuppressed();
            for(Throwable throwable : throwables){
                System.out.println(throwable.getMessage());
            }
            System.out.println("Вы ввели ни разу не целое число!");
        }

        method1();
    }
}
