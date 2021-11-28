package br.edu.unifio.api_user.repository;

import br.edu.unifio.api_user.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
