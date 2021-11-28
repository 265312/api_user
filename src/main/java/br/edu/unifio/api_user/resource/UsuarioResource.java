package br.edu.unifio.api_user.resource;
import br.edu.unifio.api_user.domain.Usuario;
import br.edu.unifio.api_user.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;  
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "email"));
        return usuarios;
    }
    @PostMapping
    public Usuario inserir (@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioSalvo;
    }
    @DeleteMapping("/{USR_ID}")
    public void excluir (@PathVariable Integer USR_ID) {
        usuarioRepository.deleteById(USR_ID);
    }

    @PutMapping("/{USR_ID}")
    public Usuario editar (@PathVariable Integer USR_ID, @RequestBody Usuario usuarioEntrada) {
        Optional<Usuario> opcional = usuarioRepository.findById(USR_ID);
        Usuario usuarioSaida= opcional.get();

        usuarioSaida.setEmail(usuarioEntrada.getEmail());
        usuarioSaida.setSenha(usuarioEntrada.getSenha());


        Usuario usuarioSalvo = usuarioRepository.save(usuarioSaida);
        return usuarioSalvo;
    }
}
