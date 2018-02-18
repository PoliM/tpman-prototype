package ch.ocram.tpmanprototype.masterdata.adapter.rest;

import ch.ocram.tpmanprototype.masterdata.domain.CategoryRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class MasterDataResource {

    @Inject
    private CategoryRepository categoryRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProperties() {
        return Response.ok(categoryRepository.findAll()).build();
    }
}
