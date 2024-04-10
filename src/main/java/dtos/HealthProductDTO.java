package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
// Task 1.3
public class HealthProductDTO {
    private static int counter = 1;
    private int id;
    private String category;
    private String name;
    private double price;
    private double calories;
    private String description;
    private LocalDate expireDate;
}
