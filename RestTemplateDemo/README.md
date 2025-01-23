## RestTemplateDemo

This project was primarily intended to experiment around with RestTemplate library, however with time, some ideas were added.

### Handling RestTemplate errors
When using RestTemplate you can either get a client-side or a server-side(remote) error.
I tried to have a generic implementetion of catch this 2 types of errors.

Perhaps I should go beyond and catch errors by status.

### Using `@ControllerAdvice` for Generic Error Handling
Returns a response entity in case an error in a program is caught

### Mapping java objects between DTO's and Domain model
Configuring `Mapstruct` combined with `lombok` was time consumint. There was an error regarding the `annotationProcessorPaths` which was really frustrating.

Using ModelMapper was quite straight forward but I didn't dig deeper on how to use this 2 libraries.
