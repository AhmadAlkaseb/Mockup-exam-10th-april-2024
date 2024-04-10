package daos;

import dtos.HealthProductDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import persistence.model.HealthProduct;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class storageDAO implements iDAO {
    private static EntityManagerFactory emf;

    public storageDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private HealthProductDTO generatedDTO(HealthProduct healthProduct) {
        return HealthProductDTO.builder()
                .id(healthProduct.getId())
                .category(healthProduct.getCategory())
                .name(healthProduct.getName())
                .price(healthProduct.getPrice())
                .calories(healthProduct.getCalories())
                .description(healthProduct.getDescription())
                .expireDate(healthProduct.getExpireDate())
                .build();
    }

    @Override
    public Set<HealthProductDTO> getAll() {
        Set<HealthProductDTO> set = new HashSet<>();
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<HealthProduct> query = em.createQuery("SELECT h FROM health_product h", HealthProduct.class);
            List<HealthProduct> list = query.getResultList();
            for (HealthProduct h : list) {
                set.add(generatedDTO(h));
            }
        }
        return set;
    }

    @Override
    public HealthProductDTO getById(int id) {
        return null;
    }

    @Override
    public Set<HealthProductDTO> getByCategory(String category) {
        return Set.of();
    }

    @Override
    public HealthProductDTO create(HealthProductDTO healthProduct) {
        return null;
    }

    @Override
    public HealthProductDTO update(HealthProductDTO healthProduct) {
        return null;
    }

    @Override
    public HealthProductDTO delete(int id) {
        return null;
    }

    @Override
    public Set<HealthProductDTO> getTwoWeeksToExpire() {
        return Set.of();
    }

    @Override
    public Set<HealthProductDTO> getProductsWithLessThan50Calories() {
        return Set.of();
    }

    @Override
    public List<String> getProductNames() {
        return List.of();
    }

    @Override
    public double groupByCategories(String category) {
        return 0;
    }

    @Override
    public void addProductToStorage(int storageId, int productId) {

    }

    @Override
    public Set<HealthProduct> getProductsByStorageShelf(int storageId) {
        return Set.of();
    }
}
