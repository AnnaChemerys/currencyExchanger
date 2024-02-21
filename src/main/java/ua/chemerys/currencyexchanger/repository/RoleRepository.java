package ua.chemerys.currencyexchanger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chemerys.currencyexchanger.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String userRole);
}
