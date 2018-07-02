package org.letscode.restfull.a2lpo.sarafan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundExceptions extends RuntimeException {
}