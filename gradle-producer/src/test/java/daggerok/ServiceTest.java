package daggerok;

import daggerok.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static java.time.Duration.ofMillis;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@Slf4j
@DisplayName("Service 😱")
@TestInstance(PER_CLASS)
class ServiceTest {

  Service service;

  /**
   * note: method can be static if you remove @TestInstance(PER_CLASS) test class annotation
   */
  @BeforeAll
  void beforeAll() {
    log.info("before all execution...");
  }

  /**
   * note: method can be static if you remove @TestInstance(PER_CLASS) test class annotation
   */
  @AfterAll
  void afterAll() {
    log.info("after all execution...");
  }

  @BeforeEach
  void setUp() {
    log.info("before each execution...");
    service = new ServiceImpl();
  }

  @AfterEach
  void tearDown() {
    log.info("after each execution...");
  }

  @Test
  @DisplayName("get output should return default value")
  void getOutputNoArguments() {

    log.info("starting test execution...");

    assertTimeout(ofMillis(100), () -> {
      // Simulate task that takes 10 ms.
      Thread.sleep(10);
      assertThat(service.getOutput(), equalTo("Hello, World!"));
    });

    log.info("test execution done.");
  }

  @Test
  @DisplayName("should return transformed value")
  void getOutput() {

    log.info("starting test execution...");

    assertAll("get output",
              () -> {

                final String output = service.getOutput("Max");
                assertThat(output, equalTo("Hello, Max!"));

                // Executed only if the previous assertion is valid.
                assertAll("output",
                          () -> {
                            final String name = output.split(" ")[1];
                            assertThat("Should starts from capital", name, startsWith("M"));
                          },
                          () -> assertThat("Should ends with exclamation mark", output, endsWith("!"))
                );
              },
              () -> {
                // Grouped assertion, so processed independently
                // of results of first name assertions.

                final String output = service.getOutput("ololo");
                assertThat(output, equalTo("Hello, Ololo!"));

                final String name = output.split(" ")[1];

                // Executed only if the previous assertion is valid.
                assertAll("output",
                          () -> assertThat("Should starts from capital", name, startsWith("O")),
                          () -> assertThat("Should ends with exclamation mark", output, endsWith("!"))
                );
              }
    );

    log.info("test execution done.");
  }
}
