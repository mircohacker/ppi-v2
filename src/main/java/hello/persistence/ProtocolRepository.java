package hello.persistence;

import hello.model.Protocol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProtocolRepository extends JpaRepository<Protocol, Long> {

}