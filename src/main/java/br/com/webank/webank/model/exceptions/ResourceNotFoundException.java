package br.com.webank.webank.model.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }

    // Not Found = 404
    // Bad Request = 400
}
