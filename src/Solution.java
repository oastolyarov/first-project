import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        int coolVariable = -1;
        int perfectVariable = 7;
        int awesomeVariable = (--coolVariable * 10) + coolVariable + (--perfectVariable + --coolVariable);
                                    // -30                -3                         3
        System.out.println(awesomeVariable);
    }
}
