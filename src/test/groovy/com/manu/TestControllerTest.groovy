package com.manu

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.server.types.files.StreamedFile
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class TestControllerTest extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    def 'failing test: should get file'() {
        when:
        def request = HttpRequest.GET("/tests/1")
        def response = client.toBlocking().exchange(request, StreamedFile)

        then:
        response.body.get().inputStream.text == "this is a test log 1"
    }

    def 'successful test: should get file'() {
        when:
        def request = HttpRequest.GET("/tests/1")
        def response = client.toBlocking().exchange(request, StreamedFile)

        then:
        new String(response.unpooledContent.array) == "this is a test log 1"
    }
}
