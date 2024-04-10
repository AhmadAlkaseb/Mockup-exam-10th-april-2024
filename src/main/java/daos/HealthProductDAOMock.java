package daos;

import dtos.HealthProductDTO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HealthProductDAOMock {
    private static int counter = 1;
    private static Map<Integer, HealthProductDTO> productDTOSet = new HashMap<>();

    public static Set<HealthProductDTO> getAll() {
        Set<HealthProductDTO> resultSet = new HashSet<>();
        for (Map.Entry<Integer, HealthProductDTO> entry : productDTOSet.entrySet()) {
            HealthProductDTO productDTO = entry.getValue();
            productDTO.setId(entry.getKey());
            resultSet.add(productDTO);
        }
        return resultSet;
    }

    public static HealthProductDTO getById(int id) {
        return productDTOSet.get(id);
    }

    public static Set<HealthProductDTO> getByCategory(String category) {
        Set<HealthProductDTO> list = new HashSet<>();
        for (Map.Entry<Integer, HealthProductDTO> entry : productDTOSet.entrySet()) {
            if (entry.getValue().getCategory().equals(category)) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    public static HealthProductDTO create(HealthProductDTO healthProduct) {
        healthProduct.setId(counter);
        productDTOSet.put(counter, healthProduct);
        counter++;
        return healthProduct;
    }

    public static HealthProductDTO update(HealthProductDTO healthProduct) {
        int id = healthProduct.getId();
        if (productDTOSet.containsKey(id)) {
            productDTOSet.put(id, healthProduct);
            return healthProduct;
        }
        return null;
    }

    public static HealthProductDTO delete(int id) {
        return productDTOSet.remove(id);
    }

    public static Set<HealthProductDTO> getTwoWeeksToExpire() {
        Set<HealthProductDTO> set = new HashSet<>();
        LocalDate twoWeeksAhead = LocalDate.now().plusWeeks(2);
        for (HealthProductDTO healthProductDTO : productDTOSet.values()) {
            if (healthProductDTO.getExpireDate().isBefore(twoWeeksAhead) || healthProductDTO.getExpireDate().isEqual(twoWeeksAhead)) {
                set.add(healthProductDTO);
            }
        }
        return set;
    }
}