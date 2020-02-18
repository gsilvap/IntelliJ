#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

#set($class=${NAME}+"Controller")
#set($serviceclass=$class.replace("Controller","Service"))
#set($serviceclassname=$serviceclass.substring(0,1).toLowerCase()+$serviceclass.substring(1))
#set($subject=$class.replace("Controller",""))
#set($api=$subject.toLowerCase())


import com.arcelormittal.ninasteel.nina_ui.domain.model.${subject}Entity;
import com.arcelormittal.ninasteel.nina_ui.service.${serviceclass};
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PATCH;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Controller
@Tag(name = "Restful")
@Path(value = "/${api}s")
public class ${class}{
    private final ${serviceclass} ${serviceclassname};

    public ${class}(${serviceclass} $serviceclassname){
        this.${serviceclassname} = ${serviceclassname};
    }

    @GET
    @Path(value = "/")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Administrator", "Developer", "Business Operator"})
    public List<${subject}Entity> get${subject}(){
        return ${serviceclassname}.findAll();
    }

    @GET
    @Path(value = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Administrator", "Developer", "Business Operator"})
    public ${subject}Entity get${subject}(@PathParam("id") Integer id) {
        return ${serviceclassname}.findOne(id).orElseThrow(() -> new NotFoundException("Entity not found"));
    }

    @POST
    @Path(value = "/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Administrator", "Developer", "Business Operator"})
    public ${subject}Entity add${subject}(${subject}Entity requestBody) {
        return ${serviceclassname}.save${subject}Restful(requestBody);
    }

    @PUT
    @Path(value = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Administrator", "Developer", "Business Operator"})
    public ${subject}Entity update${subject}(@PathParam("id") Integer id, ${subject}Entity requestBody) {
        return ${serviceclassname}.update${subject}Restful(id, requestBody);
    }

    @PATCH
    @Path(value = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Administrator", "Developer", "Business Operator"})
    @Operation(hidden = true)
    public ${subject}Entity patch${subject}(@PathParam("id") Integer id, ${subject}Entity requestBody) {
        return ${serviceclassname}.update${subject}Restful(id, requestBody);
    }

    @PUT
    @Path(value = "/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Administrator", "Developer", "Business Operator"})
    public List<${subject}Entity> patch${subject}(List<${subject}Entity> requestBody) {
        List<${subject}Entity> responseList = new ArrayList<>();

        for (${subject}Entity eachEntity : requestBody) {
            ${subject}Entity responseEntity = ${serviceclassname}.update${subject}Restful(eachEntity.getId(), eachEntity);
            responseList.add(responseEntity);
        }

        return responseList;
    }

    @DELETE
    @Path(value = "/{id}")
    @RolesAllowed({"Administrator", "Developer", "Business Operator"})
    public void delete${subject}(@PathParam("id") Integer id) {
        ${serviceclassname}.delete${subject}Restful(id);
    }

    @DELETE
    @Path(value = "/")
    @RolesAllowed({"Administrator", "Developer", "Business Operator"})
    public void delete${subject}(@QueryParam("id") List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new WebApplicationException(
                    Response.status(Response.Status.METHOD_NOT_ALLOWED)
                            .entity("Cannot delete full collection")
                            .build()
            );
        } else {
            ${serviceclassname}.delete${subject}Restful(ids);
        }
    }
}