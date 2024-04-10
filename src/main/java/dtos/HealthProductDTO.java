package dtos;

import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
