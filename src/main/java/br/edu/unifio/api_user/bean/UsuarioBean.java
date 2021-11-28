package br.edu.unifio.api_user.bean;
import br.edu.unifio.api_user.domain.Usuario;
import lombok.Data;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@ViewScoped
@Data
public class UsuarioBean {
    private Usuario usuario;
    private List<Usuario> usuarios;

    public void novo() {

        usuario= new Usuario();
        RestTemplate restTemplate = new RestTemplate();

    }
    public void listar(){
        //produtos = produtoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        RestTemplate restTemplate = new RestTemplate();
        Usuario[] vetor = restTemplate.getForObject("http://localhost:8080/usuarios", Usuario[].class);
        usuarios = Arrays.asList(vetor);
    }
    public void salvar(){
        try {
            // produtoRepository.save(produto);

            RestTemplate restTemplate = new RestTemplate();

            if(usuario.getUSR_ID() == null) {
                restTemplate.postForObject("http://localhost:8080/usuarios", usuario, Usuario.class);
            } else {
                HttpEntity<Usuario> httpEntity = new HttpEntity<>(usuario);
                restTemplate.exchange(
                        "http://localhost:8080/usuarios/" + usuario.getUSR_ID(),
                        HttpMethod.PUT,
                        httpEntity,
                        Usuario.class
                );
            }

            Messages.addFlashGlobalInfo("Usuario salvo com sucesso");

           // Faces.navigate("produto-listagem.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            Messages.addGlobalError("Já existe um usuario cadastrado para o nome informado");
        }
    }

    public void selecionarEdicao(Usuario usuario) {
        Faces.setFlashAttribute("usuario", usuario);
        Faces.navigate("usuario-edicao.xhtml?faces-redirect=true");
    }
    public void selecionarExclusao(Usuario usuario){
        Faces.setFlashAttribute("usuario", usuario);
       Faces.navigate("usuario-exclusao.xhtml?faces-redirect=true");
    }
    public void carregar(){
        usuario = Faces.getFlashAttribute("usuario");

        RestTemplate restTemplate = new RestTemplate();

        if (usuario == null) {
            Faces.navigate("usuario-listagem.xhtml?faces-redirect=true");
        }
    }
    public void excluir(){
        try {

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete("http://localhost:8080/usuarios/" + usuario.getUSR_ID());

            Messages.addFlashGlobalInfo("Usuario removido com sucesso");

           Faces.navigate("usuario-listagem.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            Messages.addGlobalError("O usuario selecionado está vinculado com outros registros");
        }
    }
}