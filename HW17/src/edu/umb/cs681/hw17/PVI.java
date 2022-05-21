package edu.umb.cs681.hw17;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PVI {

    public static void main(String args[]) {
        Path tmp = FileSystems.getDefault().getPath("").toAbsolutePath();
        String pathName = tmp+"\\PVI2.csv";
        Path path = Paths.get (pathName);

        System.out.println(pathName);
        
        System.out.println("reached");
        try (Stream<String> lines = Files.lines(path)) {
        	
            List<List<String>> matrix = lines.map(line -> {
                return Stream.of( line.split(",") ) .map(value->value.substring(0, value.length()))
                        .collect( Collectors.toList() ); }) .collect( Collectors.toList() );
            System.out.println(matrix);
            System.out.println("hi");

            List M = matrix.stream().parallel().filter((i) -> i.get(4).contains("Massachusetts")).collect(Collectors.toList());

            List suffolk = matrix.stream().parallel().filter((i) -> i.get(5).contains("Suffolk")).collect(Collectors.toList()).get(0);
            System.out.println("\n1. Number of deaths occurred in the Suffolk county of the Massachusetts state are: " + suffolk.get(7));

            String DeathsinM = matrix.stream().parallel().filter((i) -> i.get(4).contains("Massachusetts"))
                    .map((i) -> i.get(7)).reduce("0", (subtotal, element) -> String.valueOf(Integer.parseInt(subtotal) + Integer.parseInt(element)));

            System.out.println("\n2. Total number of deaths in State of Massachusetts are: " + DeathsinM);

            System.out.println("\n3. Average number of deaths in State of Massachusetts are: " + Integer.parseInt(DeathsinM)/M.size());





        } catch (IOException ex) {
            System.out.println("Exception");
            // do something or re-throw...
        }
    }

}