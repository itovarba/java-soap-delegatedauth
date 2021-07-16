package com.authenticationservice;

import com.sforce.soap.authentication.Authenticate;
import com.sforce.soap.authentication.AuthenticateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class DelegatedAuthentication {
	private static final String NAMESPACE_URI = "https://iberia-delegated-auth.herokuapp.com/authenticationService";

	private UserRepository userRepository;

	@Autowired
	public DelegatedAuthentication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deledatedAuthentication")
	@ResponsePayload
	public AuthenticateResult deledatedAuthentication(@RequestPayload Authenticate request) {
		AuthenticateResult response = new AuthenticateResult();
        response.setAuthenticated(false);

        if (userRepository.findUser(request.getUsername()) != null){
		    response.setAuthenticated(true);
        }
		return response;
	}
}