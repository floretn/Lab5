package dop.chain;

import dop.battle.MyException;
import task1_3.FileFormatException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Chain3 extends Chain2 {

    public int inputYear() throws MyException {
        int year = super.inputYear();
        try {
            BufferedReader br = new BufferedReader(new FileReader("filename1"));
        }catch (FileNotFoundException e) {
            throw new MyException("Файл не найден!", e);
        }
        return year;
    }

    public static void main(String[] args) throws MyException {
        Chain3 chain3 = new Chain3();
        chain3.inputYear();
    }
}
