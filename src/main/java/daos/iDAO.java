package daos;

import dtos.HealthProductDTO;
import persistence.model.HealthProduct;

import java.util.List;
import java.util.Set;

public interface iDAO {

    Set<HealthProductDTO> getAll();

    HealthProductDTO getById(int id);

    Set<HealthProductDTO> getByCategory(String category);

    HealthProductDTO create(HealthProductDTO healthProduct);

    HealthProductDTO update(HealthProductDTO healthProduct);

    HealthProductDTO delete(int id);

    Set<HealthProductDTO> getTwoWeeksToExpire();

    Set<HealthProductDTO> getProductsWithLessThan50Calories();

    List<String> getProductNames();

    double groupByCategories(String category);

    void addProductToStorage(int storageId, int productId);

    Set<HealthProduct> getProductsByStorageShelf(int storageId);
}
