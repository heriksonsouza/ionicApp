package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import model.Usuario;

@Path("/usuario")
@ApplicationPath("/resource")
public class UsuarioService extends Application {

	@POST
	@Path("/autenticar")
	@Produces("application/json")
	@Consumes("application/json")
	public Response autentica(Usuario us) {
		System.out.println("pessoa: " + p.getEmail());
		List<Usuario> lUs = listaComStatus(Usuario.class);
		for (Pessoa pes : lp) {
			if (pes.getEmail().equals(p.getEmail()) && pes.getSenha().equals(p.getSenha())) {
				return Response.status(200).entity("{\"resposta\": \"" + String.valueOf(pes.getId()) + "\"}").build();
			}
		}

		return Response.status(200).entity("{\"resposta\": \"fail\"}").build();

	}

	@GET
	@Path("/recuperaId")
	@Produces("application/json")
	public Response recuperaId(@QueryParam("id") Long id) {
		Usuario us = (Usuario) dao.buscarPorId(Usuario.class, id);
		if (p != null && p.getId() != null) {
			return Response.status(200).entity(p).build();
		}
		return Response.status(200).entity("fail").build();

	}

	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Usuario> listar() {
		Usuario usl = new Usuario();
		usl.setNome("Herikson");
		List<Usuario> us1 = new ArrayList<Usuario>();
		us1.add(us1);

		System.out.println("Chamou: ");

		// return Response.status(200).entity("{nome:\"frank\"}").build();
		// return Response.status(200).header("Access-Control-Allow-Origin",
		// "*")
		// .entity(lc).build();
		return lc;
		// try {
		// List<Cliente> clientes = dao.lista(Cliente.class);
		// return clientes;
		// } catch (Exception e) {
		// throw new WebApplicationException(500);
		// }
	}

	@GET
	@Path("/hello")
	public Response hello() {
		return Response.status(200).entity("chamou.").build();

	}

	@GET
	@Path("/deaf")
	public Response deaf(@QueryParam("texto") String texto) {
		System.out.println(texto);
		System.out.println(texto);
		return Response.status(200).entity("foi").build();

	}

	@GET
	@Path("/buscar/{id_cliente}")
	@Produces("application/json")
	public Cliente buscar(@PathParam("id_cliente") Long id_cliente) {
		try {
			Cliente cliente = (Cliente) dao.buscarPorId(Cliente.class, id_cliente);

			return cliente;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@POST
	@Path("/inserir")
	@Produces
	@Consumes("application/json")
	public Response cadastrar(Usuario objUsuario) {
		System.out.println("Usuário: " + objUsuario.getNome());
		return Response.status(200).entity("{\"nome\":\"frank\"}").build();
		// return Response.status(200).entity("asdfsadf").build();
		// try {
		// dao.inserir(objClinte);
		// return Response.status(200).entity("cadastro realizado.").build();
		// } catch (Exception e) {
		// throw new WebApplicationException(500);
		// }
	}

}
