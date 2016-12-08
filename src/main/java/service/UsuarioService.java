package service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import controle.Validacao;
import modelo.Usuario;
import controle.ListaUsuario;


@ApplicationPath("/resource")
@Path("/usuario")
public class UsuarioService extends Application {
	private Validacao validacao = new Validacao();

	@POST
	@Path("/login")
	@Consumes("application/json")
	public Response verificarUsuario(Usuario usuario) {
		System.out.println("Chamou");
		if (validacao.validarLogin(usuario)) {
			return Response.status(Response.Status.OK).entity("{\"status\": true}").build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).entity("{\"status\": false}").build();
	}

	@POST
	@Path("/inserir")
	@Consumes("application/json")
	public Response inserir(Usuario usuario) {
		System.out.println("Entrou");
		try {
			if (!validacao.validarCPFUsuario(usuario)) {
				if (!validacao.verificarEmailUsuario(usuario)) {
					if (!validacao.verificarCPFUsuario(usuario)) {
						ListaUsuario.usuarios.add(usuario);
						return Response.status(Response.Status.OK).entity(Response.Status.OK.toString()).build();
					} else {
						return Response.status(Response.Status.FOUND).encoding(Response.Status.FOUND.toString())
								.build();
					}
				} else {
					return Response.status(Response.Status.FOUND).encoding(Response.Status.FOUND.toString()).build();
				}
			} else {
				return Response.status(Response.Status.FOUND).encoding(Response.Status.FOUND.toString()).build();
			}

		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/buscarNome/{nome}")
	@Produces("application/json")
	public Response buscarPorNome(@PathParam("nome") String nome) {
		return Response.status(200).entity(new GenericEntity<List<Usuario>>(
				ListaUsuario.usuarios.stream().filter(us -> us.getNome().equals(nome)).collect(Collectors.toList())) {
		}).build();
	}

	@GET
	@Path("/buscarCPF/{cpf}")
	@Produces("application/json")
	public Response buscarCPF(@PathParam("cpf") String cpf) {

		return Response.status(200).entity(new GenericEntity<List<Usuario>>(
				ListaUsuario.usuarios.stream().filter(us -> us.getCpf().equals(cpf)).collect(Collectors.toList())) {
		}).build();
	}

	@GET
	@Path("/buscarEmail/{email}")
	@Produces("application/json")
	public Response buscarEmail(@PathParam("email") String email) {
		return Response.status(200).entity(new GenericEntity<List<Usuario>>(
				ListaUsuario.usuarios.stream().filter(us -> us.getEmail().equals(email)).collect(Collectors.toList())) {
		}).build();
	}

	@GET
	@Path("/buscarDataNascimento/{dataNascimento}")
	@Produces("application/json")
	public Response buscarPorDataNascimento(@PathParam("dataNascimento") LocalDate dataNascimento) {
		return Response
				.status(200).entity(ListaUsuario.usuarios.stream()
						.filter(us -> us.getDtNascimento().equals(dataNascimento)).collect(Collectors.toList()))
				.build();
	}

	@GET
	@Path("/listar")
	@Produces("application/json")
	public Response listarTodos() {
		return Response.status(200).entity(new GenericEntity<List<Usuario>>(ListaUsuario.usuarios) {
		}).build();
	}

}
