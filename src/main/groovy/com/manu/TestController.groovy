package com.manu

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.types.files.StreamedFile

@CompileStatic
@Controller("/")
class TestController {
    @Produces(MediaType.TEXT_PLAIN)
    @Get("/tests/{id}")
    HttpResponse<StreamedFile> getFile(String id){
        def LOGS = "this is a test log $id"
        def response = new StreamedFile(new ByteArrayInputStream(LOGS.bytes), MediaType.APPLICATION_OCTET_STREAM_TYPE)
        return response ? HttpResponse.ok(response) : HttpResponse.<StreamedFile>notFound()
    }
}
