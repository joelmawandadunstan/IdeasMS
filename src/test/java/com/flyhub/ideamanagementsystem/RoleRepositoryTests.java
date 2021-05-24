
  package com.flyhub.ideamanagementsystem;
  
  import static org.assertj.core.api.Assertions.assertThat;
  
  import java.util.List;
  
  import org.junit.jupiter.api.Test; import
  org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
  import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
  import org.springframework.test.annotation.Rollback;
  
  import com.flyhub.ideamanagementsystem.entity.Role; import
  com.flyhub.ideamanagementsystem.repositories.RoleRepository;
  
  import
  org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.
  Replace;;
  
  @DataJpaTest
  
  @AutoConfigureTestDatabase(replace = Replace.NONE)
  
  @Rollback(false) public class RoleRepositoryTests {
  
  @Autowired RoleRepository roleRepo;
  
  @Test public void testCreateRoles() { Role user = new Role("User"); Role
  admin = new Role("Admin"); Role customer = new Role("Customer");
  
  roleRepo.saveAll(List.of(user, admin, customer));
  
  List<Role> listRoles = roleRepo.findAll();
  
  assertThat(listRoles.size()).isEqualTo(3); }
  
  }
 