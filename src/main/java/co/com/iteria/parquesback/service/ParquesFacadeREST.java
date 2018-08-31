/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.iteria.parquesback.service;

import co.com.iteria.parquesback.Parques;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author Juan Sebastián Rodriguez Moncayo
 * 
 */
@Stateless
@Path("/parks")
public class ParquesFacadeREST extends AbstractFacade<Parques> {

    @PersistenceContext(unitName = "co.com.iteria_ParquesBack_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ParquesFacadeREST() {
        super(Parques.class);
    }

    //Metodo para crear los parques
    @POST    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(@Context HttpServletResponse response, Parques entity) {
        int secuencia = last()+1;
        entity.setStatus("Open");
        entity.setId(String.valueOf(secuencia));
        
        create(entity);
        response.setHeader("Location", "/parks/"+String.valueOf(secuencia));
        
        return Response.ok(entity).status(201).build();
    }
    
    @Override
    public void create(Parques entity){
       super.create(entity); 
    }
    

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") String id, Parques entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    //Metodo de consulta de parques por id
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject find(@PathParam("id") String id) {

        Parques parqueTemp = super.find(id);
        JsonObject json = Json.createObjectBuilder()
                .add("id", parqueTemp.getId())
                .add("name", parqueTemp.getName())
                .add("state", parqueTemp.getState())
                .add("capacity", parqueTemp.getCapacity())
                .add("status", parqueTemp.getStatus())
                .build();
        
        return json;
        
    }

    //Metodo encargado de retornar todos las parques
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray obtenerTodo(@DefaultValue("null") @QueryParam("status") String status) {
         List<Parques> lstparques = new ArrayList<Parques>();
        if(status.equalsIgnoreCase("null")){
            lstparques = super.findAll();
        }else{
            lstparques = super.findStatus(status);
        }
       
        JsonArrayBuilder parqueArray = Json.createArrayBuilder();
        for (Parques parque : lstparques) {
            JsonObjectBuilder json = Json.createObjectBuilder()
                    .add("id", parque.getId())
                    .add("name", parque.getName())
                    .add("state", parque.getState())
                    .add("capacity", parque.getCapacity())
                    .add("status", parque.getStatus());
            parqueArray.add(json);
        }

        return parqueArray.build(); 
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Parques> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    public int last() {
        return Integer.parseInt(getEntityManager().createQuery("select COALESCE(max(p.id),0) from Parques p").getSingleResult().toString());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
