package daos;

import dtos.HealthProductDTO;
import persistence.model.HealthProduct;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class HealthProductDAOMock implements iDAO {
    private static int counter = 1;
    private static Map<Integer, HealthProductDTO> productDTOSet = new HashMap<>();

    @Override
    public Set<HealthProductDTO> getAll() {
        Set<HealthProductDTO> resultSet = new HashSet<>();
        for (Map.Entry<Integer, HealthProductDTO> entry : productDTOSet.entrySet()) {
            HealthProductDTO productDTO = entry.getValue();
            productDTO.setId(entry.getKey());
            resultSet.add(productDTO);
        }
        return resultSet;
    }

    @Override
    public HealthProductDTO getById(int id) {
        return productDTOSet.get(id);
    }

    @Override
    public Set<HealthProductDTO> getByCategory(String category) {
        Set<HealthProductDTO> list = new HashSet<>();
        for (Map.Entry<Integer, HealthProductDTO> entry : productDTOSet.entrySet()) {
            if (entry.getValue().getCategory().equals(category)) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    @Override
    public HealthProductDTO create(HealthProductDTO healthProduct) {
        healthProduct.setId(counter);
        productDTOSet.put(counter, healthProduct);
        counter++;
        return healthProduct;
    }

    @Override
    public HealthProductDTO update(HealthProductDTO healthProduct) {
        int id = healthProduct.getId();
        if (productDTOSet.containsKey(id)) {
            productDTOSet.put(id, healthProduct);
            return healthProduct;
        }
        return null;
    }

    @Override
    public HealthProductDTO delete(int id) {
        return productDTOSet.remove(id);
    }

    @Override
    public Set<HealthProductDTO> getTwoWeeksToExpire() {
        Set<HealthProductDTO> set = new HashSet<>();
        LocalDate twoWeeksAhead = LocalDate.now().plusWeeks(2);
        for (HealthProductDTO healthProductDTO : productDTOSet.values()) {
            if (healthProductDTO.getExpireDate().isBefore(twoWeeksAhead) || healthProductDTO.getExpireDate().isEqual(twoWeeksAhead)) {
                set.add(healthProductDTO);
            }
        }
        return set;
    }

    @Override
    public Set<HealthProductDTO> getProductsWithLessThan50Calories() {
        return getAll()
                .stream()
                .filter(healthProductDTO -> healthProductDTO.getCalories() < 50)
                .collect(Collectors.toSet());
    }

    @Override
    public List<String> getProductNames() {
        return getAll()
                .stream()
                .map(HealthProductDTO::getName)
                .collect(Collectors.toList());
    }

    @Override
    public double groupByCategories(String category) {
        return getAll()
                .stream()
                .filter(healthProductDTO -> healthProductDTO.getCategory().equals(category))
                .mapToDouble(HealthProductDTO::getPrice)
                .sum();
    }

    @Override
    public void addProductToStorage(int storageId, int productId) {

    }

    @Override
    public Set<HealthProduct> getProductsByStorageShelf(int storageId) {
        return Set.of();
    }
}