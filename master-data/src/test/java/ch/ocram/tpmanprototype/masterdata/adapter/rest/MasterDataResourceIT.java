package ch.ocram.tpmanprototype.masterdata.adapter.rest;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class MasterDataResourceIT {

    @Test
    public void shouldReturnMasterData() {
        String port = System.getProperty("liberty.test.port");
        System.out.println("PORT: "+port);
        String war = System.getProperty("war.name");
        System.out.println("WAR: " + war);
        String url = "http://localhost:" + port + "/" + war + "/";

        Client client = ClientBuilder.newClient();
        client.register(JsrJsonpProvider.class);

        WebTarget target = client.target(url + "master-data");
        Response response = target.request().get();

        assertEquals("Incorrect response code from " + url, 200, response.getStatus());

        String entity = response.readEntity(String.class);
        System.out.println(entity);
        assertThat(entity).contains("{\"id\":2,\"name\":\"Celestial\"}");

        response.close();
    }
}