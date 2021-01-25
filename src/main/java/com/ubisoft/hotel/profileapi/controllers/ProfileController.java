package com.ubisoft.hotel.profileapi.controllers;


import com.ubisoft.hotel.profileapi.vo.Profile;
import com.ubisoft.hotel.profileapi.controller.util.HeaderUtil;
import com.ubisoft.hotel.profileapi.repository.ProfileRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.ejb.Timeout;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.slf4j.Logger;

/**
 * REST controller for managing Profile.
 */
@Path("/profile")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// @RolesAllowed(USER)
public class ProfileController {

    @Inject
    private Logger log;

    @Inject
    private ProfileRepository profileRepository;

    private static final String ENTITY_NAME = "profile";

    @APIResponse(responseCode = "200", description = "OK")
    @GET
    @Path("/info")
    public Response info() {        
        return Response.ok(profileRepository.getEntityManager().getProperties()).build();
    }    
    
    /**
     * POST : Create a new profile.
     *
     * @param profile the profile to create
     * @return the Response with status 201 (Created) and with body the new
     * profile, or with status 400 (Bad Request) if the profile has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @Operation(summary = "create a new profile", description = "Create a new profile")
    @APIResponse(responseCode = "201", description = "Created")
    @APIResponse(responseCode = "400", description = "Bad Request")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProfile(Profile profile) throws URISyntaxException {
        log.debug("REST request to save Profile : {}", profile);
        profileRepository.create(profile);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/profile/" + profile.getId())),
                ENTITY_NAME, profile.getId().toString())
                .entity(profile).build();
    }

    /**
     * PUT : Updates an existing profile.
     *
     * @param profile the profile to update
     * @return the Response with status 200 (OK) and with body the updated
     * profile, or with status 400 (Bad Request) if the profile is not valid, or
     * with status 500 (Internal Server Error) if the profile couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @Operation(summary = "update profile", description = "Updates an existing profile")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "400", description = "Bad Request")
    @APIResponse(responseCode = "500", description = "Internal Server Error")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProfile(Profile profile) throws URISyntaxException {
        log.debug("REST request to update Profile : {}", profile);
        profileRepository.edit(profile);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), ENTITY_NAME, profile.getId().toString())
                .entity(profile).build();
    }

    /**
     * GET : get all the profiles.
     *
     * @return the Response with status 200 (OK) and the list of profiles in
     * body
     *
     */
    @Timed
    @Operation(summary = "get all the profiles")
    @APIResponse(responseCode = "200", description = "OK")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout
    public List<Profile> getAllProfiles() {
        log.debug("REST request to get all Profiles");
        List<Profile> profiles = profileRepository.findAll();
        return profiles;
    }

    /**
     * GET /:id : get the "id" profile.
     *
     * @param id the id of the profile to retrieve
     * @return the Response with status 200 (OK) and with body the profile, or
     * with status 404 (Not Found)
     */
    @Timed
    @Operation(summary = "get the profile")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "404", description = "Not Found")
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile(@PathParam("id") Long id) {
        log.debug("REST request to get Profile : {}", id);
        Profile profile = profileRepository.find(id);
        return Optional.ofNullable(profile)
                .map(result -> Response.status(Response.Status.OK).entity(profile).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" profile.
     *
     * @param id the id of the profile to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @Operation(summary = "remove the profile")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "404", description = "Not Found")
    @DELETE
    @Path("/{id}")
    public Response removeProfile(@PathParam("id") Long id) {
        log.debug("REST request to delete Profile : {}", id);
        profileRepository.remove(profileRepository.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), ENTITY_NAME, id.toString()).build();
    }

}
