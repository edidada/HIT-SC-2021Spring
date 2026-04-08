package com.hit.sc.lab1.p1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MagicSquare {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Integer.toString(i) + ".txt:");
            System.out.println(isLegalMagicSquare(i + ".txt"));
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要生成幻方方阵的行列数n");
        int n = sc.nextInt();
        boolean flag = generateMagicSquare(n);
        if (flag) {
            System.out.println(Integer.toString(6) + ".txt:");
            System.out.println(isLegalMagicSquare(6 + ".txt"));
        } else System.out.println(flag);
    }

    public static boolean isLegalMagicSquare(String fileName) throws IOException {
        File file = new File("src/P1/txt/" + fileName);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String str;
        ArrayList<String> arrayList = new ArrayList<>();
        while ((str = br.readLine()) != null) {
            arrayList.add(str);
        }
        br.close();
        isr.close();
        int rows, cols;
        rows = arrayList.size();
        for (int i = 0; i < rows; i++) {
            if (arrayList.get(i).contains(" ")) {
                System.out.print("数字之间并非完全使用\\t分割!");
                return false;
            }
        }
        int cols_temp;
        cols = arrayList.get(0).split("\t+").length;
        for (int i = 1; i < rows; i++) {
            cols_temp = arrayList.get(i).split("\t+").length;
            if (cols_temp != cols) {
                System.out.print("列数不统一,并非矩阵!");
                return false;
            }
        }
        if (rows != cols) {
            System.out.print("行列数不一致！");
            return false;
        }
        int n = rows;
        int[][] square = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                String s = arrayList.get(i).split("\t+")[j];
                Pattern pattern = Pattern.compile("[0-9]*");
                if (!pattern.matcher(s).matches()) {
                    System.out.print("不全是正整数!");
                    return false;
                }
                square[i][j] = Integer.parseInt(s);
            }
        int rows_value = 0;
        int rows_value_temp = 0, cols_value_temp = 0, diagonals_value_temp = 0;
        for (int i = 0; i < n; i++) {
            rows_value += square[0][i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rows_value_temp += square[i][j];
                cols_value_temp += square[j][i];
                diagonals_value_temp += square[j][n - 1 - j];
            }
            if (rows_value_temp != rows_value || cols_value_temp != rows_value || diagonals_value_temp != rows_value) {
                System.out.print("不是幻方！");
                return false;
            } else {
                rows_value_temp = 0;
                cols_value_temp = 0;
                diagonals_value_temp = 0;
            }
        }
        return true;
    }

    public static boolean generateMagicSquare(int n) {
        if (n <= 0 || n % 2 == 0)
            return false;
        int magic[][] = new int[n][n];
        int row = 0, col = n / 2, i, j, square = n * n;
        for (i = 1; i <= square; i++) {
            magic[row][col] = i;
            if (i % n == 0)
                row++;
            else {
                if (row == 0)
                    row = n - 1;
                else
                    row--;
                if (col == (n - 1))
                    col = 0;
                else
                    col++;
            }
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
                System.out.print(magic[i][j] + "\t");
            System.out.println();
        }
        String txtPath = "src/P1/txt/6.txt";
        try {
            File writeName = new File(txtPath);
            writeName.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
            for (int k = 0; k < n; k++) {
                for (int l = 0; l < n; l++) {
                    String s = Integer.toString(magic[k][l]);
                    if (l != n - 1)
                        out.write(s + '\t');
                    else
                        out.write(s + '\n');
                }
            }
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
