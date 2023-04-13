package edu.miu.shoppingcartsystem.repository;


import edu.miu.shoppingcartsystem.model.ERole;
import edu.miu.shoppingcartsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
