package com.timing.functional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicReference;

public class TimingTest {
  @AfterEach
  public void tearDown() throws Exception { }

  @Test
  public void test() {
    final String description = "Supply carrot";
    
    // significa que este código se ejecutará de manera segura incluso si algo
    // dentro de la función cronometrada descarga el cálculo a un hilo
    // diferente. Delinea claramente lo que puede cambiar, solo el valor en esta
    // referencia atómica.
    AtomicReference<String> output = new AtomicReference<>("");
       
    Timing.timed(description,
        () -> "carrot",
        output::set);
    
    assert(output.get().contains(description));
  }
}
