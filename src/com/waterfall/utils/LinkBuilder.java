package com.waterfall.utils;

import javax.ws.rs.core.UriInfo;

import com.waterfall.RESTResources.UserRestResource;
import com.waterfall.models.LinkModel;

public class LinkBuilder {

	public LinkBuilder( ) {

	}
	
	public static LinkModel buildSelfLink(Class entityClass, UriInfo uriInfo, Long id, String relation) {
		String uri = uriInfo.getBaseUriBuilder()
		.path(entityClass) // ger http://localhost:8080/waterfall/users
		.path(Long.toString(id))             // {userId}
		.build()
		.toString();
		
		System.out.println(uri);
		
		LinkModel linkModel = new LinkModel();
		linkModel.setUri(uri);
		linkModel.setRelation(relation);
		
		return linkModel;
		
	}
	
	public static LinkModel buildDropLink(Class entityClass, UriInfo uriInfo, Long id, String relation) {
		String uri = uriInfo.getBaseUriBuilder()
		.path(entityClass) // ger http://localhost:8080/waterfall/users
		.path(Long.toString(id))
		.path("drops")
		.build()
		.toString();
		
		System.out.println(uri);
		
		LinkModel linkModel = new LinkModel();
		linkModel.setUri(uri);
		linkModel.setRelation(relation);
		
		return linkModel;
		
	}
	
	public static LinkModel buildCommentLink(Class entityClass, UriInfo uriInfo, Long id, String relation) {
		String uri = uriInfo.getBaseUriBuilder()
		.path(entityClass) // ger http://localhost:8080/waterfall/users
		.path(Long.toString(id))
		.path("comments")
		.build()
		.toString();
				
		System.out.println(uri);
				
		LinkModel linkModel = new LinkModel();
		linkModel.setUri(uri);
		linkModel.setRelation(relation);
				
		return linkModel;
	}
	
	public static LinkModel buildOwnerLink(Class entityClass, UriInfo uriInfo, Long id, String relation) {
		String uri = uriInfo.getBaseUriBuilder()
		.path(entityClass) // ger http://localhost:8080/waterfall/users
		.path(Long.toString(id))
		.build()
		.toString();
				
		System.out.println(uri);
				
		LinkModel linkModel = new LinkModel();
		linkModel.setUri(uri);
		linkModel.setRelation(relation);
				
		return linkModel;
	}
	
}
