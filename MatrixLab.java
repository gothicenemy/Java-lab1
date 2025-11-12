/**
 * Лабораторна робота №1.
 * Виконавець: Максимовський
 * Група: ІМ-31
 * Варіант: C5=2, C7=2, C11=5
 */
public class MatrixLab {

    /**
     * Виконавчий метод (main) згідно з завданням
     */
    public static void main(String[] args) {

        final boolean TEST_EXCEPTION_MODE = false;

        short[][] matrixA = {
                {5, 1, 9, 3},
                {-10, 4, 11, 6},
                {7, 8, 3, 0},
                {100, 50, 25, 15}
        };

        short[][] matrixB;

        if (TEST_EXCEPTION_MODE) {
            matrixB = new short[][] {
                    {1, 2},
                    {3, 4}
            };
            System.out.println("!!! УВІМКНЕНО РЕЖИМ ТЕСТУВАННЯ ВИКЛЮЧЕНЬ !!!");
        } else {
            matrixB = new short[][] {
                    {3, 8, 0, 1},
                    {12, 1, -5, 4},
                    {2, 1, 6, 9},
                    {-50, 10, -5, 80}
            };
        }

        printMatrix("Матриця A:", matrixA);
        printMatrix("Матриця B:", matrixB);

        try {
            short[][] matrixC = addMatrices(matrixA, matrixB);
            printMatrix("Результат (C = A + B):", matrixC);

            long finalSum = calculateSpecialSum(matrixC);

            System.out.println("=========================================");
            System.out.println("Дія 2 (C11 = 5):");
            System.out.println("Сума (max непарні рядки + min парні рядки) = " + finalSum);
            System.out.println("=========================================");

        } catch (IllegalArgumentException e) {
            System.err.println("ПОМИЛКА: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Виникла непередбачувана помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Дія 1 (C5 = 2): Додавання матриць C = A + B.
     * @param a Матриця A (short[][])
     * @param b Матриця B (short[][])
     * @return Матриця C (short[][])
     * @throws IllegalArgumentException якщо матриці мають різні розміри
     */
    public static short[][] addMatrices(short[][] a, short[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("Матриці мають різні розміри! Додавання неможливе.");
        }

        int rows = a.length;
        int cols = a[0].length;
        short[][] c = new short[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                c[i][j] = (short) (a[i][j] + b[i][j]);
            }
        }
        return c;
    }

    /**
     * Дія 2 (C11 = 5): Обчислює суму найбільших елементів в рядках з непарними
     * номерами та найменших елементів в рядках з парними номерами.
     * @param c Матриця C (short[][])
     * @return Загальна сума (long)
     */
    public static long calculateSpecialSum(short[][] c) {
        long totalSum = 0;

        for (int i = 0; i < c.length; i++) {
            if (c[i].length == 0) {
                continue;
            }

            if (i % 2 == 0) {
                short minInRow = c[i][0];
                for (int j = 1; j < c[i].length; j++) {
                    if (c[i][j] < minInRow) {
                        minInRow = c[i][j];
                    }
                }
                totalSum += minInRow;
            } else {
                short maxInRow = c[i][0];
                for (int j = 1; j < c[i].length; j++) {
                    if (c[i][j] > maxInRow) {
                        maxInRow = c[i][j];
                    }
                }
                totalSum += maxInRow;
            }
        }
        return totalSum;
    }

    /**
     * Допоміжний метод для виведення матриці на екран.
     * @param title Заголовок для матриці
     * @param matrix Матриця для друку (short[][])
     */
    public static void printMatrix(String title, short[][] matrix) {
        System.out.println("=========================================");
        System.out.println(title);
        System.out.println("=========================================");
        if (matrix == null) {
            System.out.println("Матриця <null>");
            return;
        }
        for (short[] row : matrix) {
            for (short val : row) {
                System.out.printf("%6d", val);
            }
            System.out.println();
        }
    }
}
