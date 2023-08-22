import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] inputMass = input.split(" ");
        String[] inputMass1 = new String[4];
        if (inputMass.length < 4) {
            inputMass1[0] = "-a";
            for (int i = 1; i < 4; i++) {
                inputMass1[i] = inputMass[i - 1];
            }
        } else inputMass1 = inputMass;
        String mod = inputMass1[0];
        if (Objects.equals(mod, "-a") || Objects.equals(mod, "-d")) {
            String fileOneName = inputMass1[2];
            String fileTwoName = inputMass1[3];
            String fileOutName = inputMass1[1];
            BufferedReader br = null;
            int counterLineOneFile = 0;
            int counterLineOneFile1 = 0;
            int i = 0;
            int j = 0;
            int counterConcatMass = 0;
            try {
                File file = new File(fileOutName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                PrintWriter pw = new PrintWriter(file);

                br = new BufferedReader(new FileReader(fileOneName));
                String line;
                while ((line = br.readLine()) != null) {
                    counterLineOneFile++;
                }
                int[] mass1 = new int[counterLineOneFile];
                br.close();
                br = new BufferedReader(new FileReader(fileOneName));
                while ((line = br.readLine()) != null) {
                    mass1[i] = Integer.parseInt(line);
                    i++;
                }
                br.close();
                br = new BufferedReader(new FileReader(fileTwoName));
                while ((line = br.readLine()) != null) {
                    counterLineOneFile1++;
                }
                int[] mass2 = new int[counterLineOneFile1];
                br.close();
                br = new BufferedReader(new FileReader(fileTwoName));
                while ((line = br.readLine()) != null) {
                    mass2[j] = Integer.parseInt(line);
                    j++;
                }
                int[] result = new int[mass1.length + mass2.length];
                for (int c = 0; c < mass1.length; c++) {
                    result[c] = mass1[c];
                    counterConcatMass++;
                }
                for (int cc = 0; cc < mass2.length; cc++) {
                    result[counterConcatMass++] = mass2[cc];
                }


                if (Objects.equals(mod, "-d")) {
                    mergeSort(result);
                    sort(result);
                } else {
                    mergeSort(result);
                }
                pw.println(arrayToString(result));
                pw.close();

            } catch (IOException e) {
                System.out.print("Error: " + e);
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                }
            }

        } else {
            System.out.println("Error: The sort order is incorrect.");
            System.exit(1);
        }

    }
    public static int[] mergeSort (int[] array){
        int [] tmp;
        int [] currentSrc = array;
        int [] currentDest = new int [array.length];
        int size = 1;
        while (size < array.length) {
            for (int i = 0; i < array.length; i += 2 * size) {
                mergeData(currentSrc, i, currentSrc, i + size, currentDest, i, size);
            }

            tmp = currentSrc;
            currentSrc = currentDest;
            currentDest = tmp;

            size = size * 2;
            //System.out.println(arrayToString(currentSrc));
        }

        return currentSrc;
    }
    private static void mergeData (int [] mass1, int mass1Start, int[] mass2, int mass2Start,
                                   int [] dest, int destStart, int size) {
        int indx1 = mass1Start;
        int indx2 = mass2Start;

        int mass1End = Math.min(mass1Start + size, mass1.length);
        int mass2End = Math.min(mass2Start + size, mass1.length);

        int interCount = mass1End - mass1Start + mass2End - mass2Start;

        for (int i = destStart; i < destStart + interCount; i++) {
            if (indx1 < mass1End && (indx2 >= mass2End || mass1[indx1] < mass2[indx2])) {
                dest[i] = mass1[indx1];
                indx1++;
            } else {
                dest[i] = mass2[indx2];
                indx2++;
            }
        }


    }
    private static String arrayToString(int[] array){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++){
            if (i > 0 ){
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }
    public static int[] sort(int[] massive) {
        for (int i = 0; i < massive.length / 2; i++) {
            int tmp = massive[i];

            massive[i] = massive[massive.length - i - 1];
            massive[massive.length - i - 1] = tmp;
        }
        return massive;
    }
}