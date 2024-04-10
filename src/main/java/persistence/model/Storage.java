package persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity(name = "storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "updated_time_stamp")
    private LocalTime updatedTimeStamp;
    @Column(name = "total_amount")
    private double totalAmount;
    @Column(name = "shelf_number")
    private int shelfNumber;
    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL)
    private Set<HealthProduct> healthProductList = new HashSet<>();

    @PreUpdate
    private void onUpdate() {
        this.updatedTimeStamp = LocalTime.now();
    }

    public Storage(double totalAmount, int shelfNumber) {
        this.updatedTimeStamp = LocalTime.now();
        this.totalAmount = totalAmount;
        this.shelfNumber = shelfNumber;
    }
}
